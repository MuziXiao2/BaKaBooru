package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Tag(name = "标签管理", description = "用于管理标签的接口")
public class TagController {
    private final TagService tagService;

    @Operation(
            summary = "添加标签",
            description = "添加新的标签",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "标签引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TagRequestDTO.class))
            )
    )
    @PostMapping
    public ApiResponse<TagDetailResponseDTO> addTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagDetailResponseDTO tagResponseDTO = tagService.addTag(tagRequestDTO);
        return ApiResponse.success(tagResponseDTO);
    }
}
