package com.action.doc.assign;


import com.action.ACore;

import com.bo.admin.doc.category.classify.BClassify;
import com.bo.admin.doc.category.dossiers.BDossiers;
import com.bo.doc.assign.BAssignRecv;
import com.bo.doc.assign.BTrailerRecv;
import com.bo.doc.docsrecv.BDocsrecv;
import com.bo.main.BMain;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.groups.FGroup;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsrecv.FDocsrecv;

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


public class AAssignRecv extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _SUCCESS;
        FDocAssign bean = (FDocAssign)form;
        FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
        bean.setRuleId(BRules.getRuleId());//moi them 
        bean.setCreator(me.getId());
        int storeStatusId=bean.getStatusId();
        bean = setCheckRules(request,bean);
        bean.setDocId(bean.getId());
        if(bean.getValue("readed")==null) bean.setReaded(0);
        if(bean.getValue("review")==null) bean.setReview(0);
        bean.setMeId(bean.me.getId());
        bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        BAssignRecv bo =new BAssignRecv();        
        target = validate(bean,anchor,errors);     
        
        if(errors.isEmpty()){
                if (anchor.equals(_DOC_ASSIGN)){                        
                    bean.setMeId(me.getId());                                    
                    request.setAttribute("docAssign",bean); 
                    if (bean.getCheckSelectRecv()==1){
                       // request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));   
                    }
                    target = _DOC_ASSIGN;
                }else if (anchor.equals("_VIEWUSERS")){               
                    request.setAttribute("docAssign",bean);     
                   // request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));               
                    target=anchor;    
                }if (anchor.equals(_DOC_GROUP_SELECT)){   
                    bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    bean.setMeId(me.getId());              
                    request.setAttribute("docAssign",bean); 
                    FBeans beans = null;
                    if (bean.getCheckSelectRecv()==1){
                        beans = bo.getAllGroupByRule(me.getId());
                        request.setAttribute("BGroups",beans);      
                        request.setAttribute("BUsersDep",bo.getUserByGroupId(bean,beans!=null &&  beans.size()>0?((FGroup)beans.get(0)).getId():0));   
                    }
                    target = _DOC_GROUP_SELECT;
                }else if (anchor.equals("_VIEW_LIST_TILE")){      
                    target=anchor;    
                }else if (anchor.equals(_DOC_DEPARMENT_SELECT)){
                    bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    bean.setMeId(me.getId());  
                    request.setAttribute("docAssign",bean); 
                    if (bean.getCheckSelectRecv()==1){
                       // request.setAttribute("BUsersDep",bo.getUserByDepartmentId(bean,0));   
                    }                     
                      target = _DOC_DEPARMENT_SELECT;
                }
                else if (anchor.equals(_PREPARE)){       
                
                    FDocsrecv beanDocRecv = new FDocsrecv();
                    beanDocRecv.setId(bean.getId());
                    beanDocRecv = new BDocsrecv().getById(beanDocRecv);            
                    FDocAssign  beanAssign = new  FDocAssign();
                    beanAssign.setId(bean.getId());
                    beanAssign.setDocCode(beanDocRecv.getDocCode());
                    beanAssign.setCreateDate(beanDocRecv.getDocDate()); 
                    beanAssign.setType(bean.getType());
                    request.setAttribute("docAssign",beanAssign);            
                    target = _PREPARE;
                        
                }else if(anchor.equals("_DETAIL")){  
                
                    FDocsrecv beanDocRecv = new FDocsrecv();      
                    beanDocRecv.setId(bean.getId());
                    beanDocRecv.setUserId((int)me.getId());
                    beanDocRecv.setForYouId(bean.getForYouId());
                    beanDocRecv.setWorkflowId(bean.getWorkflowId());
                    beanDocRecv.setObServer(bean.getObServer());
                    beanDocRecv = new BDocsrecv().getDetail(beanDocRecv);
                    beanDocRecv.setObServer(bean.getObServer());
                    beanDocRecv.setType(bean.getType());
                    if (bean.getForYouId()>0){                           
                           BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                           BRules.setForYouId(beanDocRecv.getForYouId());
                           BRules.setMeId(me.getId());
                           BRules=new BAssignRecv().checkAsignRule(BRules);
                           request.setAttribute("BRuleForYouRecv",BRules);
                           beanDocRecv.setForYouId(bean.getForYouId());
                    }
                    request.setAttribute("BDocsrecvs",beanDocRecv);
                    target =anchor;

                }else if (anchor.equals(_DOC_REVIEW)){
                    
                    FDocsrecv beanrecv =new FDocsrecv();
                    beanrecv.setId(bean.getId());
                    beanrecv.setUserId((int)me.getId());
                    beanrecv.setObServer(bean.getObServer());
                    beanrecv =new BDocsrecv().getDetail(beanrecv);
                    if(BRules.getCheckViewReview()!=2){
                        request.setAttribute("BDocReviews",bo.getAllReview(bean)); 
                    }
                    
                    if (bean.getForYouId()>0){
                           BRules.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                           BRules.setForYouId(bean.getForYouId());
                           BRules.setMeId(me.getId());
                           BRules=new BAssignRecv().checkAsignRule(BRules);
                           request.setAttribute("BRuleForYouRecv",BRules);
                           beanrecv.setForYouId(bean.getForYouId());
                    }
                    beanrecv.setObServer(bean.getObServer());
                    request.setAttribute("BDocsrecvs",beanrecv);
                    target ="_CREATE_REVIEW_TEMP" ;
                }
                    else if (anchor.equals(_SHOW)){ 
                        FBeans beans = new FBeans();
                        FDocAssign beanAssign = new FDocAssign();
                        beanAssign = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                        beanAssign.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                        beanAssign.setRuleId(beanAssign.getId());                      
                        request.setAttribute("BDepartments",beans);     
                        request.setAttribute("BTranfers",beans.size()>0?(FDepartment)beans.get(0):null);                           
                        target = _SHOW;
                        
                    }else if (anchor.equals("_SHOW_GROUP")){
                        FBeans beans = new FBeans();
                        beans = bo.getAllGroupByRuleAndTranfer(me.getId(),AppConfigs.DOCSRECV_WORKFLOWID);
                        request.setAttribute("BGroups",beans);  
                        request.setAttribute("BTranfers",beans.size()>0?(FGroup)beans.get(0):null);                           
                        target = "_SHOW_GROUP";
                        
                    } else if (anchor.equals("_SHOW_ALL")){ 
                        FDocAssign beantem = null;
                        if (bean.getForYouId()>0){                     
                            beantem = new FDocAssign();
                            beantem.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
                            beantem.setForYouId(bean.getForYouId());                                     
                            beantem = new BAssignRecv().checkAsignRule(beantem);
                            request.setAttribute("BRuleForYou",beantem);                         
                        }else{              
                             beantem = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);                
                        }
                        
                        beantem.setType(bean.getType());
                        beantem.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                        beantem.setMeId(bean.getForYouId()>0?beantem.getForyouCreator():me.getId());
                        beantem.setId(bean.getId());
                        beantem.setDocId(bean.getId());
                        beantem.setDepartmentId(0);
                        beantem.setCheckShowTransfer(1);
                        beantem.setMembers(bean.getMembers());
                        request.setAttribute("docAssign",beantem);     
                        FBeans beansDep=bo.getAllRecordByRule(beantem,bean.getMeId());
                        request.setAttribute("BDepartments",beansDep);  
                        FBeans beansGroup=new BAssignRecv().getAllGroupByRule(me.getId());
                        request.setAttribute("BGroups",beansGroup);
                        FBeans beansU = new BAssignRecv().getUserByDepartmentId(beantem,bean.getDepartmentId(),bean.getGroupId());
                        request.setAttribute("BUsersDep",beansU);                   
                         request.setAttribute("BTranfers",beansU.size()>0?(FUser)beansU.get(0):null);
                        target =anchor;
                    }else if (anchor.equals("_CREATE_FROM_DOC")){
                        if(bean.getId()==0){
                            request.getSession().setAttribute("BStorerecv",bean);
                        }else{
                            if (bo.insert(bean,(int)me.getId())){                 
                                errors.add("alert",new ActionError("alert.insert.doc.successfull"));                
                            }
                        }
                        target=forwardFormAssign(request,target,bean);
                        request.setAttribute("CreateDoc",1); 
                        
                  } else if (anchor.equals(_CREATE)){
                          
                        request.getSession().setAttribute("Members",bean.getMembers());
                        FDocAssign beantemp = new FDocAssign();
                        beantemp.setMeId(bean.me.getId());
                        beantemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                        
                        if (bean.getForYouId()>0){
                            beantemp.setForYouId(bean.getForYouId());                           
                            beantemp = new BAssignRecv().checkAsignRule(beantemp); 
                            bean.setStatusId(beantemp.getStatusId());
                            bean.setMeId(me.getId());
                            bean.setForyouCreator(beantemp.getForyouCreator());                                                   
                        }else {
                            beantemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);  
                        }                        
                       
                        FDocAssign beanAssign = new FDocAssign();
                        beanAssign.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                        beanAssign.setMeId(me.getId());
                        beanAssign.setDocId(bean.getId());
                        beanAssign.setId(bean.getId());
                        beanAssign.setStatusId(beantemp.getStatusId());
                        beanAssign.setForyouCreator(beantemp.getForyouCreator()); 
                        beanAssign.setMembers(bean.getMembers()); 
                        beanAssign.setDepartmentId(bean.getDepartmentId());
                        beanAssign.setForYouId(bean.getForYouId());
                        beanAssign.setCurrentDateLocal(bean.dateToString(bean.getCurrentDate()));
                        beanAssign.setIndexTrailer(2);                      
                       
                        if (bo.insert(bean,(int)me.getId())){                              
                             errors.add("alert",new ActionError("alert.insert.doc.successfull"));                
                        }else{
                             errors.add("alert",new ActionError("errors.direct.error"));     
                        }
                        if(bean.getReview()==1){
                            request.setAttribute("docAssign",bean);
                        }else{
                            target=forwardFormAssign(request,target,bean);
                        }
                    
                }else if (anchor.equals(_DOC_ASSIGN_CREATE)){
                
                    FDocAssign beantemp = new FDocAssign();
                    if (bean.getForYouId()>0){
                        beantemp.setForYouId(bean.getForYouId());
                        beantemp = new BAssignRecv().checkAsignRule(beantemp); 
                        bean.setStatusId(beantemp.getStatusId()); 
                    }else {
                        beantemp = checkRules(request,bean);
                    }
                    
                    Long secureId = (Long)request.getSession().getAttribute("secureId");     
                    int msgReview=0;int msgDirect=0;
                    if (secureId!=null && secureId==bean.getSecureId()){                
                            if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                                insertReview(bean);
                                bean.setSecureId(System.currentTimeMillis());
                                request.getSession().setAttribute("secureId",bean.getSecureId());
                                msgReview=1;
                            }
                            beantemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                            beantemp.setDepartmentId(bean.getDepartmentId());
                            beantemp.setId(bean.getId());
                            if (new BAssignRecv().insertDirect(bean) ){
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
                
                    if(bean.getReaded()==0){
                        target =forwardInforDoc(request,target,bean);
                    }else{
                        
                        target=forwardListDoc(request,target,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV));
                    }
                }else if (anchor.equals("_ASSIGN_LIST_DOC")  ){
                                      
                    FDocAssign beanTemp = new FDocAssign();
                    FDocAssign beanRules = new FDocAssign();
                    beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    int foryouId = bean.getForYouId();
                    if (bean.getForYouId()>0){
                        beanTemp.setForYouId(bean.getForYouId());
                        beanTemp = new BAssignRecv().checkAsignRule(beanTemp); 
                        bean.setStatusId(beanTemp.getStatusId());
                        bean.setMeId(me.getId());
                        bean.setForYouId(0);        
                    }else{
                        beanTemp=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                        beanTemp = checkRules(request,beanTemp);
                    }
                    
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                    if (secureId!=null && secureId==bean.getChangeId()){                
                        int checkErrorReview=0,checkErrorDirect=0;
                        if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                            insertReview(bean);
                            if (AppConfigs.DOC_READ_EXCUTE!=1){
                                beanTemp.setDisposeUser(null);
                                new BAssignRecv().updateReadedAssignRecv(beanTemp);                       
                            }
                            checkErrorReview=1;
                        }
                        
                        bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
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
                        
                       if (new BAssignRecv().insertDirect(beanAssign)){        //                         
                           checkErrorDirect=1;
                       }
                        
                        if(checkErrorReview==1 || checkErrorDirect==1){
                            errors.add("alert",new ActionError("errors.direct.successfull"));   
                        }else if(checkErrorReview==1 && checkErrorDirect==0){
                            errors.add("alert",new ActionError("errors.send.review.successfull"));   
                        }
                        
                        bean.setChangeId(System.currentTimeMillis());
                        request.getSession().setAttribute("secureId",bean.getChangeId());
                    }    
                    bean.setStatusId(storeStatusId);
                    target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV));
                   
                    
                }else if (anchor.equals("_CREATE_REVIEW_POST") ||  anchor.equals("_CREATE_REVIEW_SELECT") || anchor.equals("_UPDATE_STATUS")){        
                  
                  
                    String userReplyTem =""; 
                    if(anchor.equals("_UPDATE_STATUS")){
                        userReplyTem = bean.getUserReply();
                        bean.setUserReply(null);
                        if(bean.getStoreId()>-2 && bean.getId()!=0 ){
                            if (new BDocsrecv().updateStatus(bean.getStoreId(),bean.getId()));
                        }
                    }
                                                        
                    FDocAssign  beanTemp = new FDocAssign();
                    beanTemp.setMeId(me.getId());
                    beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    beanTemp.setForYouId(bean.getForYouId());
                    if (bean.getForYouId()>0){
                        beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
                    }else{
                        beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                    }
                    
                    FDocAssign beanAssign = new FDocAssign();  
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
                    
                     if(bean.getObServer()>0){
                         request.setAttribute("trackingObserver","");
                     }    
                    
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                        
                        Long secureId = (Long)request.getSession().getAttribute("secureId");
                        if (secureId!=null && secureId==bean.getSecureId()){
                                bean.setCreator(me.getId());
                                String members =(String)request.getSession().getAttribute("Members");
                                String[] disposeUsers=null;
                                String userReply="";
                                if(members!=null){
                                    disposeUsers=members.split(",");
                                    for(int i=0;i<disposeUsers.length;i++){
                                       if (disposeUsers[i]!=null && !disposeUsers[i].equals("")){
                                          if(!userReply.equals(""))  userReply+=",";
                                            userReply+="|"+disposeUsers[i].split("#")[1]+"|";
                                       }
                                    }
                                    bean.setReviewIds(userReply);
                                }else if (bean.getUserReply()!=null && !bean.getUserReply().equals("")) {  
                                    bean.setReviewIds("|"+bean.getUserReply().split(",")[0]+"|");
                                }else if (!userReplyTem.equals("")){
                                    bean.setReviewIds("|"+userReplyTem.split(",")[0]+"|");
                                }                                
                                insertReview(bean);
                                bean.setSecureId(System.currentTimeMillis());
                                request.getSession().setAttribute("secureId",bean.getSecureId());
                                errors.add("alert",new ActionError("errors.send.review.successfull.repply"));
                        } 
                        
                    }
                    if (anchor.equals("_CREATE_REVIEW_POST") || anchor.equals("_UPDATE_STATUS")){                   
                        
                        new BAssignRecv().updateReadedReply(beanAssign);// 
                    }
                         //duong van duc //xoa cong van gui nham
                     if(bean.getWrongWay()!=0){
                         new BDocsrecv().delTrailerByUserRecv(me.getId(),bean.getDocId()==0?bean.getId():bean.getDocId(),bean.getForYouId());
                     }
                    bean.setStatusId(storeStatusId); 
                    target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV));
                    
                }else if (anchor.equals("_CREATE_WRONG_WAY")){
            
                    FDocAssign  beanTemp = new FDocAssign();
                    beanTemp.setMeId(me.getId());
                    beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    beanTemp.setForYouId(bean.getForYouId());
                    if (bean.getForYouId()>0){
                        beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
                    }else{
                        beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                    }
                    
                    FDocAssign beanAssign = new FDocAssign();  
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
                    
                    int userSend = new BAssignRecv().getUserRecvDoc(bean.getId(),me.getId());
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
                    
                    new BAssignRecv().updateReadedReply(beanAssign);
                    
                    bean.setStatusId(-5);
                    new BDocsrecv().delTrailerByUserRecv(me.getId(),bean.getId(),bean.getForYouId());
                    target=forwardListDoc(request,anchor,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND));
                }   
                
                else if (anchor.equals("_CREATE_REVIEW_TEMP")  ){   
                    if(bean.getObServer()>0){
                        request.setAttribute("trackingObserver","");
                    }
                   
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                        if (secureId!=null && secureId==bean.getSecureId()){
                                bean.setCreator(me.getId());
                                bo.insertReview(bean,(int)me.getId());
                                bean.setSecureId(System.currentTimeMillis());
                                request.getSession().setAttribute("secureId",bean.getSecureId());
                        } 
                    }           
                    if(BRules.getCheckViewReview()!=2){
                        request.setAttribute("BDocReviews",bo.getAllReview(bean)); 
                    }
                    FDocsrecv beantemp=new FDocsrecv();
                    beantemp.setId(bean.getId());
                    request.setAttribute("BDocsrecvs",beantemp);
                    target="_CREATE_REVIEW_TEMP";
                    
                } else if (anchor.equals(_CREATE_REVIEW)){
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                        if (secureId!=null && secureId==bean.getSecureId()){
                                bean.setCreator(me.getId());
                                bo.insertReview(bean,(int)me.getId());
                                bean.setSecureId(System.currentTimeMillis());
                                request.getSession().setAttribute("secureId",bean.getSecureId());
                        } 
                    }
                    
                    FDocAssign  beanTemp = new FDocAssign();
                    beanTemp.setMeId(me.getId());
                    beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                    if (bean.getForYouId()>0){
                        beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
                    }else{
                        beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                    }
                    checkRules(request,beanTemp);                              
                    target=forwardListDoc(request,target,bean,(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV));
                    
                } else if(anchor.equals("_OK_UPDATE_CLASSIFY")){
                        new BDocsrecv().updateClassify(bean.getDocId(),bean.getStoreClassify());
                    errors.add("alert",new ActionError("errors.classify.successfull"));                  
                        target=anchor;
                } else if(anchor.equals(_PREPARED_CREATE)){
                    
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
                    FDocsrecv beanRecv = new FDocsrecv();
                    if(bean.getReaded()==1){
                        FDocAssign  beanTemp = new FDocAssign();
                        beanTemp.setMeId(me.getId());
                        beanTemp.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
                        if (bean.getForYouId()>0){
                            beanTemp = new BAssignRecv().checkAsignRule(beanTemp);
                        }else{
                            beanTemp = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
                        }
                        target=forwardListDoc(request,target,bean,new FDocAssign());
                    }else{
                        target =forwardInforDoc(request,target,bean);
                    }
                 }else if(anchor.equals(_SEARCH)){
                     
                     FDocsrecv beanrecv=new FDocsrecv();
                     beanrecv.setViews(-1);
                     beanrecv.setStatusId(-3);
                     beanrecv.setDocCode(bean.getDocCode());
                     beanrecv.setAbstracts(bean.getAbstracts());
                     beanrecv.setDocDate(bean.getDocDate());
                     beanrecv.setFromVnName(bean.getFromVnName());
                     FDocAssign beanrule = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV); 
                     int checkWait = 0 ;
                     if (request.getSession().getAttribute("checkWait")!=null){
                       checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                     }
                     request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecv(beanrecv,(int)me.getId(),beanrule.getStatusIds(),checkWait));
                     request.setAttribute("BClassify",new BClassify().getAll());
                     request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
                     request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanrule,0));
                    
                     request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanrule,0,checkWait==1?1:0,(beanrule.getStatusIds()!=null && !beanrule.getStatusIds().equals(""))?beanrule.getStatusIds():"1110"));  
                     if (beanrule.getCheckNotIncharge()>0 || beanrule.getCheckUnReaded()>0){
                         request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
                     }                  
                     target=anchor;
                }else if (anchor.equals(_PREPARED_SAVE)){     
                
                    if (BRules.getCheckDocTranfer()!=0 || bean.getObServer()==1){
                        bean.setCheckDocTranfer(BRules.getCheckDocTranfer());
                        request.setAttribute("BdocsMove",new BTrailerRecv().getDocRecvByDocId(bean,(int)bean.me.getId())); 
                    }  
                    target = DOC_TRAILER_RECV_VIEW;      
                    
                }else if (anchor.equals(_SAVE)){
                    
                    FDocAssign beantemp=new FDocAssign();
                    beantemp=bo.getByReviewId(bean.getReviewId());
                    String picturePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_RECV_PATH + beantemp.getPathFile();          
                    File files = new File(picturePath);
                    if(files.exists()){
                        bean.download(picturePath,URLEncoder.encode(beantemp.getFileName(),"UTF-8"),null);
                        
                    }else{
                        bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
                    }
                    
                }
        }
        
       if(!errors.isEmpty()) {
            saveErrors(request,errors);
        }
       
       return mapping.findForward(target);
    }
       public String forwardFormAssign(HttpServletRequest request,String target,FDocAssign bean) throws  EException,IOException{
        request.setAttribute("docAssign",bean); 
        bean.setCheckShowTransfer(1);
        if (bean.getCheckSelectRecv()==1){
              //  request.setAttribute("BUsersDep",new BAssignRecv().getUserByDepartmentId(bean,0));
        }
        target="_CREATE_FROM_DOC";
       return target;
       }
       public String forwardInforDoc(HttpServletRequest request,String target,FDocAssign bean) throws  EException,IOException{
           FDocsrecv beanDocrecv = new FDocsrecv();
           beanDocrecv.setId(bean.getId());
           beanDocrecv.setUserId((int)me.getId());
           beanDocrecv = new BDocsrecv().getDetail(beanDocrecv);
           request.setAttribute("docsrecv",beanDocrecv);
           if (bean.getCheckDirect()==1 && bean.getCheckSelectDept()==1){
               request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment(me.getId()));  
           }
           bean.setFileUpload(null);  
           bean.setTitle("");  
           bean.setIssue("");
           bean.setDeadLine("");
           request.setAttribute("docAssign",bean);                         
           bean.setDocId(bean.getId());
           request.setAttribute("BClassify",new BClassify().getAll());
           request.setAttribute("BDossiers",new BDossiers().getAllByUserID((int)me.getId()));
           FDocAssign BRules=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
           if(BRules.getCheckViewReview()!=2){
                request.setAttribute("BDocReviews",new BAssignRecv().getAllReview(bean));             
           }
           target = _PREPARED_CREATE;
       return target;
       }
       public String forwardListDoc(HttpServletRequest request,String target,FDocAssign bean,FDocAssign beanRule) throws  EException,IOException,SQLException{
                 
                 beanRule.setMeId(bean.getMeId());
                 beanRule.setWorkflowId(bean.getWorkflowId());
                 int checkWait = 0 ;
                 if (request.getSession().getAttribute("checkWait")!=null){
                    checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                 }
                 request.setAttribute("BDocsRecv",new BMain().getDocRecvByStatus(beanRule,bean.getObServer()));//khong theo doi
                 request.setAttribute("BDocsRecvRead",new BMain().getDocRecvByRead(beanRule,bean.getObServer(),checkWait,(beanRule.getStatusIds()!=null && !beanRule.getStatusIds().equals(""))?beanRule.getStatusIds():"1110"));//0:khong phai theo doi         
                         
                if (beanRule.getCheckNotIncharge()>0 || beanRule.getCheckUnReaded()>0){
                     request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
                 }
                 if (beanRule.getCheckDirect()==1 && beanRule.getCheckSelectDept()==1){
                     request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment((int)me.getId()));  
                 }
                 beanRule.setDocId(bean.getId());
                 FDocsrecv beanTemp =new FDocsrecv();
                 beanTemp.setStatusId(bean.getStatusId());
                 beanTemp.setDossierId(bean.getDossierId());
                 beanTemp.setViews(bean.getViews());
                 beanTemp.setObServer(bean.getObServer());
                 request.setAttribute("docAssign",beanRule); 
                 request.setAttribute("docsrecv",beanTemp);    
                 if(bean.getObServer()==0){
                     
                     request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecv(beanTemp,(int)me.getId(),beanRule.getStatusIds(),checkWait));
                 }else{
                     request.setAttribute("BSearch",new BDocsrecv().getAllDocsrecvOBServer(beanTemp,(int)me.getId(),checkWait));           
                 }
                 request.setAttribute("BClassify",new BClassify().getAll());
                 request.setAttribute("BDossiers",new BDossiers().getAllByUserID(bean.me.getId()));
                 target="_DOCS_RECV_LIST";
                 return target;
             }

       
      public void insertReview(FDocAssign bean) throws  EException,IOException{   
               String pictureFolder = seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD);                       
               String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_RECV_PATH + pictureFolder;                          
              (new File(dirs)).mkdirs();  
               boolean haveFile=false;
               if(bean.getFileUpload()!=null){
                   haveFile = bean.getFileUpload().getFileSize()>0;
               }
               if(haveFile){
                   String ext = encodeFileName(bean.me.getId());                                
                   bean.upload(bean.getFileUpload(), dirs + ext);
                   bean.setFileName(new String((bean.getFileUpload().getFileName().getBytes()),"UTF-8"));
                   bean.setPathFile(pictureFolder + ext);
               }
               bean.setCreator(me.getId());
               new BAssignRecv().insertReview(bean,(int)me.getId());
               bean.setFileUpload(null);           
   }
    private String validate(FDocAssign bean,String anchor,ActionErrors errors){
//        if(anchor.equals(_CREATE)){
//            if(bean.getUsersId()==null){
//                errors.add("alert",new ActionError("infor.check.add.new.error"));   
//            }            
//        }
         return anchor;
    }
       public FDocAssign checkRules(HttpServletRequest request, FDocAssign beantemp) throws  EException{          
           if (beantemp.getCheckDirect()==1 && beantemp.getCheckSelectDept()==1){
               request.setAttribute("BDepartments",new BAssignRecv().getAllDepartment((int)me.getId()));  
           }
           return beantemp;
       }
       
       public FDocAssign setCheckRules(HttpServletRequest request,FDocAssign bean) throws  EException{
           FDocAssign beantemp =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);           
           bean.setCheckDirect(beantemp.getCheckDirect());      
           bean.setCheckReview(beantemp.getCheckReview());      
           bean.setCheckSelectRecv(beantemp.getCheckSelectRecv());      
           bean.setCheckActive(beantemp.getCheckActive());      
           bean.setStatusId(beantemp.getStatusId());  
           bean.setCheckSelectDept(beantemp.getCheckSelectDept());
           bean.setCheckStore(beantemp.getCheckStore());
           bean.setCheckViewReview(beantemp.getCheckViewReview());
           bean.setCheckAssign(beantemp.getCheckAssign());
           return bean;
       }
       
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    } 
    
   }
