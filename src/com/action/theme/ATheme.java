package com.action.theme;


import com.action.ACore;

import com.bo.theme.BTheme;

import com.exp.EException;

import com.form.theme.FTheme;

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


public class ATheme extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FTheme bean = (FTheme)form;     
        BTheme bo = new BTheme();
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
       
       if(!errors.isEmpty()){
        }else if(anchor.equals(_CREATE)){  
            if (bo.addNew(bean)){
                errors.add("alert",new ActionError("alert.insert.successfull")); 
            }else{
                errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
            }
            request.setAttribute("BThemes",new BTheme().getAll());    
            target = _SHOW ;
        }else if(anchor.equals(_EDIT)){      
            if (bo.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
            }
            request.setAttribute("BThemes",new BTheme().getAll());    
            target = _SHOW ;
        }else if(anchor.equals(_DELETE)){    
            if (bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.delete.unSuccessfull"));   
            }
            request.setAttribute("theme",new FTheme());
            request.setAttribute("BThemes",new BTheme().getAll());    
            target = _SHOW ;
        }else if (anchor.equals(_PREPARE)){
           if (bean.getId()==0){
               request.setAttribute("theme",new FTheme());
           }else{
                request.setAttribute("theme",bo.getById(bean));
           }
           request.setAttribute("BThemes",new BTheme().getAll()); 
           target = _SHOW ;
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);        
        return mapping.findForward(target);
    }
    
    private String validate(FTheme bean,String anchor,ActionErrors errors){
        
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
//            if(bean.getTitle()==null || bean.getTitle().equals("")){
//                errors.add("alert",new ActionError("errors.from.code.null"));
//            }else if(bean.getPathImages()==null || bean.getPathImages().equals("")){
//                errors.add("alert",new ActionError("errors.from.vnName.null"));
//            }
        }
        return anchor;
        
    }
}
