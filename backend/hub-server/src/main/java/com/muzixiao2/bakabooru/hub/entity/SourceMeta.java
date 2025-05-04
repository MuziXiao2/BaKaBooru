package com.muzixiao2.bakabooru.hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "source-meta")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class SourceMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //名称
    @Column(name = "name")
    private String name;
    //URL
    @Column(name = "url")
    private String url;
    //添加时间
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant addedAt;
}
