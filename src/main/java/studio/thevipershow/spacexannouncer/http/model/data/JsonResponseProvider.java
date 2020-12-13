package studio.thevipershow.spacexannouncer.http.model.data;

import java.util.concurrent.CompletableFuture;

public interface JsonResponseProvider {

    /**
     * Provide a json response through a typed CompletableFuture.
     * @param <T> The type of the response.
     * @return The completable future.
     */
    <T extends AbstractJsonResponse> CompletableFuture<T> makeRequest();
}
