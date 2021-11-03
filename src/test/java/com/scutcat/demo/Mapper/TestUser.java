package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestUser {
    @Resource
    UserMapper mapper;
    @Test
    public void TestAdd(){
        mapper.addUser(new User("123456","test测试",1,"https://thirdwx.qlogo.cn/mmopen/vi_32/7g6icshVrInLufNoerpJ6R6CYX8opia5gjT7971sI5ab86Qpibw1ECibZDb4VzhO3ibEj3DGF3TLaA0JVyUGFvJCvSA/132"));
    }
    @Test
    public void TestGet(){
        System.out.println(mapper.getUser("123456"));
    }
}
