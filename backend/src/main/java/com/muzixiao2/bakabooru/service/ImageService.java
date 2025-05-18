package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.entity.ImageImageFile;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.repository.ImageFileRepository;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.util.HashUtil;
import com.muzixiao2.bakabooru.util.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ImageFileRepository imageFileRepository;

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(ImageRequestDTO imageRequestDTO) {
        // 创建实体
        Image image = imageMapper.toEntity(imageRequestDTO);
        // 保存实体
        image = imageRepository.save(image);
        // 获取实体
        image = imageRepository.findByUuid(image.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        // 返回响应DTO
        return imageMapper.toResponseDTO(image);
    }

    // 获取单个图片
    @Transactional(readOnly = true)
    public ImageResponseDTO getImage(String uuid) {
        //获取所需实体
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        //转换为响应DTO
        return imageMapper.toResponseDTO(image);
    }

    // 获取所有图片
    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getAllImages() {
        //获取所需实体
        List<Image> imageList = imageRepository.findAll();
        //转换为响应DTO
        return imageList.stream().map(imageMapper::toResponseDTO).toList();
    }

    // 添加图片文件
    @Transactional
    public ImageFileResponseDTO addImageFile(String uuid, MultipartFile file) {
        //计算图片哈希
        String hash = HashUtil.calculateHash(file);
        //保存图片文件
        if (!imageFileRepository.existsByHash(hash)) {
            ImageFileUploadResponseDTO imageFileUploadResponseDTO = minIOUtil.upload(hash, file);
            ImageFile imageFile = imageMapper.toEntity(imageFileUploadResponseDTO);
            imageFileRepository.save(imageFile);
        }
        //获取所需实体
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageFile imageFile = imageFileRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("图片文件不存在"));
        //添加图片文件到图片
        ImageImageFile imageImageFile = image.addImageFile(imageFile, file.getOriginalFilename());
        //转换为响应DTO
        return imageMapper.toResponseDTO(imageImageFile);
    }

    // 获取所有图片文件
    @Transactional(readOnly = true)
    public List<ImageFileResponseDTO> getAllImageFiles(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return image.getImageImageFiles()
                .stream()
                .map(imageMapper::toResponseDTO)
                .toList();
    }

    // 获取图片文件临时URL
    @Transactional(readOnly = true)
    public String getImageFileUrl(String hash) {
        ImageFile imageFile = imageFileRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return minIOUtil.generatePresignedUrl(imageFile.getHash());
    }
}
