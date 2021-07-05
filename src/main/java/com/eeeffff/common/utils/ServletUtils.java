/*
 * Copyright (c) 2014 laidian. All Rights Reserved.
 * @author yaoyuming
 */
package com.eeeffff.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet 工具类
 * @author yaoyuming
 */
public class ServletUtils {

      private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

      /**
       * 获取 HttpServletRequest 实例
       * @return
       */
      public static HttpServletRequest getRequest() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return attributes == null ? null : attributes.getRequest();
      }

      /**
       * 获取 HttpServletResponse 实例
       * @return
       */
      public static HttpServletResponse getResponse() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return attributes == null ? null : attributes.getResponse();
      }

      /**
       * 获取 HttpSession 实例
       * @return
       */
      public static HttpSession getSession() {
            return getRequest().getSession();
      }

      /**
       * 判断浏览器的类型(IE/non IE)
       * @return
       */
      public static boolean isIEBrowser() {
            String userAgent = getRequest().getHeader("user-agent");

            logger.info("userAgent = {}", userAgent);

            //IE 判断
            if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
                  return true;
            }
            //Mozilla(火狐,chrome...)
            return false;
      }

      /**
       * 获取请求url
       * @return
       */
      public static String getRequestURL() {
            return getRequest().getRequestURL().toString();
      }

}