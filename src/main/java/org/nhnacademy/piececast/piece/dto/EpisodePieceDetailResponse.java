package org.nhnacademy.piececast.piece.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class EpisodePieceDetailResponse {

    private ProgramInfo program;
    private EpisodeInfo episode;

    @Data
    @AllArgsConstructor
    public static class ProgramInfo {
        private Long id;
        private String name;
        private String thumbnailUrl;
    }

    @Data
    @AllArgsConstructor
    public static class EpisodeInfo {
        private Long episodeId;
        private LocalDate date;
        private List<PieceDetail> pieces;
    }

    @Data
    @AllArgsConstructor
    public static class PieceDetail {
        private Long pieceId;
        private String title;
        private String summary;
        private String mp3Path;
        private List<String> tags;
        private List<MusicDto> musics;
        private List<StoryDto> stories;
    }
}
