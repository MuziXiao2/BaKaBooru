package com.bakabooru.dao;

import com.bakabooru.pojo.Gallery;
import com.bakabooru.pojo.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GalleryMapper {

    @Select("SELECT g.id AS g_id, g.name AS g_name, i.id AS i_id, i.url AS i_url, i.title AS i_title " +
            "FROM galleries g LEFT JOIN images i ON g.id = i.gallery_id WHERE g.id = #{galleryId}")
    @Results({
            @Result(property = "id", column = "g_id"),
            @Result(property = "name", column = "g_name"),
            @Result(property = "images", javaType = List.class, column = "g_id",
                    many = @Many(select = "com.bakabooru.dao.GalleryMapper.getImagesByGalleryId"))
    })
    Gallery getGallery(int galleryId);

    @Select("SELECT id, url, title FROM images WHERE gallery_id = #{galleryId}")
    List<Image> getImagesByGalleryId(int galleryId);
}