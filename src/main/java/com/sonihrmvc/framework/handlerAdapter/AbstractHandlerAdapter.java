package com.sonihrmvc.framework.handlerAdapter;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 14:19
**/

import com.sonihr.context.ApplicationContext;

public abstract class AbstractHandlerAdapter implements HandlerAdapter {
    protected ApplicationContext mvcContext;

    public AbstractHandlerAdapter(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }
}
