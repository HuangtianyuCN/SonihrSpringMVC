package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 10:18
**/

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
    void init();
}
