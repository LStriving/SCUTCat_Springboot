package com.hailin545.simpletest.Service.Imp;

import com.hailin545.simpletest.Dto.Comment;
import com.hailin545.simpletest.Mapper.CommentMapper;
import com.hailin545.simpletest.Service.CommentService;
import com.hailin545.simpletest.uitls.JsonResult;

import javax.annotation.Resource;

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
