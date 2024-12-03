package com.bakabooru.controller;

import com.bakabooru.pojo.ImageDTO;
import com.bakabooru.pojo.ImageVO;
import com.bakabooru.service.GalleryService;
import com.bakabooru.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gallery")
@Tag(name = "GalleryController", description = "图库管理")
public class GalleryController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private GalleryService galleryService;

    @Operation(
            summary = "获取画廊中的图片",
            description = "获取指定画廊中的所有图片",
            tags = {"Gallery Operations"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功获取图片列表", content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ImageVO.class))
                    )),
                    @ApiResponse(responseCode = "404", description = "画廊不存在", content = @Content)
            }
    )
    @GetMapping("/{gallery_name}")
    public List<ImageVO> getImages(@PathVariable("gallery_name") String galleryName) {
        return galleryService.getImages(galleryName);
    }

    @Operation(
            summary = "添加单张图片",
            description = "将单张图片的名称和URL添加到图库内",
            tags = {"Image Upload"},
            requestBody = @RequestBody(
                    description = "包含图片名称和URL的表单数据",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = ImageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "图片上传成功", content = @Content),
                    @ApiResponse(responseCode = "400", description = "请求无效", content = @Content),
                    @ApiResponse(responseCode = "500", description = "服务器内部错误", content = @Content)
            }
    )
    @PostMapping(value = "/{gallery_name}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addImage(@ModelAttribute ImageDTO imageDTO,
                                         @PathVariable("gallery_name") String galleryName) {
        try {
            imageService.addImage(imageDTO, galleryName);
            log.info("图片\"{}\"添加成功.", imageDTO.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("图片上传失败: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(
            summary = "删除图片",
            description = "根据图片ID从画廊中删除图片",
            tags = {"Image Operations"},
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "ids",
                            description = "要删除的图片ID数组",
                            in = ParameterIn.QUERY,
                            required = true,
                            schema = @Schema(type = "array", implementation = Integer.class)
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "图片删除成功", content = @Content),
                    @ApiResponse(responseCode = "404", description = "图片未找到", content = @Content),
                    @ApiResponse(responseCode = "500", description = "服务器内部错误", content = @Content)
            }
    )
    @DeleteMapping("/{gallery_name}")
    public ResponseEntity<Void> deleteImages(@RequestParam int[] ids) {
        boolean isDeleted = true;

        for (int id : ids)
            isDeleted &= imageService.deleteImage(id);

        return (isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
