package com.muzixiao2.bakabooru.util;

import com.muzixiao2.bakabooru.config.MinioConfig;
import com.muzixiao2.bakabooru.config.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@RequiredArgsConstructor
public class MinioLauncher {
    private final MinioProperties properties;

    public MinioConfig launchMinio() throws IOException {
        File minioBin = new File(properties.getExecPath());
        if (!minioBin.exists()) {
            throw new FileNotFoundException("MinIO可执行文件未找到: " + properties.getExecPath() + 
                "\n请下载MinIO可执行文件并在application.yml中配置正确的路径");
        }

        File dataDir = new File(properties.getDataPath());
        dataDir.mkdirs();

        int port = getFreePort();
        int consolePort = getFreePort();

        ProcessBuilder builder = new ProcessBuilder(
                minioBin.getAbsolutePath(),
                "server",
                dataDir.getAbsolutePath(),
                "--address=:" + port,
                "--console-address=:" + consolePort
        );

        builder.environment().put("MINIO_ROOT_USER", properties.getRootUser());
        builder.environment().put("MINIO_ROOT_PASSWORD", properties.getRootPassword());

        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process proc = builder.start();
        Runtime.getRuntime().addShutdownHook(new Thread(proc::destroy));

        waitForReady(port);

        String endpoint = "http://127.0.0.1:" + port;
        return new MinioConfig(
            endpoint,
            properties.getRootUser(),
            properties.getRootPassword(),
            properties.getBucketName()
        );
    }

    private static void waitForReady(int port) throws IOException {
        for (int i = 0; i < 20; i++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("127.0.0.1", port), 300);
                return;
            } catch (IOException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("MinIO 启动超时");
    }

    private static int getFreePort() throws IOException {
        try (ServerSocket s = new ServerSocket(0)) {
            return s.getLocalPort();
        }
    }
}
