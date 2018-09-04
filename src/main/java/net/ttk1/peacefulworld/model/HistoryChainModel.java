package net.ttk1.peacefulworld.model;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.*;

/**
 * @author ttk1 and mmtsk
 */
@Cache
@Entity
@Table(name = "history_chain")
public class HistoryChainModel extends Model {
    @Id
    private long id;

    // 発生時刻
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
    private int x;

    // 座標y
    private int y;

    // 座標z
    private int z;

    private String blockDataString;

    public HistoryChainModel(long time, long origin, long parent, long player, String worldName, int x, int y, int z, String blockDataString) {
        this.time = time;
        this.origin = origin;
        this.parent = parent;
        this.player = player;
        this.rollback = false;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockDataString = blockDataString;

        this.save();

        if (origin == 0) {
            this.origin = this.id;
            this.update();
        }
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

    public boolean getRollback() {
        return rollback;
    }

    public void setRollback(boolean rollback) {
        this.rollback = rollback;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getBlockDataString() {
        return blockDataString;
    }

    public static class BlockHistoryFinder extends Finder<Long, HistoryChainModel>{
        BlockHistoryFinder(String ebeanServerName){
            super(HistoryChainModel.class, ebeanServerName);
        }
    }
}
