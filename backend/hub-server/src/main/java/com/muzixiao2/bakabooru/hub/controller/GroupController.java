package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

}