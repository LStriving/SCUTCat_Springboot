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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="User对象", description="用户信息表")
public class User {
    @ApiModelProperty(value = "用户ID")
    String uid;
    @ApiModelProperty(value = "用户昵称")
    String name;
    @ApiModelProperty(value = "用户类型(Admin和common user)")
    String type;
    String registerTime;
    @ApiModelProperty(value = "用户性别（0代表女性，1代表男性）")
    int gender;
    @ApiModelProperty(value = "头像链接")
    String avatarUrl;
    public User(String uid,String name,int gender,String avatarUrl){
        setUid(uid);
        setName(name);
        setGender(gender);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setRegisterTime(df.format(new Date()));
        setAvatarUrl(avatarUrl);
    }
}
