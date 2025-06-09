package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TodayView {
    @Id
    private Long pieceId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private Piece piece;

    private Long view;
    private java.time.LocalDate date;
}
