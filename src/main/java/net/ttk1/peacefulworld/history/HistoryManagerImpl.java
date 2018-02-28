package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryBuilder;
import net.ttk1.peacefulworld.api.HistoryManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */
public class HistoryManagerImpl implements HistoryManager {
    @Override
    public History getHistory(long id) {
        return null;
    }

    @Override
    public List<History> getHistoryAt(Location loc){
        return null;
    }

    @Override
    public HistoryBuilder getHistoryBuilder() {
        return null;
    }
}
