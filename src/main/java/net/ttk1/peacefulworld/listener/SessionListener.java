package net.ttk1.peacefulworld.listener;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.model.PlayerModel;
import net.ttk1.peacefulworld.model.SessionHistoryModel;
import net.ttk1.peacefulworld.service.PlayerService;
import net.ttk1.peacefulworld.service.SessionHistoryService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author ttk1 and mmtsk
 */
public class SessionListener implements Listener {
    private PlayerService playerService;
    private SessionHistoryService sessionHistoryService;
    private PeacefulWorld plugin;

    @Inject
    private void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Inject
    private void setSessionHistoryService(SessionHistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

    @Inject
    private void setPlugin(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();
        String playerName = player.getName();
        long playerId = playerService.getPlayerID(playerUuid);

        if (playerId < 0) {
            // first join
            plugin.getLogger().info(player.getPlayerListName() + " is first join. uuid is " + playerName);
            playerId = playerService.registerPlayer(playerUuid, playerName);

        } else if (!playerService.getPlayerName(playerId).equals(playerName)){
                // name update
                plugin.getLogger().info("player of uuid: " + playerName+ " changed own name from " + playerService.getPlayerName(playerId) + " to " + playerName);
                playerService.updatePlayerName(playerId, playerName);
        }
        sessionHistoryService.login(playerId);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();
        String playerName = player.getName();
        long playerId = playerService.getPlayerID(playerUuid);

        if (playerId < 0) {
            plugin.getLogger().info("something is wrong. missed player record with uuid: " + playerUuid);
            plugin.getLogger().info("creating new player record");
            playerId = playerService.registerPlayer(playerUuid, playerName);
        }
        sessionHistoryService.logout(playerId);
    }
}
