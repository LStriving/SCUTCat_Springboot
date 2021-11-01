package com.scutcat.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestUser {
    @Resource
    UserMapper mapper;
    @Test
    public void TestGet(){
        System.out.println(mapper.getUser("123456"));
    }
}
