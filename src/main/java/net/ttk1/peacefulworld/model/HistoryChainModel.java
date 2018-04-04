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
    //@CreatedTimestamp
    private long time;

    // 起源となるHistoryのid
    private long origin;

    // 親のHistoryのid
    private long parent;

    // プレーヤーid
    private long player;

    // ロールバックしたか
    private boolean rollback;


    // BlockAdapter関係

    // ワールド名
    private String worldName;

    // 座標x
    private long x;

    // 座標y
    private long y;

    // 座標z
    private long z;

    private int typeTo;
    private int typeFrom;
    private int typeIdTo;
    private int typeIdFrom;
    private byte dataTo;
    private byte dataFrom;

    public HistoryChainModel(long time, long origin, long parent, long player, boolean rollback, String worldName, long x, long y, long z, int typeTo, int typeIdTo, byte dataTo, int typeFrom, int typeIdFrom, byte dataFrom) {
        this.time = time;
        this.origin = origin;
        this.parent = parent;
        this.player = player;
        this.rollback = rollback;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;

        this.typeTo = typeTo;
        this.typeIdTo = typeIdTo;
        this.dataTo = dataTo;

        this.typeFrom = typeFrom;
        this.typeIdFrom = typeIdFrom;
        this.dataFrom = dataFrom;
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

    public boolean isRollbacked() {
        return rollback;
    }

    public void setRollback(boolean rollback) {
        this.rollback = rollback;
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

    public int getTypeTo() {
        return typeTo;
    }

    public byte getDataTo() {
        return dataTo;
    }

    public int getTypeIdTo() {
        return typeIdTo;
    }

    public int getTypeFrom() {
        return typeFrom;
    }

    public int getTypeIdFrom() {
        return typeIdFrom;
    }

    public byte getDataFrom() {
        return dataFrom;
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
