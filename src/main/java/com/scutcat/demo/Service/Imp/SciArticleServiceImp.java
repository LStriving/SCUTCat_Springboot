package com.scutcat.demo.Service.Imp;

import com.scutcat.demo.Dto.SciArticle;
import com.scutcat.demo.Mapper.SciArticleMapper;
import com.scutcat.demo.Service.SciArticleService;
import com.scutcat.demo.uitls.JsonResult;
import com.scutcat.demo.uitls.dropDuplicate;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class SciArticleServiceImp implements SciArticleService {
    @Resource
    SciArticleMapper mapper;

    @Override
    public JsonResult getAll() {
        List<String> res = mapper.getAll();
        if(res!=null)
            return new JsonResult(true,"get all science post",res);
        else return new JsonResult(false,"data access failed!",null);
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
        return new JsonResult(true,"found", dropDuplicate.drop(res));
    }

    @Override
    public JsonResult get(String aid) {
        SciArticle article = mapper.get(aid);
        if (article==null)return new JsonResult(false,"article with aid: "+aid+" not found",null);
        return new JsonResult(true,"found",article);
    }

    @Override
    public JsonResult publish(String aid, String title, String tag, String content) {
        if (mapper.get(aid)!=null)return new JsonResult(false,"Aid conflict,need a different id",null);
        SciArticle sciArticle = new SciArticle(aid, title, tag, content);
        mapper.add(sciArticle);
        if(mapper.get(aid)==null)return new JsonResult(false,"publish failed!",null);
        return new JsonResult(true,"Publish success",null);

    }

    @Override
    public JsonResult delete(String aid) {
        SciArticle article = mapper.get(aid);
        if (article==null)
            return new JsonResult(false,"article not found",null);
        mapper.delete(aid);
        if (mapper.get(aid)==null)
            return new JsonResult(true,"Deletion successes!",null);
        return new JsonResult(false,"Deletion failed!",null);
    }
}
