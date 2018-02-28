package net.ttk1.peacefulworld.api;

import org.bukkit.Location;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */
public interface HistoryManager {
    History getHistory(long id);
    List<History> getHistoryAt(Location loc);
    HistoryBuilder getHistoryBuilder();
}
