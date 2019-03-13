package com.yyh.authoritymanagement.beans.common;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

/**
 * 接口信息
 */
@Getter
@ToString
@ApiModel(value = "接口信息")
public enum Msg {

  SaveFailed("保存失败", 40501),

  DeleteFailed("删除失败", 40502),

  UpdateFailed("更新失败", 40503),

  QueryFailed("查询失败", 40504),

  OK("", 200),

  PARAM("参数错误", 201),

  ERROR("后端接口报错", 202),

  EMPTY("无数据", 203),

  FORWARD("第三方接口访问不了", 204),

  AUTH_ERROR("身份认证失败", 205),

  NO_PERMISSION("无权限", 206),

  FILE_TYPE_ERROR("文件类型错误", 201001),

  METHOD_NOT_ALLOWED("请求方式不支持", 201002),

  SOLVED_QUESTION("已解决的问题不能回答", 202001),

  CANNOT_DELETE_ANSWER("已采纳的回答不能删除", 202002),

  THIRD_PARTY_DATA_ERR("第三方接口获取数据错误", 204001),

  THIRD_PARTY_AUTH_ERR("第三方授权错误", 204002);


  private Integer code;
  private String  msg;

  Msg(String msg, Integer code) {
    this.msg = msg;
    this.code = code;
  }


}
