package studio.thevipershow.spacexannouncer.telemetry;

@FunctionalInterface
public interface BStatsMetrics {

    int id = 9639;

    /**
     * Start metrics service
     */
    void startMetrics();
}
