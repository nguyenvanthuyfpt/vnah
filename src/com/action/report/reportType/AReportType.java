package com.action.report.reportType;


import com.action.ACore;

import com.bo.report.reportType.BReportType;

import com.exp.EException;

import com.form.FBeans;
import com.form.report.reportType.FReportType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AReportType extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException 
  {  
    
    final String LOCATION = this.toString() + "->executeAction()";
    ActionErrors errors = new ActionErrors();
    
    String target = _SUCCESS;
    String anchor=request.getParameter(APP_ANCHOR);
    
    FReportType bean = (FReportType)form;

    if(anchor.equals(_CREATE) || anchor.equals(_EDIT) || anchor.equals(_DELETE))
    {
      if((bean.getName() == null) || (bean.getName().equals("")))
      {
        errors.add("alert",new ActionError("errors.category.reportType.namenull"));
      }else if((bean.getCode() == null) || (bean.getCode().equals("")))
      {
        errors.add("alert",new ActionError("errors.category.reportType.codenull"));
      }
    }
    if(errors.isEmpty())
    {
      //Het loi
      BReportType bo = new BReportType();
      if(anchor.equals(_CREATE)){ 
            bo.addNew(bean, errors);
      } else if(anchor.equals(_EDIT)){
        if(bean.getId()>0){
        if(!bo.checkNameIDReportType(bean)){
            bo.update(bean,errors);
        }else{
          errors.add("alert", new ActionError("errors.category.reportType.existname"));
        }
        }else{
            bean.reset();
            errors.add("alert", new ActionError("errors.data.notexist"));
        }
      }
      else if(anchor.equals(_DELETE))
      {
          bo.delete(bean, errors);
          errors.add("alert",new ActionError("alert.delete.successfull"));
          bean.reset();
      }
    }
    
     if(anchor.equals(_SELECT))
     {
      if(bean.getId() == 0)
      {
        bean.reset();
      }
      else
      {
        BReportType bo = new BReportType();
        FReportType beantemp = bo.getDocTypeById(bean);
        bean.setId(beantemp.getId());
        bean.setName(beantemp.getName());
        bean.setCode(beantemp.getCode());
        bean.setDescription(beantemp.getDescription());
      }
     }          
    
    
    BReportType boDocType = new BReportType();
    FBeans beansDocType = boDocType.getAllReportType();
    request.setAttribute("BReportType",beansDocType);  
    
    if(!errors.isEmpty()){
        saveErrors(request,errors);
    }
    
    return mapping.findForward(target);
  }
}
