package com.yyh.authoritymanagement.util;

import org.springframework.stereotype.Component;

@Component
public class UrlPathMatcherUtil implements UrlMatcherUtil {
    @Override
    public Object compile(String paramString) {
        return null;
    }

    @Override
    public boolean pathMatchesUrl(Object paramObject, String paramString) {
        return false;
    }

    @Override
    public String getUniversalMatchPattern() {
        return null;
    }

    @Override
    public boolean requiresLowerCaseUrl() {
        return false;
    }
}
