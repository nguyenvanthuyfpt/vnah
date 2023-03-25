package com.action.require.requires;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.require.requires.BRequires;

import com.exp.EException;

import com.form.admin.departments.FDepartment;
import com.form.require.requires.FRequire;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ATrailer extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FRequire bean = (FRequire)form;
        bean.setDateCreate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setCreator(me.getId());
       
        BRequires bo = new BRequires();
        if (bean.me.getId()==0) return null;
      
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();      
        target = validate(bean,anchor,errors);                    
        if(errors.isEmpty()){           
             if(anchor.equals("_DETAIL")){
                    request.setAttribute("BRequire",bo.getById(bean));                   
                    target = "_DETAIL";
            }else if(anchor.equals("_SHOW_REQUIRE")){
                   request.setAttribute("BRequire",bo.getById(bean));                
                    target = "_SHOW_REQUIRE";
            }else if (anchor.equals(_CREATE)){
                  bean.setActive(1);
                  bean.setDepartmentId((int)me.getDepartmentID());
//                  if (bo.insert(bean)){
//                      errors.add("alert",new ActionError("require.cmd.insert.rm.susscess"));  
//                  }else{
//                      errors.add("alert",new ActionError("require.cmd.insert.rm.susscess"));  
//                  }
                target=_CREATE;
            }else if (anchor.equals("_REQUIRE_LIST")){
                request.setAttribute("BRequires",bo.getAllRequires(bean));
                target = "_REQUIRE_LIST";
            }
        }
        if(!errors.isEmpty())
        saveErrors(request,errors);    
        
        return mapping.findForward(target);
    }
    
    
    public void prepareView(HttpServletRequest request,FRequire bean,BRequires bo) throws  EException,SQLException{
        
        FRequire beanRequire =new FRequire();        
        FDepartment beanDept =new FDepartment();
        beanDept.setId((int)me.getDepartmentID());
        beanDept=new BDepartments().getRecordByID(beanDept);
        bean.setCode("YC"+"/"+me.getId()+"/"+ beanDept.getCode().toUpperCase());       
        bean.setSurcureId(System.currentTimeMillis());
        bean.setDateline(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setDatetimto(bean.getDateline());
        bean.setDatetimFrom(bean.getDateline());
        request.getSession().setAttribute("secureId",bean.getSurcureId());
        request.setAttribute("frmRequire",bean);
    }
    private String validate(FRequire bean,String anchor,ActionErrors errors){
//          if(anchor.equals(_EDIT)){
//            if((bean.getCode().trim().equals(_BLANK) || 
//                bean.getDescription().trim().equals(_BLANK) || 
//                bean.getTitle().trim().equals(_BLANK))){
//                errors.add("alert",new ActionError("errors.requires.edit.short"));   
//            }else if(!bean.getDeadLine().trim().equals(_BLANK) && (
//                     !bean.isDate(bean.getDeadLine()) ||
//                      bean.getCurrentDate().after(bean.stringToDate(bean.getDeadLine())))){
//                errors.add("alert",new ActionError("errors.requires.edit.deadline"));   
//            }else if( bean.getId()<=0){
//                errors.add("alert",new ActionError("errors.requires.edit.idnull"));   
//            }
//        }else if(anchor.equals(_CREATE)){
//            if((bean.getCode().trim().equals(_BLANK) || bean.getDescription().trim().equals(_BLANK) ||  bean.getTitle().trim().equals(_BLANK))){
//                errors.add("alert",new ActionError("errors.requires.edit.short"));   
//            }else if(!bean.getDeadLine().trim().equals("") &&(!bean.isDate(bean.getDeadLine()))){
//                      errors.add("alert",new ActionError("errors.requires.edit.deadline")); 
//                    
//            }else if((bean.getDeadLine()==null) || (bean.getDeadLine().equals(""))){
//                errors.add("alert",new ActionError("errors.requires.DeadLine.null"));   
//            }else if(bean.getCurrentDate().after(bean.stringToDate(bean.getDeadLine()))){
//                  errors.add("alert",new ActionError("errors.requires.DeadLine.after.CurrentDate"));   
//                
//            }
//        }else if(anchor.equals(_DELETE)){
//            if(bean.getId()<=0){
//                errors.add("alert",new ActionError("errors.requires.edit.idnull"));   
//            }
//        }
    return anchor;
    }     
  
}