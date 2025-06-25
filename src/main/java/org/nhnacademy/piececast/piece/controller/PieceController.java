package org.nhnacademy.piececast.piece.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.dto.EpisodePieceDetailResponse;
import org.nhnacademy.piececast.piece.service.PieceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/episodes")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService pieceService;

    @Operation(summary = "회차 전체 조각 상세 목록 + 프로그램 정보")
    @GetMapping("/{episodeId}/full")
    public ResponseEntity<EpisodePieceDetailResponse> getEpisodeWithAllPieceDetails(@PathVariable Long episodeId) {
        return ResponseEntity.ok(pieceService.getEpisodeDetailByEpisodeId(episodeId));
    }
}

