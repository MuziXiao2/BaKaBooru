package com.bakabooru.controller;

import com.bakabooru.pojo.ImageVO;
import com.bakabooru.pojo.UploadImageDTO;
import com.bakabooru.service.GalleryService;
import com.bakabooru.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gallery/{gallery_name}")
@OpenAPIDefinition(
        info = @Info(
                title = "Gallery API",
                version = "1.0",
                description = "APIs for managing image galleries",
                termsOfService = "http://example.com/terms",
                contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Support", email = "support@example.com")
        )
)
@Tag(name = "Gallery", description = "Operations related to image galleries")
public class GalleryController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private GalleryService galleryService;

    @Operation(summary = "Get images in a gallery", description = "Retrieve all images in a specified gallery.")
    @GetMapping
    public List<ImageVO> getImages(@PathVariable("gallery_name") String galleryName) {
        return galleryService.getImages(galleryName);
    }

    @Operation(summary = "Upload images to a gallery", description = "Upload one or more images to the specified gallery.")
    @PostMapping
    public ResponseEntity uploadImages(
            @Parameter(description = "List of images to upload") @RequestParam UploadImageDTO[] images,
            @PathVariable("gallery_name") String galleryName) throws IOException {

        for (UploadImageDTO uploadImageDTO : images) {
            imageService.addImage(uploadImageDTO, galleryName);
            log.info("图片\"{}\"上传成功.", uploadImageDTO.getName());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Delete images from a gallery", description = "Delete images from a specified gallery using their IDs.")
    @DeleteMapping
    public ResponseEntity deleteImages(@RequestParam int[] ids) {
        boolean isDeleted = true;

        for (int id : ids)
            isDeleted &= imageService.deleteImage(id);

        return (isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
