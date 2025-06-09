package org.nhnacademy.piececast.program.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.program.dto.PieceDto;
import org.nhnacademy.piececast.program.dto.ProgramCardDto;
import org.nhnacademy.piececast.program.projection.ProgramWithLatestEpisodeAndPiecesProjection;
import org.nhnacademy.piececast.program.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<ProgramCardDto> getLatestEpisodeCards() {
        List<ProgramWithLatestEpisodeAndPiecesProjection> rows =
                programRepository.findProgramsWithLatestEpisodeAndPieces();

        Map<Long, ProgramCardDto> result = new LinkedHashMap<>();

        for (var row : rows) {
            result.computeIfAbsent(row.getProgramId(), id -> new ProgramCardDto(
                    row.getProgramId(),
                    row.getProgramName(),
                    row.getStation(),
                    row.getEpisodeId(),
                    row.getLatestEpisodeDate(),
                    new ArrayList<>()
            )).getPieces().add(new PieceDto(row.getPieceId(), row.getPieceTitle()));
        }

        return new ArrayList<>(result.values());
    }
}
