package com.scutcat.demo.dao;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYR
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="SCIArticle对象", description="科普文章信息表")
public class SciArticle {
    @ApiModelProperty(value = "科普文章的id")
    String aid;
    @ApiModelProperty(value = "科普文章的标签列（#分割）")
    String tag;
    @ApiModelProperty(value = "科普文章的内容")
    String content;
    @ApiModelProperty(value = "科普文章的点赞数")
    int like=0;
    @ApiModelProperty(value = "科普文章的标题")
    String title;

    public SciArticle(String aid,String title,String tag,String content){
        setAid(aid);
        setContent(content);
        setTag(tag);
        setTitle(title);
    }

}
