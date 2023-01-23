package com.ebay.config;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


@Configuration
public class EbayPropConfiguration {
    @Value("${name}")
    private String name;
    @Value("${credentials}")
    private String credentials;


    public String decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return new String(decodedBytes);
    }

    public void load() {

        String inputStreamString = decode(name) +
                "\n" +
                decode(credentials);
        CredentialUtil.load(inputStreamString);

    }
}



