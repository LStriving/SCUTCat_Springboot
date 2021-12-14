package com.scutcat.demo.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYR
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="UserFollowPost对象", description="用户关注帖子信息表")
public class UserFollowPost {
    @ApiModelProperty(value = "主动关注的用户的id")
    String uid;
    @ApiModelProperty(value = "被关注帖子的id")
    String pid;
}
