package com.scutcat.demo.Controller;

import com.scutcat.demo.Service.SciArticleService;
import com.scutcat.demo.uitls.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sciArticle")
public class SciArtController {
    @Resource
    SciArticleService service;

    @RequestMapping("/getAll")
    public JsonResult getAll(){
        return service.getAll();
    }

    @RequestMapping("/search")
    public JsonResult search(@RequestParam("pattern")String pattern){
        return service.search(pattern);
    }
    @RequestMapping("/get")
    public JsonResult get(@RequestParam("aid")String aid){
        return service.get(aid);
    }
    @RequestMapping("/publish")
    public JsonResult publish(@RequestParam("aid")String aid,
                              @RequestParam("title")String title,
                              @RequestParam("tag")String tag,
                              @RequestParam("content")String content){
        return service.publish(aid,title,tag,content);
    }
    @RequestMapping("/delete")
    public JsonResult delete(@RequestParam("aid")String aid){
        return service.delete(aid);
    }

}
