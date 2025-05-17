package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
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

    // 上传图片文件
    @Transactional
    public ImageFileUploadResponseDTO uploadImageFile(MultipartFile file) {
        String hash = HashUtil.calculateHash(file);
        Optional<ImageFile> imageFileOptional = imageFileRepository.findByHash(hash);
        ImageFileUploadResponseDTO imageFileUploadResponseDTO;
        if (imageFileOptional.isEmpty()) {
            imageFileUploadResponseDTO = minIOUtil.upload(hash, file);
            ImageFile imageFile = imageMapper.toEntity(imageFileUploadResponseDTO);
            imageFileRepository.save(imageFile);
        } else {
            imageFileUploadResponseDTO = imageMapper.toUploadResponseDTO(imageFileOptional.get());
        }
        return imageFileUploadResponseDTO;
    }

    // 获取图片文件
    @Transactional(readOnly = true)
    public ImageFileResponseDTO getImageFile(String hash) {
        ImageFile imageFile = imageFileRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageFileResponseDTO imageFileResponseDTO = imageMapper.toResponseDTO(imageFile);
        //获取图片文件临时URL
        imageFileResponseDTO.setUrl(minIOUtil.generatePresignedUrl(imageFile.getHash()));
        return imageFileResponseDTO;
    }

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(ImageRequestDTO imageRequestDTO) {
        // 创建实体
        Image image = imageMapper.toEntity(imageRequestDTO);
        // 保存实体
        image = imageRepository.save(image);
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
    public ImageResponseDTO addImageFile(String uuid, ImageFileRequestDTO imageFileRequestDTO) {
        //获取所需实体
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageFile imageFile = imageFileRepository.findByHash(imageFileRequestDTO.getHash())
                .orElseThrow(() -> new IllegalArgumentException("图片文件不存在"));
        //图集封面
        if (image.getImageFiles().isEmpty()) {
            image.setCoverHash(imageFile.getHash());
        }
        //设置图片文件标题
        image.setTitle(imageFileRequestDTO.getTitle());
        //添加图片文件到图片
        image.addImageFile(imageFile);
        //转换为响应DTO
        return imageMapper.toResponseDTO(image);
    }

    // 获取所有图片文件
    @Transactional(readOnly = true)
    public List<ImageFileResponseDTO> getAllImageFiles(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return image.getImageFiles()
                .stream()
                .map(imageFile -> getImageFile(imageFile.getHash()))
                .toList();
    }
}
