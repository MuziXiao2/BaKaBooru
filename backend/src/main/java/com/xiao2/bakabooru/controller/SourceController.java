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
    public List<SourceResponseDTO> getAllSources() {
        return sourceService.getAllSource();
    }

    /*图集操作*/
    // 给图源添加图集
    @PostMapping("/api/sources/{source_id}")
    public ResponseResult<Atlas> addAtlas(@PathVariable("source_id") Long sourceId, @RequestBody AtlasRequestDTO atlasRequestDTO) {
        Atlas atlas = sourceService.addAtlas(sourceId, atlasRequestDTO);
        return ResponseResult.success(atlas, "添加图集成功");
    }

    // 从图源获取所有图集
    @GetMapping("/api/sources/{source_id}")
    public List<AtlasResponseDTO> getAllAtlases(@PathVariable("source_id") Long sourceId) {
        return sourceService.getAllAtlases(sourceId);
    }

    /*图片操作*/
    // 给图集添加张图片
    @PostMapping("/api/sources/{source_id}/{atlas_id}")
    public ResponseResult<Image> addImage(@PathVariable("atlas_id") Long atlasId, @RequestBody ImageRequestDTO imageRequestDTO) {
        Image image = sourceService.addImage(atlasId, imageRequestDTO);
        return ResponseResult.success(image, "添加图片成功");

    }

    // 从图集获取所有图片
    @GetMapping("/api/sources/{source_name}/{atlas_id}")
    public List<ImageResponseDTO> getAllImages(@PathVariable("atlas_id") Long atlasId) {
        return sourceService.getAllImages(atlasId);
    }
}
