package org.nhnacademy.piececast.program.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/latest")
    public ResponseEntity<List<ProgramCardDto>> getProgramsLatestCards() {
        return ResponseEntity.ok(programService.getLatestEpisodeCards());
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<ProgramCardDto>> getProgramsByDate(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(programService.getProgramsByDate(date));
    }

}
