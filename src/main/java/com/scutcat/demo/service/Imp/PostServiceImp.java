package com.scutcat.demo.service.Imp;

import com.scutcat.demo.dao.History;
import com.scutcat.demo.dao.LikePost;
import com.scutcat.demo.dao.Post;
import com.scutcat.demo.dao.UserFollowPost;
import com.scutcat.demo.mapper.PostMapper;
import com.scutcat.demo.mapper.UserMapper;
import com.scutcat.demo.service.PostService;
import com.scutcat.demo.uitls.JsonResult;
import com.scutcat.demo.uitls.DropDuplicate;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LYR
 */
@Service("PostService")
public class PostServiceImp implements PostService {
    @Resource
    PostMapper postMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public JsonResult update(String pid, String content, String tag) {
        Post post = postMapper.getPost(pid);
        if(post==null){
            return new JsonResult<>(404,"Post not found");
        }else{
            post.setContent(content);
            post.setTag(tag);
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            post.setTime(ft.format(new Date()));
            postMapper.updatePost(post);
            return new JsonResult<>();
        }
    }

    @Override
    public JsonResult getRecommend(String mode, String uid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404,"User not found");
        }
        switch (mode) {
            case "like":
                return new JsonResult<>( postMapper.getRecommendByLike(uid));
            case "time":
                return new JsonResult<>( postMapper.getRecommendByTime(uid));
            case "hot":
                return new JsonResult<>( postMapper.getRecommendByHot(uid));
            default:
                return new JsonResult<>(200,"Unknown mode, return data sorted by time",postMapper.getRecommendByTime(uid));
        }
    }

    @Override
    public JsonResult addPost(String uid, String pid, String content, String tag) {
        postMapper.insertPost(new Post(pid,uid,content,tag));
        if(postMapper.getPost(pid)!=null){
            return new JsonResult<>();
        }
        return new JsonResult<>(500,"Publish fail!");
    }

    @Override
    public JsonResult deletePost(String uid, String pid) {
        Post post =postMapper.getPost(pid);
        if(post==null) {
            return new JsonResult<>(404,"Post does not exist");
        }
        if(post.getUid().equals(uid)||userMapper.getUser(post.getUid()).getType().equals("Admin")){
            postMapper.deletePost(pid);
        }else{
            return new JsonResult<>(403,"Deletion fails!Authority deny!");
        }
        if(postMapper.getPost(pid)==null){
            return new JsonResult<>();
        }else {
            return new JsonResult<>(500,"Deletion fails!");
        }
    }

    @Override
    public JsonResult likePost(String uid, String pid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404, "User does not exist");
        }
        Post post=postMapper.getPost(pid);
        if(post==null) {
            return new JsonResult<>(404,"Post does not exist");
        }
        if(postMapper.getPostLikeUser(pid).contains(uid)){
            return new JsonResult<>(201,"User already like it!You may want to call dislike function");
        }
        int curr=post.getLike();
        post.setLike(curr+1);
        postMapper.updatePost(post);
        if(postMapper.getPost(pid).getLike()==curr+1){
            postMapper.likePost(new LikePost(uid,pid));
            if(postMapper.getPostLikeUser(pid).contains(uid)&&postMapper.getPost(pid).getLike()==curr+1) {
                return new JsonResult<>();
            }
        }
        return new JsonResult<>(500, "Like on post failed!");
    }

    @Override
    public JsonResult dislikePost(String uid, String pid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404, "User does not exist");
        }
        Post post=postMapper.getPost(pid);
        if(post==null) {
            return new JsonResult<>(404,"Post does not exist");
        }
        if(!postMapper.getPostLikeUser(pid).contains(uid)) {
            return new JsonResult<>(404,"User does not like the post");
        }
        int curr=post.getLike();
        post.setLike(curr-1);
        postMapper.updatePost(post);
        if(postMapper.getPost(pid).getLike()==curr-1){
            postMapper.dislikePost(new LikePost(uid,pid));
            if(!postMapper.getPostLikeUser(pid).contains(uid)&&postMapper.getPost(pid).getLike()==curr-1) {
                return new JsonResult<>();
            }
        }return new JsonResult<>(500,"Dislike fails");
    }

    @Override
    public JsonResult search(String pattern) {
        //tag,content,title
        //all match
        List<String> res = postMapper.search(pattern);
        //low score match
        Result parse = ToAnalysis.parse(pattern);
        for (Term word:parse.getTerms()){
            res.addAll(postMapper.search(word.getName()));
        }

        return new JsonResult<>(DropDuplicate.drop(res));
    }

    @Override
    public JsonResult getUserAll(String uid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404,"User does not exist");
        }
        return new JsonResult<>(postMapper.getUserAll(uid));
    }

    @Override
    public JsonResult follow(String uid, String pid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404,"User does not exist");
        }
        if(postMapper.getPost(pid)==null) {
            return new JsonResult<>(404,"Post not found");
        }
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult<>(201,"Follow already!");
        }
        postMapper.follow(new UserFollowPost(uid,pid));
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult<>();
        }
        return new JsonResult<>(500,"Follow failed!");
    }

    @Override
    public JsonResult unfollow(String uid, String pid) {
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404,"User does not exist");
        }
        if(postMapper.getPost(pid)==null) {
            return new JsonResult<>(404,"Post not found");
        }
        if(postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult<>(404,"Have not followed!");
        }
        postMapper.unfollow(new UserFollowPost(uid,pid));
        if(!postMapper.getPostFollowUser(pid).contains(uid)){
            return new JsonResult<>();
        }
        return new JsonResult<>(500,"Unfollow failed!");
    }

    @Override
    public JsonResult read(String uid, String pid) {
        if(userMapper.getUser(uid)==null){
            return new JsonResult<>(404,"User not found");
        }
        Post post=postMapper.getPost(pid);
        if(post==null){
            return new JsonResult<>(404,"Post not found");
        }
        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        History history=new History(uid,pid,ft.format(new Date()));
        postMapper.addHistory(history);
        if(userMapper.getHistory(uid).contains(history)){
            return new JsonResult<>(post);
        }
        return new JsonResult<>(500,"Cannot add history",post);
    }

    @Override
    public JsonResult getUserFollow(String uid) {
        if(userMapper.getUser(uid)==null){
            return new JsonResult<>(404,"User not found");
        }
        return new JsonResult<>(postMapper.getPostFollowedByUser(uid));
    }

    @Override
    public JsonResult sharePost(String pid) {
        Post post = postMapper.getPost(pid);
        if (post==null) {
            return new JsonResult<>(404,"Post not found");
        }
        post.setShare(post.getShare()+1);
        postMapper.updatePost(post);
        return new JsonResult<>();
    }
}
