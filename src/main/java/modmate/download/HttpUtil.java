package modmate.download;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import modmate.log.Log;

/**
 * Utility class for handling HTTP requests and responses.
 */
public class HttpUtil {

    /**
     * Retrieves data from the specified URL using HttpClient.
     *
     * @param uri the URL to retrieve data from
     * @return a CompletableFuture containing the response data as a String
     */
    public static CompletableFuture<String> retrieveDataFromURL(URI uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        String responseData = response.body();
                        if (responseData.isEmpty()) {
                            throw new IllegalArgumentException("JSON response is empty or null");
                        }
                        return responseData;
                    } else {
                        Log.saveLog("\n[HttpUtil]   Request failed. Response Code: " + response.statusCode());
                        throw new RuntimeException("Request failed. Response Code: " + response.statusCode());
                    }
                })
                .exceptionally(ex -> {
                    Log.saveLog("\n[HttpUtil]   Exception occurred: " + ex.getMessage());
                    throw new RuntimeException(ex);
                });
    }
}
