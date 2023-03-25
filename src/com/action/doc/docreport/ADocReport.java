package com.action.doc.docreport;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.doc.docreport.BDocReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.doc.docreport.FDocReport;

import com.inf.doc.IKeyDoc;

import com.lib.AppConfigs;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADocReport extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocReport bean = (FDocReport)form; 
        BDocReport bo=new BDocReport();
        
        if(bean.getValue("totalReport")==null) bean.setTotalReport(0);
        if(bean.getValue("depId")==null) bean.setDepId((int)me.getDepartmentID());
        if(bean.getValue("userId")==null) bean.setUserId((int)me.getId());
        if(bean.getValue("type")==null) bean.setType(0);        
        String anchor=bean.getValue(APP_ANCHOR,"");         
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        if(bean.getDocIds()!=null && bean.getDocIds().equals(",")){
            bean.setDocIds("");
        }        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_SELECT) || anchor.equals(_VIEW) ||  anchor.equals(_SEARCH)){    
        
            bean.setCheckObserver(Integer.parseInt(bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID?request.getSession().getAttribute(AppConfigs.CHECK_OBSERVER_DOCSRECV).toString():request.getSession().getAttribute(AppConfigs.CHECK_OBSERVER_DOCSSEND).toString()));              
            List params =new ArrayList();
            Date dayStart=null;int dayAmount=0;
             if (!anchor.equals(_SEARCH)){
                    if(bean.getTypeReport()==0){            
                        
                        dayStart=bean.getCurrentSqlDate();                
                        dayAmount=1;
                        bean.setFromDate(bean.dateToString(dayStart));
                        bean.setToDate(bean.getFromDate());                
                        
                    }else if(bean.getTypeReport()==1){            
                        dayStart=bean.addDays(bean.getCurrentSqlDate(),-bean.getDay(bean.getCurrentSqlDate())+2);
                        dayAmount=7;   
                        bean.setFromDate(bean.dateToString(dayStart));               
                        bean.setToDate(bean.dateToString(bean.addDays(dayStart,dayAmount-1)));                
                        
                    }else if(bean.getTypeReport()==2){     
                    
                        dayStart=bean.stringToSqlDate("01/"+ bean.getMonth(bean.getCurrentSqlDate()) +"/"+ bean.getYear(bean.getCurrentSqlDate()));    
                        dayAmount=bean.getDaysOfMonth(bean.getCurrentSqlDate());
                        bean.setFromDate(bean.dateToString(dayStart));               
                        bean.setToDate(bean.dateToString(bean.addDays(dayStart,dayAmount-1)));
                        
                    }else if(bean.getTypeReport()==3){            
                     
                        dayStart=bean.stringToSqlDate("01/01/"+ bean.getYear(bean.getCurrentSqlDate()));
                        String dayEnd=bean.getDaysOfMonth(bean.stringToSqlDate("01/12/"+ bean.getYear(bean.getCurrentSqlDate())))+"/12/"+bean.getYear(bean.getCurrentSqlDate());
                        dayAmount=bean.getDays(dayStart,bean.stringToSqlDate(dayEnd));
                        bean.setFromDate(bean.dateToString(dayStart));               
                        bean.setToDate(bean.dateToString(bean.addDays(dayStart,dayAmount)));
                     
                    }else{            
                        dayStart=bean.stringToSqlDate(bean.getFromDate());
                        dayAmount=1;                
                    }  
             }
           
            params.add(bean.stringToSqlDate(bean.getFromDate()));
            params.add(bean.addDays(bean.stringToSqlDate(bean.getToDate()),1));
            params.add(bean.getUserId());
            
            if (bean.getTotalReport()==0){
                 FBeans beans = new FBeans();
                 beans = new BDepartments().getAllRecord(0);
                 request.setAttribute("BDepartments",beans);
                 request.setAttribute("BUsers",bo.getUserByDepartmentID(bean.getDepId(),0));
            }
            
            if (bean.getTotalReport()!=1){
                    if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_STATUS){
                        request.setAttribute("BTotalsStatus",bo.getTotalDocByStatus(bean.getWorkflowId(),params));
                    }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_VIEWS){
                        request.setAttribute("BTotalsTransfer",bo.getTotalDocByTransfer(bean.getWorkflowId(),params));
                    }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_DOCTYPE){
                        request.setAttribute("BTotalsDocType",bo.getTotalDocByDocType(bean.getWorkflowId(),params));
                    }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_BRANCH){
                        request.setAttribute("BTotalsBranch",bo.getTotalDocByBranch(bean.getWorkflowId(),params));
                    }
            }
            request.setAttribute("BDocs",bo.getAllDoc(bean,bean.getPageIndex(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            request.setAttribute("docreport",bean);
            target=_SELECT;
            
        }else if(anchor.equals("_TOTALS")){
        
            List params =new ArrayList();
            Date dayStart=null;int dayAmount=0;
            dayStart=bean.stringToSqlDate(bean.getFromDate());
            dayAmount=bean.getDays(dayStart,bean.stringToSqlDate(bean.getToDate()));
            params.add(dayStart);
            params.add(bean.addDays(dayStart,dayAmount));
            params.add(bean.getUserId()==0?me.getId():bean.getUserId());
            bean.setFromDate(bean.dateToString(dayStart));
            bean.setToDate(bean.dateToString(bean.addDays(dayStart,dayAmount)));
            if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_STATUS){
                request.setAttribute("BTotalsStatus",bo.getTotalDocByStatus(bean.getWorkflowId(),params));
            }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_VIEWS){
                request.setAttribute("BTotalsTransfer",bo.getTotalDocByTransfer(bean.getWorkflowId(),params));
            }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_DOCTYPE){
                request.setAttribute("BTotalsDocType",bo.getTotalDocByDocType(bean.getWorkflowId(),params));
            }else if(bean.getTypeTotal()==IKeyDoc.REPORT_TYPE_TOTALS_BRANCH){
                request.setAttribute("BTotalsBranch",bo.getTotalDocByBranch(bean.getWorkflowId(),params));
            }
            request.setAttribute("docreport",bean);
            target=anchor;
            
        }else if(anchor.equals(_SHOW)){
        
            FBeans beans = new FBeans();
            beans = bo.getUserByDepartmentID(bean.getDepId(),0);
            request.setAttribute("BUsers",beans);
            bean.setUserId(beans!=null?(int)((FUser)beans.get(0)).getId():bean.getUserId());
            request.setAttribute("docreport",bean);
            target=anchor;
            
        }else if(anchor.equals("_OPEN_CHOSE")){
        
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
            request.setAttribute("BUsers",bo.getUserByDepartmentID(bean.getDepId(),0));
            request.setAttribute("docreport",bean);            
            target=bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID?anchor:"_OPEN_CHOSE_SEND";
            
        }else if(anchor.equals(_REPORT)){      
        
            if(bean.getValue("fields")==null) bean.setFields(null);
            List params =new ArrayList();
            Date dayStart=null;int dayAmount=0;
            dayStart=bean.stringToSqlDate(bean.getFromDate());
            dayAmount=bean.getDays(dayStart,bean.stringToSqlDate(bean.getToDate()));
            params.add(dayStart);
            params.add(bean.addDays(dayStart,dayAmount+1));           
            if(bean.getType()==0){
                params.add(bean.getUserId()>0?bean.getUserId():me.getId());
            }
            String pathFile=bo.exportExcel(bo.getAllReportExcel(seed,params,bean.getType()),bean,bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID?"data_recv.xls":"data_send.xls",bean.getType());
            bean.download(pathFile,"fileName.xls",null);
            bean.deleteFile(pathFile);
            target=anchor;

        }
        bean.reset();
       
       if(!errors.isEmpty()) saveErrors(request,errors);
       return mapping.findForward(target);
    }
  
    private String validate(FDocReport bean,String anchor,ActionErrors errors){
           if(anchor.equals(_SELECT)){
                if( bean.getFromDate()!=null && bean.getFromDate().equals("") && bean.isInteger(bean.getFromDate()) && bean.isDate(bean.getFromDate())){
                    errors.add("alert",new ActionError("error.broadcast.createtime.notIsDate"));                
                }else if(bean.getToDate()!=null && !bean.getToDate().equals("") && bean.isInteger(bean.getToDate()) && bean.isDate(bean.getToDate())){
                    errors.add("alert",new ActionError("error.broadcast.createtime.notIsDate"));                
                }
           }
        return anchor;
    }
  
}
