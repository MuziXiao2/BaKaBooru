package com.muzixiao2.bakabooru.backendsource.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "atlas")
@Data
@NoArgsConstructor
public class Atlas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //序号
    @Column(name = "sn")
    private Double sn;
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
    @CreationTimestamp
    private Instant createAt;
    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
