package com.sonihrmvc.framework.annocation;/*
@author 黄大宁Rhinos
@date 2019/5/27 - 21:26
**/

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {
}
