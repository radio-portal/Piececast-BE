package org.nhnacademy.piececast.piece.repository;

import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.MusicDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PieceRepository extends JpaRepository<Piece, Long> {

    // 회차에 속한 전체 조각 제목 리스트용
    List<Piece> findByEpisode_EpisodeId(Long episodeId);

    // 조각의 태그 목록
    @Query("""
        SELECT pt.tag.name
        FROM PieceTag pt
        WHERE pt.piece.pieceId = :pieceId
    """)
    List<String> findTagsByPieceId(@Param("pieceId") Long pieceId);

    // 회차의 전체 선곡 리스트 (piece가 null이면 회차 전체용 음악)
    @Query("""
        SELECT new org.nhnacademy.piececast.piece.dto.MusicDto(m.title, m.artist)
        FROM EpisodeMusic em
        JOIN em.music m
        WHERE em.episode.episodeId = :episodeId AND em.piece IS NULL
    """)
    List<MusicDto> findEpisodeMusic(@Param("episodeId") Long episodeId);


    // 조각에 연결된 음악 리스트
    @Query("""
        SELECT new org.nhnacademy.piececast.piece.dto.MusicDto(m.title, m.artist)
        FROM EpisodeMusic em
        JOIN em.music m
        WHERE em.piece.pieceId = :pieceId
    """)
    List<MusicDto> findPieceMusic(@Param("pieceId") Long pieceId);

}
