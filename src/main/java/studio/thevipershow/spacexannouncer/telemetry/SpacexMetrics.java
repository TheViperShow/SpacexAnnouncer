package studio.thevipershow.spacexannouncer.telemetry;

import org.bstats.bukkit.Metrics;
import studio.thevipershow.spacexannouncer.SpacexAnnouncer;

public class SpacexMetrics implements BStatsMetrics {

    private final SpacexAnnouncer spacexAnnouncer;
    private Metrics metrics;

    public SpacexMetrics(SpacexAnnouncer spacexAnnouncer) {
        this.spacexAnnouncer = spacexAnnouncer;
    }

    @Override
    public final void startMetrics() {
        metrics = new Metrics(spacexAnnouncer, id);
    }
}
