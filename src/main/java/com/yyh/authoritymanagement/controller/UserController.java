package com.yyh.authoritymanagement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yyh.authoritymanagement.bean.User;
import com.yyh.authoritymanagement.dao.UserMapper;
import com.yyh.authoritymanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@Api(tags = "用户信息")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(@ApiParam(name = "用户名") String username,@ApiParam(name = "密码") String password) throws AuthenticationException {
        return userService.login(username, password);
    }
    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @ApiOperation(value = "刷新token")
    @PutMapping(value = "/token")
    public String refreshToken(@ApiParam(name = "验证header")@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }
    @PreAuthorize("#username == authentication.name")
    @PostMapping(value = "/{username}")
    public String getInfo(@PathVariable String username) {
        return JSON.toJSONString(userService.getInfo(username));
    }
    @PostMapping("/")
    public String register(User user,
                           @RequestParam(name="yzm",required = true)String yzm, HttpSession session) {
        String yzmn=String.valueOf(session.getAttribute("yzm"));
        if(!yzmn.equals(yzm)){
            return "error:验证码错误";
        }
        return userService.register(user);
    }
}
