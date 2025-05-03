package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.mapper.AtlasMapper;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtlasService {
    private final AtlasMapper atlasMapper;
    private final SourceRepository sourceRepository;
    private final SourceClientRegistry sourceClientRegistry;
}
