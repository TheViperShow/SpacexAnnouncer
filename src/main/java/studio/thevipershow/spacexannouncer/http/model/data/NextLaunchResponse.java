package studio.thevipershow.spacexannouncer.http.model.data;

import studio.thevipershow.spacexannouncer.http.model.RequestType;

public final class NextLaunchResponse extends AbstractJsonResponse {

    private String rocketUID = UNAVAILABLE;
    private long fireDateUnixTime = -1;
    private String details = UNAVAILABLE;
    private int flightNumber = -1;
    private String missionName = UNAVAILABLE;
    private String youtubeWebcast = UNAVAILABLE;

    public NextLaunchResponse(String json) {
        super(json, RequestType.LAUNCH);
    }

    @Override
    public final void tryAssignValues() {
        try {
            setRocketUID(jsonObject.get("rocket").getAsString());
            setFireDateUnixTime(jsonObject.get("date_unix").getAsLong());
            setDetails(jsonObject.get("details").getAsString());
            setFlightNumber(jsonObject.get("flight_number").getAsInt());
            setMissionName(jsonObject.get("name").getAsString());
            var webcast = jsonObject.get("links").getAsJsonObject().get("webcast");
            if (!webcast.isJsonNull()) {
                setYoutubeWebcast(webcast.getAsString());
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * Get the UID of the rocket.
     * This UID can be used inside rocket requests
     * to obtain rocket information from SpaceX API.
     *
     * @return The UID of this rocket.
     */
    public final String getRocketUID() {
        return rocketUID;
    }

    /**
     * Set the rocket UID.
     *
     * @param rocketUID A valid rocket UID.
     */
    public final void setRocketUID(String rocketUID) {
        this.rocketUID = rocketUID;
    }

    /**
     * Get the UNIX time of the launch.
     *
     * @return The UNIX time of the launch start.
     */
    public final long getFireDateUnixTime() {
        return fireDateUnixTime;
    }

    /**
     * Set the launch unix time.
     *
     * @param fireDateUnixTime The unix time.
     */
    public final void setFireDateUnixTime(long fireDateUnixTime) {
        this.fireDateUnixTime = fireDateUnixTime;
    }

    /**
     * Get some information about this launch
     * and what will be accomplished through it.
     *
     * @return Launch details.
     */
    public final String getDetails() {
        return details;
    }

    /**
     * Set the details of this launch.
     *
     * @param details The details.
     */
    public final void setDetails(String details) {
        this.details = details;
    }

    /**
     * Get the number of this flight.
     * It represents how many flights have been
     * previously done.
     *
     * @return The number of this flight.
     */
    public final int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Set this flight's number.
     *
     * @param flightNumber The flight's number.
     */
    public final void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Get the name of this mission.
     *
     * @return The name of this mission.
     */
    public final String getMissionName() {
        return missionName;
    }

    /**
     * Set the name of this mission.
     *
     * @param missionName The name of this mission.
     */
    public final void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    /**
     * Get the link that allows user to see this launch
     * through the YouTube streaming platform.
     *
     * @return A valid YouTube link to observe this launch.
     */
    public final String getYoutubeWebcast() {
        return youtubeWebcast;
    }

    /**
     * Set the youtube link.
     *
     * @param youtubeWebcast A valid youtube link for this launch.
     */
    public final void setYoutubeWebcast(String youtubeWebcast) {
        this.youtubeWebcast = youtubeWebcast;
    }
}
