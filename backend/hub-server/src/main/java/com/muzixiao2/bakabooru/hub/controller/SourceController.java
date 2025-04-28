package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sources")
@RequiredArgsConstructor
public class SourceController {
    private final SourceService sourceService;


}
