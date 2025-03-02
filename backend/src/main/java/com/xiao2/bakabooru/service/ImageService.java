package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.converter.ImageConverter;
import com.xiao2.bakabooru.dto.ImageRequestDTO;
import com.xiao2.bakabooru.dto.ImageResponseDTO;
import com.xiao2.bakabooru.model.Image;
import com.xiao2.bakabooru.repository.AtlasRepository;
import com.xiao2.bakabooru.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AtlasRepository atlasRepository;

    // 给图集添加张图片
    public Image addImage(ImageRequestDTO imageRequestDTO) {
        // 创建Image对象
        Image image = ImageConverter.toImage(imageRequestDTO);

        //判断图集是否存在
        Long atlasId = image.getAtlasId();
        if (!atlasRepository.existsById(atlasId))
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
                .map(ImageConverter::toImageResponseDTO)
                .collect(Collectors.toList());
    }
}
