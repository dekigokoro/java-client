package io.dekigokoro.client.userdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author amy
 * @since 4/1/19.
 */
public final class UserData {
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("player_id")
    private String playerId;
    @JsonProperty("data")
    private ObjectNode data;
    
    UserData() {
    }
    
    public String getAppId() {
        return appId;
    }
    
    void setAppId(final String appId) {
        this.appId = appId;
    }
    
    public String getPlayerId() {
        return playerId;
    }
    
    void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }
    
    public ObjectNode getData() {
        return data;
    }
    
    void setData(final ObjectNode data) {
        this.data = data;
    }
}
