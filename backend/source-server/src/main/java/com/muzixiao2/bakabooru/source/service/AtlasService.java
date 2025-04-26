package com.muzixiao2.bakabooru.source.service;

import com.muzixiao2.bakabooru.source.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.source.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.source.dto.ImageRequestDTO;
import com.muzixiao2.bakabooru.source.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.source.entity.AtlasImage;
import com.muzixiao2.bakabooru.source.entity.Image;
import com.muzixiao2.bakabooru.source.mapper.AtlasMapper;
import com.muzixiao2.bakabooru.source.entity.Atlas;
import com.muzixiao2.bakabooru.source.mapper.ImageMapper;
import com.muzixiao2.bakabooru.source.repository.AtlasImageRepository;
import com.muzixiao2.bakabooru.source.repository.AtlasRepository;
import com.muzixiao2.bakabooru.source.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.muzixiao2.bakabooru.source.utils.MinIOUtil;

@Service
@RequiredArgsConstructor
public class AtlasService {
    private final MinIOUtil minIOUtil;
    private final AtlasMapper atlasMapper;
    private final ImageMapper imageMapper;
    private final AtlasRepository atlasRepository;
    private final AtlasImageRepository atlasImageRepository;
    private final ImageRepository imageRepository;

    // 添加图集
    public AtlasResponseDTO addAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建实体
        Atlas atlas = atlasMapper.toEntity(atlasRequestDTO);
        // 保存实体
        atlas = atlasRepository.save(atlas);
        // 返回响应DTO
        return atlasMapper.toResponseDTO(atlas);
    }

    // 添加图片
    public ImageResponseDTO addImage(String uuid, ImageRequestDTO imageRequestDTO) {
        //获取所需实体
        Atlas atlas = atlasRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图集不存在"));
        Image image = imageRepository.findByHash(imageRequestDTO.getHash())
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        //创建图集图片关系
        AtlasImage atlasImage = new AtlasImage();
        atlasImage.setAtlas(atlas);
        atlasImage.setImage(image);
        atlasImage.setTitle(imageRequestDTO.getTitle());
        //保存图集图片关系
        atlasImageRepository.save(atlasImage);
        //转换为响应DTO
        ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
        imageResponseDTO.setUrl(minIOUtil.generatePresignedUrl(image.getHash()));
        imageResponseDTO.setTitle(atlasImage.getTitle());
        return imageResponseDTO;
    }

    // 获取单个图集
    public AtlasResponseDTO getAtlas(String uuid) {
        //获取所需实体
        Atlas atlas = atlasRepository.findByUuid(uuid).orElse(null);
        if (atlas == null) throw new IllegalArgumentException("图集不存在");
        //转换为响应DTO
        return atlasMapper.toResponseDTO(atlas);
    }

    // 获取所有图集
    public List<AtlasResponseDTO> getAllAtlases(Instant updatedAfter) {
        //获取所需实体
        List<Atlas> atlasList = (updatedAfter == null) ? atlasRepository.findAll()//全量查询
                : atlasRepository.findAllByUpdatedAtAfterOrderByCreatedAtAsc(updatedAfter);// 增量查询
        //转换为响应DTO
        return atlasList.stream().map(atlasMapper::toResponseDTO).collect(Collectors.toList());
    }

    // 获取图片列表
    public List<ImageResponseDTO> getImages(String uuid) {
        Atlas atlas = atlasRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图集不存在"));
        List<AtlasImage> atlasImageList = atlasImageRepository.findAllByAtlas(atlas);
        return atlasImageList.stream().map(atlasImage -> {
            Image image = atlasImage.getImage();
            ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
            imageResponseDTO.setUrl(minIOUtil.generatePresignedUrl(image.getHash()));
            imageResponseDTO.setTitle(atlasImage.getTitle());
            return imageResponseDTO;
        }).toList();
    }
}
