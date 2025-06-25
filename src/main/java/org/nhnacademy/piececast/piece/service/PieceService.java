package org.nhnacademy.piececast.piece.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.EpisodePieceDetailResponse;
import org.nhnacademy.piececast.piece.dto.MusicDto;
import org.nhnacademy.piececast.piece.dto.StoryDto;
import org.nhnacademy.piececast.piece.repository.PieceRepository;
import org.nhnacademy.piececast.program.domain.Episode;
import org.nhnacademy.piececast.program.domain.Program;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;

    public EpisodePieceDetailResponse getEpisodeDetailByEpisodeId(Long episodeId) {
        List<Piece> pieces = pieceRepository.findByEpisode_EpisodeId(episodeId);
        if (pieces.isEmpty()) {
            throw new RuntimeException("해당 회차의 조각이 존재하지 않습니다.");
        }

        Episode episode = pieces.get(0).getEpisode();  // 어차피 같은 episode
        Program program = episode.getProgram();
        List<Long> pieceIds = pieces.stream().map(Piece::getPieceId).toList();

        Map<Long, List<String>> tagMap = pieceRepository.findTagsByPieceIdsAsMap(pieceIds);
        Map<Long, List<MusicDto>> musicMap = pieceRepository.findPieceMusicMap(pieceIds);
        Map<Long, List<StoryDto>> storyMap = pieceRepository.findPieceStoriesMap(pieceIds);

        List<EpisodePieceDetailResponse.PieceDetail> pieceDetails = pieces.stream()
                .map(p -> new EpisodePieceDetailResponse.PieceDetail(
                        p.getPieceId(),
                        p.getTitle(),
                        p.getSummary(),
                        p.getMp3Path(),
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
