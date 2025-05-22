package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.service.TagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Tag(name = "标签管理", description = "用于管理标签的接口")
public class TagController {
    private final TagService tagService;




}
