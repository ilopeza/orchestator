package com.spotify.orchestator.model.response.detection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * To model a simplified artist
 *
 * @see:https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-simplified
 */
@Data
public class ArtistSimplified {
    @JsonProperty("id")
    private String id; //The Spotify ID for the artist.
    @JsonProperty("name")
    private String name; //The name of the artist.
    @JsonProperty("type")
    private ObjectTypeEnum type; //The object type: "artist"
    @JsonProperty("uri")
    private String uri;   //The Spotify URI for the artist.
}
