package com.hailin545.simpletest.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFollowPost {
    String uid;
    String pid;

    public void followPost(String uid,String pid){
        setUid(uid);
        setPid(pid);
    }
}
