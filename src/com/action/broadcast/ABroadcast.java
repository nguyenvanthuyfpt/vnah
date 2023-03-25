package com.action.broadcast;


import com.action.ACore;

import com.bo.broadcast.BBroadcast;

import com.exp.EException;

import com.form.broadcast.FBroadcast;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ABroadcast extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException 
  {  
    
    final String LOCATION = this.toString() + "->executeAction()";
    ActionErrors errors = new ActionErrors();    
    String target = _SUCCESS;
    String anchor=request.getParameter(APP_ANCHOR);
    BBroadcast bo = new BBroadcast();
    FBroadcast bean = (FBroadcast)form;  
    bean.setContent(bean.getElm1());
    bean.setUser_id((int)me.getId());
    target = validate(bean,anchor,errors);
    
    if(errors.isEmpty()){         
      if(anchor.equals(_CREATE)){      
          if (bo.addNew(bean)){              
              errors.add("alert",new ActionError("broadcast.insert.sussecss"));  
          }else{
              errors.add("alert",new ActionError("broadcast.insert.error"));
          }
          target = _CREATE;
          
      }else if (anchor.equals(_PREPARED_EDIT)){     
          request.setAttribute("broadcast",bo.getBroadcastById(bean));
          target = _PREPARE;          
      }
      else if(anchor.equals(_EDIT)){ 
          if (bo.update(bean)){
              errors.add("alert",new ActionError("broadcast.edit.sussecss"));  
          }else{
              errors.add("alert",new ActionError("broadcast.edit.error"));  
          }
           target = _CREATE;
      }
      else if(anchor.equals(_DELETE)){
      
            if ( bo.delete(bean)){
                errors.add("alert",new ActionError("broadcast.delete.sussecss"));  
            }else{
                errors.add("alert",new ActionError("broadcast.delete.error"));  
            }
            request.setAttribute("BBroadcasts",bo.getAllBroadcast(bean));
           target = _VIEW;         
      
      }else if (anchor.equals("_DETAIL")){
           request.setAttribute("BRoadcast",bo.getBroadcastById(bean)); 
           request.setAttribute("BRoadcasts",bo.getAllBroadcastTop(bean)); 
           target = "_DETAIL";
      }
      else if (anchor.equals(_VIEW)){
           request.setAttribute("BBroadcasts",bo.getAllBroadcast(bean));
           target = _VIEW;
           
      }else if (anchor.equals("_MAIN_VIEW") || anchor.equals(_SHOW)){
         if (!(bean.getCreatetime()!=null && !bean.getCreatetime().equals("") && bean.isDate(bean.getCreatetime()))){
            bean.setCreatetime(bean.dateToString(bean.getCurrentSqlDate()));
         }
          request.setAttribute("BBroadcasts",bo.getShowCalenda(bean));
          target = anchor;
      }
      else if (anchor.equals(_PREPARE)){          
          bean.setTitle("");
          bean.setContent("");
          target = _PREPARE;          
      }else if (anchor.equals("_ORDERS")){
          bo.orders(bean.getOrders(),bean.getBroadcastId(),bean.getIndex());
          request.setAttribute("BBroadcasts",bo.getAllBroadcast(bean));
          target =_VIEW;          
      }
      
    }else{
    
    }
    if(!errors.isEmpty()){
        saveErrors(request,errors);
    }
    
    return mapping.findForward(target);
  }
  
  
    private String validate(FBroadcast bean,String anchor,ActionErrors errors){
       if(anchor.equals(_CREATE) || anchor.equals(_EDIT)){
            if (bean.getTitle()==null || bean.getTitle().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("broadcast.infor.check"));  
            }
       }
       if(anchor.equals(_VIEW)){
            if(bean.getCreatetime()!=null && !bean.getCreatetime().equals("")){
                         if(!bean.isDate(bean.getCreatetime())){
                             errors.add("alert",new ActionError("error.broadcast.createtime.notIsDate"));  
                         }
            }
        }
       return anchor;
    }
}
