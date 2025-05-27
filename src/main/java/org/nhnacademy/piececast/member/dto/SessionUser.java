package org.nhnacademy.piececast.member.dto;

import lombok.Getter;
import org.nhnacademy.piececast.member.domain.Member;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    public SessionUser(Member member) {
        this.name = member.getUsername();
        this.email = member.getEmail();
    }
}