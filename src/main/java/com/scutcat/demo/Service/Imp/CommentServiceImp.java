package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.Dto.LikeComment;
import com.scutcat.demo.Dto.User;
import com.scutcat.demo.Mapper.CommentMapper;
import com.scutcat.demo.Mapper.PostMapper;
import com.scutcat.demo.Mapper.UserMapper;
import com.scutcat.demo.Service.CommentService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("CommentService")
public class CommentServiceImp implements CommentService {
    @Resource
    CommentMapper mapper;
    @Resource
    UserMapper userMapper;
    @Resource
    PostMapper postMapper;
    @Override
    public JsonResult like(String uid, String cid) {
        if(userMapper.getUser(uid)==null)
            return new JsonResult(false, "User does not exist",null);
        Comment comment=mapper.getComment(cid);
        if(comment==null)
            return new JsonResult(false,"Comment does not exist",null);
        if(mapper.likeCommentUser(cid).contains(uid))
            return new JsonResult(false,"Comment has already been liked by the user",null);
        int curr=comment.getLike();
        comment.setLike(curr+1);
        mapper.updateComment(comment);
        if(mapper.getComment(cid).getLike()==curr+1){
            mapper.likeComment(new LikeComment(uid,cid));
            if(mapper.likeCommentUser(cid).contains(uid))
            return new JsonResult(true,"Add comment like success",null);
        }
        return new JsonResult(false,"Add like fails",null);
    }

    @Override
    public JsonResult delete(String uid, String cid) {
        User user=userMapper.getUser(uid);
        Comment comment=mapper.getComment(cid);
        if(comment==null){
            return new JsonResult(false,"Comment does not exists!",null);
        }
        if(comment.getUid().equals(uid)||user.getType().equals("Admin")){
            mapper.deleteComment(cid);
            if(mapper.getComment(cid)==null){
                return new JsonResult(true,"Comment deleted!",null);
            }
        }
        return new JsonResult(false,"Comment deletion fails!",null);
    }

    @Override
    public JsonResult dislike(String uid, String cid) {
        Comment comment=mapper.getComment(cid);
        if(comment==null){
            return new JsonResult(false,"Comment does not exist!",null);
        }
        User user=userMapper.getUser(uid);
        if(user==null){
            return new JsonResult(false,"User dose not exist",null);
        }
        if(mapper.likeCommentUser(cid).contains(uid)){
            int like = comment.getLike();
            comment.setLike(like-1);
            mapper.updateComment(comment);
            mapper.dislikeComment(new LikeComment(uid,cid));
            if(!mapper.likeCommentUser(cid).contains(uid)&&mapper.getComment(cid).getLike()==like-1){
                return new JsonResult(true,"Dislike comment!",null);
            }
        }
        else {
            return new JsonResult(false, "Deletion fails! Because the relationship like does not exist", null);
        }
        return new JsonResult(false,"Deletion fails!",null);
    }

    @Override
    public JsonResult getByPostId(String pid) {
        if(postMapper.getPost(pid)==null)return new JsonResult(false,"Post does not exits",null);
        return new JsonResult(true,"comment id returned",mapper.getCommentByPid(pid));
    }

    @Override
    public JsonResult publishComment(Comment comment) {
        if(userMapper.getUser(comment.getUid())==null){
            return new JsonResult(false,"User does not exits!",null);
        }
        if(postMapper.getPost(comment.getPid())==null){
            return new JsonResult(false,"Post dose not exists!",null);
        }
        mapper.addComment(comment);
        if(mapper.getComment(comment.getCid())!=null){
            return new JsonResult(true,"Comment publishes！",null);
        }else{
            return new JsonResult(false,"Publishing comment failed!",null);
        }
    }

    @Override
    public JsonResult getByPostIdWithTime(String pid) {
        if(postMapper.getPost(pid)==null)return new JsonResult(false,"Post does not exits",null);
        return new JsonResult(true,"comment id returned",mapper.getCommentByPidByTime(pid));
    }
}
