package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhnacademy.piececast.program.domain.Episode;
@Entity
@Getter
@NoArgsConstructor
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pieceId;

    @Column(name = "mp3_path", nullable = true, columnDefinition = "TEXT")
    private String mp3Path;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = true)
    private int pieceOrder;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;
}