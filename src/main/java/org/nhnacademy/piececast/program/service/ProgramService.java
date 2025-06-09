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

    public List<ProgramCardDto> getProgramsByDate(LocalDate date) {
        List<ProgramWithLatestEpisodeAndPiecesProjection> raw = programRepository.findProgramsByEpisodeDate(date);

        Map<Long, ProgramCardDto> grouped = new LinkedHashMap<>();

        for (var row : raw) {
            grouped.computeIfAbsent(row.getProgramId(), id -> new ProgramCardDto(
                    row.getProgramId(),
                    row.getProgramName(),
                    row.getStation(),
                    row.getEpisodeId(),
                    row.getLatestEpisodeDate(),
                    new ArrayList<>()
            )).getPieces().add(
                    row.getPieceId() != null ? new PieceDto(row.getPieceId(), row.getPieceTitle()) : null
            );
        }

        // null 조각 제거
        grouped.values().forEach(card -> card.getPieces().removeIf(Objects::isNull));

        return new ArrayList<>(grouped.values());
    }

}
