package org.nhnacademy.piececast.piece.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.dto.EpisodePieceDetailResponse;
import org.nhnacademy.piececast.piece.service.PieceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService pieceService;

    @Operation(summary = "회차 상세 조각 목록 + 프로그램 정보")
    @GetMapping("/{pieceId}/episode-full")
    public ResponseEntity<EpisodePieceDetailResponse> getEpisodeWithAllPieceDetails(@PathVariable Long pieceId) {
        return ResponseEntity.ok(pieceService.getEpisodeDetailByPieceId(pieceId));
    }

}
