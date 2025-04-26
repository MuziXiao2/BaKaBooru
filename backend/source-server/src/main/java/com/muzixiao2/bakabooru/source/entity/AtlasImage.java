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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "atlas_uuid", nullable = false)
    private Atlas atlas;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_hash", referencedColumnName = "hash", nullable = false)
    private Image image;

    //图片标题
    @Column(name = "title")
    private String title;

    //图片添加时间
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
