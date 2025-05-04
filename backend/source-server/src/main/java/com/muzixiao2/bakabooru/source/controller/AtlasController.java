package com.muzixiao2.bakabooru.source.controller;

import com.muzixiao2.bakabooru.source.dto.*;
import com.muzixiao2.bakabooru.source.dto.request.AtlasRequestDTO;
import com.muzixiao2.bakabooru.source.dto.request.ImageRequestDTO;
import com.muzixiao2.bakabooru.source.dto.response.AtlasResponseDTO;
import com.muzixiao2.bakabooru.source.dto.response.ImageResponseDTO;
import com.muzixiao2.bakabooru.source.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/atlas")
@RequiredArgsConstructor
@Tag(name = "图集管理", description = "用于管理图集的接口")
public class AtlasController {
    private final AtlasService atlasService;

    @PostMapping
    @Operation(summary = "添加图集", description = "根据请求内容新增一个图集记录")
    public ApiResponse<AtlasResponseDTO> addAtlas(
            @RequestBody AtlasRequestDTO atlasRequestDTO
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.addAtlas(atlasRequestDTO);
        return ApiResponse.success(atlasResponseDTO);
    }

    @GetMapping
    @Operation(summary = "获取所有图集", description = "可选按更新时间过滤，返回图集列表")
    public ApiResponse<List<AtlasResponseDTO>> getAllAtlases() {
        List<AtlasResponseDTO> atlasResponseDTOList = atlasService.getAllAtlases();
        return ApiResponse.success(atlasResponseDTOList);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "获取单个图集详情", description = "根据图集UUID获取其详细信息")
    public ApiResponse<AtlasResponseDTO> getAtlas(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.getAtlas(uuid);
        return ApiResponse.success(atlasResponseDTO);
    }

}
