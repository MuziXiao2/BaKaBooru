package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.source.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.service.SourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
@RequiredArgsConstructor
@Tag(name = "图源管理", description = "用于管理图像来源服务器的接口")
public class SourceController {

    private final SourceService sourceService;

    @Operation(
            summary = "添加图源",
            description = "注册一个新的图源服务器",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图源引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SourceReferenceDTO.class))
            )
    )
    @PostMapping
    public ApiResponse<SourceResponseDTO> addSource(@RequestBody SourceReferenceDTO sourceReferenceDTO) {
        SourceResponseDTO sourceResponseDTO = sourceService.addSource(sourceReferenceDTO);
        return ApiResponse.success(sourceResponseDTO);
    }

    @Operation(
            summary = "获取图源信息",
            description = "根据 ID 获取图源服务器的详细信息"
    )
    @GetMapping("/{id}")
    public ApiResponse<SourceResponseDTO> getSource(@PathVariable("id") Long id) {
        SourceResponseDTO sourceResponseDTO = sourceService.getSource(id);
        return ApiResponse.success(sourceResponseDTO);
    }

    @Operation(
            summary = "获取所有图源",
            description = "列出系统中所有注册的图源服务器"
    )
    @GetMapping
    public ApiResponse<List<SourceResponseDTO>> getAllSources() {
        List<SourceResponseDTO> sourceResponseDTOList = sourceService.getAllSources();
        return ApiResponse.success(sourceResponseDTOList);
    }
}
