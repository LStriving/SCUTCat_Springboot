package com.scutcat.demo.controller;

import com.scutcat.demo.service.PostService;
import com.scutcat.demo.uitls.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LYR
 */
@RestController
@Api(tags = "帖子管理")
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
    @GetMapping("/read/{uid}/{pid}")
    @ApiOperation(value = "获取帖子的数据", notes = "返回文章")
    public JsonResult read(@PathVariable("uid")@NotNull(message = "用户ID不能为空") String uid,
                           @PathVariable("pid")@NotNull(message = "文章ID不能为空") String pid){
        return postService.read(uid,pid);
    }
    @PostMapping("/publish")
    @ApiOperation(value = "发布帖子",notes = "参数的设置有待修改")
    public JsonResult publish(@RequestParam("uid")String uid,
                              @RequestParam("pid")String pid,
                              @RequestParam("content")String content,
                              @RequestParam("tag")String tag){
        return postService.addPost(uid,pid,content,tag);
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除帖子",notes = "真删除，不可恢复，后续会考虑加入伪删除接口")
    public JsonResult delete(@RequestParam("uid")@NotNull(message = "用户的id不能为空") String uid,
                             @RequestParam("pid")@NotNull(message = "帖子的id不能为空")String pid){
        return postService.deletePost(uid,pid);
    }
    @PostMapping("/like/{uid}/{pid}")
    @ApiOperation(value = "用户点赞帖子",notes = "用户id在前")
    public JsonResult like(@PathVariable("uid")@NotNull(message = "用户的id不能为空") String uid,
                           @PathVariable("pid")@NotNull(message = "帖子的id不能为空") String pid){
        return postService.likePost(uid,pid);
    }
    @PostMapping("/dislike")
    @ApiOperation(value = "用户取消点赞帖子")
    public JsonResult dislike(@RequestParam("uid")@NotNull(message = "用户的id不能为空")String uid,
                           @RequestParam("pid")@NotNull(message = "帖子的id不能为空")String pid){
        return postService.dislikePost(uid,pid);
    }
    @GetMapping("/search")
    @ApiOperation(value = "搜索相关的帖子",notes = "可以在文章的标题，内容，标签中查找")
    public JsonResult search(@RequestParam("pattern")String pattern){
        return postService.search(pattern);
    }
    /**
     * @param uid user id
     * @return return the id of user's posts order by time
     */
    @GetMapping("/getUserAll/{uid}")
    @ApiOperation(value = "获取用户所有的文章",notes = "按照用户发表的时间排序")
    public JsonResult getUserAll(@PathVariable("uid")@NotNull(message = "用户ID不能为空")String uid){
        return postService.getUserAll(uid);
    }

    @PostMapping("/follow/{pid}/{uid}")
    @ApiOperation(value = "用户订阅跟踪帖子")
    public JsonResult follow(@PathVariable("pid")@NotNull(message = "文章ID不能为空")String pid,
                             @PathVariable("uid")@NotNull(message = "用户ID不能为空")String uid){
        return postService.follow(uid,pid);
    }
    @PostMapping("/unfollow/{pid}/{uid}")
    @ApiOperation(value = "用户取消订阅跟踪帖子")
    public JsonResult unfollow(@PathVariable("pid")String pid,
                             @PathVariable("uid")String uid){
        return postService.unfollow(uid,pid);
    }
    @GetMapping("/UserFollow/{uid}")
    @ApiOperation(value = "获取关注的所有帖子id")
    public JsonResult getUserFollow(@PathVariable("uid")@NotNull(message = "用户ID不能为空")String uid){
        return postService.getUserFollow(uid);
    }
    @PostMapping("/share/{pid}")
    @ApiOperation(value = "用户分享帖子")
    public JsonResult sharePost(@PathVariable("pid")@NotNull(message = "文章ID不能为空")String pid){
        return postService.sharePost(pid);
    }
    @PostMapping("/update")
    public JsonResult update(@RequestParam("pid")String pid,
                             @RequestParam("content")String content,
                             @RequestParam("tag")String tag){
        return postService.update(pid,content,tag);
    }

    /**
     *
     * @param mode:Support three mode: time,like,hot
     * @param uid: User Id
     * @return
     */
    @GetMapping("/recommend/{uid}")
    @ApiOperation(value = "获取推荐帖子的id",notes = "推荐的方式有多种选择，默认为最新发布")
    @ApiImplicitParam(name = "mode", value = "time,like,hot", paramType = "query", dataType = "String")
    public JsonResult getRecommend(@RequestParam("mode")String mode,@PathVariable("uid")String uid){
        return postService.getRecommend(mode,uid);
    }
}
