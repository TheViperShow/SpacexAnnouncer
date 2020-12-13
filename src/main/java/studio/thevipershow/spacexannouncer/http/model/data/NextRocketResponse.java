package studio.thevipershow.spacexannouncer.http.model.data;

import studio.thevipershow.spacexannouncer.http.model.RequestType;

public final class NextRocketResponse extends AbstractJsonResponse {

    private double rocketHeight = -1;
    private double rocketDiameter = -1;
    private int rocketMassKilograms = -1;
    private String rocketName = UNAVAILABLE;
    private String rocketType = UNAVAILABLE;
    private int stages = -1;
    private int boosters = -1;
    private int launchCost = -1;
    private String country = UNAVAILABLE;
    private String company = UNAVAILABLE;
    private String description = UNAVAILABLE;

    public NextRocketResponse(String json) {
        super(json, RequestType.ROCKET);
    }

    @Override
    public final void tryAssignValues() {
        try {
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
        } catch (Exception ignored) {

        }
    }

    /**
     * Get the height of this rocket in meters.
     *
     * @return The height of this rocket.
     */
    public double getRocketHeight() {
        return rocketHeight;
    }

    /**
     * Set the height of this rocket.
     *
     * @param rocketHeight the height of this rocket.
     */
    public void setRocketHeight(double rocketHeight) {
        this.rocketHeight = rocketHeight;
    }

    /**
     * Get the diameter of this rocket in meters.
     *
     * @return the diameter of this rocket.
     */
    public double getRocketDiameter() {
        return rocketDiameter;
    }

    /**
     * Set the diameter of this rocket
     *
     * @param rocketDiameter The diameter.
     */
    public void setRocketDiameter(double rocketDiameter) {
        this.rocketDiameter = rocketDiameter;
    }

    /**
     * Get the mass of the rocket in kilograms.
     *
     * @return Get the mass of this rocket.
     */
    public int getRocketMassKilograms() {
        return rocketMassKilograms;
    }

    /**
     * Set the mass of this rocket.
     *
     * @param rocketMassKilograms The mass of the rocket.
     */
    public void setRocketMassKilograms(int rocketMassKilograms) {
        this.rocketMassKilograms = rocketMassKilograms;
    }

    /**
     * Get the name of this rocket.
     * (Example: Falcon 9)
     *
     * @return The name of this rocket.
     */
    public String getRocketName() {
        return rocketName;
    }

    /**
     * Set the name of this rocket.
     *
     * @param rocketName the name of this rocket.
     */
    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    /**
     * Get the type of this rocket.
     *
     * @return The type of this rocket.
     */
    public String getRocketType() {
        return rocketType;
    }

    /**
     * Set the type of this rocket.
     *
     * @param rocketType The type of the rocket.
     */
    public void setRocketType(String rocketType) {
        this.rocketType = rocketType;
    }

    /**
     * Get the stages of the rocket.
     *
     * @return The total number of this rocket stages.
     */
    public int getStages() {
        return stages;
    }

    /**
     * Set the stages of this rocket.
     *
     * @param stages The stages of this rocket.
     */
    public void setStages(int stages) {
        this.stages = stages;
    }

    /**
     * Get the number of boosters that this rocket has.
     *
     * @return The number of boosters.
     */
    public int getBoosters() {
        return boosters;
    }

    /**
     * Set the number of boosters that this rocket has.
     *
     * @param boosters The number of boosters.
     */
    public void setBoosters(int boosters) {
        this.boosters = boosters;
    }

    /**
     * Get the estimated cost of this rocket operation.
     *
     * @return The cost of this rocket.
     */
    public int getLaunchCost() {
        return launchCost;
    }

    /**
     * Set the cost of this rocket.
     *
     * @param launchCost The rocket cost.
     */
    public void setLaunchCost(int launchCost) {
        this.launchCost = launchCost;
    }

    /**
     * Get the country that the rocket is currently in.
     *
     * @return The country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the current country of this rocket.
     *
     * @param country The country name.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the company that worked on this rocket.
     *
     * @return The name of this company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the name of the company.
     *
     * @param company The name of the company.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Get a small description that explains features and
     * usages of this rocket.
     *
     * @return A description for this rocket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for this rocket.
     *
     * @param description A description for the rocket.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
