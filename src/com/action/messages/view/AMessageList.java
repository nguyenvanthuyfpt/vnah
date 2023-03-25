package com.action.messages.view;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.mail.BRegister;
import com.bo.messages.create.BCreate;
import com.bo.messages.create.BCreateFiles;

import com.exp.EException;

import com.form.FBeans;
import com.form.messages.create.FCreate;
import com.form.tasks.problem.FProblem;

import com.lib.AppConfigs;

import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AMessageList extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FCreate bean = (FCreate)form;   
        bean.setCreator((int)me.getId()); 
        bean.setAmountRevUnRead(0);
        BCreate bo = new BCreate();
        String anchor=bean.getValue(APP_ANCHOR,"");        
        countEmail(bean,request);
        ActionErrors errors = new ActionErrors();
        if (anchor.equals(_PREPARED_CREATE)){
            bean.resetFiles();
            FProblem change =new FProblem();
            change.setApp(bean.getApp());
            request.setAttribute("change",change);
            FBeans beans = new FBeans();          
            beans = bo.getAllDepartment();
            bean.setSecureId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",bean.getSecureId());
            request.setAttribute("BDepartments",beans);    
            
            bean.setFileUpload(null);
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,bean.getDepartmentID()));
            if(bean.getToPertion()>0){
                bean.setEmpsRev(new FBeans());
                bean.getEmpsRev().add(bean);
            }else{
                bean.setEmpsRev(null);
            }
            request.getSession().setAttribute("BApp",bean.getApp());   
            request.getSession().setAttribute("accountEmail",new BRegister().getTopActive(me.getId()));    
            target = _PREPARED_CREATE;
      
        
        }else if(anchor.equals(_PREPARED_SAVE)){    
        
            request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId()));           
            request.setAttribute("createMessage",bean);             
            target =_SHOW;
            
        }else if(anchor.equals(_CREATE_SUCCESS)){    
       //s bean.setS
        bean.setName("");
            request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId()));           
            
            request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));    
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,(int)me.getDepartmentID()));
            request.setAttribute("createMessage",bean);
            target =_CREATE_SUCCESS;
       
        }else if(anchor.equals("_SHOW_USER")){    
        
            if(bean.getDepartmentID()>0){
                FBeans beans = bo.getUserByDepartmentId(bean,bean.getDepartmentID());
                if(beans!=null && beans.size()>0){
                    request.setAttribute("BUsersDep",beans);
                }
            }
            target = anchor;   
                 
        }else if (anchor.equals("_FILEDINHKEM")){   
            request.setAttribute("BFiles",new BCreateFiles().getAllFileByMessageId(bean.getId()));
            target=anchor;
            
         
            
       
            
        } else if (anchor.equals(_VIEW)){  
            
            FCreate beanC = new FCreate();   
            beanC = bo.getRecordByID(bean,(int)me.getId());
            beanC.setCheckEmp(null);
            if (beanC.getFileName()==null) beanC.setFileName(_BLANK);
            request.setAttribute("BMessage",beanC);
            target = _VIEW;            
        }
        else if (anchor.equals(_SAVE)){
        
            String pictureFolder = AppConfigs.MESSAGES_FILE_PATH;
            String picturePath = AppConfigs.APP_SYSTEM_PATH + pictureFolder;    
            FCreate beanC = new FCreate();   
            beanC = new BCreateFiles().getByFileId(bean.getFileId());
            
            File files =new File(picturePath + beanC.getFileName());
            if(files.exists()){
                URLEncoder.encode("","UTF-8");
                bean.download(picturePath + beanC.getFileName(),URLEncoder.encode(beanC.getReadName(),"UTF-8"),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);               
            }
        }
        
        request.setAttribute("messsagesList",bean); 
        request.setAttribute("createMessage",bean);         
        if(!errors.isEmpty()) saveErrors(request,errors);        
        return mapping.findForward(target);
    }
       public void countEmail(FCreate bean,HttpServletRequest request) throws  EException,SQLException{    
             request.setAttribute("BAmount",new BCreate().getAllAmount(bean,(int)bean.me.getId()));   
       }
         
   }