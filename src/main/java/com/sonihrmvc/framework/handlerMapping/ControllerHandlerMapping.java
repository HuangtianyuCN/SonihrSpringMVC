package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 11:02
**/

import com.sonihr.context.ApplicationContext;

//该类用于注册所有实现了Controller接口的实例,xml中该类id应该配置为"/"开头的，以作为uri的key
public class ControllerHandlerMapping extends AbstractHandlerMapping{

    public ControllerHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    protected void registryURLAndHandler() {

    }
}
