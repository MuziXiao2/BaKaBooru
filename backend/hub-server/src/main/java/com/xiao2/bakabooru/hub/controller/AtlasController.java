package com.xiao2.bakabooru.hub.controller;

import com.xiao2.bakabooru.hub.dto.AtlasQueryDTO;
import com.xiao2.bakabooru.hub.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.hub.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.hub.dto.ResponseResult;
import com.xiao2.bakabooru.hub.service.AtlasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atlases")
@RequiredArgsConstructor
public class AtlasController {
    private final AtlasService atlasService;

    /**
     * 创建图集
     */
    @PostMapping
    public ResponseResult<AtlasResponseDTO> createAtlas(@RequestBody AtlasRequestDTO requestDTO) {
        AtlasResponseDTO atlasResponseDTO = atlasService.createAtlas(requestDTO);
        return ResponseResult.success(atlasResponseDTO, "图集创建成功");
    }

    /**
     * 获取图集
     */
    @GetMapping("/{id}")
    public ResponseResult<AtlasResponseDTO> getAtlas(@PathVariable Long id) {
        return ResponseResult.success(atlasService.getAtlas(id), "获取图集成功");
    }

    /**
     * 查询图集
     */
    @PostMapping("/search")
    public ResponseResult<List<AtlasResponseDTO>> searchAtlases(@RequestBody AtlasQueryDTO queryDTO) {
        List<AtlasResponseDTO> result = atlasService.searchAtlases(queryDTO);
        return ResponseResult.success(result, "图集查询成功");
    }


}
