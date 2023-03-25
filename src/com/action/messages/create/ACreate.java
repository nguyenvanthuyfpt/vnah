package com.action.messages.create;


//import com.action.mail.AControl;


import com.action.ACore;

import com.bo.admin.mail.BRegister;
import com.bo.admin.users.BUsers;
import com.bo.messages.create.BCreate;
import com.bo.messages.create.BCreateFiles;

import com.dao.sms.DSendSms;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.mail.FMailAccount;
import com.form.admin.users.FUser;
import com.form.mail.FMail;
import com.form.messages.create.FCreate;

import com.lib.AppConfigs;

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


public class ACreate extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {      
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FCreate bean = (FCreate)form;   
        bean.setCreator((int)me.getId());
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        BCreate bo = new BCreate();    
        target = validate(bean,anchor,errors);
        if(bean.getValue("sendConpany")==null) bean.setSendConpany(0);;
        countEmail(bean,request);
        if(bean.getValue("sendMail")==null) bean.setSendMail(0);
        if(!errors.isEmpty()){     
            FBeans beans = bo.getAllDepartment();
            request.setAttribute("BDepartments",beans);
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,((FDepartment)beans.get(0)).getId()));             
            request.setAttribute("UserOption",bo.getUserByInUserId(bean,(int)me.getId()));
            if(anchor.equals(_CREATE)){
                request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId()));  
                
                target =_CREATE;
            }else{
                target = _PREPARED_CREATE;
            }
        }else if (anchor.equals(_PREPARED_DELETE)){
          
            if (bo.updateUnRemove(bean)){
                   errors.add("alert",new ActionError("errors.message.remove.unRemove.suss"));              
            }else{
                errors.add("alert",new ActionError("errors.message.remove.notdelete"));
            }
            request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId())); 
            target = _SHOW;  
        }else if(anchor.equals("_REPLY_PREPARE")){    
            
            bean.getUsersId();
            bean.resetFiles();
            FCreate beanT=new FCreate();
            bean.setEmpsRev(new FBeans());
            for(int j=0;j<bean.getUsersId().length;j++){
                beanT=new FCreate();
                FUser beanUser=new FUser();
                beanUser.setId(bean.getUsersId()[j]);
                beanUser=new BUsers().getRecordByID(beanUser);
                beanT.setUserFullName(beanUser.getFullName());
                beanT.setToPertion(beanUser.getId());
                bean.getEmpsRev().add(beanT);
            }
            request.setAttribute("createMessage",bean);
            FBeans beans = new FBeans();          
            beans = bo.getAllDepartment();
            bean.setSecureId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",bean.getSecureId());
            request.setAttribute("BDepartments",beans);    
            bean.setFileUpload(null);
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,bean.getDepartmentID()));
            request.getSession().setAttribute("BApp",bean.getApp());   
            request.getSession().setAttribute("accountEmail",new BRegister().getTopActive(me.getId()));    
            
            target=_CREATE_FALSE;
            
            
        }else if(anchor.equals("_SEARCH_PAGE")){
            FUser beanUser=new FUser();
            beanUser.setNameUser(bean.getToAddress());
            request.setAttribute("BMycontacts",new BUsers().search(beanUser));
            target=anchor;
            
        }else if (anchor.equals("_SEND_PAST_REPLY") || anchor.equals("_SEND_PAST_REPLY_ALL")){   
            
            bean.setFulltext(bean.getContentFast());
            FCreate beanT=new FCreate();
            beanT= bo.getRecordByID(bean,0);
            bean.setDepartmentID((int)bean.me.getDepartmentID());
            bean.setName("Re:" + beanT.getName());
            bean.setCreator((int)me.getId());
            if(anchor.equals("_SEND_PAST_REPLY")){
               int[] usersId={beanT.getCreator()};
               bean.setUsersId(usersId);
               bean.setUserFullName(beanT.getUserFullName());
            }
            if(anchor.equals("_SEND_PAST_REPLY_ALL")){
            int[] usersIdAll=new int[beanT.getEmpsRev().size()];
                for(int j=0;j<beanT.getEmpsRev().size();j++){
                    FCreate beanTemp =(FCreate)beanT.getEmpsRev().get(j);
                    usersIdAll[j]=beanTemp.getToPertion();
                }
                bean.setUsersId(usersIdAll);
                request.setAttribute("BUserRecvs",beanT.getEmpsRev());    
            }
            bo.insert(bean);
            FBeans beans = bo.getAllDepartment();
            request.setAttribute("BDepartments",beans);
            beans = bo.getUserByDepartmentId(bean,(int)me.getDepartmentID());
            if(beans!=null && beans.size()>0){
                request.setAttribute("BUsersDep",beans);                                                
            }
            request.setAttribute("error",1);
            bean.setFulltext(bean.getContentFast());
            
            
            
            request.setAttribute("messsagesList",bean);
            target ="_SEND_PAST_REPLY";  
        }else if (anchor.equals(_PREPARED_EDIT)){   

            FCreate beanC =new FCreate();
            if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0 && bean.getId()==0){
                beanC.setId(bean.getCheckEmp()[0]);
            }else{
                beanC.setId(bean.getId());
            }
                int reply=bean.getReply();
                beanC = bo.getRecordByID(beanC,0);
                beanC.setToPertion(beanC.getCreator());
                beanC.setEmpsRev(new FBeans());
                beanC.getEmpsRev().add(beanC);
                beanC.setReply(reply);
                beanC.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",beanC.getSecureId());
                String[] nameTem = beanC.getName().split(":");
                if (nameTem.length>=1){
                    beanC.setName("Re:" + nameTem[nameTem.length-1]);
                }else{
                    beanC.setName("Re:" + beanC.getName());                    
                }
            
            
            
            beanC.setFulltext("<br/>" + beanC.getTimeCreate() + ";" + beanC.getUserFullName() +"<div style=\"margin:10px;padding:5px;border-left:1px solid #CCCCCC;\">"+beanC.getFulltext()+"</DIV>");
            request.setAttribute("createMessage",beanC); 
            FBeans beans = new FBeans();          
            beans = bo.getAllDepartment();           
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BDepartments",beans);         
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,bean.getDepartmentID()));               
            
            target = _PREPARED_EDIT;   
            
        }else if (anchor.equals("_REPLY_ALL")){   
            
            if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0 && bean.getId()==0){
                bean.setId(bean.getCheckEmp()[0]);
            }
            int reply=bean.getReply();
            bean = bo.getRecordByID(bean,0);
            FCreate beanTU=new FCreate();
            beanTU.setToPertion(bean.getCreator());
            beanTU.setUserFullName(bean.getUserFullName());
            bean.getEmpsRev().add(beanTU);
            bean.setReply(reply);
            bean.setSecureId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",bean.getSecureId());
            String[] nameTem = bean.getName().split(":");
            if (nameTem.length>=1){
                bean.setName("Re:" + nameTem[nameTem.length-1]);
            }else{
                bean.setName("Re:" + bean.getName());                    
            }
            bean.setFulltext("<br/>" + bean.getTimeCreate() + ";" + bean.getUserFullName() +"<div style=\"margin:10px;padding:5px;border-left:1px solid #CCCCCC;\">"+bean.getFulltext()+"</DIV>");
            request.setAttribute("createMessage",bean); 
            FBeans beans = new FBeans();          
            beans = bo.getAllDepartment();           
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BDepartments",beans);         
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,bean.getDepartmentID()));               
            target = _PREPARED_EDIT;    
            
        }else if(anchor.equals("_FORWARD") ){   
            
                if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0 && bean.getId()==0){
                    bean.setId(bean.getCheckEmp()[0]);
                }
                int reply=bean.getReply();
                bean = bo.getRecordByID(bean,0);
                bean.setEmpsRev(new FBeans());
                bean.setReply(reply);
                bean.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",bean.getSecureId());
                String[] nameTem = bean.getName().split(":");
                if (nameTem.length>=1){
                    bean.setName("Fwd:" + nameTem[nameTem.length-1]);
                }else{
                    bean.setName("Fwd:" + bean.getName());                    
                }
            request.setAttribute("createMessage",bean); 
            FBeans beans = new FBeans();          
            beans = bo.getAllDepartment();           
            
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BDepartments",beans);         
            request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,bean.getDepartmentID()));               
        //            request.setAttribute("UserOption",bo.getUserByInUserId(bean,userId));
            
            target = _PREPARED_EDIT;                    
        }else if(anchor.equals(_PREPARED_SAVE)){    
            
            request.setAttribute("BMessages",new BCreate().getAllMessRecByUserId(bean,(int)me.getId()));           
            bean.setName("");
            request.setAttribute("createMessage",bean);             
            target =_PREPARED_SAVE;                  
            
        }else if (anchor.equals("_DELETE_ALL")){
            bean.setDelete(1);
            bean.setType(3);
            FBeans beans=new BCreate().getAllMessRecByUserId(bean,(int)me.getId());
            int[] checkEmp=new int[beans.size()];
            for(int i=0;i<beans.size();i++){
                FCreate beanT=(FCreate)beans.get(i);
                checkEmp[i]=beanT.getId();
            }
            bean.setCheckEmp(checkEmp);
            bo.updateRemove(bean);
            request.setAttribute("BMessages",new BCreate().getAllMessRecByUserId(bean,(int)me.getId()));           
            errors.add("alert",new ActionError("errors.message.remove.delete.suss"));
            target =_PREPARED_SAVE;
            
        }else if (anchor.equals(_DELETE)){
        
            if (bo.updateRemove(bean)){
               if (bean.getType()!=3){                    
                   errors.add("alert",new ActionError("errors.message.remove.suss"));
               }else{
                   errors.add("alert",new ActionError("errors.message.remove.delete.suss"));
               }
            }else{
                errors.add("alert",new ActionError("errors.message.remove.notdelete"));
            }
            bean.setName("");
            request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId())); 
            bean.setCheckEmp(null);
            target = _SHOW;                
        }else if(anchor.equals("_SEARCH_TEXT")){    
                
                    request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId()));           
                    request.setAttribute("createMessage",bean);             
                    target = "_SEARCH_TEXT";         
        
            
        }else if (anchor.equals(_CREATE)){
        
            Long secureId = (Long)request.getSession().getAttribute("secureId");
            if (secureId!=null && secureId==bean.getSecureId()){ 
                if (bo.insert(bean)){
                    bean.setType(2);
                    if(bean.getTotalFile()>0 || (bean.getFileIds()!=null && bean.getFileIds().length>0)){
                        new BCreateFiles().addBath(bean);
                    }
                   if(bean.getSendMail()>0 || bean.getSendSms()>0){
                               FBeans beans = new FBeans();
                               bean.setId(bo.getTopId(me.getId()));
                               beans = new BCreateFiles().getAllFileByMessageId(bean.getId());
                               int totals=beans.size() + (bean.getFileIds()!=null?bean.getFileIds().length:0);
                               String[] filenames = new String[totals];
                               String[] files =new String[totals];
                               int i =0;
                               for (i =0;i<beans.size();i++){
                                     FCreate BFiles =(FCreate)beans.get(i);
                                     files[i]= BFiles.getReadName();
                                     filenames[i]=AppConfigs.APP_SYSTEM_PATH + AppConfigs.MESSAGES_FILE_PATH + BFiles.getFileName();
                               }
                               
                               if(bean.getFileIds()!=null){
                                   for (int j =0;j<bean.getFileIds().length;j++){
                                         FCreate BFiles =new BCreateFiles().getByFileId(bean.getFileIds()[j]);
                                         files[i]= BFiles.getReadName();
                                         filenames[i]=AppConfigs.APP_SYSTEM_PATH + AppConfigs.MESSAGES_FILE_PATH + BFiles.getFileName();
                                       i++;
                                   }
                               }
                               BUsers BUser =new BUsers();
                               FMailAccount beanEmail=new BRegister().getById(bean.getSendMail());
                                if(beanEmail!=null){
                                beanEmail.setPortMail((beanEmail.getSercure()==0?25:993)+"");
                                }
                               for(int k=0;k<bean.getUsersId().length;k++){
                                    FUser beanusers =new FUser();
                                    beanusers.setId(bean.getUsersId()[k]);
                                    beanusers=BUser.getRecordByID(beanusers);
                                    new DSendSms().sendSms(bean.getName(),beanusers.getPhone());
                                    if(beanEmail!=null){
                                        bo.sendMail(me.getFullName(),beanEmail,bean.getName(),bean.getFulltext(),beanusers.getEmail(),filenames,files); 
                                    }
                                    
                               }
                               
                   }
                    bean.resetFiles();
                    target = _CREATE;    
                    request.setAttribute("error",1);
                }else{
                    request.setAttribute("error",0);
                    target = _CREATE;
                }
                bean.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",bean.getSecureId());
              }
            FBeans beans = bo.getAllDepartment();
            request.setAttribute("BDepartments",beans);
            beans = bo.getUserByDepartmentId(bean,(int)me.getDepartmentID());
            if(beans!=null && beans.size()>0){
                request.setAttribute("BUsersDep",beans);                                                
            }
            bean.setFileUpload(null); 
            String userFullName="";
            if(bean.getUsersId()!=null){
            for(int i=0;i<bean.getUsersId().length;i++){
                 FUser beanusers =new FUser();
                 beanusers.setId(bean.getUsersId()[i]);
                 beanusers=new BUsers().getRecordByID(beanusers);
                 if(!userFullName.equals("")) userFullName+="; ";
                 userFullName+=beanusers.getFullName(); 
            }
            }
            bean.setEmps(userFullName);
            request.setAttribute("messsagesList",bean);    
        
        
        }else if (anchor.equals("_PREPARED_SEND_EMAIL")){       
            
            FCreate beanT=new FCreate();
            if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0){
                beanT.setId(bean.getCheckEmp()[0]);
                beanT=bo.getRecordByID(beanT,(int)me.getId());                
            }
            beanT.getEmpsRev();
            String addRess="";
            FUser beanuser =new FUser();
            if(beanT.getEmpsRev()!=null && beanT.getEmpsRev().size()>0){
            for(int i=0;i<beanT.getEmpsRev().size();i++){
                FCreate btem =(FCreate)beanT.getEmpsRev().get(i);
                beanuser =new FUser();
                beanuser.setId(btem.getToPertion());
                beanuser=new BUsers().getRecordByID(beanuser);                  
                if(!beanuser.getEmail().equals("")){
                    if(!addRess.equals("")) addRess+=";";
                    addRess+=beanuser.getEmail();
             }}}
            FMailAccount emailAcc=new FMailAccount();
            emailAcc=new BRegister().getTopActive(bean.me.getId());
            emailAcc.setPortMail((emailAcc.getSercure()==0?25:993)+"");
            FMail beanMail =new FMail();
            beanMail.setSubject(beanT.getName());
            beanMail.setContent(beanT.getFulltext());
            //Folder folder=(Folder)request.getSession().getAttribute("folderStore");
            //new AControl().total(beanMail,request,folder);
            if(emailAcc.getUserMail()!=null && !emailAcc.getUserMail().equals("")){
                beanMail.setToAddress(emailAcc.getUserMail()+";");
            }
            request.setAttribute("sendMail",beanMail);
            request.setAttribute("BRegisters",new BRegister().getByMeId(me.getId()));     
            
            target=anchor;
            
        }else if (anchor.equals(_SEARCH)){    
        
                FBeans beans = bo.getAllDepartment();
                request.setAttribute("BDepartments",beans);
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,((FDepartment)beans.get(0)).getId()));   
                target = _SELECT;
                  
        }else if (anchor.equals(_PUT)){     
        
            request.setAttribute("UserOption",bo.getUserByInUserId(bean,(int)me.getId())); 
            target = _PUT ;
            
        }else if (anchor.equals(_PREPARE)){
        
            bean.setType(1);
            request.setAttribute("BMessages",bo.getAllMessRecByUserId(bean,(int)me.getId()));  
            request.setAttribute("BAmount",bo.getAllAmount(bean,bean.getId())); 
            request.setAttribute("messsagesList",bean);              
            target = _PREPARE ;
            
        }
       
        if(!errors.isEmpty()){
            saveErrors(request,errors);
        }
        
        return mapping.findForward(target);
    }
    
    private String validate(FCreate bean,String anchor,ActionErrors errors){
       if(anchor.equals(_CREATE)){
            if(((bean.getSendConpany()==0) && bean.getUsersId()==null) || bean.getName().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("messages.insert.name.isBrank"));
                }
       }       
       return anchor;
   }
   public void countEmail(FCreate bean,HttpServletRequest request) throws  EException,SQLException{    
         request.setAttribute("BAmount",new BCreate().getAllAmount(bean,(int)bean.me.getId()));   
   }
   
}