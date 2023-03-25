package com.action.directory;


import com.action.ACore;

import com.bo.admin.users.BUsers;
import com.bo.directory.BDirectory;

import com.exp.EException;

import com.form.admin.users.FUser;
import com.form.directory.FDirectory;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADirectory extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDirectory bean = (FDirectory)form;                    
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();  
        BDirectory bo = new BDirectory();
        if (anchor.equals(_SEARCH)){   
            request.setAttribute("BDirectories",bo.getAllSearch(bean));             
            target = _SEARCH;
            
        }else if (anchor.equals(_SHOW)){
            BUsers users = new BUsers();
            FUser beanU = new FUser();
            beanU.setId(bean.getUserId());
            FUser user = users.getRecordByID(beanU);
            request.setAttribute("BUserInfor",user); 
            target = _SHOW;
        }
      
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    
   
}
