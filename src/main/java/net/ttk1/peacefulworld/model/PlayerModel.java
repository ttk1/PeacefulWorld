package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author ttk1 and mmtsk
 */
@Cache
@Entity
@Table(name = "player", uniqueConstraints = { @UniqueConstraint(columnNames = {"player_uuid"}) })
public class PlayerModel extends Model{
    @Id
    private long id;
    private String playerUuid;
    private String playerName;

    public PlayerModel(String playerUuid, String playerName) {
        this.playerUuid = playerUuid;
        this.playerName = playerName;
        this.save();
    }

    // setter
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // getter
    public long getId() {
        return id;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static class PlayerFinder extends Finder<Long, PlayerModel>{
        public PlayerFinder(String ebeanServerName){
            super(PlayerModel.class, ebeanServerName);
        }
    }
}
