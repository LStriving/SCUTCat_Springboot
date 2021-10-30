package com.hailin545.simpletest.Service.Imp;

import com.hailin545.simpletest.Dto.Post;
import com.hailin545.simpletest.Mapper.PostMapper;
import com.hailin545.simpletest.Service.PostService;
import com.hailin545.simpletest.uitls.JsonResult;

import javax.annotation.Resource;

public class PostServiceImp implements PostService {
    @Resource
    PostMapper postMapper;

    @Override
    public JsonResult addPost(String uid, String pid, String content, String tag) {
        postMapper.insertPost(new Post(pid,uid,content,tag));
        if(postMapper.getPost(pid)!=null){
            return new JsonResult(true,"Publish post success!",null);
        }
        return new JsonResult(false,"Publish fail!",null);
    }
}
