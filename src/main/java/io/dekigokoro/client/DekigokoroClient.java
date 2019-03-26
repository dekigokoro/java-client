package io.dekigokoro.client;

import io.dekigokoro.client.Routes.Route;
import io.dekigokoro.client.currency.CurrencyHandler;
import io.dekigokoro.client.levels.LevelsHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 3/26/19.
 */
public interface DekigokoroClient {
    /**
     * @return The dekigokoro application token used by this client instance.
     */
    String getToken();
    
    HttpClient getClient();
    
    /**
     * @return The handler for all currency-related things.
     */
    CurrencyHandler getCurrencyHandler();
    
    /**
     * @return The handler for all levels-related things.
     */
    LevelsHandler getLevelsHandler();
    
    default String getApiBase() {
        return "/api/v1";
    }
    
    default String getApiHost() {
        return "https://dekigokoro.io";
    }
    
    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    default HttpRequest.Builder getNewRequest(final String route) {
        return HttpRequest.newBuilder(URI.create(getApiHost() + getApiBase() + route))
                .header("Authorization", getToken())
                .header("Content-Type", "application/json");
    }
    
    default CompletableFuture<HttpResponse<String>> sendRequest(final HttpRequest request) {
        return getClient().sendAsync(request, BodyHandlers.ofString());
    }
    
    default CompletableFuture<HttpResponse<String>> get(final Route route) {
        return sendRequest(getNewRequest(route.getRoute()).GET().build());
    }
    
    default CompletableFuture<HttpResponse<String>> put(final Route route, final String body) {
        return sendRequest(getNewRequest(route.getRoute()).PUT(BodyPublishers.ofString(body)).build());
    }
}
