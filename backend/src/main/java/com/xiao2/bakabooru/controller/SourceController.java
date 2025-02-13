package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SourceController {
    @Autowired
    private SourceService sourceService;

    /*图源操作*/
    //添加图源
    @PostMapping("/api/sources")
    public ResponseResult<Source> addSource(@RequestBody SourceRequestDTO sourceRequestDTO) {
        Source source = sourceService.addSource(sourceRequestDTO);
        return ResponseResult.success(source, "添加图源成功");
    }

    //获取所有图源
    @GetMapping("/api/sources")
    public List<SourceResponseDTO> getAllSource() {
        return sourceService.getAllSource();
    }

    /*图集操作*/
    // 给图源添加图集
    @PostMapping("/api/atlases")
    public ResponseResult<Atlas> addAtlas(@RequestBody AtlasRequestDTO atlasRequestDTO) {
        Atlas atlas = sourceService.addAtlas(atlasRequestDTO);
        return ResponseResult.success(atlas, "添加图集成功");
    }

    // 从图源获取所有图集
    @GetMapping("/api/atlases/{source_id}")
    public List<AtlasResponseDTO> getAllAtlas(@PathVariable("source_id") Long sourceId) {
        return sourceService.getAllAtlas(sourceId);
    }

    /*图片操作*/
    // 给图集添加张图片
    @PostMapping("/api/images")
    public ResponseResult<Image> addImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        Image image = sourceService.addImage(imageRequestDTO);
        return ResponseResult.success(image, "添加图片成功");

    }

    // 从图集获取所有图片
    @GetMapping("/api/images/{atlas_id}")
    public List<ImageResponseDTO> getAllImage(@PathVariable("atlas_id") Long atlasId) {
        return sourceService.getAllImage(atlasId);
    }
}
