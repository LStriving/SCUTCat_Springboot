package com.scutcat.demo.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFollow {
    String uid;
    String uid_followed;
    public void follow(String uid,String uid_followed) {
        setUid(uid);
        setUid_followed(uid_followed);
    }
}
