package studio.thevipershow.spacexannouncer.http.model.cache;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;

public final class JsonResponseCacheFactory {

    private static JsonResponseCacheFactory instance = null;

    private JsonResponseCacheFactory() {
    }

    public static JsonResponseCacheFactory getInstance() {
        if (instance == null) {
            instance = new JsonResponseCacheFactory();
        }
        return instance;
    }

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
