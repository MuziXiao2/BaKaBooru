package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClientFactory;
import com.muzixiao2.bakabooru.hub.entity.Source;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import com.muzixiao2.bakabooru.hub.updater.SourceUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncService {
    private final SourceUpdater sourceUpdater;
    private final SourceClientFactory sourceClientFactory;
    private final SourceRepository sourceRepository;

    public List<Source> syncSource() {
        return sourceRepository.findAllByOrderByAddedAtAsc()
                .stream()
                .map(this::syncSource)
                .toList();
    }

    public Source syncSource(Source source) {
        var client = sourceClientFactory.createClient(source.getUrl());
        var response = client.fetchSource();

        if (response.isSuccess()) {
            var dto = response.getData();
            sourceUpdater.update(source, dto);
            sourceRepository.save(source);
            return source;
        } else return null;
    }
}
