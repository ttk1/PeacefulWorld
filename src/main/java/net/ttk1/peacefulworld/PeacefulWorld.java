/**
 * Copyright (C) Tama's Workshop 2018
 */

package net.ttk1.peacefulworld;

import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.history.HistoryManagerImpl;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author ttk1 and mmtsk
 */
public class PeacefulWorld extends JavaPlugin {
    private HistoryManager historyManager;
    private FileConfiguration conf;
    private Logger logger;

    public PeacefulWorld(){
        super();
        this.logger = getLogger();
    }

    @Override
    public void onEnable() {
        // 設定ファイルの読み込み
        conf = getConfig();

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
}