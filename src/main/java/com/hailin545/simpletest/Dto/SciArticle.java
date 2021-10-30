package com.hailin545.simpletest.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SciArticle {
    String aid;
    String tag;
    String content;
    int like=0;
    String title;

    public void publishSciArticle(String aid,String title,String tag,String content){
        setAid(aid);
        setContent(content);
        setTag(tag);
        setTitle(title);
    }

}
