package com.xiao2.bakabooru.backenddata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.*;
import com.xiao2.bakabooru.backenddata.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private ObjectMapper objectMapper;

    // POST 请求
    @PostMapping
    public ResponseResult<?> postApi(
            @RequestParam("type") String type,
            @RequestParam(value = "action", required = false, defaultValue = "create") String action,
            @RequestBody(required = false) Object requestDTO) {

        // 判断请求体是否为空
        if (requestDTO == null) throw new IllegalArgumentException("请求体不能为空");

        try {
            return switch (type) {
                case "group" -> {// 组操作
                    GroupRequestDTO groupRequestDTO = objectMapper.convertValue(requestDTO, GroupRequestDTO.class);
                    Group group = groupService.createGroup(groupRequestDTO);
                    yield ResponseResult.success(group, "创建组成功");
                }
                case "source" -> {// 图源操作
                    if ("create".equals(action)) {// 创建图源
                        SourceRequestDTO sourceRequestDTO = objectMapper.convertValue(requestDTO, SourceRequestDTO.class);
                        Source source = sourceService.createSource(sourceRequestDTO);
                        yield ResponseResult.success(source, "创建图源成功");
                    } else if ("add".equals(action)) {// 添加图源
                        SourceReferenceDTO sourceReferenceDTO = objectMapper.convertValue(requestDTO, SourceReferenceDTO.class);
                        Source source = sourceService.addSource(sourceReferenceDTO);
                        yield ResponseResult.success(source, "添加图源成功");
                    } else {
                        throw new IllegalArgumentException("无效的 action 参数");
                    }
                }
                default -> throw new IllegalArgumentException("无效的 type 参数");
            };
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("请求体解析失败: " + e.getMessage(), e);
        }
    }

    // GET 请求
    @GetMapping
    public List<?> getApi(
            @RequestParam("type") String type,
            @RequestParam(value = "group_id", required = false) Long groupId) {
        return switch (type) {
            case "group" -> groupService.getGroups();
            case "source" -> sourceService.getSources(groupId);
            default -> throw new IllegalArgumentException("无效的 type 参数");
        };
    }
}