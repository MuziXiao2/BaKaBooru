package com.xiao2.bakabooru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.service.AtlasService;
import com.xiao2.bakabooru.service.GroupService;
import com.xiao2.bakabooru.service.ImageService;
import com.xiao2.bakabooru.service.SourceService;
import com.xiao2.bakabooru.utils.MinIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private AtlasService atlasService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ObjectMapper objectMapper;

    // POST 请求
    @PostMapping
    public ResponseResult<?> postApi(
            @RequestParam("type") String type,
            @RequestBody(required = false) Object requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("请求体不能为空");
        }
        try {
            return switch (type) {
                case "group" -> {
                    GroupRequestDTO groupRequestDTO = objectMapper.convertValue(requestDTO, GroupRequestDTO.class);
                    Group group = groupService.createGroup(groupRequestDTO);
                    yield ResponseResult.success(group, "创建组成功");
                }
                case "source" -> {
                    SourceRequestDTO sourceRequestDTO = objectMapper.convertValue(requestDTO, SourceRequestDTO.class);
                    Source source = sourceService.addSource(sourceRequestDTO);
                    yield ResponseResult.success(source, "添加图源成功");
                }
                case "atlas" -> {
                    AtlasRequestDTO atlasRequestDTO = objectMapper.convertValue(requestDTO, AtlasRequestDTO.class);
                    Atlas atlas = atlasService.addAtlas(atlasRequestDTO);
                    yield ResponseResult.success(atlas, "添加图集成功");
                }
                case "image" -> {
                    ImageRequestDTO imageRequestDTO = objectMapper.convertValue(requestDTO, ImageRequestDTO.class);
                    Image image = imageService.addImage(imageRequestDTO);
                    yield ResponseResult.success(image, "添加图片成功");
                }
                default -> throw new IllegalArgumentException("无效的 type 参数");
            };
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("请求体解析失败: " + e.getMessage(), e);
        }
    }

    // GET 请求（保持不变）
    @GetMapping
    public List<?> getApi(
            @RequestParam("type") String type,
            @RequestParam(value = "group_id", required = false) Long groupId,
            @RequestParam(value = "source_id", required = false) Long sourceId,
            @RequestParam(value = "atlas_id", required = false) Long atlasId) {
        return switch (type) {
            case "group" -> groupService.getGroups();
            case "source" -> sourceService.getSources(groupId);
            case "atlas" -> atlasService.getAtlases(sourceId);
            case "image" -> imageService.getImages(atlasId);
            default -> throw new IllegalArgumentException("无效的 type 参数");
        };
    }

    // 上传图片
    @PostMapping(params = "type=upload")
    public UploadResponseDTO uploadImage(@RequestParam("file") MultipartFile file) {
        return MinIOUtil.upload(file);
    }
}