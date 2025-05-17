package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.config.SourceConfig;
import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.source.SourceResponseDTO;
import com.muzixiao2.bakabooru.mapper.SourceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "图源管理", description = "用于管理图像来源服务器的接口")
public class SourceController {
    private final SourceConfig sourceConfig;
    private final SourceMapper sourceMapper;

    @Operation(
            summary = "获取图源信息",
            description = "根据 ID 获取图源服务器的详细信息"
    )
    @GetMapping("/source")
    public ApiResponse<SourceResponseDTO> getSource() {
        SourceResponseDTO sourceResponseDTO = sourceMapper.toResponseDTO(sourceConfig);
        return ApiResponse.success(sourceResponseDTO);
    }
}
