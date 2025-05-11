package com.muzixiao2.bakabooru.source.utils;

import com.muzixiao2.bakabooru.source.config.MinioProperties;
import com.muzixiao2.bakabooru.source.dto.image.ImageUploadResponseDTO;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
     * 上传文件到MinIO，objectKey由文件内容hash生成
     */
    // 修改后的upload
    public ImageUploadResponseDTO upload(String hash, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(hash)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("上传文件到MinIO失败", e);
        }
        int width, height;
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e) {
            throw new RuntimeException("获取图片信息", e);
        }
        // 返回上传成功后的信息
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        int idx = originalFilename.lastIndexOf('.');
        if (idx != -1) {
            extension = originalFilename.substring(idx);
            originalFilename = originalFilename.substring(0, idx);
        }
        return new ImageUploadResponseDTO(hash, originalFilename, extension, file.getSize(), width, height);
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
                            .method(Method.GET)
                            .expiry(urlExpirySeconds)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("生成带令牌临时链接失败", e);
        }
    }
}
