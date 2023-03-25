package com.action.servey;


import com.action.ACore;

import com.bo.servey.BServey;
import com.bo.servey.BServeyQuestions;

import com.exp.EException;

import com.form.servey.FServeyQuestions;

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
public class AServeyQuestions extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
  {  
    
        final String LOCATION = this.toString() + "->executeAction()";
        String target = _SUCCESS;
        FServeyQuestions bean = (FServeyQuestions)form;
        BServeyQuestions  bo =new BServeyQuestions();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        anchor=validate(anchor,errors,bean);
        if(bean.getValue("questionId")==null) bean.setQuestionId(0);
        if(!errors.isEmpty()){
        }else{
            if(anchor.equals(_CREATE)){
                    if(bo.addNew(bean)){
                        errors.add("alert",new ActionError("alert.insert.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.insert.unSuccessfull"));   
                    }
            }else if(anchor.equals("_SELECT_SERVEY")){
            
             target=anchor;
            }else if(anchor.equals(_EDIT)){
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("alert.update.unSuccessfull"));   
                    }
            }else if(anchor.equals(_VIEW)){
            request.setAttribute("servey",new BServey().getById(bean.getServeyId()));
            request.setAttribute("BTotals",bo.getByServeyId(bean.getServeyId()));
            target=anchor;
            }else if(anchor.equals("_COUNT")){
            if(bean.getQuestionId()>0){
                bo.count(bean);
                errors.add("alert",new ActionError("alert.do.servey.successfull"));   
            }
            bean.reset();
                target=anchor;
            }else if(anchor.equals(_DELETE)){
            
                    if(bo.delete(bean.getQuestionId(),(int)me.getId())){
                        errors.add("alert",new ActionError("alert.delete.successfull"));   
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.delete.unSuccessfull"));   
                    }
            }else if(anchor.equals(_SELECT)){
                if(bean.getServeyId()==0){
                    bean.reset();
                }else{
                    request.setAttribute("serveyQuestions",bo.getById(bean.getQuestionId()));
                }
            }
        }
        request.setAttribute("BServeyQuestions",bo.getByServeyId(bean.getServeyId()));
      request.setAttribute("BServey",new BServey().getAll());
      if(!errors.isEmpty()) saveErrors(request,errors);
    return mapping.findForward(target);
  }
  
  public String validate(String anchor,ActionErrors errors,FServeyQuestions bean) throws  EException,SQLException{
      BServeyQuestions bo =new BServeyQuestions();
      if(anchor.equals(_CREATE) || anchor.equals(_EDIT)){
              if(bean.getQuestion()==null ||  bean.getQuestion().equals(_BLANK)){
                  errors.add("alert",new ActionError("alert.code.isNull"));   
              }
      }
     return anchor;
  }
}
