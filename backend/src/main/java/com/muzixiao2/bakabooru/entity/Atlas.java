package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;
import java.util.HashSet;
import java.time.Instant;


@Entity
@Table(name = "atlas")
@Data
@NoArgsConstructor
public class Atlas {
    //UUID
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;
    //标题
    @Column(name = "title")
    private String title;
    //创建者
    @Column(name = "creator")
    private String creator;
    //描述
    @Column(name = "description")
    private String description;
    //封面哈希值
    @Column(name = "cover_hash")
    private String coverHash;
    //创建时间
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    //包含标签
    @ManyToMany
    @JoinTable(
            name = "atlas_tags",
            joinColumns = @JoinColumn(name = "atlas_uuid"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
