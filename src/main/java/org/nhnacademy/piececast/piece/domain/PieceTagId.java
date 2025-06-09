package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PieceTagId implements Serializable {
    private Long pieceId;
    private Integer tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceTagId)) return false;
        PieceTagId that = (PieceTagId) o;
        return Objects.equals(pieceId, that.pieceId) &&
                Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, tagId);
    }
}

