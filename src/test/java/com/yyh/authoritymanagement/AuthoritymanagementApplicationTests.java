package com.yyh.authoritymanagement;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyh.authoritymanagement.bean.*;
import com.yyh.authoritymanagement.dao.PermissionMapper;
import com.yyh.authoritymanagement.dao.RoleMapper;
import com.yyh.authoritymanagement.dao.UserMapper;
import com.yyh.authoritymanagement.dao.UserRoleLinkMapper;
import com.yyh.authoritymanagement.util.SMSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthoritymanagementApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleLinkMapper userRoleLinkMapper;
	@Test
	public void contextLoads() {
	/*	User u=new User();
		u.setUsername("admin");
		u.setEmail("56333178@qq.com");
		u.setMobilephone("18951729400");
		u.setIdcard("320382199010011632");
		u.setPassword(new BCryptPasswordEncoder().encode("123456"));
		userMapper.insert(u);*/
		/*User u=userMapper.selectByPrimaryKey("admin");
		u.setPassword(new BCryptPasswordEncoder().encode("123456"));
		userMapper.updateByPrimaryKey(u);*/
//		Permission permission=new Permission();
//		permission.setUrl("/hello");
//		permission.setName("hello");
//		permission.setPid("0");
//		permissionMapper.insert(permission);
//		User user=userMapper.selectByPrimaryKey("admin");
//		System.out.println();
//		Role role=new Role();
//		role.setName("超级管理员");
//		role.setDescription("拥有系统所有权限");
//		System.out.println(roleMapper.insert(role));
//		role.setPermissions(permissionMapper.selectByExample(new PermissionExample()));
//		User user=userMapper.selectByPrimaryKey("admin");
//		UserRoleLink userRoleLink=new UserRoleLink();
//		userRoleLink.setRoleid(1);
//		userRoleLink.setUsername(user.getUsername());
//		userRoleLinkMapper.insert(userRoleLink);
//		SMSUtil.sendMessage("18951729400");
		PageHelper.startPage(2,1);
		List<Role> roles=roleMapper.selectByExample(new RoleExample());
		PageInfo<Role> pageInfo=new PageInfo<>(roles);
		System.out.println();

	}

}

