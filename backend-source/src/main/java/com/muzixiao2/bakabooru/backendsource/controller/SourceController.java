package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/source")
public class SourceController {

    public final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public ResponseResult<SourceInfo> getSourceInfo() {
        return ResponseResult.success(sourceService.getSourceInfo(), "获取图源信息成功");
    }
}
