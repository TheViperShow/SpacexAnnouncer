package studio.thevipershow.spacexannouncer.http.model.cache;


import org.jetbrains.annotations.NotNull;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;

public interface JsonResponseCache<T extends AbstractJsonResponse> {

    long getCreationTime();

    @NotNull
    T getJsonResponse();
}
