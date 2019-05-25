package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 11:00
**/

import com.sonihr.context.ApplicationContext;

//这个类用于bean的id与相应uri的匹配，比如/mvc/person 就匹配Person这个类
public class BeanNameHandlerMapping extends AbstractHandlerMapping{
    public BeanNameHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    protected void registryURLAndHandler() {

    }
}
