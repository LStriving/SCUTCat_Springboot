package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Mapper.UserMapper;
import com.scutcat.demo.Service.UserService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("UserService")
public class UserServiceImp implements UserService {
    @Resource
    UserMapper mapper;

    @Override
    public JsonResult login(String uid) {
       return null;
    }
}
