package com.muzixiao2.bakabooru.source.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "source")
public class SourceConfig {
    private String defaultName;
    private String url;
    private String ossUrl;
    private String creator;
    private String createdAt;
    private String updatedAt;
}