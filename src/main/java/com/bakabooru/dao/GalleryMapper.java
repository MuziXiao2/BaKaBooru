package com.bakabooru.dao;

import com.bakabooru.pojo.Gallery;
import com.bakabooru.pojo.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GalleryMapper {
    @Select("SELECT i.id, i.url, i.title, i.created_at, i.updated_at " +
            "FROM image i " +
            "JOIN gallery_image gi ON i.id = gi.image_id " +
            "WHERE gi.gallery_id = #{galleryId}")
    List<Image> getImagesByGalleryId(int galleryId);

    @Select("SELECT name FROM gallery WHERE id = #{galleryId}")
    String getGalleryNameByGalleryId(int galleryId);

    @Select("SELECT COUNT(id) FROM image")
    int getNextImageId();

    // 添加到 gallery 表
    @Insert("INSERT INTO gallery (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addGallery(Gallery gallery);

    // 添加到 image 表
    @Insert("INSERT INTO image (url, title) VALUES (#{url}, #{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addImage(Image image);

    // 在 gallery_image 表中建立关联
    @Insert("INSERT INTO gallery_image (gallery_id, image_id) VALUES (#{galleryId}, #{imageId})")
    void addGalleryImage(int galleryId, int imageId);
}