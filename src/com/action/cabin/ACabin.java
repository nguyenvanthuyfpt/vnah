package com.action.cabin;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.users.BUsers;
import com.bo.cabin.BCabin;
import com.bo.cabin.cabinType.BCabinType;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.users.FUser;
import com.form.cabin.FCabin;
import com.form.cabin.cabinType.FCabinType;

import com.inf.IRoles;
import com.inf.cabin.IKeyCabin;

import com.lib.AppConfigs;

import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ACabin extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
       final String LOCATION = this + "->executeAction()";
       String target =_SUCCESS;
       FCabin bean = (FCabin)form;
       bean.setMeId(me.getId());
       String anchor=bean.getValue(APP_ANCHOR,"");
       ActionErrors errors = new ActionErrors();
       BCabin bo = new BCabin();
       target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
            if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
                FBeans beans = new FBeans();          
                beans =new BDepartments().getAllRecord(0);
                request.setAttribute("BDepartments",beans);    
                bean.setDepartmentID((int)me.getDepartmentID());
                request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(bean.getDepartmentID(),0));

                request.setAttribute("BCabinTypes0",new BCabinType().getAll(me.getId(),0,(int)me.getDepartmentID()));
                request.setAttribute("BCabinTypes1",new BCabinType().getAll(me.getId(),1,(int)me.getDepartmentID()));
                request.setAttribute("BCabinTypes3",new BCabinType().getAll(me.getId(),3,(int)me.getDepartmentID()));
                
                request.setAttribute("BCabin",new BCabin().getAllByType(bean));
                request.setAttribute("cabin",bean);
                target=_PREPARE;
            }
        }else if(anchor.equals(_EDIT)){
        
            String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.CABIN_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs(); 
            String filename="";
            boolean haveFile = bean.getUpFile().getFileSize()>0;
                if(haveFile){
                  filename=encodeFileName(bean.me.getId());
                  bean.upload(bean.getUpFile(),dirs + filename);
                  bean.setCapacity(bean.getUpFile().getFileSize()+" B");
                  bean.setFileStore(bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP + filename);
                  bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.cabin.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("errors.cabin.insert.exitsName"));   
                    }
                }else{
                    FCabin beantemp =bo.getById(bean.getId());
                    bean.setCapacity(beantemp.getCapacity());
                    bean.setFileStore(beantemp.getFileStore());
                    bean.setRealName(beantemp.getRealName());
                    if(bo.update(bean)){
                        errors.add("alert",new ActionError("alert.cabin.update.successfull"));   
                    }else{
                        errors.add("alert",new ActionError("errors.cabin.insert.exitsName"));   
                    }
                }
            bean=bo.getById(bean.getId());
            bean.setRootPath("");
            if(bean.getCabinType_id()>0){
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                }
            }

            request.setAttribute("cabin",bean);
            FBeans beansD = new BDepartments().getAllRecord(0);
            request.setAttribute("BDepartments",beansD);
            if(bean.getType()==IKeyCabin.CABIN_PRIVATE){
                request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(((FDepartment)beansD.get(0)).getId(),0));   
            }
            bean.setMeId(me.getId());
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes",new BCabinType().getAll(bean.getMeId(),bean.getType(),(int)me.getDepartmentID()));    
            target=bean.getType()==IKeyCabin.CABIN_PRIVATE?_PREPARE:"_PREPARE_DEP";
            
        }else if(anchor.equals(_DOWNLOAD)){
                
                FCabin beanTemp = new FCabin();
                beanTemp=bo.getById(bean.getId());
                BUsers boUsers =new BUsers();
                FUser beanUsers =new FUser();
                beanUsers.setId(beanTemp.getUserId());
                beanUsers=boUsers.getRecordByID(beanUsers);
                String filePath="";
                if(beanTemp.getFileStore()!=null && !beanTemp.getFileStore().equals("")){
                    filePath =AppConfigs.APP_SYSTEM_PATH+AppConfigs.CABIN_FILE_PATH+AppConfigs.SYSTEM_FILE_SCHIP+ beanTemp.getFileStore();
                }
                File file = new File(filePath);
                if(file.exists()){
                    bean.download(filePath,URLEncoder.encode(beanTemp.getRealName(),"UTF-8"),null);
                }else{
                    bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
                }
        }else if(anchor.equals("_INPUT_OK")){            
            if(bean.getTypeFile()==1){
                FCabinType beanT =new FCabinType();
                BCabinType boT =new BCabinType();
                beanT.setId(bean.getId());
                beanT=boT.getCabinTypeById(beanT);
                beanT.setParentID(bean.getCabinTypeInput_id());
                boT.update(beanT);
                errors.add("alert",new ActionError("alert.cabintype.remove.successfull"));   
            }else if(bean.getTypeFile()==3){
                FCabinType beanT =new FCabinType();
                BCabinType boT =new BCabinType();
                beanT.setId(bean.getId());
                beanT=boT.getCabinTypeById(beanT);
                beanT.setParentID(bean.getCabinTypeInput_id());
                boT.addNew(beanT);
                errors.add("alert",new ActionError("alert.cabintype.remove.successfull"));   
            }else if(bean.getTypeFile()==0){
                FCabin beanTem =new FCabin();
                beanTem=bo.getById(bean.getId());
                beanTem.setMeId(me.getId());
                beanTem.setCabinType_id(bean.getCabinTypeInput_id());
                beanTem.setDepartmentID(beanTem.getDepartmentID());
                bo.update(beanTem);
                  errors.add("alert",new ActionError("alert.cabin.remove.successfull"));   
            }else if(bean.getTypeFile()==2){
                FCabin beanTem =new FCabin();
                beanTem=bo.getById(bean.getId());
                beanTem.setMeId(me.getId());
                beanTem.setCabinType_id(bean.getCabinTypeInput_id());
                beanTem.setDepartmentID(beanTem.getDepartmentID());
                bo.tranferData(beanTem);
                  errors.add("alert",new ActionError("alert.cabin.remove.successfull"));   
            }
                  anchor=_SEARCH;
       
        }else if(anchor.equals("_INPUT_STORE")){
            FCabinType beanT =new FCabinType();
            FBeans beanTs = new BCabinType().getAll(me.getId(),bean.getType(),(int)me.getDepartmentID());
            if(bean.getTypeFile()>0){
                for (int i=beanTs.size()-1;i>=0;--i )  {
                    beanT=(FCabinType)beanTs.get(i);
                    if((beanT.getId()==bean.getId())||(beanT.getId()==bean.getCabinType_id())){
                        beanTs.remove(i);
                    }
                }
            }else{
                for (int i=beanTs.size()-1;i>=0;--i )  {
                    beanT=(FCabinType)beanTs.get(i);
                    if(beanT.getId()==bean.getCabinType_id()){
                        beanTs.remove(i);
                    break;
                    }
                }
            }
            
            
                request.setAttribute("BCabinTypes",beanTs);
                request.setAttribute("cabinInput",bean);
                target=anchor;
       
        }else if(anchor.equals(_PREPARED_EDIT)){
        
            FCabin beantemp=new FCabin();
            beantemp.reset();
            beantemp=bo.getById(bean.getId());
            FBeans beans = new BDepartments().getAllRecord(0);
            request.setAttribute("BDepartments",beans);
            if(bean.getType()==IKeyCabin.CABIN_PRIVATE){
            request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(((FDepartment)beans.get(0)).getId(),0));     
            }
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes",new BCabinType().getAll(bean.getMeId(),bean.getType(),(int)me.getDepartmentID()));
            beantemp.setType(bean.getType());
            beantemp.setRootPath("");
            if(bean.getCabinType_id()>0){
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   beantemp.setRootPath(beantemp.getRootPath()+"/"+ params.get(i));
                }
            }
            request.setAttribute("cabin",beantemp);
            target=bean.getType()==IKeyCabin.CABIN_PRIVATE?_PREPARE:"_PREPARE_DEP";

        }else if(anchor.equals(_PREPARED_CREATE)){
            
            FBeans beans = new FBeans();          
            beans =new BDepartments().getAllRecord(0);
            request.setAttribute("BDepartments",beans);    
            bean.setDepartmentID((int)me.getDepartmentID());
            request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(bean.getDepartmentID(),0));
            if(bean.getToPertion()>0){
                bean.setEmpsRev(new FBeans());
                bean.getEmpsRev().add(bean);
            }else{
                bean.setEmpsRev(null);
            }
            int cabintype_temp=bean.getCabinType_id();
            int type_temp=bean.getType();
            bean.reset();
             bean.setRootPath("");
             if(cabintype_temp>0){
                 FCabinType beanCa=new FCabinType();
                 beanCa.setParentID(cabintype_temp);
                 List params =new ArrayList();
                for (int i=cabintype_temp;i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BCabinType().getCabinTypeById(beanCa);
                    params.add(beanCa.getName());
                }
                 for (int i=params.size()-1;i>-1;i--) {
                    bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                 }
             }
            bean.setCabinType_id(cabintype_temp);
            bean.setType(type_temp);
            request.setAttribute("cabin",bean);
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes",new BCabinType().getAll(bean.getMeId(),bean.getType(),(int)me.getDepartmentID()));
            target=bean.getType()==IKeyCabin.CABIN_PRIVATE?_PREPARE:"_PREPARE_DEP";
        }else if(anchor.equals(_CREATE)){
            int cabintype_temp=bean.getCabinType_id();
            String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.CABIN_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs();                        
            String filename="";
            boolean haveFile = bean.getUpFile().getFileSize()>0;
            if(haveFile){
                filename=encodeFileName(bean.me.getId());
                bean.upload(bean.getUpFile(),dirs + filename);
                bean.setCapacity(bean.getUpFile().getFileSize()+"");
                bean.setFileStore(bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP + filename);
                bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
            }
            bean.setMeId(me.getId());
                if(bo.insert(bean)){
                    errors.add("alert",new ActionError("alert.cabin.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.cabin.insert.exitsName"));   
                }
            bean.reset();
            bean.setMeId((int)bean.me.getId());
            bean.setFullName(me.getFullName());
            bean.setRootPath("");
            if(bean.getCabinType_id()>0){
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=cabintype_temp;i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                }
            }
            bean.setCabinType_id(cabintype_temp);
            request.setAttribute("cabin",bean);
            FBeans beansD = new BDepartments().getAllRecord(0);
            request.setAttribute("BDepartments",beansD);
            if(bean.getType()==IKeyCabin.CABIN_PRIVATE){
                request.setAttribute("BUsersDep",new BUsers().getUserByDepartmentID(((FDepartment)beansD.get(0)).getId(),0));   
            }
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes",new BCabinType().getAll(bean.getMeId(),bean.getType(),(int)me.getDepartmentID()));
            
            target=bean.getType()==IKeyCabin.CABIN_PRIVATE?_PREPARE:"_PREPARE_DEP";
        }else if(anchor.equals(_SHOW)){    
               
                   if(bean.getDepartmentID()>0){
                       FBeans beans = new BUsers().getUserByDepartmentID(bean.getDepartmentID(),0);
                       if(beans!=null && beans.size()>0){
                           request.setAttribute("BUsersDep",beans);
                       }
                   }
                   target = _SHOW;
         
        }else if(anchor.equals(_DELETE)){
            bean=bo.getById(bean.getId());
            bean.setMeId((int)me.getId());
            if(bo.delete(bean.getId())){
                if(bo.checkFileStoreCabin(bean)){
                    bean.deleteFile(AppConfigs.APP_SYSTEM_PATH+AppConfigs.CABIN_FILE_PATH+AppConfigs.SYSTEM_FILE_SCHIP+ bean.getFileStore());
                }
                errors.add("alert",new ActionError("alert.cabin.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.cabin.delete.fail"));   
            }
            
            request.setAttribute("BCabin",bo.getAllCabinUnionCabinType(bean));
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            bean.setRootPath("");
            if(bean.getCabinType_id()>0){
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                }
            }

            request.setAttribute("cabin",bean);
            target=_SEARCH;
        }else if(anchor.equals("_ORDERBY")){
            if(bean.getType()==IKeyCabin.CABIN_SHARE){
                    FBeans beanUserShare =new FBeans();
                    bean.setRootPath("");
                    beanUserShare=new BCabin().getUserShare((int)me.getId());
                    request.setAttribute("BUserShares",beanUserShare);
                    FCabin beanTemp =new FCabin();
                    if(beanUserShare!=null && beanUserShare.size()>0){
                       beanTemp=(FCabin)beanUserShare.get(0);
                       if(bean.getUserId()<=0){
                           bean.setUserId(beanTemp.getUserId());
                       }
                    }
                request.setAttribute("BCabinTypes0",new BCabinType().getAll(me.getId(),0,(int)me.getDepartmentID()));
                request.setAttribute("BCabinTypes1",new BCabinType().getAll(me.getId(),1,(int)me.getDepartmentID()));
                request.setAttribute("BCabinTypes3",new BCabinType().getAll(me.getId(),3,(int)me.getDepartmentID()));
                
                request.setAttribute("BCabin",new BCabin().getAllByType(bean));
            }else{
            request.setAttribute("BCabin",bo.getAllCabinUnionCabinType(bean));
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            bean.setRootPath("");
            if(bean.getCabinType_id()>0){
               
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
//               //.println(i);
                   beanCa.setId(beanCa.getParentID());

                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                }
            }
            }

            request.setAttribute("cabin",bean);
            target=_SEARCH;
        }
        if(anchor.equals(_SEARCH)){
//            String fileIndex=IKeyCabin.APP_SYSTEM_PATH+ IKey.LUCENE_PATH_INDEX;
//            String namefile=IKeyCabin.APP_SYSTEM_PATH+IKey.LUCENE_PATH_FILE;
//            File files=new File(fileIndex);
//            if(!files.exists()){
//            DSearchCabin.CreateIndex(files,namefile);
//            }
//            
//            if(bean.getContentSearch()!=null && !bean.getContentSearch().equals("")){
//               bean.setContentLucene(DSearchCabin.SearchIndex(fileIndex,bean.getContentSearch()));
//            }

            if(bean.getType()==IKeyCabin.CABIN_DEPARTMENT){
                if(me.isRole(IRoles.rCABIN_MUTI)){
                bean.setCabinType_id(0);
                    request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                }else{
                    bean.setDepartmentID((int)me.getDepartmentID());
                }
            }
            
            if(bean.getType()==IKeyCabin.CABIN_SHARE){
                request.setAttribute("BUserShares",new BCabin().getUserShare((int)me.getId()));
                request.setAttribute("BCabin",bo.getAllByType(bean));
            }else{
            request.setAttribute("BCabin",bo.getAllCabinUnionCabinType(bean));
            }
            request.setAttribute("BCabinTypes0",new BCabinType().getAll(bean.getMeId(),0,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes1",new BCabinType().getAll(bean.getMeId(),1,(int)me.getDepartmentID()));
            request.setAttribute("BCabinTypes3",new BCabinType().getAll(bean.getMeId(),3,(int)me.getDepartmentID()));
            bean.setRootPath("");
            if(bean.getCabinType_id()>0){
               
                FCabinType beanCa=new FCabinType();
                beanCa.setParentID(bean.getCabinType_id());
                List params =new ArrayList();
               for (int i=bean.getCabinType_id();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BCabinType().getCabinTypeById(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                   bean.setRootPath(bean.getRootPath()+"/"+ params.get(i));
                }
            }

            request.setAttribute("cabin",bean);
            if((bean.getTypeFile()==1)||(bean.getTypeFile()==3)){
                target= _SUCCESS;
            }else{
                target=anchor;
            }
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
    private String validate(FCabin bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
            if(bean.getName().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.cabin.edit.name.null"));   
            }
        }
    return anchor;
    }
   
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    }
   
}
