package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.Post;
import com.scutcat.demo.Mapper.PostMapper;
import com.scutcat.demo.Service.PostService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("PostService")
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
