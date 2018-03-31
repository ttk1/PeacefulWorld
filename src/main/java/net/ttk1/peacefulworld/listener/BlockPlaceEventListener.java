package net.ttk1.peacefulworld.listener;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.api.BlockAdapter;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryBuilder;
import net.ttk1.peacefulworld.api.HistoryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import sun.jvm.hotspot.opto.Block;

import java.util.Date;

/**
 * @author ttk1 and mmtsk
 */
public class BlockPlaceEventListener implements Listener {

    @Inject
    private PeacefulWorld plugin;

    HistoryManager historyManager = plugin.getHistoryManager();

    @EventHandler
    public void onBlockPlaceEventListener(BlockPlaceEvent event) {

        HistoryBuilder historyBuilder = historyManager.getHistoryBuilder();
        historyBuilder = historyBuilder.setTime(new Date().getTime());
        historyBuilder = historyBuilder.setPlayer(event.getPlayer());
        historyBuilder = historyBuilder.setParent(null);
        historyBuilder = historyBuilder.setBlockReplaced(BlockAdapter.of(event.getBlockPlaced()));
        historyBuilder = historyBuilder.setBlock(BlockAdapter.of(event.getBlock()));

        History history = historyBuilder.build();

    }

}
