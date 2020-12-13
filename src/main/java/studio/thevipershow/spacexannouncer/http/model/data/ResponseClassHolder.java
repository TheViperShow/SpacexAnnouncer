package studio.thevipershow.spacexannouncer.http.model.data;

@FunctionalInterface
public interface ResponseClassHolder {

    /**
     * Get the response type
     *
     * @return A response type.
     */
    Class<? extends AbstractJsonResponse> getAbstractJsonResponseType();
}
