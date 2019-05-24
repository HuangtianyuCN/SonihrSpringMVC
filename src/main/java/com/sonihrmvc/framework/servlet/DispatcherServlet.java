package com.sonihrmvc.framework.servlet;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:22
**/

import com.sonihr.context.AbstractApplicationContext;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import com.sonihrmvc.webappProject.controller.PersonController;
import com.sonihrmvc.webappProject.service.PersonService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        String mvcXmlPath = this.getInitParameter("contextConfigLocation");
        if(mvcXmlPath==null||mvcXmlPath.length()==0)
            return;
        if(mvcXmlPath.startsWith("classpath:")){
            mvcXmlPath = mvcXmlPath.substring(10);
        }
        ServletContext servletContext = this.getServletContext();
        ApplicationContext mvcContext = null;
        try {
            ApplicationContext springContext = (ApplicationContext)servletContext.getAttribute("springContext");
            mvcContext = new ClassPathXmlApplicationContext(springContext,mvcXmlPath);
            System.out.println(springContext);
            PersonService personService = (PersonService) mvcContext.getBean("personService");
            System.out.println("personService=" + personService);
            PersonController personController = (PersonController) mvcContext.getBean("personController");
            System.out.println("personController=" + personController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private Object doGetBean(String beanName,ApplicationContext springContext,ApplicationContext mvcContext) throws Exception {
//        Object object = null;
//        try{
//            object = mvcContext.getBean(beanName);
//        }catch (Exception e){
//            object = springContext.getBean(beanName);
//        }
//        return object;
//    }
}
