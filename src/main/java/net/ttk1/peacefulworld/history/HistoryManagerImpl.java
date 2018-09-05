package net.ttk1.peacefulworld.history;

import com.google.inject.Singleton;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import org.bukkit.Location;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */

@Singleton
public class HistoryManagerImpl implements HistoryManager {
    @Override
    public History getHistory(long id) {
        return null;
    }

    @Override
    public List<History> getHistoryAt(Location loc){
        return null;
    }
}
