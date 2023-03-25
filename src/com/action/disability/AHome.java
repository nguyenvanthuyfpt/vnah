package com.action.disability;


import com.action.admin.login.ALoginSystem;

import com.exp.EException;

import com.form.admin.login.FLoginSystem;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AHome extends ALoginSystem {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        ActionErrors errors = new ActionErrors();
        
        FLoginSystem bean = (FLoginSystem)form;
        
        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }
}
