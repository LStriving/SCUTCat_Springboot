package com.scutcat.demo.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LYR
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="Post对象", description="帖子信息表")
public class Post {
    @ApiModelProperty(value = "帖子的id")
    String pid;
    @ApiModelProperty(value = "用户的id")
    String uid;
    @ApiModelProperty(value = "帖子内容")
    String content;
    @ApiModelProperty(value = "帖子的标签列（#区隔）")
    String tag;
    @ApiModelProperty(value = "帖子的最新修改时间")
    String time;
    @ApiModelProperty(value = "帖子的点赞数")
    int like=0;
    @ApiModelProperty(value = "帖子的阅读数")
    int read=0;
    @ApiModelProperty(value = "帖子的关注数")
    int follow=0;
    @ApiModelProperty(value = "帖子被分享的此时")
    int share=0;
    @ApiModelProperty(value = "帖子的热度")
    double hot=0;

    public Post(String pid,String uid,String content,String tag){
        setPid(pid);
        setUid(uid);
        setContent(content);
        setTag(tag);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setTime(ft.format(new Date()));
    }
}
