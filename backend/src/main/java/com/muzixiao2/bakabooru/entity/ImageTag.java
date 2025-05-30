package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"image", "name"})
@Entity
@Table(name = "image_tag")
public class ImageTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_uuid", nullable = false)
    private Image image;

    @Id
    @Column(name = "name", nullable = false)
    private String name;
}
