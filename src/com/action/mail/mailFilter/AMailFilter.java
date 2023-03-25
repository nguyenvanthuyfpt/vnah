package com.action.mail.mailFilter;


import com.action.ACore;

import com.bo.mail.mailFilter.BMailFilter;

import com.exp.EException;

import com.form.mail.mailFilter.FMailFilter;

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


/***
 * ms.duong van duc
 * ***/

public class AMailFilter extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
        String target = _SUCCESS;
        FMailFilter bean = (FMailFilter)form;
        BMailFilter  bo =new BMailFilter();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        bean.setUserId((int)me.getId());
        anchor=validate(request,anchor,errors,bean);
        if(!errors.isEmpty()){
        }else{
            if(anchor.equals(_CREATE)){
                    if(bo.addNew(bean)){
                        errors.add("alert",new ActionError("alert.insert.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
                    }
                    request.setAttribute("mailFilter",new FMailFilter());
                anchor=_SHOW;
            }else if(anchor.equals(_PREPARED_CREATE)){
                request.setAttribute("mailFilter",new FMailFilter());
                anchor=_SHOW;
            }else if(anchor.equals(_PREPARED_EDIT)){
                
                request.setAttribute("mailFilter",bo.getById(bean.getId()));
                //request.setAttribute("BServerTypes",bo.getAllServerType());
                target=_EDIT;
                
            }else if(anchor.equals(_EDIT)){
                    
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
                    }
                    request.setAttribute("mailFilter",bo.getById(bean.getId()));
                    anchor=_SHOW;
            }else if(anchor.equals(_DELETE)){
                    
                    if(bo.delete(bean.getId())){
                        errors.add("alert",new ActionError("alert.delete.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.delete.unSuccessfull"));   
                    }
                    request.setAttribute("mailFilter",new FMailFilter());
                   anchor=_SHOW; 
                
            }
            if(anchor.equals(_SHOW)){
                request.setAttribute("BMailFilters",bo.getAllByMeId(me.getId()));   
            }
        }
        
      if(!errors.isEmpty()) saveErrors(request,errors);
    return mapping.findForward(target);
  }
  
  public String validate(HttpServletRequest request,String anchor,ActionErrors errors,FMailFilter bean) throws  EException,SQLException{
      BMailFilter bo =new BMailFilter();
     if(anchor.equals(_CREATE)){
     }else if(anchor.equals(_EDIT)){
     }
     
     return anchor;
  }
}
