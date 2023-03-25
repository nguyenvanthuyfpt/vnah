package com.action.mail;


import com.action.ACore;

import com.exp.EException;

import com.form.mail.FMail;

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


public class AStoreTemp extends  ACore {
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws EException
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws Throwable
     */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    //,Throwable
        {
            final String LOCATION = this + "->executeAction()";
            String target = _ERROR;
            FMail bean = (FMail)form;
            request.setCharacterEncoding("utf-8");
            String anchor=bean.getValue(APP_ANCHOR,"");
            ActionErrors errors = new ActionErrors();
            anchor=validate(request,anchor,errors,bean);
            if(!errors.isEmpty()){
                    target=anchor;
            }else{
                if(anchor.equals("_DOWNLOAD")){
                    FMail beanTem=(FMail)request.getSession().getAttribute("sendMail");
                    beanTem=(FMail)beanTem.getAllFiles().get(bean.getFileId()-1);
                    String filePath =AppConfigs.APP_SYSTEM_PATH + AppConfigs.FILE_MAIL_UPLOAD  + AppConfigs.SYSTEM_FILE_SCHIP +  bean.getUserMail() + AppConfigs.SYSTEM_FILE_SCHIP + beanTem.getFileId();
                }
            }
            
            if(!errors.isEmpty()) saveErrors(request,errors);
            return mapping.findForward(target);
        }
    

    
    public String validate(HttpServletRequest request,String anchor,ActionErrors errors,FMail bean) throws  EException,SQLException{
       
       if(anchor.equals(_SEND)){
           if(bean.getToAddress()==null || bean.getToAddress().equals("")){
               errors.add("alert",new ActionError("alert.error.toaddress.isNull"));   
           } 
       }
       return anchor;
    }
    
}