package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;
import io.ebean.annotation.CreatedTimestamp;

import javax.persistence.*;

/**
 * @author ttk1 and mmtsk
 */
@Cache
@Entity
@Table(name = "history_chain")
public class HistoryChainModel extends Model {
    public static final BlockHistoryFinder find = new BlockHistoryFinder();
    @Id
    private long id;

    // 発生時刻
    @CreatedTimestamp
    private long time;

    // 起源となるHistoryのid
    private long origin;

    // 親のHistoryのid
    private long parent;

    // プレーヤーid
    private long player;

    // ロールバックしたか
    private boolean rollback;

    // ワールド名
    private String worldName;

    // 座標x
    private long x;

    // 座標y
    private long y;

    // 座標z
    private long z;

    public HistoryChainModel(long origin, long parent, long player, String worldName, long x, long y, long z) {
        this.origin = origin;
        this.parent = parent;
        this.player = player;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public long getOrigin() {
        return origin;
    }

    public long getParent() {
        return parent;
    }

    public long getPlayer() {
        return player;
    }

    public boolean isRollback() {
        return rollback;
    }

    public String getWorldName() {
        return worldName;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public static class BlockHistoryFinder extends Finder<Long, HistoryChainModel>{
        BlockHistoryFinder(){
            super(HistoryChainModel.class);
        }
        BlockHistoryFinder(String serverName){
            super(HistoryChainModel.class, serverName);
        }
    }
}
