package net.ttk1.peacefulworld.api;

import org.bukkit.entity.Entity;

/**
 * @author ttk1 and mmtsk
 */
public interface HistoryBuilder {
    History build();
    HistoryBuilder setTime(long time);
    HistoryBuilder setPlayer(Entity player);
    HistoryBuilder setParent(History parent);
    HistoryBuilder setBlock(BlockAdapter block);
    HistoryBuilder setBlockReplaced(BlockAdapter blockReplaced);
}
