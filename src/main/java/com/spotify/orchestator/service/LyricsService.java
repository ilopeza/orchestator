package com.spotify.orchestator.service;

import com.spotify.orchestator.exceptions.BadRequestException;
import com.spotify.orchestator.exceptions.MusicDetectionServiceException;
import com.spotify.orchestator.model.response.LyricsResponse;
import com.spotify.orchestator.model.response.detection.CurrentPlayingItem;
import com.spotify.orchestator.model.response.musicinfo.MusicInfoResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;


@Slf4j
@Data
@Service
public class LyricsService {

    public static final String NO_LYRICS_FOUND = "No lyrics found";
    private WebClient.Builder musicInfoClientBuilder;
    private WebClient.Builder detectionClientBuilder;

    public LyricsService(@Qualifier("infoFinderServiceWebClientBuilder") WebClient.Builder musicInfoClientBuilder,
                         @Qualifier("detectServiceWebClientBuilder") WebClient.Builder detectionClientBuilder) {
        this.musicInfoClientBuilder = musicInfoClientBuilder;
        this.detectionClientBuilder = detectionClientBuilder;
    }

    public LyricsResponse findLyrics(String userId) {
        if (isBlank(userId)) {
            //error
            throw new BadRequestException("Missing information to find lyrics");
        }
        log.info("Detecting current track for user {}", userId);
        val currentPlayingItem = getCurrentPlayingItem(userId);
        log.info("Searching lyrics for track {}", currentPlayingItem.getName());
        val musicInfo = getMusicInfoResponse(currentPlayingItem);

        return LyricsResponse.builder()
                .lyrics(isBlank(musicInfo.getLyrics()) ? NO_LYRICS_FOUND : musicInfo.getLyrics())
                .currentPlayingItem(currentPlayingItem)
                .build();
    }

    private MusicInfoResponse getMusicInfoResponse(CurrentPlayingItem currentPlayingItem) {
        val albumName = isNull(currentPlayingItem.getAlbum()) ? "" : currentPlayingItem.getAlbum().getName();
        val artists = currentPlayingItem.getArtists();
        val artistName = artists.get(0).getName();

        return musicInfoClientBuilder.build()
                .get()
                .uri(uriBuilder -> {
                    uriBuilder = uriBuilder.path("/api/search/lyrics/extract")
                            .queryParam("artist_name", artistName)
                            .queryParam("track_name", currentPlayingItem.getName());
                    if (isNotBlank(albumName)) {
                        uriBuilder = uriBuilder.queryParam("album_name", albumName);
                    }
                    val uri = uriBuilder.build();
                    log.info("Calling URI: {}", uri);
                    return uri;
                })
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new MusicDetectionServiceException(clientResponse)))
                .bodyToMono(MusicInfoResponse.class)
                .block();
    }

    private CurrentPlayingItem getCurrentPlayingItem(String userId) {
        return detectionClientBuilder.build()
                .get()
                .uri(uriBuilder -> {
                    val uri = uriBuilder.path("/player/{userId}/currently-playing")
                            .build(userId);
                    log.info("Calling URI: {}", uri);
                    return uri;
                })
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new MusicDetectionServiceException(clientResponse)))
                .bodyToMono(CurrentPlayingItem.class)
                .block();
    }
}
