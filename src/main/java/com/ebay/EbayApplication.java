package com.ebay;


import com.ebay.config.EbayPropConfiguration;
import com.ebay.config.EbayYamlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@SpringBootApplication
public class EbayApplication implements CommandLineRunner {

    @Autowired
    EbayYamlConfiguration ebayConfiguration;

    public static void main(String[] args) {

        SpringApplication.run(EbayApplication.class);
    }

    @Override
    public void run(String... args) {
        ebayConfiguration.load();
    }
}
