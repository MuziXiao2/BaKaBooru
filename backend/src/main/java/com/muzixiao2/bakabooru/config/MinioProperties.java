package com.muzixiao2.bakabooru.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    /**
     * MinIO可执行文件路径
     */
    private String execPath = "minio.exe";

    /**
     * MinIO数据存储目录
     */
    private String dataPath = "data/minio";

    /**
     * MinIO管理员用户名
     */
    private String rootUser = "minioadmin";

    /**
     * MinIO管理员密码
     */
    private String rootPassword = "minioadmin";

    /**
     * MinIO存储桶名称
     */
    private String bucketName = "bakabooru";
} 