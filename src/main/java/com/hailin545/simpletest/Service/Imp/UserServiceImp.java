package com.hailin545.simpletest.Service.Imp;

import com.hailin545.simpletest.Mapper.UserMapper;
import com.hailin545.simpletest.Service.UserService;
import com.hailin545.simpletest.uitls.JsonResult;

import javax.annotation.Resource;

public class UserServiceImp implements UserService {
    @Resource
    UserMapper mapper;

    @Override
    public JsonResult login(String uid) {
       return null;
    }
}
