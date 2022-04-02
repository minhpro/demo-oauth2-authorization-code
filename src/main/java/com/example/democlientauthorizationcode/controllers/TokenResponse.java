package com.example.democlientauthorizationcode.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {
    String access_token;
    String token_type;
    String refresh_token;
    Long expires_in;
    Map<String, Object> user;
}
