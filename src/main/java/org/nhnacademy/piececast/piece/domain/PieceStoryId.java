package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PieceStoryId implements Serializable {

    @Column(name = "piece_id")
    private Long pieceId;

    @Column(name = "story_id")
    private Long storyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceStoryId)) return false;
        PieceStoryId that = (PieceStoryId) o;
        return Objects.equals(pieceId, that.pieceId) && Objects.equals(storyId, that.storyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, storyId);
    }
}
