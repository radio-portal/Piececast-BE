package org.nhnacademy.piececast.service;

import org.nhnacademy.piececast.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void deleteById(Long id);
}
