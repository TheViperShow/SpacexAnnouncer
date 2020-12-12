package studio.thevipershow.spacexannouncer.config;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.SpacexAnnouncer;
import studio.thevipershow.spacexannouncer.config.announcements.AnnouncementSectionConfig;
import studio.thevipershow.vtc.ConfigurationEntryAndType;
import studio.thevipershow.vtc.TomlSectionConfiguration;

public enum Configurations implements ConfigurationEntryAndType {
    ANNOUNCEMENTS("announcements.toml", AnnouncementSectionConfig.class);

    private final String configName;
    private final Class<? extends TomlSectionConfiguration<SpacexAnnouncer, ?>> configClass;

    Configurations(String configName, Class<? extends TomlSectionConfiguration<SpacexAnnouncer, ?>> configClass) {
        this.configClass = configClass;
        this.configName = configName;
    }

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @Override
    public @NotNull Class<? extends TomlSectionConfiguration<SpacexAnnouncer, ?>> getClassData() {
        return configClass;
    }

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @Override
    public @NotNull String getStringData() {
        return configName;
    }
}
