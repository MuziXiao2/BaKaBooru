package com.muzixiao2.bakabooru.source.controller;

import com.muzixiao2.bakabooru.source.dto.*;
import com.muzixiao2.bakabooru.source.dto.response.SourceResponseDTO;
import com.muzixiao2.bakabooru.source.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/source")
@RequiredArgsConstructor
@Tag(name = "图源管理", description = "用于管理图像来源服务器的接口")
public class SourceController {
    private final SourceService sourceService;

    @GetMapping
    @Operation(summary = "获取图源信息", description = "获取当前source服务的基本信息，比如名称、版本、权限等")
    public ApiResponse<SourceResponseDTO> getSource() {
        SourceResponseDTO sourceResponseDTO = sourceService.getSource();
        return ApiResponse.success(sourceResponseDTO);
    }
}
