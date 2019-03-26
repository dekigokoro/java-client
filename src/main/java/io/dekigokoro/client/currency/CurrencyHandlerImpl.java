package io.dekigokoro.client.currency;

import io.dekigokoro.client.DekigokoroClient;
import io.dekigokoro.client.Routes;
import io.dekigokoro.client.Utils;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 3/26/19.
 */
public class CurrencyHandlerImpl implements CurrencyHandler {
    private final DekigokoroClient client;
    
    public CurrencyHandlerImpl(@Nonnull final DekigokoroClient client) {
        this.client = client;
    }
    
    @Nonnull
    @Override
    public DekigokoroClient getClient() {
        return client;
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<CurrencyData>> getBalance(@Nonnull final String player) {
        return client.get(Routes.CURRENCY_GET_PLAYER.param("player", player))
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, CurrencyData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<CurrencyData>> getBalance(@Nonnull final String player, @Nonnull final String subkey) {
        return client.get(Routes.CURRENCY_SUBKEY_GET_PLAYER.param("player", player).param("subkey", subkey))
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, CurrencyData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<CurrencyData>> putBalance(@Nonnull final String player,
                                                                @Nonnull final BigDecimal balance) {
        return client.put(Routes.CURRENCY_SET_PLAYER.param("player", player),
                Utils.objectNode().put("balance", balance).toString())
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, CurrencyData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<CurrencyData>> putBalance(@Nonnull final String player, @Nonnull final String subkey,
                                                                @Nonnull final BigDecimal balance) {
        return client.put(Routes.CURRENCY_SUBKEY_SET_PLAYER.param("player", player).param("subkey", subkey),
                Utils.objectNode().put("balance", balance).toString())
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, CurrencyData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<List<CurrencyData>> getRankings(final int limit, final int after) {
        return client.get(Routes.CURRENCY_GET_RANKINGS
                .startQuery()
                .queryParam("limit", String.valueOf(limit))
                .queryParam("after", String.valueOf(after))
                .endQuery())
                .thenApply(Utils::arrayFromDekigokoroResponse);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<List<CurrencyData>> getRankings(@Nonnull final String subkey, final int limit, final int after) {
        return client.get(Routes.CURRENCY_SUBKEY_GET_RANKINGS
                .param("subkey", subkey)
                .startQuery()
                .queryParam("limit", String.valueOf(limit))
                .queryParam("after", String.valueOf(after))
                .endQuery())
                .thenApply(Utils::arrayFromDekigokoroResponse);
    }
}
