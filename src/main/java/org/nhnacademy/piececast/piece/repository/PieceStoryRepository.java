package org.nhnacademy.piececast.piece.repository;

import org.nhnacademy.piececast.piece.domain.PieceStory;
import org.nhnacademy.piececast.piece.domain.PieceStoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieceStoryRepository extends JpaRepository<PieceStory, PieceStoryId> {
}
