package com.muzixiao2.bakabooru.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Data
@Component
@ConfigurationProperties(prefix = "source")
public class SourceConfig {
    private String name;
    private String url;
    private String ossUrl;
    private String creator;
    private Instant createdAt;
}