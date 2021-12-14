package com.scutcat.demo.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Common对象", description="评论信息表")
public class Comment {
    @ApiModelProperty(value = "评论id")
    String cid;
    String pid;
    String uid;
    String content;
    int like=0;
    String time;
    boolean top=false;

    public Comment(String cid,String pid,String uid,String content){
        setCid(cid);
        setPid(pid);
        setContent(content);
        setUid(uid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setTime(simpleDateFormat.format(new Date()));
    }
}
