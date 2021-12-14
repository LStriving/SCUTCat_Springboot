package com.scutcat.demo.mapper;

import com.scutcat.demo.Dto.SciArticle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestSciArt {
    @Resource
    SciArticleMapper mapper;
    @Test
    public void testGetAll(){
        for (String item : mapper.getAll()){
            System.out.println(item);
        }
        System.out.println(mapper.getAll());
    }
    @Test
    public void testSearch(){
        for (String item : mapper.search("cat")){
            System.out.println(item);
        }
        System.out.println(mapper.search("cat"));
    }
    @Test
    public void testGet(){
        System.out.println(mapper.get("123456"));
    }
    @Test
    public void testAdd(){
        mapper.add(new SciArticle("12345","title","#tag","cat科普科普科普"));
    }
    @Test
    public void testDelete(){
        mapper.delete("123456");
    }
}
