package com.scutcat.demo.service.Imp;

import com.scutcat.demo.Dto.User;
import com.scutcat.demo.Dto.UserFollow;
import com.scutcat.demo.mapper.UserMapper;
import com.scutcat.demo.service.UserService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public JsonResult follow(String uid, String uuid) {
        if(mapper.getUser(uid)==null||mapper.getUser(uuid)==null){
            return new JsonResult(false,"User dose not exist!",null);
        }
        UserFollow follow=new UserFollow(uid,uuid);
        if(mapper.getFollow(uid).contains(uuid)){
            return new JsonResult(false,"User already follow him/her",null);
        }
        mapper.followUser(follow);
        if(mapper.getFollow(uid).contains(uuid)){
            return new JsonResult(true,"Follow!",null);
        }
        return new JsonResult(false,"Action follow fails!",null);
    }

    @Override
    public JsonResult unfollow(String uid, String uuid) {
        if(mapper.getUser(uid)==null||mapper.getUser(uuid)==null){
            return new JsonResult(false,"User dose not exist!",null);
        }
        if(!mapper.getFollow(uid).contains(uuid)){
            return new JsonResult(false,"User did not follow him/her",null);
        }
        mapper.unfollowUser(new UserFollow(uid,uuid));
        if(!mapper.getFollow(uid).contains(uuid)){
            return new JsonResult(true,"Unfollow!",null);
        }
        else return new JsonResult(false,"Unfollow action fails!",null);
    }

    @Override
    public JsonResult getFans(String uid) {
        if(mapper.getUser(uid)==null)return new JsonResult(false,"User dose not exist",null);
        List<String> fans=mapper.getFans(uid);
        return new JsonResult(true,"success",fans);
    }

    @Override
    public JsonResult getFollowing(String uid) {
        if(mapper.getUser(uid)==null)return new JsonResult(false,"User dose not exist",null);
        List<String> following=mapper.getFollow(uid);
        return new JsonResult(true,"success",following);
    }

    @Override
    public JsonResult updateInfo(String uid, String name, String type, int gender, String avatarUrl) {
        User user=mapper.getUser(uid);
        if(user==null) return new JsonResult(false,"User not found!",null);
        user.setGender(gender);
        user.setAvatarUrl(avatarUrl);
        user.setName(name);
        user.setType(type);
        mapper.updateUser(user);
        User newUser=mapper.getUser(uid);
        if(newUser.getType().equals(type)&&
            newUser.getGender()==gender&&
            newUser.getAvatarUrl().equals(avatarUrl)&&
            newUser.getName().equals(name)){
            return new JsonResult(true,"Successfully update!",null);
        }
        return new JsonResult(false,"Fail to update",null);
    }
}
