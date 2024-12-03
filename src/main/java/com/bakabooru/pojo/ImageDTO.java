package com.bakabooru.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    @Schema(description = "图片的名称", example = "示例图片")
    @NotNull(message = "图片名称不能为空")
    @NotBlank(message = "图片名称不能为空白字符")
    @JsonProperty("name")
    private String name;


    @Schema(description = "图片的URL", example = "https://example.com/image.jpg")
    @NotNull(message = "图片URL不能为空")
    @NotBlank(message = "图片URL不能为空白字符")
    @JsonProperty("url")
    private String url;
}
