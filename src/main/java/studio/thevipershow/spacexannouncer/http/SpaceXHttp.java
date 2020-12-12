package studio.thevipershow.spacexannouncer.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import studio.thevipershow.spacexannouncer.http.model.NextLaunchResponse;

public class SpaceXHttp {

    private static final String BASE_URL = "https://api.spacexdata.com/v4";
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofMillis(+1000))
            .build();

    public HttpRequest buildGetRequest(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "SpaceX-Announcer Spigot")
                .header("Content-Type", "application/json")
                .build();
    }

    public HttpRequest nextLaunchRequest() {
        return buildGetRequest(BASE_URL + "/launches/next");
    }

    public CompletableFuture<HttpResponse<String>> getResponse(HttpRequest httpRequest) {
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    public CompletableFuture<NextLaunchResponse> getNextLaunch() {
        return getResponse(nextLaunchRequest()).thenApply(response -> {
            final int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new RuntimeException("The request did not return properly, exit code " + statusCode);
            }
            var launchResponse = new NextLaunchResponse(response.body());
            launchResponse.tryAssignValues();
            return launchResponse;
        });
    }
}
