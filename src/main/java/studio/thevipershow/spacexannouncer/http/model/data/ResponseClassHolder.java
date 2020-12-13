package studio.thevipershow.spacexannouncer.http.model.data;

@FunctionalInterface
public interface ResponseClassHolder {

    Class<? extends AbstractJsonResponse> getAbstractJsonResponseType();
}
