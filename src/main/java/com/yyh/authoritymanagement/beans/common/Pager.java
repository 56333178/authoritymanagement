package com.yyh.authoritymanagement.beans.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * 检索分页
 */
@Getter
@Setter
@ApiModel("Pager")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pager implements Serializable {

  @ApiModelProperty(value = "页码", example = "0")
  @JsonProperty(value = "page")
  private Integer page;
  @ApiModelProperty(value = "单页记录条数", example = "10")
  @JsonProperty(value = "page_size")
  private Integer pageSize;
  @ApiModelProperty(value = "记录总数", example = "0")
  @JsonProperty(value = "total")
  private Integer total;

  public Pager(Integer page, Integer pageSize, long total) {
    this.page = page;
    this.pageSize = pageSize;
    this.total = (int) total;
  }


  public Pager(Page page) {
    this.page = page.getPageable().getPageNumber();
    this.pageSize = page.getPageable().getPageSize();
    this.total = (int) page.getTotalElements();
  }


}
