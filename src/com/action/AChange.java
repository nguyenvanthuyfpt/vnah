package com.action;


import com.bo.admin.departments.BDepartments;
import com.bo.admin.doc.category.branch.BBranch;
import com.bo.admin.doc.category.classify.BClassify;
import com.bo.admin.doc.category.dataBaseServer.BDataBaseServer;
import com.bo.admin.doc.category.doctype.BDocType;
import com.bo.admin.doc.category.dossiers.BDossiers;
import com.bo.admin.doc.category.express.BExpress;
import com.bo.admin.doc.category.form.BForm;
import com.bo.admin.doc.category.secure.BSecure;
import com.bo.admin.doc.category.status.BStatus;
import com.bo.admin.doc.category.thePen.BThePen;
import com.bo.admin.doc.category.transfer.BTransfer;
import com.bo.admin.doc.category.via.BVia;
import com.bo.admin.doc.rules.BDocRules;
import com.bo.admin.mail.BRegister;
import com.bo.admin.reports.rules.BReportsRule;
import com.bo.admin.templates.rules.BTemplatesRule;
import com.bo.admin.users.BUsers;
import com.bo.broadcast.BBroadcast;
import com.bo.cabin.BCabin;
import com.bo.cabin.cabinType.BCabinType;
import com.bo.calendar.agenda.BCalendar;
import com.bo.calendarMeeting.agenda.BCalendarMeeting;
import com.bo.calendarXe.BCalendarXe;
import com.bo.directory.BDirectory;
import com.bo.doc.assign.BAssignRecv;
import com.bo.doc.docreport.BDocReport;
import com.bo.doc.docsrecv.BDocsrecv;
import com.bo.doc.docssend.BDocssend;
import com.bo.doc.from.BFrom;
import com.bo.foryou.BForYou;
import com.bo.main.BMain;
import com.bo.messages.create.BCreate;
import com.bo.mycontact.BMycontact;
import com.bo.pgroups.BPgroups;
import com.bo.report.BReport;
import com.bo.report.reportType.BReportType;
import com.bo.servey.BServey;
import com.bo.servey.BServeyQuestions;
import com.bo.tasks.categories.BCategories;
import com.bo.tasks.problem.BProblem;
import com.bo.template.BTemplate;
import com.bo.template.templateType.BTemplateType;

import com.dao.calendar.DCalendarLib;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.rules.FWorkflow;
import com.form.admin.templates.rules.FTemplatesRule;
import com.form.admin.users.FUser;
import com.form.broadcast.FBroadcast;
import com.form.cabin.FCabin;
import com.form.cabin.cabinType.FCabinType;
import com.form.calendar.agenda.FAgenda;
import com.form.calendarMeeting.agenda.FAgendaMeeting;
import com.form.calendarXe.FCalendarXe;
import com.form.directory.FDirectory;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docreport.FDocReport;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;
import com.form.doc.from.FFrom;
import com.form.foryou.FForYou;
import com.form.messages.create.FCreate;
import com.form.mycontact.FMycontact;
import com.form.pgroups.FPgroup;
import com.form.report.FReport;
import com.form.servey.FServey;
import com.form.tasks.categories.FCategories;
import com.form.tasks.problem.FProblem;
import com.form.template.FTemplate;

import com.inf.IRoles;
import com.inf.cabin.IKeyCabin;

