package com.startup;


import com.exp.EException;

import com.form.admin.login.FLoginSystem;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;


public class SCalendar{
    public boolean load(ActionForm form,ActionErrors errors) throws EException {
        boolean result = false;
        FLoginSystem bean = (FLoginSystem)form;
        HttpServletRequest request = bean.getRequest();
        try  {
//                DCalendar DCal=new DCalendar();
//                Date dayStart=DCal.getDayStart(bean.getMonth(bean.getCurrentSqlDate()),bean.getYear(bean.getCurrentSqlDate()));
//                Date dayEnd=DCal.getDayEnd(bean.getMonth(bean.getCurrentSqlDate()),bean.getYear(bean.getCurrentSqlDate()));
//                request.setAttribute("BEvents",new BPublicAgenda().getEvents((int)bean.me.getId(),dayStart,bean.getDays(dayStart,dayEnd)+1));              
//                FAgenda beanTemp =new FAgenda();
//                beanTemp.setMonth(beanTemp.getMonth(beanTemp.getCurrentSqlDate()));
//                beanTemp.setYear(beanTemp.getYear(beanTemp.getCurrentSqlDate()));
//                beanTemp.setDayId(DCal.convertDateToID(bean.getCurrentSqlDate()));                        
//                beanTemp.setCalendarType(1);                        
//                request.getSession().setAttribute("BMenus",new BMenu().getAllMenuByUserId(bean.me.getId()));
                result = true;
        } catch (Exception ex)  {
        }
        
        return result;
    }
}
