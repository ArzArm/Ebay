package com.ebay.controller;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;
import com.ebay.api.client.auth.oauth2.model.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping()
public class AuthorizationController {
   public RefreshToken refreshToken;
    @Value("${ebay.environment}")
    public Environment environment;

    @GetMapping()
    public RefreshToken getRefreshToken(@RequestParam("code") String code) throws IOException {
        OAuth2Api oAuth2Api = new OAuth2Api();
        OAuthResponse authResponse = oAuth2Api.exchangeCodeForAccessToken(environment, code);
        refreshToken = authResponse.getRefreshToken().get();

        return refreshToken;
    }
}
