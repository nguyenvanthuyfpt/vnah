package com.action.servey;


import com.action.ACore;

import com.bo.servey.BServey;

import com.exp.EException;

import com.form.servey.FServey;

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


public class AServey extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
    
        final String LOCATION = this.toString() + "->executeAction()";
        String target = _SUCCESS;
        FServey bean = (FServey)form;
        BServey  bo =new BServey();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        anchor=validate(anchor,errors,bean);
        if(!errors.isEmpty()){
        }else{
            if(anchor.equals(_CREATE)){
                    if(bo.addNew(bean)){
                        errors.add("alert",new ActionError("alert.insert.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
                    }
            }else if(anchor.equals(_EDIT)){
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
                    }
            }else if(anchor.equals(_DELETE)){
                    if(bo.delete(bean.getServeyId())){
                        errors.add("alert",new ActionError("alert.delete.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.delete.unSuccessfull"));   
                    }
            }else if(anchor.equals(_SELECT)){
                if(bean.getServeyId()==0){
                    bean.reset();
                }else{
                    request.setAttribute("servey",bo.getById(bean.getServeyId()));
                }
            }
        }
        request.setAttribute("BServey",bo.getAll());
      if(!errors.isEmpty()) saveErrors(request,errors);
    return mapping.findForward(target);
  }
  
  public String validate(String anchor,ActionErrors errors,FServey bean) throws  EException,SQLException{
      BServey bo =new BServey();
      if(anchor.equals(_CREATE) || anchor.equals(_EDIT)){
              if(bean.getCode()==null ||  bean.getCode().equals(_BLANK)){
                  errors.add("alert",new ActionError("alert.code.isNull"));   
              }else if(bean.getName()==null ||  bean.getName().equals(_BLANK)){
                  errors.add("alert",new ActionError("alert.name.isNull"));   
              }else{
                  if(bean.getServeyId()==0){//case for addnew
                      if(bo.checkInser(bean.getCode(),0)){
                          errors.add("alert",new ActionError("alert.isExits.code"));   
                      }
                  }else{//case for update
                      if(bo.checkUpdate(bean.getCode(),bean.getServeyId())){
                          errors.add("alert",new ActionError("alert.isExits.code"));   
                      }        
                  }
              } 
      }
     return anchor;
  }
}
