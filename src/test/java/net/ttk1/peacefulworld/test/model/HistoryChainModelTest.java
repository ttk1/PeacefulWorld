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
        HistoryChainModel hc = new HistoryChainModel(100L, 200L, 300L, 400L, false, "test world", 500L, 600L, 700L, 0, 1, (byte) 0x00, 0, 1, (byte) 0x00);
        hc.save();

        // select
        hc = HistoryChainModel.find.query().where().eq("id", 1L).findOne();
        assertThat(hc.getId(), is(1L));
        assertThat(hc.getOrigin(), is(200L));
        assertThat(hc.getParent(), is(300L));
        assertThat(hc.getPlayer(), is(400L));
        assertThat(hc.getWorldName(), is("test world"));
        assertThat(hc.getX(), is(500L));
        assertThat(hc.getY(), is(600L));
        assertThat(hc.getZ(), is(700L));
    }
}