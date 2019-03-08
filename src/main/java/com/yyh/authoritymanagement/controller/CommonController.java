package com.yyh.authoritymanagement.controller;

import com.yyh.authoritymanagement.util.SMSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "验证码接口")
@RestController
public class CommonController {
    @RequestMapping("/yzm")
    public String sendYzm(@ApiParam(name = "mobilephone", value = "手机号码")@RequestParam(value = "mobilephone",required = true)String mobilephone, HttpServletRequest request){
        int yzm=(int)((Math.random()+1)*1000);
        request.getSession().setAttribute("yzm",yzm);
        System.out.println("验证码为："+yzm);
        return SMSUtil.sendMessage(mobilephone,yzm+"");
    };
}
