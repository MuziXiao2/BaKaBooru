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
        Source source = new Source(sourceRequestDTO);
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
        Atlas atlas = new Atlas(atlasRequestDTO);

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
        Image image = new Image(imageRequestDTO);

        // 获取Image的position (!!!高并发下可能出问题!!!)
        Long atlasId = imageRequestDTO.getAtlasId();
        Long position = imageRepository.countByAtlasId(atlasId);
        image.setPosition(position);

        // 保存Image对象
        image = imageRepository.save(image);

        return image;
    }

    // 从图集获取所有图片
    public List<ImageResponseDTO> getAllImage(Long atlasId) {
        return imageRepository
                .findByAtlasIdOrderByPositionAsc(atlasId)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

}