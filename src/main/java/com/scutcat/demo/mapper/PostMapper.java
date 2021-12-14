package com.scutcat.demo.mapper;

import com.scutcat.demo.dao.History;
import com.scutcat.demo.dao.LikePost;
import com.scutcat.demo.dao.Post;
import com.scutcat.demo.dao.UserFollowPost;
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
    List<String> getUserAll(String uid);
    void likePost(LikePost likePost);
    List<String> getPostLikeUser(String pid);
    void dislikePost(LikePost likePost);
    void follow(UserFollowPost userFollowPost);
    List<String> getPostFollowUser(String pid);
    void unfollow(UserFollowPost userFollowPost);
    void addHistory(History history);
    List<String>getPostFollowedByUser(String uid);
    List<String> search(String name);
    List<String> getRecommendByLike(String uid);
    List<String> getRecommendByTime(String uid);
    List<String> getRecommendByHot(String uid);
    List<String> getAllPost();
}
