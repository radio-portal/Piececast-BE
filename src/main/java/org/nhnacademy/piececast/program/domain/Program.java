package org.nhnacademy.piececast.program.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    @Column(nullable = false)
    private String station;

    @Column(nullable = false)
    private String program;

    @Column(nullable = false)
    private String djName;

    @Column(nullable = false)
    private int time;
}
