package com.sonihrmvc.framework.ContextListener;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 19:17
**/

import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import com.sonihrmvc.webappProject.service.PersonService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String springXmlPath = servletContext.getInitParameter("contextConfigLocation");
        if(springXmlPath.startsWith("classpath:")){
            springXmlPath = springXmlPath.substring(10);
        }
        ApplicationContext applicationContext = null;
        try {
            applicationContext = new ClassPathXmlApplicationContext(springXmlPath);
            servletContext.setAttribute("springContext",applicationContext);
//            PersonService personService = (PersonService) applicationContext.getBean("personService");
//            System.out.println(personService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
