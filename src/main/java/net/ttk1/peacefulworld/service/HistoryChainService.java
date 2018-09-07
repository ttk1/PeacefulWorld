package net.ttk1.peacefulworld.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.history.HistoryImpl;
import net.ttk1.peacefulworld.model.HistoryChainModel;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;

import static net.ttk1.peacefulworld.model.HistoryChainModel.HistoryChainFinder;

@Singleton
public class HistoryChainService {
    private PeacefulWorld plugin;
    private String ebeanServerName;
    private HistoryChainFinder historyChainFinder;
    private PlayerService playerService;

    @Inject
    protected void setPlugin(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    @Inject
    protected void setEbeanServerName(@Named("ebeanServerName") String ebeanServerName) {
        this.ebeanServerName = ebeanServerName;
        setHistoryChainFinder(new HistoryChainFinder(ebeanServerName));
    }

    @Inject
    protected void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    protected void setHistoryChainFinder(HistoryChainFinder historyChainFinder) {
        this.historyChainFinder = historyChainFinder;
    }

    public long registerHistory(long originId, long parentId, long playerId, Location location, BlockData blockData) {
        // time
        long time = System.currentTimeMillis();

        // worldName, x, y, z
        String worldName = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        // blockDataString
        String blockDataString = blockData.getAsString();

        HistoryChainModel historyChain = new HistoryChainModel(time, originId, parentId, playerId, worldName, x, y, z, blockDataString);

        return historyChain.getId();
    }

    // 負の値が返ったらid未登録
    public long getOriginId(long historyId) {
        HistoryChainModel historyChain = historyChainFinder.byId(historyId);
        if (historyChain == null) {
            return -1;
        } else {
            return historyChain.getOriginId();
        }
    }

    // 0が返ったらparentをもたない
    // 負の値が返ったらid未登録
    public long getParentId(long historyId) {
        HistoryChainModel historyChain = historyChainFinder.byId(historyId);
        if (historyChain == null) {
            return -1;
        } else {
            return historyChain.getParentId();
        }
    }

    // nullが返ったら未登録
    public History getHistory(long historyId) {
        HistoryChainModel historyChain = historyChainFinder.byId(historyId);
        if (historyChain == null) {
            return null;
        } else {
            // time, originId, parentId
            long time = historyChain.getTime();
            long originId = historyChain.getOriginId();
            long parentId = historyChain.getParentId();

            // playerUuid
            String playerUuid = playerService.getPlayerUuid(historyChain.getPlayerId());

            // location
            World world = plugin.getServer().getWorld(historyChain.getWorldName());
            double x = (double) historyChain.getX();
            double y = (double) historyChain.getY();
            double z = (double) historyChain.getZ();
            Location location = new Location(world, x, y, z);

            // blockData
            BlockData blockData = plugin.getServer().createBlockData(historyChain.getBlockDataString());

            return new HistoryImpl(historyId, time, originId, parentId, playerUuid, location, blockData);
        }
    }
}
