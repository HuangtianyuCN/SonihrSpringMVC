package com.sonihrmvc.framework.handlerMapping;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 10:04
**/

import com.sonihr.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

//该类作用为，在xml中配置key=某uri，value=某具体注解@Controller的类，读取xml后加入handlerRegistry
public class SimpleUrlHandlerMapping extends AbstractHandlerMapping{
    public SimpleUrlHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    protected void registryURLAndHandler() {
        Map<String,Object> map = new HashMap<>();
        readXML(map);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            handlerRegistry.put(entry.getKey(),entry.getValue());
        }
    }
    //这个Object存放的是类
    void readXML(Map<String,Object> map){
        //todo:填充读取xml代码
    }
}
