package com.scutcat.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String uid;
    String name;
    String type;
    String registerTime;
    int gender;
    String avatar_url;
    public User(String uid,String name,int gender,String avatarUrl){
        setUid(uid);
        setName(name);
        setGender(gender);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setRegisterTime(df.format(new Date()));
        setAvatar_url(avatarUrl);
    }
}
