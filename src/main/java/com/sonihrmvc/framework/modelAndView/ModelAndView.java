package com.sonihrmvc.framework.modelAndView;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 11:14
**/

import com.sonihrmvc.framework.view.View;

public class ModelAndView {
    private Model model;
    private String view;

    public ModelAndView() {
    }

    public ModelAndView(Model model, String view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
