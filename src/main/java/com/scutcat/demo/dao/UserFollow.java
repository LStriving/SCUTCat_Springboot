package com.scutcat.demo.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYR
 */
@Data
@NoArgsConstructor
@ApiModel(value="UserFollow对象", description="用户关注信息表")
public class UserFollow {
    @ApiModelProperty(value = "主动关注的用户的id")
    String uid;
    @ApiModelProperty(value = "被关注用户的id")
    String uidFollowed;
    public UserFollow(String uid,String uidFollowed) {
        setUid(uid);
        setUidFollowed(uidFollowed);
    }
}
