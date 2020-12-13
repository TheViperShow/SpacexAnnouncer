package studio.thevipershow.spacexannouncer.http;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.cache.JsonResponseCacheFactory;
import studio.thevipershow.spacexannouncer.http.model.cache.SpaceXRequestsCache;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseClassHolder;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseProvider;

public final class SpaceXHttp<S extends Enum<S> & ResponseClassHolder & ResponseProvider> {

    private final JsonResponseCacheFactory jsonResponseCacheFactory = JsonResponseCacheFactory.getInstance();
    private final SpaceXRequestsCache<S> spaceXRequestsCache;

    public SpaceXHttp() {
        this.spaceXRequestsCache = new SpaceXRequestsCache<>(this);
    }

    public final <T extends AbstractJsonResponse> CompletableFuture<T> makeRequest(@NotNull S s) {
        return spaceXRequestsCache.getResponse(s);
    }

    @NotNull
    public final JsonResponseCacheFactory getJsonResponseCacheFactory() {
        return jsonResponseCacheFactory;
    }

    @NotNull
    public final SpaceXRequestsCache<S> getSpaceXRequestsCache() {
        return spaceXRequestsCache;
    }
}
