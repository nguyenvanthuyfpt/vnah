package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BEvent;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FEvent;
import com.form.disability.categorys.FTinh;

import java.io.IOException;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AEvent extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FEvent bean =(FEvent)form;
        FBeans beans = new FBeans();
        
        int userId = (int)bean.me.getId();
        int defaultLocation = 0;
         String anchor=bean.getValue(APP_ANCHOR,"");
        HttpSession session = seed.getRequest().getSession();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = bean.me.getDepartmentName();
        
        // Tree Location
        if (userId==375) {
            beans = new BTinh().getAllRecordByParentId(0);
            FTinh beanTinh = (FTinh)beans.get(0);
            defaultLocation = beanTinh.getId();            
        } else {
            beans = new BTreeView().getTree(0, false, SQL, characters, member);            
        }
        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        Map<String, Object> mapObject = (Map<String, Object>)request.getSession().getAttribute("MAP_OBJECT");
        Map<String, String> map_combobox = (HashMap<String,String>)mapObject.get("KPI_COMBOX");
        Map<String, String> map_event_type = (HashMap<String,String>)mapObject.get("KPI_EVENT_TYPE");
        Map<String, String> map_event_field = (HashMap<String,String>)mapObject.get("KPI_EVENT_FIELD");
        Map<String, String> map_kpi_vote_result = (HashMap<String,String>)mapObject.get("KPI_VOTE_RESULT"); 
        
        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {            
            if (new BEvent().update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.indicator.update.unique.code"));
            }
            
            request.setAttribute("event", bean);
            target = anchor;
        } else if (anchor.equals(_CREATE)) {           
            bean.setEventId(0);
            if (new BEvent().insert(bean)>0) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.event.insert.unique.code"));
            }
            request.setAttribute("event", bean);
            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            if (new BEvent().delete(bean)) {
                bean = new FEvent();
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
            }
            request.setAttribute("event", bean); 
            target = anchor;
        } else if (anchor.equals(_VIEW)) {                        
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
         
            request.setAttribute("event", bean);
            target = anchor;
        } else if (anchor.equals("_DETAIL")) {
            if (bean.getDtlId()>0) {
                bean.setEventId(bean.getDtlId());
                FEvent beanTemp = new BEvent().getRecordByID(bean);
                request.setAttribute("event", beanTemp);
            }
            target = anchor;
        } else if (anchor.equals("_CHANGE_OPTION")) {
            FEvent beanTemp = new FEvent();
            beanTemp.setLocationId(bean.getLocationId());
            String strCode = new BEvent().getNextCodeEvent(beanTemp);
            beanTemp.setCode(strCode);
            request.setAttribute("event", beanTemp);
            getListEvent(beanTemp, request, session);
            target = anchor;
        }
        
        request.setAttribute("listEvents", new BEvent().getAll(bean, session));
        request.setAttribute("mapBaseline", map_combobox);
        request.setAttribute("mapEventType", map_event_type);
        request.setAttribute("mapEventField", map_event_field);
        request.setAttribute("mapVoteResult", map_kpi_vote_result);
        request.setAttribute("menuEvents", new BEvent().getAllRecord(0));        
        request.setAttribute("BTreeTinhs", beans);
       
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }

    private String validate(FEvent bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getActivity().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.event.edit.activity.invalid"));
            } 
        } else if (anchor.equals(_CREATE)) {
            // Activity
            if ((bean.getActivity().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.event.edit.activity.invalid"));
            }
            // Location
            if ((bean.getLocation().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.event.edit.location.invalid"));
            }
            // StartDate / EndDate
            if (bean.getStartDate().equals(_BLANK) || bean.getEndDate().equals(_BLANK)) {
                errors.add("alert", new ActionError("errors.common.date.start.end.invalid"));
            } else if (bean.getStartDate().equals(_BLANK) && bean.getEndDate().equals(_BLANK)) {
                if (bean.getDays(bean.stringToDate(bean.getStartDate()), bean.stringToDate(bean.getEndDate())) < 0) {
                    errors.add("alert", new ActionError("errors.common.date.start.end.invalid"));
                }  
            }
        } else if (anchor.equals(_DELETE)) {
        }
        return anchor;
    }
    
    private void getListEvent(FEvent fEvent, HttpServletRequest request, HttpSession session) {
        FBeans beans = new FBeans();
        try {
            beans = new BEvent().getAll(fEvent, session);
            request.setAttribute("listEvents", beans);
            request.setAttribute("total", beans.size());
        } catch (EException e) {            
        }
    }
}
