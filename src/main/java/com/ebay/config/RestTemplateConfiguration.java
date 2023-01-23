package com.ebay.config;

import com.ebay.api.client.auth.oauth2.model.AccessToken;
import com.ebay.exception.MissingAccessTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Configuration
public class RestTemplateConfiguration {
    @Autowired
    Authorization authorization;

    private HttpHeaders getHttpHeaders() throws MissingAccessTokenException {
        AccessToken accessToken;
        try {
            accessToken = authorization.getAccessToken();
        } catch (Exception exception) {
            throw new MissingAccessTokenException("Missing access token");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLanguage(Locale.US);
        headers.setBearerAuth(accessToken.getToken());
        return headers;
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new MyRestInterceptor()));
        return restTemplate;
    }

    private class MyRestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest,
                                            byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            httpRequest.getHeaders().addAll(getHttpHeaders());
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }


}