import com.lib.AppConfigs;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AChange extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FProblem bean = (FProblem)form;         
        request.getSession().setAttribute("BApp",bean.getApp());            
        FCreate beanC = new FCreate();             
        beanC.setType(bean.getType());        
        String anchor=((FSeed)form).getValue(APP_ANCHOR,"");        
        BForYou bo =new BForYou();
        if(bean.getValue("statusId")==null)  bean.setStatusId(0);
        if(anchor.equals(_FORYOU__PREPARE)){ 
            FBeans beans = new FBeans();
            FForYou beanF =new FForYou();
            beanF.setBoss(me.getFullName());
            beanF.setUserIdFrom((int)me.getId());            
            beans = new BDocRules().getAllWorkflow();
            request.setAttribute("BWorkflows",beans);
            if (beans!=null && beans.size()>0) beanF.setWorkflowId(((FWorkflow)beans.get(0)).getWorkflowId());
            request.setAttribute("forYou",beanF);
            request.setAttribute("BUsers",bo.getAllDiffUserId(beanF));            
            target = _FORYOU__PREPARE;
       
        }else  if(anchor.equals("_FORYOU_EDIT")){
        
            FForYou beanF = new FForYou();
            bo.updateActive(beanF);
            beanF.setStatus(-1);
            request.setAttribute("BForYous",bo.getByUserIdFrom(beanF,(int)me.getId()));
            target=anchor;
            
        }else  if(anchor.equals("_FORYOU_LIST")){ 
            
            FForYou beanF = new FForYou();
            bo.updateActive(beanF); 
            request.setAttribute("BForYous",bo.getByUserIdTo(beanF,(int)me.getId()));
            target=anchor;
            
        }
        else if(anchor.equals("_CATEGORY")){ 
        
            BSecure boSecure = new BSecure();
            FBeans beansSecure= boSecure.getAllSecure();
            request.setAttribute("BSecure",beansSecure);
            target = "_CATEGORY";
            
        }else if(anchor.equals("_CABIN")){
             FCabin beanT=new FCabin();
             FCabinType beanType=new FCabinType();
             BCabinType boType=new BCabinType();
             beanT.setType(bean.getType());
             beanT.setCabinType_id(bean.getCabinType_id());
             beanType.setId(bean.getCabinType_id());
             if(beanType.getId()>0){
                 beanType=boType.getCabinTypeById(beanType);
                 beanT.setBack(beanType.getParentID());
             }
             beanT.setMeId(me.getId());
             if(bean.getType()==IKeyCabin.CABIN_SHARE){
                     FBeans beanUserShare =new FBeans();
                     beanUserShare=new BCabin().getUserShare((int)me.getId());
                     request.setAttribute("BUserShares",beanUserShare);
                     FCabin beanTemp =new FCabin();
                     if(beanUserShare!=null && beanUserShare.size()>0){
                        beanTemp=(FCabin)beanUserShare.get(0);
                        beanT.setUserId(beanTemp.getUserId());
                     }
                 request.setAttribute("BCabinTypes0",new BCabinType().getAll(me.getId(),0,(int)me.getDepartmentID()));
                 request.setAttribute("BCabinTypes1",new BCabinType().getAll(me.getId(),1,(int)me.getDepartmentID()));
                 request.setAttribute("BCabinTypes3",new BCabinType().getAll(me.getId(),3,(int)me.getDepartmentID()));
                 request.setAttribute("BCabin",new BCabin().getAllByType(beanT));
             }

             if(bean.getType()!=IKeyCabin.CABIN_SHARE){
                 FBeans beans=new FBeans();
                 beans=new BCabinType().getAll(me.getId(),beanT.getType(),(int)me.getDepartmentID());
                 request.setAttribute("BCabinTypes0",new BCabinType().getAll(me.getId(),0,(int)me.getDepartmentID()));
                 request.setAttribute("BCabinTypes1",new BCabinType().getAll(me.getId(),1,(int)me.getDepartmentID()));
                 request.setAttribute("BCabinTypes3",new BCabinType().getAll(me.getId(),3,(int)me.getDepartmentID()));
                 request.setAttribute("BCabin",new BCabin().getAllCabinUnionCabinType(beanT));
             }
             if(bean.getType()==IKeyCabin.CABIN_DEPARTMENT){
                    beanT.setDepartmentID((int)me.getDepartmentID());
                     if(me.isRole(IRoles.rCABIN_MUTI)){
                         request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                     }
                 request.setAttribute("BCabin",new BCabin().getAllCabinUnionCabinType(beanT));
             }
             FBeans beans = new FBeans();          
             beans = new BCreate().getAllDepartment();
             if(beans!=null){
                FDepartment beanDep = (FDepartment)beans.get(0);
                 beanT.setDepartmentID(beanDep.getId());
             }
             request.setAttribute("BDepartments",beans);    
             beanC.setDepartmentID((int)me.getDepartmentID());
             request.setAttribute("BUsersDep",new BCreate().getUserByDepartmentId(beanC,beanC.getDepartmentID()));   
             beanT.setType(bean.getType());
             beanT.setRootPath("");
             if(bean.getCabinType_id()>0){
                 FCabinType beanCa=new FCabinType();
                 BCabinType beanCabinType=new BCabinType();
                 beanCa.setParentID(bean.getCabinType_id());
                 List params =new ArrayList();
                for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=beanCabinType.getCabinTypeById(beanCa);
                    params.add(beanCa.getName());
                }
                 for (int i=params.size()-1;i>-1;i--) {
                    beanT.setRootPath(beanT.getRootPath()+"/"+ params.get(i));
                 }
             }
             beanT.setDepartmentID((int)me.getDepartmentID());
             FCabinType beanCabinType=new FCabinType();
             beanCabinType.setType(bean.getType());
             beanCabinType.setParentID(bean.getCabinType_id());
             request.setAttribute("cabinType",beanCabinType);
             beanT.setCabinType_id(bean.getCabinType_id());
             request.setAttribute("cabin",beanT);
             beanC.setEmpsRev(null);
             beanC.setFileUpload(null);
             target=anchor;
            
         }else if(anchor.equals("_PREPARED_TEMPLATE")){ 
            
            FTemplate beanTem =new FTemplate();
            beanTem.setUserId((int)me.getId());
            beanTem.setCreator(bean.me.getFullName());
            beanTem.setDepartmentId((int)me.getDepartmentID());
            request.setAttribute("template",beanTem);
            request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
            
            //FCreate beanT=new FCreate();
            FBeans beans = new FBeans();          
            beans = new BCreate().getAllDepartment();
            request.setAttribute("BDepartments",beans);    
            beanC.setEmpsRev(null);
            beanC.setFileUpload(null);
            beanC.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",new BCreate().getUserByDepartmentId(beanC,beanC.getDepartmentID()));   
            target=anchor;
            
        }else if(anchor.equals("_TEMPLATE_ALL")){ 
        
            FTemplate beanTemp =new FTemplate();
            beanTemp.setDepartmentId((int)bean.me.getDepartmentID());
            beanTemp.setNameDep(bean.me.getDepartmentName());
            request.setAttribute("BTemplate",new BTemplate().getAll(beanTemp,(int)me.getId(),bean.getType()));
            
            beanTemp.setUserId((int)me.getId());
            beanTemp.setType(bean.getType());
            
            request.setAttribute("template",beanTemp);
            request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
            
            FTemplatesRule beanC1 =new FTemplatesRule();
            beanC1.setUserId((int)me.getId());
            request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
            target="_TEMPLATE";
            
        }else if(anchor.equals("_TEMPLATE_RECYCLE")){
            
            FTemplate beanTemp =new FTemplate();
            request.setAttribute("BTemplate",new BTemplate().getAll(beanTemp,(int)me.getId(),0));
            beanTemp.setType(0);
            request.setAttribute("template",beanTemp);
            request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
            
            FTemplatesRule beanC1 =new FTemplatesRule();
            beanC1.setUserId((int)me.getId());
                request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
            target="_TEMPLATE";
            
        }else if(anchor.equals("_REPORT_ALL")){ 
            
            FReport beanReport=new FReport();
            BReport boRp=new BReport();
            beanReport.setMeId((int)me.getId());
            beanReport.setType(bean.getType());
            FBeans beans =new FBeans();
            beans=boRp.getUserByRules(me.getId());
            request.setAttribute("BUsers",beans);
            if(beans!=null && beans.size()>0){
                beanReport.setUserId(((FUser)beans.get(0)).getId()); 
            }
            
            request.setAttribute("BReport",boRp.getAllByType(beanReport));
            request.setAttribute("reports",beanReport);
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
            request.setAttribute("BListUsers",new BReportsRule().getAllBossByMeId(me.getId()));  
            target="_REPORT";
           
        }else if(anchor.equals("_DOSSIERS_LIST")){ 
            
            request.setAttribute("BDossiers",new BDossiers().getAll((int)me.getId()));
            target="_DOSSIERS_LIST";
            
        }else if(anchor.equals("_DOCS_SEND_DT_LIST")){
            
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            FDocAssign beanrule=new FDocAssign();
            FDocAssign BCheckRule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(2,me.getId(),0,beanrule.getStatusIds()));
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(2,me.getId(),0,checkWaitSend,BCheckRule.getStatusIds()!=null?BCheckRule.getStatusIds():"1110"));
            if (BCheckRule.getCheckNotIncharge()>0 || BCheckRule.getCheckUnReaded()>0){
            request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
            }
            FDocssend BDocssend=new FDocssend();
            if(bean.getStatusId()==0){
                BDocssend.setStatusId(BCheckRule.getCheckUnReaded()==0?-3:0);
            }else{
                BDocssend.setStatusId(bean.getStatusId());
            }
            
            BDocssend.setType(2);
            request.setAttribute("docssend",BDocssend);
            request.setAttribute("BSearch",new BDocssend().getAlldocssend(BDocssend,(int)me.getId(),BDocssend.getType(),checkWaitSend,BCheckRule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            checkRules(request,AppConfigs.DOCSSEND_WORKFLOWID,BCheckRule);
            target="_DOCS_SEND_LIST";
            
        }else if(anchor.equals("_DOCS_REPORTS")){
            
            FDocReport BDocReport = new FDocReport();
            BDocReport.setFromDate(bean.dateToString(bean.getCurrentSqlDate()));
            BDocReport.setToDate(bean.dateToString(bean.getCurrentSqlDate()));
            if (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV).toString())==1 ||  (Integer.parseInt(request.getSession().getAttribute(com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV).toString())!=1 && request.getSession().getAttribute("01.01")!=null)){ 
                BDocReport.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
            }else{
                BDocReport.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            }
            
            
            BDocReport.setUserId((int)me.getId());
            BDocReport.setCheckObserver(BDocReport.getWorkflowId()==1?Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_OBSERVER_DOCSRECV).toString()):Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_OBSERVER_DOCSSEND).toString()));
            BDocReport.setDepId((int)me.getDepartmentID());
            BDocReport.setTotalReport(0);
            BDocReport boDocReport=new BDocReport();
            
            List params =new ArrayList();           
            params.add(bean.getCurrentSqlDate());
            params.add(bean.addDays(bean.getCurrentSqlDate(),1));
            params.add(me.getId());
            
            request.setAttribute("BTotalsStatus",new BDocReport().getTotalDocByStatus(bean.getWorkflowId(),params));
            request.setAttribute("BTotalsTransfer",new BDocReport().getTotalDocByTransfer(bean.getWorkflowId(),params));
            request.setAttribute("BTotalsDocType",new BDocReport().getTotalDocByDocType(bean.getWorkflowId(),params));
            request.setAttribute("BTotalsBranch",new BDocReport().getTotalDocByBranch(bean.getWorkflowId(),params));
            
            request.setAttribute("docreport",BDocReport);
            request.setAttribute("BDocs",boDocReport.getAllDoc(BDocReport,0,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));            
            target=anchor;
            
            if(BDocReport.getCheckObserver()>0){
                FBeans beans = new FBeans();
                beans =new BDepartments().getAllRecord(0);              
                request.setAttribute("BDepartments",beans);
                request.setAttribute("BUsers",boDocReport.getUserByDepartmentID(BDocReport.getDepId(),0));
               // params.add(BDocReport.getDepId());
            }
           
        }else if(anchor.equals("_DOCS_SEND_LIST")){
            request.setAttribute("BClassify",new BClassify().getAll());
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            if (bean.getViews()==1){
                new BDocssend().updateView(0,me.getId());               
            }
            
            FDocAssign BCheckRule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            if(BCheckRule.getStatusIds()!=null){
            request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BCheckRule.getStatusIds()));
            }
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BCheckRule.getStatusIds()!=null?BCheckRule.getStatusIds():"1110"));
            if (BCheckRule.getCheckNotIncharge()>0 || BCheckRule.getCheckUnReaded()>0){
            request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
            }
            
            FDocssend BDocssend=new FDocssend();
            BDocssend.setStatusId(bean.getStatusId());
            BDocssend.setType(1);
            BDocssend.setViews(bean.getViews());
            BDocssend.setReaded(bean.getReaded());
            request.setAttribute("docssend",BDocssend);
            request.setAttribute("BSearch",new BDocssend().getAlldocssend(BDocssend,(int)me.getId(),checkWaitSend,BDocssend.getType(),BCheckRule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()))); 
            request.getSession().setAttribute("secureId",null);
            checkRules(request,AppConfigs.DOCSSEND_WORKFLOWID,BCheckRule);
            target="_DOCS_SEND_LIST";
            
        }else if(anchor.equals("_DOCS_RECV_LIST")){
            
            request.setAttribute("BClassify",new BClassify().getAll());
            FDocAssign BCheckRule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            FDocsrecv BDocsrecv =new FDocsrecv();
            BDocsrecv.setDossierId(0);
            BDocsrecv.setStatusId(bean.getStatusId());
            BDocsrecv.setDossierId(bean.getDossierId());
            BDocsrecv.setViews(bean.getViews());
               
            
            int checkWait = 0 ;
            if (request.getSession().getAttribute("checkWait")!=null){
               checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
            }
            
            request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecv(BDocsrecv,(int)me.getId(),BCheckRule.getStatusIds(),checkWait));
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID(bean.me.getId()));
            if (bean.getViews()==1){
                new BDocsrecv().updateView(0,me.getId());               
            }
            
            BCheckRule.setMeId(bean.me.getId());            
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BCheckRule,0));
            
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BCheckRule,0,checkWait==1?1:0,(BCheckRule.getStatusIds()!=null && !BCheckRule.getStatusIds().equals(""))?BCheckRule.getStatusIds():"1110"));
            if (BCheckRule.getCheckNotIncharge()>0 || BCheckRule.getCheckUnReaded()>0){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
            }
            
            request.getSession().setAttribute("secureId",null);
            checkRules(request,AppConfigs.DOCSRECV_WORKFLOWID,BCheckRule);  
            bean.setViews(bean.getViews()!=0?-10:bean.getViews());
            request.setAttribute("docsrecv",BDocsrecv);  
            target="_DOCS_RECV_LIST";
            
        }else if(anchor.equals("_DOCS_SEARCH")){ 
        
            
            request.setAttribute("BFroms",new BFrom().getAllFrom(new FFrom()));
            request.setAttribute("BForms",new BForm().getAllForm());
            request.setAttribute("BDeps",new BDepartments().getAllRecord(0));
            request.setAttribute("BSecures",new BSecure().getAllSecure());
            request.setAttribute("BExpresss",new BExpress().getAllExpress());
            request.setAttribute("BVias",new BVia().getAllVia());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BDocTypes",new BDocType().getAllDocType());
            request.setAttribute("BStatus",new BStatus().getAllStatus());
            request.setAttribute("BClassify",new BClassify().getAll());
            FDocsrecv beantemp =new FDocsrecv();
            request.setAttribute("docsrecv",beantemp);
            target="_DOCS_SEARCH";
            
        }else if(anchor.equals("_CALENDA_PRIVATE")){ //LICH CA NHAN
        
            BCalendar BOMyagenda=new BCalendar();
            DCalendarLib DCal=new DCalendarLib();
            Date dayStart=DCal.getDayStart(bean.dateToString(bean.getCurrentSqlDate()));
            Date dayEnd=DCal.getDayEnd(bean.dateToString(bean.getCurrentSqlDate()));
            FAgenda beanTemp =new FAgenda();
            request.setAttribute("BEvents",BOMyagenda.getEventsByMonth((int)me.getId(),dayStart,bean.getDays(dayStart,dayEnd)+1,bean.getType(),me.getDepartmentID()));
            beanTemp.setUserId((int)me.getId());
            beanTemp.setType(bean.getType());
            beanTemp.setSelectDate(bean.dateToString(bean.getCurrentSqlDate()));
            beanTemp.setDayId(DCal.convertDateToID(bean.getCurrentSqlDate()));
            request.setAttribute("agenda",beanTemp);
            target="_CALENDA_PRIVATE";
        
        }else if(anchor.equals("_CALENDA_MEETING")){ //LICH HOP
        
            BCalendarMeeting BOMyagenda=new BCalendarMeeting();
            DCalendarLib DCal=new DCalendarLib();
            Date dayStart=DCal.getDayStart(bean.dateToString(bean.getCurrentSqlDate()));
            Date dayEnd=DCal.getDayEnd(bean.dateToString(bean.getCurrentSqlDate()));
            FAgendaMeeting beanTemp =new FAgendaMeeting();
            request.setAttribute("BEvents",BOMyagenda.getEventsByMonth((int)me.getId(),dayStart,bean.getDays(dayStart,dayEnd)+1));
            beanTemp.setUserId((int)me.getId());
            beanTemp.setSelectDate(bean.dateToString(bean.getCurrentSqlDate()));
            beanTemp.setDayId(DCal.convertDateToID(bean.getCurrentSqlDate()));
            request.setAttribute("agendaMeeting",beanTemp);
            target=anchor;   
        }else if(anchor.equals("_CALENDAR_XE")){ //LICH HOP
        
            BCalendarXe BOMyagenda=new BCalendarXe();
            DCalendarLib DCal=new DCalendarLib();
            Date dayStart=DCal.getDayStart(bean.dateToString(bean.getCurrentSqlDate()));
            Date dayEnd=DCal.getDayEnd(bean.dateToString(bean.getCurrentSqlDate()));
            FCalendarXe beanTemp =new FCalendarXe();
            request.setAttribute("BEvents",BOMyagenda.getEventsByMonth((int)me.getId(),dayStart,bean.getDays(dayStart,dayEnd)+1));
            beanTemp.setUserId((int)me.getId());
            beanTemp.setSelectDate(bean.dateToString(bean.getCurrentSqlDate()));
            beanTemp.setDayId(DCal.convertDateToID(bean.getCurrentSqlDate()));
            request.setAttribute("agendaXe",beanTemp);
            target=anchor;   
            
        }else if(anchor.equals(_HOME)){ 
        
            request.setAttribute("BUsers",bo.getAllUsers());
            FForYou beanF =new FForYou();
            beanF.setBoss(bean.me.getFullName());
            beanF.setUserIdFrom((int)me.getId());
            request.setAttribute("foryou",beanF);
            
        }else   if(anchor.equals("_CATEGORY"))  {
        
            BSecure boSecure = new BSecure();
            FBeans beansSecure= boSecure.getAllSecure();
            request.setAttribute("BSecure",beansSecure);
            target = "_CATEGORY";
            
        }else if(anchor.equals("_CATEGORY_VIA")){

            BVia boVia = new BVia();
            FBeans beansVia= boVia.getAllVia();
            request.setAttribute("BVia",beansVia);
            target=anchor;
        }else if(anchor.equals("_CATEGORY_BRANCH")){

            BBranch boVia = new BBranch();
            FBeans beansVia= boVia.getAllVia();
            request.setAttribute("BBranch",beansVia);
            target=anchor;
        }else if(anchor.equals("_CATEGORY_CLASSIFY")){

            BClassify boVia = new BClassify();
            FBeans beansVia= boVia.getAll();
            request.setAttribute("BClassify",beansVia);
            target=anchor;
        }else if(anchor.equals("_CATEGORY_THEPEN")){

            BThePen boThePen = new BThePen();
            FBeans beansThePen= boThePen.getAllThePen();
            request.setAttribute("BThePen",beansThePen);
            target=anchor;
        }else if(anchor.equals("_DATABASE_SERVER")){
        
            BDataBaseServer boDataBaseServer = new BDataBaseServer();
            FBeans beanDataBaseServer= boDataBaseServer.getAll();
            request.setAttribute("BDataBaseServer",beanDataBaseServer);
            target=anchor;
        }else if (anchor.equals("_CABIN_PUBLIC")){        
            
                FBeans beans =new FBeans();
                beans=new BCabinType().getAll(me.getId(),IKeyCabin.CABIN_PUBLIC,(int)me.getId());
                request.setAttribute("BCabinTypes",beans);
                FCabin beanT=new FCabin();
                beanT.setCabinType_id((beans!=null && beans.size()>0)?((FCabinType)beans.get(0)).getId():0);
                beanT.setType(IKeyCabin.CABIN_PUBLIC);
                request.setAttribute("BCabins",new BCabin().getAllByType(beanT));
                request.setAttribute("cabinList",beanT);
               target=anchor;
            
        }
        
        else if(anchor.equals("_CATEGORY_SERVEY")){     
            request.setAttribute("BServey",new BServey().getAll());
            target=anchor;
        }else if(anchor.equals("_CATEGORY_SERVEYQUESTIONS")){             
            FBeans beanSQ=new BServey().getAll();
            request.setAttribute("BServey",beanSQ);
            if(beanSQ!=null && beanSQ.size()>0){
            FServey beanServey=(FServey)beanSQ.get(0);
                request.setAttribute("BServeyQuestions",new BServeyQuestions().getByServeyId(beanServey.getServeyId()));
            }
            target=anchor;
        }else if(anchor.equals("_MAIL_ACCOUNT")){
            BRegister boregister = new BRegister();
            FBeans beansMailAccount= boregister.getAll();
            request.setAttribute("BMailAccount",beansMailAccount);
            request.setAttribute("BUsers",new BProblem().getAllUser());             
            //request.setAttribute("BServerTypes",boregister.getAllServerType());
            target=anchor;
        }else if(anchor.equals("_CATEGORY_TRANSFER")){        
            BTransfer boTransfer = new BTransfer();
            FBeans beansTransfer= boTransfer.getAllTransfer();
            request.setAttribute("BTransfer",beansTransfer);
            target=anchor;
            
        }else if(anchor.equals("_CATEGORY_DOC_TYPE")){
        
            BDocType boDocType = new BDocType();
            FBeans beansDocType= boDocType.getAllDocType();
            request.setAttribute("BDocType",beansDocType);
            target=anchor;
            
        }else if(anchor.equals("_UNITS_LIST")){
        
            BFrom boFrom =new BFrom();
            FBeans beansFrom= boFrom.getAllFrom(new FFrom());
            request.setAttribute("BFroms",beansFrom);
            target=anchor;
            
        }else if(anchor.equals("_CATEGORY_FORM")){
        
            BForm boForm =new BForm();
            FBeans beansForm= boForm.getAllForm();
            request.setAttribute("BForm",beansForm);
            target=anchor;
        }else if(anchor.equals("_PREPARED_CABIN")){
        
            FBeans beans = new FBeans();          
            beans =new BDepartments().getAllRecord(0);
            request.setAttribute("BDepartments",beans);    
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(bean.getDepartmentID(),0));
            FCabin beanTemp=new FCabin();
            if(beanTemp.getToPertion()>0){
                beanTemp.setEmpsRev(new FBeans());
                beanTemp.getEmpsRev().add(bean);
            }else{
                beanTemp.setEmpsRev(null);
            }
            request.setAttribute("cabin",beanTemp);
            request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),1,(int)me.getDepartmentID()));
            //target=bean.getType()==IKeyCabin.CABIN_PRIVATE?_PREPARE:_VIEWDEPARTMENTS;
            target=anchor;
        
        }else if(anchor.equals("_CATEGORY_STATUS")){
        
            BStatus boStatus =new BStatus();
            FBeans beansStatus= boStatus.getAllStatus();
            request.setAttribute("BStatus",beansStatus);
            target=anchor;
            
        }else if(anchor.equals("_CATEGORY_TYPE_CABIN")){
    
            FCabinType beanT=new FCabinType();
            request.setAttribute("BCabinTypes",new BCabinType().getAll(me.getId(),IKeyCabin.CABIN_PUBLIC,0));
            request.setAttribute("cabinType",beanT);
            target=anchor;
            
        }else if(anchor.equals("_CATEGORY_EXPRESS")){
        
            BExpress boExpress = new BExpress();
            FBeans beansExpress= boExpress.getAllExpress();
            request.setAttribute("BExpress",beansExpress);
            target= "_CATEGORY_EXPRESS";
            
        }else if(anchor.equals("_CATEGORY_REPORT_TYPE")){
        
            BReportType BReportType = new BReportType();
            FBeans beanReport= BReportType.getAllReportType();
            request.setAttribute("BReportType",beanReport);
            target= "_CATEGORY_REPORT_TYPE";
            
        }else if(anchor.equals("_CATEGORY_TEMPLATE_TYPE")){

            BTemplateType boExpress = new BTemplateType();
            FBeans beansExpress= boExpress.getAllTemplateType();
            request.setAttribute("BTemplateType",beansExpress);
            target= "_CATEGORY_TEMPLATE_TYPE";

        }else if (anchor.equals("_TASKS_ASSIGN")){        
            
            BProblem boC = new BProblem(); 
            bean.setComplateSearch(2);
            request.setAttribute("BProblems",boC.getAllRecord(bean,bean.me.getId()));
            FCategories beanCa = new FCategories();
            beanCa.setCreator((int)me.getId());
            request.setAttribute("BCategories",boC.getAllCategories(beanCa)); 
            request.setAttribute("BCategories",new BCategories().getAllRecord(beanCa,(int)me.getId(),0)); 
            
            bean.setAssignCheck(boC.getAssignCheck(bean.me.getId()));
            request.getSession().removeAttribute("secureId");
            request.setAttribute("problem",bean);
            bean.setAmountDeadline(0);
            
            FBeans beans =new BProblem().getAllDepartment(me.getId());
            request.setAttribute("BDepartments",beans);           
            if(beans!=null && beans.size()>0){
                bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
            }
            bean.setCreator((int)me.getId());
            request.setAttribute("BUsersDep",new BProblem().getUserByDepartmentId(bean));
            target ="_TASKS_ASSIGN";
            
        }else if (anchor.equals("_MESSAGES")){    
        
            request.setAttribute("messsagesList",beanC);             
            beanC.setType(bean.getType());
            request.setAttribute("BMessages",new BCreate().getAllMessRecByUserId(beanC,(int)me.getId())); 
            request.setAttribute("createMessage",beanC); 
            FBeans beans = new FBeans();          
            beans = new BCreate().getAllDepartment();
            request.setAttribute("BDepartments",beans);    
            beanC.setEmpsRev(null);
            beanC.setFileUpload(null);
            beanC.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",new BCreate().getUserByDepartmentId(beanC,beanC.getDepartmentID()));   
            request.setAttribute("BAmount",new BCreate().getAllAmount(beanC,(int)me.getId()));   
            request.getSession().setAttribute("accountEmail",new BRegister().getTopActive(me.getId()));    
            target ="_MESSAGES";
            
        }else if (anchor.equals("_TASKS_ASSIGN_CATEGORY")){ 
        
            FCategories beanCa = new FCategories();                   
            request.setAttribute("BCategories",(new BCategories()).getAllRecord(beanCa,(int)me.getId(),1));                        
            request.setAttribute("problem",bean);
            
            FBeans beans =new BProblem().getAllDepartment(me.getId());
            request.setAttribute("BDepartments",beans);           
            if(beans!=null && beans.size()>0){
                bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
            }
            bean.setCreator((int)me.getId());
            request.setAttribute("BUsersDep",new BProblem().getUserByDepartmentId(bean));
            
            target  ="_TASKS_ASSIGN_CATEGORY";    
            
        }else if (anchor.equals(_DIRECTIONARY_PREPARE)){ 
        
            FDirectory beanT=new FDirectory();
            beanT.setDepartmentId((int)me.getDepartmentID());
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0)); 
            request.setAttribute("BDirectories",new BDirectory().getAllSearch(beanT)); 
            request.setAttribute("directory",beanT);
            target = _DIRECTIONARY_PREPARE;            
            }else if (anchor.equals("_HELP")){ 
            target=anchor;
        }else if (anchor.equals(_PREPARE_BROADCAST)){
        
            FBroadcast beanTemp = new FBroadcast();
            beanTemp.setPageIndex(1);
            beanTemp.setUser_id((int)me.getId());
            request.setAttribute("broadcast",beanTemp);
            request.setAttribute("BBroadcasts",new BBroadcast().getAllBroadcast(beanTemp));
            target = _PREPARE_BROADCAST;
            
        }else if (anchor.equals("_USERS_ONLINE")){
            target = "_USERS_ONLINE"; 
        
        }else if (anchor.equals(_LIST_MYCONTACT)){       
            
            FMycontact beanM = new FMycontact();
            beanM.setUserId(bean.me.getId());
            FBeans beans = new FBeans();
            beans = new BPgroups().getAllRecord(me.getId());
            request.setAttribute("BPgroups",beans);
            beanM.setPgroupId(beans!=null && beans.size()>0?((FPgroup)beans.get(0)).getId():0);
            request.setAttribute("BMycontacts",new BMycontact().getAllMycontactByPgroup(beanM));            
            target = _LIST_MYCONTACT;
            
        }else if (anchor.equals("_SHOW_MYCONTACT")){
            request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(me.getId()));    
            target = "_SHOW_MYCONTACT";
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
   
    
    
    public FDocAssign checkRules(HttpServletRequest request,int workflow,FDocAssign beantemp) throws  EException{
        beantemp.setMeId(me.getId());
        beantemp.setWorkflowId(workflow);
        if (beantemp.getCheckDirect()==1 && beantemp.getCheckSelectDept()==1){
            request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment((int)me.getId()));  
        }
        request.setAttribute("docAssign",beantemp);  
        return beantemp;
    }
    
}
