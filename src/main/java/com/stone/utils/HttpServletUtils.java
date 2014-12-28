package com.stone.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletUtils {


  public static void putObjectInSession(HttpServletRequest request, String attributeName, Object object){

    HttpSession session = request.getSession();
    session.setAttribute(attributeName, object);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getObjectFromSession(HttpServletRequest request, String attributeName){

    HttpSession session = request.getSession();
    return (T) session.getAttribute(attributeName);
  }

  public static void removeObjectFromSession(HttpServletRequest request, String attributeName){

    HttpSession session = request.getSession();
    session.removeAttribute(attributeName);
  }

  public static String getRequestURIWithParameters(HttpServletRequest request) {

    String requestURI = request.getRequestURI();
    String queryString = request.getQueryString();
    return StringUtils.isBlank(queryString) ? requestURI
        : String.format("%s?%s", requestURI, queryString);
  }

  public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {

    Cookie[] cookies = request.getCookies();
    if (ArrayUtils.isNotEmpty(cookies)) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          return cookie;
        }
      }
    }
    return null;
  }

}
