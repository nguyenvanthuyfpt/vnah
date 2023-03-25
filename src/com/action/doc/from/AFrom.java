package com.action.doc.from;


import com.action.ACore;

import com.bo.admin.doc.category.form.BForm;
import com.bo.doc.from.BFrom;

import com.exp.EException;

import com.form.doc.from.FFrom;

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


public class AFrom extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FFrom bean = (FFrom)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BFrom bo =new BFrom();
        target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
        }else if(anchor.equals("_SEARCH_FROM_DOC")){
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target=_SEARCH_DOS_REFERENCE;
        }else if(anchor.equals(_SEARCH)){
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target=_SUCCESS;
        }else if(anchor.equals(_CREATE_SUCCESS)){
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target = _CREATE_SUCCESS;
        }else if(anchor.equals(_PREPARED_CREATE)){
                bean.reset();                
                request.setAttribute("BFoms",new BForm().getAllForm());
                request.setAttribute("from",bean);
                target=_PREPARE;        
        }else if(anchor.equals(_PREPARED_EDIT)){
                bean=bo.getFormById(bean);
                request.setAttribute("from",bean);
               request.setAttribute("BFoms",new BForm().getAllForm());
            target=_PREPARE;
        }else if(anchor.equals("_SAVE_NEW_RECV")){
              request.setAttribute("BFroms",bo.getAllFomByFromId(bean.getFomId()));
              target = "_SAVE_NEW_RECV";
        }else if(anchor.equals("_SAVE_NEW_SEND")){
              request.setAttribute("BFroms",bo.getAllFrom(bean));
              target = "_SAVE_NEW_SEND";              
        }else if(anchor.equals(_CREATE) || anchor.equals(_GET)){
                if(bo.addNew(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.insert.code.exits"));   
                }            
                if(anchor.equals(_GET)){
                    target = _GET;                      
                }else{
                    request.setAttribute("BFroms",bo.getAllFrom(bean));
                    target=_CREATE;
                }
        }else if(anchor.equals(_EDIT)){
                if(bo.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.insert.code.exits"));   
                }
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target=_CREATE;
        }else if(anchor.equals(_DELETE)){
            bo.delete(bean);
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target=_CREATE;
        } else if(anchor.equals("_UNITS_LIST")){   
            request.setAttribute("BFroms",bo.getAllFrom(bean));
            target=anchor;
        }       
        if(!errors.isEmpty()){
            saveErrors(request,errors);
            request.setAttribute("BFroms",bo.getAllFrom(bean));
        } 
       return mapping.findForward(target);
    }
    
    private String validate(FFrom bean,String anchor,ActionErrors errors){
       if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
                    if(bean.getCode()==null || bean.getCode().equals("")){
                        errors.add("alert",new ActionError("errors.from.code.null"));
                    }else if(bean.getVnName()==null || bean.getVnName().equals("")){
                        errors.add("alert",new ActionError("errors.from.vnName.null"));
                    }else if(bean.getEnName()==null || bean.getEnName().equals("")){
                        errors.add("alert",new ActionError("errors.from.enName.null"));
                    }
      }
        return anchor;
        }

}
