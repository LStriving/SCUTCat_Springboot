package com.scutcat.demo.service;

import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service()
public interface SciArticleService {
    JsonResult getAll();
    JsonResult search(String pattern);
    JsonResult get(String aid);
    JsonResult publish(String aid, String title, String tag, String content);
    JsonResult delete(String aid, String uid);
}
