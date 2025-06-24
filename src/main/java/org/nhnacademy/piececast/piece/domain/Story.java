package org.nhnacademy.piececast.piece.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyId;

    @Column(nullable = false)
    private String listenerName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}