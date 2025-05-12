package com.muzixiao2.bakabooru.hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "source-meta")
@Data
@NoArgsConstructor
public class SourceMeta {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid = UUID.randomUUID().toString();
    //名称
    @Column(name = "name")
    private String name;
    //URL
    @Column(name = "url", unique = true, nullable = false)
    private String url;
    //添加时间
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant addedAt;
}
