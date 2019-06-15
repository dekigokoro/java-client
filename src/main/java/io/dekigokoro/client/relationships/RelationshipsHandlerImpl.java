package io.dekigokoro.client.relationships;

import com.fasterxml.jackson.databind.JsonNode;
import io.dekigokoro.client.DekigokoroClient;
import io.dekigokoro.client.Routes;
import io.dekigokoro.client.Utils;

import javax.annotation.Nonnull;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author amy
 * @since 6/14/19.
 */
public class RelationshipsHandlerImpl implements RelationshipsHandler {
    private final DekigokoroClient client;
    
    public RelationshipsHandlerImpl(@Nonnull final DekigokoroClient client) {
        this.client = client;
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<RelationshipData>> getRelationship(@Nonnull final String player,
                                                                         @Nonnull final String target) {
        return client.get(Routes.RELATIONSHIPS_GET_PLAYER_TARGET.param("player", player).param("target", target))
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, RelationshipData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Optional<RelationshipData>> putRelationship(@Nonnull final String player,
                                                                         @Nonnull final String target,
                                                                         @Nonnull final String type) {
        return client.put(Routes.RELATIONSHIPS_SET_PLAYER_TARGET.param("player", player)
                        .param("target", target),
                Utils.objectNode().put("type", type).toString())
                .thenApply(e -> Utils.objectFromDekigokoroResponse(e, RelationshipData.class))
                .thenApply(Optional::ofNullable);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<Boolean> deleteRelationship(@Nonnull final String player,
                                                                            @Nonnull final String target) {
        return client.delete(Routes.RELATIONSHIPS_DELETE_PLAYER_TARGET.param("player", player)
                .param("target", target))
                .thenApply(HttpResponse::body)
                .thenApply(Utils::stringToObjectNode)
                .thenApply(e -> e.get("deleted"))
                .thenApply(JsonNode::asBoolean);
    }
    
    @Nonnull
    @Override
    public CompletableFuture<List<RelationshipData>> paginateRelationships(@Nonnull final String player, final int limit, final int after) {
        return client.get(Routes.RELATIONSHIPS_PAGINATE_PLAYER
                .param("player", player)
                .startQuery()
                .queryParam("limit", String.valueOf(limit))
                .queryParam("after", String.valueOf(after))
                .endQuery())
                .thenApply(Utils::arrayFromDekigokoroResponse);
    }
}
