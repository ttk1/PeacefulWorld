package net.ttk1.peacefulworld.api;

import java.util.List;

/**
 * @author ttk1
 */
public interface History {
    long getId();
    long getTime();
    History getOrigin();
    History getParent();
    List<History> getChildren();
    BlockAdapter getBlock();
    BlockAdapter getBlockReplaced();
}
