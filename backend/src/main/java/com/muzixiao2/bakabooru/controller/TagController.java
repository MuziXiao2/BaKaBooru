package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "标签管理", description = "用于管理标签的接口")
public class TagController {
    private final TagService tagService;

    @Operation(
            summary = "为图片添加标签",
            description = "为图片添加标签"
    )
    @PostMapping("/image/{uuid}/tag")
    public ApiResponse<List<String>> addTags(
            @PathVariable("uuid") String uuid,
            @RequestBody String tag
    ) {
        List<String> tagDetailResponseDTO = tagService.addTag(uuid, tag);
        return ApiResponse.success(tagDetailResponseDTO);
    }

    @Operation(
            summary = "为图片更新标签",
            description = "为图片更新标签"
    )
    @PutMapping("/image/{uuid}/tag")
    public ApiResponse<List<String>> updateTags(
            @PathVariable("uuid") String uuid,
            @RequestBody List<String> tags
    ) {
        List<String> tagDetailResponseDTO = tagService.updateTags(uuid, tags);
        return ApiResponse.success(tagDetailResponseDTO);
    }

    @Operation(
            summary = "获取图片内所有标签",
            description = "获取图片内所有标签"
    )
    @GetMapping("/image/{uuid}/tag")
    public ApiResponse<List<String>> getTags(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        List<String> tagDetailResponseDTO = tagService.getTags(uuid);
        return ApiResponse.success(tagDetailResponseDTO);
    }
}
