package com.action.foryou;


import com.action.ACore;

import com.bo.admin.doc.rules.BDocRules;
import com.bo.foryou.BForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.doc.rules.FWorkflow;
import com.form.foryou.FForYou;

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


public class AForYou extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FForYou bean = (FForYou)form;
        bean.setMeId(me.getId());
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BForYou bo =new BForYou();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
        
            FBeans beans = new FBeans();
            bean = new FForYou();
            beans = new BDocRules().getAllWorkflow();
            request.setAttribute("BWorkflows",beans);
            if (beans!=null && beans.size()>0){
                bean.setWorkflowId(((FWorkflow)beans.get(0)).getWorkflowId());
            }           
            
            bean.setBoss(me.getFullName());
            bean.setUserIdFrom((int)me.getId());
            bean.setMeId(me.getId());
            request.setAttribute("forYou",bean);
            request.setAttribute("BUsers",bo.getAllDiffUserId(bean));
            if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
                request.setAttribute("BForYous",bo.getByUserIdFrom(bean,(int)me.getId())); 
            }else{
                target=_PREPARE;
            }
        }else  if(anchor.equals(_DELETE)){ 
        
            if(bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.forYou.delete.faill"));      
            }
            request.setAttribute("BForYous",bo.getByUserIdFrom(bean,(int)bean.me.getId()));
            request.setAttribute("BWorkflows",new BDocRules().getAllWorkflow());
            target=_EDIT;
        }else  if(anchor.equals("_FORYOU_LIST")){     
            bo.updateActive(bean);
            request.setAttribute("BForYous",bo.getByUserIdTo(bean,(int)me.getId()));
            target=anchor;
        }else  if(anchor.equals(_EDIT)){ 
        
             bean.setUserIdFrom((int)bean.me.getId());
            if(bo.update(bean)){
                 errors.add("alert",new ActionError("alert.forYou.update.successfull"));   
            }else{
                  errors.add("alert",new ActionError("errors.forYou.update.faill"));      
            }
            request.setAttribute("BForYous",bo.getByUserIdFrom(bean,(int)bean.me.getId()));
            request.setAttribute("BUsers",bo.getAllDiffUserId(bean));
            bean.setPublicInfor(0);
            target=_CREATE;
            
        }else  if(anchor.equals(_PREPARED_EDIT)){ 
        
            request.setAttribute("forYou",bo.getById(bean));
            request.setAttribute("BUsers",bo.getAllDiffUserId(bean));
            request.setAttribute("BWorkflows",new BDocRules().getAllWorkflow());
            target=_FORYOU_CREATE;
            
        }else  if(anchor.equals(_CREATE)){ 
            bean.setBoss(me.getFullName());
            if(bo.addNew(bean)){
                 errors.add("alert",new ActionError("alert.insert.successfull"));   
            }else{
                  errors.add("alert",new ActionError("errors.forYou.insert.faill"));      
            }
           
            request.setAttribute("BForYous",bo.getByUserIdFrom(bean,(int)me.getId())); 
            bean.setPublicInfor(0);
            target= _CREATE;
        }else  if(anchor.equals(_VIEW)){ 
            request.setAttribute("BForYous",bo.getByUserIdFrom(bean,(int)me.getId())); 
            target= _CREATE;
        }else if (anchor.equals(_FORYOU_CREATE)){
        
            FBeans beans = new FBeans();
            bean = new FForYou();
            beans = new BDocRules().getAllWorkflow();
            request.setAttribute("BWorkflows",beans);
            if (beans!=null && beans.size()>0){
                bean.setWorkflowId(((FWorkflow)beans.get(0)).getWorkflowId());
            }           
           
            bean.setBoss(me.getFullName());
            bean.setUserIdFrom((int)me.getId());
            bean.setMeId(me.getId());
            bean.setPublicInfor(0);
            request.setAttribute("forYou",bean);
            request.setAttribute("BUsers",bo.getAllDiffUserId(bean));
            target = _FORYOU_CREATE;
            
        }else if (anchor.equals(_SELECT)){
        
            request.setAttribute("BUsers",bo.getAllDiffUserId(bean));
            target = _SELECT;

        }else if (anchor.equals("_VIEW_DOCS")){
        if(bean.getWorkflowId()==1){
            request.setAttribute("BListDocsRecv",bo.getAllDocByForyouId(bean.getId(),bean.getWorkflowId()));
        }else{
            request.setAttribute("BListDocsSend",bo.getAllDocByForyouId(bean.getId(),bean.getWorkflowId()));
        }
            
            target =anchor;
        }else if (anchor.equals(_SHOW)){
            request.setAttribute("BForYou",bo.getById(bean));
            request.setAttribute("BDetailts",bo.getAllDetailt(bean));
            target = _SHOW ;
            
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
       return mapping.findForward(target);
    }
    
    private String validate(FForYou bean,String anchor,ActionErrors errors){
            if(anchor.equals(_CREATE)  || anchor.equals(_EDIT) ){
                    if(bean.getUserIdTo()<=0){
                        errors.add("alert",new ActionError("errors.foryou.edit.userIdTo.unPermision"));   
                    }else if(bean.getDateFrom()==null ||  bean.getDateFrom().equals("") || !bean.isDate(bean.getDateFrom())){
                        errors.add("alert",new ActionError("errors.foryou.edit.dateFrom.notIsDate"));   
                    }else if(bean.getDateTo()==null || bean.getDateTo().equals("") || !bean.isDate(bean.getDateTo())){                       
                        errors.add("alert",new ActionError("errors.foryou.edit.dateTo.notIsDate"));                          
                    }else if (bean.stringToDate(bean.getDateFrom()).after(bean.stringToDate(bean.getDateTo()))){
                        errors.add("alert",new ActionError("errors.foryou.edit.dateTo.dateFrom"));
                    }
            }
        return anchor;
        }

}
