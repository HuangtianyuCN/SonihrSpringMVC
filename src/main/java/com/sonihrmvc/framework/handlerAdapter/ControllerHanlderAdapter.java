package com.sonihrmvc.framework.handlerAdapter;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 13:57
**/

import com.sonihr.context.ApplicationContext;
import com.sonihrmvc.framework.handlerMapping.Controller;
import com.sonihrmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerHanlderAdapter extends AbstractHandlerAdapter {
    public ControllerHanlderAdapter(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    public boolean supports(Object handler) {
        if(handler instanceof Controller)
            return true;
        return false;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return null;
    }
}
