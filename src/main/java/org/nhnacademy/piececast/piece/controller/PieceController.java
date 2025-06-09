package org.nhnacademy.piececast.piece.controller;

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

    @GetMapping("/{pieceId}")
    public ResponseEntity<PieceDetailResponse> getPieceDetail(@PathVariable Long pieceId) {
        return ResponseEntity.ok(pieceService.getPieceDetail(pieceId));
    }
}
