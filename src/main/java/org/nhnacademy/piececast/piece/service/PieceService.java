package org.nhnacademy.piececast.piece.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.EpisodePieceDetailResponse;
import org.nhnacademy.piececast.piece.dto.StoryDto;
import org.nhnacademy.piececast.piece.repository.PieceRepository;
import org.nhnacademy.piececast.piece.repository.PieceStoryRepository;
import org.nhnacademy.piececast.piece.repository.PieceTagRepository;
import org.nhnacademy.piececast.program.domain.Episode;
import org.nhnacademy.piececast.program.domain.Program;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // 회차에 속한 모든 조각
        List<Piece> pieces = pieceRepository.findByEpisode_EpisodeId(episode.getEpisodeId());

        List<EpisodePieceDetailResponse.PieceDetail> pieceDetails = pieces.stream()
                .map(piece -> new EpisodePieceDetailResponse.PieceDetail(
                        piece.getPieceId(),
                        piece.getTitle(),
                        piece.getSummary(),
                        pieceRepository.findTagsByPieceId(piece.getPieceId()),
                        pieceRepository.findPieceMusic(piece.getPieceId()),
                        pieceStoryRepository.findByPiece_PieceId(piece.getPieceId())
                                .stream()
                                .map(story -> new StoryDto(story.getStory().getListenerName(), story.getStory().getContent()))
                                .toList()
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
