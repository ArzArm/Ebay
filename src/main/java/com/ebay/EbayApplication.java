package com.ebay;


import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.model.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.InputStream;


@SpringBootApplication
public class EbayApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(EbayApplication.class);
    private static final String EBAY_CONFIG = "ebay-config.yaml";

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }



    public static void main(String[] args) {
        try (final InputStream fis = EbayApplication.class.getClassLoader()
                .getResourceAsStream(EBAY_CONFIG)) {
            CredentialUtil.load(fis);
            System.out.println(CredentialUtil.getCredentials(Environment.SANDBOX).toString());
        } catch (Exception credFailureEx) {
            LOGGER.error("Failed to load eBay configuration.", credFailureEx);
            return;
        }
        SpringApplication.run(EbayApplication.class);
    }


}
