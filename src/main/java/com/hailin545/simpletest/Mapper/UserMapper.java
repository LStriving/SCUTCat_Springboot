package com.hailin545.simpletest.Mapper;

import com.hailin545.simpletest.Dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUser(String uid);
    void addUser(String uid,String name,String time,String type,int gender);
    void updateUser(String uid,String name,String type,int gender);
}
