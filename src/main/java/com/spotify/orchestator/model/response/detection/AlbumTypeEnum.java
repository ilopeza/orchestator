package com.spotify.orchestator.model.response.detection;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AlbumTypeEnum {
    @JsonProperty("album")
    ALBUM,
    @JsonProperty("single")
    SINGLE,
    @JsonProperty("compilation")
    COMPILATION
}
