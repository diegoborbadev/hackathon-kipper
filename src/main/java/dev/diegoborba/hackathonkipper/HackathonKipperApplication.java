package dev.diegoborba.hackathonkipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HackathonKipperApplication {
    public static void main(String[] args) {
        SpringApplication.run(HackathonKipperApplication.class, args);
    }
}