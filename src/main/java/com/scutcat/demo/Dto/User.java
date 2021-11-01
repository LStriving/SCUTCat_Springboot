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
    String time;
    int gender;
    public User(String uid,String name,int gender){
        setUid(uid);
        setName(name);
        setGender(gender);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setTime(df.format(new Date()));
    }
}
