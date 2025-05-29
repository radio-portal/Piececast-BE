package org.nhnacademy.piececast.music.controller;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.music.domain.Music;
import org.nhnacademy.piececast.music.dto.PlaylistResponse;
import org.nhnacademy.piececast.music.dto.SpotifyTrackInfo;
import org.nhnacademy.piececast.music.service.EpisodeMusicService;
import org.nhnacademy.piececast.music.service.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/programs")
public class PlaylistController {

    private final EpisodeMusicService episodeMusicService;
    private final SpotifyService spotifyService;

    @GetMapping("/{programId}/episodes/{episodeId}/playlist")
    public ResponseEntity<PlaylistResponse> getPlaylist(
            @PathVariable Long programId,
            @PathVariable Long episodeId) {

        List<Music> musics = episodeMusicService.getMusicsByEpisodeId(episodeId);
        List<PlaylistResponse.MusicWithSpotify> playlist = new ArrayList<>();

        for (Music music : musics) {
            SpotifyTrackInfo trackInfo = spotifyService.searchTrack(music.getTitle(), music.getArtist());
            playlist.add(new PlaylistResponse.MusicWithSpotify(
                    music.getMusicId(),
                    music.getTitle(),
                    music.getArtist(),
                    trackInfo
            ));
        }

        return ResponseEntity.ok(new PlaylistResponse(programId, episodeId, playlist));
    }
}