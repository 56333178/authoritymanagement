package com.yyh.authoritymanagement.beans.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ·
 * 接口返回值类
 */
@Getter
@Setter
@ToString
@ApiModel("ResultData")
public class ResultData<T> implements Serializable {

  @ApiModelProperty(value = "接口返回状态", example = "200")
  protected Integer code;
  @ApiModelProperty(value = "接口返回数据")
  protected T       data;
  @ApiModelProperty(value = "接口返回信息", example = "ok")
  protected String  msg;

  public ResultData() {
    this.msg = Msg.EMPTY.getMsg();
    this.code = Msg.EMPTY.getCode();
  }


  public ResultData(Msg msg, String s) {
    this.data = null;
    this.msg = msg.getMsg() + ":" + s;
    this.code = msg.getCode();
  }


  public ResultData(T data, String msg, Integer code) {
    this.data = data;
    this.msg = msg;
    this.code = code;
  }


  public ResultData(Msg msg) {
    this.data = null;
    this.msg = msg.getMsg();
    this.code = msg.getCode();
  }


  public ResultData(@NotNull T data) {
    this.data = data;
    this.msg = Msg.OK.getMsg();
    this.code = Msg.OK.getCode();
  }


  /**
   *
   */
  @Deprecated
  public ResultData(@NotNull T data, Pager pager) {
    this.data = data;
    this.msg = Msg.OK.getMsg();
    this.code = Msg.OK.getCode();
  }


  public ResultData(T data, Msg msg) {
    this.data = data;
    this.msg = msg.getMsg();
    this.code = msg.getCode();
  }


}
