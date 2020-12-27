package com.spotify.orchestator.model.response.detection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Type of object in spotify
 */
public enum ObjectTypeEnum {
    @JsonProperty("album")
    ALBUM("album"),
    @JsonProperty("artist")
    ARTIST("artist"),
    @JsonProperty("playlist")
    PLAYLIST("playlist"),
    @JsonProperty("episode")
    EPISODE("episode"),
    @JsonProperty("track")
    TRACK("track"),
    @JsonProperty("ad")
    AD("ad");

    String description;

    ObjectTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
