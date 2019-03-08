package com.yyh.authoritymanagement.service.impl;

import com.yyh.authoritymanagement.bean.Permission;
import com.yyh.authoritymanagement.bean.PermissionExample;
import com.yyh.authoritymanagement.bean.User;
import com.yyh.authoritymanagement.dao.PermissionMapper;
import com.yyh.authoritymanagement.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectByPrimaryKey(username);

        if(user!=null){

            return user;
        }else {
            throw new UsernameNotFoundException("用户名: " + username + " do not exist!");
        }
//        User user=new User("admin1",new BCryptPasswordEncoder().encode("1234568"),null);
//        return user;
//        if (user != null) {
//            return user;
//        } else {
//
//        }
    }
}
