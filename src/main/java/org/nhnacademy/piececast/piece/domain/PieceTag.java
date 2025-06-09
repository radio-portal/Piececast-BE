package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PieceTag {

    @EmbeddedId
    private PieceTagId id;

    @ManyToOne
    @MapsId("pieceId")
    @JoinColumn(name = "piece_id", nullable = false)
    private Piece piece;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}
