package org.nhnacademy.piececast.subscribe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhnacademy.piececast.member.domain.Member;
import org.nhnacademy.piececast.program.domain.Program;

@Entity
@Getter
@NoArgsConstructor
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
}