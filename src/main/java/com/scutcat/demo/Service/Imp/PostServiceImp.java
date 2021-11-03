package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.History;
import com.scutcat.demo.Dto.LikePost;
import com.scutcat.demo.Dto.Post;
import com.scutcat.demo.Dto.UserFollowPost;
import com.scutcat.demo.Mapper.PostMapper;
import com.scutcat.demo.Mapper.UserMapper;
import com.scutcat.demo.Service.PostService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("PostService")
public class PostServiceImp implements PostService {
    @Resource
    PostMapper postMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public JsonResult addPost(String uid, String pid, String content, String tag) {
        postMapper.insertPost(new Post(pid,uid,content,tag));
        if(postMapper.getPost(pid)!=null){
            return new JsonResult(true,"Publish post success!",null);
        }
        return new JsonResult(false,"Publish fail!",null);
    }

    @Override
    public JsonResult deletePost(String uid, String pid) {
        Post post =postMapper.getPost(pid);
        if(post==null)return new JsonResult(false,"Post does not exist",null);
        if(post.getUid().equals(uid)||userMapper.getUser(post.getUid()).getType().equals("Admin")){
            postMapper.deletePost(pid);
        }else{
            return new JsonResult(false,"Deletion fails!Authority deny!",null);
        }
        if(postMapper.getPost(pid)==null){
            return new JsonResult(true,"Deletion successes!",null);
        }else {
            return new JsonResult(false,"Deletion fails!",null);
        }
    }

    @Override
    public JsonResult likePost(String uid, String pid) {
        if(userMapper.getUser(uid)==null)
            return new JsonResult(false, "User does not exist",null);
        Post post=postMapper.getPost(pid);
        if(post==null)
            return new JsonResult(false,"Post does not exist",null);
        if(postMapper.getPostLikeUser(pid).contains(uid)){
            return new JsonResult(false,"User already like it!You may want to call dislike function",null);
        }
        int curr=post.getLike();
        post.setLike(curr+1);
        postMapper.updatePost(post);
        if(postMapper.getPost(pid).getLike()==curr+1){
            postMapper.likePost(new LikePost(uid,pid));
            if(postMapper.getPostLikeUser(pid).contains(uid)&&postMapper.getPost(pid).getLike()==curr+1)
            return new JsonResult(true,"Like for post",null);
        }
        return new JsonResult(false, "Like on post failed!",null);
    }

    @Override
    public JsonResult dislikePost(String uid, String pid) {
        if(userMapper.getUser(uid)==null)
            return new JsonResult(false, "User does not exist",null);
        Post post=postMapper.getPost(pid);
        if(post==null)
            return new JsonResult(false,"Post does not exist",null);
        if(!postMapper.getPostLikeUser(pid).contains(uid))
            return new JsonResult(false,"User does not like the post",null);
        int curr=post.getLike();
        post.setLike(curr-1);
        postMapper.updatePost(post);
        if(postMapper.getPost(pid).getLike()==curr-1){
            postMapper.dislikePost(new LikePost(uid,pid));
            if(!postMapper.getPostLikeUser(pid).contains(uid)&&postMapper.getPost(pid).getLike()==curr-1)
                return new JsonResult(true,"Dislike for post",null);
        }return new JsonResult(false,"Dislike fails",null);
    }

    @Override
    public JsonResult search(String pattern) {
        //搜索tag

        //搜索标题
        //搜索内容
        return null;
    }

    @Override
    public JsonResult getUserAll(String uid) {
        if(userMapper.getUser(uid)==null)return new JsonResult(false,"User does not exist",null);
        return new JsonResult(true,"pid returned",postMapper.getUserAll(uid));
    }

    @Override
    public JsonResult follow(String uid, String pid) {
        if(userMapper.getUser(uid)==null)
            return new JsonResult(false,"User does not exist",null);
        if(postMapper.getPost(pid)==null)
            return new JsonResult(false,"Post not found",null);
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult(false,"Follow already!",null);
        }
        postMapper.follow(new UserFollowPost(uid,pid));
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult(true,"Follow!",null);
        }
        return new JsonResult(false,"Follow failed!",null);
    }

    @Override
    public JsonResult unfollow(String uid, String pid) {
        if(userMapper.getUser(uid)==null)
            return new JsonResult(false,"User does not exist",null);
        if(postMapper.getPost(pid)==null)
            return new JsonResult(false,"Post not found",null);
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult(false,"Have not followed!",null);
        }
        postMapper.unfollow(new UserFollowPost(uid,pid));
        if(!postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult(true,"Unfollow success!",null);
        }
        return new JsonResult(false,"Unfollow failed!",null);
    }

    @Override
    public JsonResult read(String uid, String pid) {
        if(userMapper.getUser(uid)==null){
            return new JsonResult(false,"User not found",null);
        }
        Post post=postMapper.getPost(pid);
        if(post==null){
            return new JsonResult(false,"Post not found",null);
        }
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        History history=new History(uid,pid,ft.format(new Date()));
        postMapper.addHistory(history);
        if(userMapper.getHistory(uid).contains(history)){
            return new JsonResult(true,"Loaded",post);
        }
        return new JsonResult(false,"Cannot add history",post);
    }

    @Override
    public JsonResult getUserFollow(String uid) {
        if(userMapper.getUser(uid)==null){
            return new JsonResult(false,"User not found",null);
        }
        return new JsonResult(true,"Got user's following post",postMapper.getPostFollowedByUser(uid));
    }
}
