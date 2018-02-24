package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class HistoryManagerImpl implements HistoryManager {
    public HistoryManagerImpl(FileConfiguration conf){
        // DBとの接続処理など？
    }

    @Override
    public List<History> getHistoryAt(Location loc){
        return null;
    }
}
