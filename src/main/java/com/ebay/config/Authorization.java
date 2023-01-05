package com.ebay.config;


import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.AccessToken;
import com.ebay.api.client.auth.oauth2.model.Environment;


import com.ebay.api.client.auth.oauth2.model.OAuthResponse;
import com.ebay.api.client.auth.oauth2.model.RefreshToken;
import com.ebay.controller.AuthorizationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class Authorization {

    @Autowired
    AuthorizationController authorizationController;

    OAuth2Api oAuth2Api = new OAuth2Api();
    private static final List<String> SCOPE_LIST_SANDBOX =
            Arrays.asList(new String[]{"https://api.ebay.com/oauth/api_scope", "https://api.ebay.com/oauth/api_scope/sell.inventory"});


    public OAuthResponse getOauth2Response() throws IOException {
        RefreshToken refreshToken = authorizationController.refreshToken;
        return oAuth2Api.getAccessToken(Environment.SANDBOX, refreshToken.getToken(), SCOPE_LIST_SANDBOX);
    }

    public AccessToken getAccessToken() throws IOException {
        OAuthResponse oauth2Response = getOauth2Response();
        return oauth2Response.getAccessToken().get();
    }

}

