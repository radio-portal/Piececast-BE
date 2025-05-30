
package org.nhnacademy.piececast.music.domain;

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
public class EpisodeMusicId implements Serializable {
    private Long episodeId;
    private Long musicId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EpisodeMusicId)) return false;
        EpisodeMusicId that = (EpisodeMusicId) o;
        return Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(musicId, that.musicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, musicId);
    }
}
