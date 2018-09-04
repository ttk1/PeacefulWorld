package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;
import io.ebean.annotation.CreatedTimestamp;

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
    @Id
    private long id;

    // 時刻
    //@CreatedTimestamp
    private long time;

    // プレーヤーテーブルのid
    private long player;

    // type:0 -> login
    // type:1 -> logout
    private int type;


    public SessionHistoryModel(long time, long player, int type) {
        this.time = time;
        this.player = player;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public long getPlayer() {
        return player;
    }

    public int getType() {
        return type;
    }

    public static class SessionHistoryFinder extends Finder<Long, SessionHistoryModel>{
        SessionHistoryFinder(String ebeanServerName){
            super(SessionHistoryModel.class, ebeanServerName);
        }
    }
}
