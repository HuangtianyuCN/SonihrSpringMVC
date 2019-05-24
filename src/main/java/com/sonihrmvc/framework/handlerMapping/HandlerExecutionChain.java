package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:19
**/

public class HandlerExecutionChain {
    private Object handler;
    private HandlerInterceptor[] interceptors;

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public HandlerInterceptor[] getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(HandlerInterceptor[] interceptors) {
        this.interceptors = interceptors;
    }


}
