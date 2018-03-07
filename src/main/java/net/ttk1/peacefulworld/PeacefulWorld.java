/**
 * Copyright (C) Tama's Workshop 2018
 */

package net.ttk1.peacefulworld;

import ch.qos.logback.classic.Level;
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

    public PeacefulWorld(){
        //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean.SQL")).setLevel(Level.INFO);
        //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean.SUM")).setLevel(Level.INFO);
        //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean.TXN")).setLevel(Level.INFO);
        //((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean.DDL")).setLevel(Level.INFO);
        ((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebean")).setLevel(Level.INFO);
        ((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("io.ebeaninternal")).setLevel(Level.INFO);
        ((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("org.avaje")).setLevel(Level.INFO);
        ((ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger("org.avaje")).setLevel(Level.INFO);

        this.logger = getLogger();
        this.config = getConfig();
    }

    @Override
    public void onEnable() {
        // loading congfig.yml
        loadConfig();

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
    }

    @Override
    public void onDisable() {
        logger.info("PeacefulWorld disabled");
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
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