package com.action.doc.assign;


import com.action.ACore;

import com.bo.admin.doc.category.classify.BClassify;
import com.bo.admin.doc.category.dossiers.BDossiers;
import com.bo.doc.assign.BAssignRecv;
import com.bo.doc.assign.BAssignSend;
import com.bo.doc.assign.BTrailerSend;
import com.bo.doc.docssend.BDocssend;
import com.bo.doc.docssend.BFilesSend;
import com.bo.main.BMain;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docssend.FDocssend;
import com.form.doc.docssend.FFilesSend;

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


public class AAssignSend extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocAssign bean = (FDocAssign)form;
        FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
        bean.setRuleId(BRules.getRuleId());//moi them 
        
        int storeStatusId=bean.getStatusId();       
        bean.setDocId(bean.getId());
        bean.setMeId(me.getId());
        if(bean.getValue("readed")==null) bean.setReaded(0);
        if(bean.getValue("review")==null) bean.setReview(0);
        if(bean.getValue("wrongWay")==null) bean.setWrongWay(0);
        
        bean.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        BAssignSend bo =new BAssignSend();        
        target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
        
        }else if (anchor.equals("_VIEWUSERS")){               
            request.setAttribute("docAssign",bean);     
                    request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));               
        target=anchor;
        }else if (anchor.equals(_DOC_ASSIGN)){               
            bean.setMeId(me.getId());           
            request.setAttribute("docAssign",bean);     
            if (bean.getCheckSelectRecv()==1){
                    request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));               
            }
            target = _DOC_ASSIGN;//      
        }else if (anchor.equals("_VIEW_LIST_TILE")){
        target=anchor;
        }else if (anchor.equals(_DOC_DEPARMENT_SELECT)){
            bean.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            bean.setMeId(me.getId());                               
            request.setAttribute("docAssign",bean); 
            if (bean.getCheckSelectRecv()==1){
                request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));   
            }                     
              target = _DOC_DEPARMENT_SELECT;
        } else if (anchor.equals(_PREPARE)){
        
            FDocssend beanDocSend = new FDocssend();
            beanDocSend.setId(bean.getId());
            beanDocSend.setUserId((int)me.getId());
            beanDocSend = new BDocssend().getDetail(beanDocSend,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));            
            FDocAssign  beanAssign = new  FDocAssign();
            beanAssign.setDocCode(beanDocSend.getDocCode());
            beanAssign.setCreateDate(beanDocSend.getDocDate()); 
            beanAssign.setType(bean.getType());
            request.setAttribute("docAssign",beanAssign);  
            target = _PREPARE;
            
        }else if(anchor.equals("_DETAIL")){
        
            FDocssend BDocssend = new FDocssend();      
            BDocssend.setId(bean.getId());
            BDocssend.setUserId((int)me.getId());
            BDocssend.setWorkflowId(bean.getWorkflowId());
            BDocssend.setObServer(bean.getObServer());
            BDocssend = new BDocssend().getDetail(BDocssend,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            BDocssend.setType(bean.getType());
            if (bean.getForYouId()>0){                   
                   BRules.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                   BRules.setForYouId(bean.getForYouId());
                   BRules.setMeId(me.getId());
                   BRules=new BAssignRecv().checkAsignRule(BRules);
                   request.setAttribute("BRuleForYou",BRules);
                   BDocssend.setForYouId(bean.getForYouId());
            }
            BDocssend.setObServer(bean.getObServer());
            request.setAttribute("BDocssends",BDocssend);
            target =anchor;
        }else if (anchor.equals(_DOC_REVIEW)){
        
            FDocssend beansend =new FDocssend();
            beansend.setId(bean.getId());
            beansend.setUserId((int)me.getId());
            beansend.setObServer(bean.getObServer());
            beansend =new BDocssend().getDetail(beansend,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
            beansend.setObServer(bean.getObServer());
            
            if(BRules.getCheckViewReview()!=2){
            request.setAttribute("BDocReviews",bo.getAllReview(bean)); 
            }
            if (bean.getForYouId()>0){
                   
                   BRules.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                   BRules.setForYouId(bean.getForYouId());
                   BRules.setMeId(me.getId());
                   BRules=new BAssignRecv().checkAsignRule(BRules);
                   request.setAttribute("BRuleForYou",BRules);
                   beansend.setForYouId(bean.getForYouId());
            }
            request.setAttribute("BDocssends",beansend);
            request.setAttribute("docsend",bean);
            target ="_CREATE_REVIEW_TEMP" ;
        }else if (anchor.equals("_SHOW_ALL")){
                     FDocAssign beanAssign = null ;
                     if (bean.getForYouId()>0){                     
                         beanAssign = new FDocAssign();
                         beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                         beanAssign.setForYouId(bean.getForYouId());                                     
                         beanAssign = new BAssignRecv().checkAsignRule(beanAssign);
                         request.setAttribute("BRuleForYou",beanAssign);                         
                     }else{              
                          beanAssign = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);                
                     }
                    
                    bean.setMeId(bean.getForYouId()>0?beanAssign.getForyouCreator():me.getId());                     
                    request.setAttribute("docAssign",bean);
                    bean.setCheckShowTransfer(1);
                   
                    FBeans beansUsers=new BAssignSend().getUserInRule(bean,bean.getDepartmentId(),bean.getGroupId());
                    request.setAttribute("BUsers",beansUsers);  
                    request.setAttribute("BTranfers",beansUsers.size()>0?(FUser)beansUsers.get(0):null);
                    target =anchor;
        } 
        else if (anchor.equals("_VIEW_DRAFT")){
            String draftPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + me.getId();          
            File file = new File(draftPath);
            if(file.exists() && me.getExtTagLong()!=null && me.getExtTagString()!=null){ 
                bean.download(draftPath,(String)me.getExtTagString(),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
            target = anchor;
        } else if (anchor.equals("_GET_DRAFT")){
            String draftPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + me.getId();          
            File file = new File(draftPath);
            if(file.exists() && me.getExtTagLong()!=null && me.getExtTagString()!=null){ 
                target = "_GET_DRAFT";
            }
        } else if (anchor.equals("_DRAFT")){ 
            FDocAssign beantemp=new FDocAssign();
            beantemp=bo.getByReviewId(bean.getReviewId());
            String picturePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_SEND_PATH + beantemp.getPathFile();          
            File files = new File(picturePath);
            if(files.exists()){
                long secureId = System.currentTimeMillis();
                me.setExtTagLong(secureId);
                me.setExtTagString(beantemp.getFileName());
                Users.saveUser(me);
                String server = request.getServerName().replaceAll("\\.","@"); 
                String contextPath = request.getContextPath().replaceAll("/","@");
                String name = "VOffice." + secureId + "." + me.getSessionID() + ".http." + server + "." + contextPath + "." + request.getServerPort() + ".Draft.doc";
                bean.download(picturePath,name,null);
                bean.deleteFile(AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + me.getId());
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
            target=anchor;
        }else if (anchor.equals("_TRANFER_DOCS")){
            FDocAssign beantemp=new FDocAssign();
            beantemp=bo.getByReviewId(bean.getReviewId());
             String pathFile =AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_SEND_PATH + beantemp.getPathFile();          
            String dirRoot = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD);
            File files = new File(pathFile);
            (new File(dirRoot)).mkdirs();                        
            String filename=encodeFileName(bean.me.getId());
            if(files.exists()){
                new CopyFile().copyFile(pathFile,dirRoot+filename);
                BFilesSend bFiles =new BFilesSend();
                FFilesSend FormFile =new FFilesSend();
                FormFile.setDocId(bean.getId());
                FormFile.setFileName(beantemp.getFileName());
                FormFile.setFile(filename);
                FormFile.setUserId(me.getId());
                bFiles.insert(FormFile);
                errors.add("alert",new ActionError("doc.content.create.main.version.successfull"));  
            }else{
                errors.add("alert",new ActionError("doc.content.file.notExits"));  
            }
            target =anchor;
        }else if (anchor.equals("_CREATE_FROM_DOC")){
            
            if(bean.getId()==0){
                request.getSession().setAttribute("BStoresend",bean);
            }else {
            if (bo.insert(bean,(int)me.getId(),bean.getRecvUserName())){                 
                errors.add("alert",new ActionError("alert.insert.doc.successfull"));                
            }
            }
            target=forwardFormAssign(request,target,bean);
            request.setAttribute("CreateDoc",1); 
            
        }else if (anchor.equals(_CREATE)){        
            
            
           request.getSession().setAttribute("Members",bean.getMembers());
           
            FDocAssign beanTemp = new FDocAssign();
            beanTemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            beanTemp.setForYouId(bean.getForYouId());
            int foryouId = bean.getForYouId();
            if (bean.getForYouId()>0){
                beanTemp.setForYouId(bean.getForYouId());
                beanTemp = new BAssignRecv().checkAsignRule(beanTemp); 
                bean.setStatusId(beanTemp.getStatusId());
                bean.setMeId(me.getId());
                bean.setForYouId(0);        
            }else{
                
                beanTemp=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                beanTemp = checkRules(request,beanTemp);
            }  
            
          
            FDocAssign beanAssign = new FDocAssign();
            beanAssign.setForYouId(foryouId); 
            beanAssign.setDocId(bean.getId());
            beanAssign.setId(bean.getId());
            beanAssign.setStatusId(beanTemp.getStatusId());
            beanAssign.setMeId(me.getId());
            beanAssign.setDisposeUser(null);
            beanAssign.setWorkflowId(beanTemp.getWorkflowId());
            beanAssign.setDepartmentId(bean.getDepartmentId());
            beanAssign.setForyouCreator(beanTemp.getForyouCreator()); 
            beanAssign.setMembers(bean.getMembers());
            beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
            beanAssign.setIndexTrailer(2);
            if (bo.insert(beanAssign,(int)me.getId(),bean.getRecvUserName())){                 
                errors.add("alert",new ActionError("alert.insert.doc.successfull"));                
            }else{
                errors.add("alert",new ActionError("errors.direct.error"));     
            }
            
            if(bean.getReview()==1){
                request.setAttribute("docAssign",bean);
            }else{
                target=forwardFormAssign(request,target,bean);
            }
            
            target=anchor;
        }else if (anchor.equals(_DOC_ASSIGN_CREATE)){
            FDocAssign beantemp = checkRules(request,bean);
            Long secureId = (Long)request.getSession().getAttribute("secureId");
            int msgReview=0;int msgDirect=0;
            if ((secureId!=null && secureId==bean.getSecureId())){  
                        if(bean.getTitle()!=null && !bean.getTitle().equals(_BLANK)){
                            insertReview(bean);
                            bean.setSecureId(System.currentTimeMillis());
                            request.getSession().setAttribute("secureId",bean.getSecureId());
                            msgReview=1;
                        }
                        beantemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                        beantemp.setDepartmentId(bean.getDepartmentId());
                        beantemp.setId(bean.getId());
                        if (bo.insertDirect(beantemp,(int)me.getId())){
                            msgDirect=1;                
                        }
                if(msgReview==1 && msgDirect==1){
                    errors.add("alert",new ActionError("errors.review.and.direct.successfull"));                  
                        //y kien va gui cv  thanhh cong
                }else if(msgReview==1 && msgDirect!=1){
                    errors.add("alert",new ActionError("errors.send.review.successfull"));                  
                        //y kien thanh cong
                }else if(msgReview!=1 && msgDirect==1){
                    errors.add("alert",new ActionError("errors.direct.successfull"));                  
                        //Chuyen cong van thanh cong
                }else{
                    errors.add("alert",new ActionError("infor.check.add.new.error"));                  
                }
            }
            
            bean.setStatusId(storeStatusId);
            if(bean.getReaded()==0){
                target =forwardInforDoc(request,target,bean);
            }else{
                target=forwardListDoc(request,target,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND));
            }
            request.setAttribute("BClassify",new BClassify().getAll());
            request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
        } else if(anchor.equals("_OK_UPDATE_CLASSIFY")){
                new BDocssend().updateClassify(bean.getDocId(),bean.getStoreClassify());
                errors.add("alert",new ActionError("errors.classify.successfull"));                  
                target=anchor;
        }else if (anchor.equals(_PREPARED_CREATE)  ){
         
            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if (secureId!=null && secureId==bean.getSecureId()){
                    insertReview(bean);
                    errors.add("alert",new ActionError("errors.send.review.successfull"));                  
                    bean.setSecureId(System.currentTimeMillis());
                    request.getSession().setAttribute("secureId",bean.getSecureId());
                }
            }
            bean.setStatusId(storeStatusId);
            if(bean.getReaded()==1){
                target=forwardListDoc(request,target,bean,new FDocAssign());
            }else{
                target =forwardInforDoc(request,target,bean);
            }
        }else if (anchor.equals("_ASSIGN_LIST_DOC")  ){ 
            
            FDocAssign beanTemp = new FDocAssign();
            beanTemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            beanTemp.setForYouId(bean.getForYouId());
            int foryouId = bean.getForYouId();
            if (bean.getForYouId()>0){
                beanTemp.setForYouId(bean.getForYouId());
                beanTemp = new BAssignRecv().checkAsignRule(beanTemp); 
                bean.setStatusId(beanTemp.getStatusId());
                bean.setMeId(me.getId());
                bean.setForYouId(0);        
            }else{
                
                beanTemp=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                beanTemp = checkRules(request,beanTemp);
            }            
            
            if(beanTemp.getCheckDefineFileEmit()==1 && bean.getIdFiles()!=null){
                bean.setDocId(bean.getId());
                new BDocssend().updateFileDocsSend(bean);
            }
            
            if (beanTemp.getCheckDirect()==1 && beanTemp.getCheckSelectDept()==1){
                request.setAttribute("BDepartments",new BAssignSend().getAllDepartment((int)me.getId()));  
            }
            
            Long secureId = (Long)request.getSession().getAttribute("secureId");
            if (secureId!=null && secureId==bean.getChangeId()){ 
                int checkErrorReview=0,checkErrorDirect=0;
                if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                    insertReview(bean);
                   
                    if (AppConfigs.DOC_READ_EXCUTE!=1){
                        new BAssignSend().updateReadedAssignRecv(beanTemp);                       
                    }
                    checkErrorReview=1;
                }
                bean.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                bean.setStatusId(beanTemp.getStatusId());
                
                FDocAssign beanAssign = new FDocAssign();
                beanAssign.setForYouId(foryouId); 
                beanAssign.setDocId(bean.getId());
                beanAssign.setStatusId(beanTemp.getStatusId());
                beanAssign.setMeId(me.getId());
                beanAssign.setDisposeUser(null);
                beanAssign.setWorkflowId(beanTemp.getWorkflowId());
                beanAssign.setDepartmentId(bean.getDepartmentId());
                beanAssign.setForyouCreator(beanTemp.getForyouCreator());   
                beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
                beanAssign.setIndexTrailer(3);
                
                if (new BAssignSend().insertDirect(beanAssign,(int)me.getId())){               
                   // new BTrailerSend().updateReaded(bean.getId(),1,(int)me.getId(),foryouId);
                    checkErrorDirect=1;
                }
                
                if(checkErrorReview==1 || checkErrorDirect==1){
                    errors.add("alert",new ActionError("errors.direct.successfull"));   
                }else if(checkErrorReview==1 && checkErrorDirect==0){
                    errors.add("alert",new ActionError("errors.send.review.successfull"));   
                }
                
                bean.setSecureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",bean.getSecureId());
            }
              bean.setStatusId(storeStatusId);
              target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND));
            
            }else if (anchor.equals("_CREATE_REVIEW_POST") ||  anchor.equals("_CREATE_REVIEW_SELECT") || anchor.equals("_UPDATE_STATUS")){        
                
                    String  userReplyTemp ="";
                    if(anchor.equals("_UPDATE_STATUS")){
                        userReplyTemp = bean.getUserReply();
                        bean.setUserReply(null);
                        if(bean.getStoreId()>-2 && bean.getId()!=0 ){
                            new BDocssend().updateStatus(bean.getStoreId(),bean.getId());
                        }
                    }
                    
                    FDocAssign  beanTemp = new FDocAssign();
                    beanTemp.setMeId(me.getId());
                    beanTemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                    beanTemp.setForYouId(bean.getForYouId());
                    if (bean.getForYouId()>0){
                        beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
                    }else{
                        beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                    }   
                    FDocAssign beanAssign = new FDocAssign();  
                    beanAssign.setId(bean.getId());
                    beanAssign.setDocId(bean.getId());
                    beanAssign.setStatusId(beanTemp.getStatusId());
                    beanAssign.setMeId(me.getId());
                    beanAssign.setForyouCreator(beanTemp.getForyouCreator());
                    beanAssign.setDisposeUser(null);
                    beanAssign.setWorkflowId(beanTemp.getWorkflowId());
                    beanAssign.setUserReply(bean.getUserReply());
                    beanAssign.setForYouId(bean.getForYouId());
                    beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
                    beanAssign.setIndexTrailer(1);
                    if(bean.getIdFiles()!=null){  
                        bean.setDocId(bean.getId());           
                        new BDocssend().updateFileDocsSend(bean);  
                    }
                    
                    if(bean.getObServer()>0){
                        request.setAttribute("trackingObserver","");
                    }
                    
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                        Long secureId = (Long)request.getSession().getAttribute("secureId");
                        if(secureId!=null && secureId==bean.getSecureId()){
                                bean.setCreator(me.getId());
                                String members =(String)request.getSession().getAttribute("Members");
                                String[] disposeUsers=null;
                                String userReply="";
                                if(members!=null){
                                    disposeUsers=members.split(",");
                                    for(int i=0;i<disposeUsers.length;i++){
                                        if (disposeUsers[i]!=null && !disposeUsers[i].equals("")){
                                           if(!userReply.equals("")) userReply+=",";
                                            userReply+="|"+disposeUsers[i].split("#")[1]+"|";
                                        }
                                    }
                                    bean.setReviewIds(userReply);
                                }else if (bean.getUserReply()!=null && !bean.getUserReply().equals("")){                               
                                    bean.setReviewIds("|"+bean.getUserReply().split(",")[0] +"|");
                                }else if (!userReplyTemp.equals("")){
                                    bean.setReviewIds("|"+userReplyTemp.split(",")[0] +"|");
                                }
                                
                                insertReview(bean);                        
                                bean.setSecureId(System.currentTimeMillis());
                                request.getSession().setAttribute("secureId",bean.getSecureId());
                                errors.add("alert",new ActionError("errors.send.review.successfull.repply"));
                        } 
                    }
                    
                    if (anchor.equals("_CREATE_REVIEW_POST") || anchor.equals("_UPDATE_STATUS")){                                    
                        new BAssignSend().updateReadedReply(beanAssign);
                    } 
                    
                    bean.setStatusId(storeStatusId);                
                    if(bean.getWrongWay()!=0){
                        new BDocssend().delTrailerByUserRecv(me.getId(),bean.getDocId()==0?bean.getId():bean.getDocId(),bean.getForYouId());
                    }                
                    target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND));
            
        }else if (anchor.equals("_CREATE_WRONG_WAY")){
            
            FDocAssign  beanTemp = new FDocAssign();
            beanTemp.setMeId(me.getId());
            beanTemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            beanTemp.setForYouId(bean.getForYouId());
            if (bean.getForYouId()>0){
                beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
            }else{
                beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
            }   
            FDocAssign beanAssign = new FDocAssign();  
            beanAssign.setId(bean.getId());
            beanAssign.setDocId(bean.getId());
            beanAssign.setStatusId(beanTemp.getStatusId());
            beanAssign.setMeId(me.getId());
            beanAssign.setForyouCreator(beanTemp.getForyouCreator());
            beanAssign.setDisposeUser(null);
            beanAssign.setWorkflowId(beanTemp.getWorkflowId());
            beanAssign.setUserReply(bean.getUserReply());
            beanAssign.setForYouId(bean.getForYouId());
            beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
            beanAssign.setIndexTrailer(1);
            
            int userSend = new BAssignSend().getUserSendDoc(bean.getId(),me.getId());
            bean.setUserReply(+userSend + ","+0);           
            
            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if(secureId!=null && secureId==bean.getSecureId()){   
                       
                         bean.setReviewIds("|"+ userSend +"|");
                        insertReview(bean);                        
                        bean.setSecureId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getSecureId());
                        errors.add("alert",new ActionError("errors.send.review.successfull.repply"));
                } 
            }  
            
            new BAssignSend().updateReadedReply(beanAssign);
            
            bean.setStatusId(-5);
            new BDocssend().delTrailerByUserRecv(me.getId(),bean.getDocId(),bean.getForYouId());
            target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND));
        }
        
        else if (anchor.equals("_CREATE_REVIEW_TEMP")  ){   
            if(bean.getObServer()>0){
                request.setAttribute("trackingObserver","");
            }
            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
            Long secureId = (Long)request.getSession().getAttribute("secureId");
                if(secureId!=null && secureId==bean.getSecureId()){
                        bean.setCreator(me.getId());
                        bo.insertReview(bean,(int)me.getId());
                        bean.setSecureId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getSecureId());
                } 
            }
            if(BRules.getCheckViewReview()!=2){
            request.setAttribute("BDocReviews",bo.getAllReview(bean)); 
            }
            FDocssend beantemp=new FDocssend();
            beantemp.setId(bean.getId());
            request.setAttribute("BDocssends",beantemp);
            target="_CREATE_REVIEW_TEMP";
        }else if (anchor.equals(_CREATE_REVIEW)  ){   
            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
            Long secureId = (Long)request.getSession().getAttribute("secureId");
                if(secureId!=null && secureId==bean.getSecureId()){
                        bean.setCreator(me.getId());
                        bo.insertReview(bean,(int)me.getId());
                        bean.setSecureId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getSecureId());
                } 
            }
            checkRules(request,bean);
            bean.setStatusId(storeStatusId);
            target=forwardListDoc(request,target,bean,new FDocAssign());
            
        }else if (anchor.equals(_SEARCH_PAGE)){
            request.setAttribute("BDocResults",bo.getAlldocsByType(bean,(int)me.getId()));               
            target = _SEARCH_PAGE ;
            
//        }else if (anchor.equals(_SEARCH)){
//            request.setAttribute("BDocReviews",bo.getAllReview(bean));               
//            target = _SEARCH ;
//            
         }else if(anchor.equals(_SEARCH)){
            FDocssend beansend=new FDocssend();
            beansend.setViews(-1);
            beansend.setStatusId(-3);
            beansend.setDocCode(bean.getDocCode());
            beansend.setAbstracts(bean.getAbstracts());
            beansend.setDocDate(bean.getDocDate());
            beansend.setSigner(bean.getSigner());
             FDocAssign  beanrule =  (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);   
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
             request.setAttribute("BSearch",new BDocssend().getAlldocssend(beansend,(int)me.getId(),1,checkWaitSend,beanrule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
             if(beanrule.getStatusIds()!=null){
             request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,bean.me.getId(),0,beanrule.getStatusIds()));            
             }
             request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,bean.me.getId(),0,checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));    
             if(beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
             request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
             }
             request.setAttribute("BClassify",new BClassify().getAll());
             request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
             target=anchor;
        }else if (anchor.equals(_PREPARED_SAVE)){
            if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){
                bean.setCheckDocTranfer(BRules.getCheckDocTranfer());
                request.setAttribute("BdocsMove",new BTrailerSend().getDocAssignByDocId(bean,(int)bean.me.getId()));
            }
            request.setAttribute("docAssign",bean);  
            target = DOC_TRAILER_RECV_VIEW;
        }else if (anchor.equals(_SAVE)){
            FDocAssign beantemp=new FDocAssign();
            beantemp=bo.getByReviewId(bean.getReviewId());
            String picturePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_SEND_PATH + beantemp.getPathFile();          
            File files = new File(picturePath);
            if(files.exists()){
                bean.download(picturePath,URLEncoder.encode(beantemp.getFileName(),"UTF-8"),null);
            }else{
                bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
            }
        }
        if(!errors.isEmpty()) {
            saveErrors(request,errors);
       }
       return mapping.findForward(target);
    }
    public String forwardListDoc(HttpServletRequest request,String target,FDocAssign bean,FDocAssign beanRule) throws  EException,IOException,SQLException{
        int checkWaitSend = 0 ;
        
        if (request.getSession().getAttribute("checkWaitSend")!=null){
           checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
        }
        FDocAssign beanrule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
        request.setAttribute("BDocsSend",new BMain().getAmountOfStatus(1,me.getId(),bean.getObServer(),beanrule.getStatusIds()));
        request.setAttribute("BDocsSendRead",new BMain().getDocsendRead(1,me.getId(),bean.getObServer(),checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));
        if(beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
            request.setAttribute("BDocsSendWait",new BMain().getTotalWaitSend((int)bean.me.getId()));
        }
        FDocAssign beanTempS = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
        if (beanTempS.getCheckDirect()==1 && beanTempS.getCheckSelectDept()==1){
            request.setAttribute("BDepartments",new BAssignSend().getAllDepartment((int)me.getId()));  
        }
        FDocssend beanTemp=new FDocssend();
        beanTemp.setStatusId(bean.getStatusId());
        beanTemp.setType(bean.getType());
        beanTemp.setDossierId(bean.getDossierId());
        beanTemp.setViews(bean.getViews());
        beanTemp.setObServer(bean.getObServer());
       // beanTemp.setExistInRules(Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_USER_IN_RULE_DOCSSEND).toString()));
        request.setAttribute("docssend",beanTemp);
        request.setAttribute("BClassify",new BClassify().getAll());
        request.setAttribute("BDossiers",new BDossiers().getAllByUserID(bean.me.getId()));
        if(bean.getObServer()==0){
            request.setAttribute("BSearch",new BDocssend().getAlldocssend(beanTemp,(int)me.getId(),1,checkWaitSend,beanrule.getStatusIds(),Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
        }else{
            request.setAttribute("BSearch",new BDocssend().getAlldocssendObServer(beanTemp,(int)me.getId(),1,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString())));
        }
        target ="_DOCS_SEND_LIST";
    return target;
    }
   public String forwardFormAssign(HttpServletRequest request,String target,FDocAssign bean) throws  EException,IOException{
    FDocAssign beantem =new FDocAssign();
    beantem.setType(bean.getType());
    beantem.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
    beantem.setMeId(me.getId());
    beantem = (FDocAssign)request.getSession().getAttribute("BRuleDocsSend");
    beantem.setTabActive(bean.getTabActive());
    beantem.setId(bean.getId());
    request.setAttribute("docAssign",beantem);     
    if (beantem.getCheckSelectRecv()==1){
            FBeans beans = new BAssignSend().getUserByDepartmentId(beantem,0);
            request.setAttribute("BUsersDep",beans);                   
            request.setAttribute("BTranfers",beans.size()>0?(FUser)beans.get(0):null);
    }
    target="_CREATE_FROM_DOC";
  return target;
}
  
  
  
    public String forwardInforDoc(HttpServletRequest request,String target,FDocAssign bean) throws  EException,IOException{
        FDocssend beanDocSend = new FDocssend();
        beanDocSend.setId(bean.getId());
        beanDocSend.setUserId((int)me.getId());
        beanDocSend = new BDocssend().getDetail(beanDocSend,Integer.parseInt(request.getSession().getAttribute(AppConfigs.CHECK_CREATOR_DOCSSEND).toString()));
        request.setAttribute("docssend",beanDocSend);
        checkRules(request,bean);
        bean.setFileUpload(null);  
        bean.setTitle("");  
        bean.setIssue("");  
        bean.setDeadLine("");
        request.setAttribute("docAssign",bean);                         
        bean.setDocId(bean.getId());
        request.setAttribute("BClassify",new BClassify().getAll());
        request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
        FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
        if(BRules.getCheckViewReview()!=2){
            request.setAttribute("BDocReviews",new BAssignSend().getAllReview(bean));             
        }
        target = _PREPARED_CREATE;
    return target;
    }
    public void insertReview(FDocAssign bean) throws  EException,IOException{
         String pictureFolder = seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD);                   
         String picturePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_SEND_PATH + pictureFolder;                          
         (new File(picturePath)).mkdirs();
          String ext = encodeFileName(me.getId());
          bean.setFileName(_BLANK);
          bean.setPathFile(_BLANK);
          boolean haveFile = bean.getFileUpload()==null?false:(bean.getFileUpload().getFileSize()>0);
          if(haveFile){
               bean.upload(bean.getFileUpload(),picturePath + ext);
               bean.setFileName(new String(bean.getFileUpload().getFileName().getBytes(),"UTF-8"));
               bean.setPathFile(pictureFolder + ext);
          }
          bean.setCreator(me.getId());
          new BAssignSend().insertReview(bean,(int)me.getId());
          bean.setFileUpload(null);  
   }
    public FDocAssign checkRules(HttpServletRequest request,FDocAssign beantemp) throws  EException{
        beantemp.setMeId(me.getId());
        beantemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);       
        if (beantemp.getCheckDirect()==1 && beantemp.getCheckSelectDept()==1){
            request.setAttribute("BDepartments",new BAssignSend().getAllDepartment((int)me.getId()));  
        }
        return beantemp;
    }
    
    public FDocAssign setCheckRules(HttpServletRequest request,FDocAssign bean) throws  EException{
        FDocAssign beantemp = new FDocAssign();
        beantemp.setMeId(bean.getMeId());
        beantemp.setForYouId(bean.getForYouId());
        beantemp.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
      if (bean.getForYouId()>0){
         beantemp = new BAssignRecv().checkAsignRule(beantemp);
      }else{
         beantemp =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);           
      }       
        return beantemp;
    }
    
    private String validate(FDocAssign bean,String anchor,ActionErrors errors){
        if(anchor.equals(_CREATE)){
            if(bean.getUsersId()==null){
              //  errors.add("alert",new ActionError("infor.check.add.new.error"));   
            }            
        }
    return anchor;
    }
    
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    } 
   
}
