package com.sonihrmvc.framework.handlerAdapter;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 13:49
**/


import com.alibaba.fastjson.JSONObject;
import com.sonihr.context.ApplicationContext;
import com.sonihrmvc.framework.annocation.ResponseBody;
import com.sonihrmvc.framework.handlerMapping.RequestMappingHandler;
import com.sonihrmvc.framework.modelAndView.Model;
import com.sonihrmvc.framework.modelAndView.ModelAndView;
import com.sonihrmvc.framework.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class AnnotationHandlerAdapter extends AbstractHandlerAdapter {
    public AnnotationHandlerAdapter(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    public boolean supports(Object handler) {
        if(handler instanceof RequestMappingHandler)
            return true;
        return false;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestMappingHandler rmHandler = (RequestMappingHandler)handler;
        Model model = new Model();
        Object[] args = ArgumentResolverUtil.resloveRequsetParam(request,rmHandler.getMethod(),model);
        Object obj = rmHandler.getMethod().invoke(rmHandler.getBean(),args);
        if(obj==null)
            return null;
        //@ResponseBody
        Annotation annotation = rmHandler.getMethod().getAnnotation(ResponseBody.class);
        if(annotation!=null){
            response.getWriter().write(JSONObject.toJSONString(obj));
            return null;
        }
        if(obj instanceof ModelAndView)
            return (ModelAndView)obj;
        ModelAndView mv = new ModelAndView();
        if(obj instanceof String){
            mv.setModel(model);
            mv.setView((String) obj);
        }

        return mv;
    }

}
