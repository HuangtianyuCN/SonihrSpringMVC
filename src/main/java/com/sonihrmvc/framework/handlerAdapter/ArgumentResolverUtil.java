package com.sonihrmvc.framework.handlerAdapter;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 13:58
**/

import com.sonihr.beans.converter.ConverterFactory;
import com.sonihrmvc.framework.handlerMapping.RequestMappingHandler;
import com.sonihrmvc.framework.modelAndView.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArgumentResolverUtil {
    public static Object[] resloveRequsetParam(HttpServletRequest request, Method method,Model model) throws Exception {
        Map<String,String[]> paramMap = request.getParameterMap();
        Map<String,String> argMap = new LinkedHashMap<>();
        for(Map.Entry<String,String[]> entry:paramMap.entrySet()){
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for(int i=0;i<paramValueArr.length;i++){
                if(i==paramValueArr.length-1)
                    paramValue += paramValueArr[i];
                else
                    paramValue += paramValueArr[i] + ",";
            }
            argMap.put(paramName,paramValue);//处理后的request键值对
        }

        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        for(int i=0;i<parameters.length;i++){
            Parameter parameter = parameters[i];
            //如果形参中有Model类，则创建一个参数
            if(parameter.getType() == Model.class){
                args[i] = model;
                continue;
            }
            if(argMap.containsKey(parameter.getName())){
                String value = argMap.get(parameter.getName());
                Type type = parameter.getType();
                if(type == String.class)
                    args[i] = value;
                else
                    args[i] = ConverterFactory.getConverterMap().get(parameter.getType()).parse(value);
            }else {
                Type type = parameter.getType();
                Object bean = ((Class) type).newInstance();
                try{
                    BeanUtils.populate(bean,argMap);
                    args[i] = ((Class) type).cast(bean) ;
                }catch(Exception e){
                    args[i] = null;
                }
            }
        }
        return args;
    }
}
