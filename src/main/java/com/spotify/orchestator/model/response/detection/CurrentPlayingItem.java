package com.spotify.orchestator.model.response.detection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CurrentPlayingItem {
    @JsonProperty("id")
    private String id; //The Spotify ID for the episode.
    @JsonProperty("name")
    private String name; //The name of the episode.
    @JsonProperty("type")
    private ObjectTypeEnum type;//The object type: “track”.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the episode.
    @JsonProperty("album")
    private AlbumSimplified album;    //The album on which the track appears. The album object includes a link in href to full information about the album.
    @JsonProperty("artists")
    private List<ArtistSimplified> artists;

}
