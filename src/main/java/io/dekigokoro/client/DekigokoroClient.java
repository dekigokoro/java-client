package io.dekigokoro.client;

import io.dekigokoro.client.Routes.Route;
import io.dekigokoro.client.currency.CurrencyHandler;
import io.dekigokoro.client.levels.LevelsHandler;
import io.dekigokoro.client.userdata.UserDataHandler;

import javax.annotation.Nonnull;
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
    static DekigokoroClient create(@Nonnull final String token) {
        return new DekigokoroClientImpl(token);
    }
    
    /**
     * @return The dekigokoro application token used by this client instance.
     */
    @Nonnull
    String getToken();
    
    @Nonnull
    HttpClient getClient();
    
    /**
     * @return The handler for all currency-related things.
     */
    @Nonnull
    CurrencyHandler getCurrencyHandler();
    
    /**
     * @return The handler for all levels-related things.
     */
    @Nonnull
    LevelsHandler getLevelsHandler();
    
    /**
     * @return The handler for all user-data-related things.
     */
    @Nonnull
    UserDataHandler getUserDataHandler();
    
    @Nonnull
    default String getApiBase() {
        return "/api/v1";
    }
    
    @Nonnull
    default String getApiHost() {
        return "https://dekigokoro.io";
    }
    
    @Nonnull
    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    default HttpRequest.Builder getNewRequest(@Nonnull final String route) {
        return HttpRequest.newBuilder(URI.create(getApiHost() + getApiBase() + route))
                .header("Authorization", getToken())
                .header("Content-Type", "application/json");
    }
    
    @Nonnull
    default CompletableFuture<HttpResponse<String>> sendRequest(@Nonnull final HttpRequest request) {
        return getClient().sendAsync(request, BodyHandlers.ofString());
    }
    
    @Nonnull
    default CompletableFuture<HttpResponse<String>> get(@Nonnull final Route route) {
        return sendRequest(getNewRequest(route.getRoute()).GET().build());
    }
    
    @Nonnull
    default CompletableFuture<HttpResponse<String>> put(@Nonnull final Route route, @Nonnull final String body) {
        return sendRequest(getNewRequest(route.getRoute()).PUT(BodyPublishers.ofString(body)).build());
    }
}
