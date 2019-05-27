package com.sonihrmvc.framework.servlet;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:22
**/

import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import com.sonihrmvc.framework.handlerAdapter.AnnotationHandlerAdapter;
import com.sonihrmvc.framework.handlerAdapter.ControllerHanlderAdapter;
import com.sonihrmvc.framework.handlerAdapter.HandlerAdapter;
import com.sonihrmvc.framework.handlerMapping.*;
import com.sonihrmvc.framework.modelAndView.ModelAndView;
import com.sonihrmvc.framework.view.View;
import com.sonihrmvc.framework.viewresolver.InternalResourceViewResolver;
import com.sonihrmvc.framework.viewresolver.ViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends HttpServlet {
    private ApplicationContext mvcContext = null;
    private HandlerMapping handlerMapping = null;
    private List<HandlerAdapter> handlerAdapters = null;
    private ViewResolver resolver = null;


    @Override
    public void init() {
        try {
            doInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //HandlerMapping从request映射到一个处理链，包括一个handler和一组拦截器
        HandlerExecutionChain handlerExecutionChain =  doHandlerMapping(request);
        Object handler = handlerExecutionChain.getHandler();//获取handler
        List<HandlerInterceptor> handlerInterceptors = handlerExecutionChain.getInterceptors();//获取拦截器链
        doInterceptorsPreHandle(request,response,handlerInterceptors,handler);//进行前置处理
        ModelAndView mv = doHandlerAdapter(request,response,handler);//进入HandlerAdaper模块
        doInterceptorsPostHandle(request,response,handlerInterceptors,handler,mv);//进行POST处理
        //视图解析器解析mv
        View view = resolver.resolveViewName(mv.getView());
        //页面渲染
        view.render(mv.getModel(),request,response);
        doInterceptorsAfterCompletion(request,response,handlerInterceptors,handler,new Exception());
    }

    private ModelAndView doHandlerAdapter(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelAndView mv = handlerAdapter.handle(request,response,handler);
        return mv;
    }

    private HandlerAdapter getHandlerAdapter(Object handler){
        for(HandlerAdapter adapter:this.handlerAdapters){
            if(adapter.supports(handler))
                return adapter;
        }
        return null;
    }

    private HandlerExecutionChain doHandlerMapping(HttpServletRequest request) throws Exception {
        return handlerMapping.getHandler(request);
    }

    private void doInterceptorsPreHandle(HttpServletRequest request,HttpServletResponse response,List<HandlerInterceptor> handlerInterceptors,Object handler)throws Exception{
        for(int i=0;i<handlerInterceptors.size();i++){
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            if(!interceptor.preHandle(request,response,handler)){
                for(int j=i-1;j>=0;j--){
                    handlerInterceptors.get(j).afterCompletion(request,response,handler,new Exception());
                }
                break;
            }
        }
    }

    private void doInterceptorsPostHandle(HttpServletRequest request,HttpServletResponse response,List<HandlerInterceptor> handlerInterceptors,Object handler,ModelAndView mv)throws Exception{
        for(int i=handlerInterceptors.size()-1;i>=0;i--){
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            interceptor.postHandle(request,response,handler,mv);
        }
    }

    private void doInterceptorsAfterCompletion(HttpServletRequest request,HttpServletResponse response,List<HandlerInterceptor> handlerInterceptors,Object handler,Exception ex)throws Exception{
        for(int i=handlerInterceptors.size()-1;i>=0;i--){
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            interceptor.afterCompletion(request,response,handler,ex);
        }
    }

    private void doInit() throws Exception {
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
        InitHandlerMappings(mvcContext);
        InitHandlerAdapters(mvcContext);
        InitViewResolver(mvcContext);
    }

    private void InitViewResolver(ApplicationContext mvcContext) throws Exception {
        List<ViewResolver> resolvers = mvcContext.getBeanFactory().getBeansForType(ViewResolver.class);
        if(resolvers.size()!=1){
            throw new Exception("未配置视图解析器或配置多个");
        }
        this.resolver = resolvers.get(0);
    }

    private void InitHandlerAdapters(ApplicationContext mvcContext){
        handlerAdapters = new ArrayList<>();
        handlerAdapters.add(new AnnotationHandlerAdapter(mvcContext));
        handlerAdapters.add(new ControllerHanlderAdapter(mvcContext));
    }

    private void InitHandlerMappings(ApplicationContext mvcContext){
        handlerMapping = new AnnotationHandlerMapping(mvcContext);
        ((AnnotationHandlerMapping) handlerMapping).init();
        new ControllerHandlerMapping(mvcContext).init();
        new SimpleUrlHandlerMapping(mvcContext).init();
        new BeanNameHandlerMapping(mvcContext).init();
    }
}
