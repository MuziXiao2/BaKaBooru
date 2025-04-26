package com.xiao2.bakabooru.hub.controller;

import com.xiao2.bakabooru.hub.service.GroupService;
import com.xiao2.bakabooru.hub.dto.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    /**
     * 获取所有分组
     */
    @GetMapping
    public ResponseResult<?> getAllGroups() {
        return ResponseResult.success(groupService.getAllGroups(), "获取组成功");
    }
}