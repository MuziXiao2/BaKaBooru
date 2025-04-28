package com.muzixiao2.bakabooru.hub.init;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientFactory;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import com.muzixiao2.bakabooru.hub.entity.Source;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SourceSyncInitializer implements CommandLineRunner {

    private final SourceClientFactory sourceClientFactory;
    private final SourceRepository sourceRepository;

    @Override
    public void run(String... args) {
        log.info("更新图源信息...");

        SourceClient client;
        SourceSyncDTO sourceSyncDTO;
        List<Source> sources = sourceRepository.findAllByOrderByAddedAtAsc();
        for (Source source : sources) {
            client = sourceClientFactory.createClient(source.getUrl());
            sourceSyncDTO = client.fetchSource().getData();
            source.setDefaultName(sourceSyncDTO.getDefaultName());
            source.setCreator(sourceSyncDTO.getCreator());
            source.setCreatedAt(sourceSyncDTO.getCreatedAt());
            source.setUpdatedAt(sourceSyncDTO.getUpdatedAt());
            sourceRepository.save(source);
        }

    }
}
