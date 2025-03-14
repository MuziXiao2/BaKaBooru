package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.converter.AtlasConverter;
import com.xiao2.bakabooru.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.model.Atlas;
import com.xiao2.bakabooru.repository.AtlasRepository;
import com.xiao2.bakabooru.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtlasService {

    @Autowired
    private AtlasRepository atlasRepository;
    @Autowired
    private SourceRepository sourceRepository;

    // 给图源添加图集
    public Atlas addAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建Atlas对象
        Atlas atlas = AtlasConverter.toAtlas(atlasRequestDTO);

        //判断图源是否存在
        Long sourceId = atlas.getSourceId();
        if (!sourceRepository.existsById(sourceId)) throw new IllegalArgumentException("图源不存在");

        //保存Atlas对象
        atlas = atlasRepository.save(atlas);

        return atlas;
    }

    // 从图源获取所有图集
    public List<AtlasResponseDTO> getAtlases(Long sourceId) {
        return atlasRepository.findBySourceId(sourceId).stream().map(AtlasConverter::toAtlasResponseDTO).collect(Collectors.toList());
    }

}
