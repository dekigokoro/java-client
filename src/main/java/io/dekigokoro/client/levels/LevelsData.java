package io.dekigokoro.client.levels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author amy
 * @since 3/26/19.
 */
public final class LevelsData {
    @JsonProperty("player_id")
    private String player;
    @JsonProperty("subkey")
    private String subkey;
    @JsonProperty("app_id")
    private String application;
    @JsonProperty("balance")
    private BigDecimal exp;
    
    LevelsData() {
    }
    
    public String getPlayer() {
        return player;
    }
    
    void setPlayer(final String player) {
        this.player = player;
    }
    
    public String getSubkey() {
        return subkey;
    }
    
    void setSubkey(final String subkey) {
        this.subkey = subkey;
    }
    
    public String getApplication() {
        return application;
    }
    
    void setApplication(final String application) {
        this.application = application;
    }
    
    public BigDecimal getExp() {
        return exp;
    }
    
    void setExp(final BigDecimal exp) {
        this.exp = exp;
    }
}
