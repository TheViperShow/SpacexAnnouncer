package studio.thevipershow.spacexannouncer;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.spacexannouncer.config.Configurations;
import studio.thevipershow.spacexannouncer.commands.SpacexCommand;
import studio.thevipershow.spacexannouncer.telemetry.BStatsMetrics;
import studio.thevipershow.spacexannouncer.telemetry.SpacexMetrics;
import studio.thevipershow.vtc.PluginsConfigurationsManager;

public final class SpacexAnnouncer extends JavaPlugin {

    private final PluginsConfigurationsManager pluginsConfigurationsManager = PluginsConfigurationsManager.getInstance();
    private PaperCommandManager paperCommandManager;
    private BStatsMetrics bStatsMetrics;

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

        bStatsMetrics = new SpacexMetrics(this);
        bStatsMetrics.startMetrics();
    }

    public final PluginsConfigurationsManager getPluginsConfigurationsManager() {
        return pluginsConfigurationsManager;
    }

    public final PaperCommandManager getPaperCommandManager() {
        return paperCommandManager;
    }

    public final BStatsMetrics getbStatsMetrics() {
        return bStatsMetrics;
    }
}
