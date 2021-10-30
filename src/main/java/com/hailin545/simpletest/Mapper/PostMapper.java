package com.hailin545.simpletest.Mapper;

import com.hailin545.simpletest.Dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    void insertPost(Post post);
    void deleterPost(String pid);
    void updatePost(Post post);
    Post getPost(String pid);
    List<Post> getUserAll(String uid);

}
