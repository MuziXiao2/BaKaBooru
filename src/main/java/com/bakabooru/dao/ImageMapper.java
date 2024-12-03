package com.bakabooru.dao;

import com.bakabooru.pojo.ImageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Select("select i.id, i.name, i.url " +
            "from images i " +
            "join galleries g " +
            "on i.gallery_name = g.name " +
            "where g.name = #{galleryName};")
    List<ImageVO> getImages(String galleryName);

    @Insert("insert into images (name,url,gallery_name) values (#{name},#{url},#{galleryName})")
    void addImage(String name, String url, String galleryName);

    @Delete("DELETE FROM images WHERE id = #{id}")
    int deleteImage(int id);

}