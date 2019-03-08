package com.yyh.authoritymanagement.dao;

import com.yyh.authoritymanagement.bean.UserPermissionLink;
import com.yyh.authoritymanagement.bean.UserPermissionLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionLinkMapper {
    long countByExample(UserPermissionLinkExample example);

    int deleteByExample(UserPermissionLinkExample example);

    int deleteByPrimaryKey(@Param("username") String username, @Param("permissionid") Integer permissionid);

    int insert(UserPermissionLink record);

    int insertSelective(UserPermissionLink record);

    List<UserPermissionLink> selectByExample(UserPermissionLinkExample example);

    UserPermissionLink selectByPrimaryKey(@Param("username") String username, @Param("permissionid") Integer permissionid);

    int updateByExampleSelective(@Param("record") UserPermissionLink record, @Param("example") UserPermissionLinkExample example);

    int updateByExample(@Param("record") UserPermissionLink record, @Param("example") UserPermissionLinkExample example);

    int updateByPrimaryKeySelective(UserPermissionLink record);

    int updateByPrimaryKey(UserPermissionLink record);
}