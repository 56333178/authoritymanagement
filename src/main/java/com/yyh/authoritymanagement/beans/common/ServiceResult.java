package com.yyh.authoritymanagement.beans.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by dtyhe on 2017/8/21.
 */
@ApiModel
public class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "查询成功", example = "true")
    private boolean success = true;
    @ApiModelProperty
    private String msg = "";
    @ApiModelProperty
    private T data;
    @ApiModelProperty(value = "当前页", example = "0")
    private Integer pageIndex = 0;
    @ApiModelProperty(value = "页数大小", example = "10")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "总页数", example = "0")
    private Integer totalPage = 0;
    @ApiModelProperty(value = "总数", example = "0")
    private long total = 0;
    @ApiModelProperty(value = "附加信息")
    private String extendInfo;

    public ServiceResult() {

    }

    public ServiceResult(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }

    public ServiceResult(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public ServiceResult(T data, Integer pageIndex, Integer pageSize, Integer totalPage, long total) {
        super();
        this.data = data;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
