package com.scutcat.demo.Service;

import com.scutcat.demo.Dto.Comment;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service()
public interface CommentService {
    JsonResult publishComment(Comment comment);
}
