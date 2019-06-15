package io.dekigokoro.client;

import javax.annotation.Nonnull;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author amy
 * @since 3/26/19.
 */
@SuppressWarnings({"StaticVariableOfConcreteClass", "unused"})
public final class Routes {
    // @formatter:off
    
    // Currency routes
    public static final Route CURRENCY_GET_PLAYER               = new Route("/currency/:player");
    public static final Route CURRENCY_SET_PLAYER               = new Route("/currency/:player");
    public static final Route CURRENCY_INCREMENT_PLAYER         = new Route("/currency/:player");
    public static final Route CURRENCY_GET_RANKINGS             = new Route("/currency/rankings");
    public static final Route CURRENCY_SUBKEY_GET_PLAYER        = new Route("/currency/:player/:subkey");
    public static final Route CURRENCY_SUBKEY_SET_PLAYER        = new Route("/currency/:player/:subkey");
    public static final Route CURRENCY_SUBKEY_INCREMENT_PLAYER  = new Route("/currency/:player/:subkey");
    public static final Route CURRENCY_SUBKEY_GET_RANKINGS      = new Route("/currency/rankings/:subkey");
    
    // Levels routes
    public static final Route LEVELS_GET_PLAYER                 = new Route("/levels/:player");
    public static final Route LEVELS_SET_PLAYER                 = new Route("/levels/:player");
    public static final Route LEVELS_INCREMENT_PLAYER           = new Route("/levels/:player");
    public static final Route LEVELS_GET_RANKINGS               = new Route("/levels/rankings");
    public static final Route LEVELS_SUBKEY_GET_PLAYER          = new Route("/levels/:player/:subkey");
    public static final Route LEVELS_SUBKEY_SET_PLAYER          = new Route("/levels/:player/:subkey");
    public static final Route LEVELS_SUBKEY_INCREMENT_PLAYER    = new Route("/levels/:player/:subkey");
    public static final Route LEVELS_SUBKEY_GET_RANKINGS        = new Route("/levels/rankings/:subkey");
    
    // User data routes
    public static final Route USER_DATA_GET_PLAYER              = new Route("/userdata/:player");
    public static final Route USER_DATA_SET_PLAYER              = new Route("/userdata/:player");
    
    // @formatter:on
    
    private Routes() {
    }
    
    public static final class Route {
        private final String route;
        private final boolean inQuery;
        private final Map<String, String> query = new LinkedHashMap<>();
        
        private Route(@Nonnull final String route) {
            this(route, false);
        }
        
        private Route(@Nonnull final String route, final boolean inQuery) {
            this.route = route;
            this.inQuery = inQuery;
        }
        
        @SuppressWarnings("WeakerAccess")
        public String getRoute() {
            return route;
        }
        
        public Route param(@Nonnull final String param, @Nonnull final String value) {
            return new Route(route.replace(':' + param, value));
        }
        
        public Route startQuery() {
            if(inQuery) {
                throw new IllegalStateException("Can't start querystring mode when it's already started!");
            }
            return new Route(route + '?', true);
        }
        
        public Route queryParam(@Nonnull final String key, @Nonnull final String val) {
            if(!inQuery) {
                throw new IllegalStateException("Can't add query params when not in query mode!");
            }
            query.put(key, val);
            return this;
        }
        
        public Route endQuery() {
            if(!inQuery) {
                throw new IllegalStateException("Can't end query when not in query mode!");
            }
            final List<String> queryParts = query.entrySet().stream()
                    .map(e -> URLEncoder.encode(e.getKey(), Charset.defaultCharset())
                            + '=' + URLEncoder.encode(e.getValue(), Charset.defaultCharset()))
                    .collect(Collectors.toList());
            return new Route(route + String.join("&", queryParts), false);
        }
    }
}
