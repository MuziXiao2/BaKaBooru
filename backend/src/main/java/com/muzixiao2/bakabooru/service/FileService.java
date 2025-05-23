package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.file.FileDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.file.FileUploadResponseDTO;
import com.muzixiao2.bakabooru.entity.File;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.repository.FileRepository;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.util.HashUtil;
import com.muzixiao2.bakabooru.util.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final FileRepository fileRepository;
    private final ImageRepository imageRepository;

    // 获取文件临时URL
    @Transactional(readOnly = true)
    public String getFileUrl(String hash) {
        File file = fileRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("文件不存在"));
        return minIOUtil.generatePresignedUrl(file.getHash());
    }

    // 获取图片内所有文件详细信息
    @Transactional(readOnly = true)
    public List<FileDetailResponseDTO> getFileDetails(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return image
                .getImageFiles()
                .stream()
                .map(imageMapper::toResponseDTO)
                .toList();
    }

    // 为图片添加文件
    @Transactional
    public FileDetailResponseDTO addFile(String uuid, MultipartFile sourceFile) {
        String hash = HashUtil.calculateHash(sourceFile);
        if (!fileRepository.existsByHash(hash)) {
            FileUploadResponseDTO fileUploadResponseDTO = minIOUtil.upload(hash, sourceFile);
            File file = imageMapper.toEntity(fileUploadResponseDTO);
            fileRepository.save(file);
        }
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        File file = fileRepository.findByHash(hash).orElseThrow(() -> new IllegalArgumentException("图片文件不存在"));
        ImageFile imageFile = image.addImageFile(file, sourceFile.getOriginalFilename());
        return imageMapper.toResponseDTO(imageFile);
    }
}
