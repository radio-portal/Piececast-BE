package org.nhnacademy.piececast.music.controller;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.music.dto.PlaylistResponse;
import org.nhnacademy.piececast.music.service.EpisodeMusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/episodes")
public class PlaylistController {

    private final EpisodeMusicService episodeMusicService;

    @GetMapping("/{episodeId}/playlist")
    public ResponseEntity<PlaylistResponse> getPlaylist(@PathVariable Long episodeId) {
        List<PlaylistResponse.MusicSimple> playlist = episodeMusicService.getMusicListByEpisodeId(episodeId);
        return ResponseEntity.ok(new PlaylistResponse(episodeId, playlist));
    }
}
