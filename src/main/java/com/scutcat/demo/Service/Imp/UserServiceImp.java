package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.User;
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

    @Override
    public JsonResult getUser(String uid) {
        User user=mapper.getUser(uid);
        if(user!=null)
            return new JsonResult(true,"user info",user);
        else return new JsonResult(false,"User dose not exist",null);
    }
}
