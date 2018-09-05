package net.ttk1.peacefulworld.api;

import com.google.inject.ImplementedBy;
import net.ttk1.peacefulworld.history.HistoryManagerImpl;
import org.bukkit.Location;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */

@ImplementedBy(HistoryManagerImpl.class)
public interface HistoryManager {
    History getHistory(long id);
    List<History> getHistoryAt(Location loc);
}
