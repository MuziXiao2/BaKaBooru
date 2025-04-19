package com.xiao2.bakabooru.backenddata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "source")
@Data
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //图源组id
    @Column(name = "group_id")
    private Long groupId;
    //图源序号
    @Column(name = "sn")
    private Long sn;

    //图源名称
    @Column(name = "name")
    private String name;
    //图源URL
    @Column(name = "source_url")
    private String sourceUrl;
    //OSSURL
    @Column(name = "oss_url")
    private String ossUrl;
    //图源者
    @Column(name = "creator")
    private String creator;
    //创建时间
    @Column(name = "created_at")
    private Instant createdAt;
    //更新时间
    @Column(name = "updated_at")
    private Instant updatedAt;
}
