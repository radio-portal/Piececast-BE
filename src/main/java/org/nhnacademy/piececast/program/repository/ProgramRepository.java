package org.nhnacademy.piececast.program.repository;

import org.nhnacademy.piececast.program.domain.Program;
import org.nhnacademy.piececast.program.projection.ProgramWithLatestEpisodeAndPiecesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query("""
    SELECT p.programId AS programId, p.program AS programName, p.station AS station,
           e.episodeId AS episodeId, e.date AS latestEpisodeDate,
           pe.pieceId AS pieceId, pe.title AS pieceTitle
    FROM Program p
    JOIN Episode e ON e.program = p
    LEFT JOIN Piece pe ON pe.episode = e
    WHERE NOT EXISTS (
        SELECT 1 FROM Episode e2
        WHERE e2.program = p AND e2.date > e.date
    )
    ORDER BY p.programId, pe.pieceId
""")

    List<ProgramWithLatestEpisodeAndPiecesProjection> findProgramsWithLatestEpisodeAndPieces();
}
