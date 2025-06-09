package org.nhnacademy.piececast.program.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieceDto {
    private Long pieceId;
    private String title;
}