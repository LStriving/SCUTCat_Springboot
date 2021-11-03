package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.Dto.LikeComment;
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
