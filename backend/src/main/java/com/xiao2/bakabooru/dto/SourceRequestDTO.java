package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Source;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.Instant;

@Data
public class SourceRequestDTO {
    private String name;
    private String type;
    private String url;

    @PrePersist
    public Source toSource() {
        Source source = new Source();

        source.setName(name);
        source.setType(type);
        source.setUrl(url);

        Instant now = Instant.now();
        source.setCreateAt(now);
        source.setUpdatedAt(now);

        return source;
    }
}
