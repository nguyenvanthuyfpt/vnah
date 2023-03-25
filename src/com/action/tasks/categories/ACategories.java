package com.action.tasks.categories;


import com.action.ACore;

import com.bo.tasks.categories.BCategories;
import com.bo.tasks.problem.BProblem;

import com.exp.EException;

import com.form.tasks.categories.FCategories;
import com.form.tasks.problem.FProblem;

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


public class ACategories extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FCategories bean = (FCategories)form;   
        bean.setCreator((int)me.getId());
        BCategories bo = new BCategories();
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        if (bean.me.getId()==0){
            return mapping.findForward(_LOGOUT);
        } 
        target = validate(bean,anchor,errors);
        
        if(errors.isEmpty()){     
            if (anchor.equals(_PREPARED_CREATE)){
                FCategories beanT=new FCategories();
                 request.setAttribute("formCate",beanT);
                 target = _PREPARED_CREATE;
            }else if (anchor.equals(_CREATE)){
                bean.setCreator((int)bean.me.getId());
                if(bo.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));      
                }else{
                    errors.add("alert",new ActionError("categories.add.new.error"));  
                }                
                request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));                  
                target =_CREATE ;
            }else if (anchor.equals(_PREPARED_EDIT)){            
                request.setAttribute("formCate",bo.getCategoriesById(bean));                                
                target = _PREPARED_CREATE;
            }else if (anchor.equals(_EDIT)){
                bean.setCreator((int)bean.me.getId());
                if (bo.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull")); 
                }else{
                    errors.add("alert",new ActionError("categories.update.new.error")); 
                }
                request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));  
                target = _CREATE;
            }else if (anchor.equals(_SELECT)){            
                request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));                        
                target  = _SELECT;            
            }else if (anchor.equals(_DELETE)){
                if (bo.delete(bean)){
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError(" alert.delete.error")); 
                }
                request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));                        
                target  = _CREATE;            
            }else if (anchor.equals(_SEARCH_PAGE)){
                request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));    
                target = _CREATE;
            }else if (anchor.equals(_PREPARE)){
                BProblem boP = new BProblem(); 
                FProblem beanP = new FProblem();
                beanP.setCategoriesId(bean.getId());
                request.setAttribute("BProblems",boP.getAllRecord(beanP,bean.me.getId())); 
                request.setAttribute("BCategories",boP.getAllCategories(bean)); 
                request.setAttribute("problem",beanP); 
                target = _PREPARE;
            }            
        } else {
            request.setAttribute("BCategories",bo.getAllRecord(bean,(int)bean.me.getId(),1));   
            target = _CREATE;
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    
    private String validate(FCategories bean,String anchor,ActionErrors errors){
       if(anchor.equals(_CREATE)){
            if (bean.getTitle().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("categories.add.new.error"));  
            }
       }else if (anchor.equals(_EDIT)){
           if (bean.getTitle().trim().equals(_BLANK)){
               errors.add("alert",new ActionError("categories.update.new.error"));  
           }
       }
       return anchor;
    }
}
