/**
 * Copyright (C) Tama's Workshop 2018
 */

package net.ttk1.peacefulworld;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.history.HistoryManagerImpl;
import net.ttk1.peacefulworld.listener.BlockPlaceEventListener;
import org.bukkit.Location;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ClassLoadConfig;
import io.ebean.config.ServerConfig;
import net.ttk1.peacefulworld.api.HistoryManager;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ClassLoadConfig;
import io.ebean.config.ServerConfig;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.avaje.datasource.DataSourceConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author ttk1 and mmtsk
 */
public class PeacefulWorld extends JavaPlugin {

    @Inject private BlockPlaceEventListener blockPlaceEventListener;

    private HistoryManager historyManager;
    private FileConfiguration conf;
    private Logger logger;
    private EbeanServer server;

    public PeacefulWorld(){
        super();
        this.logger = getLogger();
    }

    @Override
    public void onEnable() {

        PeacefulWorldBindModule module = new PeacefulWorldBindModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        // 設定ファイルの読み込み
        createConfig();
        conf = getConfig();

        ServerConfig sc = new ServerConfig();
        sc.setDefaultServer(true);
        sc.setRegister(true);
        sc.setName("db");
        sc.setClasses(Arrays.asList(HistoryModel.class));

        URLClassLoader urlClassLoader = null;
        ClassLoader currentClassLoader = null;
        try {
            InputStream inputStream = new FileInputStream(new File(getDataFolder(), "ebean.properties"));
            Properties properties = new Properties();
            properties.load(inputStream);
            sc.loadFromProperties(properties);
            sc.setClassLoadConfig(new ClassLoadConfig(this.getClass().getClassLoader()));
            server = EbeanServerFactory.create(sc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*File f = new File("D:/testserver/plugins/PeacefulWorld-0.0-SNAPSHOT.jar");
        URI u = f.toURI();
        try {
            urlClassLoader = new URLClassLoader(new URL[]{u.toURL()});
            currentClassLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(urlClassLoader);
            server = EbeanServerFactory.create(sc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if(currentClassLoader != null) {
             //   Thread.currentThread().setContextClassLoader(currentClassLoader);
            }
        }*/
        /*
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(urlClassLoader, new Object[]{u.toURL()});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/

        HistoryModel historyModel = new HistoryModel("hello");
        historyModel.save();
        //server = Ebean.getServer("db");
        //server.save(historyModel);

        logger.info("PeacefulWorld enabled");
        logger.info(conf.getString("test"));

    }

    @Override
    public void onDisable() {
        logger.info("PeacefulWorld disabled");
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }


    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
            file = new File(getDataFolder(), "ebean.properties");
            if (!file.exists()) {
                getLogger().info("ebean.properties not found, creating!");
                saveResource("ebean.properties", false);
            } else {
                getLogger().info("ebean.properties found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}