package net.ttk1.peacefulworld.api;

import org.bukkit.entity.Entity;

import java.util.List;

/**
 * @author ttk1
 */
public interface History {
    long getId();
    long getTime();
    Entity getPlayer();
    History getOrigin();
    History getParent();
    List<History> getChildren();
    BlockAdapter getBlock();
    BlockAdapter getBlockReplaced();
}
