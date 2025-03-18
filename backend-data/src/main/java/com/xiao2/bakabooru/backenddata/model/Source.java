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
    //图源类型
    @Column(name = "type")
    private String type;
    //图源url
    @Column(name = "url")
    private String url;
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
