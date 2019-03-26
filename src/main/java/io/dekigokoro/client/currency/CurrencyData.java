package io.dekigokoro.client.currency;

import java.math.BigDecimal;

/**
 * @author amy
 * @since 3/26/19.
 */
public final class CurrencyData {
    private final String player;
    private final String subkey;
    private final String application;
    private final BigDecimal balance;
    
    public CurrencyData(final String player, final String subkey, final String application, final BigDecimal balance) {
        this.player = player;
        this.subkey = subkey;
        this.application = application;
        this.balance = balance;
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
    
    public BigDecimal getBalance() {
        return balance;
    }
}
