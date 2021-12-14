package com.scutcat.demo.uitls;

import com.scutcat.demo.Dto.Post;
import com.scutcat.demo.mapper.PostMapper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author LYR
 */
public class UpdateHotScheduler {
    @Resource
    PostMapper mapper;
    //update post hot using the equation
    @Scheduled(cron="0 59 * * * ? *",zone="Asia/Shanghai")
    public void UpdatePostHot() throws ParseException {
        //get all id
        List<String> all= mapper.getAllPost();
        for(String id:all){
            //get post by id
            Post post = mapper.getPost(id);
            //get post last modified time
            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            Date past= df.parse(post.getTime());
            Date now = new Date();
            double hot = (Math.log(1 + post.getRead()) + 1.75 * post.getLike() + 3.2 * post.getFollow())*Math.exp(-(now.getTime()-past.getTime())/86400.0);
            post.setHot(hot);
            mapper.updatePost(post);
        }
    }
}


