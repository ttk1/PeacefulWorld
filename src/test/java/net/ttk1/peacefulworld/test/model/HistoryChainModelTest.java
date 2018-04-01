package net.ttk1.peacefulworld.test.model;

import net.ttk1.peacefulworld.model.HistoryChainModel;
import org.junit.Before;
import org.junit.Test;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HistoryChainModelTest {
    @Test
    public void insertTest(){
        // insert
        HistoryChainModel hc = new HistoryChainModel(100L, 200L, 300L, "test world", 400L, 500L, 600L);
        hc.save();

        // select
        hc = HistoryChainModel.find.query().where().eq("id", 1L).findOne();
        assertThat(hc.getId(), is(1L));
        assertThat(hc.getOrigin(), is(100L));
        assertThat(hc.getParent(), is(200L));
        assertThat(hc.getPlayer(), is(300L));
        assertThat(hc.getWorldName(), is("test world"));
        assertThat(hc.getX(), is(400L));
        assertThat(hc.getY(), is(500L));
        assertThat(hc.getZ(), is(600L));
    }
}