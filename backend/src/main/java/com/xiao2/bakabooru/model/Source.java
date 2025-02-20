package com.xiao2.bakabooru.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

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

    //图源类型
    @Column(name = "type")
    private String type;

    //图源url
    @Column(name = "url")
    private String url;

    //创建时间
    @Column(name = "created_at")
    private Instant createAt;

    //更新时间
    @Column(name = "updated_at")
    private Instant updatedAt;
}
