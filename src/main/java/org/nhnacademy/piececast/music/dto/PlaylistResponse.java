package org.nhnacademy.piececast.music.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaylistResponse {
    private Long episodeId;
    private List<MusicSimple> playlist;

    @Getter
    @AllArgsConstructor
    public static class MusicSimple {
        private Long musicId;
        private String title;
        private String artist;
    }
}
