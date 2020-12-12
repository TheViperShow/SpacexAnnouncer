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
        setFireDateUnixTime(jsonObject.get("static_fire_date_unix").getAsLong());
        setDetails(jsonObject.get("details").getAsString());
        setFlightNumber(jsonObject.get("flight_number").getAsInt());
        setMissionName(jsonObject.get("name").getAsString());
        setYoutubeWebcast(jsonObject.get("links").getAsJsonObject().get("webcast").getAsString());
    }

    public String getJson() {
        return json;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public String getRocketUID() {
        return rocketUID;
    }

    public void setRocketUID(String rocketUID) {
        this.rocketUID = rocketUID;
    }

    public long getFireDateUnixTime() {
        return fireDateUnixTime;
    }

    public void setFireDateUnixTime(long fireDateUnixTime) {
        this.fireDateUnixTime = fireDateUnixTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getYoutubeWebcast() {
        return youtubeWebcast;
    }

    public void setYoutubeWebcast(String youtubeWebcast) {
        this.youtubeWebcast = youtubeWebcast;
    }
}
