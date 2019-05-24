package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 19:28
**/

import com.sonihr.beans.annotation.Component;
import com.sonihrmvc.framework.handlerMapping.HandlerInterceptor;
import com.sonihrmvc.framework.handlerMapping.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TransactionHandlerInterceptor implements HandlerInterceptor {
    @Override
    public String[] getPath() {
        return new String[]{"/mvc/person/"};
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Transaction的preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Transaction的postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Transaction的afterCompletion");
    }
}
