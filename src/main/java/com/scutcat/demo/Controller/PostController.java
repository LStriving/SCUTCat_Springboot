package com.scutcat.demo.Controller;

import com.scutcat.demo.Service.PostService;
import com.scutcat.demo.uitls.JsonResult;
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
