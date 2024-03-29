package com.scutcat.demo.mapper;

import com.scutcat.demo.dao.Comment;
import com.scutcat.demo.dao.LikeComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    Comment getComment(String cid);
    void addComment(Comment comment);
    void deleteComment(String cid);
    void updateComment(Comment comment);
    void likeComment(LikeComment likeComment);
    List<String> likeCommentUser(String cid);
    void dislikeComment(LikeComment likeComment);
    List<String> getCommentByPid(String pid);
    List<String> getCommentByPidByTime(String pid);
}
