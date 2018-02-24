package net.ttk1.peacefulworld.api;

import org.bukkit.Location;

import java.util.List;

public interface HistoryManager {
    List<History> getHistoryAt(Location loc);
}
