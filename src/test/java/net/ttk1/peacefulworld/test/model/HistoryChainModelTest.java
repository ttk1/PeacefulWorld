package net.ttk1.peacefulworld.test.model;

import net.ttk1.peacefulworld.model.HistoryChainModel;
import org.junit.Before;
import org.junit.Test;

import static net.ttk1.peacefulworld.model.HistoryChainModel.HistoryChainFinder;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HistoryChainModelTest {
    private HistoryChainFinder historyChainFinder;

    @Before
    public void before() {
        this.historyChainFinder = new HistoryChainFinder("db");
    }

    @Test
    public void insertTest(){
        // insert
        HistoryChainModel hc = new HistoryChainModel(100L, 200L, 300L, 400L, "test_world", 10, 20, 30, "test_data");
        hc.save();

        // select
        hc = historyChainFinder.query().where().eq("id", 1L).findOne();
        assertThat(hc.getId(), is(1L));
        assertThat(hc.getOriginId(), is(200L));
        assertThat(hc.getParentId(), is(300L));
        assertThat(hc.getPlayerId(), is(400L));
        assertThat(hc.getWorldName(), is("test_world"));
        assertThat(hc.getX(), is(10));
        assertThat(hc.getY(), is(20));
        assertThat(hc.getZ(), is(30));
    }
}