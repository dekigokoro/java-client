package io.dekigokoro.client.relationships;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 6/14/19.
 */
public interface RelationshipsHandler {
    /**
     * Get the relationship between a player and a target.
     *
     * @param player The player to get the relationship for.
     * @param target The target of the relationship.
     *
     * @return A future that completes with an optional that may contain the
     * relationship data.
     */
    @Nonnull
    CompletableFuture<Optional<RelationshipData>> getRelationship(@Nonnull String player, @Nonnull String target);
    
    /**
     * Set a relationship between a player and a target.
     *
     * @param player The player to set the relationship for.
     * @param target The target of the relationship.
     * @param type   The type of the relationship.
     *
     * @return A future that completes with an optional that may contain the
     * relationship data.
     */
    @Nonnull
    CompletableFuture<Optional<RelationshipData>> putRelationship(@Nonnull String player, @Nonnull String target,
                                                                  @Nonnull String type);
    
    /**
     * @param player The player to delete the relationship for.
     * @param target The target of the relationship.
     *
     * @return A future that completes with a boolean representing whether or
     * not the relationship was deleted.
     */
    @Nonnull
    CompletableFuture<Boolean> deleteRelationship(@Nonnull String player, @Nonnull String target);
    
    /**
     * Get the first page of 100 relationships for the given player.
     *
     * @param player The id of the player to get relationships for.
     *
     * @return A future that completes with a possibly-empty list of
     * relationships matching the specified criteria.
     */
    @Nonnull
    default CompletableFuture<List<RelationshipData>> paginateRelationships(@Nonnull final String player) {
        return paginateRelationships(player, 100);
    }
    
    /**
     * @param player The id of the player to get relationships for.
     * @param limit  The maximum number of relationships to fetch.
     *
     * @return A future that completes with a possibly-empty list of
     * relationships matching the specified criteria.
     */
    @Nonnull
    default CompletableFuture<List<RelationshipData>> paginateRelationships(@Nonnull final String player,
                                                                            @Nonnegative final int limit) {
        return paginateRelationships(player, limit, 0);
    }
    
    /**
     * @param player The id of the player to get relationships for.
     * @param limit  The maximum number of relationships to fetch.
     * @param after  The position to get relationships after.
     *
     * @return A future that completes with a possibly-empty list of
     * relationships matching the specified criteria.
     */
    @Nonnull
    CompletableFuture<List<RelationshipData>> paginateRelationships(@Nonnull String player, @Nonnegative int limit,
                                                                    @Nonnegative int after);
}
