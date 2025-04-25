package com.xiao2.bakabooru.backenddata.controller;

import com.xiao2.bakabooru.backenddata.service.GroupService;
import com.xiao2.bakabooru.backenddata.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 获取所有分组
     */
    @GetMapping
    public ResponseResult<?> getAllGroups() {
        return ResponseResult.success(groupService.getAllGroups(), "获取组成功");
    }
}