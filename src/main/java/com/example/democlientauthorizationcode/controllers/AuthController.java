package com.example.democlientauthorizationcode.controllers;

import com.example.democlientauthorizationcode.oauth2.OAuth2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${oauth2.provider.authorization-uri:http://localhost:3000/login/oauth2/auth}")
    String oauth2AuthorizationUrl;

    @Value("${oauth2.state.value:jGJRpNZ8sOl2k}")
    String oauth2StateValue;

    @Value("${oauth2.client-id:123}")
    String clientId;

    @Value("${oauth2.redirect-uri:http://localhost:8080/api/auth/oauth2response}")
    String redirectUrl;

    @Autowired
    OAuth2Client oAuth2Client;

    @GetMapping("/login")
    public String login() {
        return String.format("redirect:%s?client_id=%s&response_type=code&state=%s&redirect_uri=%s",
                oauth2AuthorizationUrl, clientId, oauth2StateValue, redirectUrl);
    }

    @GetMapping("oauth2response")
    @ResponseBody
    public TokenResponse oauth2response(@RequestParam("code") String code, @RequestParam("state") String state) {
        if (oauth2StateValue.equals(state))
            return oAuth2Client.getToken(code);
        else
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
}
