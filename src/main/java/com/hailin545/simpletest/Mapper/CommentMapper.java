package com.hailin545.simpletest.Mapper;

import com.hailin545.simpletest.Dto.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {
    Comment getComment(String cid);
    void addComment(Comment comment);
    void deleteComment(String cid);
    void updateComment(Comment comment);
}
