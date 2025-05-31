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

    //查看次数
    @Column(name = "view_count")
    private Long viewCount = 0L;

    //创建时间
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    //更新时间
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    //图片文件
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "order_index")
    private List<ImageFile> imageFiles = new ArrayList<>();

    //图片标签
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name ASC")
    private List<ImageTag> imageTags = new ArrayList<>();

    //添加标签
    public void addTag(String tag) {
        imageTags.add(new ImageTag(this, tag));
    }

    //更新标签
    public void updateTags(List<String> tags) {
        imageTags.clear();
        for (String tag : tags)
            imageTags.add(new ImageTag(this, tag));
    }

    //添加查看次数
    public void incrementViewCount() {
        this.viewCount++;
    }

    //获取标签
    public List<String> getTags() {
        return imageTags
                .stream()
                .map(ImageTag::getName)
                .toList();
    }

    //添加文件
    public ImageFile addImageFile(File file, String fileName) {
        ImageFile imageFile = new ImageFile();
        imageFile.setFile(file);
        imageFile.setImage(this);
        imageFile.setFileName(fileName);
        this.imageFiles.add(imageFile);
        file.getImageFiles().add(imageFile);
        return imageFile;
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

}
