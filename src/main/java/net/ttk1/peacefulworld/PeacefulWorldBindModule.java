package net.ttk1.peacefulworld;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.ebean.EbeanServer;
import org.bukkit.configuration.Configuration;

/**
 * author ttk1 and mmtsk
 */
public class PeacefulWorldBindModule extends AbstractModule {

    private final PeacefulWorld plugin;
    private final Configuration configuration;

    // let plugin be given one when instantiated
    public PeacefulWorldBindModule(PeacefulWorld plugin, Configuration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
    }

    // Guice.createInjector method requires AbstractModule with configure method
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        this.bind(PeacefulWorld.class).toInstance(this.plugin);
        this.bind(Configuration.class).toInstance(this.configuration);
        this.bind(EbeanServer.class).toProvider(EbeanServerProvider.class).asEagerSingleton();
    }

}
