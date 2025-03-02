package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.dto.SourceGroupRequestDTO;
import com.xiao2.bakabooru.dto.SourceGroupResponseDTO;
import com.xiao2.bakabooru.model.SourceGroup;
import com.xiao2.bakabooru.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class SourceGroupController {
    @Autowired
    private SourceService sourceService;

    //添加组
    @PostMapping
    public ResponseResult<SourceGroup> addSourceGroup(@RequestBody SourceGroupRequestDTO sourceGroupRequestDTO) {
        SourceGroup sourceGroup = sourceService.addSourceGroup(sourceGroupRequestDTO);
        return ResponseResult.success(sourceGroup, "添加组成功");
    }

    //获取所有组
    @GetMapping
    public List<SourceGroupResponseDTO> getSourceGroups() {
        return sourceService.getSourceGroups();
    }
}
