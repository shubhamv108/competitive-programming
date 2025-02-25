package code.shubham.api;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

// Copy-Paste this class
class HttpClientDirector {
    private static final Gson GSON = new Gson();

    public static <Response> Response invoke(final String url, final Class<Response> clazz) {
        final String finalUrl = url.replace(" ", "%20");

        final StringBuilder responseBuilder = new StringBuilder();
        try {
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(finalUrl).openConnection();
            connection.getResponseCode();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String l;
                while ((l = bufferedReader.readLine()) != null)
                    responseBuilder.append(l);
            }
            connection.disconnect();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return GSON.fromJson(responseBuilder.toString(), clazz);
    }

    public static <Response> Response invokeSync(final String url, final Class<Response> clazz) {
        final String finalUrl = url.replace(" ", "%20");
        try {
            HttpResponse<String> response = java.net.http.HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(new URI(finalUrl)).GET().build(), BodyHandlers.ofString());
            return GSON.fromJson(response.body(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static <Response> CompletableFuture<Response> invokeAsync(final String url, final Class<Response> clazz) {
        final String finalUrl = url.replace(" ", "%20");
        try {
            return java.net.http.HttpClient.newHttpClient()
                    .sendAsync(HttpRequest.newBuilder(new URI(finalUrl)).GET().build(), BodyHandlers.ofString())
                    .thenApplyAsync(stringHttpResponse -> GSON.fromJson(stringHttpResponse.body(), clazz));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
