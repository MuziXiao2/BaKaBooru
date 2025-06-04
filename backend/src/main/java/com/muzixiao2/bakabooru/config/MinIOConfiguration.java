package com.muzixiao2.bakabooru.config;

import com.muzixiao2.bakabooru.util.MinIOUtil;
import com.muzixiao2.bakabooru.util.MinioLauncher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MinIOConfiguration {

    private final MinioLauncher minioLauncher;

    public MinIOConfiguration(MinioLauncher minioLauncher) {
        this.minioLauncher = minioLauncher;
    }

    @Bean
    public MinIOUtil minioUtil() throws IOException {
        MinioConfig config = minioLauncher.launchMinio();
        return new MinIOUtil(config);
    }
}
