package com.hailin545.simpletest.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {
    String cid;
    String pid;
    String uid;
    String content;
    int like=0;
    String time;
    boolean top=false;

    public void Review(String cid,String pid,String uid,String content){
        setCid(cid);
        setPid(pid);
        setContent(content);
        setUid(uid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setTime(simpleDateFormat.format(new Date()));
    }
}
