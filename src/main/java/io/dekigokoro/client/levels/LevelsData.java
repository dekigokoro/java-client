package io.dekigokoro.client.levels;

import java.math.BigDecimal;

/**
 * @author amy
 * @since 3/26/19.
 */
public class LevelsData {
    private final String player;
    private final String subkey;
    private final String application;
    private final BigDecimal exp;
    
    public LevelsData(final String player, final String subkey, final String application, final BigDecimal exp) {
        this.player = player;
        this.subkey = subkey;
        this.application = application;
        this.exp = exp;
    }
    
    public String getPlayer() {
        return player;
    }
    
    public String getSubkey() {
        return subkey;
    }
    
    public String getApplication() {
        return application;
    }
    
    public BigDecimal getExp() {
        return exp;
    }
}
