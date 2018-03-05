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

    private HistoryManager historyManager;
    private Configuration conf;
    private Logger logger;

    public PeacefulWorld(){
        super();
        this.logger = getLogger();
    }

    @Override
    public void onEnable() {

        loadConfig();

        PeacefulWorldBindModule module = new PeacefulWorldBindModule(this, getConfig());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

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