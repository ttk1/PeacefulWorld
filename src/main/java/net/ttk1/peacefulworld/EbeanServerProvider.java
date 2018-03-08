package net.ttk1.peacefulworld;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.annotation.TxIsolation;
import io.ebean.config.ClassLoadConfig;
import io.ebean.config.ServerConfig;
import org.avaje.datasource.DataSourceConfig;

import com.google.inject.Inject;
import org.bukkit.configuration.ConfigurationSection;

import javax.inject.Provider;

/**
 * @author ttk1 and mmtsk
 */
public class EbeanServerProvider implements Provider<EbeanServer> {
    private PeacefulWorld plugin;
    private ConfigurationSection dbConfig;

    @Inject
    private void setPlugin(PeacefulWorld plugin) {
        this.plugin = plugin;
        this.dbConfig = plugin.getConfig().getConfigurationSection("DB");
    }

    @Override
    public EbeanServer get() {
        if (dbConfig == null){
            return null;
        }

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        ServerConfig serverConfig = new ServerConfig();

        String dbType = dbConfig.getString("type", "sqlite");
        String dbname = dbConfig.getString("dbname", "database");
        String username = dbConfig.getString("username", "");
        String password = dbConfig.getString("password", "");

        if (dbType.equals("sqlite")){
            dataSourceConfig.setDriver("org.sqlite.JDBC");
            dataSourceConfig.setUrl("jdbc:sqlite:"+plugin.getDataFolder().getAbsolutePath()+"\\database.db");
            dataSourceConfig.setIsolationLevel(TxIsolation.valueOf("SERIALIZABLE").getLevel());
        } else if(dbType.equals("mysql")){
            dataSourceConfig.setDriver("com.mysql.jdbc.Driver");
            dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/"+dbname);
            dataSourceConfig.setIsolationLevel(TxIsolation.valueOf("SERIALIZABLE").getLevel());
        } else {
            return null;
        }

        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);

        serverConfig.setResourceDirectory(plugin.getDataFolder().getAbsolutePath());
        serverConfig.setName("db");
        //serverConfig.setDdlCreateOnly(true);
        serverConfig.setDdlRun(true);
        serverConfig.setDdlGenerate(true);
        serverConfig.setDataSourceConfig(dataSourceConfig);
        //serverConfig.setClasses(Arrays.asList(HistoryModel.class));
        //serverConfig.addClass(HistoryModel.class);
        serverConfig.addPackage("net.ttk1.peacefulworld.model");
        serverConfig.setClassLoadConfig(new ClassLoadConfig(this.getClass().getClassLoader()));

        return EbeanServerFactory.createWithContextClassLoader(serverConfig, this.getClass().getClassLoader());
    }
}
