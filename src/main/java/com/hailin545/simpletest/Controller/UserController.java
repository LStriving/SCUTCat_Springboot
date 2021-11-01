package com.hailin545.simpletest.Controller;

import com.hailin545.simpletest.Service.UserService;
import com.hailin545.simpletest.uitls.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService service;


}
