package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.BIndicatorVal;
import com.bo.disability.categorys.BIndicator;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FIndicatorVal;
import com.form.disability.categorys.FIndicator;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AIndicator extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FIndicator bean =(FIndicator)form;
        
        String anchor=bean.getValue(APP_ANCHOR,"");     
        HttpSession session = seed.getRequest().getSession();
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        String characters = "/ ";
        String member = "";
        String SQL = "SELECT id,parent_id,name FROM kpi_indicator WHERE parent_id = ?";
        
        FBeans optIndicators = new FBeans();
        optIndicators = new BTreeView().getTree(0, false, SQL, characters, member);  
        
        FBeans beans = (FBeans)session.getAttribute("BTreeTinhs");
        request.setAttribute("BTreeTinhs", beans);
        
        FBeans listDtl = new FBeans();
        FIndicatorVal fIndVal = new FIndicatorVal();
        
        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {            
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", new ActionError("errors.indicator.edit.circle"));
            } else if (new BIndicator().update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.indicator.update.unique.code"));
            }
            
            request.setAttribute("indicator", bean);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        } else if (anchor.equals(_CREATE)) {           
            bean.setId(0);
            if (new BIndicator().insert(bean)>0) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.indicator.insert.unique.code"));
            }
            request.setAttribute("indicator", bean);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            if (new BIndicator().delete(bean)) {
                bean = new FIndicator();
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
            }
            request.setAttribute("indicator", bean);  
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            
            //bean.reset();
            target = anchor;
        } else if (anchor.equals(_VIEW)) {                        
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
         
            request.setAttribute("indicator", bean);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        } else if (anchor.equals("_DETAIL")) {
            if (bean.getIndId()>0) {
                bean.setId(bean.getIndId());
                FIndicator beanTemp = new BIndicator().getRecordByID(bean);
                request.setAttribute("indicator", beanTemp);
            }
            
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        } else if (anchor.equals("_CHANGE_DATATYPE") || anchor.equals("_CHANGE_TYPE") || anchor.equals("_CHANGE_OPTION")) {            
            int indId = bean.getIndId();
            int type = bean.getType();
            if (indId>0) {
                bean.setId(indId);
                FIndicator beanTemp = new BIndicator().getRecordByID(bean);
                
                beanTemp.setDataType(bean.getDataType());
                beanTemp.setType(type);
                beanTemp.setTypePeriod(bean.getTypePeriod());
                request.setAttribute("indicator", beanTemp);
            }
            
            fIndVal.setType(type);
            fIndVal.setIndId(indId);
            fIndVal.setLocationId(bean.getLocationId());
            listDtl = new BIndicatorVal().getIndicatorValues(fIndVal);            
            
            request.setAttribute("listDtl", listDtl);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        } else if (anchor.equals("_INPUT_VALUE") || anchor.equals("_DETAIL_VALUE")) {
            int indId = bean.getIndId();
            int mapId = bean.getMapId();
            if (anchor.equals("_DETAIL_VALUE")) {
                fIndVal.setId(mapId);                
                fIndVal = new BIndicatorVal().getDetailByID(fIndVal);
                
                bean.setId(indId);
                bean = new BIndicator().getRecordByID(bean);
                bean.setLocationId(fIndVal.getLocationId());
                bean.setDtlId(fIndVal.getId());
                bean.setCreateDate(fIndVal.getCreateDate());
                bean.setModifyDate(fIndVal.getModifyDate());
                bean.setQuarter(fIndVal.getQuarter());
                bean.setYear(fIndVal.getYear());
                bean.setValue(fIndVal.getVal());
                bean.setType(fIndVal.getType());
            } else {
                bean.setId(bean.getIndId());
                bean = new BIndicator().getRecordByID(bean);
                bean.setDtlId(0);
                bean.setCreateDate(fIndVal.getCreateDate());
                bean.setType(-1);
            }
            
            fIndVal.setIndId(indId);
            fIndVal.setLocationId(bean.getLocationId());            
            listDtl = new BIndicatorVal().getIndicatorValues(fIndVal);            
            request.setAttribute("indicator", bean);
            request.setAttribute("listDtl", listDtl);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("BTreeTinhs", beans);   
            target = anchor;
        } else if (anchor.equals("_INSERT_VALUE") || anchor.equals("_UPDATE_VALUE")) {            
            int indId = bean.getIndId();
            int dtlId = anchor.equals("_INSERT_VALUE")?0:bean.getDtlId();
            
            fIndVal.setId(dtlId);
            fIndVal.setIndId(indId);
            fIndVal.setCreateDate(bean.getCreateDate());
            fIndVal.setModifyDate(anchor.equals("_UPDATE_VALUE")?bean.getModifyDate():null);
            fIndVal.setLocationId(bean.getLocationId());
            fIndVal.setQuarter(bean.getQuarter());
            fIndVal.setYear(bean.getYear());
            fIndVal.setVal(bean.getValue());
            fIndVal.setType(bean.getType());
            
            if (dtlId==0) {                
                if (new BIndicatorVal().insert(fIndVal)) {
                    errors.add("alert", new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("alert", new ActionError("common.error.insert.unsuccess"));                  
                }
            } else {
                if (new BIndicatorVal().update(fIndVal)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", new ActionError("common.error.update.unsuccess"));                  
                }
            }
            
            bean.setId(indId);
            bean = new BIndicator().getRecordByID(bean);
            
            fIndVal.setIndId(indId);
            fIndVal.setLocationId(bean.getLocationId());
            listDtl = new BIndicatorVal().getIndicatorValues(fIndVal);
            request.setAttribute("indicator", bean);
            request.setAttribute("listDtl", listDtl);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("BTreeTinhs", beans);   
            target = anchor;
        } else if (anchor.equals("_DELETE_VALUE")) {
            int mapId = bean.getMapId();
            int indId = bean.getIndId();
            fIndVal.setId(mapId);            
            if (new BIndicatorVal().delete(fIndVal)) {
                bean.setId(indId);
                bean = new BIndicator().getRecordByID(bean);
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("common.error.delete.unsuccessd"));
            }
            
            fIndVal.setIndId(indId);
            fIndVal.setLocationId(bean.getLocationId());      
            listDtl = new BIndicatorVal().getIndicatorValues(fIndVal);            
            request.setAttribute("indicator", bean);
            request.setAttribute("listDtl", listDtl);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("BTreeTinhs", beans);   
            target = anchor;
        } else if (anchor.equals("_VIEW_VALUE")) {                        
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
            
            fIndVal.setPageIndex(bean.getPageIndex());
         
            request.setAttribute("indicator", bean);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("BTreeTinhs", beans);   
            request.setAttribute("listDtl", new BIndicatorVal().getIndicatorValues(fIndVal));
            target = anchor;
        } else if (anchor.equals("_CANCEL_VALUE")) {
            bean = new FIndicator();
            request.setAttribute("indicator", bean);
            request.setAttribute("optIndicators", optIndicators);
            request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
            request.setAttribute("listIndicators", new BIndicator().getAll(bean));
            target = anchor;
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }

    private String validate(FIndicator bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.indicator.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.indicator.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.indicator.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.indicator.edit.idnull"));
            }
        }
        return anchor;
    }
}
