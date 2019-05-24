package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 11:26
**/

import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.factory.AbstractBeanFactory;
import com.sonihr.context.AbstractApplicationContext;
import com.sonihr.context.ApplicationContext;
import com.sonihrmvc.framework.annocation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.*;

public class AnnotationHandlerMapping implements HandlerMapping{
    private ApplicationContext mvcContext;
    private HashMap<String,RequestMappingHandler> handlerRegistry;
    public AnnotationHandlerMapping(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
        this.handlerRegistry = new HashMap<>();
        init();
    }

    public ApplicationContext getMvcContext() {
        return mvcContext;
    }

    public void setMvcContext(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }

    public HashMap<String, RequestMappingHandler> getHandlerRegistry() {
        return handlerRegistry;
    }

    public void setHandlerRegistry(HashMap<String, RequestMappingHandler> handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }


    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {

        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain();
        //System.out.println("uri = "+request.getRequestURI());
        RequestMappingHandler handler = handlerRegistry.get(request.getRequestURI());
        //System.out.println("handler = "+handler);
        handlerExecutionChain.setHandler(handler);
        return handlerExecutionChain;
    }

    @Override
    public void init() {
        AbstractBeanFactory beanFactory = mvcContext.getBeanFactory();
        Map<String, BeanDefinition> map = beanFactory.getBeanDefinitionMap();
        for(Map.Entry<String, BeanDefinition> entry:map.entrySet()){
            String prefix = null;
            String suffix = null;
            Class clazz = entry.getValue().getBeanClass();//通过类名获得前缀
            Object bean = entry.getValue().getBean();
            Annotation annotation = clazz.getAnnotation(RequestMapping.class);
            if(annotation!=null){
                prefix = ((RequestMapping)annotation).value();
            }else{
                continue;
            }
            Method[] methods = clazz.getMethods();//通过方法获得后缀
            for(Method method:methods){
                annotation =method.getAnnotation(RequestMapping.class);
                if(annotation!=null){
                    suffix = ((RequestMapping)annotation).value();
                    String url = prefix + suffix;
                    handlerRegistry.put(url,new RequestMappingHandler(bean,method,null));
                    //System.out.println("url = "+url);
                }else{
                    continue;
                }
            }
        }
    }
}
