package com.muzixiao2.bakabooru.config;

import com.muzixiao2.bakabooru.util.MinIOUtil;
import com.muzixiao2.bakabooru.util.MinioLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MinIOConfiguration {

    @Bean
    public MinIOUtil minioUtil() throws IOException {
        MinioConfig config = MinioLauncher.launchMinio();
        return new MinIOUtil(config);
    }
}
