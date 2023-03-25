package com.action.doc.docsSearch;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.doc.category.classify.BClassify;
import com.bo.admin.doc.category.doctype.BDocType;
import com.bo.admin.doc.category.dossiers.BDossiers;
import com.bo.admin.doc.category.express.BExpress;
import com.bo.admin.doc.category.form.BForm;
import com.bo.admin.doc.category.secure.BSecure;
import com.bo.admin.doc.category.status.BStatus;
import com.bo.admin.doc.category.via.BVia;
import com.bo.doc.docsSearch.BDocsSearch;

import com.exp.EException;

import com.form.FBeans;
import com.form.doc.docsSearch.FDocsSearch;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADocsSearch extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocsSearch bean = (FDocsSearch)form;
        BDocsSearch bo =new BDocsSearch();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        errors = validate(anchor,bean,errors);
        if(!errors.isEmpty()){
            anchor=_PREPARE;
        }else if(anchor.equals(_SEARCH) || anchor.equals("_REVIEW")){
            if(bean.getValue("fields")==null){//tim kiem cong van 
                bean.setFields(null);
            }else{//In so cong van 
                bean.setPageIndex(-1);
            }
             FBeans beans=new FBeans();
             beans=bo.search(bean);
             if(beans.size()==0){
                 errors.add("alert",new ActionError("alert.docsSearch.notdateToSearch"));   
                 anchor=_SUCCESS;
             }
             request.setAttribute("BSearch",beans);
            FDocsrecv beanrecv =new FDocsrecv();
            FDocssend beansend =new FDocssend();
            if(bean.getType()==1){//cong van di
                beansend.setType(1);
               
                beansend.setStatusId(bean.getStatusId());                
                request.setAttribute("docssend",beansend);
                target="_SEARCH_SEND";
            }else if(bean.getType()==2) {//du thao
                 beansend.setType(2);
                 beansend.setStatusId(bean.getStatusId());
                  beansend.setExistInRules(Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_USER_IN_RULE_DOCSSEND).toString()));
                 request.setAttribute("docssend",beansend);
                 target="_SEARCH_SEND";
            }else{//cong van den
                beanrecv.setStatusId(bean.getStatusId());                
                request.setAttribute("docsrecv",beanrecv);
                target="_SEARCH_RECV";
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BStatus",new BStatus().getAllStatus());
            if(anchor.equals("_REVIEW")){
                request.setAttribute("BFiedls",bean);
                if(bean.getValue("export")==null) bean.setExport(0);
                if(bean.getExport()==1){
                     String report = bo.exportExcel(beans,bean,"data_1.xls");    
                     bean.download(report,"fileName.xls",null);
                     bean.deleteFile(report);
                     bean.setPageIndex(0);
                }
                target="_REVIEW";
            }
        }else if(anchor.equals("_PRINTER_FORM_DOCS")){
            
            target =anchor;

        }
        if (anchor.equals(_PREPARE))  {
            if(errors.isEmpty()){
                bean.reset();                
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BForms",new BForm().getAllForm());
            request.setAttribute("BDeps",new BDepartments().getAllRecord(0));
            request.setAttribute("BSecures",new BSecure().getAllSecure());
            request.setAttribute("BExpresss",new BExpress().getAllExpress());
            request.setAttribute("BVias",new BVia().getAllVia());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BDocTypes",new BDocType().getAllDocType());
            request.setAttribute("BStatus",new BStatus().getAllStatus());
            request.setAttribute("docsSearch",bean);                   
            target =_SUCCESS;
        }
        
         if(!errors.isEmpty()) saveErrors(request,errors);
       return mapping.findForward(target);
    }
    
    private ActionErrors validate(String anchor,FDocsSearch bean,ActionErrors errors){
         if(anchor.equals(_SEARCH))  {
             if((bean.getTimeCreateFrom()!=null) && (!bean.getTimeCreateFrom().equals(""))){ 
                     if(!bean.isDate(bean.getTimeCreateFrom())) {
                     errors.add("alert", new ActionError("errors.docsSearch.TimeCreateFromNotDate"));
                     }
             }else if(bean.getTimeCreateTo()!=null && !bean.getTimeCreateTo().equals("")){
                     if(!bean.isDate(bean.getTimeCreateTo())) {
                     errors.add("alert", new ActionError("errors.docsSearch.TimeCreateToNotDate"));
                     }
             } else if(bean.getLocalDateFrom()!=null &&   !bean.getLocalDateFrom().equals("")){
                     if(!bean.isDate(bean.getLocalDateFrom())) {
                     errors.add("alert", new ActionError("errors.docsSearch.LocalDateFromNotDate"));
                     }
             }else if(bean.getLocalDateTo()!=null  && !bean.getLocalDateTo().equals("")){
                     if(!bean.isDate(bean.getLocalDateTo())) {
                     errors.add("alert", new ActionError("errors.docsSearch.LocalDateToNotDate"));
                     }
             }else if(bean.getDocDateFrom()!=null &&  !bean.getDocDateFrom().equals("")) {
                     if(!bean.isDate(bean.getDocDateFrom())) {
                     errors.add("alert", new ActionError("errors.docsSearch.DocDateFromNotDate"));
                     }
             }else if(bean.getDocDateTo()!=null &&   !bean.getDocDateTo().equals("")){
                     if(!bean.isDate(bean.getDocDateTo())) {
                     errors.add("alert", new ActionError("errors.docsSearch.DocDateToNotDate"));
                     }
             }else  if(bean.getDeadLineFrom()!=null && !bean.getDeadLineFrom().equals(""))  {
                     if(!bean.isDate(bean.getDeadLineFrom())) {
                     errors.add("alert", new ActionError("errors.docsSearch.DeadLineFromNotDate"));
                     }
             }else  if(bean.getDeadLineTo()!=null  &&  !bean.getDeadLineTo().equals("")){
                     if(!bean.isDate(bean.getDeadLineTo())) {
                     errors.add("alert", new ActionError("errors.docsSearch.DeadLineToNotDate"));
                     }
             }
         }
            
        return errors;
        }
  
}
