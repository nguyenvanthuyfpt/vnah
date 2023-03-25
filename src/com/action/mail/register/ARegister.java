package com.action.mail.register;


import com.action.ACore;

import com.bo.admin.mail.BRegister;

import com.exp.EException;

import com.form.admin.mail.FMailAccount;

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

public class ARegister extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
        String target = _SUCCESS;
        FMailAccount bean = (FMailAccount)form;
        BRegister  bo =new BRegister();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        bean.setUserId((int)me.getId());
        if(bean.getValue("sercure")==null) bean.setSercure(0);
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
                    request.setAttribute("registerMail",new FMailAccount());
                anchor=_SHOW;
            }else if(anchor.equals(_PREPARED_CREATE)){
                request.setAttribute("registerMail",new FMailAccount());
                //request.setAttribute("BServerTypes",bo.getAllServerType());
                anchor=_SHOW;
            }else if(anchor.equals(_ACTIVE)){    
                bo.updateActive(bean);
                request.setAttribute("BRegisters",bo.getByMeId(me.getId()));   
                target="_ORDERS";
            }else if(anchor.equals(_PREPARED_EDIT)){
                
                request.setAttribute("registerMail",bo.getById(bean.getId()));
                //request.setAttribute("BServerTypes",bo.getAllServerType());
                target=_EDIT;
                
            }else if(anchor.equals(_EDIT)){
                    
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
                    }
                    request.setAttribute("registerMail",bo.getById(bean.getId()));
                anchor=_SHOW;
            }else if (anchor.equals("_ORDERS")){
                bo.orders(bean.getOrderEmail(),bean.getId(),bean.getIndex(),me.getId());
                request.setAttribute("BRegisters",bo.getByMeId(me.getId()));   
                target=anchor;        
                
            }else if(anchor.equals(_DELETE)){
                    
                    if(bo.delete(bean.getId())){
                        errors.add("alert",new ActionError("alert.delete.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.delete.unSuccessfull"));   
                    }
                    request.setAttribute("registerMail",new FMailAccount());
                   anchor=_SHOW; 
                
            }
            if(anchor.equals(_SHOW)){
                request.setAttribute("BRegisters",bo.getByMeId(me.getId()));   
               // request.setAttribute("BServerTypes",bo.getAllServerType());
            }
        }
        
      if(!errors.isEmpty()) saveErrors(request,errors);
    return mapping.findForward(target);
  }
  
  public String validate(HttpServletRequest request,String anchor,ActionErrors errors,FMailAccount bean) throws  EException,SQLException{
      BRegister bo =new BRegister();
     if(anchor.equals(_CREATE)){
             if(bean.getUserMail()==null ||  bean.getUserMail().equals(_BLANK)){
                 errors.add("alert",new ActionError("alert.userMail.isNull"));   
             }else if(bean.getPassMail()==null ||  bean.getPassMail().equals(_BLANK)){
                 errors.add("alert",new ActionError("alert.passMail.isNull"));   
             }
     }else if(anchor.equals(_EDIT)){
     
         if(bean.getUserMail()==null ||  bean.getUserMail().equals(_BLANK)){
             errors.add("alert",new ActionError("alert.userMail.isNull"));   
         }else if(bean.getPassMail()==null ||  bean.getPassMail().equals(_BLANK)){
             errors.add("alert",new ActionError("alert.passMail.isNull"));                
         }

//         else{
//             if(bo.checkUpdate(bean.getUserMail(),bean.getId())){
//                 errors.add("alert",new ActionError("alert.isExits.userMail"));   
//             }
//         }
         
     }
     if(!errors.isEmpty()){
      request.setAttribute("BRegisters",bo.getByMeId(me.getId()));     
         //request.setAttribute("BServerTypes",bo.getAllServerType());
     }
     return anchor;
  }
}
