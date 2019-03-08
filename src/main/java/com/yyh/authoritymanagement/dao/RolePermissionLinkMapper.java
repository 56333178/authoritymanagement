package com.yyh.authoritymanagement.dao;

import com.yyh.authoritymanagement.bean.RolePermissionLink;
import com.yyh.authoritymanagement.bean.RolePermissionLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionLinkMapper {
    long countByExample(RolePermissionLinkExample example);

    int deleteByExample(RolePermissionLinkExample example);

    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("permissionid") Integer permissionid);

    int insert(RolePermissionLink record);

    int insertSelective(RolePermissionLink record);

    List<RolePermissionLink> selectByExample(RolePermissionLinkExample example);

    RolePermissionLink selectByPrimaryKey(@Param("roleid") Integer roleid, @Param("permissionid") Integer permissionid);

    int updateByExampleSelective(@Param("record") RolePermissionLink record, @Param("example") RolePermissionLinkExample example);

    int updateByExample(@Param("record") RolePermissionLink record, @Param("example") RolePermissionLinkExample example);

    int updateByPrimaryKeySelective(RolePermissionLink record);

    int updateByPrimaryKey(RolePermissionLink record);
}