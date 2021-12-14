package com.scutcat.demo.service;

import com.scutcat.demo.dao.User;
import com.scutcat.demo.dao.UserFollow;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author LYR
 */
@Component
@Repository
@Service()
public interface UserService {
    JsonResult login(String uid);
    JsonResult<User> getUser(String uid);
    JsonResult follow(UserFollow userFollow);
    JsonResult unfollow(UserFollow userFollow);
    JsonResult getFans(String uid);
    JsonResult getFollowing(String uid);
    JsonResult updateInfo(User user);
}
