package com.action.report;


import com.action.ACore;

import com.bo.admin.reports.rules.BReportsRule;
import com.bo.admin.users.BUsers;
import com.bo.report.BReport;
import com.bo.report.reportType.BReportType;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.report.FReport;

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


public class AReport extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_SUCCESS;
        FReport bean = (FReport)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BReport bo = new BReport();
        bean.setMeId((int)me.getId());
        target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
            if(anchor.equals(_SEARCH)){
                bean.setTimeCreateForm("");
                bean.setTimeCreateTo("");
                
                request.setAttribute("BReport",bo.getAllByType(bean));
                request.setAttribute("reports",bean);
                request.setAttribute("BReportTypes",new BReportType().getAllReportType());
                request.setAttribute("BUsers",bo.getUserByRules(me.getId()));
                target=_SUCCESS;
            }else if(anchor.equals(_CREATE)  ||  anchor.equals(_EDIT)){
                bean.setCreator(me.getFullName());
                request.setAttribute("reports",bean);
            }
            
        }else if(anchor.equals(_EDIT)){
            request.setAttribute("reports",bean);
            FReport beantemp =bo.getRecordByID(bean);
            String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs(); 
            String filename="";
            boolean haveFile = bean.getUpFile().getFileSize()>0;
                if(haveFile){
                  filename=encodeFileName(bean.me.getId());
                  bean.upload(bean.getUpFile(),dirs + filename);
                  bean.setId(beantemp.getId());
                  bean.setFileStore(filename);
                  bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.report.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("errors.report.insert.exitsName"));   
                    }
                }else{
                    bean.setFileStore(beantemp.getFileStore());
                    bean.setRealName(beantemp.getRealName());
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.report.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("errors.report.insert.exitsName"));   
                    }
                }
            
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
            request.setAttribute("reports",bo.getRecordByID(bean));
            target=anchor;
        }else if(anchor.equals(_DOWNLOAD)){
        
        
                FReport beanTemp = new FReport();
                beanTemp=bo.getRecordByID(bean);
                BUsers boUsers =new BUsers();
                FUser beanUsers =new FUser();
                beanUsers.setId(beanTemp.getUserId());
                beanUsers=boUsers.getRecordByID(beanUsers);
                String filePath =AppConfigs.APP_SYSTEM_PATH+AppConfigs.REPORT_FILE_PATH+AppConfigs.SYSTEM_FILE_SCHIP+beanUsers.getUsername()+AppConfigs.SYSTEM_FILE_SCHIP;
                File files =new File(filePath+beanTemp.getFileStore());
                if(files.exists() && !beanTemp.getFileStore().equals("")){
                    //////.println(filePath+beanTemp.getFileStore());
                    bean.download(filePath+beanTemp.getFileStore(),URLEncoder.encode(beanTemp.getRealName(),"UTF-8"),null);
                }else{
                    bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
                }

            
        }else if(anchor.equals(_PREPARED_EDIT)){
        FReport beantemp=new FReport();
            beantemp=bo.getRecordByID(bean);
            beantemp.setId(bean.getId());
            beantemp.setType(bean.getType());
            request.setAttribute("reports",beantemp);
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
            target =anchor;
            
        }else if(anchor.equals(_PREPARED_CREATE)){
        
            if(bean.getToPertion()>0){
                bean.setEmpsRev(new FBeans());
                bean.getEmpsRev().add(bean);
            }else{
                bean.setEmpsRev(null);
            }
            request.setAttribute("reports",bean);
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
        
            target=anchor;
        }else if(anchor.equals(_CREATE)){
            String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs();                        
            String filename="";
            boolean haveFile = bean.getUpFile().getFileSize()>0;
            if(haveFile){
                filename=encodeFileName(bean.me.getId());
                bean.upload(bean.getUpFile(),dirs + filename);
                bean.setFileStore(filename);
                bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
            }bean.setUserId((int)me.getId());
                if(bo.insert(bean)){
                bean.reset();
                    errors.add("alert",new ActionError("alert.report.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.report.insert.exitsName"));   
                }
                
            request.setAttribute("reports",bean);
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
            target=anchor;
        }else if(anchor.equals("_RESTORE")){
            if(bo.restore(bean.getIds(),1)){
                errors.add("alert",new ActionError("alert.template.restore.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.report.delete.fail"));   
            }
            request.setAttribute("BReport",new BReport().getAllByType(bean));
            request.setAttribute("BReportTypes",new BReportType().getAllReportType());
            request.setAttribute("BUsers",bo.getUserByRules(me.getId()));
            request.setAttribute("reports",bean);
            target=_SUCCESS;
//        }else if(anchor.equals("_EMPTY")){
//            String filePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
//            FReport beantemp = new FReport();
//            beantemp=bo.getRecordByID(bean);
//            bean.deleteFile(filePath + beantemp.getFileStore());
//            if(bo.delete(bean.getIds())){
//                errors.add("alert",new ActionError("alert.report.delete.successfull"));   
//            }else{
//                errors.add("alert",new ActionError("errors.report.delete.fail"));   
//            }
//            request.setAttribute("BReport",new BReport().getAllByType(bean));
//            request.setAttribute("reports",bean);
//            target=_SUCCESS;
        }else if(anchor.equals(_DELETE)){
                if(bo.delete(bean.getId())){
                    errors.add("alert",new ActionError("alert.report.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.report.delete.fail"));   
                }
            
           request.setAttribute("BReport",bo.getAllByType(bean));
           request.setAttribute("BReportTypes",new BReportType().getAllReportType());
           
           request.setAttribute("BUsers",bo.getUserByRules(me.getId()));
           
           request.setAttribute("reports",bean);
           target=_SUCCESS;
           
       }else  if(anchor.equals(_SEARCH)){
            bean.setMeId((int)me.getId());
           request.setAttribute("BReport",bo.getAllByType(bean));
           request.setAttribute("BReportTypes",new BReportType().getAllReportType());
           request.setAttribute("BUsers",bo.getUserByRules(me.getId()));
           request.setAttribute("reports",bean);
           target=_SUCCESS;
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        request.setAttribute("BListUsers",new BReportsRule().getAllBossByMeId(me.getId()));  
        return mapping.findForward(target);
    }
    private String validate(FReport bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
            if(bean.getName()!=null && bean.getName().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.bossier.name.null"));   
            }
        }else if(anchor.equals(_SEARCH)){
            if(bean.getTimeCreateForm()!=null && !bean.getTimeCreateForm().equals("")){
                if(!bean.isDate(bean.getTimeCreateForm())){
                    errors.add("alert",new ActionError("errors.template.search.timeCreateFrom.notIsDate"));   
                }else if(bean.getTimeCreateTo()!=null &&  !bean.getTimeCreateTo().equals("")){
                    if(!bean.isDate(bean.getTimeCreateTo())){
                        errors.add("alert",new ActionError("errors.template.search.timeCreateTo.notIsDate"));   
                    }
                }
            }else if(bean.getTimeCreateTo()!=null && !bean.getTimeCreateTo().equals("")){
                if(!bean.isDate(bean.getTimeCreateTo())){
                    errors.add("alert",new ActionError("errors.template.search.timeCreateTo.notIsDate"));   
                }else if(bean.getTimeCreateForm()!=null  && !bean.getTimeCreateForm().equals("")){
                    if(!bean.isDate(bean.getTimeCreateForm())){
                        errors.add("alert",new ActionError("errors.template.search.timeCreateFrom.notIsDate"));   
                    }
                }
            }
        }
    return anchor;
    }
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    }
   
}
