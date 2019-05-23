package com.sonihrmvc.webappProject.service;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 20:01
**/

import com.sonihr.beans.annotation.Service;
import com.sonihr.beans.annotation.Value;

@Service
public class Person {
    @Value("黄大宁的第一次注入")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
