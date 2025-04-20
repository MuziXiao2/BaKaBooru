package com.muzixiao2.bakabooru.backendsource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.model.Atlas;
import com.muzixiao2.bakabooru.backendsource.model.Image;
import com.muzixiao2.bakabooru.backendsource.service.*;
import com.muzixiao2.bakabooru.backendsource.utils.MinIOUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AtlasService atlasService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SourceService sourceService;

    // GET请求
    @GetMapping
    public ResponseResult<?> getApi(
            @RequestParam("type") String type,
            @RequestParam(value = "atlas_id", required = false) Long atlasId) {
        return switch (type) {
            case "source" -> {
                SourceInfo sourceInfo = sourceService.getSourceInfo();
                yield ResponseResult.success(sourceInfo, "获取图源信息成功");
            }
            case "atlas" -> {
                List<AtlasResponseDTO> atlases = atlasService.getAtlases();
                yield ResponseResult.success(atlases, "获取图集列表成功");
            }
            case "image" -> {
                List<ImageResponseDTO> images = imageService.getImages(atlasId);
                yield ResponseResult.success(images, "获取图片列表成功");
            }
            default -> throw new IllegalArgumentException("无效的 type 参数");
        };
    }


    // POST请求
    @PostMapping
    public ResponseResult<?> postApi(
            @RequestParam("type") String type,
            @RequestBody(required = false) Object requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("请求体不能为空");
        }
        try {
            return switch (type) {
                case "atlas" -> {
                    AtlasRequestDTO atlasRequestDTO = objectMapper.convertValue(requestDTO, AtlasRequestDTO.class);
                    Atlas atlas = atlasService.createAtlas(atlasRequestDTO);
                    yield ResponseResult.success(atlas, "创建图集成功");
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

    // 上传图片文件
    @PostMapping(params = "type=upload")
    public UploadResponseDTO uploadImage(@RequestParam("file") MultipartFile file) {
        return MinIOUtil.upload(file);
    }

}