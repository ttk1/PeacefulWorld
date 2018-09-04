package net.ttk1.peacefulworld;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import io.ebean.EbeanServer;

/**
 * author ttk1 and mmtsk
 */
public class PluginModule extends AbstractModule {

    private final PeacefulWorld plugin;

    // let plugin be given one when instantiated
    public PluginModule(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(PeacefulWorld.class).toInstance(plugin);
        bind(EbeanServer.class).toProvider(EbeanServerProvider.class).asEagerSingleton();
        bind(String.class).annotatedWith(Names.named("ebeanServerName")).toInstance(plugin.getDataFolder().getAbsolutePath()+"\\database");
    }
}