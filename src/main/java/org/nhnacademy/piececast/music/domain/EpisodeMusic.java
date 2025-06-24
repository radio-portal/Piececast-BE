package org.nhnacademy.piececast.music.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhnacademy.piececast.piece.domain.Piece;
import org.nhnacademy.piececast.program.domain.Episode;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EpisodeMusic {

    @EmbeddedId
    private EpisodeMusicId id;

    @ManyToOne
    @MapsId("episodeId")
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    @ManyToOne
    @MapsId("musicId")
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = true)
    private Piece piece;

    private int musicOrder;
}