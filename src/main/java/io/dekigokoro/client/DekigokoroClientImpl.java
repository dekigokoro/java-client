package io.dekigokoro.client;

import io.dekigokoro.client.currency.CurrencyHandler;
import io.dekigokoro.client.currency.CurrencyHandlerImpl;
import io.dekigokoro.client.levels.LevelsHandler;
import io.dekigokoro.client.levels.LevelsHandlerImpl;
import io.dekigokoro.client.userdata.UserDataHandler;
import io.dekigokoro.client.userdata.UserDataHandlerImpl;

import javax.annotation.Nonnull;
import java.net.http.HttpClient;

/**
 * @author amy
 * @since 3/26/19.
 */
public class DekigokoroClientImpl implements DekigokoroClient {
    private final String token;
    private final CurrencyHandler currencyHandler;
    private final LevelsHandler levelsHandler;
    private final UserDataHandler userDataHandler;
    private final HttpClient client;
    
    public DekigokoroClientImpl(final String token) {
        this.token = token;
        currencyHandler = new CurrencyHandlerImpl(this);
        levelsHandler = new LevelsHandlerImpl(this);
        userDataHandler = new UserDataHandlerImpl(this);
        client = HttpClient.newBuilder().build();
    }
    
    @Nonnull
    @Override
    public String getToken() {
        return token;
    }
    
    @Override
    @Nonnull
    public HttpClient getClient() {
        return client;
    }
    
    @Override
    @Nonnull
    public CurrencyHandler getCurrencyHandler() {
        return currencyHandler;
    }
    
    @Override
    @Nonnull
    public LevelsHandler getLevelsHandler() {
        return levelsHandler;
    }
    
    @Override
    @Nonnull
    public UserDataHandler getUserDataHandler() {
        return userDataHandler;
    }
}
