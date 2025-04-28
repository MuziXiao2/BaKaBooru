package com.muzixiao2.bakabooru.hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "atlas")
@Data
@NoArgsConstructor
public class Atlas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @Column(name = "name")
    private String title;
    //创建者
    @Column(name = "creator")
    private String creator;
    //创建时间
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    //所属图源
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    //所属标签
    @ManyToMany
    @JoinTable(
            name = "atlas_tags",
            joinColumns = @JoinColumn(name = "atlas_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
