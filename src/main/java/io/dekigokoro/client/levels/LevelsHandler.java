package io.dekigokoro.client.levels;

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
public interface LevelsHandler {
    @Nonnull
    DekigokoroClient getClient();
    
    /**
     * Get the exp for the given player.
     *
     * @param player The id of the player to get the exp for.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<LevelsData>> getExp(@Nonnull String player);
    
    /**
     * Get the exp for the given player.
     *
     * @param player The id of the player to get the exp for.
     * @param subkey The subkey to get data from.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<LevelsData>> getExp(@Nonnull String player, @Nonnull String subkey);
    
    /**
     * Set the exp for the given player.
     *
     * @param player The id of the player to set the exp for.
     * @param exp    The new exp to set.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<LevelsData>> putExp(@Nonnull String player, @Nonnull BigDecimal exp);
    
    /**
     * Set the exp for the given player in the given subkey.
     *
     * @param player The id of the player to set the exp for.
     * @param subkey The subkey to set the exp under.
     * @param exp    The new exp to set.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    @Nonnull
    CompletableFuture<Optional<LevelsData>> putExp(@Nonnull String player, @Nonnull String subkey, @Nonnull BigDecimal exp);
    
    /**
     * Increment the given player's exp by the given amount.
     *
     * @param player    The id of the player to increment the exp for.
     * @param increment The amount to increment by.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    CompletableFuture<Optional<LevelsData>> incrementExp(@Nonnull String player, @Nonnull BigDecimal increment);
    
    /**
     * Increment the given player's exp by the given amount in the given
     * subkey.
     *
     * @param player    The id of the player to increment the exp for.
     * @param subkey    The subkey to increment the exp under.
     * @param increment The amount to increment by.
     *
     * @return A future that completes with an optional that may contain the
     * player's data.
     */
    CompletableFuture<Optional<LevelsData>> incrementExp(@Nonnull String player, @Nonnull String subkey,
                                                         @Nonnull BigDecimal increment);
    
    /**
     * Gets the highest-to-lowest ordered currency exps.
     *
     * @return The first 100 players.
     */
    @Nonnull
    default CompletableFuture<List<LevelsData>> getRankings() {
        return getRankings(100);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency exps.
     *
     * @param limit The maximum number of players to fetch.
     *
     * @return The first {@code limit} players.
     */
    @Nonnull
    default CompletableFuture<List<LevelsData>> getRankings(@Nonnegative final int limit) {
        return getRankings(limit, 0);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency exps, after the given
     * rank.
     *
     * @param limit The maximum number of players to fetch.
     * @param after The rank after which to fetch players, ex. fetch all after
     *              the first 50.
     *
     * @return The first {@code limit} ranked players after the given rank.
     */
    @Nonnull
    CompletableFuture<List<LevelsData>> getRankings(@Nonnegative int limit, @Nonnegative int after);
    
    /**
     * Gets the highest-to-lowest ordered currency exps in the given
     * subkey.
     *
     * @param subkey The subkey to fetch from.
     *
     * @return The first 100 ranked players from the given subkey.
     */
    @Nonnull
    default CompletableFuture<List<LevelsData>> getRankings(@Nonnull final String subkey) {
        return getRankings(subkey, 100);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency exps in the given
     * subkey.
     *
     * @param subkey The subkey to fetch from.
     * @param limit  The maximum number of players to fetch.
     *
     * @return The first {@code limit} ranked players from the given subkey.
     */
    @Nonnull
    default CompletableFuture<List<LevelsData>> getRankings(@Nonnull final String subkey, @Nonnegative final int limit) {
        return getRankings(subkey, limit, 0);
    }
    
    /**
     * Gets the highest-to-lowest ordered currency exps in the given
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
    CompletableFuture<List<LevelsData>> getRankings(@Nonnull String subkey, @Nonnegative int limit, @Nonnegative int after);
}
