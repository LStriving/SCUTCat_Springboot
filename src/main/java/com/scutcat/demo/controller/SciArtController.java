package com.scutcat.demo.controller;

import com.scutcat.demo.dao.SciArticle;
import com.scutcat.demo.service.SciArticleService;
import com.scutcat.demo.uitls.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author LYR
 */
@RestController
@Api(tags = "科普管理")
@RequestMapping("/sciArticle")
public class SciArtController {
    @Resource
    SciArticleService service;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有科普文章的id")
    public JsonResult getAll(){
        return service.getAll();
    }

    @RequestMapping("/search")
    @ApiOperation(value = "根据关键字搜索相关科普文章",notes = "在科普的标题，内容和标签中查找")
    public JsonResult search(@RequestParam("pattern")String pattern){
        return service.search(pattern);
    }

    @GetMapping("/article/{aid}")
    @ApiOperation(value = "根据id获取科普文章")
    public JsonResult get(@PathVariable("aid")String aid){
        return service.get(aid);
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发表科普文章",notes="认证用户才拥有此权力")
    public JsonResult publish(@RequestBody SciArticle article){
        return service.publish(article);
    }

    @RequestMapping("/delete/{aid}/{uid}")
    @ApiOperation(value = "删除科普文章",notes = "认证用户才拥有此权力")
    public JsonResult delete(@PathVariable("aid")@NotNull(message = "文章的id不能为空") String aid,
                             @PathVariable("uid")@NotNull(message = "用户的id不能为空")String uid){
        return service.delete(aid, uid);
    }

}
