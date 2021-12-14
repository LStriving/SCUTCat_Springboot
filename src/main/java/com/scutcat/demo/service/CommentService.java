package com.scutcat.demo.service;

import com.scutcat.demo.dao.Comment;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service()
public interface CommentService {
    JsonResult publishComment(Comment comment);
    JsonResult like(String uid, String cid);
    JsonResult delete(String uid, String cid);
    JsonResult dislike(String uid, String cid);
    JsonResult getByPostId(String pid);
    JsonResult getByPostIdWithTime(String pid);
}
