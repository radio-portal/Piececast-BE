package org.nhnacademy.piececast.piece.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.dto.PieceDetailResponse;
import org.nhnacademy.piececast.piece.service.PieceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService pieceService;

    @Operation(summary = "조각 상세 조회", description = "조각 ID를 기준으로 프로그램, 회차, 조각 정보를 상세하게 조회합니다.")
    @GetMapping("/{pieceId}")
    public ResponseEntity<PieceDetailResponse> getPieceDetail(@PathVariable Long pieceId) {
        return ResponseEntity.ok(pieceService.getPieceDetail(pieceId));
    }
}
