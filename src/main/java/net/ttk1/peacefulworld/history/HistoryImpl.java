package net.ttk1.peacefulworld.history;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.service.HistoryChainService;
import net.ttk1.peacefulworld.service.PlayerService;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */
public class HistoryImpl implements History {
    private PlayerService playerService;
    private HistoryChainService historyChainService;

    private long id;
    private long time;
    private long originId;
    private long parentId;
    private String playerUuid;
    private Location location;
    private BlockData blockData;

    @Inject
    private void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Inject
    private void setHistoryChainService(HistoryChainService historyChainService) {
        this.historyChainService = historyChainService;
    }

    public HistoryImpl(long id, long time, long originId, long parentId, String playerUuid, Location location, BlockData blockData) {
        this.id = id;
        this.time = time;
        this.originId = originId;
        this.parentId = parentId;
        this.playerUuid = playerUuid;
        this.location = location;
        this.blockData = blockData;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public BlockData getBlockData() {
        return null;
    }

    @Override
    public String getPlayerUuid() {
        return null;
    }

    @Override
    public History getOrigin() {
        return null;
    }

    @Override
    public History getParent() {
        return null;
    }

    @Override
    public List<History> getChildren() {
        return null;
    }

    @Override
    public boolean isRollbacked() {
        return false;
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public boolean rollback(boolean force) {
        return false;
    }

    @Override
    public boolean rollbackAll() {
        return false;
    }

    @Override
    public boolean rollbackAll(boolean force) {
        return false;
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
