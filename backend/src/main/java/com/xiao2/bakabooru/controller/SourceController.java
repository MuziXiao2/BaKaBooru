package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    //添加图源
    @PostMapping
    public ResponseResult<Source> addSource(@RequestBody SourceRequestDTO sourceRequestDTO) {
        Source source = sourceService.addSource(sourceRequestDTO);
        return ResponseResult.success(source, "添加图源成功");
    }

    //获取所有图源
    @GetMapping
    public List<SourceResponseDTO> getSources(@RequestParam Long groupId) {
        return sourceService.getSources(groupId);
    }

}
