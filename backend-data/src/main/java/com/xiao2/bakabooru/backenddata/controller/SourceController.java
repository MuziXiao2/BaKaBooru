package com.xiao2.bakabooru.backenddata.controller;

import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.entity.Source;
import com.xiao2.bakabooru.backenddata.mapper.SourceMapper;
import com.xiao2.bakabooru.backenddata.service.SourceService;
import com.xiao2.bakabooru.backenddata.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    /**
     * 创建图源
     */
    @PostMapping
    public ResponseResult<SourceResponseDTO> createSource(@RequestBody SourceRequestDTO requestDTO) {
        SourceResponseDTO sourceResponseDTO = sourceService.createSource(requestDTO);
        return ResponseResult.success(sourceResponseDTO, "创建图源成功");
    }

    /**
     * 添加图源
     */
    @PostMapping("/add")
    public ResponseResult<SourceResponseDTO> addSource(@RequestBody SourceReferenceDTO requestDTO) {
        SourceResponseDTO sourceResponseDTO = sourceService.addSource(requestDTO);
        return ResponseResult.success(sourceResponseDTO, "添加图源成功");
    }

    /**
     * 获取所有图源
     */
    @GetMapping
    public ResponseResult<List<SourceResponseDTO>> getAllSources() {
        List<SourceResponseDTO> sourceResponseDTOList = sourceService.getAllSource();
        return ResponseResult.success(sourceResponseDTOList, "获取图源成功");
    }
}
