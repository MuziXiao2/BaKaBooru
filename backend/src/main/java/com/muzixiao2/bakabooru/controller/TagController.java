package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.tag.ImageTagResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.ImageTagDetailResponseDTO;
import com.muzixiao2.bakabooru.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path = "/image/{uuid}/tag")
    public ApiResponse<ImageTagDetailResponseDTO> addTag(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,
            @Parameter(description = "标签名", required = true, example = "kana")
            @RequestParam("tagName") String tagName,
            @Parameter(description = "标签类型", required = true, example = "character")
            @RequestParam("tagType") String tagType
    ) {
        ImageTagDetailResponseDTO tagDetailResponseDTO = tagService.addTag(uuid, tagName, tagType);
        return ApiResponse.success(tagDetailResponseDTO);
    }

    @Operation(
            summary = "获取图片内所有标签",
            description = "获取图片内所有标签"
    )
    @GetMapping("/image/{uuid}/tag")
    public ApiResponse<ImageTagResponseDTO> getTags(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        ImageTagResponseDTO imageTagResponseDTO = tagService.getTags(uuid);
        return ApiResponse.success(imageTagResponseDTO);
    }
}
