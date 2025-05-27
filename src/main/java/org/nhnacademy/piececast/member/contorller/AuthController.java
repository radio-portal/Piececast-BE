package org.nhnacademy.piececast.member.contorller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("http://localhost:8080/login/oauth2/code/spotify")
    public String home() {
        return "home"; // templates/home.html
    }

    @GetMapping("/secure")
    @ResponseBody
    public String secure(@AuthenticationPrincipal OAuth2User principal) {
        return "로그인 사용자: " + principal.getAttribute("display_name");
    }
}

