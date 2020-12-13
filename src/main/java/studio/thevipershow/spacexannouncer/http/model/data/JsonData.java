package studio.thevipershow.spacexannouncer.http.model.data;

@FunctionalInterface
public interface JsonData {

    /**
     * Try and assign values in the Json data.
     * This method may do nothing if the data
     * is invalid or unreadable.
     */
    void tryAssignValues();
}
