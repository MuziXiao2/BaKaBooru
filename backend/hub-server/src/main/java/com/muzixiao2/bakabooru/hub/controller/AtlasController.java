package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.service.AtlasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atlases")
@RequiredArgsConstructor
public class AtlasController {
    private final AtlasService atlasService;

}
