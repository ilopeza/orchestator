package com.spotify.orchestator.model.response;

import com.spotify.orchestator.model.response.detection.CurrentPlayingItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LyricsResponse {
    private String lyrics;
    private CurrentPlayingItem currentPlayingItem;
}
