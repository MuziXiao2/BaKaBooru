package com.muzixiao2.bakabooru.util;

import com.muzixiao2.bakabooru.config.MinioProperties;
import com.muzixiao2.bakabooru.dto.file.FileUploadResponseDTO;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * MinIO 工具类，负责启动 MinIO 服务、文件上传和生成预签名 URL。
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
public class MinIOUtil {
    private final MinioProperties properties;

    private MinioClient minioClient;
    private String endpoint;
    private Process minioProcess;

    /**
     * 初始化 MinIO 服务和客户端，确保桶存在。
     * 在 Spring Bean 初始化后调用。
     */
    @PostConstruct
    public void init() {
        try {
            // 启动 MinIO 服务
            launchMinioServer();

            // 初始化 MinIO 客户端
            minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(properties.getRootUser(), properties.getRootPassword())
                    .build();

            // 等待 MinIO 服务就绪
            waitForMinIO();

            // 检查并创建桶
            String bucketName = properties.getBucketName();
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("MinIO 初始化失败", e);
        }
    }

    /**
     * 启动 MinIO 服务，动态分配端口并设置环境变量。
     */
    private void launchMinioServer() throws IOException {
        File minioBin = new File(properties.getExecPath());
        if (!minioBin.exists()) {
            throw new FileNotFoundException("MinIO 可执行文件未找到: " + properties.getExecPath());
        }

        File dataDir = new File(properties.getDataPath());
        dataDir.mkdirs();

        // 动态分配端口
        int port = getFreePort();
        int consolePort = getFreePort();

        // 构建 MinIO 启动命令
        ProcessBuilder builder = new ProcessBuilder(
                minioBin.getAbsolutePath(),
                "server",
                dataDir.getAbsolutePath(),
                "--address=:" + port,
                "--console-address=:" + consolePort
        );

        // 设置环境变量
        builder.environment().put("MINIO_ROOT_USER", properties.getRootUser());
        builder.environment().put("MINIO_ROOT_PASSWORD", properties.getRootPassword());

        // 重定向输出和错误日志
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        minioProcess = builder.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        this.endpoint = "http://127.0.0.1:" + port;
        waitForReady(port);
    }

    /**
     * 关闭 MinIO 服务进程。
     */
    private void shutdown() {
        if (minioProcess != null && minioProcess.isAlive()) {
            minioProcess.destroy();
        }
    }

    /**
     * 等待 MinIO 服务端口可用。
     *
     * @param port MinIO 服务端口
     */
    private void waitForReady(int port) throws IOException {
        for (int i = 0; i < 20; i++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("127.0.0.1", port), 300);
                return;
            } catch (IOException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new IOException("MinIO 启动超时");
    }

    /**
     * 获取可用端口。
     *
     * @return 可用端口号
     */
    private static int getFreePort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }

    /**
     * 等待 MinIO 客户端连接成功。
     */
    private void waitForMinIO() {
        int attempts = 0;
        while (attempts < properties.getMaxRetries()) {
            try {
                minioClient.listBuckets();
                return;
            } catch (Exception e) {
                attempts++;
                System.err.printf("等待 MinIO 启动（第 %d/%d 次）...\n", attempts, properties.getMaxRetries());
                try {
                    TimeUnit.SECONDS.sleep(properties.getRetryIntervalSeconds());
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("MinIO 启动失败，超过最大重试次数");
    }

    /**
     * 上传文件到 MinIO 并返回文件信息。
     *
     * @param hash 文件的唯一标识
     * @param file 上传的文件
     * @return 文件信息 DTO
     */
    public FileUploadResponseDTO upload(String hash, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        try (InputStream inputStream = file.getInputStream()) {
            // 上传文件到 MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucketName())
                            .object(hash)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            // 获取图片宽高
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new IOException("无法读取图片");
            }
            int width = image.getWidth();
            int height = image.getHeight();

            // 获取文件类型
            Tika tika = new Tika();
            String mimeType = tika.detect(file.getInputStream());
            String type = (mimeType != null) ? mimeType : "unknown";

            return new FileUploadResponseDTO(hash, type, file.getSize(), width, height);
        } catch (IOException e) {
            throw new RuntimeException("文件上传或处理失败", e);
        } catch (Exception e) {
            throw new RuntimeException("上传文件到 MinIO 失败", e);
        }
    }

    /**
     * 生成文件的预签名 URL。
     *
     * @param objectKey 文件的键
     * @return 预签名 URL
     */
    public String generatePresignedUrl(String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(properties.getBucketName())
                            .object(objectKey)
                            .method(Method.GET)
                            .expiry(properties.getUrlExpirySeconds())
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("生成预签名 URL 失败", e);
        }
    }
}