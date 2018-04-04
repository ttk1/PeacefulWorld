package net.ttk1.peacefulworld.listener;

import com.google.inject.Inject;
import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.model.PlayerModel;
import net.ttk1.peacefulworld.model.SessionHistoryModel;
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
    private PeacefulWorld plugin;

    @Inject
    protected void setPlugin(PeacefulWorld plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        long time = System.currentTimeMillis();
        PlayerModel playerModel = PlayerModel.find.query().where().eq("uuid",player.getUniqueId().toString()).findOne();

        if (playerModel == null) {
            // first join
            plugin.getLogger().info(player.getPlayerListName() + " is first join. uuid is " + player.getUniqueId().toString());
            playerModel = new PlayerModel(player.getUniqueId().toString(), player.getPlayerListName());
            playerModel.save();

            long playerId = playerModel.getId();
            SessionHistoryModel sessionHistoryModel = new SessionHistoryModel(time, playerId, 0);
            sessionHistoryModel.save();
        } else {
            if (!playerModel.getName().equals(player.getPlayerListName())){
                // name update
                plugin.getLogger().info("player of uuid: " + player.getUniqueId().toString() + " changed own name from " + playerModel.getName() + " to " + player.getPlayerListName());
                playerModel.setName(player.getPlayerListName());
                playerModel.update();
            }

            SessionHistoryModel sessionHistoryModel = new SessionHistoryModel(time, playerModel.getId(), 0);
            sessionHistoryModel.save();
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        long time = System.currentTimeMillis();
        PlayerModel playerModel = PlayerModel.find.query().where().eq("uuid",player.getUniqueId().toString()).findOne();

        if (playerModel == null) {
            plugin.getLogger().info("something is wrong. missed player record with uuid: " + player.getUniqueId().toString());
            plugin.getLogger().info("creating new player record");

            playerModel = new PlayerModel(player.getUniqueId().toString(), player.getPlayerListName());
            playerModel.save();

            long playerId = playerModel.getId();
            SessionHistoryModel sessionHistoryModel = new SessionHistoryModel(time, playerId, 1);
            sessionHistoryModel.save();
        } else {
            // logoutの際にはplayer nameはチェックしない
            SessionHistoryModel sessionHistoryModel = new SessionHistoryModel(time, playerModel.getId(), 1);
            sessionHistoryModel.save();
        }
    }
}
