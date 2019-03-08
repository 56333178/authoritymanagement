package com.yyh.authoritymanagement.util;

public interface UrlMatcherUtil  {
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversalMatchPattern();
    boolean requiresLowerCaseUrl();
}
