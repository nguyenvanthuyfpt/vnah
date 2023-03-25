package com.action.disability;


import com.action.ACore;

import com.bo.disability.BInforNKT;

import com.exp.EException;

import com.form.disability.FInforNKT;

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


public class AInforNKT extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FInforNKT bean = (FInforNKT)form;         
        BInforNKT bo=new BInforNKT();
        String anchor=bean.getValue(APP_ANCHOR,"");        
        if(anchor.equals("_INSERT")){
                if(bo.addBatch(bean.getTempId(),bean.getNktId())){
                    errors.add("alert",new ActionError("alert.insert.successfull"));
                }else{
                    errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
                }
            request.setAttribute("infor",bean);
            request.setAttribute("srcString",new BInforNKT().getSRC(bean.getNktId()));
            request.setAttribute("BTemps",new BInforNKT().getAllTemp(bean));
            target=_CREATE;
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
   
   
}
