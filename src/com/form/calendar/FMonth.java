
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
public class FMonth extends FSeed
{
    private int year;    
    private int month;
    private FBeans weeks;
    private int size;
    private int day_start_of_week;
    private String  date_start;
    private String  date_end;
    private String  date_start_option;
    private int  date_start_inweek;

    public int getYear() {
        if(year<=0) year = getYear(getCurrentSqlDate());
        return year;
    }

    public void setYear(int year) {       
        this.year = year;
    }

    public int getMonth() {
        if(month<1 || month>12) month = getMonth(getCurrentSqlDate());
        return month;
    }

    public void setMonth(int month) {        
        this.month = month;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FBeans getWeeks() {
        return weeks;
    }

    public void setWeeks(FBeans weeks) {
        this.weeks = weeks;
    }


    public int getDay_start_of_week() {
        return day_start_of_week;
    }

    public void setDay_start_of_week(int day_start_of_week) {
        this.day_start_of_week = day_start_of_week;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getDate_start_inweek() {
        return date_start_inweek;
    }

    public void setDate_start_inweek(int date_start_inweek) {
        this.date_start_inweek = date_start_inweek;
    }

    public String getDate_start_option() {
        return date_start_option;
    }

    public void setDate_start_option(String date_start_option) {
        this.date_start_option = date_start_option;
    }
}
