package com.action.cabin.cabinType;


import com.action.ACore;

import com.bo.cabin.BCabin;
import com.bo.cabin.cabinType.BCabinType;

import com.exp.EException;

import com.form.cabin.FCabin;
import com.form.cabin.cabinType.FCabinType;

import com.inf.cabin.IKeyCabin;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ACabinType extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
    
    final String LOCATION = this.toString() + "->executeAction()";
    ActionErrors errors = new ActionErrors();
    String target = _SUCCESS;
    String anchor=request.getParameter(APP_ANCHOR);
    FCabinType bean = (FCabinType)form;
    bean.setUserId((int)me.getId());
    bean.setDepartmentId((int)me.getDepartmentID());
    BCabinType bo = new BCabinType();
      target = validate(bean,anchor,errors);
    if(!errors.isEmpty()){
    }else{
     if(anchor.equals(_CREATE)){ 
              if(bo.addNew(bean)){
                    bean.reset();
                    errors.add("alert",new ActionError("alert.insert.successfull"));
              }else{
                    errors.add("alert",new ActionError("errors.cabinType.insert.cabinTypecode"));
              }
              anchor=_SUCCESS;
              
      } else if(anchor.equals(_VIEW)){        
          
          bean.setType(IKeyCabin.CABIN_PRIVATE);
          request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes0",bo.getAll(me.getId(),0,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes1",bo.getAll(me.getId(),1,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes3",bo.getAll(me.getId(),3,(int)me.getDepartmentID()));
          request.setAttribute("cabinType",bean); 
          target=anchor;
            
      } else if(anchor.equals("_VIEW_DEP")){
        int type=bean.getType();
          bean.reset();
          bean.setType(type);
          request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes0",bo.getAll(me.getId(),0,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes1",bo.getAll(me.getId(),1,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes3",bo.getAll(me.getId(),3,(int)me.getDepartmentID()));
          request.setAttribute("cabinType",bean); 
          target=_VIEW;

      } else if(anchor.equals(_EDIT)){
                   if( bo.update(bean)){
                       errors.add("alert",new ActionError("alert.update.successfull"));
                   }else{
                       errors.add("alert",new ActionError("alert.update.error"));
                   }
              anchor=_SUCCESS;
          
      }  else if(anchor.equals(_DELETE)){
                  if(bo.delete(bean)){
                      bean.reset();
                      errors.add("alert",new ActionError("alert.delete.successfull"));
                  }else{
                      errors.add("alert",new ActionError("errors.cabinType.delete.exist"));
                  }
              anchor=_SUCCESS;
            
      }else if(anchor.equals("_SELECT_TYPE")){
          request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes0",bo.getAll(me.getId(),0,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes1",bo.getAll(me.getId(),1,(int)me.getDepartmentID()));
          request.setAttribute("BCabinTypes3",bo.getAll(me.getId(),3,(int)me.getDepartmentID()));
          
      }else if(anchor.equals(_SELECT)){
            if(bean.getId()==0){bean.reset();}else{
            bean=bo.getCabinTypeById(bean); 
            }
            request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes0",bo.getAll(me.getId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",bo.getAll(me.getId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",bo.getAll(me.getId(),3,(int)me.getDepartmentID()));
            request.setAttribute("cabinType",bean); 
target="_PREPARE_EDIT";
      }
    
    if(anchor.equals(_SUCCESS)){
        request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID()));
        request.setAttribute("BCabinTypes0",bo.getAll(me.getId(),0,(int)me.getDepartmentID()));
        request.setAttribute("BCabinTypes1",bo.getAll(me.getId(),1,(int)me.getDepartmentID()));
        request.setAttribute("BCabinTypes3",bo.getAll(me.getId(),3,(int)me.getDepartmentID()));
        request.setAttribute("cabinType",bean); 
        FCabin beanT=new FCabin();
        beanT.setType(bean.getType());
        beanT.setCabinType_id(bean.getParentID());
        beanT.setMeId(me.getId());
        BCabin boT=new BCabin();
        beanT.setDepartmentID((int)me.getDepartmentID());
        request.setAttribute("BCabin",boT.getAllCabinUnionCabinType(beanT));
            beanT.setRootPath("");
            if(beanT.getCabinType_id()>0){
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(beanT.getCabinType_id());
                List params =new ArrayList();
               for (int i=beanT.getCabinType_id();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   beanT.setRootPath(beanT.getRootPath()+"/"+ params.get(i));
                }
            }
            
            request.setAttribute("cabin",beanT);
        target=anchor;
        }     
    }

    if(!errors.isEmpty()){
        saveErrors(request,errors);
    }
    
    return mapping.findForward(target);
  }
    private String validate(FCabinType bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
            if(bean.getName().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.cabinType.edit.name.null"));   
                anchor=_SUCCESS;
            }
        }
    return anchor;
    }

}
