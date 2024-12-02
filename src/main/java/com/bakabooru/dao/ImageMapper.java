package com.bakabooru.dao;

import com.bakabooru.pojo.ImageVO;
import com.bakabooru.pojo.ImageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Select("select i.id, i.name, i.url " +
            "from images i " +
            "join galleries g " +
            "on i.gallery_id = g.id " +
            "where g.name = #{galleryName};")
    List<ImageVO> getImages(String galleryName);

    @Insert("insert into images (name,url,gallery_id) values (#{name},#{url},#{galleryName})")
    void addImage(ImageDTO imageDTO);

    @Delete("DELETE FROM images WHERE id = #{id}")
    int deleteImage(int id);

}