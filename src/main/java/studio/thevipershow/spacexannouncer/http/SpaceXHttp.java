package studio.thevipershow.spacexannouncer.http;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.cache.JsonResponseCacheFactory;
import studio.thevipershow.spacexannouncer.http.model.cache.SpaceXRequestsCache;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseClassHolder;
import studio.thevipershow.spacexannouncer.http.model.data.JsonResponseProvider;

public final class SpaceXHttp<S extends Enum<S> & ResponseClassHolder & JsonResponseProvider> {

    private final JsonResponseCacheFactory jsonResponseCacheFactory = JsonResponseCacheFactory.getInstance();
    private final SpaceXRequestsCache<S> spaceXRequestsCache;

    public SpaceXHttp() {
        this.spaceXRequestsCache = new SpaceXRequestsCache<>(this);
    }

    public final <T extends AbstractJsonResponse> CompletableFuture<T> makeRequest(@NotNull S s) {
        return spaceXRequestsCache.getResponse(s);
    }

    /**
     * Get the factory for json cache data responses.
     *
     * @return Json responses factory.
     */
    @NotNull
    public final JsonResponseCacheFactory getJsonResponseCacheFactory() {
        return jsonResponseCacheFactory;
    }

    /**
     * Get cache for spacex requests.
     *
     * @return The cache container for all spacex requests.
     */
    @NotNull
    public final SpaceXRequestsCache<S> getSpaceXRequestsCache() {
        return spaceXRequestsCache;
    }
}
