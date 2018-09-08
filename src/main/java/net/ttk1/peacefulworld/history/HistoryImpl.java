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
    private String worldName;
    private int x;
    private int y;
    private int z;
    private String blockDataString;

    @Inject
    private void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Inject
    private void setHistoryChainService(HistoryChainService historyChainService) {
        this.historyChainService = historyChainService;
    }

    public HistoryImpl(long id, long time, long originId, long parentId, String playerUuid,
                       String worldName, int x, int y, int z, String blockDataString) {
        this.id = id;
        this.time = time;
        this.originId = originId;
        this.parentId = parentId;
        this.playerUuid = playerUuid;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockDataString = blockDataString;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public History getOrigin() {
        return null;
    }

    public long getOriginId() {
        return originId;
    }

    @Override
    public History getParent() {
        return null;
    }

    public long getParentId() {
        return parentId;
    }

    @Override
    public String getPlayerUuid() {
        return playerUuid;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public BlockData getBlockData() {
        return null;
    }

    public String getBlockDataString() {
        return blockDataString;
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
