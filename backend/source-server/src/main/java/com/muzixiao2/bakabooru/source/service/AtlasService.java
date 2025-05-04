package com.muzixiao2.bakabooru.source.service;

import com.muzixiao2.bakabooru.source.dto.request.AtlasRequestDTO;
import com.muzixiao2.bakabooru.source.dto.response.AtlasResponseDTO;
import com.muzixiao2.bakabooru.source.dto.request.ImageRequestDTO;
import com.muzixiao2.bakabooru.source.dto.response.ImageResponseDTO;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtlasService {
    private final AtlasMapper atlasMapper;
    private final AtlasRepository atlasRepository;

    // 添加图集
    @Transactional
    public AtlasResponseDTO addAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建实体
        Atlas atlas = atlasMapper.toEntity(atlasRequestDTO);
        // 保存实体
        atlas = atlasRepository.save(atlas);
        // 返回响应DTO
        return atlasMapper.toResponseDTO(atlas);
    }

    // 获取单个图集
    @Transactional(readOnly = true)
    public AtlasResponseDTO getAtlas(String uuid) {
        //获取所需实体
        Atlas atlas = atlasRepository.findByUuid(uuid).orElse(null);
        if (atlas == null) throw new IllegalArgumentException("图集不存在");
        //转换为响应DTO
        return atlasMapper.toResponseDTO(atlas);
    }

    // 获取所有图集
    @Transactional(readOnly = true)
    public List<AtlasResponseDTO> getAllAtlases() {
        //获取所需实体
        List<Atlas> atlasList = atlasRepository.findAll();
        //转换为响应DTO
        return atlasList.stream().map(atlasMapper::toResponseDTO).collect(Collectors.toList());
    }
}
