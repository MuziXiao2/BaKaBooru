package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasResponseDTO;
import com.muzixiao2.bakabooru.hub.service.AtlasService;
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
                    content = @Content(schema = @Schema(implementation = AtlasReferenceDTO.class))
            )
    )
    @PostMapping("/source/{sourceUuid}")
    public ApiResponse<AtlasResponseDTO> addAtlas(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @RequestBody AtlasReferenceDTO atlasReferenceDTO
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.addAtlas(sourceUuid, atlasReferenceDTO);
        return ApiResponse.success(atlasResponseDTO);
    }

    @Operation(
            summary = "获取单个图集",
            description = "获取该图源下的单个图集"
    )
    @GetMapping("/source/{sourceUuid}/atlas/{atlasUuid}")
    public ApiResponse<AtlasResponseDTO> getAtlas(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid
    ) {
        AtlasResponseDTO atlasResponseDTO = atlasService.getAtlas(sourceUuid, atlasUuid);
        return ApiResponse.success(atlasResponseDTO);
    }

    @Operation(
            summary = "获取所有图集",
            description = "获取该图源的所有图集"
    )
    @GetMapping("/source/{sourceUuid}/atlas")
    public ApiResponse<List<AtlasResponseDTO>> getAllAtlases(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid
    ) {
        List<AtlasResponseDTO> atlasResponseDTOList = atlasService.getAllAtlases(sourceUuid);
        return ApiResponse.success(atlasResponseDTOList);
    }
}
