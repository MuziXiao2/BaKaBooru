package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.model.Atlas;
import com.xiao2.bakabooru.service.AtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atlases")
public class AtlasController {

    @Autowired
    private AtlasService atlasService;

    /*图集操作*/
    // 给图源添加图集
    @PostMapping
    public ResponseResult<Atlas> addAtlas(@RequestBody AtlasRequestDTO atlasRequestDTO) {
        Atlas atlas = atlasService.addAtlas(atlasRequestDTO);
        return ResponseResult.success(atlas, "添加图集成功");
    }

    // 从图源获取所有图集
    @GetMapping("/{source_id}")
    public List<AtlasResponseDTO> getAllAtlas(@PathVariable("source_id") Long sourceId) {
        return atlasService.getAllAtlas(sourceId);
    }
}
