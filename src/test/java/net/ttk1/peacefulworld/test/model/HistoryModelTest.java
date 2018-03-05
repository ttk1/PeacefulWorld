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
        for (int i = 0; i < 10; i++) {
            HistoryModel historyModel = new HistoryModel("hello");
            historyModel.save();
            System.out.println(historyModel.getId());
        }
        //historyModel = Ebean.find(HistoryModel.class, 123);
        //assertThat(historyModel.getName(), is("hello"));
    }
}