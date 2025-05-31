package com.muzixiao2.bakabooru.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinioConfig {
    private final String endpoint;
    private final String accessKey;
    private final String secretKey;
    private final String bucketName;
}
