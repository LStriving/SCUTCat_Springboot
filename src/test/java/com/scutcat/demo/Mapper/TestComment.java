package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.Dto.LikeComment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestComment {
    @Resource
    CommentMapper mapper;
    @Test
    public void testGet(){
        mapper.getComment("123");
    }
    @Test
    public void testAdd(){
        mapper.addComment(new Comment("120","123","123456","这又是一条评论"));
    }
    @Test
    public void testDelete(){
        mapper.deleteComment("123");
    }
    @Test
    public void testUpdate(){
        mapper.updateComment(new Comment("1234","123","123456","为什么不更新"));
    }
    @Test
    public void testLike(){
        mapper.likeComment(new LikeComment("123456","122"));
    }
    @Test
    public void testLikeCommentUser(){
        mapper.likeCommentUser("123");
    }
    @Test
    public void testDislikeComment(){
        mapper.dislikeComment(new LikeComment("123456","123"));
    }
    @Test
    public void testGetCommentByPid(){
        mapper.getCommentByPid("123");
    }
    @Test
    public void testGetCommentByPidByTime(){
        System.out.println(mapper.getCommentByPidByTime("123"));
    }
}
