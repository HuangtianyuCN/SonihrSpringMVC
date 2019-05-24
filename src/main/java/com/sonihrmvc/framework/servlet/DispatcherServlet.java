package com.sonihrmvc.framework.servlet;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:22
**/

import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import com.sonihrmvc.framework.handlerMapping.HandlerExecutionChain;
import com.sonihrmvc.framework.handlerMapping.HandlerMapping;
import com.sonihrmvc.framework.handlerMapping.AnnotationHandlerMapping;
import com.sonihrmvc.framework.handlerMapping.RequestMappingHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
    private ApplicationContext mvcContext = null;
    private HandlerMapping handlerMapping = null;


    @Override
    public void init() throws ServletException {
        doInit();
        handlerMapping = new AnnotationHandlerMapping(mvcContext);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //Todo：
        HandlerExecutionChain handlerExecutionChain =  handlerMapping.getHandler(request);
        RequestMappingHandler handler = handlerExecutionChain.getHandler();

        //至于如何传参，就是HandlerAdapter的事情了
        handler.getMethod().invoke(handler.getBean(),null);//和AOP不冲突，内部bean如果是代理类，会调用代理后方法,
    }

    private void doInit() throws ServletException {
        super.init();
        String mvcXmlPath = this.getInitParameter("contextConfigLocation");
        if(mvcXmlPath==null||mvcXmlPath.length()==0)
            return;
        if(mvcXmlPath.startsWith("classpath:")){
            mvcXmlPath = mvcXmlPath.substring(10);
        }
        ServletContext servletContext = this.getServletContext();

        try {
            ApplicationContext springContext = (ApplicationContext)servletContext.getAttribute("springContext");
            mvcContext = new ClassPathXmlApplicationContext(springContext,mvcXmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
