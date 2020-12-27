package com.spotify.orchestator.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchLyricsRequest {
    private String artistName;
    private String trackName;
    private String albumName;
}
