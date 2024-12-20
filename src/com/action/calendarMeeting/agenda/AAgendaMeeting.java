package com.action.calendarMeeting.agenda;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.users.BUsers;
import com.bo.calendarMeeting.agenda.BCalendarMeeting;

import com.dao.calendar.DCalendarLib;

import com.exp.EException;

import com.form.calendarMeeting.agenda.FAgendaMeeting;

import com.inf.IRoles;

import com.lib.AppConfigs;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AAgendaMeeting extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FAgendaMeeting bean = (FAgendaMeeting)form;
        BCalendarMeeting bo=new BCalendarMeeting();
        if (bean.getSelectDate()==null || !bean.isDate(bean.getSelectDate())){
            bean.setSelectDate(bean.dateToString(bean.getCurrentSqlDate()));
        }
        DCalendarLib DCal=new DCalendarLib();
        String anchor=bean.getValue(APP_ANCHOR,"");
        if(bean.getId_week()==0){
            bean.setId_week(DCal.getID_Week(bean.getCurrentSqlDate()));
        }
        bean.setUserId((int)me.getId());
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        bean.setYear(bean.getYear()==0?bean.getYear(bean.getCurrentSqlDate()):bean.getYear());
            bean.setCalendarType(0);
            if(!errors.isEmpty()){
            }else if(anchor.equals("_CALENDAR_MONTH")){   
                Date currentDate=bean.getCurrentSqlDate();
                if(bean.getSelectDate()!=null && !bean.getSelectDate().equals("")){
                    currentDate=bean.stringToSqlDate(bean.getSelectDate());
                }
                Date dayStart=DCal.getDayStart(bean.dateToString(currentDate));
                Date dayEnd=DCal.getDayEnd(bean.dateToString(currentDate));
                request.setAttribute("BEvents",bo.getEventsByMonth(bean.getUserId(),dayStart,bean.getDays(dayStart,dayEnd)+1));
                bean.setSelectDate(bean.dateToString(currentDate));
                bean.setDayId(DCal.convertDateToID(currentDate));
                request.setAttribute("calendarMeeting",bean);
                target=anchor;
            }else if(anchor.equals("_CALENDAR_WEEK")){
                Date dayStart=DCal.convertIDToDate(DCal.getID_Week(bean.getCurrentSqlDate()));
                if(bean.getSelectDate()!=null && !bean.getSelectDate().equals("")){
                    dayStart=DCal.convertIDToDate(DCal.getID_Week(bean.stringToSqlDate(bean.getSelectDate())));
                }
                request.setAttribute("BEvents",bo.getEventsTest(bean.getUserId(),dayStart,7));
                bean.setDayId(DCal.getID_Week(dayStart));
                bean.setDay(dayStart);
                bean.setId_week(bean.getDayId());
                request.setAttribute("calendarMeeting",bean);
                target=_SELECT_WEEK;
                
            }else if(anchor.equals("_WEEK_CURENT")){
            
                int day=bean.getDay(bean.getCurrentSqlDate());
                Date dayStart=bean.addDays(bean.getCurrentSqlDate(),-day+2);
                request.setAttribute("BEvents",bo.getEventsTest((int)bean.me.getId(),dayStart,7));
                bean.setDayId(DCal.convertDateToID(dayStart));
                bean.setId_week(bean.getDayId());
                bean.setDayId(DCal.convertDateToID(bean.getCurrentSqlDate()));
                request.setAttribute("calendarMeeting",bean);
                target=_SELECT_WEEK;

            }else if(anchor.equals(_HOME_CURRENT)){
                Date currentDate=bean.getCurrentSqlDate();
                Date dayStart=DCal.getDayStart(bean.dateToString(currentDate));
                Date dayEnd=DCal.getDayEnd(bean.dateToString(currentDate));
                request.setAttribute("BEvents",bo.getEventsByMonth(bean.getUserId(),dayStart,bean.getDays(dayStart,dayEnd)+1));
                bean.setSelectDate(bean.dateToString(currentDate));
                bean.setDayId(DCal.convertDateToID(currentDate));
                request.setAttribute("calendarMeeting",bean);
                target="_CALENDAR_MONTH";
                
       
            }else if(anchor.equals(_NEXT_WEEK)){
            
                bean.setUserId((bean.getUserId()>0 && me.isRole(IRoles.rOBAGENDA))?bean.getUserId():(int)bean.me.getId());
                
                Date dayStart=bean.addDays(DCal.convertIDToDate(bean.getId_week()),7);
                request.setAttribute("BEvents",bo.getEventsTest(bean.getUserId(),dayStart,7));
                bean.setId_week(DCal.convertDateMonthYearToWeekID(dayStart));
                bean.setDayId(DCal.convertDateToID(bean.stringToSqlDate(bean.getSelectDate())));
                if(me.isRole(IRoles.rOBAGENDA)){
                    request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));    
                    request.setAttribute("BUsers",new BUsers().getUserByDepartmentID(bean.getDepartmentId(),0));    
                }
                request.setAttribute("BDate",bean);
                request.setAttribute("calendarMeeting",bean);
                target=_SELECT_WEEK;
                
            }else if(anchor.equals(_PREW_WEEK)){
            
                bean.setUserId((bean.getUserId()>0 && me.isRole(IRoles.rOBAGENDA))?bean.getUserId():(int)bean.me.getId());
                
                Date dayStart=bean.addDays(DCal.convertIDToDate(bean.getId_week()),-7);
                request.setAttribute("BEvents",bo.getEventsTest((int)bean.me.getId(),dayStart,7));
                bean.setId_week(DCal.convertDateMonthYearToWeekID(dayStart));
                bean.setDayId(DCal.convertDateToID(bean.stringToSqlDate(bean.getSelectDate())));
                if(me.isRole(IRoles.rOBAGENDA)){
                    request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));    
                    request.setAttribute("BUsers",new BUsers().getUserByDepartmentID(bean.getDepartmentId(),0));    
                }
                request.setAttribute("BDate",bean);
                request.setAttribute("calendarMeeting",bean);
                target=_SELECT_WEEK;
            }else if(anchor.equals(_SELECT_WEEK)){
                
               
                
                Date dayStart=DCal.convertIDToDate(bean.getId_week());
                request.setAttribute("BEvents",bo.getEventsTest(bean.getUserId(),dayStart,7));
                bean.setDay(dayStart);
                request.setAttribute("calendarMeeting",bean);
                target=anchor;
                
            }else if(anchor.equals("_CALENDAR_EXCUTE")){     
                FAgendaMeeting beanTemp=new FAgendaMeeting();
                if(bean.getId()<=0){
                    beanTemp.setTimeEvent(bean.dateToString(DCal.convertIDToDate(bean.getDayId())));
                    beanTemp.setDay(DCal.convertIDToDate(bean.getDayId()));
                    beanTemp.reset();
                    beanTemp.setTypeCalendar(bean.getTypeCalendar());
                    beanTemp.setDayId(bean.getDayId());
                    beanTemp.setMonth(bean.getMonth());
                    beanTemp.setYear(bean.getYear());
                    request.setAttribute("calendarMeeting",beanTemp);
                }else{
                    beanTemp=bo.getById(bean.getId());
                    beanTemp.setMonth(bean.getMonth());
                    beanTemp.setYear(bean.getYear());
                    request.setAttribute("calendarMeeting",beanTemp);
                }
                target=anchor;
            }else if(anchor.equals("_SEARCH_MAIN")){
                     request.setAttribute("BCalendaInDeps",bo.getCalendar(bean.getCurrentSqlDate(),me.getId(),2,bean.getDepartmentId()));
                     target=anchor;
            }else if(anchor.equals(_PREPARED_CREATE)){  
                
                int typeCalendar=bean.getTypeCalendar();
                bean.setToTimes(bean.getTimes().replaceAll("#",":"));
                if(bean.getId()>0){
                    bean=new BCalendarMeeting().getById(bean.getId());
                    bean.setTypeCalendar(typeCalendar);
                }
                bean.setTimes(bean.getTimes().replaceAll("#",":"));
                request.setAttribute("calendarMeeting",bean);
                target=anchor;
        }if(anchor.equals("_DETAIL_CALENDAR")){    
            request.setAttribute("BEventTimes",new BCalendarMeeting().getEventsTest(bean.getUserId(),bean.stringToSqlDate(bean.getTimeEvent()),1));
            target=anchor;
       }
        
        return mapping.findForward(target);
    }
    
    
    
    private String validate(FAgendaMeeting bean,String anchor,ActionErrors errors){
        if(anchor.equals(_SELECT_MONTH)){
            if(bean.getSelectDate()!=null && !bean.getSelectDate().equals("") && bean.isDate(bean.getSelectDate())){
                int month=bean.getMonth(bean.stringToSqlDate(bean.getSelectDate()));
                int year=bean.getYear(bean.stringToSqlDate(bean.getSelectDate()));
                if(year<AppConfigs.APP_DATE_YEAR_MIN || year>AppConfigs.APP_DATE_YEAR_MAX){
                    errors.add("alert",new ActionError("errors.agenda.search.year"));   
                }else if(month<0 || month>12){
                    errors.add("alert",new ActionError("errors.agenda.search.month"));   
                }
            }
        }else if (anchor.equals(_SAVE)){
            if (bean.getAddress()==null || (bean.getAddress()!=null && bean.getAddress().equals(""))){
                errors.add("alert",new ActionError("errors.agenda.address.month.caption"));
            }
        }
        return anchor;
      }
}
