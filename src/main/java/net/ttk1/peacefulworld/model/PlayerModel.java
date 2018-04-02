package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ttk1 and mmtsk
 */
@Cache
@Entity
@Table(name = "player")
public class PlayerModel extends Model{
    public static final PlayerFinder find = new PlayerFinder();

    @Id
    private long id;
    private String uuid;
    private String name;

    public PlayerModel(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public static class PlayerFinder extends Finder<Long, PlayerModel>{
        PlayerFinder(){
            super(PlayerModel.class);
        }
        PlayerFinder(String serverName){
            super(PlayerModel.class, serverName);
        }
    }
}
