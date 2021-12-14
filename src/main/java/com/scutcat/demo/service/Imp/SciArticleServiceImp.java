package com.scutcat.demo.service.Imp;

import com.scutcat.demo.dao.SciArticle;
import com.scutcat.demo.mapper.SciArticleMapper;
import com.scutcat.demo.mapper.UserMapper;
import com.scutcat.demo.service.SciArticleService;
import com.scutcat.demo.uitls.JsonResult;
import com.scutcat.demo.uitls.DropDuplicate;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SciArticleService")
public class SciArticleServiceImp implements SciArticleService {
    @Resource
    SciArticleMapper mapper;
    @Resource
    UserMapper userMapper;

    @Override
    public JsonResult getAll() {
        List<String> res = mapper.getAll();
        if(res!=null) {
            return new JsonResult<>(res);
        } else {
            return new JsonResult(404,"data access failed!");
        }
    }

    @Override
    public JsonResult search(String pattern) {
        //all match
        List<String> res = mapper.search(pattern);

        //partially match
        //separate pattern to key word

        //separate pattern and keep all
        //high score match

        //low score match
        Result parse = ToAnalysis.parse(pattern);

        for (Term word:parse.getTerms()){
            res.addAll(mapper.search(word.getName()));
        }
        return new JsonResult<>(DropDuplicate.drop(res));
    }

    @Override
    public JsonResult get(String aid) {
        SciArticle article = mapper.get(aid);
        if (article==null) {
            return new JsonResult<>(404,"article with aid: "+aid+" not found");
        }
        return new JsonResult<>(article);
    }

    @Override
    public JsonResult publish(SciArticle article) {
        if (mapper.get(article.getAid())!=null) {
            return new JsonResult<>(201,"Aid conflict,need a different id");
        }
        try {
            mapper.add(article);
            return new JsonResult<>();
        }catch (Exception e){
            return new JsonResult<>(500, e.getMessage());
        }


    }

    @Override
    public JsonResult delete(String aid, String uid) {
        if(!"Admin".equals(userMapper.getUser(uid).getType())){
           return new JsonResult<>(403,"用户没有权限删除文章");
        }

        SciArticle article = mapper.get(aid);
        if (article==null) {
            return new JsonResult<>(404,"article not found");
        }
        mapper.delete(aid);
        if (mapper.get(aid)==null) {
            return new JsonResult<>(200,"Deletion successes!");
        }
        return new JsonResult<>(500,"Deletion failed!");
    }
}
