package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:27
**/

import com.sonihr.beans.annotation.Autowired;
import com.sonihr.beans.annotation.Controller;
import com.sonihrmvc.framework.annocation.RequestMapping;
import com.sonihrmvc.webappProject.service.PersonService;

@Controller
@RequestMapping("/mvc/person/")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping("eat.do")
    public void eating(){
        System.out.println("I am eating!");
    }

    @RequestMapping("speak.do")
    public void speak(int age){
        System.out.println("i am " + age + "years old");
    }

    @RequestMapping("baby.do")
    public void baby(int age,Baby baby,String name){
        System.out.println(age);
        System.out.println(baby);
        System.out.println(name);
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public String toString() {
        return "PersonController{" +
                "personService=" + personService +
                '}';
    }
}
