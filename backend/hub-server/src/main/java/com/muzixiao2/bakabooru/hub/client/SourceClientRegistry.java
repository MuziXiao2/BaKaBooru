package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SourceClientRegistry implements InitializingBean {
    private final SourceRepository sourceRepository;
    private final SourceClientFactory sourceClientFactory;
    private final Map<SourceMeta, SourceClient> sourceClientMap = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() {
        List<SourceMeta> sourceMetaList = sourceRepository.findAll();
        sourceMetaList.forEach(this::addClient);
    }

    public void addClient(SourceMeta sourceMeta) {
        sourceClientMap.put(sourceMeta, sourceClientFactory.createClient(sourceMeta.getUrl()));
    }

    public SourceClient getClient(Long sourceId) {
        SourceMeta sourceMeta = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new IllegalArgumentException("图源不存在"));
        SourceClient sourceClient = sourceClientMap.get(sourceMeta);
        if (sourceClient == null) throw new IllegalArgumentException("图源无法连接");
        return sourceClient;
    }

}
