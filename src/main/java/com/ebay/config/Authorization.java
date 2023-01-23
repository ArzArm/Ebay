package com.ebay.config;


import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.AccessToken;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;
import com.ebay.api.client.auth.oauth2.model.RefreshToken;
import com.ebay.controller.AuthorizationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Authorization {

    @Autowired
    AuthorizationController authorizationController;

    @Value("${ebay.scope.list}")
    private List<String> scopeList;

    OAuth2Api oAuth2Api = new OAuth2Api();

    public OAuthResponse getOauth2Response() throws IOException {
        RefreshToken refreshToken = authorizationController.refreshToken;
        return oAuth2Api.getAccessToken(authorizationController.environment, refreshToken.getToken(), scopeList);
    }


    public AccessToken getAccessToken() throws IOException {
        OAuthResponse oauth2Response = getOauth2Response();
        return oauth2Response.getAccessToken().get();
    }

}

