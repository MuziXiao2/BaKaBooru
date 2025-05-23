package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @Column(length = 64, nullable = false, unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private String hash;
    //图片类型
    @Column(name = "type")
    private String type;
    //字节数
    @Column(name = "size")
    private Long size;
    //宽
    @Column(name = "width")
    private Integer width;
    //高
    @Column(name = "height")
    private Integer height;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageFile> imageFiles = new ArrayList<>();

    public boolean isOrphaned() {
        return imageFiles.isEmpty();
    }
}
