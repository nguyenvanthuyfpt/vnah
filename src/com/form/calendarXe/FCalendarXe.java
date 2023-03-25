package com.form.calendarXe;


import com.form.FBeans;
import com.form.FSeed;

import java.sql.Date;

public class FCalendarXe extends FSeed
{
    //moi them
    private int userCreate;
    private String userFullNameCreate;
    private int eventId;
    private String timeEvent;
    private String timeCreate;
    private String times;
    private String toTimes;
    private String baseEmp;
    private String content;
    private String bienSo;
    
    private FBeans eventsInDay=new FBeans();
    private String selectDate;
    private int month;
    private int year;
    private int date;
    private int active;
    private int pageIndex;
    private int parentId;
    
    
    private int dayId;
    private int calendarType=0;//rieng
    private int departmentId;
    private int id;
    private long userIdAsign;
    private int userId; 
    private String departmentIds;
    private String userIdAsignName;    
    private String userIds;
    private String departmentName;
    private String fullName;
    private String address;
    private String event;
    private String title;
  
    private String members;
    private Date day;
    private int typeCalendar;
    private int id_week;
    private String elm1;

 public void reset(){
         this.setId(0);
         this.setAddress("");
         this.setEvent("");
 }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

 

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
   
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getTypeCalendar() {
        return typeCalendar;
    }

    public void setTypeCalendar(int typeCalendar) {
        this.typeCalendar = typeCalendar;
    }

    public int getId_week() {
        return id_week;
    }

    public void setId_week(int id_week) {
        this.id_week = id_week;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public int getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(int calendarType) {
        this.calendarType = calendarType;
    }

    public String getElm1() {
        return elm1;
    }

    public void setElm1(String elm1) {
        this.elm1 = elm1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

   

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public String getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds) {
        this.departmentIds = departmentIds;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

  

    public long getUserIdAsign() {
        return userIdAsign;
    }

    public void setUserIdAsign(long userIdAsign) {
        this.userIdAsign = userIdAsign;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUserIdAsignName() {
        return userIdAsignName;
    }

    public void setUserIdAsignName(String userIdAsignName) {
        this.userIdAsignName = userIdAsignName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

  

    public FBeans getEventsInDay() {
        return eventsInDay;
    }

    public void setEventsInDay(FBeans eventsInDay) {
        this.eventsInDay = eventsInDay;
    }


    public int getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(int userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserFullNameCreate() {
        return userFullNameCreate;
    }

    public void setUserFullNameCreate(String userFullNameCreate) {
        this.userFullNameCreate = userFullNameCreate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

 

    public String getToTimes() {
        return toTimes;
    }

    public void setToTimes(String toTimes) {
        this.toTimes = toTimes;
    }

    public String getBaseEmp() {
        return baseEmp;
    }

    public void setBaseEmp(String baseEmp) {
        this.baseEmp = baseEmp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }
}
