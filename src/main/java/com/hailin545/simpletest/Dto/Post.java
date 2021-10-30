package com.hailin545.simpletest.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
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

    public void PublishPost(String pid,String uid,String content,String tag){
        setPid(pid);
        setUid(uid);
        setContent(content);
        setTag(tag);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        setTime(ft.format(new Date()));
    }
}
