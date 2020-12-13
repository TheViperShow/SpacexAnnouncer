package studio.thevipershow.spacexannouncer.http.model.cache;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;

public final class JsonResponseCacheFactory {

    private static JsonResponseCacheFactory instance = null;

    private JsonResponseCacheFactory() {
    }

    /**
     * Get the instance of this class
     *
     * @return This object instance.
     */
    public static synchronized JsonResponseCacheFactory getInstance() {
        if (instance == null) {
            instance = new JsonResponseCacheFactory();
        }
        return instance;
    }

    /**
     * Build cache for a json response.
     *
     * @param data The response data.
     * @param <T>  The type of the response.
     * @return a json response cache with the same type of the data.
     */
    @NotNull
    public final <T extends AbstractJsonResponse> JsonResponseCache<T> buildCache(@NotNull T data) {
        final long currentTime = System.currentTimeMillis();

        class TempCache implements JsonResponseCache<T> {

            private final long creationTime;
            private final T responseData;

            public TempCache(long creationTime, T responseData) {
                this.creationTime = creationTime;
                this.responseData = responseData;
            }

            @Override
            public final long getCreationTime() {
                return this.creationTime;
            }

            @Override
            public final @NotNull T getJsonResponse() {
                return this.responseData;
            }
        }

        return new TempCache(currentTime, data);
    }
}
