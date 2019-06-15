package io.dekigokoro.client.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author amy
 * @since 6/14/19.
 */
public final class RelationshipData {
    @JsonProperty("app_id")
    private String application;
    @JsonProperty("player_id")
    private String player;
    @JsonProperty("target_id")
    private String target;
    @JsonProperty("type")
    private String type;
    
    public String getApplication() {
        return application;
    }
    
    void setApplication(final String application) {
        this.application = application;
    }
    
    public String getPlayer() {
        return player;
    }
    
    void setPlayer(final String player) {
        this.player = player;
    }
    
    public String getTarget() {
        return target;
    }
    
    void setTarget(final String target) {
        this.target = target;
    }
    
    public String getType() {
        return type;
    }
    
    void setType(final String type) {
        this.type = type;
    }
}
