package com.hailin545.simpletest.Service;

import com.hailin545.simpletest.uitls.JsonResult;

public interface PostService {
    JsonResult addPost(String uid,String pid,String content,String tag);

}
