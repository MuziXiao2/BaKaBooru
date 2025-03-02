package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.SourceGroupRequestDTO;
import com.xiao2.bakabooru.dto.SourceGroupResponseDTO;
import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.model.SourceGroup;
import com.xiao2.bakabooru.service.SourceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/source-groups")
public class SourceGroupController {
    @Autowired
    private SourceGroupService sourceGroupService;

    /*组操作*/
    //添加组
    @PostMapping
    public ResponseResult<SourceGroup> addGroup(@RequestBody SourceGroupRequestDTO sourceGroupRequestDTO) {
        SourceGroup sourceGroup = sourceGroupService.addSourceGroup(sourceGroupRequestDTO);
        return ResponseResult.success(sourceGroup, "添加组成功");
    }

    //获取所有组
    @GetMapping
    public List<SourceGroupResponseDTO> getAllGroup() {
        return sourceGroupService.getAllSourceGroup();
    }
}
