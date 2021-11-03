package com.scutcat.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestPost {
    @Resource
    PostMapper mapper;
    @Test
    public void testGetPostLikeUser(){
        for (String item : mapper.getPostLikeUser("123")){
            System.out.println(item);
        }
        System.out.println(mapper.getPostLikeUser("123").contains("123456"));
    }
}
