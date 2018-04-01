package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.api.BlockAdapter;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryBuilder;
import org.bukkit.entity.Entity;

/**
 * @author ttk1 and mmtsk
 */
public class HistoryBuilderImpl implements HistoryBuilder {
    private long time;
    private Entity player;
    private History parent;
    private BlockAdapter block;
    private BlockAdapter blockReplaced;

    @Override
    public History build(){
        return new HistoryImpl(time, player, parent, block, blockReplaced);
    }

    @Override
    public HistoryBuilder setTime(long time) {
        this.time = time;
        return this;
    }

    @Override
    public HistoryBuilder setPlayer(Entity player) {
        this.player = player;
        return this;
    }

    @Override
    public HistoryBuilder setParent(History parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public HistoryBuilder setBlock(BlockAdapter block) {
        this.block = block;
        return this;
    }

    @Override
    public HistoryBuilder setBlockReplaced(BlockAdapter blockReplaced) {
        this.blockReplaced = blockReplaced;
        return this;
    }
}
