package com.yyh.authoritymanagement.dao;

import com.yyh.authoritymanagement.beans.UserRoleLink;
import com.yyh.authoritymanagement.beans.UserRoleLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleLinkMapper {
    long countByExample(UserRoleLinkExample example);

    int deleteByExample(UserRoleLinkExample example);

    int deleteByPrimaryKey(@Param("username") String username, @Param("roleid") Integer roleid);

    int insert(UserRoleLink record);

    int insertSelective(UserRoleLink record);

    List<UserRoleLink> selectByExample(UserRoleLinkExample example);

    UserRoleLink selectByPrimaryKey(@Param("username") String username, @Param("roleid") Integer roleid);

    int updateByExampleSelective(@Param("record") UserRoleLink record, @Param("example") UserRoleLinkExample example);

    int updateByExample(@Param("record") UserRoleLink record, @Param("example") UserRoleLinkExample example);

    int updateByPrimaryKeySelective(UserRoleLink record);

    int updateByPrimaryKey(UserRoleLink record);
}