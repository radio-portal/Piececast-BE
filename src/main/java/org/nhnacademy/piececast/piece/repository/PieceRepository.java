package org.nhnacademy.piececast.piece.repository;

import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.MusicDto;
import org.nhnacademy.piececast.piece.dto.StoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import java.util.stream.Collectors;

public interface PieceRepository extends JpaRepository<Piece, Long> {

    List<Piece> findByEpisode_EpisodeId(Long episodeId);

    @Query("""
        SELECT pt.piece.pieceId, pt.tag.name
        FROM PieceTag pt
        WHERE pt.piece.pieceId IN :pieceIds
    """)
    List<Object[]> findTagsByPieceIds(@Param("pieceIds") List<Long> pieceIds);

    default Map<Long, List<String>> findTagsByPieceIdsAsMap(List<Long> pieceIds) {
        return findTagsByPieceIds(pieceIds).stream()
                .collect(Collectors.groupingBy(
                        row -> (Long) row[0],
                        Collectors.mapping(row -> (String) row[1], Collectors.toList())
                ));
    }

    @Query("""
        SELECT em.piece.pieceId, new org.nhnacademy.piececast.piece.dto.MusicDto(m.title, m.artist)
        FROM EpisodeMusic em
        JOIN em.music m
        WHERE em.piece.pieceId IN :pieceIds
    """)
    List<Object[]> findPieceMusicByPieceIds(@Param("pieceIds") List<Long> pieceIds);

    default Map<Long, List<MusicDto>> findPieceMusicMap(List<Long> pieceIds) {
        return findPieceMusicByPieceIds(pieceIds).stream()
                .collect(Collectors.groupingBy(
                        row -> (Long) row[0],
                        Collectors.mapping(row -> (MusicDto) row[1], Collectors.toList())
                ));
    }

    @Query("""
        SELECT ps.piece.pieceId, new org.nhnacademy.piececast.piece.dto.StoryDto(s.listenerName, s.content)
        FROM PieceStory ps
        JOIN ps.story s
        WHERE ps.piece.pieceId IN :pieceIds
    """)
    List<Object[]> findPieceStories(@Param("pieceIds") List<Long> pieceIds);

    default Map<Long, List<StoryDto>> findPieceStoriesMap(List<Long> pieceIds) {
        return findPieceStories(pieceIds).stream()
                .collect(Collectors.groupingBy(
                        row -> (Long) row[0],
                        Collectors.mapping(row -> (StoryDto) row[1], Collectors.toList())
                ));
    }
}