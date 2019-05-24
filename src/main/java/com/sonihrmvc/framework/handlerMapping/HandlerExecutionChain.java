package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:19
**/

import java.util.List;

public class HandlerExecutionChain {
    private RequestMappingHandler handler;
    private List<HandlerInterceptor> interceptors;

    public RequestMappingHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestMappingHandler handler) {
        this.handler = handler;
    }

    public List<HandlerInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
