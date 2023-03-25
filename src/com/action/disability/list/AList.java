package com.action.disability.list;


import com.action.ACore;

import com.bo.admin.reportSystem.BReportSystem;
import com.bo.disability.list.BList;

import com.exp.EException;

import com.form.disability.list.FList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AList extends ACore 
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException 
  {  
    
    final String LOCATION = this.toString() + "->executeAction()";
    String target = _SUCCESS;
    FList bean = (FList)form;  
    bean.setUserId((int)me.getId());
    BList bo = new BList();     
    ActionErrors errors = new ActionErrors();
    String anchor=bean.getValue(APP_ANCHOR,"");
    if (anchor.equals(_DELETE)){     
      if (bo.delete(bean)){
          errors.add("listErrors", new ActionError("errors.delete"));          
      }
      request.setAttribute("beans",bo.getAllListReport(bean));
        request.setAttribute("anchor",1);
      bean.setIdEditName(0);
      target="_LIST_REPORT";
    }else if (anchor.equals(_PREPARED_EDIT)){       
      //  bo.getListReportById(bean);
        bean.setListId(0);
        request.setAttribute("beans",bo.getAllListReport(bean));        
        target=_PREPARED_EDIT;
    }else if (anchor.equals(_EDIT)){        
        bean.setEmpEstablised((int)me.getId());   
        if (bo.update(bean)){          
           errors.add("listErrors", new ActionError("errors.edit"));                                    
        }
        request.setAttribute("beans",bo.getAllListReport(bean));   
        bean.setIdEditName(0);
        target=_EDIT;
    }else if (anchor.equals("_SHOW_LISTEMP")){
        BList boL = new BList();                 
        request.setAttribute("beansEmp",boL.getEmpByListReport(bean)); 
        target="_SHOW_LISTEMP";
    }else if (anchor.equals("_DELETE_EMP")){       
        if (bo.deleteEmp(bean)){
            errors.add("listErrors", new ActionError("errors.delete")); 
        }
        BList boL = new BList();
        request.setAttribute("beans",bo.getAllListReport(bean));
        request.setAttribute("beansEmp",boL.getEmpByListReport(bean));
        bean.setIdEditName(0);
        target="_DELETE_EMP";
    }else if (anchor.equals("_PREPARED_SHOW")){ 
    
        FList beanL =new FList();
        beanL.setUserId((int)me.getId());   
        beanL.setPageIndex(bean.getPageIndex());
        BList boL = new BList();           
        request.setAttribute("beans",boL.getAllListReport(beanL));
        request.setAttribute("BReports",new BReportSystem().getAll());
        beanL.setRadioCheck(bean.getRadioCheck());
        request.setAttribute("list",beanL);
        bean.setIdEditName(0);
        target="_PREPARED_SHOW";
    }else if (anchor.equals("_LIST_SHOW")){
        BList boL = new BList();   
        request.setAttribute("beans",boL.getAllListReport(bean));      
        bean.setIdEditName(0);
        target="_LIST_SHOW";
//    }else if (anchor.equals("_LIST_REPORT")){ 
//            BListreport boreport = new BListreport();         
//        String report = boreport.exportExcel(boreport.getListReportApps(bean),bean,AppConfigs.REPORT_FILE_EXCEL_APPS);            
//        bean.download(report,IKey.REPORT_FILE_NAME,null);
//        bean.deleteFile(report); 

    }
    if(!errors.isEmpty()){
        saveErrors(request,errors);
    }    
    return mapping.findForward(target);
  }
}
