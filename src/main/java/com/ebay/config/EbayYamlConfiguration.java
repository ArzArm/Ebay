package com.ebay.config;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
@Configuration
public class EbayYamlConfiguration {

    @Value("${file.path}")
    private String ebayConfig;

    public void load() {
        File file = new File(ebayConfig);
        try (final FileInputStream inputStream1 = new FileInputStream(file);) {
            CredentialUtil.load(inputStream1);
        } catch (Exception credFailureEx) {
            log.error("Failed to load eBay configuration.", credFailureEx);
        }

    }

}
