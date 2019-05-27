package com.sonihrmvc.framework.viewresolver;/*
@author 黄大宁Rhinos
@date 2019/5/27 - 10:57
**/

import com.sonihrmvc.framework.view.View;

public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}
