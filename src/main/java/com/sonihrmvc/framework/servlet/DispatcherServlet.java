package com.sonihrmvc.framework.servlet;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:22
**/

import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import com.sonihrmvc.framework.handlerMapping.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DispatcherServlet extends HttpServlet {
    private ApplicationContext mvcContext = null;
    private HandlerMapping handlerMapping = null;


    @Override
    public void init() throws ServletException {
        doInit();
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
        HandlerExecutionChain handlerExecutionChain =  handlerMapping.getHandler(request);
        List<HandlerInterceptor> handlerInterceptors = handlerExecutionChain.getInterceptors();
        Object handler = handlerExecutionChain.getHandler();
        for(int i=0;i<handlerInterceptors.size();i++){
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            if(!interceptor.preHandle(request,response,handler)){
                for(int j=i-1;j>=0;j--){
                    handlerInterceptors.get(j).afterCompletion(request,response,handler,new Exception());
                }
                break;
            }
        }
        //至于如何传参，就是HandlerAdapter的事情了
        if(handler instanceof RequestMappingHandler)
            ((RequestMappingHandler)handler).getMethod().invoke(((RequestMappingHandler)handler).getBean(),null);//和AOP不冲突，内部bean如果是代理类，会调用代理后方法,
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
        InitHandlerMapping(mvcContext);
    }

    private void InitHandlerMapping(ApplicationContext mvcContext){
        handlerMapping = new AnnotationHandlerMapping(mvcContext);
        ((AnnotationHandlerMapping) handlerMapping).init();
        new ControllerHandlerMapping(mvcContext).init();
        new SimpleUrlHandlerMapping(mvcContext).init();
        new BeanNameHandlerMapping(mvcContext).init();
    }
}
