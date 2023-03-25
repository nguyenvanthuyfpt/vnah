package com.action.pgroups;


import com.action.ACore;

import com.bo.mycontact.BMycontact;
import com.bo.pgroups.BPgroups;

import com.exp.EException;

import com.form.pgroups.FPgroup;

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


public class APgroups extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FPgroup bean = (FPgroup)form;
        bean.setDateCreate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setUserId(me.getId());
        String anchor=bean.getValue(APP_ANCHOR,"");
        BPgroups groups = new BPgroups();
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_EDIT) || anchor.equals(_EDIT_FALSE)){
                if(groups.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.error"));   
                }        
                request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(bean.me.getId()));     
                target =anchor;
                
        }else if(anchor.equals(_CREATE) || anchor.equals(_CREATE_FALSE)){               
                bean.setId(0);
                if(groups.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.insert.error"));   
                }
                //request.setAttribute("BPgroups",groups.getAllRecord(me.getId())); 
                bean.setName("");
                bean.setDescription("");
                bean.setId(0);
                request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(bean.me.getId()));  
                target =anchor;
                
        }else if(anchor.equals(_DELETE)){
                 
                if(groups.delete(bean)){
                    bean = new FPgroup();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
                request.setAttribute("BPgroups",groups.getAllRecord(me.getId())); 
                request.setAttribute("pgroup",new FPgroup());
                bean.setId(0);
                target = _CREATE;
        } else if (anchor.equals(_PREPARED_CREATE)){        
            request.setAttribute("BPgroups",groups.getAllRecord(me.getId()));
            request.setAttribute("pgroup",new FPgroup());
            target=_PREPARED_CREATE;
        } else if (anchor.equals(_PREPARE)){
            request.setAttribute("BPgroups",groups.getAllRecord(me.getId()));
            request.setAttribute("pgroup",new FPgroup());
            target = _PREPARE;
            
        }else if (anchor.equals(_VIEW)){
            request.setAttribute("BPgroups",groups.getAllRecord(me.getId()));   
            bean.setName("");
            bean.setDescription("");
            target = _VIEW;


        }else if (anchor.equals("_SELECT_AJAX")){
            if (bean.getId()==0){
                bean.setName("");
                bean.setDescription("");
            }else{
                request.setAttribute("pgroup",groups.getRecordById(bean));      
            }
            request.setAttribute("BPgroups",groups.getAllRecord(me.getId()));
        target=_PREPARED_CREATE;
        }else if (anchor.equals(_SELECT)){  
        
            request.setAttribute("pgroup",groups.getRecordById(bean));      
            request.setAttribute("BPgroups",groups.getAllRecord(me.getId())); 
            if (bean.getId()==0){
                bean.setName("");
                bean.setDescription("");
            }
            target = _SELECT;
        }
        
   
      

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FPgroup bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
                if (bean.getName()==null || bean.getName().trim().equals(_BLANK)){
                    errors.add("alert",new ActionError("alert.pgroup.name.error"));   
                }
       }
    return anchor;
    }
    
}
