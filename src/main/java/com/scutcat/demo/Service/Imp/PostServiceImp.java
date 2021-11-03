package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.LikePost;
import com.scutcat.demo.Dto.Post;
import com.scutcat.demo.Mapper.PostMapper;
import com.scutcat.demo.Mapper.UserMapper;
import com.scutcat.demo.Service.PostService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        int curr=post.getLike();
        post.setLike(curr+1);
        postMapper.updatePost(post);
        if(postMapper.getPost(pid).getLike()==curr+1){
            postMapper.likePost(new LikePost(uid,pid));
            if(postMapper.getPostLikeUser(pid).contains(uid))
            return new JsonResult(true,"Like for post",null);
        }
        return new JsonResult(false, "Like on post failed!",null);
    }
}
