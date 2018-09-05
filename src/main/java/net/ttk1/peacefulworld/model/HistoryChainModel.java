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
    private long originId;

    // 親のHistoryのid
    private long parentId;

    // プレーヤーid
    private long playerId;

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

    public HistoryChainModel(long time, long originId, long parentId, long playerId, String worldName, int x, int y, int z, String blockDataString) {
        this.time = time;
        this.originId = originId;
        this.parentId = parentId;
        this.playerId = playerId;
        this.rollback = false;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockDataString = blockDataString;

        this.save();

        if (originId == 0) {
            this.originId = this.id;
            this.update();
        }
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public long getOriginId() {
        return originId;
    }

    public long getParentId() {
        return parentId;
    }

    public long getPlayerId() {
        return playerId;
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

    public static class HistoryChainFinder extends Finder<Long, HistoryChainModel>{
        public HistoryChainFinder(String ebeanServerName){
            super(HistoryChainModel.class, ebeanServerName);
        }
    }
}
