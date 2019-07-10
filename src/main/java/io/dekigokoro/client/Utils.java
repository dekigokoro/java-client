package io.dekigokoro.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.dekigokoro.client.exceptions.NotAuthorizedException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author amy
 * @since 3/26/19.
 */
public final class Utils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final JsonNodeFactory FACTORY = JsonNodeFactory.withExactBigDecimals(true);
    
    private Utils() {
    }
    
    @Nullable
    public static <T> T objectFromDekigokoroResponse(@Nonnull final HttpResponse<String> res, @Nonnull final Class<T> type) {
        switch(res.statusCode()) {
            case 403: {
                throw new NotAuthorizedException(res.body());
            }
            case 404: {
                return null;
            }
            case 200: {
                try {
                    return MAPPER.readValue(res.body(), type);
                } catch(final IOException e) {
                    throw new IllegalStateException(e);
                }
            }
            default: {
                throw new IllegalStateException("Unknown HTTP status code: " + res.statusCode());
            }
        }
    }
    
    @Nonnull
    public static <T> List<T> arrayFromDekigokoroResponse(@Nonnull final HttpResponse<String> res) {
        switch(res.statusCode()) {
            case 403: {
                throw new NotAuthorizedException(res.body());
            }
            case 404: {
                return Collections.emptyList();
            }
            case 200: {
                try {
                    return MAPPER.readValue(res.body(), new TypeReference<List<T>>() {
                    });
                } catch(final IOException e) {
                    throw new IllegalStateException(e);
                }
            }
            default: {
                throw new IllegalStateException("Unknown HTTP status code: " + res.statusCode());
            }
        }
    }
    
    @Nonnull
    public static ObjectNode objectNode() {
        return FACTORY.objectNode();
    }
    
    @Nonnull
    public static ObjectNode mapToObjectNode(@Nonnull final Map<String, Object> map) {
        return MAPPER.valueToTree(map);
    }
    
    @Nonnull
    public static ObjectNode stringToObjectNode(@Nonnull final String data) {
        return MAPPER.valueToTree(data);
    }
}
