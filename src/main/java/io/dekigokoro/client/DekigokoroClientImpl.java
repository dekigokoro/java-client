package io.dekigokoro.client;

import io.dekigokoro.client.currency.CurrencyHandler;
import io.dekigokoro.client.currency.CurrencyHandlerImpl;
import io.dekigokoro.client.levels.LevelsHandler;
import io.dekigokoro.client.levels.LevelsHandlerImpl;
import io.dekigokoro.client.relationships.RelationshipsHandler;
import io.dekigokoro.client.relationships.RelationshipsHandlerImpl;
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
    private final RelationshipsHandler relationshipsHandler;
    private final HttpClient client;
    
    DekigokoroClientImpl(final String token) {
        this.token = token;
        currencyHandler = new CurrencyHandlerImpl(this);
        levelsHandler = new LevelsHandlerImpl(this);
        userDataHandler = new UserDataHandlerImpl(this);
        relationshipsHandler = new RelationshipsHandlerImpl(this);
        client = HttpClient.newBuilder().build();
    }
    
    @Nonnull
    @Override
    public String getToken() {
        return token;
    }
    
    @Nonnull
    @Override
    public HttpClient getClient() {
        return client;
    }
    
    @Nonnull
    @Override
    public CurrencyHandler getCurrencyHandler() {
        return currencyHandler;
    }
    
    @Nonnull
    @Override
    public LevelsHandler getLevelsHandler() {
        return levelsHandler;
    }
    
    @Nonnull
    @Override
    public UserDataHandler getUserDataHandler() {
        return userDataHandler;
    }
    
    @Nonnull
    @Override
    public RelationshipsHandler getRelationshipsHandler() {
        return relationshipsHandler;
    }
}
