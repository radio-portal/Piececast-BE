package org.nhnacademy.piececast.music.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "회차별 음악 재생목록 조회",
            description = "해당 episodeId에 포함된 음악 목록을 DB에서 꺼내 순서대로 반환합니다."
    )
    @GetMapping("/{episodeId}/playlist")
    public ResponseEntity<PlaylistResponse> getPlaylist(@PathVariable Long episodeId) {
        List<PlaylistResponse.MusicSimple> playlist = episodeMusicService.getMusicListByEpisodeId(episodeId);
        return ResponseEntity.ok(new PlaylistResponse(episodeId, playlist));
    }
}
