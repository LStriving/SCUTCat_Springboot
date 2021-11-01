package com.hailin545.simpletest.Service;

import com.hailin545.simpletest.Dto.Comment;
import com.hailin545.simpletest.uitls.JsonResult;

public interface CommentService {
    JsonResult publishComment(Comment comment);
}
