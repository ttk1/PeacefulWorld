package net.ttk1.peacefulworld.api;

import org.bukkit.entity.Entity;

import java.util.List;

/**
 * @author ttk1 and mmtsk
 */

public interface History {
    /**
     * @return このhistoryエンティティのユニークなIDを返す
     */
    long getId();

    /**
     * @return このhistoryが生成されたunix time(ミリ精度)を返す
     */
    long getTime();

    /**
     * @return このhistoryを引き起こしたエンティティのuuidを返す
     */
    String getUuid();

    /**
     * 自分自身が発生源のhistoryは自分自身を返す。
     * @return このhistoryの発生源となるhistoryエンティティを返す
     */
    History getOrigin();

    /**
     * 親を持たない（自分自身が発生源）のhistoryはnullを返す。
     * @return このhistoryの直接の発生元となるhistoryエンティティを返す
     */
    History getParent();

    /**
     * 子を持たない場合空のリストを返す。
     * @return このhistoryによって引き起こされたhistoryエンティティのリストを返す。
     */
    List<History> getChildren();

    /**
     * @return 変更後のBlockの情報を持つBlockAdapterを返す
     */
    BlockAdapter getBlock();

    /**
     * @return 変更前のBlockの情報を持つBlockAdapterを返す
     */
    BlockAdapter getBlockReplaced();

    /**
     * @return このHistoryがロールバックされたものか否かを返す
     */
    boolean isRollbacked();

    /**
     * このhistoryをロールバックする。
     * 競合が発生した場合失敗し、なにも変化しない。
     * @return ロールバックが成功したかどうか
     */
    boolean rollbackThis();

    /**
     * force=falseの場合rollbackThis()と同じ動作をする。
     * force=trueの場合、強制的にロールバックする。この場合競合したhistoryが消えることになる。
     * @param force 強制的にロールバックするか
     * @return
     */
    boolean rollbackThis(boolean force);

    /**
     * このhistoryに関係するすべてのhistoryをロールバックする。
     * 競合が発生した場合失敗し、なにも変化しない。
     * @return ロールバックが成功したかどうか
     */
    boolean rollbackAll();

    /**
     * force=falseの場合rollback()と同じ動作をする。
     * force=trueの場合、強制的にロールバックする。この場合競合したhistoryが消えることになる。
     * @param force 強制的にロールバックするか
     * @return
     */
    boolean rollbackAll(boolean force);

    /**
     *
     * @return rollbackThis()が成功するか否かを返す
     */
    boolean isConflict();

    /**
     *
     * @return rollbackAll()が成功するか否かを返す
     */
    boolean isConflictAll();

    /**
     *
     * @return rollbackThis()で競合しているhistoryのリストを返す
     */
    List<History> getConflicts();

    /**
     *
     * @return このhistoryに関係するhistoryのうち競合を起こすもののリストを返す
     */
    List<History> getAllConflicts();
}
