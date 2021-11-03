package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.History;
import com.scutcat.demo.Dto.User;
import com.scutcat.demo.Dto.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User getUser(String uid);
    void addUser(User user);
    void updateUser(User user);
    List<String> getFollow(String uid);
    void followUser(UserFollow follow);
    void unfollowUser(UserFollow follow);
    List<String> getFans(String uid);
    List<History> getHistory(String uid);
}
