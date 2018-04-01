package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.api.BlockAdapter;
import net.ttk1.peacefulworld.api.History;
import org.bukkit.entity.Entity;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */
public class HistoryImpl implements History {
    HistoryImpl(long time, Entity player, History parent, BlockAdapter block, BlockAdapter blockReplaced) {

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
    public Entity getPlayer() {
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
    public BlockAdapter getBlock() {
        return null;
    }

    @Override
    public BlockAdapter getBlockReplaced() {
        return null;
    }

    @Override
    public boolean isRollbacked() {
        return false;
    }

    @Override
    public boolean rollbackThis() {
        return false;
    }

    @Override
    public boolean rollbackThis(boolean force) {
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
