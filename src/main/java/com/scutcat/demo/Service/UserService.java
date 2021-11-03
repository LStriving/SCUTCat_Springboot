package com.scutcat.demo.Service;

import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service()
public interface UserService {
    JsonResult login(String uid);
    JsonResult getUser(String uid);
    JsonResult follow(String uid, String uuid);
    JsonResult unfollow(String uid, String uuid);
    JsonResult getFans(String uid);
    JsonResult getFollowing(String uid);
    JsonResult updateInfo(String uid, String name, String type, int gender, String avatarUrl);
}
