package com.muzixiao2.bakabooru.backendsource.service;

import com.muzixiao2.bakabooru.backendsource.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.backendsource.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.backendsource.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.AtlasImage;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import com.muzixiao2.bakabooru.backendsource.mapper.AtlasMapper;
import com.muzixiao2.bakabooru.backendsource.entity.Atlas;
import com.muzixiao2.bakabooru.backendsource.mapper.ImageMapper;
import com.muzixiao2.bakabooru.backendsource.repository.AtlasImageRepository;
import com.muzixiao2.bakabooru.backendsource.repository.AtlasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.muzixiao2.bakabooru.backendsource.utils.MinIOUtil;

@Service
@RequiredArgsConstructor
public class AtlasService {
    private final MinIOUtil minIOUtil;
    private final AtlasMapper atlasMapper;
    private final ImageMapper imageMapper;
    private final AtlasRepository atlasRepository;
    private final AtlasImageRepository atlasImageRepository;

    // 添加图集
    public AtlasResponseDTO addAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建实体
        Atlas atlas = atlasMapper.toEntity(atlasRequestDTO);
        // 保存实体
        atlas = atlasRepository.save(atlas);
        // 返回响应DTO
        return atlasMapper.toResponseDTO(atlas);
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
                : atlasRepository.findAllByUpdatedAtAfterOrderBySnAsc(updatedAfter);// 增量查询
        //转换为响应DTO
        return atlasList.stream().map(atlasMapper::toResponseDTO).collect(Collectors.toList());
    }

    // 获取图片列表
    public List<ImageResponseDTO> getImages(String uuid) {
        Atlas atlas = atlasRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图集不存在"));
        List<AtlasImage> atlasImageList = atlasImageRepository.findAllByAtlasOrderBySortOrderAsc(atlas);
        return atlasImageList.stream().map(atlasImage -> {
            Image image = atlasImage.getImage();
            ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
            imageResponseDTO.setUrl(minIOUtil.generatePresignedUrl(image.getHash()));
            imageResponseDTO.setTitle(atlasImage.getTitle());
            return imageResponseDTO;
        }).toList();
    }
}
