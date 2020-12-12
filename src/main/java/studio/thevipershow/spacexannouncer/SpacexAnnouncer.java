package studio.thevipershow.spacexannouncer;

import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.spacexannouncer.config.Configurations;
import studio.thevipershow.vtc.PluginsConfigurationsManager;

public final class SpacexAnnouncer extends JavaPlugin {

    private final PluginsConfigurationsManager pluginsConfigurationsManager = PluginsConfigurationsManager.getInstance();

    @Override
    public final void onEnable() { // Plugin startup logic
        pluginsConfigurationsManager.loadPluginData(this, Configurations.class);
        pluginsConfigurationsManager.getPluginData(this).getLoadedTomlConfigs().values().forEach(tomlConfig -> {
            tomlConfig.exportResource(false);
            tomlConfig.storeContent();
            tomlConfig.loadAllValues();
        });
    }
}
