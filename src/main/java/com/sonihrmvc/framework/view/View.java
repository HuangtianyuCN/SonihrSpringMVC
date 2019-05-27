package com.sonihrmvc.framework.view;/*
@author 黄大宁Rhinos
@date 2019/5/27 - 10:42
**/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface View {
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
