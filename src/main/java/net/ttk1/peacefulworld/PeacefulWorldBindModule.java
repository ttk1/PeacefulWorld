package net.ttk1.peacefulworld;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * author ttk1 and mmtsk
 */
public class PeacefulWorldBindModule extends AbstractModule {

    private final PeacefulWorld plugin;

    // let plugin be given one when instantiated
    public PeacefulWorldBindModule(PeacefulWorld plugin) {
        this.plugin = plugin;
    }

    // Guice.createInjector method requires AbstractModule with configure method
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        this.bind(PeacefulWorld.class).toInstance(this.plugin);
    }

}
