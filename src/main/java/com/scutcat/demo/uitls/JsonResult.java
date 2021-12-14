package com.scutcat.demo.uitls;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYR
 */
@Data
@ApiModel(description = "通用响应类")
public class JsonResult<T> {
    @ApiModelProperty(value = "状态码")
    int status=200;
    @ApiModelProperty(value = "返回消息")
    String message="";
    @ApiModelProperty(value = "返回数据")
    T data;

    /**
     * 成功和失败两种情况的构造函数
     */
    public JsonResult(T data){setData(data);}
    public JsonResult(int code,String msg){setStatus(code);setMessage(msg);}

    public JsonResult<T> success(T data){return new JsonResult<>(data);}
    public JsonResult<T> failed(int code,String msg){return new JsonResult<>(code, msg);}
}
