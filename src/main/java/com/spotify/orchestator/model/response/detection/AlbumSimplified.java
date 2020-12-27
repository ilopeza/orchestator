package com.spotify.orchestator.model.response.detection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * To model a simplified view of an album
 *
 * @see //developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified
 */
@Data
public class AlbumSimplified {
    @JsonProperty("artists")
    private List<ArtistSimplified> artists; //	array of simplified artist objects	The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the album.
    @JsonProperty("id")
    private String id; //The Spotify ID for the album.
    @JsonProperty("name")
    private String name; //The name of the album. In case of an album takedown, the value may be an empty string.
    @JsonProperty("release_date")
    private String releaseDate; //The date the album was first released, for example 1981. Depending on the precision, it might be shown as 1981-12 or 1981-12-15.
    @JsonProperty("type")
    private ObjectTypeEnum type;    //The object type: “album”
    @JsonProperty("uri")
    private String uri;//The Spotify URI for the album.
}
