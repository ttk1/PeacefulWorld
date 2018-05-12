package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.api.BlockAdapter;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.model.HistoryChainModel;
import net.ttk1.peacefulworld.model.PlayerModel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ttk1 and mmtsk
 */
public class HistoryImpl implements History {
    private final HistoryChainModel historyChain;
    HistoryImpl(long time, Entity player, History parent, BlockAdapter block, BlockAdapter blockReplaced) {
        PlayerModel playerModel = PlayerModel.find.query().where().eq("uuid", player.getUniqueId().toString()).findOne();
        if (playerModel == null) {
            playerModel = new PlayerModel(player.getUniqueId().toString(), player.getName());
            playerModel.save();
        }
        long playerId = playerModel.getId();

        if (parent == null) {
            // parent, originが無い場合は0Lをセットする
            // find.byIdでnullが帰る値なら何でもよい。（auto_incrementは1開始なので0以下の値なら何でもよい。）
            historyChain = new HistoryChainModel(
                    time,
                    0,
                    0,
                    playerId,
                    false,
                    block.getLocation().getWorld().getName(),
                    block.getLocation().getBlockX(),
                    block.getLocation().getBlockY(),
                    block.getLocation().getBlockZ(),
                    block.getType().getId(),
                    block.getTypeId(),
                    block.getData(),
                    blockReplaced.getType().getId(),
                    blockReplaced.getTypeId(),
                    blockReplaced.getData()
            );
        } else {
            historyChain = new HistoryChainModel(
                    time,
                    parent.getOrigin().getId(),
                    parent.getId(),
                    playerId,
                    false,
                    block.getLocation().getWorld().getName(),
                    block.getLocation().getBlockX(),
                    block.getLocation().getBlockY(),
                    block.getLocation().getBlockZ(),
                    block.getType().getId(),
                    block.getTypeId(),
                    block.getData(),
                    blockReplaced.getType().getId(),
                    blockReplaced.getTypeId(),
                    blockReplaced.getData()
            );
        }
        historyChain.save();
    }

    HistoryImpl(HistoryChainModel historyChain) {
        this.historyChain = historyChain;
    }

    @Override
    public long getId() {
        return historyChain.getId();
    }

    @Override
    public long getTime() {
        return historyChain.getTime();
    }

    @Override
    public String getUuid() {
        PlayerModel playerModel = PlayerModel.find.byId(historyChain.getPlayer());
        if (playerModel == null) {
            // 通常はnullになることは無い
            // TODO:例外処理
            return null;
        } else {
            return playerModel.getUuid();
        }
    }

    @Override
    public History getOrigin() {
        return new HistoryImpl(HistoryChainModel.find.byId(historyChain.getOrigin()));
    }

    @Override
    public History getParent() {
        return new HistoryImpl(HistoryChainModel.find.byId(historyChain.getParent()));
    }

    @Override
    public List<History> getChildren() {
        List<HistoryChainModel> childChains = HistoryChainModel.find.query().where().eq("parent", historyChain.getId()).findList();

        List<History> childHistories = new ArrayList<>();
        for (HistoryChainModel historyChain: childChains) {
            childHistories.add(new HistoryImpl(historyChain));
        }
        return childHistories;
    }

    @Override
    public BlockAdapter getBlock() {
        World world = Bukkit.getWorld(historyChain.getWorldName());
        double x = historyChain.getX();
        double y = historyChain.getY();
        double z = historyChain.getZ();

        Location loc = new Location(world, x, y, z);
        Material type = Material.getMaterial(historyChain.getTypeTo());
        int typeId = historyChain.getTypeIdTo();
        byte data = historyChain.getDataTo();

        return new BlockAdapterImpl(loc, type, typeId, data);
    }

    @Override
    public BlockAdapter getBlockReplaced() {
        World world = Bukkit.getWorld(historyChain.getWorldName());
        double x = historyChain.getX();
        double y = historyChain.getY();
        double z = historyChain.getZ();

        Location loc = new Location(world, x, y, z);
        Material type = Material.getMaterial(historyChain.getTypeFrom());
        int typeId = historyChain.getTypeIdFrom();
        byte data = historyChain.getDataFrom();

        return new BlockAdapterImpl(loc, type, typeId, data);
    }

    @Override
    public boolean isRollbacked() {
        historyChain.refresh();
        return historyChain.isRollbacked();
    }

    @Override
    public boolean rollbackThis() {
        if (isConflict()) {
            return false;
        } else {
            BlockAdapter blockAdapter = getBlockReplaced();
            Block block = blockAdapter.getLocation().getBlock();

            block.setType(blockAdapter.getType());
            block.setTypeId(blockAdapter.getTypeId());
            block.setData(blockAdapter.getData());
            return true;
        }
    }

    @Override
    public boolean rollbackThis(boolean force) {
        if (force) {
            BlockAdapter blockAdapter = getBlockReplaced();
            Block block = blockAdapter.getLocation().getBlock();

            block.setType(blockAdapter.getType());
            block.setTypeId(blockAdapter.getTypeId());
            block.setData(blockAdapter.getData());
            return true;
        } else {
            return rollbackThis();
        }
    }

    @Override
    public boolean rollbackAll() {
        List<HistoryChainModel> historyChainModels = HistoryChainModel.find.query().where().eq("origin", getOrigin()).eq("rollback", false).findList();
        boolean success = true;
        for (HistoryChainModel historyChainModel: historyChainModels) {
            HistoryImpl history = new HistoryImpl(historyChain);
            success &= history.rollbackThis();
        }
        return success;
    }

    @Override
    public boolean rollbackAll(boolean force) {
        if (force) {
            List<HistoryChainModel> historyChainModels = HistoryChainModel.find.query().where().eq("origin", getOrigin()).eq("rollback", false).findList();
            for (HistoryChainModel historyChainModel: historyChainModels) {
                HistoryImpl history = new HistoryImpl(historyChain);
                history.rollbackThis(true);
            }
            return true;
        } else {
            return rollbackAll();
        }
    }

    @Override
    public boolean isConflict() {
        return false;
    }

    @Override
    public boolean isConflictAll() {
        return false;
    }

    @Override
    public List<History> getConflicts() {
        return null;
    }

    @Override
    public List<History> getAllConflicts() {
        return null;
    }
}
