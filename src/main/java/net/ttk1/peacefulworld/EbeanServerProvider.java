package net.ttk1.peacefulworld;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ClassLoadConfig;
import io.ebean.config.ServerConfig;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.avaje.datasource.DataSourceConfig;

import com.google.inject.Inject;
import javax.inject.Provider;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class EbeanServerProvider implements Provider<EbeanServer> {
    private PeacefulWorld plugin;
    private Properties properties;

    @Inject
    public void setPlugin(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    @Inject
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public EbeanServer get() {
        DataSourceConfig ds = new DataSourceConfig();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(plugin.getDataFolder(), "ebean.properties"));
            Properties properties = new Properties();
            properties.load(inputStream);
            ds.loadSettings(properties, "db");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerConfig config = prepareDatabase();
        config.loadFromProperties();
        config.setDefaultServer(true);
        config.setClassLoadConfig(new ClassLoadConfig(this.getClass().getClassLoader()));
        config.setClasses(Arrays.asList(HistoryModel.class));

        return EbeanServerFactory.createWithContextClassLoader(config, this.getClass().getClassLoader());
    }

    private ServerConfig prepareDatabase() {
        //Setup the data source
        DataSourceConfig ds = new DataSourceConfig();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(plugin.getDataFolder(), "ebean.properties"));
            Properties properties = new Properties();
            properties.load(inputStream);
            ds.loadSettings(properties, "db");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Setup the server configuration
        ServerConfig sc = new ServerConfig();
        sc.setDefaultServer(true);
        sc.setRegister(true);
        sc.setName(ds.getUrl().replaceAll("[^a-zA-Z0-9]", ""));
        //Finally the data source
        sc.setDataSourceConfig(ds);
        return sc;
    }
}
