package studio.thevipershow.spacexannouncer;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.spacexannouncer.config.Configurations;
import studio.thevipershow.spacexannouncer.commands.SpacexCommand;
import studio.thevipershow.vtc.PluginsConfigurationsManager;

public final class SpacexAnnouncer extends JavaPlugin {

    private final PluginsConfigurationsManager pluginsConfigurationsManager = PluginsConfigurationsManager.getInstance();
    private PaperCommandManager paperCommandManager;

    @Override
    public final void onEnable() { // Plugin startup logic
        pluginsConfigurationsManager.loadPluginData(this, Configurations.class);
        pluginsConfigurationsManager.getPluginData(this).getLoadedTomlConfigs().values().forEach(tomlConfig -> {
            tomlConfig.exportResource(false);
            tomlConfig.storeContent();
            tomlConfig.loadAllValues();
        });

        paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.enableUnstableAPI("help");
        paperCommandManager.registerCommand(new SpacexCommand(this));
    }
}
