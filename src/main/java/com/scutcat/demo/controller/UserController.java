package com.scutcat.demo.controller;

import com.scutcat.demo.dao.User;
import com.scutcat.demo.dao.UserFollow;
import com.scutcat.demo.service.UserService;
import com.scutcat.demo.uitls.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LYR
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService service;

    @GetMapping("/info/{uid}")
    @ApiOperation(value = "获取用户信息",notes = "该接口后续可能放弃，直接把id保存在session中")
    public JsonResult<User> getInfo(@PathVariable String uid){
        return service.getUser(uid);
    }
    @GetMapping("/info")
    @ApiOperation(value = "获取用户的信息",notes = "用户需要登录，用户的信息保存在缓存中")
    public  JsonResult<User> getInfo(){
        //接口待实现
        return new JsonResult<>(new User());
        //return service.getUser();
    }

    @PostMapping("/follow")
    @ApiOperation(value = "用户关注一个用户",notes = "主动方在前")
    public JsonResult follow(@RequestBody UserFollow userFollow){
        return service.follow(userFollow);
    }
    @PostMapping("/unfollow")
    @ApiOperation(value = "用户取消关注另外一个用户",notes = "主动的一方在前")
    public JsonResult unfollow(@RequestBody UserFollow userFollow){
        return service.unfollow(userFollow);
    }
    @GetMapping("/fans/{uid}")
    @ApiOperation(value = "获取用户的粉丝id")
    public JsonResult getFans(@PathVariable("uid")String uid){
        return service.getFans(uid);
    }
    @GetMapping("/following")
    @ApiOperation(value = "获取用户所关注的用户",notes = "返回用户的用户id列表")
    public JsonResult getFollowing(@RequestParam("uid")String uid){
        return service.getFollowing(uid);
    }
    @PostMapping("/updateInfo")
    @ApiOperation(value = "更新用户信息")
    public JsonResult updateInfo(@RequestBody User user){
        return service.updateInfo(user);
    }
}
