package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SourceClientRegistry implements InitializingBean {
    private final SourceRepository sourceRepository;
    private final SourceClientFactory sourceClientFactory;
    private final Map<String, SourceClient> sourceClientMap = new ConcurrentHashMap<>();


    @Override
    public void afterPropertiesSet() {
        List<SourceMeta> sourceMetaList = sourceRepository.findAll();
        sourceMetaList.forEach(sourceMeta -> addClient(sourceMeta.getUrl()));
    }

    public void addClient(String url) {
        sourceClientMap.put(url, sourceClientFactory.createClient(url));
    }

    public Optional<SourceClient> getClient(String url) {
        return Optional.ofNullable(sourceClientMap.get(url));
    }

    public List<SourceClient> getClients() {
        return new ArrayList<>(sourceClientMap.values());
    }
}
