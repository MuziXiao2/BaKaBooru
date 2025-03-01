package com.xiao2.bakabooru.utils;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MinIOFileUploader {

    private static final MinioClient minioClient;

    private static final String bucketName = "bakabooru";

    static {
        minioClient = MinioClient.builder().endpoint("http://localhost:9000").credentials("ga9t9ShVs41vivkX0zaR", "fjogwAu69vI4g8ZpbftsbVMjJno3oBShX77wSA9p").build();

        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!bucketExists) minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updataImage(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 获取文件输入流
        try (InputStream inputStream = file.getInputStream()) {

            // 生成唯一标识符作为文件名
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            }

            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(inputStream, file.getSize(), -1).contentType(file.getContentType()).build());
        }
    }

}