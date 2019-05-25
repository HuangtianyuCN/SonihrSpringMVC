package com.sonihrmvc.framework.modelAndView;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 11:14
**/

public class ModelAndView {
    Object bean;

    public ModelAndView(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
