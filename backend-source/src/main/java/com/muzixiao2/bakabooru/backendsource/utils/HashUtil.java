package com.muzixiao2.bakabooru.backendsource.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class HashUtil {

    /**
     * 计算MultipartFile的SHA-256哈希值
     *
     * @param file 文件
     * @return 哈希字符串
     */
    public static String calculateHash(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return DigestUtils.sha256Hex(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("计算文件哈希失败", e);
        }
    }
}
