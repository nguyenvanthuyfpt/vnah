
/*
 * FBean.java
 *
 */
package com.form.calendar;

import com.form.FBeans;
import com.form.FSeed;

/**
 * FBean
 */
public class FWeek extends FSeed
{
    private int day_Start;    
    private int day_End;
    private int id_week;
    private FBeans agendas;
    private FBeans Tasks;


    public int getDay_Start() {
        return day_Start;
    }

    public void setDay_Start(int day_Start) {
        this.day_Start = day_Start;
    }

    public int getDay_End() {
        return day_End;
    }

    public void setDay_End(int day_End) {
        this.day_End = day_End;
    }

  

    public int getId_week() {
        return id_week;
    }

    public void setId_week(int id_week) {
        this.id_week = id_week;
    }


    public FBeans getTasks() {
        return Tasks;
    }

    public void setTasks(FBeans Tasks) {
        this.Tasks = Tasks;
    }

    public FBeans getMyagendas() {
        return agendas;
    }

    public void setMyagendas(FBeans agendas) {
        this.agendas = agendas;
    }
}
