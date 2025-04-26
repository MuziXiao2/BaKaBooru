package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/atlases")
@RequiredArgsConstructor
public class AtlasController {
    private final AtlasService atlasService;

    @PostMapping
    @Operation(summary = "添加图集", description = "根据请求内容新增一个图集记录")
    public ResponseResult<AtlasResponseDTO> addAtlas(
            @RequestBody AtlasRequestDTO atlasRequestDTO
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.addAtlas(atlasRequestDTO);
        return ResponseResult.success(atlasResponseDTO, "添加图集成功");
    }

    @PostMapping("/{uuid}")
    @Operation(summary = "向图集添加图片", description = "给指定UUID的图集添加一张图片记录")
    public ResponseResult<ImageResponseDTO> addImage(
            @Parameter(description = "图集的UUID", required = true)
            @PathVariable String uuid,
            @RequestParam ImageRequestDTO imageRequestDTO
    ) {
        ImageResponseDTO imageResponseDTO = atlasService.addImage(uuid, imageRequestDTO);
        return ResponseResult.success(imageResponseDTO, "添加图片成功");
    }

    @GetMapping
    @Operation(summary = "获取所有图集", description = "可选按更新时间过滤，返回图集列表")
    public ResponseResult<List<AtlasResponseDTO>> getAllAtlases(
            @Parameter(description = "只获取更新时间在此之后的图集")
            @RequestParam(value = "updatedAfter", required = false)
            Instant updatedAfter
    ) {
        List<AtlasResponseDTO> atlasResponseDTOList = atlasService.getAllAtlases(updatedAfter);
        return ResponseResult.success(atlasResponseDTOList, "获取图集列表成功");
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "获取单个图集详情", description = "根据图集UUID获取其详细信息")
    public ResponseResult<AtlasResponseDTO> getAtlas(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable String uuid
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.getAtlas(uuid);
        return ResponseResult.success(atlasResponseDTO, "获取图集详情成功");
    }

    @GetMapping("/{uuid}/images")
    @Operation(summary = "获取图集下所有图片", description = "根据图集UUID，查询该图集中包含的所有图片")
    public ResponseResult<List<ImageResponseDTO>> getImages(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable String uuid
    ) {
        List<ImageResponseDTO> imageResponseDTOList = atlasService.getImages(uuid);
        return ResponseResult.success(imageResponseDTOList, "获取图片列表成功");
    }
}
