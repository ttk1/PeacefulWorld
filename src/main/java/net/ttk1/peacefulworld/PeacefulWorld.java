package net.ttk1.peacefulworld;

import net.ttk1.peacefulworld.api.History;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.history.HistoryManagerImpl;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PeacefulWorld extends JavaPlugin {
    private HistoryManager historyManager;
    private FileConfiguration conf;

    public PeacefulWorld(){
        super();
    }

    @Override
    public void onEnable() {
        // 設定ファイルの読み込み
        conf = getConfig();

        // DBとの接続周りはHistoryManagerの実装に任せる
        this.historyManager = new HistoryManagerImpl(conf);
        getLogger().info("PeacefulWorld enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("PeacefulWorld disabled");
    }
}