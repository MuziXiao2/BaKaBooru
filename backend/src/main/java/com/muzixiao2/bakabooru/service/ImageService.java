package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.image.ImageRequestDTO;
import com.muzixiao2.bakabooru.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.entity.Atlas;
import com.muzixiao2.bakabooru.entity.AtlasImage;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.repository.AtlasImageRepository;
import com.muzixiao2.bakabooru.repository.AtlasRepository;
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
    private final AtlasRepository atlasRepository;
    private final AtlasImageRepository atlasImageRepository;

    // 上传图片
    public ImageUploadResponseDTO uploadImage(MultipartFile imageFile) {
        String hash = HashUtil.calculateHash(imageFile);
        Optional<Image> imageOptional = imageRepository.findByHash(hash);
        ImageUploadResponseDTO imageUploadResponseDTO;
        if (imageOptional.isEmpty()) {
            imageUploadResponseDTO = minIOUtil.upload(hash, imageFile);
            Image image = imageMapper.toEntity(imageUploadResponseDTO);
            imageRepository.save(image);
        } else {
            imageUploadResponseDTO = imageMapper.toUploadResponseDTO(imageOptional.get());
        }
        return imageUploadResponseDTO;
    }

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(String uuid, ImageRequestDTO imageRequestDTO) {
        //获取所需实体
        Atlas atlas = atlasRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图集不存在"));
        Image image = imageRepository.findByHash(imageRequestDTO.getHash())
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        //图集封面
        if (atlasImageRepository.count() == 0) {
            atlas.setCoverHash(image.getHash());
            atlasRepository.save(atlas);
        }
        //创建图集图片关系
        AtlasImage atlasImage = new AtlasImage();
        atlasImage.setAtlasUuid(atlas.getUuid());
        atlasImage.setImageHash(image.getHash());
        atlasImage.setTitle(imageRequestDTO.getTitle());
        //保存图集图片关系
        atlasImageRepository.save(atlasImage);
        //转换为响应DTO
        ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
        imageResponseDTO.setUrl(minIOUtil.generatePresignedUrl(image.getHash()));
        imageResponseDTO.setTitle(atlasImage.getTitle());
        return imageResponseDTO;
    }

    // 获取图片
    @Transactional(readOnly = true)
    public ImageResponseDTO getImage(String hash) {
        Image image = imageRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
        imageResponseDTO.setUrl(minIOUtil.generatePresignedUrl(image.getHash()));
        return imageResponseDTO;
    }

    // 获取图片列表
    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getAllImages(String uuid) {
        Atlas atlas = atlasRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图集不存在"));
        List<AtlasImage> atlasImageList = atlasImageRepository.findAllByAtlasUuid(atlas.getUuid());
        return atlasImageList
                .stream()
                .map(atlasImage -> getImage(atlasImage.getImageHash()))
                .toList();
    }
}
