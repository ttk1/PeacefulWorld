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
    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_LOGOUT = 1;

    @Id
    private long id;

    // 時刻
    //@CreatedTimestamp
    private long time;

    // プレーヤーテーブルのid
    private long playerId;

    // type:0 -> login
    // type:1 -> logout
    private int type;


    public SessionHistoryModel(long time, long playerId, int type) {
        this.time = time;
        this.playerId = playerId;
        this.type = type;
        this.save();
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public long getPlayerId() {
        return playerId;
    }

    public int getType() {
        return type;
    }

    public static class SessionHistoryFinder extends Finder<Long, SessionHistoryModel>{
        public SessionHistoryFinder(String ebeanServerName){
            super(SessionHistoryModel.class, ebeanServerName);
        }
    }
}
