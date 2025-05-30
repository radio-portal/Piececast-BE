// AuthController.java
package org.nhnacademy.piececast.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/success")
    public String authSuccess(@RequestParam String token) {
        return "Login Success! Your JWT: " + token;
    }

    @GetMapping("/profile")
    public Object profile(@AuthenticationPrincipal Object user) {
        return user;
    }
}
