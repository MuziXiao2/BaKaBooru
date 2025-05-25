package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "image")
public class Image {
    //UUID
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid = UUID.randomUUID().toString();
    //标题
    @Column(name = "title")
    private String title;
    //创建者
    @Column(name = "creator")
    private String creator;
    //描述
    @Column(name = "description")
    private String description;
    //创建时间
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "order_index")
    private List<ImageFile> imageFiles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "image_tag",
            joinColumns = @JoinColumn(name = "image_uuid"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public ImageFile addImageFile(File file, String fileName) {
        ImageFile link = new ImageFile();
        link.setImage(this);
        link.setFile(file);
        link.setFileName(fileName);
        this.imageFiles.add(link);
        return link;
    }

    //删除文件
    public void removeFile(File file) {
        imageFiles.removeIf(link -> {
            if (link.getFile().equals(file)) {
                file.getImageFiles().remove(link);
                return true;
            }
            return false;
        });
    }

    public void moveFile(int fromIndex, int toIndex) {
        List<ImageFile> list = this.getImageFiles();
        int size = list.size();

        if (fromIndex < 0 || fromIndex >= size) {
            throw new IndexOutOfBoundsException("fromIndex 超出范围");
        }
        if (toIndex < 0 || toIndex >= size) {
            throw new IndexOutOfBoundsException("toIndex 超出范围");
        }
        if (fromIndex == toIndex) {
            return;
        }

        ImageFile item = list.remove(fromIndex);
        list.add(toIndex, item);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getImages().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getImages().remove(this);
    }
}
