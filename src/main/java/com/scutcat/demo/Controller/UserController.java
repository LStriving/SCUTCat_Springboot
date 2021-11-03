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

    @RequestMapping("getInfo")
    public JsonResult getInfo(@RequestParam("uid")String uid){
        return service.getUser(uid);
    }

}
