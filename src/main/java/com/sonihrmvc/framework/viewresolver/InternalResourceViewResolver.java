package com.sonihrmvc.framework.viewresolver;/*
@author 黄大宁Rhinos
@date 2019/5/27 - 11:08
**/

import com.sonihrmvc.framework.view.InternalResourceView;
import com.sonihrmvc.framework.view.View;

public class InternalResourceViewResolver implements ViewResolver {
    private String viewClass;
    private String prefix;
    private String suffix;

    public String getViewClass() {
        return viewClass;
    }

    public void setViewClass(String viewClass) {
        this.viewClass = viewClass;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public View resolveViewName(String viewName) throws Exception {
        if(viewClass.equals("com.sonihrmvc.framework.view.InternalResourceView")){
            if(viewName.startsWith("redirect:"))
                return new InternalResourceView(viewName.substring(9),true);
            else
                return new InternalResourceView(prefix + viewName + suffix,false);
        }
        return null;
    }
}
