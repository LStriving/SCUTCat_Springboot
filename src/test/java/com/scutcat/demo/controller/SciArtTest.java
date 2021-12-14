package com.scutcat.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
public class SciArtTest {
    @Resource
    SciArtController controller;
    @Test
    public void testGetAll(){
        System.out.println(controller.getAll());
    }
    @Test
    public void testSearch(){
        System.out.println(controller.search("cat"));
    }
    @Test
    public void testGet(){
        System.out.println(controller.get("123456"));
    }
    @Test
    public void testPublish(){
        System.out.println(controller.publish("123456","title","#tag","content"));
    }
    @Test
    public void testDelete(){
        System.out.println(controller.delete("123456"));
    }
}