package studio.thevipershow.spacexannouncer.http.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class NextLaunchResponse {

    private final String json;
    private final JsonObject jsonObject;

    private String rocketUID;
    private long fireDateUnixTime;
    private String details;
    private int flightNumber;
    private String missionName;
    private String youtubeWebcast;

    public NextLaunchResponse(String json) {
        this.json = json;
        this.jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (!this.jsonObject.isJsonObject()) {
            throw new RuntimeException("The data was not a json object.");
        }
    }

    public final void tryAssignValues() {
        setRocketUID(jsonObject.get("rocket").getAsString());
        setFireDateUnixTime(jsonObject.get("date_unix").getAsLong());
        setDetails(jsonObject.get("details").getAsString());
        setFlightNumber(jsonObject.get("flight_number").getAsInt());
        setMissionName(jsonObject.get("name").getAsString());
        setYoutubeWebcast(jsonObject.get("links").getAsJsonObject().get("webcast").getAsString());
    }

    public final String getJson() {
        return json;
    }

    public final JsonObject getJsonObject() {
        return jsonObject;
    }

    public final String getRocketUID() {
        return rocketUID;
    }

    public final void setRocketUID(String rocketUID) {
        this.rocketUID = rocketUID;
    }

    public final long getFireDateUnixTime() {
        return fireDateUnixTime;
    }

    public final void setFireDateUnixTime(long fireDateUnixTime) {
        this.fireDateUnixTime = fireDateUnixTime;
    }

    public final String getDetails() {
        return details;
    }

    public final void setDetails(String details) {
        this.details = details;
    }

    public final int getFlightNumber() {
        return flightNumber;
    }

    public final void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public final String getMissionName() {
        return missionName;
    }

    public final void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public final String getYoutubeWebcast() {
        return youtubeWebcast;
    }

    public final void setYoutubeWebcast(String youtubeWebcast) {
        this.youtubeWebcast = youtubeWebcast;
    }
}
