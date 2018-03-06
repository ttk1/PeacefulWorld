package net.ttk1.peacefulworld.listener;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.PeacefulWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author ttk1 and mmtsk
 */
public class BlockPlaceEventListener implements Listener {

    @Inject
    private PeacefulWorld plugin;

    @EventHandler
    public void onBlockPlaceEventListener(BlockPlaceEvent event) {
    }
}
