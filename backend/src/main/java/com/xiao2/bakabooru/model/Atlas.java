package com.xiao2.bakabooru.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    //封面url
    @Column(name = "cover_url")
    private String coverUrl;

    //创建者
    @Column(name = "creator")
    private String creator;

    //创建时间
    @Column(name = "created_at")
    private LocalDateTime createAt;

    //更新时间
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //所属图源id
    @Column(name = "source_id")
    private Long sourceId;


}
