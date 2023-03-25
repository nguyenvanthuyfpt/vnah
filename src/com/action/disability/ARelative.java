package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDisability;
import com.bo.disability.BRelative;
import com.bo.disability.categorys.BQuanhe;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FDisability;
import com.form.disability.FRelative;

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


public class ARelative extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FRelative bean = (FRelative)form;         
        BRelative bo=new BRelative();
        String anchor=((FSeed)form).getValue(APP_ANCHOR,"");  
        target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
        }else if(anchor.equals("_LIST_SHOW")){ 
            target =anchor;
        }else if(anchor.equals("_SEARCH")){
        
            target =anchor;
        }else if(anchor.equals("_INSERT")){
            if(bo.insert(bean)){
                errors.add("alert",new ActionError("alert.insert.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
            }
            bean.reset();
            request.setAttribute("relative",bean);
            target="_RELATIVE";
        }else if(anchor.equals("_UPDATE")){
            if(bo.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
            }
            request.setAttribute("relative",bean);
            target="_RELATIVE";
        }else if(anchor.equals("_DELETE")){
            
            bo.delete(bean.getId());
            request.setAttribute("relative",bean);
            target="_RELATIVE";
            
        }else if(anchor.equals("_DETAIL")){
            FRelative beantemp =new FRelative();
            beantemp=bo.getRecordByID(bean);
            beantemp.setIdNkt(bean.getIdNkt());
            request.setAttribute("relative",beantemp);
            target="_RELATIVE";
        }
        request.setAttribute("BRelatives",new BRelative().getAllByIdNkt(bean.getIdNkt()));
        FDisability T= new FDisability();
        T.setId(bean.getIdNkt());
        T=new BDisability().getRecordByID(T);
        T.setSearchSub(bean.getSearchSub());
        request.setAttribute("BRelativeNkts",new BDisability().getAllSearch(T));
        request.setAttribute("BLyDos",new BQuanhe().getAllRecord(0));
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
   
    private String validate(FRelative bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
//            if((bean.getNkt().trim().equals(_BLANK))||(bean.getMa().trim().equals(_BLANK))){
//                errors.add("alert",new ActionError("errors.disability.edit.short"));   
//            }
        }
    return anchor;
    }

    
}
