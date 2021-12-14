package com.scutcat.demo.controller;

import com.scutcat.demo.dao.Comment;
import com.scutcat.demo.service.CommentService;
import com.scutcat.demo.uitls.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author LYR
 */
@RestController
@Api(tags = "评论管理")
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService service;

    @PostMapping("/like/{uid}/{cid}")
    @ApiOperation(value = "用户点赞评论")
    public JsonResult like(@PathVariable("uid")@NotNull(message = "用户的id不能为空") String uid,
                           @PathVariable("cid")@NotNull(message = "评论的id不能为空") String cid){
        return service.like(uid,cid);
    }
    @PostMapping("/dislike/{uid}/{cid}")
    @ApiOperation(value = "取消点赞",notes = "后续可能考虑用delete请求方式替代")
    public JsonResult dislike(@PathVariable("uid")@NotNull(message = "用户的id不能为空")String uid,
                              @PathVariable("cid")@NotNull(message = "评论的id不能为空")String cid){
        return service.dislike(uid,cid);
    }
    @PostMapping("/delete/{uid}/{cid}")
    @ApiOperation(value = "删除评论",notes = "只有用户本人和管理者拥有权限")
    public JsonResult delete(@PathVariable("uid")@NotNull(message = "用户的id不能为空")String uid,
                             @PathVariable("cid")@NotNull(message = "评论的id不能为空")String cid){
        return service.delete(uid,cid);
    }
    @PostMapping("/publish/{uid}/{cid}")
    @ApiOperation(value = "发表评论",notes = "后续请求参数可能改变")
    public JsonResult publish(@PathVariable("uid")@NotNull(message = "用户的id不能为空")String uid,
                              @PathVariable("cid")@NotNull(message = "评论的id不能为空")String cid,
                              @RequestParam("content")String content,
                              @RequestParam("pid")String pid){
        return service.publishComment(new Comment(cid,pid,uid,content));
    }

    /**
     * @param pid post id
     * @return list of cid order by `like` on descend
     */
    @GetMapping("/access/{pid}")
    @ApiOperation(value = "获取相应帖子的评论",notes = "默认按点赞数降序排序")
    public JsonResult getByPostId(@PathVariable("pid")@NotNull(message = "帖子的id不能为空")String pid){
        return service.getByPostId(pid);
    }

    /**
     *
     * @param pid post id
     * @return list of cid order by `time` on descend
     */
    @GetMapping("/accessWithTime/{pid}")
    @ApiOperation(value = "按时间获取评论的id")
    public JsonResult getByPidWithTime(@PathVariable("pid")@NotNull(message = "帖子的id不能为空")String pid){
        return service.getByPostIdWithTime(pid);
    }
}
