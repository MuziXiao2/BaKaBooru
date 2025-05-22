package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.repository.ImageFileRepository;
import com.muzixiao2.bakabooru.util.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageFileService {
    private final MinIOUtil minIOUtil;
    private final ImageFileRepository imageFileRepository;

    // 获取图片文件临时URL
    @Transactional(readOnly = true)
    public String getImageFileUrl(String hash) {
        ImageFile imageFile = imageFileRepository.findByHash(hash).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return minIOUtil.generatePresignedUrl(imageFile.getHash());
    }

}
