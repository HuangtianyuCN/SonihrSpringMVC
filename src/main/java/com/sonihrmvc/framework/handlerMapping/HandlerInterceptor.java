package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:21
**/

import net.sf.cglib.proxy.MethodInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public interface HandlerInterceptor extends MethodInterceptor {
    boolean preHandle(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception;

//    void postHandle(HttpServletRequest var1, HttpServletResponse var2, Object var3, ModelAndView var4) throws Exception;

//    void afterCompletion(HttpServletRequest var1, HttpServletResponse var2, Object var3, Exception var4) throws Exception;

}
