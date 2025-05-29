package org.nhnacademy.piececast.music.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaylistResponse {
    private Long programId;
    private Long episodeId;
    private List<MusicWithSpotify> playlist;

    @Getter
    @AllArgsConstructor
    public static class MusicWithSpotify {
        private Long musicId;
        private String title;
        private String artist;
        private SpotifyTrackInfo spotify;
    }
}