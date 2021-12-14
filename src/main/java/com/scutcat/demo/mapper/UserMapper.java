package com.scutcat.demo.mapper;

import com.scutcat.demo.dao.History;
import com.scutcat.demo.dao.User;
import com.scutcat.demo.dao.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User getUser(String uid);
    void addUser(User user);
    void updateUser(User user);

    /**
     * 获取用户（uid）关注的用户的id
     * @param uid 主动关注的用户的id
     * @return 被这个用户关注的用户id
     */
    List<String> getFollow(String uid);
    void followUser(UserFollow follow);

    /**
     * 取消关注用户
     * @param follow
     */
    void unfollowUser(UserFollow follow);
    List<String> getFans(String uid);
    List<History> getHistory(String uid);
}
