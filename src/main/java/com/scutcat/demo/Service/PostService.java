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
    JsonResult deletePost(String uid, String pid);
    JsonResult likePost(String uid, String pid);
    JsonResult dislikePost(String uid, String pid);
    JsonResult search(String pattern);
    JsonResult getUserAll(String uid);
    JsonResult follow(String uid, String pid);
    JsonResult unfollow(String uid, String pid);
    JsonResult read(String uid, String pid);
    JsonResult getUserFollow(String uid);
    JsonResult sharePost(String pid);
    JsonResult update(String pid, String content, String tag);
    JsonResult getRecommend(String mode, String uid);
}
