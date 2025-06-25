package org.nhnacademy.piececast.program.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.program.dto.PieceDto;
import org.nhnacademy.piececast.program.dto.ProgramCardDto;
import org.nhnacademy.piececast.program.projection.ProgramWithLatestEpisodeAndPiecesProjection;
import org.nhnacademy.piececast.program.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<ProgramCardDto> getLatestEpisodeCards() {
        return groupPrograms(programRepository.findProgramsWithLatestEpisodeAndPieces());
    }

    public List<ProgramCardDto> getProgramsByDate(LocalDate date) {
        return groupPrograms(programRepository.findProgramsByEpisodeDate(date));
    }

    private List<ProgramCardDto> groupPrograms(List<ProgramWithLatestEpisodeAndPiecesProjection> rows) {
        Map<Long, ProgramCardDto> grouped = new LinkedHashMap<>();

        for (var row : rows) {
            ProgramCardDto dto = grouped.computeIfAbsent(row.getProgramId(), id ->
                    new ProgramCardDto(
                            row.getProgramId(),
                            row.getProgramName(),
                            row.getStation(),
                            row.getEpisodeId(),
                            row.getLatestEpisodeDate(),
                            row.getThumbnailUrl(),
                            new ArrayList<>()
                    )
            );

            if (row.getPieceId() != null) {
                dto.getPieces().add(new PieceDto(row.getPieceId(), row.getPieceTitle()));
            }
        }

        return new ArrayList<>(grouped.values());
    }
}
