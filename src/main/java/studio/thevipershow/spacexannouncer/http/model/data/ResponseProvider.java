package studio.thevipershow.spacexannouncer.http.model.data;

import java.util.concurrent.CompletableFuture;

public interface ResponseProvider {

    <T extends AbstractJsonResponse> CompletableFuture<T> makeRequest();
}
