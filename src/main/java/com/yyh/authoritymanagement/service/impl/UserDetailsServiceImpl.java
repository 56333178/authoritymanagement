package com.yyh.authoritymanagement.service.impl;

import com.yyh.authoritymanagement.beans.User;
import com.yyh.authoritymanagement.dao.PermissionMapper;
import com.yyh.authoritymanagement.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
