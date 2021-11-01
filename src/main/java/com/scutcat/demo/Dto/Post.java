package com.scutcat.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    String pid;
    String uid;
    String content;
    String tag;
    String time;
    int like=0;
    int read=0;
    int follow=0;
    int share=0;

    public Post(String pid,String uid,String content,String tag){
        setPid(pid);
        setUid(uid);
        setContent(content);
        setTag(tag);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setTime(ft.format(new Date()));
    }
}
