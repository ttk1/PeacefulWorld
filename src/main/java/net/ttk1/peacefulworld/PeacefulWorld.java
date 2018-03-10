/**
 * Copyright (C) Tama's Workshop 2018
 */

package net.ttk1.peacefulworld;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.listener.BlockPlaceEventListener;
import net.ttk1.peacefulworld.model.HistoryModel;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author ttk1 and mmtsk
 */
public class PeacefulWorld extends JavaPlugin {

    @Inject private BlockPlaceEventListener blockPlaceEventListener;
    @Inject private HistoryManager historyManager;
    private Configuration config;
    private Logger logger;

    @Override
    public void onEnable() {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader());

        // initialize
        init();

        PeacefulWorldBindModule module = new PeacefulWorldBindModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        logger.info("PeacefulWorld enabled");

        // test code
        {
            logger.info(config.getString("test"));
            logger.info(config.getConfigurationSection("test2").getString("test3"));
            HistoryModel historyModel = new HistoryModel("hello");
            historyModel.save();
            logger.info("ID: "+historyModel.getId()+", NAME: "+historyModel.getName());
        }

        Thread.currentThread().setContextClassLoader(currentClassLoader);
    }

    @Override
    public void onDisable() {
        logger.info("PeacefulWorld disabled");
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    private void init(){
        loadConfig();
        config = getConfig();
        logger = getLogger();

        // debug mode
        if (!config.getBoolean("debug", false)) {
            //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean")).setLevel(ch.qos.logback.classic.Level.INFO);
            //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebeaninternal")).setLevel(ch.qos.logback.classic.Level.INFO);
            //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("org.avaje")).setLevel(ch.qos.logback.classic.Level.INFO);
        }
    }

    private void loadConfig() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}