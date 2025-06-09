package org.nhnacademy.piececast.program.controller;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.program.dto.ProgramCardDto;
import org.nhnacademy.piececast.program.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
