package net.ttk1.peacefulworld.test.model;

import io.ebean.Ebean;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HistoryModelTest {
    @Test
    public void insertTest(){
        new HistoryModel(123L,"hello").save();
        new HistoryModel(345L,"こんにちは").save();
        assertThat(Ebean.find(HistoryModel.class, 123).getName(), is("hello"));
        assertThat(Ebean.find(HistoryModel.class, 345).getName(), is("こんにちは"));
    }
}