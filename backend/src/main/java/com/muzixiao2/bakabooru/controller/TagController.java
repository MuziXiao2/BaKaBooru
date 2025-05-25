package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.file.FileDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.service.ImageService;
import com.muzixiao2.bakabooru.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            summary = "创建标签",
            description = "创建新的标签",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "标签引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TagRequestDTO.class))
            )
    )
    @PostMapping("/tag")
    public ApiResponse<TagDetailResponseDTO> addTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagDetailResponseDTO tagResponseDTO = tagService.createTag(tagRequestDTO);
        return ApiResponse.success(tagResponseDTO);
    }

    @Operation(
            summary = "为图片添加标签",
            description = "为图片添加标签"
    )
    @PostMapping(path = "/image/{uuid}/tag")
    public ApiResponse<TagDetailResponseDTO> addTag(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,

            @Parameter(description = "标签ID，格式为 type:name", required = true, example = "character:kana")
            @RequestParam("tagId") String tagId
    ) {
        TagDetailResponseDTO tagDetailResponseDTO = tagService.addTag(uuid, tagId);
        return ApiResponse.success(tagDetailResponseDTO);
    }

    @Operation(
            summary = "获取图片内所有标签",
            description = "获取图片内所有标签"
    )
    @GetMapping("/image/{uuid}/tag")
    public ApiResponse<List<TagDetailResponseDTO>> getTagDetails(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        List<TagDetailResponseDTO> tagDetailResponseDTOList = tagService.getTagDetails(uuid);
        return ApiResponse.success(tagDetailResponseDTOList);
    }
}
