package org.nhnacademy.piececast.music.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.music.domain.Music;
import org.nhnacademy.piececast.music.repository.EpisodeMusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpisodeMusicService {

    private final EpisodeMusicRepository episodeMusicRepository;

    public List<Music> getMusicsByEpisodeId(Long episodeId) {
        return episodeMusicRepository.findByEpisode_EpisodeId(episodeId)
                .stream()
                .map(em -> em.getMusic())
                .collect(Collectors.toList());
    }
}

