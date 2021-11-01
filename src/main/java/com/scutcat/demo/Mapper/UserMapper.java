package com.scutcat.demo.Mapper;

import com.scutcat.demo.Dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUser(String uid);
    void addUser(User user);
    void updateUser(User user);
}
