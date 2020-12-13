package studio.thevipershow.spacexannouncer.http.model.cache;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.SpaceXHttp;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseClassHolder;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseProvider;

public final class SpaceXRequestsCache<S extends Enum<S> & ResponseClassHolder & ResponseProvider> {

    private final Map<S, JsonResponseCache<?>> cacheMap;
    private final SpaceXHttp<S> spaceXHttp;
    private static final long CACHE_TIME_SECONDS = 60L;

    public SpaceXRequestsCache(@NotNull SpaceXHttp<S> spaceXHttp) {
        this.spaceXHttp = spaceXHttp;
        this.cacheMap = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public final <T extends AbstractJsonResponse> CompletableFuture<T> getResponse(@NotNull S responseEnumType) {
        if (!this.cacheMap.containsKey(responseEnumType)) {
            return (CompletableFuture<T>) responseEnumType.makeRequest().whenComplete((v, e) -> {
                if (v != null) {
                    this.cacheMap.put(responseEnumType, spaceXHttp.getJsonResponseCacheFactory().buildCache(v));
                }
            });
        } else {
            var data = this.cacheMap.get(responseEnumType);
            if ((System.currentTimeMillis() - data.getCreationTime()) / 1000 <= CACHE_TIME_SECONDS) {
                return (CompletableFuture<T>) CompletableFuture.completedFuture(this.cacheMap.get(responseEnumType).getJsonResponse());
            } else { // yeah duplicated code.
                return (CompletableFuture<T>) responseEnumType.makeRequest().whenComplete((v, e) -> {
                    if (v != null) {
                        this.cacheMap.put(responseEnumType, spaceXHttp.getJsonResponseCacheFactory().buildCache(v));
                    }
                });
            }
        }
    }

    @NotNull
    public final Map<S, JsonResponseCache<?>> getCacheMap() {
        return cacheMap;
    }

    @NotNull
    public final SpaceXHttp<S> getSpaceXHttp() {
        return spaceXHttp;
    }
}
