package io.dekigokoro.client.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author amy
 * @since 3/26/19.
 */
public final class CurrencyData {
    @JsonProperty("player_id")
    private String player;
    @JsonProperty("subkey")
    private String subkey;
    @JsonProperty("app_id")
    private String application;
    @JsonProperty("balance")
    private BigDecimal balance;
    
    CurrencyData() {
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
    
    void setPlayer(final String player) {
        this.player = player;
    }
    
    void setSubkey(final String subkey) {
        this.subkey = subkey;
    }
    
    void setApplication(final String application) {
        this.application = application;
    }
    
    void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }
}
