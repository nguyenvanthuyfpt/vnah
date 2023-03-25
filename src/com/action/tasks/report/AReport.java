package com.action.tasks.report;


import com.action.ACore;

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


public class AReport extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FReport bean = (FReport)form;   
        bean.setCreator((int)me.getId());
        BReport bo = new BReport();
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();        
            
         if (anchor.equals(_CREATE)){  
         
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
               
               if (bo.insert(bean,(int)bean.me.getId(),1)){   
                   bean.setSecureId(System.currentTimeMillis());
                   request.getSession().setAttribute("secureId",bean.getSecureId());
                   errors.add("alert",new ActionError("report.insert.report.susscess"));               
               }
           }
            FProblem beanP = new FProblem();
            beanP.setType(bean.getType());
            beanP.setComplateSearch(0);
            beanP.setCategoriesId(0); 
            FCategories beanC  = new FCategories();
            beanC.setCreator((int)me.getId());
            request.setAttribute("BCategories",bo.getAllCategories(beanC));                            
            FBeans beans = bo.getAllDepartment(me.getId());
            request.setAttribute("BDepartments",beans);           
            if(beans!=null && beans.size()>0){
                beanP.setDepartmentID(((FDepartment)beans.get(0)).getId());               
            }
            beanP.setCreator((int)me.getId());
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(beanP)); 
            request.setAttribute("BProblems",new BProblem().getAllRecord(beanP,bean.me.getId())); 
            beanP.setAssignCheck(new BProblem().getAssignCheck(bean.me.getId()));
            request.setAttribute("problem",beanP); 
            target = _CREATE ;   
            
        }else if (anchor.equals(_SAVE)){
        
            String pictureFolder = AppConfigs.TASKS_FILE_PATH;
            String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder;             
            FReport beantem =new FReport();           
            beantem = bo.getReportId(bean);
            File files =new File(picturePath + beantem.getPathFile());
            if(files.exists()){
               bean.download(picturePath + beantem.getPathFile(),URLEncoder.encode(beantem.getFileName(),"UTF-8"),null);
                
            }else{
               bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
          
        }else if (anchor.equals(_SEARCH_PAGE)){
        
            request.setAttribute("BReports",bo.getAllRecord(bean,me.getId()));  
            request.setAttribute("report",bean); 
            request.setAttribute("BReport",bean); 
            target = _SEARCH_PAGE ;   
            
        }else if (anchor.equals(_REPORT)){
        
            FProblem beanP = new FProblem();           
            BProblem boP = new BProblem();
            beanP = boP.getAssignById(bean.getAssignId());               
            beanP.setProblemId(bean.getProblemId());
            request.setAttribute("BReport",boP.getProblemById(beanP));
            BReport boR = new BReport();
            FReport  beanR = new FReport();  
            beanR.setType(bean.getType());                
            beanR.setAssignId(bean.getAssignId());
            beanR.setProblemName(beanP.getTitle());
            beanR.setCreatorName(boP.getUserName(beanP.getCreator()));
            beanR.setIncharge(beanP.getIncharge());
            beanR.setProblemId(beanP.getProblemId());
            beanR.setComplate(beanP.getComplete());
            request.setAttribute("report",beanR); 
            request.setAttribute("BReports",boR.getAllRecord(beanR,me.getId())); 
            target = _REPORT;
            
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    
    private String validate(FReport bean,String anchor,ActionErrors errors){
       if(anchor.equals(_CREATE)){
            if (bean.getReport().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("report.insert.report.isBrank"));  
            }
       }       
       return anchor;
    }
    
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    } 
    
  
}
