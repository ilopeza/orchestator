package com.spotify.orchestator.model.response.musicinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicInfoResponse {
    private int status;
    private String lyrics;
}
