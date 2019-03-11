package com.yyh.authoritymanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyh.authoritymanagement.beans.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello")
    String getUser(User user, HttpServletRequest request) throws Exception {
        UserDetails user1= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return JSONObject.toJSONString(user1);
    }
}
