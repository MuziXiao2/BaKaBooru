package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.atlas.AtlasRequestDTO;
import com.muzixiao2.bakabooru.dto.atlas.AtlasResponseDTO;
import com.muzixiao2.bakabooru.service.AtlasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "图集管理", description = "用于管理图集的接口")
public class AtlasController {
    private final AtlasService atlasService;

    @Operation(
            summary = "添加图集",
            description = "添加新的图集",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图集引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AtlasRequestDTO.class))
            )
    )
    @PostMapping("/atlas")
    public ApiResponse<AtlasResponseDTO> addAtlas(
            @Parameter(description = "图源ID", required = true)
            @RequestBody AtlasRequestDTO atlasRequestDTO
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.addAtlas(atlasRequestDTO);
        return ApiResponse.success(atlasResponseDTO);
    }

    @Operation(
            summary = "获取单个图集",
            description = "获取该图源下的单个图集"
    )
    @GetMapping("/atlas/{atlasUuid}")
    public ApiResponse<AtlasResponseDTO> getAtlas(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.getAtlas(atlasUuid);
        return ApiResponse.success(atlasResponseDTO);
    }

    @Operation(
            summary = "获取所有图集",
            description = "获取该图源的所有图集"
    )
    @GetMapping("/atlas")
    public ApiResponse<List<AtlasResponseDTO>> getAllAtlases() {
        List<AtlasResponseDTO> atlasResponseDTOList = atlasService.getAllAtlases();
        return ApiResponse.success(atlasResponseDTOList);
    }
}
