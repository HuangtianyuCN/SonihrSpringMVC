package com.sonihrmvc.framework.handlerAdapter;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 22:23
**/

import com.sonihrmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean supports(Object handler);
    ModelAndView handle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception;
}
