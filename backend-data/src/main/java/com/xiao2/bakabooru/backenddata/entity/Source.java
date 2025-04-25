package com.xiao2.bakabooru.backenddata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    //图源URL
    @Column(name = "url")
    private String url;
    //所属组ID
    @Column(name = "group_name")
    private String groupName;
    //添加时间
    @Column(name = "added_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant addedAt;
}
