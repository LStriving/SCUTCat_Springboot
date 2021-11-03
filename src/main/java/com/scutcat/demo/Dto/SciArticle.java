package com.scutcat.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SciArticle {
    String aid;
    String tag;
    String content;
    int like=0;
    String title;

    public void SciArticle(String aid,String title,String tag,String content){
        setAid(aid);
        setContent(content);
        setTag(tag);
        setTitle(title);
    }

}
