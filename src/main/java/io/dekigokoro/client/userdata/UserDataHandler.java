package io.dekigokoro.client.userdata;

import io.dekigokoro.client.DekigokoroClient;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 4/1/19.
 */
public interface UserDataHandler {
    @Nonnull
    DekigokoroClient getClient();
    
    CompletableFuture<Optional<UserData>> getData(@Nonnull String player);
    
    CompletableFuture<Optional<UserData>> putData(@Nonnull String player, @Nonnull Map<String, Object> data);
}
