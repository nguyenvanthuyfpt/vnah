package com.dao.calendar;


import com.form.FSeed;

import com.inf.IConstants;

import java.sql.Date;

public class DCalendarLib{
    
    //*************************************************
    public int convertDateToID(Date date)
    {
        FSeed bean = new FSeed();
        return convertDateMonthYearToWeekID(date);
    }
    public Date convertIDToDate(int week_id)
    {
        FSeed convert = new FSeed();
        return convert.stringToSqlDate(week_id%100+ "/"+(week_id/100)%100 + "/"+ week_id/10000);
    }
    //*************************************************
     public String convertIDToString(int week_id)
     {
         return week_id%100+ "/"+(week_id/100)%100 + "/"+ week_id/10000;
     }
    public int getDateFromWeekID(int week_id)
    {        
        return week_id%100;
    }
     //*************************************************
    public int getYearFromWeekID(int week_id)
    {        
        return week_id/10000;
    }
    //*************************************************
    public int getMonthFromWeekID(int week_id)
    {
       return (week_id/100)%100;
    }
     //*************************************************      
    public int getStartWeekOfMonth(int month,int year){
      FSeed convert = new FSeed();
      int dayStartMonth=1;
      int dayInWeek = convert.getDay(convert.stringToSqlDate(dayStartMonth+"/"+month+"/"+year));
       if(dayInWeek==IConstants.SUN_DAY){
           ++dayStartMonth;
       }else if(dayInWeek>IConstants.MON_DAY){
           dayStartMonth = IConstants.DAY_IN_WEEK - dayInWeek + IConstants.DAY_OFFSET;
       }
       return dayStartMonth;
    }
    public int convertDateMonthYearToWeekID(Date date)
    {
        FSeed bean=new FSeed();
        return bean.getYear(date)*10000 + bean.getMonth(date)*100 + bean.getDate(date);
    }
    public int getID_Week(Date date)
    {
        FSeed bean = new FSeed();
        Date startWeek = new Date(date.getTime());
        int  dayInWeek = bean.getDay(startWeek);          
        if(dayInWeek==IConstants.SUN_DAY){
            startWeek = bean.addDays(startWeek,-1*(IConstants.DAY_IN_WEEK-1));
        }else if(dayInWeek>IConstants.MON_DAY){
            startWeek = bean.addDays(startWeek,IConstants.DAY_OFFSET-dayInWeek-1);
        }      
        return convertDateToID(startWeek);
    } 
    //Duong Van Duc
    
    public Date getDayStart(String selectDate)
        {        
            
            FSeed bean = new FSeed();
            int month=bean.getMonth(bean.stringToSqlDate(selectDate));
            int year=bean.getYear(bean.stringToSqlDate(selectDate));
            if(month==0){
                month=bean.getMonth(bean.getCurrentSqlDate());
            }
            if(year==0){
                year=bean.getYear(bean.getCurrentSqlDate());
            }
            Date dayStart=bean.stringToSqlDate("01/"+month+"/"+year);
            int day = bean.getDay(dayStart);
            if(day==1){
                dayStart =  bean.addDays(dayStart,-6);
            }else{
                dayStart =  bean.addDays(dayStart,2-day);
            }
            return dayStart;
        }
     public Date getDayEnd(String selectDate)
         {        
             FSeed bean = new FSeed();
             int month=bean.getMonth(bean.stringToSqlDate(selectDate));
             int year=bean.getYear(bean.stringToSqlDate(selectDate));
             if(month==0){
                 month=bean.getMonth(bean.getCurrentSqlDate());
             }
             if(year==0){
                 year=bean.getYear(bean.getCurrentSqlDate());
             }
             Date dayEnd=bean.stringToSqlDate(bean.getDaysOfMonth(month,year)+"/"+month+"/"+year);
             int day=bean.getDay(dayEnd);
             if(day>1){
                dayEnd =  bean.addDays(dayEnd,8-day);
             }
             return dayEnd;
         }
 }
