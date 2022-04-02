package com.example.democlientauthorizationcode.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal Authentication authentication) {
        return Collections.singletonMap("name", authentication.getName());
    }
}
