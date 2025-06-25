package org.nhnacademy.piececast.piece.repository;

import org.nhnacademy.piececast.piece.domain.PieceTag;
import org.nhnacademy.piececast.piece.domain.PieceTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PieceTagRepository extends JpaRepository<PieceTag, PieceTagId> {
    List<PieceTag> findByPiece_PieceId(Long pieceId);
}
