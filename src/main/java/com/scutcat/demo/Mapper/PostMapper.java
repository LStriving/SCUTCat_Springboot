package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.LikePost;
import com.scutcat.demo.Dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    void insertPost(Post post);
    void deletePost(String pid);
    void updatePost(Post post);
    Post getPost(String pid);
    List<Post> getUserAll(String uid);
    void likePost(LikePost likePost);
    List<String> getPostLikeUser(String pid);
}
