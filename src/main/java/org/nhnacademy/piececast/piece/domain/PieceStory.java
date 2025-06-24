package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class PieceStory {

    @EmbeddedId
    private PieceStoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pieceId")
    @JoinColumn(name = "piece_id")
    private Piece piece;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storyId")
    @JoinColumn(name = "story_id")
    private Story story;
}
