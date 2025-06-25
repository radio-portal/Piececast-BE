package org.nhnacademy.piececast.piece.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.EpisodePieceDetailResponse;
import org.nhnacademy.piececast.piece.dto.MusicDto;
import org.nhnacademy.piececast.piece.dto.StoryDto;
import org.nhnacademy.piececast.piece.repository.PieceRepository;
import org.nhnacademy.piececast.piece.repository.PieceStoryRepository;
import org.nhnacademy.piececast.piece.repository.PieceTagRepository;
import org.nhnacademy.piececast.program.domain.Episode;
import org.nhnacademy.piececast.program.domain.Program;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;
    private final PieceTagRepository pieceTagRepository;
    private final PieceStoryRepository pieceStoryRepository;

    public EpisodePieceDetailResponse getEpisodeDetailByPieceId(Long pieceId) {
        Piece selectedPiece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("조각 없음"));

        Episode episode = selectedPiece.getEpisode();
        Program program = episode.getProgram();

        // 1) 회차에 속한 모든 조각 가져오기 (1쿼리)
        List<Piece> pieces = pieceRepository.findByEpisode_EpisodeId(episode.getEpisodeId());
        List<Long> pieceIds = pieces.stream().map(Piece::getPieceId).toList();

        // 2) 태그, 음악, 사연을 모두 Map<PieceId, List<...>> 으로 수집 (3쿼리)
        Map<Long, List<String>> tagMap = pieceRepository.findTagsByPieceIdsAsMap(pieceIds);
        Map<Long, List<MusicDto>> musicMap = pieceRepository.findPieceMusicMap(pieceIds);
        Map<Long, List<StoryDto>> storyMap = pieceRepository.findPieceStoriesMap(pieceIds);

        // 3) 모든 조각 상세정보 조립 (메모리에서)
        List<EpisodePieceDetailResponse.PieceDetail> pieceDetails = pieces.stream()
                .map(p -> new EpisodePieceDetailResponse.PieceDetail(
                        p.getPieceId(),
                        p.getTitle(),
                        p.getSummary(),
                        tagMap.getOrDefault(p.getPieceId(), List.of()),
                        musicMap.getOrDefault(p.getPieceId(), List.of()),
                        storyMap.getOrDefault(p.getPieceId(), List.of())
                ))
                .toList();

        return new EpisodePieceDetailResponse(
                new EpisodePieceDetailResponse.ProgramInfo(
                        program.getProgramId(),
                        program.getProgram(),
                        program.getThumbnailUrl()
                ),
                new EpisodePieceDetailResponse.EpisodeInfo(
                        episode.getEpisodeId(),
                        episode.getDate(),
                        pieceDetails
                )
        );
    }
}
