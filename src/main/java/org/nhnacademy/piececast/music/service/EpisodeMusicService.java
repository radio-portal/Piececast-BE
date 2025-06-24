package org.nhnacademy.piececast.music.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.music.dto.PlaylistResponse;
import org.nhnacademy.piececast.music.repository.EpisodeMusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpisodeMusicService {

    private final EpisodeMusicRepository episodeMusicRepository;

    public List<PlaylistResponse.MusicSimple> getMusicListByEpisodeId(Long episodeId) {
        return episodeMusicRepository.findByEpisode_EpisodeId(episodeId)
                .stream()
                .map(em -> new PlaylistResponse.MusicSimple(
                        em.getMusic().getMusicId(),
                        em.getMusic().getTitle(),
                        em.getMusic().getArtist()
                ))
                .collect(Collectors.toList());
    }
}
