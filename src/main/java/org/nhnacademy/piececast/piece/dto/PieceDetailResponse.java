package org.nhnacademy.piececast.piece.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PieceDetailResponse {
    private ProgramInfo program;
    private EpisodeInfo episode;
    private PieceDetail piece;

    @Data
    @AllArgsConstructor
    public static class ProgramInfo {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class EpisodeInfo {
        private LocalDate date;
        private List<String> pieceTitles;
        private List<MusicDto> musics;
    }

    @Data
    @AllArgsConstructor
    public static class PieceDetail {
        private String title;
        private String summary;
        private List<String> tags;
        private List<MusicDto> musics;

    }
}
