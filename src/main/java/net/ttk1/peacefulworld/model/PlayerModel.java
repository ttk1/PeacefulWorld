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
    @Id
    private long id;
    private String uuid;
    private String name;

    // setter
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getter
    public long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public static class PlayerFinder extends Finder<Long, PlayerModel>{
        public PlayerFinder(String ebeanServerName){
            super(PlayerModel.class, ebeanServerName);
        }
    }
}
