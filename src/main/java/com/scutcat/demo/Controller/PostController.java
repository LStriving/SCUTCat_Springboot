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

    /**
     * get Post and add it user's view history
     * @param uid user id
     * @param pid post id
     * @return json with data : Post
     */
    @RequestMapping("/read")
    public JsonResult read(@RequestParam("uid")String uid,
                           @RequestParam("pid")String pid){
        return postService.read(uid,pid);
    }
    @RequestMapping("/publish")
    public JsonResult publish(@RequestParam("uid")String uid,
                              @RequestParam("pid")String pid,
                              @RequestParam("content")String content,
                              @RequestParam("tag")String tag){
        return postService.addPost(uid,pid,content,tag);
    }
    @RequestMapping("/delete")
    public JsonResult delete(@RequestParam("uid")String uid,
                             @RequestParam("pid")String pid){
        return postService.deletePost(uid,pid);
    }
    @RequestMapping("/like")
    public JsonResult like(@RequestParam("uid")String uid,
                           @RequestParam("pid")String pid){
        return postService.likePost(uid,pid);
    }
    @RequestMapping("/dislike")
    public JsonResult dislike(@RequestParam("uid")String uid,
                           @RequestParam("pid")String pid){
        return postService.dislikePost(uid,pid);
    }
    @RequestMapping("/search")
    public JsonResult search(@RequestParam("pattern")String pattern){
        return postService.search(pattern);
    }
    /**
     * @param uid user id
     * @return return the id of user's posts order by time
     */
    @RequestMapping("/getUserAll")
    public JsonResult getUserAll(@RequestParam("uid")String uid){
        return postService.getUserAll(uid);
    }
    @RequestMapping("/follow")
    public JsonResult follow(@RequestParam("pid")String pid,
                             @RequestParam("uid")String uid){
        return postService.follow(uid,pid);
    }
    @RequestMapping("/unfollow")
    public JsonResult unfollow(@RequestParam("pid")String pid,
                             @RequestParam("uid")String uid){
        return postService.unfollow(uid,pid);
    }
    @RequestMapping("/getUserFollow")
    public JsonResult getUserFollow(@RequestParam("uid")String uid){
        return postService.getUserFollow(uid);
    }
    @RequestMapping("/share")
    public JsonResult sharePost(@RequestParam("pid")String pid){
        return postService.sharePost(pid);
    }
    @RequestMapping("/update")
    public JsonResult update(@RequestParam("pid")String pid,
                             @RequestParam("content")String content,
                             @RequestParam("tag")String tag){
        return postService.update(pid,content,tag);
    }
}
