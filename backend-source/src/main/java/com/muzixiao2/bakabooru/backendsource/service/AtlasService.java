package com.muzixiao2.bakabooru.backendsource.service;

import com.muzixiao2.bakabooru.backendsource.converter.AtlasConverter;
import com.muzixiao2.bakabooru.backendsource.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.backendsource.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.backendsource.model.Atlas;
import com.muzixiao2.bakabooru.backendsource.repository.AtlasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtlasService {

    @Autowired
    private AtlasRepository atlasRepository;

    // 给图源添加图集
    public Atlas createAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建Atlas对象
        Atlas atlas = AtlasConverter.toAtlas(atlasRequestDTO);

        //保存Atlas对象
        atlas = atlasRepository.save(atlas);

        return atlas;
    }

    // 获取所有图集
    public List<AtlasResponseDTO> getAtlases() {
        return atlasRepository
                .findAll()
                .stream()
                .map(AtlasConverter::toAtlasResponseDTO)
                .collect(Collectors.toList());
    }

}
