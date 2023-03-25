package com.action.template.templateType;


import com.action.ACore;

import com.bo.template.templateType.BTemplateType;

import com.exp.EException;

import com.form.FBeans;
import com.form.template.templateType.FTemplateType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ATemplateType extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException 
  {  
    
    final String LOCATION = this.toString() + "->executeAction()";
    ActionErrors errors = new ActionErrors();
    
    String target = _SUCCESS;
    String anchor=request.getParameter(APP_ANCHOR);
    
    FTemplateType bean = (FTemplateType)form;

    if(anchor.equals(_CREATE) || anchor.equals(_EDIT) || anchor.equals(_DELETE))
    {
      if((bean.getName() == null) || (bean.getName().equals("")))
      {
        errors.add("alert",new ActionError("errors.category.templateType.namenull"));
      }else if((bean.getCode() == null) || (bean.getCode().equals("")))
      {
        errors.add("alert",new ActionError("errors.category.templateType.codenull"));
      }
    }
    if(errors.isEmpty())
    {
      //Het loi
      BTemplateType bo = new BTemplateType();
      if(anchor.equals(_CREATE)){ 
            bo.addNew(bean, errors);
      }else if(anchor.equals(_EDIT)){
        if(bean.getId()>0){
            if(!bo.checkNameIDTemplateType(bean)){
                bo.update(bean, errors);
            } else {
              errors.add("alert", new ActionError("errors.category.templateType.existname"));
            }
        }else{
            bean.reset();
            errors.add("alert", new ActionError("errors.data.notexist"));
        }
      } else if(anchor.equals(_DELETE)){
          bo.delete(bean, errors);
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
        BTemplateType bo = new BTemplateType();
        FTemplateType beantemp = bo.gettemplateTypeById(bean);
        bean.setId(beantemp.getId());
        bean.setName(beantemp.getName());
        bean.setCode(beantemp.getCode());
        bean.setDescription(beantemp.getDescription());
      }
     }          
    
    
    BTemplateType boDocType = new BTemplateType();
    FBeans beansDocType = boDocType.getAllTemplateType();
    request.setAttribute("BTemplateType",beansDocType);  
    
    if(!errors.isEmpty()){
        saveErrors(request,errors);
    }
    
    return mapping.findForward(target);
  }
}
