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
@Table(name = "session_history")
public class SessionHistoryModel extends Model{
    public static final SessionHistoryFinder find = new SessionHistoryFinder();
    @Id
    private long id;

    // プレーヤーテーブルのid
    private String player;

    public long getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public static class SessionHistoryFinder extends Finder<Long, SessionHistoryModel>{
        SessionHistoryFinder(){
            super(SessionHistoryModel.class);
        }
        SessionHistoryFinder(String serverName){
            super(SessionHistoryModel.class, serverName);
        }
    }
}
