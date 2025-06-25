package org.nhnacademy.piececast.piece.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PieceFullDetailDto {
    private Long pieceId;
    private String title;
    private String summary;
    private List<String> tags;
    private List<MusicDto> musics;
    private List<StoryDto> stories;
}
