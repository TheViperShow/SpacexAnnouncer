package studio.thevipershow.spacexannouncer.http.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class NextRocketResponse {

    private final String json;
    private final JsonObject jsonObject;

    private double rocketHeight;
    private double rocketDiameter;
    private int rocketMassKilograms;
    private String rocketName;
    private String rocketType;
    private int stages;
    private int boosters;
    private int launchCost;
    private String country;
    private String company;
    private String description;

    public NextRocketResponse(String json) {
        this.json = json;
        this.jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (!this.jsonObject.isJsonObject()) {
            throw new RuntimeException("This was not a json object.");
        }
    }

    public final void tryAssignValues() {
        setRocketHeight(jsonObject.get("height").getAsJsonObject().get("meters").getAsDouble());
        setRocketDiameter(jsonObject.get("diameter").getAsJsonObject().get("meters").getAsDouble());
        setRocketMassKilograms(jsonObject.get("mass").getAsJsonObject().get("kg").getAsInt());
        setRocketName(jsonObject.get("name").getAsString());
        setRocketType(jsonObject.get("type").getAsString());
        setStages(jsonObject.get("stages").getAsInt());
        setBoosters(jsonObject.get("boosters").getAsInt());
        setLaunchCost(jsonObject.get("cost_per_launch").getAsInt());
        setCountry(jsonObject.get("country").getAsString());
        setCompany(jsonObject.get("company").getAsString());
        setDescription(jsonObject.get("description").getAsString());
    }

    public String getJson() {
        return json;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public double getRocketHeight() {
        return rocketHeight;
    }

    public void setRocketHeight(double rocketHeight) {
        this.rocketHeight = rocketHeight;
    }

    public double getRocketDiameter() {
        return rocketDiameter;
    }

    public void setRocketDiameter(double rocketDiameter) {
        this.rocketDiameter = rocketDiameter;
    }

    public int getRocketMassKilograms() {
        return rocketMassKilograms;
    }

    public void setRocketMassKilograms(int rocketMassKilograms) {
        this.rocketMassKilograms = rocketMassKilograms;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getRocketType() {
        return rocketType;
    }

    public void setRocketType(String rocketType) {
        this.rocketType = rocketType;
    }

    public int getStages() {
        return stages;
    }

    public void setStages(int stages) {
        this.stages = stages;
    }

    public int getBoosters() {
        return boosters;
    }

    public void setBoosters(int boosters) {
        this.boosters = boosters;
    }

    public int getLaunchCost() {
        return launchCost;
    }

    public void setLaunchCost(int launchCost) {
        this.launchCost = launchCost;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
