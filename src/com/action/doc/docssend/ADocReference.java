package com.action.doc.docssend;


import com.action.ACore;

import com.bo.doc.docsSearch.BDocsSearch;
import com.bo.doc.docsrecv.BDocsrecv;
import com.bo.doc.docssend.BDocssend;

import com.exp.EException;

import com.form.doc.docsSearch.FDocsSearch;
import com.form.doc.docsrecv.FDocsrecv;

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


public class ADocReference extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocsSearch bean = (FDocsSearch)form;
        bean.setWorkflowId(1);
        bean.setUserId((int)me.getId());
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BDocsSearch bo =new BDocsSearch();       
            if(errors.isEmpty()){
                if(anchor.equals(_SEARCH_DOS_REFERENCE)){
                bean.setUserId((int)me.getId());
                    request.setAttribute("BSearch",bo.searchDocReference(bean));                   
                    target= _SEARCH_DOS_REFERENCE;                        
                }else if(anchor.equals("_DETAIL")){
                    
                    FDocsrecv beanRecv = new FDocsrecv(); 
                    beanRecv.setWorkflowId(bean.getWorkflowId());
                    beanRecv.setUserId((int)me.getId());
                    beanRecv.setId(bean.getId());
                    BDocsrecv boRecv =new BDocsrecv();
                    request.setAttribute("BDocsrecvs",boRecv.getDetail(beanRecv));                   
                    target="_DETAIL";
                    
                }else if(anchor.equals(_CREATE)){    
                                                      
                    request.getSession().setAttribute("docsIds",bean);
                    if (bean.getTemp()!=null && !bean.getTemp().equals(_BLANK)){
                        errors.add("alert",new ActionError("alert.doc.reference.successful"));
                    }else{
                        errors.add("alert",new ActionError("alert.doc.reference.not.option"));
                    }
                    request.setAttribute("BSearch",bo.searchDocReference(bean));                     
                    target = _SEARCH_DOS_REFERENCE;
                    
                }else if (anchor.equals("_SAVE_NEW_DOCREFERENCE")){                    
                    
                    FDocsSearch beanTemp = new FDocsSearch();    
                    beanTemp = (FDocsSearch)request.getSession().getAttribute("docsIds");                 
                    request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(beanTemp));                       
                    beanTemp.setTemp("");
                    bean.setTemp("");
                    request.getSession().removeAttribute("docsIds");
                    request.setAttribute("docssend",beanTemp);                          
                    target = "_SAVE_NEW_DOCREFERENCE" ;
                    
                }else if (anchor.equals(_DELETE)){
                    
                    BDocssend boSend = new BDocssend();
                 //   boSend.deleteReferent(bean);
                    request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(bean));   
                    FDocsSearch beanTemp = new FDocsSearch();    
                    beanTemp.setTemp(bean.getTemp());
                    beanTemp.setId(bean.getId());                   
                    request.setAttribute("docssend",bean);   
                    target = "_SAVE_NEW_DOCREFERENCE" ;
                    
                }else if (anchor.equals(_SHOW)){
                    target = _SHOW;
                }
        }
        
         if(!errors.isEmpty()) {
            saveErrors(request,errors);
         }
       return mapping.findForward(target);
    }
    
   
}
