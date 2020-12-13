package studio.thevipershow.spacexannouncer.http.model.cache;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.SpaceXHttp;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseClassHolder;
import studio.thevipershow.spacexannouncer.http.model.data.JsonResponseProvider;

public final class SpaceXRequestsCache<S extends Enum<S> & ResponseClassHolder & JsonResponseProvider> {

    private final Map<S, JsonResponseCache<?>> cacheMap;
    private final SpaceXHttp<S> spaceXHttp;
    private static final long CACHE_TIME_SECONDS = 60L;

    public SpaceXRequestsCache(@NotNull SpaceXHttp<S> spaceXHttp) {
        this.spaceXHttp = spaceXHttp;
        this.cacheMap = new ConcurrentHashMap<>();
    }

    /**
     * Get a response based on an enum object.
     *
     * @param responseEnumType The response type.
     * @param <T>              The returned response type.
     * @return The completable future with the typed response.
     */
    @SuppressWarnings("unchecked")
    public final <T extends AbstractJsonResponse> CompletableFuture<T> getResponse(@NotNull S responseEnumType) {
        try {
            if (!this.cacheMap.containsKey(responseEnumType)) {

                return (CompletableFuture<T>) responseEnumType.makeRequest().whenComplete((v, e) -> {
                    if (v != null && e == null) {
                        this.cacheMap.put(responseEnumType, spaceXHttp.getJsonResponseCacheFactory().buildCache(v));
                    } else {
                        e.printStackTrace();
                    }
                });

            } else {
                var data = this.cacheMap.get(responseEnumType);
                if ((System.currentTimeMillis() - data.getCreationTime()) / 1000 <= CACHE_TIME_SECONDS) {

                    return (CompletableFuture<T>) CompletableFuture.completedFuture(this.cacheMap.get(responseEnumType).getJsonResponse());
                } else { // yeah duplicated code.

                    return (CompletableFuture<T>) responseEnumType.makeRequest().whenComplete((v, e) -> {
                        if (v != null && e == null) {
                            this.cacheMap.put(responseEnumType, spaceXHttp.getJsonResponseCacheFactory().buildCache(v));
                        } else {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * Get the map that holds cached data.
     *
     * @return The cached data.
     */
    @NotNull
    public final Map<S, JsonResponseCache<?>> getCacheMap() {
        return cacheMap;
    }

    /**
     * Get spacexHttp class.
     *
     * @return The spacexHttp object.
     */
    @NotNull
    public final SpaceXHttp<S> getSpaceXHttp() {
        return spaceXHttp;
    }
}
