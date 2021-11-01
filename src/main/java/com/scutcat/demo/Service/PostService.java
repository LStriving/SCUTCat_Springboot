package com.scutcat.demo.Service;

import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service()
public interface PostService {
    JsonResult addPost(String uid,String pid,String content,String tag);

}
