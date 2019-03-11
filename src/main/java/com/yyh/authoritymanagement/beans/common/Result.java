package com.yyh.authoritymanagement.beans.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用于包装接口返回值.
 */
@ApiModel("ResultData")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1225240874203809268L;
    @ApiModelProperty(value = "接口返回数据")
    private T data;
    @ApiModelProperty(value = "接口返回信息", example = "ok")
    private String message = "ok";
    @ApiModelProperty(value = "接口返回状态", example = "200")
    private String code = "200";

    public Result(){}

    public Result(T data){
        this.data = data;
    }

    public Result(String code, String message){
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
