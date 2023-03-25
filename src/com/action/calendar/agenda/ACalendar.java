package com.action.calendar.agenda;


import com.action.ACore;

import com.bo.calendar.agenda.BCalendar;

import com.dao.calendar.DCalendarLib;

import com.exp.EException;

import com.form.calendar.agenda.FAgenda;

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


public class ACalendar extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FAgenda bean = (FAgenda)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BCalendar bo =new BCalendar();
        bean.setUserCreate((int)me.getId());
        bean.setDepartmentId((int)me.getDepartmentID());
            if(anchor.equals(_SAVE)){     
            if(bean.getEventId()<=0){
                if(bo.addNew(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));
                }else{
                    errors.add("alert",new ActionError("errors.insert.code.exits"));
                }
            }else{
                if(bo.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));
                }else{
                    errors.add("alert",new ActionError("errors.insert.code.exits"));
                }
            }
            anchor=_VIEW;            
        }else if(anchor.equals(_DELETE)){
            if(bo.delete(bean.getEventId())){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }
            anchor=_VIEW;
        }
        
        if(anchor.equals(_VIEW)){
            DCalendarLib DCal=new DCalendarLib();
            if(bean.getTypeCalendar()==0){
                Date dayStart=DCal.convertIDToDate(DCal.getID_Week(bean.stringToSqlDate(bean.getTimeEvent())));
                request.setAttribute("BEvents",bo.getEventsTest(bean.getUserCreate(),dayStart,7,bean.getType(),me.getDepartmentID()));
                bean.setDay(dayStart);
                bean.setId_week(DCal.getID_Week(bean.stringToSqlDate(bean.getTimeEvent())));
                request.setAttribute("agenda",bean);
                target=_SELECT_WEEK;
            }else{
                Date dayStart=DCal.getDayStart(bean.getTimeEvent());
                Date dayEnd=DCal.getDayEnd(bean.getTimeEvent());
                request.setAttribute("BEvents",bo.getEventsByMonth(bean.getUserCreate(),dayStart,bean.getDays(dayStart,dayEnd)+1,bean.getType(),me.getDepartmentID()));
                bean.setSelectDate(bean.getTimeEvent());
                bean.setDayId(DCal.convertDateToID(bean.stringToSqlDate(bean.getTimeEvent())));
                request.setAttribute("agenda",bean);
                target=_SELECT_MONTH;
            }
        }
        return mapping.findForward(target);
    }
    
  
    
    
    private String validate(FAgenda bean,String anchor,ActionErrors errors){
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
