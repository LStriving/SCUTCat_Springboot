package com.scutcat.demo.controller;

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
    public JsonResult getInfo(@PathVariable String uid){
        return service.getUser(uid);
    }
    @PostMapping("/follow/{uid}/{uuid}")
    @ApiOperation(value = "用户关注一个用户",notes = "主动方在前")
    public JsonResult follow(@PathVariable("uid")String uid,
                             @PathVariable("uuid")String uuid){
        return service.follow(uid,uuid);
    }
    @RequestMapping("/unfollow/{uid}/{uuid}")
    @ApiOperation(value = "用户取消关注另外一个用户",notes = "主动的一方在前")
    public JsonResult unfollow(@PathVariable("uid")String uid,
                               @PathVariable("uuid")String uuid){
        return service.unfollow(uid,uuid);
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
    @RequestMapping("/updateInfo")
    @ApiOperation(value = "更新用户信息")
    public JsonResult updateInfo(@RequestParam("uid")String uid,
                                  @RequestParam("name")String name,
                                  @RequestParam("type")String type,
                                  @RequestParam("gender")int gender,
                                  @RequestParam("avatarUrl")String avatarUrl){
        return service.updateInfo(uid,name,type,gender,avatarUrl);
    }
}
