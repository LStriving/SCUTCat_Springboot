package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.Mapper.CommentMapper;
import com.scutcat.demo.Service.CommentService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("CommentService")
public class CommentServiceImp implements CommentService {
    @Resource
    CommentMapper mapper;

    @Override
    public JsonResult publishComment(Comment comment) {
        mapper.addComment(comment);
        if(mapper.getComment(comment.getCid())!=null){
            return new JsonResult(true,"Comment publishes！",null);
        }else{
            return new JsonResult(false,"Publishing comment failed!",null);
        }
    }
}
