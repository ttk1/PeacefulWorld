package net.ttk1.peacefulworld.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.ttk1.peacefulworld.PeacefulWorld;
import net.ttk1.peacefulworld.model.PlayerModel;

import static net.ttk1.peacefulworld.model.PlayerModel.PlayerFinder;

import javax.persistence.PersistenceException;

@Singleton
public class PlayerService {
    private String ebeanServerName;
    private PlayerFinder playerFinder;

    @Inject
    private void setEbeanServerName(@Named("ebeanServerName") String ebeanServerName) {
        this.ebeanServerName = ebeanServerName;
        setPlayerFinder(new PlayerFinder(ebeanServerName));
    }

    private void setPlayerFinder(PlayerFinder playerFinder) {
        this.playerFinder = playerFinder;
    }

    // 負の値が返ったら未登録
    public long getPlayerID(String playerUuid) {
        PlayerModel player = playerFinder.query().where().eq("uuid", playerUuid).findOne();
        if (player == null) {
            return -1;
        } else {
            return player.getId();
        }
    }

    // nullが返ったら未登録
    public String getPlayerName(long playerId) {
        PlayerModel player = playerFinder.byId(playerId);
        if (player == null) {
            return null;
        } else {
            return player.getPlayerName();
        }
    }

    // nullが返ったら未登録
    public String getPlayerUuid(long playerId) {
        PlayerModel player = playerFinder.byId(playerId);
        if (player == null) {
            return null;
        } else {
            return player.getPlayerUuid();
        }
    }

    public long registerPlayer(String playerUuid, String playerName) {
        PlayerModel player = new PlayerModel(playerUuid, playerName);

        // uuidが重複したら例外が飛ぶ
        try {
            player.insert(ebeanServerName);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return -1;
        }
        return player.getId();
    }

    public void updatePlayerName(long playerId, String playerName) {
        PlayerModel player = playerFinder.byId(playerId);
        if (player != null) {
            player.setPlayerName(playerName);
            player.update(ebeanServerName);
        }
    }
}