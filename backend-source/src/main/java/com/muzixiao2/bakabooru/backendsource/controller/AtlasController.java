package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.service.*;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/atlases")
@RequiredArgsConstructor
public class AtlasController {
    private final AtlasService atlasService;

    //添加图集
    @PostMapping
    public ResponseResult<AtlasResponseDTO> addAtlas(@RequestBody AtlasRequestDTO atlasRequestDTO) {
        AtlasResponseDTO atlasResponseDTO = atlasService.addAtlas(atlasRequestDTO);
        return ResponseResult.success(atlasResponseDTO, "添加图集成功");
    }

    //获取所有图集
    @GetMapping
    public ResponseResult<List<AtlasResponseDTO>> getAllAtlases(
            @RequestParam(value = "updatedAfter", required = false)
            Instant updatedAfter
    ) {
        List<AtlasResponseDTO> atlasResponseDTOList = atlasService.getAllAtlases(updatedAfter);
        return ResponseResult.success(atlasResponseDTOList, "获取图集列表成功");
    }

    //获取单个图集
    @GetMapping("/{uuid}")
    public ResponseResult<AtlasResponseDTO> getAtlas(@PathVariable String uuid) {
        AtlasResponseDTO atlasResponseDTO = atlasService.getAtlas(uuid);
        return ResponseResult.success(atlasResponseDTO, "获取图集列表成功");
    }

    //获取图片列表
    @GetMapping("/{uuid}/images")
    public ResponseResult<List<ImageResponseDTO>> getImages(@PathVariable String uuid) {
        List<ImageResponseDTO> imageResponseDTOList = atlasService.getImages(uuid);
        return ResponseResult.success(imageResponseDTOList, "获取图片列表成功");
    }
}
