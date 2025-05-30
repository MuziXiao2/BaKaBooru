package com.muzixiao2.bakabooru.util;

import com.muzixiao2.bakabooru.config.MinioProperties;
import com.muzixiao2.bakabooru.dto.file.FileUploadResponseDTO;
import io.minio.*;
import io.minio.http.Method;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class MinIOUtil {
    private final MinioClient minioClient;
    private final String bucketName;
    private final int urlExpirySeconds;

    private static final int MAX_RETRIES = 5;
    private static final int RETRY_INTERVAL_SECONDS = 5;

    public MinIOUtil(MinioProperties properties) {
        this.bucketName = properties.getBucketName();
        this.urlExpirySeconds = properties.getUrlExpiry() != null ? properties.getUrlExpiry() : 3600;
        this.minioClient = MinioClient
                .builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();

        waitForMinIO();

        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化MinIO桶失败", e);
        }
    }

    /**
     * 等待 MinIO 启动并可用
     */
    private void waitForMinIO() {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                minioClient.listBuckets();
                return; // 成功
            } catch (Exception e) {
                attempts++;
                System.err.printf("等待 MinIO 启动中（第 %d/%d 次）...\n", attempts, MAX_RETRIES);
                try {
                    TimeUnit.SECONDS.sleep(RETRY_INTERVAL_SECONDS);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("等待 MinIO 启动失败，已超过最大重试次数");
    }

    public FileUploadResponseDTO upload(String hash, MultipartFile file) {
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

        // 获取图片宽高
        int width, height;
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e) {
            throw new RuntimeException("获取图片信息失败", e);
        }

        // 获取图片类型
        String type;
        try {
            Tika tika = new Tika();
            String mimeType = tika.detect(file.getInputStream());
            type = (mimeType != null) ? mimeType : "unknown";
        } catch (IOException e) {
            throw new RuntimeException("获取图片类型失败", e);
        }

        Long size = file.getSize();
        return new FileUploadResponseDTO(hash, type, size, width, height);
    }

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
