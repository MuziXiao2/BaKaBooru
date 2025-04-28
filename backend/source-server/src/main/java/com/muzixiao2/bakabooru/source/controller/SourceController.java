package com.muzixiao2.bakabooru.source.controller;

import com.muzixiao2.bakabooru.source.dto.*;
import com.muzixiao2.bakabooru.source.dto.response.SourceResponseDTO;
import com.muzixiao2.bakabooru.source.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/source")
@RequiredArgsConstructor
public class SourceController {
    private final SourceService sourceService;

    @GetMapping
    @Operation(summary = "获取图源信息", description = "获取当前source服务的基本信息，比如名称、版本、权限等")
    public ResponseResult<SourceResponseDTO> getSource() {
        return ResponseResult.success(sourceService.getSource(), "获取图源信息成功");
    }
}
