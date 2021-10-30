package com.hailin545.simpletest.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    String uid;
    String name;
    String type;
    String time;

    public void Register(String uid,String name){
        setUid(uid);
        setName(name);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        setTime(df.format(new Date()));
    }
}
