package io.dekigokoro.client.currency;

import io.dekigokoro.client.DekigokoroClient;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 3/26/19.
 */
public interface CurrencyHandler {
    @Nonnull
    DekigokoroClient getClient();
    
    /**
     * Get the balance for the given player.
     *
     * @param player The id of the player to get the balance for.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<CurrencyData>> getBalance(@Nonnull String player);
    
    /**
     * Get the balance for the given player.
     *
     * @param player The id of the player to get the balance for.
     * @param subkey The subkey to get data from.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<CurrencyData>> getBalance(@Nonnull String player, @Nonnull String subkey);
    
    /**
     * Set the balance for the given player.
     *
     * @param player  The id of the player to set the balance for.
     * @param balance The new balance to set.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<CurrencyData>> putBalance(@Nonnull String player, @Nonnull BigDecimal balance);
    
    /**
     * Set the balance for the given player in the given subkey.
     *
     * @param player  The id of the player to set the balance for.
     * @param subkey  The subkey to set the balance under.
     * @param balance The new balance to set.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<CurrencyData>> putBalance(@Nonnull String player, @Nonnull String subkey,
                                                         @Nonnull BigDecimal balance);
    
    /**
     * Gets the highest-to-lowest ordered currency balances.
     *
     * @return The first 100 players.
     */
    @Nonnull
    default CompletableFuture<List<CurrencyData>> getRankings() {
        return getRankings(100);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency balances.
     *
     * @param limit The maximum number of players to fetch.
     *
     * @return The first {@code limit} players.
     */
    @Nonnull
    default CompletableFuture<List<CurrencyData>> getRankings(@Nonnegative final int limit) {
        return getRankings(limit, 0);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency balances, after the given
     * rank.
     *
     * @param limit The maximum number of players to fetch.
     * @param after The rank after which to fetch players, ex. fetch all after
     *              the first 50.
     *
     * @return The first {@code limit} ranked players after the given rank.
     */
    @Nonnull
    CompletableFuture<List<CurrencyData>> getRankings(@Nonnegative int limit, @Nonnegative int after);
    
    /**
     * Gets the highest-to-lowest ordered currency balances in the given
     * subkey.
     *
     * @param subkey The subkey to fetch from.
     *
     * @return The first 100 ranked players from the given subkey.
     */
    @Nonnull
    default CompletableFuture<List<CurrencyData>> getRankings(@Nonnull final String subkey) {
        return getRankings(subkey, 100);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency balances in the given
     * subkey.
     *
     * @param subkey The subkey to fetch from.
     * @param limit  The maximum number of players to fetch.
     *
     * @return The first {@code limit} ranked players from the given subkey.
     */
    @Nonnull
    default CompletableFuture<List<CurrencyData>> getRankings(@Nonnull final String subkey, @Nonnegative final int limit) {
        return getRankings(subkey, limit, 0);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency balances in the given
     * subkey.
     *
     * @param subkey The subkey to fetch from.
     * @param limit  The maximum number of players to fetch.
     * @param after  The rank after which to fetch players, ex. fetch all after
     *               the first 50.
     *
     * @return The first {@code limit} players after the given rank.
     */
    @Nonnull
    CompletableFuture<List<CurrencyData>> getRankings(@Nonnull String subkey, @Nonnegative int limit, @Nonnegative int after);
}
