package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.SciArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SciArticleMapper {
    List<String> getAll();
    List<String> search(String pattern);
    SciArticle get(String aid);
    void add(SciArticle sciArticle);
    void delete(String aid);
}
