package io.dekigokoro.client;

import io.dekigokoro.client.currency.CurrencyHandler;
import io.dekigokoro.client.currency.CurrencyHandlerImpl;
import io.dekigokoro.client.levels.LevelsHandler;
import io.dekigokoro.client.levels.LevelsHandlerImpl;

import java.net.http.HttpClient;

/**
 * @author amy
 * @since 3/26/19.
 */
public class DekigokoroClientImpl implements DekigokoroClient {
    private final String token;
    private final CurrencyHandler currencyHandler;
    private final LevelsHandler levelsHandler;
    private final HttpClient client;
    
    public DekigokoroClientImpl(final String token) {
        this.token = token;
        currencyHandler = new CurrencyHandlerImpl(this);
        levelsHandler = new LevelsHandlerImpl(this);
        client = HttpClient.newBuilder().build();
    }
    
    public String getToken() {
        return token;
    }
    
    @Override
    public HttpClient getClient() {
        return client;
    }
    
    @Override
    public CurrencyHandler getCurrencyHandler() {
        return currencyHandler;
    }
    
    @Override
    public LevelsHandler getLevelsHandler() {
        return levelsHandler;
    }
}
