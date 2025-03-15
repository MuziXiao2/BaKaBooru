package com.muzixiao2.bakabooru.backendsource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "source")
public class SourceConfig {
    private String name;
    private String url;
    private String creator;
    private String createdAt;
    private String updatedAt;
}