package net.ttk1.peacefulworld.test.model;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class HistoryModelTest {
    private EbeanServer server;

    @Before
    public void before(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ServerConfig config = new ServerConfig();
        String fileName = HistoryModelTest.class.getClassLoader().getResource("ebean.properties").getPath();
        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
            Properties properties = new Properties();
            properties.load(inputStream);
            config.loadFromProperties(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server = EbeanServerFactory.create(config);
    }

    @Test
    public void insertTest(){
        HistoryModel historyModel = new HistoryModel(123L, "hello");
        historyModel.save();

        historyModel = Ebean.find(HistoryModel.class, 123);
        assertThat(historyModel.getName(), is("hello"));

        //server.execute(() -> {
        //    HistoryModel historyModel = new HistoryModel(123L, "hello");
        //    Ebean.save(historyModel);
        //});
    }
}