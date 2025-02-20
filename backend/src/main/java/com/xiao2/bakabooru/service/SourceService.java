package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {
    /*图源操作*/
    @Autowired
    private SourceRepository sourceRepository;

    //添加图源
    public Source addSource(SourceRequestDTO sourceRequestDTO) {
        // 创建Source对象
        Source source = sourceRequestDTO.toSource();
        return sourceRepository.save(source);
    }

    //获取所有图源
    public List<SourceResponseDTO> getAllSource() {
        List<Source> sources = sourceRepository.findAll();
        return sources
                .stream()
                .map(SourceResponseDTO::new)
                .collect(Collectors.toList());
    }

    /*图集操作*/
    @Autowired
    private AtlasRepository atlasRepository;

    // 给图源添加图集

    public Atlas addAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建Atlas对象
        Atlas atlas = atlasRequestDTO.toAtlas();

        //判断图源是否存在
        Long sourceId = atlas.getSourceId();
        if (!sourceRepository.existsById(sourceId))
            throw new IllegalArgumentException("图源不存在");

        //保存Atlas对象
        atlas = atlasRepository.save(atlas);

        return atlas;
    }

    // 从图源获取所有图集
    public List<AtlasResponseDTO> getAllAtlas(Long sourceId) {
        return atlasRepository
                .findBySourceId(sourceId)
                .stream()
                .map(AtlasResponseDTO::new)
                .collect(Collectors.toList());
    }

    /*图片操作*/
    @Autowired
    private ImageRepository imageRepository;

    // 给图集添加张图片
    public Image addImage(ImageRequestDTO imageRequestDTO) {
        // 创建Image对象
        Image image = imageRequestDTO.toImage();

        //判断图集是否存在
        Long atlasId = image.getAtlasId();
        if (atlasRepository.existsById(atlasId))
            throw new IllegalArgumentException("图集不存在");

        // 获取Image的sn (!!!高并发下可能出问题!!!)
        Long sn = imageRepository.countByAtlasId(atlasId);
        image.setSn(sn);

        // 保存Image对象
        image = imageRepository.save(image);

        return image;
    }

    // 从图集获取所有图片
    public List<ImageResponseDTO> getAllImage(Long atlasId) {
        return imageRepository
                .findByAtlasIdOrderBySnAsc(atlasId)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

}