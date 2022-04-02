package com.example.democlientauthorizationcode.oauth2;

import com.example.democlientauthorizationcode.controllers.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class OAuth2Client {
    public static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth2.provider.access-token-uri:http://localhost:3000/login/oauth2/token'}")
    String accessTokenUrl;

    @Value("${oauth2.client-id:123}")
    String clientId;

    @Value("${oauth2.client-secret:zFBCNr}")
    String clientSecret;

    @Value("${oauth2.redirect-uri:http://localhost:8080/api/auth/oauth2response}")
    String redirectUrl;

    /** Get accessToken flow in oauth2 authorization code
     * @param code code will be used to exchange to get access_token
     * see the flow https://auth0.com/docs/get-started/authentication-and-authorization-flow/authorization-code-flow
     * see Canvas LMS guide https://canvas.instructure.com/doc/api/file.oauth.html#oauth2-flow-2.1
     * @return
     */
    public TokenResponse getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", GRANT_TYPE);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("redirect_uri", redirectUrl);
        map.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response
                = restTemplate.postForEntity(accessTokenUrl, request, TokenResponse.class);
        return response.getBody();
    }
}
