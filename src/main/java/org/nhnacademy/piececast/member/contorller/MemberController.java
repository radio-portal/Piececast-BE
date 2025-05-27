package org.nhnacademy.piececast.contorller;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.piececast.domain.Member;
import org.nhnacademy.piececast.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/save")
    public void PersonSave(@RequestBody Member member) {
        memberService.save(member);
    }

}
