package com.scutcat.demo.service.Imp;

import com.scutcat.demo.dao.Comment;
import com.scutcat.demo.dao.LikeComment;
import com.scutcat.demo.dao.User;
import com.scutcat.demo.mapper.CommentMapper;
import com.scutcat.demo.mapper.PostMapper;
import com.scutcat.demo.mapper.UserMapper;
import com.scutcat.demo.service.CommentService;
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
        if(userMapper.getUser(uid)==null) {
            return new JsonResult<>(404, "User does not exist");
        }
        Comment comment=mapper.getComment(cid);
        if(comment==null) {
            return new JsonResult<>(404,"Comment does not exist");
        }
        if(mapper.likeCommentUser(cid).contains(uid)) {
            return new JsonResult<>(201,"Comment has already been liked by the user");
        }
        int curr=comment.getLike();
        comment.setLike(curr+1);
        mapper.updateComment(comment);
        if(mapper.getComment(cid).getLike()==curr+1){
            mapper.likeComment(new LikeComment(uid,cid));
            if(mapper.likeCommentUser(cid).contains(uid)) {
                return new JsonResult<>();
            }
        }
        return new JsonResult<>(500,"Add like fails");
    }

    @Override
    public JsonResult delete(String uid, String cid) {
        User user=userMapper.getUser(uid);
        Comment comment=mapper.getComment(cid);
        if(comment==null){
            return new JsonResult<>(404,"Comment does not exists!");
        }
        if(comment.getUid().equals(uid)||user.getType().equals("Admin")){
            mapper.deleteComment(cid);
            if(mapper.getComment(cid)==null){
                return new JsonResult<>();
            }
        }
        return new JsonResult<>(500,"Comment deletion fails!");
    }

    @Override
    public JsonResult dislike(String uid, String cid) {
        Comment comment=mapper.getComment(cid);
        if(comment==null){
            return new JsonResult<>(404,"Comment does not exist!");
        }
        User user=userMapper.getUser(uid);
        if(user==null){
            return new JsonResult<>(404,"User dose not exist");
        }
        if(mapper.likeCommentUser(cid).contains(uid)){
            int like = comment.getLike();
            comment.setLike(like-1);
            mapper.updateComment(comment);
            mapper.dislikeComment(new LikeComment(uid,cid));
            if(!mapper.likeCommentUser(cid).contains(uid)&&mapper.getComment(cid).getLike()==like-1){
                return new JsonResult<>();
            }
        }
        else {
            return new JsonResult<>(404, "Deletion fails! Because the relationship like does not exist", null);
        }
        return new JsonResult<>(500,"Deletion fails!");
    }

    @Override
    public JsonResult getByPostId(String pid) {
        if(postMapper.getPost(pid)==null) {
            return new JsonResult<>(404,"Post does not exits");
        }
        return new JsonResult<>(mapper.getCommentByPid(pid));
    }

    @Override
    public JsonResult publishComment(Comment comment) {
        if(userMapper.getUser(comment.getUid())==null){
            return new JsonResult<>(404,"User does not exits!");
        }
        if(postMapper.getPost(comment.getPid())==null){
            return new JsonResult<>(404,"Post dose not exists!");
        }
        mapper.addComment(comment);
        if(mapper.getComment(comment.getCid())!=null){
            return new JsonResult<>();
        }else{
            return new JsonResult<>(500,"Publishing comment failed!");
        }
    }

    @Override
    public JsonResult getByPostIdWithTime(String pid) {
        if(postMapper.getPost(pid)==null) {
            return new JsonResult<>(404,"Post does not exits");
        }
        return new JsonResult<>(mapper.getCommentByPidByTime(pid));
    }
}
