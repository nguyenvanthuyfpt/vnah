package com.action.sms;


import com.action.ACore;

import com.bo.servey.BServey;

import com.exp.EException;

import com.form.sms.FSendSms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.net.URLEncoder;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.net.www.protocol.http.HttpURLConnection;


public class ASendSms extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
    
        final String LOCATION = this.toString() + "->executeAction()";
        String target = _SUCCESS;
        FSendSms bean = (FSendSms)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        anchor=validate(anchor,errors,bean);
        String to ="84"+bean.getTo().substring(1,bean.getTo().length());
         String msg="";
                  try{
                  msg=URLEncoder.encode(bean.getContent(), "UTF-8");
                  } catch (UnsupportedEncodingException e) {
//                  //.println("Encoding not supported");
                  }
            if(anchor.equals("_SEND_SMS")){
                String providerUrl ="http://messenger.vietguys.biz/api/?phone="+to+"&from=8027&sms="+msg+"&account=vietsoftware&code=edxn4";
                 try{
                 URL url = new URL(providerUrl);
                 HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                 connection.setRequestMethod("GET");
                 connection.connect();
                 BufferedReader in1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 String line;
                 while ((line = in1.readLine()) != null)
                 System.out.print(line +"\n");
                 } catch (Exception ex)  {
                     ex.printStackTrace();
                 } finally  {
                 }
                 request.setAttribute("alertSmsSend",1);
                 target=anchor;
            }
            
      if(!errors.isEmpty()) saveErrors(request,errors);
    return mapping.findForward(target);
  }
  
  public String validate(String anchor,ActionErrors errors,FSendSms bean) throws  EException,SQLException{
      BServey bo =new BServey();
      if(anchor.equals(_CREATE) || anchor.equals(_EDIT)){
              
      }
     return anchor;
  }
}
