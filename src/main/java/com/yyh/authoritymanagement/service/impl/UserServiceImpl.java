package com.yyh.authoritymanagement.service.impl;

import com.yyh.authoritymanagement.beans.User;
import com.yyh.authoritymanagement.beans.UserRoleLink;
import com.yyh.authoritymanagement.dao.RoleMapper;
import com.yyh.authoritymanagement.dao.UserMapper;
import com.yyh.authoritymanagement.dao.UserRoleLinkMapper;
import com.yyh.authoritymanagement.service.UserService;
import com.yyh.authoritymanagement.util.common.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleLinkMapper userRoleLinkMapper;

    @Override
    public User getInfo(String username) {
        return userMapper.selectByPrimaryKey(username);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        String username = user.getUsername();
        if (userMapper.selectByPrimaryKey(username) != null) {
            return "用户已存在";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        int userId=userMapper.insertSelective(user);
        UserRoleLink userRoleLink=new UserRoleLink();
        userRoleLink.setUsername(username);
        int roleId=1;
        userRoleLink.setRoleid(roleId);
        userRoleLinkMapper.insert(userRoleLink);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }
}
