package com.yyh.authoritymanagement.service.impl;

import com.yyh.authoritymanagement.bean.Permission;
import com.yyh.authoritymanagement.bean.PermissionExample;
import com.yyh.authoritymanagement.dao.PermissionMapper;
import com.yyh.authoritymanagement.util.UrlMatcherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Service
public class InvocationSecurityMetadataSourceServiceImpl implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UrlMatcherUtil urlMatcher;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    /**
     * 加载权限表中所有权限
     */
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        List<Permission> permissions=permissionMapper.selectByExample(new PermissionExample());
        for(Permission permission:permissions){
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            ConfigAttribute ca = new SecurityConfig(permission.getName());
            atts.add(ca);
            resourceMap.put(permission.getUrl(), atts);
        }
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(resourceMap ==null) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = resourceMap.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return resourceMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}