package studio.thevipershow.spacexannouncer.config.announcements;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.SpacexAnnouncer;
import studio.thevipershow.vtc.TomlSectionConfiguration;

public final class AnnouncementSectionConfig extends TomlSectionConfiguration<SpacexAnnouncer, AnnouncementSection> {

    /**
     * Constructor for abstract class:
     *
     * @param javaPlugin            Your plugin instance.
     */
    public AnnouncementSectionConfig(@NotNull final SpacexAnnouncer javaPlugin) {
        super(javaPlugin, "announcements.toml", AnnouncementSection.class);
    }
}
