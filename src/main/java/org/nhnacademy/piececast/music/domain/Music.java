package org.nhnacademy.piececast.music.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;
}
