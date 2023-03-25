package com.action.doc.docsrecv;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.doc.category.branch.BBranch;
import com.bo.admin.doc.category.classify.BClassify;
import com.bo.admin.doc.category.doctype.BDocType;
import com.bo.admin.doc.category.dossiers.BDossiers;
import com.bo.admin.doc.category.express.BExpress;
import com.bo.admin.doc.category.form.BForm;
import com.bo.admin.doc.category.secure.BSecure;
import com.bo.admin.doc.category.status.BStatus;
import com.bo.admin.doc.category.via.BVia;
import com.bo.admin.doc.rules.BSign;
import com.bo.doc.assign.BAssignRecv;
import com.bo.doc.assign.BAssignSend;
import com.bo.doc.assign.BTrailerRecv;
import com.bo.doc.docsrecv.BDocsrecv;
import com.bo.doc.docsrecv.BFilesRecv;
import com.bo.doc.from.BFrom;
import com.bo.foryou.BForYou;
import com.bo.main.BMain;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.category.classify.FClassify;
import com.form.admin.doc.category.dossiers.FDossiers;
import com.form.admin.doc.category.form.FForm;
import com.form.admin.doc.rules.FDocRules;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docsrecv.FFilesRecv;
import com.form.doc.from.FFrom;
import com.form.foryou.FForYou;
import com.form.tasks.problem.FProblem;

import com.lib.AppConfigs;

