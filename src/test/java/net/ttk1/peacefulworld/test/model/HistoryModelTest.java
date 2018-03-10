package net.ttk1.peacefulworld.test.model;

//import ch.qos.logback.classic.Level;
import io.ebean.Ebean;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HistoryModelTest {
    @Before
    public  void before(){
        //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)).setLevel(Level.OFF);
    }

    @Test
    public void insertTest(){
        new HistoryModel(123L,"hello").save();
        new HistoryModel(345L,"こんにちは").save();
        assertThat(Ebean.find(HistoryModel.class, 123).getName(), is("hello"));
        assertThat(Ebean.find(HistoryModel.class, 345).getName(), is("こんにちは"));
    }
}