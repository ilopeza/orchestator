package com.spotify.orchestator.controller;

import com.spotify.orchestator.model.response.LyricsResponse;
import com.spotify.orchestator.service.LyricsService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    private LyricsService lyricsService;

    public PlayerController(LyricsService lyricsService) {
        this.lyricsService = lyricsService;
    }

    @GetMapping("/{userId}/lyrics/find")
    public LyricsResponse findLyrics(@PathVariable("userId") String userId) {
        log.info("Calling /api/vi/{userid}/lyrics/find for user {}", userId);
        val lyricsResponse = lyricsService.findLyrics(userId);
        log.info("Returning lyrics: {}", lyricsResponse);
        return lyricsResponse;
    }

}
