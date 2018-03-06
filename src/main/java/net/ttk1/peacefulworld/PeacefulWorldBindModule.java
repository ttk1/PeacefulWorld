package net.ttk1.peacefulworld;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import io.ebean.EbeanServer;
import net.ttk1.peacefulworld.api.HistoryManager;
import net.ttk1.peacefulworld.history.HistoryManagerImpl;
import org.bukkit.configuration.Configuration;

import java.lang.annotation.Annotation;

/**
 * author ttk1 and mmtsk
 */
public class PeacefulWorldBindModule extends AbstractModule {

    private final PeacefulWorld plugin;

    // let plugin be given one when instantiated
    public PeacefulWorldBindModule(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(PeacefulWorld.class).toInstance(plugin);
        bind(Configuration.class).toInstance(plugin.getConfig());
        bind(EbeanServer.class).toProvider(EbeanServerProvider.class).asEagerSingleton();
    }

}