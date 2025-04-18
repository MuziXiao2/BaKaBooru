package com.muzixiao2.bakabooru.backendsource.utils;

import com.muzixiao2.bakabooru.backendsource.dto.UploadResponseDTO;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MinIOUtil {

    private static final MinioClient minioClient = MinioClient.builder()
            .endpoint("http://localhost:9000")
            .credentials("ga9t9ShVs41vivkX0zaR", "fjogwAu69vI4g8ZpbftsbVMjJno3oBShX77wSA9p")
            .build();

    private static final String bucketName = "bakabooru";
    private static final String endpoint = "http://localhost:9000"; // 与 MinioClient 的 endpoint 保持一致

    static {
        try {
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build());

            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build());
            }
        } catch (MinioException e) {
            System.out.println("MinIO error occurred: " + e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
            throw new RuntimeException("Failed to initialize MinIO bucket", e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to initialize MinIO bucket", e);
        }
    }

    /**
     * 上传文件到 MinIO 并返回文件信息
     *
     * @param file 上传的文件
     * @return UploadResponseDTO 包含文件大小和永久访问 URL
     * @throws IllegalArgumentException 如果文件为空或无效
     */
    public static UploadResponseDTO upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file cannot be null or empty");
        }

        String uuid = UUID.randomUUID().toString();


        try (InputStream inputStream = file.getInputStream()) {
            // 上传文件
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(uuid)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            // 获取文件元数据
            StatObjectArgs statArgs = StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uuid)
                    .build();
            StatObjectResponse stat = minioClient.statObject(statArgs);

            // 构造永久访问 URL
            Long size = stat.size();
            String url = String.format("%s/%s/%s", endpoint, bucketName, uuid);

            String originalFilename = file.getOriginalFilename();

            String title, extension;
            int pointIndex = originalFilename.lastIndexOf(".");
            if (pointIndex != -1) {
                title = originalFilename.substring(0, pointIndex);
                extension = originalFilename.substring(pointIndex + 1);
            } else {
                title = originalFilename;
                extension = "";
            }


            return new UploadResponseDTO(title, uuid, extension, url, size);
        } catch (MinioException e) {
            throw new RuntimeException("Failed to upload file to MinIO: " + e.getMessage(), e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to upload file to MinIO", e);
        }
    }
}