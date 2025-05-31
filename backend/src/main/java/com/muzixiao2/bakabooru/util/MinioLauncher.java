package com.muzixiao2.bakabooru.util;

import com.muzixiao2.bakabooru.config.MinioConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class MinioLauncher {

    public static MinioConfig launchMinio() throws IOException {
        String exec = "backend/src/main/resources/bin/minio.exe";
        File minioBin = new File(exec);
        if (!minioBin.exists()) throw new FileNotFoundException("MinIO binary not found: " + exec);

        File dataDir = new File("data/minio");
        dataDir.mkdirs();

        int port = getFreePort();
        int consolePort = getFreePort();

        ProcessBuilder builder = new ProcessBuilder(
                minioBin.getAbsolutePath(), "server", dataDir.getAbsolutePath(),
                "--address=:" + port,
                "--console-address=:" + consolePort
        );

        builder.environment().put("MINIO_ROOT_USER", "minioadmin");
        builder.environment().put("MINIO_ROOT_PASSWORD", "minioadmin");

        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process proc = builder.start();
        Runtime.getRuntime().addShutdownHook(new Thread(proc::destroy));

        waitForReady(port);

        String endpoint = "http://127.0.0.1:" + port;
        String accessKey = "minioadmin";
        String secretKey = "minioadmin";
        String bucketName = "bakabooru";

        return new MinioConfig(endpoint, accessKey, secretKey, bucketName);
    }

    private static void waitForReady(int port) throws IOException {
        for (int i = 0; i < 20; i++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("127.0.0.1", port), 300);
                return;
            } catch (IOException e) {
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
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
