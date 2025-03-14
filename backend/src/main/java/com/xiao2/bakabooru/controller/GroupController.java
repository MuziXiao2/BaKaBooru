package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.GroupRequestDTO;
import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.dto.GroupResponseDTO;
import com.xiao2.bakabooru.model.Group;
import com.xiao2.bakabooru.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private SourceService sourceService;

    //添加组
    @PostMapping
    public ResponseResult<Group> addGroup(@RequestBody GroupRequestDTO groupRequestDTO) {
        Group group = sourceService.addGroup(groupRequestDTO);
        return ResponseResult.success(group, "添加组成功");
    }

    //获取所有组
    @GetMapping
    public List<GroupResponseDTO> getGroups() {
        return sourceService.getGroups();
    }
}
