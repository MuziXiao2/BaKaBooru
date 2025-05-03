package com.muzixiao2.bakabooru.hub.init;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientFactory;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import com.muzixiao2.bakabooru.hub.entity.Source;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import com.muzixiao2.bakabooru.hub.service.SyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SyncInitializer implements CommandLineRunner {
    private final SyncService syncService;

    @Override
    public void run(String... args) {
        log.info("Starting sync initializer");
        List<Source> sources = syncService.syncSource();

    }
}
