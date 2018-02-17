package net.ttk1.peacefulworld;

import org.bukkit.plugin.java.JavaPlugin;

public class PeacefulWorld extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PeacefulWorld enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("PeacefulWorld disabled");
    }
}
