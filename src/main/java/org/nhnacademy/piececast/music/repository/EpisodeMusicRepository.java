package org.nhnacademy.piececast.music.repository;

import org.nhnacademy.piececast.music.domain.EpisodeMusic;
import org.nhnacademy.piececast.music.domain.EpisodeMusicId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeMusicRepository extends JpaRepository<EpisodeMusic, EpisodeMusicId> {

    @EntityGraph(attributePaths = {"music"})
    List<EpisodeMusic> findByEpisode_EpisodeId(Long episodeId);
}