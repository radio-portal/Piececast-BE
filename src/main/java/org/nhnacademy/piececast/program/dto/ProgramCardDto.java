package org.nhnacademy.piececast.program.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ProgramCardDto {
    private Long programId;
    private String programName;
    private String station;
    private Long episodeId;
    private LocalDate latestEpisodeDate;
    private List<PieceDto> pieces;
}