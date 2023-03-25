package com.action.doc.docssend;


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
import com.bo.doc.assign.BTrailerSend;
import com.bo.doc.docsSearch.BDocsSearch;
import com.bo.doc.docsrecv.BDocsrecv;
import com.bo.doc.docssend.BDocssend;
import com.bo.doc.docssend.BFilesSend;
import com.bo.doc.from.BFrom;
import com.bo.foryou.BForYou;
import com.bo.main.BMain;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.category.classify.FClassify;
import com.form.admin.doc.category.dossiers.FDossiers;
import com.form.admin.doc.category.form.FForm;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsSearch.FDocsSearch;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;
import com.form.doc.docssend.FFilesSend;
import com.form.doc.from.FFrom;
import com.form.foryou.FForYou;
import com.form.tasks.problem.FProblem;

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


public class ADocssend extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocssend bean = (FDocssend)form;     
        int type=bean.getType();
        bean.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
        bean.setUserId((int)me.getId());
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BDocssend bo =new BDocssend();
        if(bean.getValue("docsId")==null) bean.setDocsId(null);
        if(bean.getValue("fromsId")==null) bean.setFromsId(null);
        if(bean.getValue("sendEmail")==null) bean.setSendEmail(0);
        if(bean.getValue("obServer")==null){
            bean.setObServer(0);
        } 
        
        target = validate(bean,anchor,errors,seed);
        if(!errors.isEmpty()){
           
                if(anchor.equals(_CREATE)  || anchor.equals(_EDIT) || anchor.equals(_DOC_ASSIGN_CREATE) || anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){
                bean.resetFiles();
                if(bean.getId()>0){
                FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                checkRules(request,BRules);
                bean.setUserId((int)me.getId());
                bean=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
                bean.setType(type);
                }else{
                FDocAssign beantemp =new FDocAssign();
                request.setAttribute("docAssign",beantemp);
                bean.setType(type);
                }
                request.setAttribute("docssend",bean);
                anchor=_SELECT;   
            }
            
        }else if(anchor.equals("_SEARCH_RECV")){        
        
                FDocsSearch beantemp =new FDocsSearch();
                beantemp.setLocalCode(bean.getLocalCodeSearch());
                beantemp.setDocCode(bean.getDocCodeSearch());
                beantemp.setLocalDate(bean.getLocalDateSearch());        
                beantemp.setDocDate(bean.getDocDateSearch());        
                beantemp.setDeadLine(bean.getDeadLineSearch());        
                beantemp.setAbstracts(bean.getAbstractsSearch());        
                beantemp.setUserId((int)me.getId());
                BDocsSearch boRecv =new BDocsSearch();
                beantemp.setPageIndex(bean.getPageIndex());
                request.setAttribute("BSearch",boRecv.searchDocReference(beantemp));
                target=anchor;
                
        }else if(anchor.equals("_CREATE_DOSS_SEND")){     
        
                FDossiers beantemp =new FDossiers();
                beantemp.setFullName(bean.me.getFullName());
                beantemp.setTimeOpen(bean.dateToString(bean.getCurrentSqlDate()));
                request.setAttribute("dossiers",beantemp);
                target= "_CREATE_DOSS_SEND";    
                
        }else if(anchor.equals("_CREATE_FROM")){
        
                FFrom beanF = new FFrom() ; 
                beanF.setType(type);
                beanF.setId(bean.getId());
                request.setAttribute("from",beanF);
                target=anchor;
                
        }else if (anchor.equals("_VIEW_LIST_TILE")){  
            request.setAttribute("BSigns",new BSign().getAllRecord());
            target="_VIEW_LIST_TILE";    
            
        }
        else if(anchor.equals(_DELETE)){
        
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            FDocssend beantemp=new FDocssend();
            beantemp=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            if(beantemp.getAllFiles()!=null){
                for(int i=0;i<beantemp.getAllFiles().size();i++){
                    FFilesSend beanFile =(FFilesSend)beantemp.getAllFiles().get(i);
                    bean.deleteFile(AppConfigs.APP_SYSTEM_PATH+beanFile.getPath()+beanFile.getFile());
                }
            }
            if(bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));
            }else{
                errors.add("alert",new ActionError("alert.delete.error"));
            }
            bean.setType(1);
            request.setAttribute("docssend",bean); 
            if((bean.getObServer()>0 && bean.getCheckObServer()>0) || bean.getViews()==1){
                request.setAttribute("tracking","");  
            }
            if(bean.getObServer()>0 && bean.getCheckObServer()>0){
                request.setAttribute("BSearch",bo.getAlldocssendObServer(bean,(int)me.getId(),1,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }else{
                FDocAssign BCheckRule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                request.setAttribute("BSearch",bo.getAlldocssend(bean,(int)me.getId(),1,checkWaitSend,BCheckRule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }
            FDocAssign beanrule=new FDocAssign();
            beanrule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            if(beanrule.getStatusIds()!=null){
            request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,bean.me.getId(),bean.getObServer(),beanrule.getStatusIds()));            
            }
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,bean.me.getId(),bean.getObServer(),checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));    
            if(beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
            request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            target="_BACK";
            
        }else if(anchor.equals("_PREPARED_EMIT")){
        
            FDocssend beantemp=new FDocssend();
            beantemp=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            beantemp.setDocDate(bean.dateToString(bean.getCurrentDate()));
            request.setAttribute("docssend",beantemp);
            
            request.setAttribute("BDossiers",new BDossiers().getAll((int)me.getId()));
            request.setAttribute("BForms",new BForm().getAllForm());
            request.setAttribute("BDocTypes",new BDocType().getAllDocType());
            
            if(beantemp.getReferentId()!=null && !beantemp.getReferentId().equals("")){
                FDocsrecv BRecv = new FDocsrecv();
                BRecv.setReferentId(beantemp.getReferentId());
                request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(BRecv));  
            }
            if(beantemp.getFromId()!=null && !beantemp.getFromId().equals("")){
                request.setAttribute("BFroms",new BFrom().getFromInID(beantemp.getFromId()));  
            }            
            target=anchor;
            
        }else if(anchor.equals(_PREPARED_EDIT)){
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }        
            int docId = bean.getId();
            bean.resetFiles();
            prepareInsert(request,bean,bo,type);
             if (docId>0){
                 BDocsrecv boRecv = new BDocsrecv();
                 FDocsrecv beanRecv = new FDocsrecv();
                 beanRecv.setId(docId); 
                 beanRecv.setUserId((int)me.getId());
                 beanRecv = boRecv.getDetail(beanRecv);                            
                 FBeans beans=new FBeans();
                 beans.add(beanRecv);
                 request.setAttribute("BDocsReference",beans);
             }
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            if(BRules.getStatusIds()!=null){
             request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BRules.getStatusIds()));
            }
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
            }             
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){  
                BRules.setDocId(bean.getId());
                request.setAttribute("BdocsMove",new BTrailerSend().getDocAssignByDocId(BRules,(int)me.getId())); 
            }  
            checkRules(request,BRules);
            request.getSession().setAttribute("BApp",bean.getApp());  
            bean.setId(docId);
            bean=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            bean.setType(type);
            bean.setChangeId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",bean.getChangeId());
            request.setAttribute("docssend",bean);
            if(bean.getFromId()!=null && !bean.getFromId().equals("")){
                request.setAttribute("BFroms",new BFrom().getFromInID(bean.getFromId()));  
            }
            bean.setDocsId(null);
            bean.setFromsId(null);
            anchor=_SELECT;    
            
        }else if(anchor.equals(_PREPARED_CREATE)){
            
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }        
            int docId = bean.getId();
            bean.resetFiles();
            prepareInsert(request,bean,bo,type);
             if (docId>0){
                 BDocsrecv boRecv = new BDocsrecv();
                 FDocsrecv beanRecv = new FDocsrecv();
                 beanRecv.setId(docId); 
                 beanRecv.setUserId((int)me.getId());
                 beanRecv = boRecv.getDetail(beanRecv);                            
                 FBeans beans=new FBeans();
                 beans.add(beanRecv);
                 request.setAttribute("BDocsReference",beans);
             }
             FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                        
            if(BRules.getStatusIds()!=null){
             request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BRules.getStatusIds()));
            }
           
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
            
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
            }             
           
            checkRules(request,BRules);
            request.getSession().setAttribute("BApp",bean.getApp());  
            request.setAttribute("docsrecv",new FDocsrecv());
            anchor=_SELECT;    
           
        }else if(anchor.equals("_DELETE_FILE")){
            FFilesSend beanFile=new FFilesSend();
            beanFile.setIdFiles(bean.getFileId());
            if(new BFilesSend().delete(beanFile)){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.delete.error"));   
            }
            bean=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            
            if(bean.getReferentId()!=null && !bean.getReferentId().equals("")){
                FDocsrecv BRecv = new FDocsrecv();
                BRecv.setReferentId(bean.getReferentId());
                request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(BRecv));  
            }
            
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            checkRules(request,BRules);
            bean.setType(type);
            
            request.setAttribute("docssend",bean);
            anchor=_SELECT;
        }else if(anchor.equals(_CREATE)){
            
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            
            Long secureId = (Long)request.getSession().getAttribute("secureId");          
            if(secureId!=null && secureId==bean.getChangeId()){
                bean = insertDoc(request,bean,bo,errors,bean.getType()); 
                bean.setDocId(bean.getId());
            }else{ 
                int DocType =bean.getType();                 
                bean = bo.getDocsByMaxId((int)me.getId());
                bean.setDocId(bean.getId()); 
                bean.setType(DocType);                
            }
            
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);                       
            BRules.setDocId(bean.getId());
            if(BRules.getStatusIds()!=null){
                request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BRules.getStatusIds()));
            }
            
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
            
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
               request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
            }
            
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){               
                request.setAttribute("BdocsMove",new BTrailerSend().getDocAssignByDocId(BRules,(int)me.getId()));
            } 
            anchor=_SELECT;
            
        }else if(anchor.equals("_EDIT_EMIT")){
            bean.setStatusId(AppConfigs.STATUS_STORE);
            if(bo.update(bean)){
                if(bean.getSendEmail()>0){
                    sendMail(request,bean,bo,bean.getSendEmail());
                }
                new BTrailerSend().updateReaded(bean.getId(),1,(int)me.getId(),bean.getForYouId()); 
                errors.add("alert",new ActionError("alert.emit.successfull"));   
            }
            getCategorier(request);
            request.setAttribute("docssend",bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            bean.reset();
            target="_PREPARED_EMIT";
        }else if(anchor.equals(_EDIT)){
            updateDocs(request,bean,bo,errors,type);
            FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            checkRules(request,BRules);
            FDocssend beansend=new FDocssend();
            beansend=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            beansend.setType(type);
            beansend.setChangeId(bean.getChangeId());
            request.setAttribute("docssend",beansend);
            if(bean.getReferentId()!=null && !bean.getReferentId().equals("")){            
                FDocsrecv BRecv = new FDocsrecv();
                BRecv.setReferentId(beansend.getReferentId());//van ban lien quan
                request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(BRecv));   
            }
            if(bean.getFromsId()!=null && !bean.getFromsId().equals("")){            
                request.setAttribute("BFroms",new BFrom().getFromInID(bean.getFromId()));   
            }
            anchor=_SELECT;
        }else if(anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){   
            FDocAssign beantem=(FDocAssign)request.getSession().getAttribute("BStoresend");
            if(beantem==null){
                errors.add("alert",new ActionError("errors.not.chose.userrecv"));
            }else{
                    FDocAssign BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                    if(beantem.getMembers()!=null && !beantem.getMembers().equals("")){
                            if(bean.getId()==0){
                                Long secureId = (Long)request.getSession().getAttribute("secureId");          
                                if(secureId!=null && secureId==bean.getChangeId()){
                                insertDoc(request,bean,bo,errors,bean.getType());
                                new BTrailerSend().updateReaded(bean.getId(),1,(int)me.getId(),bean.getForYouId());  //update Readed cua nguoi  nhan 
                                 if(bean.getTitle()!=null && !bean.getTitle().equals("") && beantem.getMembers()!=null){
                                     FDocssend beanId=new FDocssend();
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
                                     new BAssignSend().insertReview(beantem,(int)me.getId());
                                     
                                 }
                                }else{
                                    int DocType =bean.getType();                 
                                    bean = bo.getDocsByMaxId((int)me.getId());
                                    bean.setDocId(bean.getId()); 
                                    bean.setType(DocType);    
                                }
                            }else{
                                updateDocs(request,bean,bo,errors,type);
                                new BTrailerSend().updateReadedOfSendPeople(bean.getId(),0,(int)me.getId()); //update readed cua nguoi gui
                                new BTrailerSend().updateReaded(bean.getId(),1,(int)me.getId(),bean.getForYouId()); //update Readed cua nguoi  nhan 
                            }
                            FDocssend beanS =new FDocssend();
                            beanS=bo.getTopId((int)me.getId());
                            beantem.setDocId(beanS.getId());
                            beantem.setId(beanS.getId());
                            beantem.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
                            beantem.setIndexTrailer(2);
                            new BAssignSend().insert(beantem,(int)me.getId(),beantem.getRecvUserName());
                            errors.clear();
                            errors.add("alert",new ActionError("errors.direct.successfull"));                  
                            
                            int checkWaitSend = 0 ;
                            if (request.getSession().getAttribute("checkWaitSend")!=null){
                               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
                            }
                            
                            BRules.setDocId(bean.getId());
                            if(BRules.getStatusIds()!=null){
                                request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BRules.getStatusIds()));
                            }
                            
                            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
                            
                            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                               request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
                            }
                            
                            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){               
                                request.setAttribute("BdocsMove",new BTrailerSend().getDocAssignByDocId(BRules,(int)me.getId()));
                            } 
                    }
            }
            anchor=_SELECT;
        }else if(anchor.equals(_DOC_ASSIGN_CREATE)){
            
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            
            FDocAssign BRules = new FDocAssign();         
            Long secureId = (Long)request.getSession().getAttribute("secureId");          
            if(secureId!=null && secureId==bean.getChangeId()){
                if(bean.getId()==0){
                       bean = insertDoc(request,bean,bo,errors,bean.getType());                 
                       bean.setDocId(bean.getId());
                }else{
                      bean = updateDocs(request,bean,bo,errors,type);              
                }               
                BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                BRules.setForYouId(bean.getForYouId());
                if (bean.getForYouId()>0){
                    BRules.setForYouId(bean.getForYouId());
                    BRules = new BAssignRecv().checkAsignRule(BRules); 
                    bean.setStatusId(BRules.getStatusId()); 
                }else {
                     BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                     BRules = checkRules(request,BRules);
                }
                if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                    FDocAssign beanTemp =new FDocAssign();
                    beanTemp.setDocId(bean.getId());
                    beanTemp.setCreator(me.getId());
                    beanTemp.setTitle(bean.getTitle());
                    beanTemp.setRuleId(BRules.getRuleId());
                    new BAssignSend().insertReview(beanTemp,(int)me.getId());
                }
            }else{ 
                int docType =bean.getType();   
                int forYouId =bean.getForYouId();   
                bean = bo.getDocsByMaxId((int)me.getId());
                bean.setDocId(bean.getId()); 
                bean.setType(docType);  
                bean.setForYouId(forYouId);                
                BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                BRules.setForYouId(bean.getForYouId());
                if (bean.getForYouId()>0){
                    BRules.setForYouId(bean.getForYouId());
                    BRules = new BAssignRecv().checkAsignRule(BRules); 
                    bean.setStatusId(BRules.getStatusId()); 
                }else {
                     BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                     BRules = checkRules(request,BRules);
                }
            }
            
            
            BRules.setDocId(bean.getId());            
            FDocAssign beantemp = checkRules(request,BRules);
            beantemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            beantemp.setDepartmentId(bean.getDepartmentId());
            FDocssend beanC=bo.getTopId((int)me.getId());
            beantemp.setDocId(beanC.getId());
            beantemp.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
            beantemp.setIndexTrailer(3);
            new BAssignSend().insertDirect(beantemp,(int)me.getId());
            errors.clear();
            errors.add("alert",new ActionError("errors.direct.successfull"));
            
            if(bean.getApp()==100){
                anchor=_VIEW;
            }else if(beanC.getId()!=0){
                FDocssend beansend=new FDocssend();
                bean.setId(beanC.getId());
                beansend=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));               
                beansend.setType(bean.getType());
                request.setAttribute("docssend",beansend);
                anchor=_SELECT;
            }
            
            if(BRules.getStatusIds()!=null){
                request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,BRules.getStatusIds()));
            }
            
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
            
            if (BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
               request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
            }
            
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){               
                request.setAttribute("BdocsMove",new BTrailerSend().getDocAssignByDocId(BRules,(int)me.getId()));
            } 
            
            
        }else if(anchor.equals("_TIP_FORYOU")){
            FForYou beantemp =new FForYou();
            beantemp.setId(bean.getForYouId());
            request.setAttribute("forYou",new BForYou().getById(beantemp));
            target="_TIP_FORYOU";
        }else if(anchor.equals("_END_DOC")){
            FDocAssign beanAssign =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);            
            new BAssignRecv().updateReadedMe(beanAssign);
            anchor=_VIEW;
        }else if(anchor.equals("_DETAIL")){
            
            if(bean.getObServer()>0 || bean.getViews()==1){
                request.setAttribute("trackingObserver","");
            }
            FDocssend beansend =new FDocssend();
            beansend=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            beansend.setObServer(bean.getObServer());
            FDocAssign BRules = new FDocAssign();
            if (beansend.getForYouId()>0){
                
                BRules.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                BRules.setForYouId(beansend.getForYouId());
                BRules.setMeId(bean.getUserId());
                beansend.setCheckForYou(0);
                beansend.setRulesForYou(new FBeans());
                BRules=new BAssignRecv().checkAsignRule(BRules);
                request.setAttribute("BRuleForYou",BRules);
            }else{
                BRules =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            }
            
            
            beansend.setViews(bean.getViews());;
            beansend.setChangeId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",beansend.getChangeId());
            beansend.setForYouId(bean.getForYouId());
            request.setAttribute("docsend",bean);
            request.setAttribute("BDocssends",beansend);
            if(BRules.getCheckViewReview()!=2){
                    FDocAssign beanTemp=new FDocAssign();
                    beanTemp.setDocId(bean.getId());
                    beanTemp.setMeId(me.getId());
                    beanTemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                    beanTemp.setObServer(bean.getObServer());
                    request.setAttribute("BDocReviews",new BAssignSend().getAllReview(beanTemp));

            }
            checkRules(request,BRules);
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            target="_DETAIL";
            
        } else if(anchor.equals("_LIST_FILE_EMIT")){            
            FDocssend beantemp=new FDocssend();
            beantemp=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            request.setAttribute("BDocssends",beantemp);            

            target=anchor;
        } else if(anchor.equals("_FILEDINHKEM_IN_INFOR")){        
            request.setAttribute("BDocsFilesInInfor",new BFilesSend().getAllFilesEqualNameByDoc(bean.getId(),bean.getFileId()));            
            request.setAttribute("BDocssend",bean);
            
            target="_FILEDINHKEM";
        } else if(anchor.equals("_FILEDINHKEM")){        
             request.setAttribute("BDocssend",bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
             if(Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())==1){
                 request.setAttribute("BDocsFiles",new BFilesSend().getAllFileEmit(bean.getId(),1));
             }else{
                request.setAttribute("BDocsFiles",new BFilesSend().getAllByDocId(bean.getId()));
             }
            target="_FILEDINHKEM";        
        }
        else if(anchor.equals("_SELECT_OBSERVER")){
           bean.setStatusId(-3);
           anchor=_VIEW;   
        }else if (anchor.equals("_VIEW_CODE")){
           
            FForm beanF = new FForm();    
            beanF.setId(bean.getFormId());
            beanF = new BForm().getFormById(beanF);
            bean.setFormId(beanF.getId());
            bean.setLocalCode(bo.getCountOfYear(bean) + "/" + bean.getYear(bean.getCurrentSqlDate()) +"/"+ beanF.getCode());
            request.setAttribute("docssend",bean);
            target = "_VIEW_CODE";     
        }   
        else if(anchor.equals("_UPDATE_CLASSIFY")){
            request.setAttribute("docssend",bean);       
             request.setAttribute("BClassify",new BClassify().getAll());       
            target=anchor;
        }else if(anchor.equals("_UPDATE_STATUS")){
            if(bean.getStoreId()!=0){
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
            beanAssign.setId(bean.getId());
            beanAssign.setStatusId(beanTemp.getStatusId());
            beanAssign.setMeId(me.getId());
            beanAssign.setDisposeUser(null);
            beanAssign.setWorkflowId(beanTemp.getWorkflowId());
            beanAssign.setUserReply(bean.getUserReply());
            beanAssign.setForYouId(bean.getForYouId());
            beanAssign.setStatusIds(beanTemp.getStatusIds()); 
            new BAssignSend().updateReadedMe(beanAssign);
            anchor=_VIEW;
            
        }else if(anchor.equals(_DOWNLOAD)){
                
                FFilesSend BFiles =new FFilesSend();
                BFiles.setIdFiles(bean.getFileId());
                if(bean.getFileId()>0){
                    BFiles=new BFilesSend().getById(BFiles);
                }else{
                    BFiles=new BFilesSend().getMaxbyDocId(bean.getDocId());
                }                
                String filePath =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + BFiles.getPath() + BFiles.getFile();
                File file = new File(filePath);
                if(file.exists()){
                    new BFilesSend().addReadedFile(bean.getFileId(),me.getId());
                    bean.download(filePath,URLEncoder.encode(BFiles.getFileName(),"UTF-8"),null);
                }else{
                    bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);               
                }
            target=_VIEW;
            
        }else if (anchor.equals(_LAST_DOWNLOAD)){
            FFilesSend BFiles =new FFilesSend();
            BFiles.setDocId(bean.getId());
            BFiles=new BFilesSend().getLastFile(BFiles);
            String filePath =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + BFiles.getPath() + BFiles.getFile();
            File file = new File(filePath);
            if(file.exists()){
                new BFilesSend().addReadedFile(BFiles.getIdFiles(),me.getId());
                bean.download(filePath,URLEncoder.encode(BFiles.getFileName(),"UTF-8"),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);               
            }
            target=_VIEW;
        }else if(anchor.equals("_DOWNLOAD_DRAFT") || anchor.equals("_DOWNLOAD_DRAFT_EDIT")){
            
            FFilesSend BFiles =new FFilesSend();
            BFiles.setIdFiles(bean.getFileId());
            if(anchor.equals("_DOWNLOAD_DRAFT_EDIT")){
                BFiles.setIdFiles(bean.getFileId());    
                BFiles=new BFilesSend().getById(BFiles);
                BFiles=new BFilesSend().getMaxVByDoc(BFiles.getDocId(),BFiles.getFileName());        
            }else{
                    if(bean.getDocId()>0){
                        BFiles=new BFilesSend().getMaxbyDocId(bean.getDocId());
                    }else{
                        BFiles=new BFilesSend().getById(BFiles);
                    }  
            }
            String filePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + BFiles.getPath() + BFiles.getFile();
            //.println(filePath);
            File file = new File(filePath);
            if(file.exists()){
                new BFilesSend().addReadedFile(BFiles.getIdFiles(),me.getId());
                long secureId = System.currentTimeMillis();
                me.setExtTagLong(secureId);
                me.setExtTagString(BFiles.getFileName());
                me.setExtTagInt(bean.getDocId()==0?BFiles.getDocId():bean.getDocId());            
                me.setExtTagFloat(new Float(bean.getFileId()));
                Users.saveUser(me);
                String server = request.getServerName().replaceAll("\\.","@"); 
                String contextPath = request.getContextPath().replaceAll("/","@");
                String name ="VOffice." + secureId + "." + me.getSessionID() + ".http." + server + "." + contextPath + "." + request.getServerPort() + ".Draft.doc";
                bean.download(filePath,name,null);
                bean.deleteFile(AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + me.getId());
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);               
            }
            
            target=anchor;
        }else if(anchor.equals("_SAVE_DOC")){ 
            bean.setUserId((int)me.getId());
            bo.updateDossiers(bean);
            request.setAttribute("docssend",bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            target = DOC_DETAILT;
        }else if(anchor.equals("_PREPARE_CHOSE_RECV") || anchor.equals("_PREPARE_CHOSE_RECV_FROM_INFOR")){
           
            if(bean.getId()==0){
                request.getSession().removeAttribute("BStoresend");
            }
            if((bean.getObServer()>0 && bean.getCheckObServer()>0) || bean.getViews()==1){
                request.getSession().setAttribute("trackingInfor",1);  //dau
            }else{
                request.getSession().setAttribute("trackingInfor",0);  //hien
            }
             
             
            FDocAssign beanAssign = null ;
            if (bean.getForYouId()>0){
            
                beanAssign = new FDocAssign();
                beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                beanAssign.setForYouId(bean.getForYouId());               
                bean.setCheckForYou(0);   
                beanAssign = new BAssignRecv().checkAsignRule(beanAssign);
                request.setAttribute("BRuleForYou",beanAssign);
                
            }else{              
                 beanAssign = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);                
            }
            
            beanAssign.setType(bean.getType());
            beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);           
            beanAssign.setId(bean.getId());
            beanAssign.setDocId(bean.getId());
            beanAssign.setDepartmentId(0);
            beanAssign.setGroupId(0);
            beanAssign.setCheckShowTransfer(1);
            beanAssign.setMeId(bean.getForYouId()>0?beanAssign.getForyouCreator():me.getId());
            request.setAttribute("docAssign",beanAssign); 
             
             if (beanAssign.getCheckSelectRecv()==1){
                 FBeans beansDep=new BAssignSend().getAllDepartmentSelect(beanAssign.getMeId());
                 request.setAttribute("BDepartments",beansDep);  
                 FBeans beansGroup=new BAssignSend().getAllGroupByRule(beanAssign.getMeId());
                 request.setAttribute("BGroups",beansGroup);                                  
                 FBeans beansUsers=new BAssignSend().getUserInRule(beanAssign,0,0);
                 request.setAttribute("BUsers",beansUsers);  
                 request.setAttribute("BTranfers",beansUsers.size()>0?(FUser)beansUsers.get(0):null);
             }
             
             if(anchor.equals("_PREPARE_CHOSE_RECV")){
                 request.setAttribute("CreateDoc",1);   
             }
             bean.setDepartmentId(0);
           
             request.setAttribute("docAssignSend",beanAssign); 
             target="_PREPARE_CHOSE_RECV";
            
        }else if (anchor.equals(_CREATE_DOS_REFERENCE)){       
             request.getSession().removeAttribute("docsId");
             FDocsSearch beanTemp = new FDocsSearch();
             beanTemp.setTemp(null);
             request.setAttribute("docReference",beanTemp);                             
             target = _CREATE_DOS_REFERENCE;  
             
        }else if(anchor.equals(_SEARCH) || anchor.equals("_SEARCH_FROM_DOSSIER")){
            
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            FDocAssign  beanrule =  (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);   
            if(anchor.equals("_SEARCH_FROM_DOSSIER")){
                bean=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
                request.setAttribute("BSearch",bo.getAlldocssend(bean,(int)me.getId(),1,checkWaitSend,beanrule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }else{
                request.setAttribute("BSearch",bo.getAlldocssend(bean,(int)me.getId(),1,checkWaitSend,beanrule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }
            bean.setViews(-1);
            bean.setStatusId(-4);
           
            request.setAttribute("BSearch",bo.getAlldocssend(bean,(int)me.getId(),1,checkWaitSend,beanrule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            if(beanrule.getStatusIds()!=null){
            request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),0,beanrule.getStatusIds()));            
            }
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),0,checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));    
            if(beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
            request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)me.getId()));
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            target=_VIEW;
        }
        
        if(anchor.equals(_PREPARE_DOC_REVIEW_SEND)){
            request.getSession().setAttribute("trackingInfor",(bean.getObServer()>0 || bean.getViews()==1)?1:0);  //dau
            FDocssend beanDocSend = new FDocssend();
            FDocAssign  beanAssign =  (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);   
            bean.setUserId((int)me.getId());
            beanAssign.setMeId(bean.me.getId());
            beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);            
            beanAssign.setId(bean.getId());
            beanAssign.setDocId(bean.getId());
            beanAssign.setType(bean.getType());
            beanAssign.setStatusId(bean.getStatusId());
            beanAssign.setDossierId(bean.getDossierId());
            beanAssign.setViews(bean.getViews());
            beanDocSend = new BDocssend().getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            beanDocSend.setType(bean.getType());
            beanDocSend.setReaded(bean.getReaded());
            request.setAttribute("docssend",beanDocSend);
            if (beanAssign.getCheckDirect()==1 && beanAssign.getCheckSelectDept()==1){
                request.setAttribute("BDepartments",new BAssignSend().getAllDepartment(me.getId()));  
            }
            request.setAttribute("docAssign",beanAssign);
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            request.setAttribute("BDocReviews",new BAssignSend().getAllReview(beanAssign)); 
            target=_PREPARE_DOC_REVIEW_SEND;   
        }else if (anchor.equals("_BLOCK_FILE")){
            bo.updateBlockFile(bean);
            anchor=_VIEW;
        }
        
        
        if(anchor.equals(_SELECT)){
            getCategorier(request);
            if(bean.getType()==2){
                target=_PREPARED_SAVE;    
            }else{
                target=_SELECT;
            }
        }
        if(anchor.equals(_VIEW)){
            
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
            FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            
            if(BRules.getStatusIds()!=null){
            request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),bean.getObServer(),BRules.getStatusIds()));
            }
            
            request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),bean.getObServer(),checkWaitSend,BRules.getStatusIds()!=null?BRules.getStatusIds():"1110"));
            
            if(BRules.getCheckNotIncharge()>0 || BRules.getCheckUnReaded()>0){
                request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
            }
            
            if(bean.getObServer()>0 || bean.getViews()==1){
                request.setAttribute("tracking","");  
            }
            
            FDocssend beansend=new FDocssend();
            
            beansend.setStatusId(bean.getStatusId());
            beansend.setDossierId(bean.getDossierId());
            beansend.setClassifyId(bean.getClassifyId());
            beansend.setViews(bean.getViews());
            beansend.setObServer(bean.getObServer());
            beansend.setPageIndex(bean.getPageIndex());
            
            if(bean.getObServer()>0){
                request.setAttribute("BSearch",bo.getAlldocssendObServer(beansend,(int)me.getId(),1,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }else{
                request.setAttribute("BSearch",bo.getAlldocssend(beansend,(int)me.getId(),1,checkWaitSend,BRules.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
            }
            
            request.setAttribute("docssend",beansend);
            checkRules(request,BRules);
            target=_VIEW;   
        }
        bean.resetFiles();
        
         if(!errors.isEmpty()) saveErrors(request,errors);
       return mapping.findForward(target);
    }
    
    public String validate(FDocssend bean,String anchor,ActionErrors errors,FSeed seed) throws  EException {
        if(anchor.equals(_CREATE)  || anchor.equals(_EDIT) || anchor.equals(_DOC_ASSIGN_CREATE) || anchor.equals("_PREPARED_CREATE_AND_CREATE_DOC")){
        if(bean.getType()==1){
             if(bean.getDocCode()==null || bean.getDocCode().equals("")){
                     errors.add("alert",new ActionError("errors.docs.docCode.null"));   
             }else  if(bean.getLocalCode()==null || bean.getLocalCode().equals("")){
                 errors.add("alert",new ActionError("errors.docs.localCode.null"));   
             }else if(bean.getDocDate()==null && bean.getDocDate().equals("")){
                    errors.add("alert",new ActionError("errors.docs.docDate.null"));   
             }else  if(!bean.isDate(bean.getDocDate())){
                 errors.add("alert",new ActionError("errors.docs.docDate.notIsDate"));       
             }else  if(bean.getLocalDate()==null && bean.getLocalDate().equals("")){
                 errors.add("alert",new ActionError("errors.docs.localDate.null"));   
             }else  if((bean.getDeadLine()!=null) && (!bean.getDeadLine().equals(""))){
                 if(!bean.isDate(bean.getDeadLine())){
                     errors.add("alert",new ActionError("errors.docs.deadLine.notIsDate"));   
                 }
             }else  if(!bean.isDate(bean.getLocalDate())){
                 errors.add("alert",new ActionError("errors.docs.localDate.notIsDate"));       
             }            
        }else{
            if(bean.getDocDate()!=null && !bean.getDocDate().equals("")){
                    if(!bean.isDate(bean.getDocDate())){
                                     errors.add("alert",new ActionError("errors.docs.docDate.notIsDate"));       
                    }
            }else if(bean.getLocalDate()!=null && !bean.getLocalDate().equals("")){
                        if(!bean.isDate(bean.getLocalDate())){
                                     errors.add("alert",new ActionError("errors.docs.localDate.notIsDate"));       
                    }
            }else if(bean.getDeadLine()!=null && !bean.getDeadLine().equals("")){
                        if(!bean.isDate(bean.getDeadLine())){
                                     errors.add("alert",new ActionError("errors.docs.deadLine.notIsDate"));       
                    }
            }else if(bean.getLocalCode()==null && bean.getLocalCode().equals("")){
                     errors.add("alert",new ActionError("errors.docs.localCode.null"));   
            }else if(bean.getAbstracts()==null && bean.getAbstracts().equals("")){
                     errors.add("alert",new ActionError("errors.docs.localCode.null"));   
            }
        }
        }
        if(anchor.equals(_SEARCH)){
         if(bean.getDocDate()!=null && !bean.getDocDate().equals("") && bean.isDate(bean.getDocDate())){
             errors.add("alert",new ActionError("errors.docs.docDate.notIsDate"));       
         }
        }
        if(anchor.equals(_CREATE)){
                if(bean.getDocCode()!=null && (!bean.getDocCode().equals(""))){
                    if(new BDocssend().isExistAdd(seed)){
                        errors.add("alert",new ActionError("errors.insert.code.exits"));   
                    }
                }
        }
    return anchor;
    }
    
    private void getCategorier(HttpServletRequest request) throws  EException{
       
         //request.setAttribute("BFroms",new BFrom().getAllFrom(new FFrom()));
         request.setAttribute("BForms",new BForm().getAllForm());
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
        
    }
    public FDocAssign checkRules(HttpServletRequest request,FDocAssign beantemp) throws  EException{
        
        beantemp.setMeId(me.getId());
        beantemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
        if ((beantemp.getCheckDirect()==1) && (beantemp.getCheckSelectDept()==1)){
               request.setAttribute("BDepartments",new BAssignSend().getAllDepartment(beantemp.getForYouId()>0?beantemp.getForyouCreator():(int)me.getId()));  
        }
        request.setAttribute("docAssign",beantemp);     
        return beantemp;
    }
    public FDocssend insertDoc(HttpServletRequest request,FDocssend bean,BDocssend bo,ActionErrors errors,int type) throws  EException,SQLException{
          
            bean.setUserId((int)me.getId());       
            FDocssend beansend =new FDocssend();       
            FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            bean.setStatusId(AppConfigs.STATUS_NEW);
            if(bo.addNew(bean)){            
                bean.setId(bo.getTopId(bean.getUserId()).getId());  
                bean.setDocId(bean.getId());
                bean.setStatusId(BRules.getStatusId());
                bo.insertMe(bean);    
              
                if(bean.getDossierId()>0){
                    bean.setDossierId_doc(bean.getDossierId());
                    bo.updateDossiers(bean);  
                }
                
                checkRules(request,BRules);
                bean.setId(bo.getTopId((int)me.getId()).getId());
                if(bean.getTotalFile()>0){
                    new BFilesSend().addBath(bean,bean.getId());
                }
                 beansend=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));//test
                  //begin send mail
                 if(bean.getSendEmail()>0){
                    sendMail(request,beansend,bo,bean.getSendEmail());
                 }
                 beansend.setChangeId(System.currentTimeMillis());
                 request.getSession().setAttribute("secureId",beansend.getChangeId());
                 bean.reset();
                 errors.add("alert",new ActionError("alert.insert.successfull"));
            }else{
                FDocAssign beantemp =new FDocAssign();
                request.setAttribute("docAssign",beantemp);
                errors.add("alert",new ActionError("errors.insert.code.exits"));   
            }
            beansend.setType(type);
            request.setAttribute("docssend",beansend);
            FDocsrecv BRecv = new FDocsrecv();
            String docsId = "";
          if(bean.getDocsId()!=null){
                  for (int i = 0;i<bean.getDocsId().length;i++){
                      if (!docsId.equals("")) docsId +="," ;              
                       docsId +=bean.getDocsId()[i];
                  }  
              BRecv.setReferentId(docsId);
              request.setAttribute("BDocsReference",new BDocsrecv().getDocReference(BRecv));   
          }
        
        
            String fromsId = "";
              if(bean.getFromsId()!=null){
                      for (int i = 0;i<bean.getFromsId().length;i++){
                          if (!fromsId.equals("")) fromsId +="," ;              
                           fromsId +=bean.getFromsId()[i];
                      }  
                      request.setAttribute("BFroms",new BFrom().getFromInID(fromsId));   
              }
            bean.reset();
            bean.resetFiles();
        return beansend;
    }
    public FDocssend updateDocs(HttpServletRequest request,FDocssend bean,BDocssend bo,ActionErrors errors,int type) throws  EException,SQLException{    
       
        bean.setDossierId_doc(bean.getDossierId());
        FDocAssign beanC=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
        bean.setStatusId(beanC.getStatusId());
        if(bo.update(bean)){
            bean.setChangeId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",bean.getChangeId());
            FDocssend beanTemp=new FDocssend();
            if(bean.getTotalFile()>0){
                bean.setUserId((int)me.getId());
                beanTemp=bo.getDetail(bean,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
                new BFilesSend().addBath(bean,beanTemp.getId());
                bean.resetFiles();
            }
            sendMail(request,beanTemp,bo,bean.getSendEmail());
            errors.add("alert",new ActionError("alert.update.successfull"));   
        }else{
            errors.add("alert",new ActionError("errors.insert.code.exits"));   
        }       
        return bean; 
    }
    public void sendMail(HttpServletRequest request,FDocssend beansend,BDocssend bo,int sendMail) throws  EException,SQLException{    
            FBeans beans=new BFilesSend().getAllByDocId(beansend.getId());
            String[] filenames = null;
            String[] files = null;
            filenames = new String[beans.size()];
            files= new String[beans.size()];
            String folder =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP;
            for (int i =0;i<beans.size();i++){
              FFilesSend BFiles =(FFilesSend)beans.get(i);
              files[i]=BFiles.getFileName();
              filenames[i]=folder + BFiles.getPath() + BFiles.getFile();
            }
            if(beansend.getFromId()!=null && !beansend.getFromId().equals("")){
            beans=new BFrom().getFromInID(beansend.getFromId());
                  if(sendMail>0){
                       FFrom  beanFrom=null;
                       for(int i=0;i<beans.size();i++){
                           beanFrom=(FFrom)beans.get(i);
                           bo.sendMailDoc(!beansend.getDocCode().equals("")?beansend.getDocCode():beansend.getLocalCode(),beansend.getAbstracts(),beanFrom.getEnName(),filenames,files);
                       }
                  }
            }
    }
    public void prepareInsert(HttpServletRequest request,FDocssend bean,BDocssend bo,int type) throws  EException,SQLException{
        FProblem change =new FProblem();
        change.setApp(bean.getApp());
        request.setAttribute("change",change);
        bean.reset();
        FDepartment beanDept =new FDepartment();
        beanDept.setId((int)me.getDepartmentID());
        beanDept=new BDepartments().getRecordByID(beanDept);      
        FBeans beans= new BForm().getAllForm();
        String code = beans!=null?((FForm)beans.get(0)).getCode().toUpperCase():"";
        bean.setFormId(beans!=null?((FForm)beans.get(0)).getId():0);
        bean.setLocalCode(bo.getCountOfYear(bean) + "/" + bean.getYear(bean.getCurrentSqlDate()) +"/"+ code);
        
        bean.setCreator(bean.me.getFullName());
        bean.setLocalDate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setDocDate(bean.dateToString(bean.getCurrentSqlDate()));
        if(type==2){       
            
            FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);           
            checkRules(request,BRules); 
            bean.setStatusId(BRules.getStatusId());
            
        }else{
            FDocAssign beancheck =new FDocAssign();
            request.setAttribute("docAssign",beancheck);
            
            FDocAssign beanC=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            bean.setStatusId(beanC.getStatusId());
            
        }
        bean.setChangeId(System.currentTimeMillis());
        request.getSession().setAttribute("secureId",bean.getChangeId());
        bean.setType(type);
        bean.setStatusId(AppConfigs.STATUS_NEW);
        request.setAttribute("docssend",bean);
    }
    
}
