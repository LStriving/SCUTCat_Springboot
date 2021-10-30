package com.hailin545.simpletest.Controller;

import com.hailin545.simpletest.Service.PostService;
import com.hailin545.simpletest.uitls.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    PostService postService;

    @RequestMapping("/publish")
    public JsonResult publish(@RequestParam("uid")String uid,
                              @RequestParam("pid")String pid,
                              @RequestParam("content")String content,
                              @RequestParam("tag")String tag){
        return postService.addPost(uid,pid,content,tag);
    }
}
