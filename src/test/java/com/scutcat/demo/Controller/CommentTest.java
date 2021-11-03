package com.scutcat.demo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CommentTest {
    @Resource
    CommentController controller;
    @Test
    public void testAdd(){
        System.out.println(controller.publish("123456","123","这是一条评论","123"));
    }
    @Test
    public void testLike(){
        System.out.println(controller.like("1233456","123"));
    }
    @Test
    public void testDislike(){
        System.out.println(controller.dislike("1233456","123"));
    }
    @Test
    public void testDelete(){
        controller.delete("123456","123");
    }
}
