package com.sonihrmvc.webappProject.controller;/*
@author 黄大宁Rhinos
@date 2019/5/25 - 16:34
**/

import com.sonihr.beans.annotation.Component;


public class Baby {
    private String babyName;
    private int babtAge;
    private float weight;

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public int getBabtAge() {
        return babtAge;
    }

    public void setBabtAge(int babtAge) {
        this.babtAge = babtAge;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "babyName='" + babyName + '\'' +
                ", babtAge=" + babtAge +
                ", weight=" + weight +
                '}';
    }
}
