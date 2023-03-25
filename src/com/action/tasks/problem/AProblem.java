package com.action.tasks.problem;


import com.action.ACore;

import com.bo.tasks.categories.BCategories;
import com.bo.tasks.problem.BProblem;
import com.bo.tasks.report.BReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.tasks.categories.FCategories;
import com.form.tasks.problem.FProblem;
import com.form.tasks.report.FReport;

import com.lib.AppConfigs;

import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AProblem extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FProblem bean = (FProblem)form; 
        if(bean.getValue("complateSearch")==null) bean.setComplateSearch(2);
        if(bean.getValue("viewStop")==null) bean.setViewStop(0);
        bean.setCreator((int)me.getId());
        FCategories beanC = new FCategories();
        beanC.setCreator((int)me.getId());
        BProblem bo = new BProblem();
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();       
        bean.setAssignCheck(bo.getAssignCheck(bean.me.getId()));
        target = validate(bean,anchor,errors);
        
        if(errors.isEmpty()){     
            if (anchor.equals(_PREPARED_CREATE)){   
                request.setAttribute("change",bean);
                //request.setAttribute("BCategories",bo.getAllCategories(beanC));                           
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);           
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean));
                
                request.setAttribute("BUsers",bo.getAllUser());             
                bean.reset();
                bean.setFromDate(bean.dateToString(bean.getCurrentSqlDate()));
                bean.setToDate(bean.dateToString(bean.getCurrentSqlDate()));                  
                bean.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",bean.getSecureId());
                request.setAttribute("problem",bean);  
                request.getSession().setAttribute("BApp",bean.getApp());   
                target = _PREPARED_CREATE;  
                
            } else if (anchor.equals("_VIEW_DETAIL")){
                
                request.setAttribute("BTaskRoot",bo.getProblemById(bean));
                target=anchor;
                
            } else if (anchor.equals(_VIEW_REPORT)){

                request.setAttribute("problem",bo.getProblemById(bean));
                bean.setCreator((int)me.getId());                
                request.setAttribute("BReportsReview",bo.getReviewByAssign(bean));
                request.setAttribute("BReports",bo.getReportByAssignId(bean));
                target = _VIEW_REPORT;
                
            }else if (anchor.equals("_REVIEW_REPORT")){
                 //Xem y kien cua nguoi giao viec                           
                bean.setCreator((int)me.getId());                
                request.setAttribute("BReportsReview",bo.getReviewByAssign(bean));
                request.setAttribute("BReports",bo.getReportByAssignId(bean));
                //update status for emps asign
                new BReport().updateStatuReview(bean);
                target = "_REVIEW_REPORT";
                
            }else if (anchor.equals(_PREPARED_DELETE)){ 
            
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                beanP.setRoot(bean.getRoot());               
                beanP.setType(bean.getType());
                beanP.setAssignCheck(bean.getAssignCheck());
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                request.setAttribute("BProblem",bo.getAllRecord(bean,bean.me.getId())); 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean)); 
                beanP.setCreator((int)me.getId());
                if (beanP.getType()==0 && bean.getProblemId()!=0){
                    request.setAttribute("userOption",bo.getUserByProblemId(beanP,(int)bean.me.getId())); 
                }else{
                    beanP.setProblem("");
                }
                request.setAttribute("problem",beanP);
                request.setAttribute("BUsers",bo.getAllUser());                 
                target = _PREPARED_CREATE;                   
                
            }else if (anchor.equals(_CREATE) || anchor.equals(_TASK_ASSIGN_CREATE)){    
               
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if (secureId!=null && secureId==bean.getSecureId()){                  
                    String pictureFolder = AppConfigs.TASKS_FILE_PATH;
                    String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder;
                    boolean haveFile = bean.getFileUplaod().getFileSize()>0;                    
                    if(haveFile){
                       String ext = encodeFileName(bean.me.getId());//               
                       bean.deleteFile(picturePath + bean.getFileName());
                       bean.setFileName(new String((bean.getFileUplaod().getFileName().getBytes()),"UTF-8"));
                       bean.setPathFile(ext);
                       bean.upload(bean.getFileUplaod(),picturePath + ext);
                    }else {
                        bean.setFileName(null);
                    }
                    bean.setCreator((int)bean.me.getId());
                    bean.setAccepted(0);
                    if (bo.insert(bean)){
                        errors.add("alert",new ActionError("alert.insert.tasks.successfull"));   
                        bean.setSecureId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getSecureId());
                    }else{
                        errors.add("alert",new ActionError("problem.error.notsussecss"));    
                    }
                   
                }
                bean.setType(0);
                bean.setTitle("");
                bean.setComplateSearch(0);
                //request.setAttribute("BCategories",bo.getAllCategories(beanC));                
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);           
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean)); 
               // request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId())); 
                
                FProblem beantemp =new FProblem();
                beantemp.setFromDate(bean.dateToString(bean.getCurrentSqlDate()));
                beantemp.setToDate(bean.dateToString(bean.getCurrentSqlDate()));                  
                beantemp.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",beantemp.getSecureId());
                request.setAttribute("problem",beantemp); 
                target = _PREPARED_CREATE;   
            
            
            }else if (anchor.equals(_PREPARED_EDIT)){
            
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                beanP.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",beanP.getSecureId());     
                beanP.setAssignCheck(bean.getAssignCheck());
                beanP.setType(bean.getType());
                request.setAttribute("problem",beanP);
                //request.setAttribute("BCategories",bo.getAllCategories(beanC));                
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);           
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean)); 
                request.setAttribute("userOption",bo.getUserByProblemId(bean,(int)bean.me.getId()));
                if ( bean.getType()==1){
                    target = _CREATE_SIGN;              
                }else{
                    target = _PREPARED_CREATE;              
                }
                
            }else if (anchor.equals(_EDIT)){ 
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if (secureId!=null && secureId==bean.getSecureId()){  
                    String pictureFolder = AppConfigs.TASKS_FILE_PATH;
                    String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder;
                    boolean haveFile = bean.getFileUplaod().getFileSize()>0;                
                    if(haveFile){
                        String ext = encodeFileName(bean.me.getId());//               
                        bean.deleteFile(picturePath + bean.getFileName());
                        bean.setFileName(new String((bean.getFileUplaod().getFileName().getBytes()),"UTF-8"));
                        bean.setPathFile(ext);
                        bean.upload(bean.getFileUplaod(),picturePath + ext);                        
                    } 
                    bean.setCreator((int)bean.me.getId());
                    if (bo.update(bean)){
                        errors.add("alert",new ActionError("alert.update.successfull"));   
                        bean.setSecureId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getSecureId());
                    }else{
                        errors.add("alert",new ActionError("problem.error.notsussecss"));   
                    }    
                    
                }
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                beanP.setAssignCheck(bean.getAssignCheck());
                beanP.setSecureId(bean.getSecureId());
                request.setAttribute("problem",beanP);
                //request.setAttribute("BCategories",bo.getAllCategories(beanC));                
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);           
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean));
                request.setAttribute("userOption",bo.getUserByProblemId(bean,(int)bean.me.getId()));
                target = _PREPARED_CREATE;                
                
            }else if (anchor.equals(_SELECT)){ 
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));                        
                target  = _SELECT;                   
                
            }else if (anchor.equals(_DELETE)){     
                if (bo.delete(bean)){
                    errors.add("alert",new ActionError("alert.delete.successfull"));  
                }else{
                    errors.add("alert",new ActionError("problem.error.notsussecss"));  
                }
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));   
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                target  = _PREPARE;                   
                
            }else if (anchor.equals(_SEARCH_PAGE)){  
            
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));  
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                target = _ACTIVE;     
                
            }else if(anchor.equals(_SHOW)){
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean));
                request.setAttribute("problem",new FProblem());
                target = _SHOW;   
            }else if (anchor.equals(_TASK_ROOT)){
            
                request.setAttribute("BTaskRoot",bo.getProblemByIdRoot(bean));
                target = _TASK_ROOT;
                
            }else if (anchor.equals(_TASK_PROBLEM_STOP)){
            
                bo.updateProblemStop(bean);                
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));  
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                target = _TASK_PROBLEM_STOP ;
                
            }else if (anchor.equals(_TASK_ASSIGN_STOP)){
            
                bo.updateAssignStop(bean);
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));  
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                target = _TASK_ASSIGN_STOP;
                
            }else if (anchor.equals(_ACTIVE)){   
            
                bo.updateAccepted(bean);
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                FCategories beanTemp=new FCategories();
                request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId())); 
                request.setAttribute("problem",bean);
                target = _ACTIVE;     
                
                
            }else if (anchor.equals(_TASK_ASSIGN_ACCEPTED)){
                
                bo.updateAccepted(bean);                
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                beanP.setAssignCheck(bean.getAssignCheck());
                request.setAttribute("problem",beanP);            
                request.setAttribute("BAllProblem",bo.getListReportOfWorker(beanP,bean.me.getId()));
                target = _TASK_ASSIGN_ACCEPTED;  
            }
            
            else if (anchor.equals(_PREPARED_SAVE)){       
            
                
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));                             
                request.setAttribute("problem",bean); 
                target = _ACTIVE;          
                
            }else if (anchor.equals(_PREPARE)){
            
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId())); 
                //request.setAttribute("BCategories",bo.getAllCategories(beanC));                          
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                request.setAttribute("problem",bean); 
                target = _PREPARE;
                
            }else if ( anchor.equals(_VIEW)){
            
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                beanP.setAssignCheck(bean.getAssignCheck());
                request.setAttribute("problem",beanP);
                request.setAttribute("userAssigns",bo.getAllReportOfWorker(bean));
                target = _VIEW ;
                
            }else if (anchor.equals(_PUT)){
            
                BReport boR = new BReport() ;
                FReport beanR = new FReport();
                beanR.setAssignId(bean.getAssignId());
                request.setAttribute("BReportsList",boR.getAllRecord(beanR,me.getId()));                 
                request.setAttribute("BReport",bean); 
                target = _PUT;
                
            }else if (anchor.equals(_REPORT)){      
                if(bean.getProblemId()>0){
                    request.setAttribute("BEmpRecv",bo.getAllReportOfWorker(bean));
                }
                FProblem beanP = new FProblem();
                FProblem beanP1 = new FProblem();
                beanP = bo.getAssignById(bean.getAssignId());      
                beanP1 = bo.getProblemById(bean);
                request.setAttribute("BReport",beanP1);
                FReport  beanR = new FReport();  
                beanR.setType(bean.getType());                
                beanR.setAssignId(bean.getAssignId());
                beanR.setProblemName(bean.getTitle());
                beanR.setCreatorName(bo.getUserName(bean.getCreator()));
                beanR.setIncharge(beanP.getIncharge());
                beanR.setProblemId(beanP1.getProblemId());
                beanR.setComplate(beanP.getComplete());
                beanR.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",beanR.getSecureId());
                beanR.setCreator(beanP1.getCreator());
                request.setAttribute("report",beanR); 
                target = _REPORT ;   
                
            }else if (anchor.equals("_CREATE_REPORT")){
                
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if (secureId!=null && secureId==bean.getSecureId()){ 
                    FReport beanReport = new FReport();
                   if (bean.getFileUplaod()!=null){ 
                        String pictureFolder = AppConfigs.TASKS_FILE_PATH;
                        String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder;
                        boolean haveFile = bean.getFileUplaod().getFileSize()>0;
                        if(haveFile){
                            String ext = encodeFileName(bean.me.getId());//               
                            bean.deleteFile(picturePath + bean.getFileName());
                            beanReport.setFileName(new String((bean.getFileUplaod().getFileName().getBytes()),"UTF-8"));
                            bean.setPathFile(ext);
                            bean.upload(bean.getFileUplaod(),picturePath + ext);
                        }
                   }
                     beanReport.setCheckEmp(bean.getCheckEmp());
                     beanReport.setAssignId(bean.getAssignId());
                     beanReport.setReport(bean.getReport());
                     beanReport.setComplate(bean.getComplate());
                     beanReport.setPathFile(bean.getPathFile());
                     beanReport.setProblemId(bean.getProblemId());
                     beanReport.setCreator((int)me.getId());
                     beanReport.setIncharge(bean.getIncharge());
                     if (new BReport().insert(beanReport,(int)bean.me.getId(),1)){   //1 at here is have a new report
                       bean.setSecureId(System.currentTimeMillis());
                       request.getSession().setAttribute("secureId",bean.getSecureId());
                       errors.add("alert",new ActionError("report.insert.report.susscess"));  
                     }
                    request.setAttribute("problem",bean);
                }
                request.setAttribute("BProblems",bo.getAllRecord(bean,bean.me.getId()));
                FCategories beanCa = new FCategories();
                beanCa.setCreator((int)me.getId());
                //request.setAttribute("BCategories",bo.getAllCategories(beanCa)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                bean.setAssignCheck(bo.getAssignCheck(bean.me.getId()));               
                target = "_CREATE_REPORT";
                
            }
            else if (anchor.equals(_GET)){
            
                FProblem beanP = new FProblem();
                beanP = bo.getProblemById(bean);
                String pictureFolder = AppConfigs.TASKS_FILE_PATH;
                String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder; 
                File files =new File(picturePath + beanP.getPathFile());
                if(files.exists()){
                   bean.download(picturePath + beanP.getPathFile(),URLEncoder.encode(beanP.getFileName(),"UTF-8"),null);
                    
                }else{
                   bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
                }
            }   
        }else {
        
                //request.setAttribute("BCategories",bo.getAllCategories(beanC)); 
                 FCategories beanTemp=new FCategories();
                 request.setAttribute("BCategories",new BCategories().getAllRecord(beanTemp,(int)me.getId(),0)); 
                 
                request.setAttribute("BProblem",bo.getAllRecord(bean,bean.me.getId())); 
                FBeans beans = bo.getAllDepartment(me.getId());
                request.setAttribute("BDepartments",beans);           
                if(beans!=null && beans.size()>0){
                    bean.setDepartmentID(((FDepartment)beans.get(0)).getId());               
                }                
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean));              
                request.setAttribute("BUsers",bo.getAllUser());                
                request.setAttribute("userOption",bo.getUserByProblemId(bean,(int)bean.me.getId()));
                bean.setUsersId(null);
                bean.setUsersIdNew(null);
                bean.setIncharge(0);
                request.setAttribute("problem",bean);                  
                target = _PREPARED_CREATE;
            
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    } 
    
    private String validate(FProblem bean,String anchor,ActionErrors errors){
      
       if(anchor.equals(_CREATE) || anchor.equals(_EDIT)){
           if (bean.getTitle().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.problem.short"));                 
            }else if (bean.getFromDate()==null || bean.getToDate()==null || !bean.isDate(bean.getFromDate()) || !bean.isDate(bean.getToDate())){
                errors.add("alert",new ActionError("infor.check.add.new.date.error"));  
            }else if (bean.stringToDate(bean.getFromDate()).after(bean.stringToDate(bean.getToDate()))){
                errors.add("alert",new ActionError("errors.foryou.edit.dateTo.dateFrom"));
            }
            else if (bean.getIncharge()<=0){
                errors.add("alert",new ActionError("errors.problem.short"));  
            }else if (bean.getUsersId()==null){
                errors.add("alert",new ActionError("errors.problem.short"));  
            }
       }       
       return anchor;
    }
}
