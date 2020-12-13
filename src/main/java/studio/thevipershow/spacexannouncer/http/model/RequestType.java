package studio.thevipershow.spacexannouncer.http.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import studio.thevipershow.spacexannouncer.http.model.data.AbstractJsonResponse;
import studio.thevipershow.spacexannouncer.http.model.data.NextLaunchResponse;
import studio.thevipershow.spacexannouncer.http.model.data.NextRocketResponse;
import studio.thevipershow.spacexannouncer.http.model.data.ResponseClassHolder;
import studio.thevipershow.spacexannouncer.http.model.data.JsonResponseProvider;

@SuppressWarnings("unchecked")
public enum RequestType implements ResponseClassHolder, JsonResponseProvider {

    LAUNCH(NextLaunchResponse.class) {
        @Override
        public final CompletableFuture<NextLaunchResponse> makeRequest() {
            return getResponse(nextLaunchRequest())
                    .thenApply(rsp -> {
                        final int statusCode = rsp.statusCode();
                        if (statusCode != 200) {
                            throw new RuntimeException("The launch request did not return properly, STATUS CODE: " + statusCode);
                        }
                        var launchResponse = new NextLaunchResponse(rsp.body());
                        launchResponse.tryAssignValues();
                        return launchResponse;
                    });
        }
    },
    ROCKET(NextRocketResponse.class) {
        @Override
        public final CompletableFuture<NextRocketResponse> makeRequest() {
            return LAUNCH.makeRequest()
                    .thenCompose(rsp -> getResponse(buildGetRequest(BASE_URL + "/rockets/" + ((NextLaunchResponse) rsp).getRocketUID())).thenApply(rst -> {
                        var rocketResponse = new NextRocketResponse(rst.body());
                        if (rst.statusCode() != 200) {
                            throw new RuntimeException("The rocket request did not return properly, STATUS CODE: " + rst.statusCode());
                        }
                        rocketResponse.tryAssignValues();
                        return rocketResponse;
                    }));
        }
    };

    private static final String BASE_URL = "https://api.spacexdata.com/v4";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofMillis(+1000))
            .build();

    public static HttpRequest buildGetRequest(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "SpaceX-Announcer Spigot")
                .header("Content-Type", "application/json")
                .build();
    }

    public static HttpRequest nextLaunchRequest() {
        return buildGetRequest(BASE_URL + "/launches/next");
    }

    public static CompletableFuture<HttpResponse<String>> getResponse(HttpRequest httpRequest) {
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    RequestType(Class<? extends AbstractJsonResponse> abstractJsonResponseType) {
        this.abstractJsonResponseType = abstractJsonResponseType;
    }

    private final Class<? extends AbstractJsonResponse> abstractJsonResponseType;

    @Override
    public final Class<? extends AbstractJsonResponse> getAbstractJsonResponseType() {
        return this.abstractJsonResponseType;
    }
}
