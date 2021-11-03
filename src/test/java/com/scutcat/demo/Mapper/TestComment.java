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
    public void testAdd(){
        mapper.addComment(new Comment("123","123","123456","这是一条评论"));
    }
    @Test
    public void testLike(){
        mapper.likeComment(new LikeComment("123456","123"));
    }
}
