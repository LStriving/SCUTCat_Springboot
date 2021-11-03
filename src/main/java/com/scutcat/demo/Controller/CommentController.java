package com.scutcat.demo.Controller;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.Service.CommentService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService service;

    @RequestMapping("/like")
    public JsonResult like(@RequestParam("uid")String uid,
                           @RequestParam("cid")String cid){
        return service.like(uid,cid);
    }
    @RequestMapping("/dislike")
    public JsonResult dislike(@RequestParam("uid")String uid,
                              @RequestParam("cid")String cid){
        return service.dislike(uid,cid);
    }
    @RequestMapping("/delete")
    public JsonResult delete(@RequestParam("uid")String uid,
                             @RequestParam("cid")String cid){
        return service.delete(uid,cid);
    }
    @RequestMapping("/publish")
    public JsonResult publish(@RequestParam("uid")String uid,
                              @RequestParam("cid")String cid,
                              @RequestParam("content")String content,
                              @RequestParam("pid")String pid){
        return service.publishComment(new Comment(cid,pid,uid,content));
    }

    /**
     * @param pid post id
     * @return list of cid order by `like` on descend
     */
    @RequestMapping("/getByPostId")
    public JsonResult getByPostId(@RequestParam("pid")String pid){
        return service.getByPostId(pid);
    }

    /**
     *
     * @param pid post id
     * @return list of cid order by `time` on descend
     */
    @RequestMapping("/getByPIdOrderWithTime")
    public JsonResult getByPIDWithTime(@RequestParam("pid")String pid){
        return service.getByPostIdWithTime(pid);
    }
}
