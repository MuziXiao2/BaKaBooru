package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    //UUID
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;
    //标题
    @Column(name = "title")
    private String title;
    //创建者
    @Column(name = "creator")
    private String creator;
    //描述
    @Column(name = "description")
    private String description;
    //封面哈希值
    @Column(name = "cover_hash")
    private String coverHash;
    //创建时间
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "image_imagefile",
            joinColumns = @JoinColumn(name = "image_uuid"),
            inverseJoinColumns = @JoinColumn(name = "imagefile_hash")
    )
    private Set<ImageFile> imageFiles = new HashSet<>();

    public void addImageFile(ImageFile imageFile) {
        this.imageFiles.add(imageFile);
        imageFile.getImages().add(this);
        imageFile.setReferenceCount(imageFile.getReferenceCount() + 1);
    }

    public void removeImageFile(ImageFile imageFile) {
        this.imageFiles.remove(imageFile);
        imageFile.getImages().remove(this);
        imageFile.setReferenceCount(imageFile.getReferenceCount() - 1);
    }

}
