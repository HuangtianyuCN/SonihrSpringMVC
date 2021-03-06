package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:21
**/

import com.sonihrmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerInterceptor{

    String[] getPath();//该方法规定被拦截的地址

    //该方法在请求处理之前调用，返回true表示交给下一个拦截器，返回false表示到此为止
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    //视图返回之后，渲染之前被调用
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;

}
