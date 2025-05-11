package com.muzixiao2.bakabooru.source.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtlasImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 图集 UUID
    @Column(name = "atlas_uuid", nullable = false, length = 36)
    private String atlasUuid;

    // 图片 Hash
    @Column(name = "image_hash", nullable = false, length = 64)
    private String imageHash;

    // 图片顺序
    @Column(name = "order_index")
    private Double orderIndex;

    // 图片标题
    @Column(name = "title")
    private String title;

    // 图片添加时间
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
}
