package dev.diegoborba.hackathonkipper.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.info")
public record ApplicationProperties(
        String name,
        String version,
        String javaVersion,
        String description,
        String encoding
) {
}