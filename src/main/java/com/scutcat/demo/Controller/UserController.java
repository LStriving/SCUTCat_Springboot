package com.scutcat.demo.Controller;

import com.scutcat.demo.Service.UserService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService service;

    @RequestMapping("/getInfo")
    public JsonResult getInfo(@RequestParam("uid")String uid){
        return service.getUser(uid);
    }
    @RequestMapping("/follow")
    public JsonResult follow(@RequestParam("uid")String uid,
                             @RequestParam("uuid")String uuid){
        return service.follow(uid,uuid);
    }
    @RequestMapping("/Unfollow")
    public JsonResult unfollow(@RequestParam("uid")String uid,
                               @RequestParam("uuid")String uuid){
        return service.unfollow(uid,uuid);
    }
    @RequestMapping("/getFans")
    public JsonResult getFans(@RequestParam("uid")String uid){
        return service.getFans(uid);
    }
    @RequestMapping("/getFollowing")
    public JsonResult getFollowing(@RequestParam("uid")String uid){
        return service.getFollowing(uid);
    }
    @RequestMapping("/updateInfo")
    public JsonResult upadateInfo(@RequestParam("uid")String uid,
                                  @RequestParam("name")String name,
                                  @RequestParam("type")String type,
                                  @RequestParam("gender")int gender,
                                  @RequestParam("avatarUrl")String avatarUrl){
        return service.updateInfo(uid,name,type,gender,avatarUrl);
    }
}
