/**
 * Copyright (C) Tama's Workshop 2018
 */

package net.ttk1.peacefulworld;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.listener.BlockPlaceEventListener;
import net.ttk1.peacefulworld.listener.SessionListener;
import net.ttk1.peacefulworld.model.HistoryChainModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author ttk1 and mmtsk
 */
public class PeacefulWorld extends JavaPlugin {

    @Inject private BlockPlaceEventListener blockPlaceEventListener;
    @Inject private SessionListener sessionListener;
    @Inject private HistoryManager historyManager;
    private Configuration config;
    private Logger logger;

    @Override
    public void onEnable() {
        // クラスローダーを書き換える
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader());

        // 初期化処理
        init();

        PeacefulWorldBindModule module = new PeacefulWorldBindModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);


        // event listenerの登録
        {
            getServer().getPluginManager().registerEvents(sessionListener, this);
            getServer().getPluginManager().registerEvents(blockPlaceEventListener, this);
        }

        // クラスローダーを元に戻す
        Thread.currentThread().setContextClassLoader(currentClassLoader);
        logger.info("PeacefulWorld enabled");
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

        // debug用の設定はここに
        if (!config.getBoolean("debug", false)) {
            logger.info("debug mode enabled");
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("pw") && args.length >= 1){
            try{
                for (int i = 0; i <= 10; i++){
                    sender.sendMessage(args[0]+i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}