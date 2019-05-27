package com.sonihrmvc.framework.view;/*
@author 黄大宁Rhinos
@date 2019/5/27 - 10:42
**/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class InternalResourceView implements View {
    private String path;
    private boolean IsRedirect;

    public boolean isRedirect() {
        return IsRedirect;
    }

    public void setRedirect(boolean redirect) {
        IsRedirect = redirect;
    }

    public InternalResourceView() {
    }



    public InternalResourceView(String path, boolean isRedirect) {
        this.path = path;
        IsRedirect = isRedirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for(Map.Entry<String,?> entry:model.entrySet()){
            request.setAttribute(entry.getKey(),entry.getValue());
        }
        if(!this.IsRedirect)
            request.getRequestDispatcher(path).forward(request,response);
        else
            response.sendRedirect(path);
    }
}
