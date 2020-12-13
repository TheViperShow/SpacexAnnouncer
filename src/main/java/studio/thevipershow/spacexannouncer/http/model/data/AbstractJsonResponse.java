package studio.thevipershow.spacexannouncer.http.model.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.RequestType;

public abstract class AbstractJsonResponse implements JsonData {

    protected static final String UNAVAILABLE = "Unavailable";
    protected final String json;
    protected final RequestType requestType;
    protected final JsonObject jsonObject;

    public AbstractJsonResponse(@NotNull String json, @NotNull RequestType requestType) {
        this.json = json;
        this.requestType = requestType;
        this.jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (!this.jsonObject.isJsonObject()) {
            throw new IllegalArgumentException("Passed json data is not a valid json object.");
        }
    }

    /**
     * Get the json text.
     * @return Text as json.
     */
    public String getJson() {
        return json;
    }

    /**
     * Get the json as object.
     * @return The json text as object.
     */
    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
