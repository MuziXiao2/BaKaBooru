package com.muzixiao2.bakabooru.backendsource.utils;

import com.muzixiao2.bakabooru.backendsource.config.MinioProperties;
import com.muzixiao2.bakabooru.backendsource.dto.ImageUploadResponseDTO;
import io.minio.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class MinIOUtil {

    private final MinioClient minioClient;
    private final String bucketName;
    private final int urlExpirySeconds;

    public MinIOUtil(MinioProperties properties) {
        this.bucketName = properties.getBucketName();
        this.urlExpirySeconds = properties.getUrlExpiry() != null ? properties.getUrlExpiry() : 3600;
        this.minioClient = MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();

        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化MinIO桶失败", e);
        }
    }

    /**
     * 利用 Commons Codec 计算MultipartFile的SHA-256哈希值（16进制字符串）
     */
    public String calculateHash(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return DigestUtils.sha256Hex(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("计算文件哈希失败", e);
        }
    }

    /**
     * 上传文件到MinIO，objectKey由文件内容hash生成
     */
    public ImageUploadResponseDTO upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        try {
            // 计算文件hash作为objectKey
            String hash = calculateHash(file);

            // 先判断文件是否已存在
            boolean exists = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(hash)
                            .build()) != null;

            // 重新获取流上传文件
            if (!exists) {
                try (InputStream inputStream2 = file.getInputStream()) {
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(hash)
                                    .stream(inputStream2, file.getSize(), -1)
                                    .contentType(file.getContentType())
                                    .build());
                }
            }

            // 获取源文件信息
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            int idx = originalFilename.lastIndexOf('.');
            if (idx != -1) {
                extension = originalFilename.substring(idx);
                originalFilename = originalFilename.substring(0, idx);
            }
            return new ImageUploadResponseDTO(hash, originalFilename, extension, file.getSize());

        } catch (Exception e) {
            throw new RuntimeException("上传文件到MinIO失败", e);
        }
    }

    /**
     * 根据图片hash生成带令牌的临时访问URL
     *
     * @param objectKey 图片唯一标识（hash）
     * @return 带令牌的临时访问URL
     */
    public String generatePresignedUrl(String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .expiry(urlExpirySeconds)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("生成带令牌临时链接失败", e);
        }
    }
}
