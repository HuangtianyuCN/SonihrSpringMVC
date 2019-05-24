package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/15 - 20:31
**/

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long time = System.nanoTime();
        System.out.println("Invocation of Method " + methodInvocation.getMethod().getName() + " start!");
        Object proceed = methodInvocation.proceed();
        System.out.println("Invocation of Method " + methodInvocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
                + " nanoseconds.");
        return proceed;
    }
}
