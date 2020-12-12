package studio.thevipershow.spacexannouncer.config.announcements;

import studio.thevipershow.vtc.ConfigurationEntryAndType;

public enum AnnouncementSection implements ConfigurationEntryAndType {

    AUTO_ANNOUNCE("announcements.auto-announce", Boolean.class),
    COUNTDOWN_ENABLED("countdown.enabled", Boolean.class),
    SECONDS_BEFORE("countdown.seconds-before", Long.class),
    COUNTDOWN_INTERVAL("countdown.announce-interval", Long.class),
    ANNOUNCE_TYPE("countdown.announce-type", String.class),
    MESSAGE_FORMAT("countdown.message-format", String.class),
    ENABLE_SOUNDS("countdown.sounds.enabled", Boolean.class),
    SOUND_NAME("countdown.sounds.sound-name", String.class),
    PITCH("countdown.sounds.pitch", Double.class),
    VOLUME("countdown.sounds.volume", Double.class);

    AnnouncementSection(String configKey, Class<?> returnTypeClass) {
        this.configKey = configKey;
        this.returnTypeClass = returnTypeClass;
    }

    private final String configKey;
    private final Class<?> returnTypeClass;

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @Override
    public final @org.jetbrains.annotations.NotNull Class<?> getClassData() {
        return returnTypeClass;
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
    public final @org.jetbrains.annotations.NotNull String getStringData() {
        return configKey;
    }
}
