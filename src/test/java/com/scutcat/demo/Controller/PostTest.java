package com.scutcat.demo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PostTest {
    @Resource
    PostController controller;
    @Test
    public void testPublish(){
        System.out.println(controller.publish("123456","123","猫猫走丢了","#求助"));
    }
    @Test
    public void testLikePost(){
        System.out.println(controller.like("123456","123"));
    }
}
