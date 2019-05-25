package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 17:21
**/

import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.annotation.Component;
import com.sonihr.beans.factory.AbstractBeanFactory;
import com.sonihr.context.ApplicationContext;
import com.sonihrmvc.framework.annocation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractHandlerMapping implements HandlerMapping {
    protected ApplicationContext mvcContext;
    protected static HashMap<String,Object> handlerRegistry = new HashMap<>();;
    protected static List<HandlerInterceptor> handlerInterceptors = new ArrayList<>();


    public AbstractHandlerMapping(ApplicationContext mvcContext){
        this.mvcContext = mvcContext;
    }

    public ApplicationContext getMvcContext() {
        return mvcContext;
    }

    public void setMvcContext(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }

    public HashMap<String, Object> getHandlerRegistry() {
        return handlerRegistry;
    }

    public void setHandlerRegistry(HashMap<String, Object> handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain();
        Object handler = handlerRegistry.get(request.getRequestURI());
        handlerExecutionChain.setHandler(handler);
        List<HandlerInterceptor> interceptors = filterWithUrl(handlerInterceptors,request.getRequestURI());
        handlerExecutionChain.setInterceptors(interceptors);
        return handlerExecutionChain;
    }

    private List<HandlerInterceptor> filterWithUrl(List<HandlerInterceptor> handlerInterceptors,String uri){
        List<HandlerInterceptor> res = new ArrayList<>();
        for(HandlerInterceptor interceptor:handlerInterceptors){
            boolean isMatch = false;
            for(String str : interceptor.getPath()){
                //System.out.println("uri="+uri+"  ,  "+"str="+str);
                if(uri.contains(str)){
                    isMatch = true;
                    break;
                }
            }
            if(isMatch){
                res.add(interceptor);
            }
        }
        return res;
    }

    protected void registryURLAndHandler(){};

    public void init(){
        registryURLAndHandler();//注册map，保存url和handler的对应关系
        initHandlerInterceptors();
    };

    private void initHandlerInterceptors() {
        try {
            if(handlerInterceptors.isEmpty())
                handlerInterceptors = mvcContext.getBeanFactory().getBeansForType(HandlerInterceptor.class);
            //System.out.println(handlerInterceptors.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
