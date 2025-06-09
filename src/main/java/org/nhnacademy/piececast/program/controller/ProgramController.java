package org.nhnacademy.piececast.program.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.piece.dto.PieceDetailResponse;
import org.nhnacademy.piececast.program.dto.ProgramCardDto;
import org.nhnacademy.piececast.program.service.ProgramService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @Operation(
            summary = "최신 회차 카드 목록 조회",
            description = "각 프로그램별 가장 최신 회차 정보를 카드 형식으로 조회합니다. 회차당 하나의 카드만 출력됩니다."
    )
    @GetMapping("/latest")
    public ResponseEntity<List<ProgramCardDto>> getProgramsLatestCards() {
        return ResponseEntity.ok(programService.getLatestEpisodeCards());
    }

    @Operation(
            summary = "특정 날짜의 회차 카드 목록 조회",
            description = "입력한 날짜에 해당하는 회차가 있는 프로그램들에 대해 카드 형식으로 정보를 조회합니다."
    )
    @GetMapping("/{date}")
    public ResponseEntity<List<ProgramCardDto>> getProgramsByDate(
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd)", example = "2025-05-22")
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(programService.getProgramsByDate(date));
    }

}
