package net.ttk1.peacefulworld;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.annotation.TxIsolation;
import io.ebean.config.ClassLoadConfig;
import io.ebean.config.ServerConfig;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.avaje.datasource.DataSourceConfig;

import com.google.inject.Inject;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author ttk1 and mmtsk
 */
public class EbeanServerProvider implements Provider<EbeanServer> {
    private PeacefulWorld plugin;
    private ConfigurationSection dbConfig;

    @Inject
    public void setConfig(Configuration config) {
        this.dbConfig = config.getConfigurationSection("DB");
    }

    @Inject
    public void setPlugin(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public EbeanServer get() {
        if (dbConfig == null){
            return null;
        }

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        ServerConfig serverConfig = new ServerConfig();

        String dbType = nullCheck(dbConfig.getString("type"));
        String username = nullCheck(dbConfig.getString("username"));
        String password = nullCheck(dbConfig.getString("password"));

        if (dbType.equals("sqlite")){
            dataSourceConfig.setDriver("org.sqlite.JDBC");
        } else if(dbType.equals("mysql")){
            dataSourceConfig.setDriver("hogehoge");
        } else {
            return null;
        }

        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setUrl("jdbc:sqlite:"+plugin.getDataFolder().getAbsolutePath()+"\\database.db");
        dataSourceConfig.setIsolationLevel(TxIsolation.valueOf("SERIALIZABLE").getLevel());


        serverConfig.setName("db");
        serverConfig.setDdlRun(true);
        serverConfig.setDdlGenerate(true);
        serverConfig.setDataSourceConfig(dataSourceConfig);
        //serverConfig.setClasses(Arrays.asList(HistoryModel.class));
        serverConfig.addClass(HistoryModel.class);
        serverConfig.setClassLoadConfig(new ClassLoadConfig(this.getClass().getClassLoader()));

        return EbeanServerFactory.createWithContextClassLoader(serverConfig, this.getClass().getClassLoader());
    }

    private String nullCheck(String val){
        if (val == null){
            return "";
        } else {
            return val;
        }
    }
}
