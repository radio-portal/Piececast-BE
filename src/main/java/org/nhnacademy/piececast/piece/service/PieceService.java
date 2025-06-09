package org.nhnacademy.piececast.piece.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.piece.dto.MusicDto;
import org.nhnacademy.piececast.piece.dto.PieceDetailResponse;
import org.nhnacademy.piececast.piece.repository.PieceRepository;
import org.nhnacademy.piececast.program.domain.Episode;
import org.nhnacademy.piececast.program.domain.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PieceService {
    private final PieceRepository pieceRepository;

    public PieceDetailResponse getPieceDetail(Long pieceId) {
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("해당 조각이 존재하지 않습니다."));
        Episode episode = piece.getEpisode();
        Program program = episode.getProgram();

        List<String> pieceTitles = pieceRepository.findByEpisode_EpisodeId(episode.getEpisodeId())
                .stream().map(Piece::getTitle).toList();

        List<String> tags = pieceRepository.findTagsByPieceId(pieceId);
        List<MusicDto> pieceMusics = pieceRepository.findPieceMusic(pieceId);
        List<MusicDto> episodeMusics = pieceRepository.findEpisodeMusic(episode.getEpisodeId());

        return new PieceDetailResponse(
                new PieceDetailResponse.ProgramInfo(program.getProgramId(), program.getProgram()),
                new PieceDetailResponse.EpisodeInfo(episode.getDate(), pieceTitles, episodeMusics),
                new PieceDetailResponse.PieceDetail(piece.getTitle(), piece.getSummary(), tags, pieceMusics)
        );
    }
}
