package org.nhnacademy.piececast.music.repository;

import org.nhnacademy.piececast.music.domain.EpisodeMusic;
import org.nhnacademy.piececast.music.domain.EpisodeMusicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeMusicRepository extends JpaRepository<EpisodeMusic, EpisodeMusicId> {

    // 특정 회차(episodeId)에 해당하는 선곡 목록 조회
    List<EpisodeMusic> findByEpisode_EpisodeId(Long episodeId);
}