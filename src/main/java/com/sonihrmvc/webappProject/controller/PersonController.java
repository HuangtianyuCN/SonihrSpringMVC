package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 21:27
**/

import com.sonihr.beans.annotation.Autowired;
import com.sonihr.beans.annotation.Controller;
import com.sonihrmvc.webappProject.service.PersonService;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

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