import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADocsrecv extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocsrecv bean = (FDocsrecv)form; 
        bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
        bean.setDocId(bean.getId());
        bean.setUserId((int)me.getId());
        String anchor=bean.getValue(APP_ANCHOR,"");         
        ActionErrors errors = new ActionErrors();
        BDocsrecv bo =new BDocsrecv();
        if(bean.getValue("obServer")==null) bean.setObServer(0);
        target = validate(bean,anchor,errors);        
        
        if(!errors.isEmpty()){
                
            if(anchor.equals(_CREATE)  || anchor.equals(_EDIT) || anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){
                bean.resetFiles();
                FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                checkRules(request,BRules);
                request.setAttribute("docsrecv",bean);
                anchor=_SELECT;
            }else if(anchor.equals(_SEARCH)){
                FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                int checkWait = 0 ;
                if (request.getSession().getAttribute("checkWait")!=null){
                   checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                }
                bean.setDocDate("");
                request.setAttribute("BSearch",bo.getAllDocsrecv(bean,(int)me.getId(),BRules.getStatusIds(),checkWait));
            }
                
        }else if(anchor.equals("_CREATE_DOSS_RECV")){          
            
            FDossiers beantemp =new FDossiers();
            beantemp.setFullName(bean.me.getFullName());
            beantemp.setTimeOpen(bean.dateToString(bean.getCurrentSqlDate()));
            request.setAttribute("dossiers",beantemp);
            target= "_CREATE_DOSS_RECV";
            
        }else if(anchor.equals("_FILEDINHKEM")){
            
            request.setAttribute("BDocsFiles",new BFilesRecv().getAllByDocId(bean.getId()));
            target= "_FILEDINHKEM";
            
        }else if(anchor.equals("_CREATE_FROM")){          
        
            FFrom beanF = new FFrom() ; 
            beanF.setType(bean.getType());
            request.setAttribute("BFoms",new BForm().getAllForm());
            request.setAttribute("from",beanF);
            target= "_CREATE_FROM";
            
        }else if(anchor.equals(_DELETE)){
        
            FDocsrecv beantemp=new FDocsrecv();
            beantemp=bo.getById(bean);
            
            for(int i=0;i<beantemp.getAllFiles().size();i++){
                FFilesRecv beanFile =(FFilesRecv)beantemp.getAllFiles().get(i);
                bean.deleteFile(AppConfigs.APP_SYSTEM_PATH+beanFile.getPath()+beanFile.getFile());
            }
            
            if(bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));
            }else{
                errors.add("alert",new ActionError("require.cmd.insert.rm.notsusscess"));
            }            
            anchor=_VIEW;
            
        }else if(anchor.equals(_PREPARED_EDIT)){
            
            if(me.getExtInformation()!=null){
                request.setAttribute("BFileScans",me.getExtInformation());
            }    
            prepareInsert(request,bean,bo);
            if (bean.getApp()!=0){
                request.getSession().setAttribute("BApp",bean.getApp());   
            }
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            
            BRules.setMeId(bean.me.getId());            
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BRules,0));
            
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BRules,0,0,(BRules.getStatusIds()!=null && !BRules.getStatusIds().equals(""))?BRules.getStatusIds():"1110"));
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
            }        
            
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){  
                BRules.setDocId(bean.getId());
                request.setAttribute("BdocsMove",new BTrailerRecv().getDocRecvByDocId(BRules,(int)me.getId())); 
            }  
            
            checkRules(request,BRules);
            request.setAttribute("docsrecv",bo.getById(bean));
            bean.resetFiles();
            anchor=_SELECT;  
            
        }else if(anchor.equals(_PREPARED_CREATE)){
            
            if(me.getExtInformation()!=null){
                request.setAttribute("BFileScans",me.getExtInformation());
            }    
            prepareInsert(request,bean,bo);
            if (bean.getApp()!=0){
                request.getSession().setAttribute("BApp",bean.getApp());   
            }
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            
            BRules.setMeId(bean.me.getId());            
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BRules,0));
            
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BRules,0,0,(BRules.getStatusIds()!=null && !BRules.getStatusIds().equals(""))?BRules.getStatusIds():"1110"));
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
            }            
            checkRules(request,BRules);
            anchor=_SELECT;
            
        }else if(anchor.equals("_DELETE_FILE")){
        
            FFilesRecv beanFile=new FFilesRecv();
            beanFile.setIdFiles(bean.getFileId());
            beanFile=new BFilesRecv().getById(beanFile);
            bean.deleteFile(AppConfigs.APP_SYSTEM_PATH+beanFile.getPath()+beanFile.getFile());
            if(new BFilesRecv().delete(beanFile)){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.delete.error"));   
            }
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            checkRules(request,BRules);
            request.setAttribute("docsrecv",bo.getById(bean));
            anchor=_SELECT;
            
         }else if(anchor.equals(_SEARCH) || anchor.equals("_SEARCH_FROM_DOSSIER")){
            
            FDocAssign beanrule = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV); 
            int checkWait = 0 ;
            if (request.getSession().getAttribute("checkWait")!=null){
              checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
            }
            
            if(anchor.equals("_SEARCH_FROM_DOSSIER")){
                bean=bo.getDetail(bean);
                request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecvDetailt(bean,(int)me.getId(),beanrule.getStatusIds(),checkWait));
            }else {
                request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecv(bean,(int)me.getId(),beanrule.getStatusIds(),checkWait));
            }
            
            request.setAttribute("BDocsrecvs",bean);
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanrule,0));
           
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanrule,0,checkWait==1?1:0,(beanrule.getStatusIds()!=null && !beanrule.getStatusIds().equals(""))?beanrule.getStatusIds():"1110"));  
            if (beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)me.getId()));
            }   
            
            target=_VIEW;             
        }else if(anchor.equals(_CREATE)){
        
            bean.setStatusId(AppConfigs.STATUS_NEW);
            Long secureId = (Long)request.getSession().getAttribute("secureId");
            if(secureId!=null && secureId==bean.getChangeId()){
               bean =insertDoc(request,bean,bo,errors);
            }else{                       
                bean = bo.getDocsByMaxId((int)me.getId());
                bean.setDocId(bean.getId());                      
                request.setAttribute("docsrecv",bean);    
            }
            
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            BRules.setMeId(me.getId()); 
            BRules.setDocId(bean.getId());
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BRules,0));
            
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BRules,0,0,(BRules.getStatusIds()!=null && !BRules.getStatusIds().equals(""))?BRules.getStatusIds():"1110"));
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)me.getId()));
            }
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){              
                request.setAttribute("BdocsMove",new BTrailerRecv().getDocRecvByDocId(BRules,(int)me.getId())); 
            }  
            bean.resetFiles();
            bean.setIdFiles(null);
            anchor=_SELECT;
            
        }else if(anchor.equals(_EDIT)){
        
            bean = updateDocs(request,bean,bo,errors);
            request.setAttribute("docsrecv",bean);
            anchor=_SELECT;
            
        }else if(anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){
        
            FDocAssign beantem=(FDocAssign)request.getSession().getAttribute("BStorerecv");
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            if(beantem==null){
                errors.add("alert",new ActionError("errors.not.chose.userrecv"));
            }else{                    
                    if(beantem.getMembers()!=null && !beantem.getMembers().equals("")){
                            if(bean.getId()==0){
                               Long secureId = (Long)request.getSession().getAttribute("secureId");
                               if(secureId!=null && secureId==bean.getChangeId()){
                                     bean =insertDoc(request,bean,bo,errors);   
                                     if(bean.getTitle()!=null && !bean.getTitle().equals("") && beantem.getMembers()!=null){
                                         FDocsrecv beanId=new FDocsrecv();
                                         beanId=bo.getTopId((int)me.getId());
                                         bean.setId(beanId.getId());
                                         beantem.setDocId(bean.getId());
                                         beantem.setCreator(me.getId());
                                         beantem.setTitle(bean.getTitle());
                                         beantem.setRuleId(BRules.getRuleId());
                                         String[] disposeUsers=null;
                                         String userReply="";
                                         if(beantem.getMembers()!=null && !beantem.getMembers().equals("")){
                                             disposeUsers=beantem.getMembers().split(",");
                                             for(int i=0;i<disposeUsers.length;i++){
                                                 if (disposeUsers[i]!=null && !disposeUsers[i].equals("")){
                                                    if(!userReply.equals("")) userReply+=",";
                                                     userReply+="|"+disposeUsers[i].split("#")[1]+"|";
                                                 }
                                             }
                                             beantem.setReviewIds(userReply);
                                         }else{
                                             beantem.setReviewIds("|"+bean.getUserReply()+"|");
                                         }
                                         new BAssignRecv().insertReview(beantem,(int)me.getId());                                     
                                     }
                                }else{                       
                                    bean = bo.getDocsByMaxId((int)me.getId());
                                    bean.setDocId(bean.getId());                      
                                    request.setAttribute("docsrecv",bean);    
                                }
                                bean.resetFiles();
                                bean.setIdFiles(null);
                            }else{
                                bean = updateDocs(request,bean,bo,errors);                                
                            }    
                            
                            FDocAssign beanAssign = new FDocAssign();  
                            beanAssign.setId(bean.getId());
                            beanAssign.setDocId(bean.getId());
                            beanAssign.setStatusId(BRules.getStatusId());
                            beanAssign.setMeId(BRules.getMeId());
                            beanAssign.setDisposeUser(beantem.getDisposeUser());
                            beanAssign.setWorkflowId(BRules.getWorkflowId());
                            beanAssign.setMembers(beantem.getMembers());
                            beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
                            beanAssign.setIndexTrailer(2);
                            
                            new BAssignRecv().insert(beanAssign,(int)me.getId());
                            errors.clear();
                            errors.add("alert",new ActionError("errors.direct.successfull"));   
                            
                            BRules.setMeId(me.getId()); 
                            BRules.setDocId(bean.getId());
                            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BRules,0));
                            
                            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BRules,0,0,(BRules.getStatusIds()!=null && !BRules.getStatusIds().equals(""))?BRules.getStatusIds():"1110"));
                            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)me.getId()));
                            }
                            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){
                                BRules.setCheckDocTranfer(BRules.getCheckDocTranfer());
                                request.setAttribute("BdocsMove",new BTrailerRecv().getDocRecvByDocId(BRules,(int)me.getId())); 
                            }  
                    }
            }              
            anchor=_SELECT;
            
        }else if(anchor.equals("_PREPARE_CHOSE_RECV") || anchor.equals("_PREPARE_CHOSE_RECV_FROM_INFOR")){
        
            if(bean.getId()==0){
                request.getSession().removeAttribute("BStorerecv");
            }
            request.getSession().setAttribute("trackingInfor",(bean.getObServer()>0 || bean.getViews()==1)?1:0);  //dau
            FDocAssign beantemp = new FDocAssign();
            beantemp.setMeId(bean.me.getId());
            beantemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
            if (bean.getForYouId()>0){
                beantemp.setForYouId(bean.getForYouId());
                beantemp = new BAssignRecv().checkAsignRule(beantemp);             
            }else {  
                beantemp.setForYouId(0);
                beantemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);                
            }
            
            beantemp.setType(bean.getType());
            beantemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);            
            beantemp.setId(bean.getId());
            beantemp.setDocId(bean.getId());
            beantemp.setDepartmentId(0);
            beantemp.setGroupId(0);
            beantemp.setCheckShowTransfer(1);
            beantemp.setMeId(bean.getForYouId()>0?beantemp.getForyouCreator():me.getId());
           
            request.setAttribute("docAssign",beantemp);     
            if (beantemp.getCheckSelectRecv()==1){ 
                FBeans beansDep=new BAssignRecv().getAllRecordByRule(beantemp,(int)beantemp.getMeId());
               request.setAttribute("BDepartments",beansDep);                
               request.setAttribute("BGroups",new BAssignRecv().getAllGroupByRule((int)beantemp.getMeId()));
               
                FBeans beansU = new BAssignRecv().getUserByDepartmentId(beantemp,0,0);
               request.setAttribute("BUsersDep",beansU);                   
               request.setAttribute("BTranfers",beansU.size()>0?(FUser)beansU.get(0):null);                           
            }            
            if(anchor.equals("_PREPARE_CHOSE_RECV")){
                request.setAttribute("CreateDoc",1);   
            }
            
            request.setAttribute("docAssignRecv",beantemp);   
            target="_PREPARE_CHOSE_RECV";
            
        }else if(anchor.equals(_DOWNLOAD)){
        
            FFilesRecv BFiles =new FFilesRecv();
            BFiles.setIdFiles(bean.getFileId());
            BFiles=new BFilesRecv().getById(BFiles);
            String filePath =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT + AppConfigs.SYSTEM_FILE_SCHIP + BFiles.getPath() + BFiles.getFile();
            File file = new File(filePath);
            if(file.exists()){
                bean.download(filePath,URLEncoder.encode(BFiles.getFileName(),"UTF-8"),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
            target=_VIEW;  
                
        }else if(anchor.equals("_SEARCH_STORES")){
            
            request.setAttribute("BSearchStore",bo.searchStore(bean,(int)me.getId()));
            target=anchor;
                
        }else if(anchor.equals("_SEARCH_REFERENCE")){
            
            request.setAttribute("BSearch",bo.searchDocReferent(bean,(int)me.getId()));
            target=anchor;
                
        }else if (anchor.equals(_LAST_DOWNLOAD)){
        
            FFilesRecv BFiles =new FFilesRecv();
            BFiles.setDocId(bean.getId());
            BFiles=new BFilesRecv().getLastFile(BFiles);
            String filePath =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + BFiles.getPath() + BFiles.getFile();
            File file = new File(filePath);
            if(file.exists()){
                bean.download(filePath,URLEncoder.encode(BFiles.getFileName(),"UTF-8"),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);               
            }
            target=_VIEW;
            
        }else if(anchor.equals("_TIP_FORYOU")){
        
            FForYou beantemp =new FForYou();
            beantemp.setId(bean.getForYouId());
            request.setAttribute("forYou",new BForYou().getById(beantemp));
            target="_TIP_FORYOU";
            
        }else if(anchor.equals("_DETAIL")){
            
            FDocRules beanDocRules = new FDocRules();
            beanDocRules = new BAssignSend().checkObserver((int)me.getId(),AppConfigs.DOCSRECV_WORKFLOWID);
            bean.setCheckObServer(beanDocRules.getObserverId());          
            FDocsrecv beanrecv =new FDocsrecv();
            beanrecv=bo.getDetail(bean);                              
            FDocAssign BRules = new FDocAssign();
            if (bean.getForYouId()>0){
                   
                   BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                   BRules.setForYouId(beanrecv.getForYouId());
                   BRules.setMeId(bean.getUserId());
                   BRules=new BAssignRecv().checkAsignRule(BRules);
                   request.setAttribute("BRuleForYouRecv",BRules);
            }else{
                 BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            }
            
            beanrecv.setViews(bean.getViews());;
            beanrecv.setChangeId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",beanrecv.getChangeId());
            request.setAttribute("BDocsrecvs",beanrecv);
            if(BRules.getCheckViewReview()!=2){
            
                FDocAssign beanTemp=new FDocAssign();
                beanTemp.setDocId(bean.getId());
                beanTemp.setMeId(me.getId());
                beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                beanTemp.setCheckViewReview(BRules.getCheckViewReview());
                request.setAttribute("BDocReviews",new BAssignRecv().getAllReview(beanTemp));
            
            }
            
            if (BRules.getCheckDossier()==1){
                request.setAttribute("BDossiers",new  BDossiers().getAll((int)bean.me.getId()));
            }
                
            checkRules(request,BRules);
            target="_DETAIL";
                
       }else if(anchor.equals(_DOC_ASSIGN_CREATE)){
        
               if(bean.getId()==0){
                   Long secureId = (Long)request.getSession().getAttribute("secureId");
                   if(secureId!=null && secureId==bean.getChangeId()){
                        insertDoc(request,bean,bo,errors);
                   }else{                       
                       bean = bo.getDocsByMaxId((int)me.getId());
                       bean.setDocId(bean.getId());                      
                       request.setAttribute("docsrecv",bean);    
                   }
                   bean.resetFiles();
                   bean.setIdFiles(null);
               }else{
                   bean =updateDocs(request,bean,bo,errors);
               }   
               
               FDocAssign BRules = new FDocAssign();
               BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
               BRules.setForYouId(bean.getForYouId());
               if (bean.getForYouId()>0){
                   BRules.setForYouId(bean.getForYouId());
                   BRules = new BAssignRecv().checkAsignRule(BRules); 
                   bean.setStatusId(BRules.getStatusId()); 
               }else {
                    BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                    BRules = checkRules(request,BRules);
               }               
               
               BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
               BRules.setDepartmentId(bean.getDepartmentId());
               BRules.setDocId(bean.getId()); 
               
            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                 FDocAssign beanTemp =new FDocAssign();
                 beanTemp.setDocId(bean.getId());
                 beanTemp.setCreator(me.getId());
                 beanTemp.setTitle(bean.getTitle());
                 beanTemp.setRuleId(BRules.getRuleId());
                 new BAssignRecv().insertReview(beanTemp,(int)me.getId());
             }
               
               errors.clear();               
               FDocAssign beanAssign = new FDocAssign();  
               beanAssign.setDocId(BRules.getDocId());
               beanAssign.setStatusId(BRules.getStatusId());
               beanAssign.setMeId(me.getId());
               beanAssign.setDisposeUser(null);
               beanAssign.setWorkflowId(BRules.getWorkflowId());
               beanAssign.setDepartmentId(bean.getDepartmentId());
               beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
               beanAssign.setIndexTrailer(3);
               new BAssignRecv().insertDirect(beanAssign);
               errors.add("alert",new ActionError("errors.direct.successfull"));                  
            
                BRules.setMeId(me.getId());            
                request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(BRules,0));
                
                request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(BRules,0,0,(BRules.getStatusIds()!=null && !BRules.getStatusIds().equals(""))?BRules.getStatusIds():"1110"));
                if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                    request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)me.getId()));
                }
                if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){
                    BRules.setCheckDocTranfer(BRules.getCheckDocTranfer());
                    request.setAttribute("BdocsMove",new BTrailerRecv().getDocRecvByDocId(BRules,(int)me.getId())); 
                }  
                anchor=_SELECT;
           
        }else if(anchor.equals("_SELECT_OBSERVER")){
           
                bean.setStatusId(-3);
                request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
                request.setAttribute("BClassify",new BClassify().getAll());
                 FDocAssign beanTempS = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV); 
                beanTempS.setMeId(bean.me.getId());
                request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanTempS,bean.getObServer()));
                 int checkWait = 0 ;
                 if (request.getSession().getAttribute("checkWait")!=null){
                    checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                 }
                request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanTempS,bean.getObServer(),checkWait==1?1:0,(beanTempS.getStatusIds()!=null && !beanTempS.getStatusIds().equals(""))?beanTempS.getStatusIds():"1110"));
                   
                 if (beanTempS.getCheckNotIncharge()>0 || beanTempS.getCheckUnReaded()>0){
                    request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
                 }
             
                 if(bean.getObServer()>0 || bean.getViews()==1){
                     request.setAttribute("tracking",System.currentTimeMillis());
                 }
                if(bean.getObServer()>0){
                    request.setAttribute("BSearch",bo.getAllDocsrecvOBServer(bean,(int)me.getId(),checkWait));
                    
                }else{
                    
                    request.setAttribute("BSearch",bo.getAllDocsrecv(bean,(int)me.getId(),beanTempS.getStatusIds(),checkWait));
                }            
                request.setAttribute("docsrecv",bean);   
                target=_VIEW;
            
         }
       
        else if(anchor.equals("_UPDATE_CLASSIFY")){
        
            bean.getDocId();
            request.setAttribute("docsrecv",bean);       
            request.setAttribute("BClassify",new BClassify().getAll());       
            target=anchor;
        
        }else if(anchor.equals("_UPDATE_STATUS")){
        
            if(bean.getStoreId()>-2 ){
                bo.updateStatus(bean.getStoreId(),bean.getId());
            }
          
            FDocAssign beanTemp = new FDocAssign();
            beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
            beanTemp.setForYouId(bean.getForYouId());
            if (bean.getForYouId()>0){
                beanTemp.setForYouId(bean.getForYouId());
                beanTemp = new BAssignRecv().checkAsignRule(beanTemp); 
                bean.setStatusId(beanTemp.getStatusId());                
                bean.setForYouId(0);        
            }else{
                beanTemp=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                beanTemp = checkRules(request,beanTemp);
            }
            
            FDocAssign beanAssign = new FDocAssign();  
            beanAssign.setDocId(bean.getId());
            beanAssign.setStatusId(beanTemp.getStatusId());
            beanAssign.setMeId(me.getId());
            beanAssign.setDisposeUser(null);
            beanAssign.setWorkflowId(beanTemp.getWorkflowId());
            beanAssign.setUserReply(bean.getUserReply());
            beanAssign.setForYouId(bean.getForYouId());
            beanAssign.setStatusIds(beanTemp.getStatusIds()); 
            new BAssignRecv().updateReadedMe(beanAssign);
           
            request.setAttribute("docsrecv",bean);   
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BClassify",new BClassify().getAll());
            
            request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
            int checkWait = 0 ;
            if (request.getSession().getAttribute("checkWait")!=null){
               checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
            }
            if(bean.getObServer()>0){
                checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());   
                request.setAttribute("BSearch",bo.getAllDocsrecvOBServer(bean,(int)me.getId(),checkWait));
            }else{
                FDocAssign beanTempS = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);                
                request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecv(bean,(int)me.getId(),beanTempS.getStatusIds(),checkWait));
            }            
            checkRules(request,beanAssign);
            beanAssign.setMeId(bean.me.getId());
          
            request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanAssign,bean.getObServer()));            
            request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanAssign,bean.getObServer(),checkWait==1?1:0,(beanAssign.getStatusIds()!=null && !beanAssign.getStatusIds().equals(""))?beanAssign.getStatusIds():"1110"));   
            target=_VIEW;
            
        }else if(anchor.equals("_VIEW_FILESCAN")){ 
        
            String scanName = (String)((List)me.getExtInformation()).get(bean.getFileId());
            String scanPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + me.getId()+"."+ scanName;          
            File file = new File(scanPath);
            if(file.exists()){ 
                bean.download(scanPath,scanName,null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
            target = anchor;
            
        }else if(anchor.equals("_GET_FILESCANS")){ 
        
            if(me.getExtInformation()!=null){
                request.setAttribute("BFileScans",me.getExtInformation());
            }
            target="_GET_FILESCANS";
            
        }else if(anchor.equals("_SCANNER")){ 
        
            long secureId = System.currentTimeMillis();
            me.setExtTagLong(secureId);  
            Users.saveUser(me);
            target = anchor;
            
        }else if(anchor.equals("_SAVE_DOC")){ 
        
            bean.setUserId((int)me.getId());
            bo.updateDossiers(bean);                        
            request.setAttribute("docsrecv",bo.getDetail(bean));
            target = DOC_DETAILT;
            
        }else if(anchor.equals("_END_DOC")){         
         
             FDocAssign beanAssign =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);            
             new BAssignRecv().updateReadedMe(beanAssign);
             anchor=_VIEW;
        }else if (anchor.equals("_VIEW_LIST_TILE")){  
            request.setAttribute("BSigns",new BSign().getAllRecord());
            target="_VIEW_LIST_TILE";    
            
        }else if (anchor.equals("_VIEW_FROM")){
        
            request.setAttribute("BFroms",new BFrom().getAllFomByFromId(bean.getFormId()));
            target = "_VIEW_FROM";
            
        }else if (anchor.equals("_VIEW_CODE")){
            
            FForm beanF = new FForm();    
            beanF.setId(bean.getFormId());
            beanF = new BForm().getFormById(beanF);
            bean.setFormId(beanF.getId());
            bean.setLocalCode(bo.getCountOfYear(bean) + "/" + bean.getYear(bean.getCurrentSqlDate()) +"/"+ beanF.getCode().toUpperCase());

            request.setAttribute("docsrecv",bean);
            target = "_VIEW_CODE";
        } else if (anchor.equals("_BLOCK_FILE")){
            bo.updateBlockFile(bean);
            anchor=_VIEW;
        }

        
        if(anchor.equals(_VIEW)){
        
                 if(bean.getValue("statusId1")!=null) bean.setStatusId(Integer.parseInt(bean.getValue("statusId1")));
                 FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                 FDocAssign beanSession = checkRules(request,BRules);
                 beanSession.setMeId(bean.me.getId());
                 FDocRules beanReules =new FDocRules();
                 beanReules = new BAssignSend().checkObserver((int)me.getId(),AppConfigs.DOCSRECV_WORKFLOWID);
                 bean.setCheckObServer(beanReules.getObserverId());
                 
                 if((bean.getObServer()>0 && bean.getCheckObServer()>0) || bean.getViews()==1){
                     request.setAttribute("tracking","");
                 }
                 
                 bean.setDocCode("");
                 bean.setDocDate("");
                 bean.setAbstracts("");
                 bean.setFromVnName("");
                 int checkWait = 0 ;
                 if (request.getSession().getAttribute("checkWait")!=null){
                    checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                 }
                 if(bean.getObServer()>0 && bean.getCheckObServer()>0){
                     request.setAttribute("BSearch",bo.getAllDocsrecvOBServer(bean,(int)me.getId(),checkWait));
                 }else{
                      
                     request.setAttribute("BSearch",bo.getAllDocsrecv(bean,(int)me.getId(),BRules.getStatusIds(),checkWait));
                 }
                 
                 request.setAttribute("docsrecv",bean);
                 request.setAttribute("BClassify",new BClassify().getAll());
                 request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
                 request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanSession,bean.getObServer()));
                 request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanSession,bean.getObServer(),(checkWait==1?1:0),(beanSession.getStatusIds()!=null && !beanSession.getStatusIds().equals(""))?beanSession.getStatusIds():"1110"));  
                
                 if (beanSession.getCheckNotIncharge()>0 || beanSession.getCheckUnReaded()>0){
                     request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
                 }
                target=_VIEW;
         }
        if(anchor.equals(_SELECT)){
        
            FBeans  beans = new FBeans();
            beans =new BForm().getAllForm();
            if (beans.size()>0 && bean.getFromId()==0){
                bean.setFromId( ((FForm)beans.get(0)).getId());
            }
            request.setAttribute("BFroms",new BFrom().getAllFomByFromId(bean.getFromId()));
            request.setAttribute("BForms",beans);
            request.setAttribute("BDeps",new BDepartments().getAllRecord(0));
            request.setAttribute("BSecures",new BSecure().getAllSecure());
            request.setAttribute("BExpresss",new BExpress().getAllExpress());
            request.setAttribute("BVias",new BVia().getAllVia());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BDocTypes",new BDocType().getAllDocType());
            request.setAttribute("BStatus",new BStatus().getAllStatus());
            request.setAttribute("BBRanch",new BBranch().getAllVia());
            FBeans beansClassify=new BClassify().getAll();
            request.setAttribute("BClassify",beansClassify);
            String arrayClassify="";
            for(int i=0;i<beansClassify.size();i++){
            FClassify BClassify=(FClassify)beansClassify.get(i);
                if(!arrayClassify.equals("")) arrayClassify+=",";
                arrayClassify+=BClassify.getId()+"#"+BClassify.getNumberDay();
            }
            request.setAttribute("arrayClassify",arrayClassify);
                target=_SELECT;
                
        }
        
        if(anchor.equals(_PREPARE_DOC_REVIEW_RECV)){
            
            request.getSession().setAttribute("trackingInfor",(bean.getObServer()>0 || bean.getViews()==1)?1:0);  
            FDocsrecv beanDocRecv = new FDocsrecv();
            beanDocRecv = new BDocsrecv().getDetail(bean);
            beanDocRecv.setReaded(bean.getReaded());
            request.setAttribute("docsrecv",beanDocRecv);
            FDocAssign  beanAssign = new FDocAssign();
            beanAssign.setMeId(bean.me.getId());   
            beanAssign.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);           
            beanAssign.setId(beanDocRecv.getId());
            beanAssign.setDocId(bean.getId());
            beanAssign.setSecureId(System.currentTimeMillis());           
            beanAssign.setViews(bean.getViews());
            beanAssign.setDossierId(bean.getDossierId());
            beanAssign.setStatusId(bean.getStatusId());
            
            FDocAssign  beanSession = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);  
            request.getSession().setAttribute("secureId",bean.getSecureId());
            if(beanSession.getCheckDirect()==1 && beanSession.getCheckSelectDept()>0){
                request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment(me.getId()));  
            }
            request.setAttribute("docAssign",beanAssign);       
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID(me.getId()));
            request.setAttribute("BDocReviews",new BAssignRecv().getAllReview(beanAssign));                               
            target=_PREPARE_DOC_REVIEW_RECV;
            
        }
        
       if(!errors.isEmpty()) saveErrors(request,errors);
       return mapping.findForward(target);
    }
    private FDocsrecv updateDocs(HttpServletRequest request,FDocsrecv bean,BDocsrecv bo,ActionErrors errors) throws  EException,SQLException{    
        
        FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
        checkRules(request,BRules);
        bean.setDossierId_doc(bean.getDossierId());
        if(bo.update(bean)){
            if(bean.getTotalFile()>0){
                new BFilesRecv().addBath(bean,bean.getId());
            }
            errors.add("alert",new ActionError("alert.update.successfull"));   
        }else{
            errors.add("alert",new ActionError("errors.insert.code.exits"));   
        }
        bean.resetFiles();
        return bean;
    }
    private String validate(FDocsrecv bean,String anchor,ActionErrors errors){
            
           if(anchor.equals(_CREATE) || anchor.equals(_DOC_ASSIGN_CREATE) || anchor.equals(_EDIT) || anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){
            if( bean.getDocCode()==null || bean.getDocCode().equals("")){
                errors.add("alert",new ActionError("errors.docs.docCode.null"));   
            }else  if(bean.getLocalCode()==null || bean.getLocalCode().equals("")){
                errors.add("alert",new ActionError("errors.docs.localCode.null"));   
            }else if(bean.getDocDate()==null || bean.getDocDate().equals("")){
                errors.add("alert",new ActionError("errors.docs.docDate.null"));   
            }else if(!bean.isDate(bean.getDocDate())){
                errors.add("alert",new ActionError("errors.docs.docDate.notIsDate"));       
            }else if(bean.getLocalDate()==null || bean.getLocalDate().equals("")){
                errors.add("alert",new ActionError("errors.docs.localDate.null"));   
            }else if(!bean.isDate(bean.getLocalDate())){
                errors.add("alert",new ActionError("errors.docs.localDate.notIsDate"));       
            }else if(bean.getDeadLine()!=null && !bean.getDeadLine().equals("") && !bean.isDate(bean.getDeadLine())){
                    errors.add("alert",new ActionError("errors.docs.deadLine.notIsDate"));       
           }
       }
       if(anchor.equals(_SEARCH)){
        if(bean.getDocDate()!=null && !bean.getDocDate().equals("") && bean.isDate(bean.getDocDate())){
            errors.add("alert",new ActionError("errors.docs.docDate.notIsDate"));       
        }
       }
        return anchor;
        }
    public FDocAssign checkRules(HttpServletRequest request,FDocAssign beantemp) throws  EException{
         
        beantemp.setMeId(me.getId());
        beantemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);       
        if (beantemp.getCheckDirect()==1 && beantemp.getCheckSelectDept()==1){
            request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment(beantemp.getForYouId()>0?beantemp.getForyouCreator():(int)me.getId()));  
        }        
        request.setAttribute("docAssign",beantemp);  
        return beantemp;
        
    }
    
    public void prepareInsert(HttpServletRequest request,FDocsrecv bean,BDocsrecv bo) throws  EException,SQLException{
        
        FProblem change =new FProblem();
        change.setApp(bean.getApp());
        request.setAttribute("change",change);
        FDepartment beanDept =new FDepartment();
        beanDept.setId((int)me.getDepartmentID());
        beanDept=new BDepartments().getRecordByID(beanDept);
        bean.setLocalCode(bo.getTopId((int)me.getId()).getId()+1+"/"+me.getId()+"/"+ beanDept.getCode().toUpperCase());
        bean.setCreator(bean.me.getFullName());
        bean.setStatusId(AppConfigs.STATUS_NEW);
        bean.setTimeCreate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setDocDate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setLocalDate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setCheckDirect(0);
        FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
        checkRules(request,BRules);
        bean.setChangeId(System.currentTimeMillis());
        request.getSession().setAttribute("secureId",bean.getChangeId());
        bean.resetFiles();
        bean.setIdFiles(null);       
        request.setAttribute("docsrecv",bean);
    }
    
    public FDocsrecv insertDoc(HttpServletRequest request,FDocsrecv bean,BDocsrecv bo,ActionErrors errors) throws  EException,SQLException{
                FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                FDocAssign beanAssign = checkRules(request,BRules);
                bean.setUserId((int)me.getId());       
                bean.setStatusId(AppConfigs.STATUS_NEW);
                if(bo.addNew(bean)){
                bean.setId(bo.getTopId(bean.getUserId()).getId());
                bean.setDocstatus(beanAssign.getStatusId());
                bo.insertMe(bean);  
                if(bean.getDossierId()>0){
                    bean.setDossierId_doc(bean.getDossierId());
                    bo.updateDossiers(bean);  
                }
                    if( bean.getId()>0 && (bean.getTotalFile()>0 || bean.getIdFiles()!=null || bean.getEmailFileIds()!=null)){
                        
                        FBeans BEmailFiles =new FBeans();
                        if(bean.getEmailFileIds()!=null && request.getSession().getAttribute("BEmailFiles")!=null){
                            BEmailFiles=(FBeans)request.getSession().getAttribute("BEmailFiles");
                            bean.setAllFiles(BEmailFiles);
                        }
                        new BFilesRecv().addBath(bean,bean.getId());
                        if(bean.getIdFiles()!=null){
                            List scans = (List)me.getExtInformation();
                            int[] ids = bean.getIdFiles();
                            for (int i=ids.length-1;i>=0;i--){
                                scans.remove(ids[i]);
                            }
                            me.setExtInformation(scans);
                            Users.saveUser(me);
                        }
                    }
                    errors.add("alert",new ActionError("alert.insert.successfull"));
                    bean.resetFiles();
                    bean=bo.getById(bean);
                    bean.setChangeId(System.currentTimeMillis());
                    request.getSession().setAttribute("secureId",bean.getChangeId());
                }else{
                    bean.resetFiles();
                    prepareInsert(request,bean,bo);
                    errors.add("alert",new ActionError("errors.insert.code.exits"));   
                }                
                request.setAttribute("docsrecv",bean);
                bean.resetFiles();
                bean.setIdFiles(null);
                return bean;
    }    
}
