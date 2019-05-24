package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 14:25
**/

import java.lang.reflect.Method;

public class RequestMappingHandler {
    private Object bean;//调用者
    private Method method;//调用方法
    private Object[] args;//调用参数

    public RequestMappingHandler(Object bean, Method method, Object[] args) {
        this.bean = bean;
        this.method = method;
        this.args = args;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
