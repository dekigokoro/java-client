package io.dekigokoro.client.userdata;

import io.dekigokoro.client.DekigokoroClient;
import io.dekigokoro.client.Routes;
import io.dekigokoro.client.Utils;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 4/1/19.
 */
public final class UserDataHandlerImpl implements UserDataHandler {
    private final DekigokoroClient client;
    
    public UserDataHandlerImpl(@Nonnull final DekigokoroClient client) {
        this.client = client;
    }
    
    @Nonnull
    @Override
    public DekigokoroClient getClient() {
        return client;
    }
    
    @Override
    public CompletableFuture<Optional<UserData>> getData(@Nonnull final String player) {
        return client.get(Routes.USER_DATA_GET_PLAYER.param("player", player))
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, UserData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Override
    public CompletableFuture<Optional<UserData>> putData(@Nonnull final String player, @Nonnull final Map<String, Object> data) {
        return client.put(Routes.USER_DATA_SET_PLAYER.param("player", player),
                Utils.mapToObjectNode(data).toString())
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, UserData.class))
                .thenApply(Optional::ofNullable);
    }
}
