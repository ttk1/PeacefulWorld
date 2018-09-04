package net.ttk1.peacefulworld.listener;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.service.HistoryChainService;
import net.ttk1.peacefulworld.service.PlayerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Date;

/**
 * @author ttk1 and mmtsk
 */
public class BlockPlaceEventListener implements Listener {
    private PlayerService playerService;
    private HistoryChainService historyChainService;

    @Inject
    private void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Inject
    private void setHistoryChainService(HistoryChainService historyChainService) {
        this.historyChainService = historyChainService;
    }

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
