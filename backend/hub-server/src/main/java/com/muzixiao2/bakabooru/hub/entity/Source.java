package com.muzixiao2.bakabooru.hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "source")
@Data
@NoArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //图源名称
    @Column(name = "name")
    private String name;
    //图源默认名称
    @Column(name = "default_name")
    private String defaultName;
    //图源URL
    @Column(name = "url")
    private String url;
    //组名
    @Column(name = "group_name")
    private String groupName;
    //创建者
    @Column(name = "creator")
    private String creator;
    //创建时间
    @Column(name = "create_at", nullable = false)
    private String createdAt;
    //更新时间
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
    //添加时间
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant addedAt;
}
