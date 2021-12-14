package com.scutcat.demo.service.Imp;

import com.scutcat.demo.dao.User;
import com.scutcat.demo.dao.UserFollow;
import com.scutcat.demo.mapper.UserMapper;
import com.scutcat.demo.service.UserService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYR
 */
@Service("UserService")
public class UserServiceImp implements UserService {
    @Resource
    UserMapper mapper;

    @Override
    public JsonResult login(String uid) {
       return null;
    }

    @Override
    public JsonResult<User> getUser(String uid) {
        User user=mapper.getUser(uid);
        if(user!=null) {
            return new JsonResult<>(user);
        } else {
            return new JsonResult<>(404,"找不到该用户");
        }
    }

    @Override
    public JsonResult follow(UserFollow userFollow) {
        try {
            mapper.followUser(userFollow);
            return new JsonResult<>();
        }catch (Exception e){
            if(mapper.getFollow(userFollow.getUid()).contains(userFollow.getUidFollowed())){
                return new JsonResult<>(201,"用户已经关注啦！");
            }else{
                System.out.println("取消关注用户失败");
                System.out.println(e.getMessage());
                System.out.println("===========================");
                e.printStackTrace();
                return new JsonResult<>().failed(500,e.getMessage());
            }
        }
    }

    @Override
    public JsonResult unfollow(UserFollow userFollow) {
        try {
            mapper.unfollowUser(userFollow);
            return new JsonResult<>();
        }catch (Exception e){
            if (!mapper.getFollow(userFollow.getUid()).contains(userFollow.getUidFollowed())) {
                return new JsonResult<>(404,"你并没有关注这个用户哦！");
            }
            System.out.println("取消关注用户失败");
            System.out.println(e.getMessage());
            System.out.println("===========================");
            e.printStackTrace();
            return new JsonResult<>().failed(500,e.getMessage());
        }
    }

    @Override
    public JsonResult getFans(String uid) {
        if(mapper.getUser(uid)==null)return new JsonResult(404,"User dose not exist");
        List<String> fans=mapper.getFans(uid);
        return new JsonResult<>(fans);
    }

    @Override
    public JsonResult getFollowing(String uid) {
        if(mapper.getUser(uid)==null) {
            return new JsonResult<>(404,"User dose not exist");
        }
        List<String> following=mapper.getFollow(uid);
        return new JsonResult<>(following);
    }

    @Override
    public JsonResult updateInfo(User user) {
        User userNew = mapper.getUser(user.getUid());
        //用户没找到
        if(userNew==null){
            return new JsonResult<>().failed(404,"User not found");
        }
        //信息没有修改，不做处理
        if(userNew==user){
            return new JsonResult<>();
        }
        try{
            mapper.updateUser(user);
            return new JsonResult<>();
        }catch (Exception e){
            System.out.println("Update User Info failed!");
            System.out.println(e.getMessage());
            System.out.println("===========================");
            e.printStackTrace();
            return new JsonResult<>().failed(500,e.getMessage());
        }

    }
}
