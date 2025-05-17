package com.muzixiao2.bakabooru.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class HashUtil {

    public static String calculateHash(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return DigestUtils.sha256Hex(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("计算文件哈希失败", e);
        }
    }
}
