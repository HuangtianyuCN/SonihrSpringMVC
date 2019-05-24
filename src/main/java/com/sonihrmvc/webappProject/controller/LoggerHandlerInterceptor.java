package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:51
**/

import com.sonihrmvc.framework.handlerMapping.HandlerInterceptor;
import com.sonihrmvc.framework.handlerMapping.ModelAndView;
import net.sf.cglib.proxy.MethodProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoggerHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
