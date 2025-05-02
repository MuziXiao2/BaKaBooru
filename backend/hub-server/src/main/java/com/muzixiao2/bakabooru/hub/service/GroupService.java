package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.mapper.GroupMapper;
import com.muzixiao2.bakabooru.hub.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

}
