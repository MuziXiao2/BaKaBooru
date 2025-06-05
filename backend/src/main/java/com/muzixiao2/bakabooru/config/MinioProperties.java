package com.muzixiao2.bakabooru.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MinIO 配置属性类，绑定 application.properties 中 minio 前缀的配置。
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    /**
     * MinIO 可执行文件路径
     */
    private String execPath = "data/minio.exe";

    /**
     * MinIO 数据存储目录
     */
    private String dataPath = "data/minio";

    /**
     * MinIO 管理员用户名
     */
    private String rootUser = "minioadmin";

    /**
     * MinIO 管理员密码
     */
    private String rootPassword = "minioadmin";

    /**
     * MinIO 存储桶名称
     */
    private String bucketName = "bakabooru";

    /**
     * 预签名 URL 有效期（秒）
     */
    private int urlExpirySeconds = 3600;

    /**
     * 初始化 MinIO 客户端重试次数
     */
    private int maxRetries = 5;

    /**
     * 重试间隔（秒）
     */
    private int retryIntervalSeconds = 5;
}