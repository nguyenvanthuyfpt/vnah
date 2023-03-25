package com.action.require.requires;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.require.category.BCatRequire;
import com.bo.admin.require.rm_status.BRmStatus;
import com.bo.require.requires.BRequires;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.require.requires.FRequire;

import com.inf.doc.IKeyDoc;

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


public class ARequires extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FRequire bean = (FRequire)form;
        bean.setDateCreate(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setCreator(me.getId());       
        BRequires bo = new BRequires();
        if (bean.me.getId()==0) return null;
        if(bean.getValue("obServer")==null) bean.setObServer(0);
      
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();      
        target = validate(bean,anchor,errors);                    
        if(errors.isEmpty()){           
              if(anchor.equals("_SHOW_REQUIRE")){
            
                request.setAttribute("frmRequire",bean);  
                request.setAttribute("BRequire",bo.getById(bean)); 
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                if (beanrule.getDirect()==1 && beanrule.getSelectDep()==1){
                    request.setAttribute("BDepartments",new BRequires().getAllDepartmentPri(me.getId(),0));  
                } 
                
                bean.setCheckReview(beanrule.getComment());
                request.setAttribute("BRmReviews",bo.getAllReview(bean));   
                bean.setSurcureId(System.currentTimeMillis());
                request.getSession().setAttribute("secureId",bean.getSurcureId());
                target = "_SHOW_REQUIRE";
                    
            }  else if (anchor.equals(_CREATE)){
            
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                bean.setActive(1);
                bean.setDepartmentId((int)me.getDepartmentID());
                insertRm(request,bean,bo,errors);
                
                request.setAttribute("BRmStatuses",new BRmStatus().getAllRmStatus());
                request.setAttribute("BCatRequires",new BCatRequire().getAllCatRequire());
                // Tinh so luong trang thai xu ly yeu cau
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }
                target=_CREATE;
                  
            }else if (anchor.equals("_REQUIRE_LIST")){                            
                if (bean.getObServer()>0){
                    request.setAttribute("BRequires",bo.getAllRequiresObserver(bean));
                }else {
                    request.setAttribute("BRequires",bo.getAllRequires(bean));    
                }
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId())); 
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    if (bean.getObServer()>0){
                        request.setAttribute("BExcuteStatus",new BRequires().getRmByStatusObserver(bean));                  
                    }else{
                        request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                    }
                }
                request.setAttribute("frmRequireEmp",bean); 
                request.setAttribute("frmRequire",bean); 
                target = "_REQUIRE_LIST";
                
            }else if (anchor.equals("_RM_ASSIGN_CREATE")){
            
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if(secureId!=null && secureId==bean.getSurcureId()){
                    FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES); 
                    bean.setRmStatus(beanRuleTrailer.getStatus_id());
                    insertRm(request,bean,bo,errors);
                    bo.insertDirect(bean);
                    
                    bean.setRmRuleId(beanRuleTrailer.getRuleId());                 
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                        bo.insertReview(bean,(int)me.getId());
                        bean.setReviewIds(null);
                        bean.setMembers(null);
                    }
                }
                
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId())); 
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }
                request.setAttribute("BRequires",bo.getAllRequires(bean)); 
                target = "_REQUIRE_LIST";
                
            }else if (anchor.equals("_TRAILER_CREATE")){
                
                    FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES); 
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                    if(secureId!=null && secureId==bean.getSurcureId()){
                        
                        bean.setRmRuleId(beanRuleTrailer.getRuleId());                 
                        if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                            bo.insertReview(bean,(int)me.getId());
                        }
                        
                        bo.insertDirect(bean);
                        bean.setReviewIds(null);
                        bean.setMembers(null);
                    } 
                    
                    request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                    request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));                     
                    if(beanRuleTrailer.getStatusIdsNameTemp()!=null){
                        bean.setRmStatusIds(beanRuleTrailer.getStatusIdsNameTemp());
                        request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                    }      
                    bean.setRmStatus(IKeyDoc.RM_STATUS_UNREAD);
                    request.setAttribute("BRequires",bo.getAllRequires(bean));
                    target = "_REQUIRE_LIST";                
            }
            else if (anchor.equals("_SELECT_EMP")){
                
                bean.setDepartmentId(0);
                bean.setGroupId(0);
                bean.setMembers("");
                FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);   
                beanRuleTrailer.setCreator(me.getId());
                FBeans beansDep= bo.getAllRecordByRule(beanRuleTrailer);
                request.setAttribute("BDepartments",beansDep);
                request.setAttribute("BGroups",bo.getAllGroupByRule(beanRuleTrailer));                
                FBeans beansU =  bo.getUserByDepartmentId(beanRuleTrailer,0,0);
                request.setAttribute("BUsersDep",beansU);               
                request.setAttribute("frmRequireEmp",bean);  
                target = "_SELECT_EMP";
                
            }else if (anchor.equals("_SHOW_ALL")){
                
                FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);   
                beanRuleTrailer.setCreator(me.getId());
                beanRuleTrailer.setMembers(bean.getMembers());               
                FBeans beansU = bo.getUserByDepartmentId(beanRuleTrailer,bean.getDepartmentId(),bean.getGroupId());
                request.setAttribute("BUsersDep",beansU);     
                request.setAttribute("frmRequireEmp",bean);  
                target = "_SHOW_ALL";
                
            }else if (anchor.equals(_DELETE)){
                bo.delete(bean);
                FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);   
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));                     
                if(beanRuleTrailer.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanRuleTrailer.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }                      
                request.setAttribute("BRequires",bo.getAllRequires(bean));
                target = "_REQUIRE_LIST";   
            }else if (anchor.equals("_PREPARED_CREATE_AND_CREATE_RM")){
            
                String members =(String)request.getSession().getAttribute("Members");
                bean.setMembers(members);
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                if(secureId!=null && secureId==bean.getSurcureId()){
                    insertRm(request,bean,bo,errors);    
                    bo.insert(bean,(int)me.getId());                    
                    errors.add("alert",new ActionError("errors.direct.successfull"));   
                }
                
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId())); 
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }
                request.setAttribute("BRequires",bo.getAllRequires(bean));
                target = "_REQUIRE_LIST";
                
            }else if (anchor.equals("_PREPARED_CREATE_TRAILER")){
                
                String members =(String)request.getSession().getAttribute("Members");
                bean.setMembers(members);
                Long secureId = (Long)request.getSession().getAttribute("secureId");
                
                if(secureId!=null && secureId==bean.getSurcureId()){  
                    FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);   
                    bean.setRmStatus(beanRuleTrailer.getStatus_id());
                    bo.insert(bean,(int)me.getId());     
                    
                    bean.setRmRuleId(beanRuleTrailer.getRuleId());                 
                    if(bean.getTitle()!=null && !bean.getTitle().equals("")){
                        bo.insertReview(bean,(int)me.getId());
                    }                                                         
                    errors.add("alert",new ActionError("errors.direct.successfull"));   
                }
                
                FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
                
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }
                
                bean.setRmStatus(IKeyDoc.RM_STATUS_UNREAD);
                request.setAttribute("BRequires",bo.getAllRequires(bean));
                target = "_REQUIRE_LIST";
            }
            else if (anchor.equals("_CREATE_FROM_DOC")){
            
                request.getSession().setAttribute("Members",bean.getMembers());                 
                target = "_CREATE_FROM_DOC";
                
            }else if (anchor.equals("_UPDATE_STORE")){
                FRequireTrailer beanRuleTrailer = (FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES); 
                bo.updateStatusStore(bean);
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));                     
                if(beanRuleTrailer.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanRuleTrailer.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }  
                
              
                if(bean.getTitle()!=null && !bean.getTitle().equals("")){  
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                    if (secureId!=null && secureId==bean.getSecureId()){                 
                            bean.setRmRuleId(beanRuleTrailer.getRuleId());
                            bean.setReviewIds("|"+ bean.getUserReply() +"|");
                            bo.insertReview(bean,(int)me.getId());                            
                            bo.updateReadedReply(bean);// 
                            
                            bean.setSecureId(System.currentTimeMillis());
                            request.getSession().setAttribute("secureId",bean.getSecureId());
                            errors.add("alert",new ActionError("errors.send.review.successfull.repply"));
                    } 
                    
                }
                
                
                request.setAttribute("BRequires",bo.getAllRequires(bean));
                target = "_UPDATE_STORE";
            }
            else if (anchor.equals(_PREPARED_SAVE)){    
            
                request.setAttribute("BRmTrailer",bo.getRMRecvByRmId(bean,(int)bean.me.getId())); 
                request.setAttribute("BRmReviews",bo.getAllReview(bean));                 
                target = DOC_TRAILER_RECV_VIEW;      
                    
            }else if (anchor.equals("_PREPARED_REVIEW")){ 
            
                request.setAttribute("BRequire",bo.getById(bean)); 
                request.setAttribute("BRmReviews",bo.getAllReview(bean)); 
                target = "_PREPARED_REVIEW";      
           
            }else if (anchor.equals("_CREATE_REVIEW_SELECT")){
                
                FRequireTrailer beanrule =(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES); 
                if(bean.getTitle()!=null && !bean.getTitle().equals("")){  
                    Long secureId = (Long)request.getSession().getAttribute("secureId");
                    if (secureId!=null && secureId==bean.getSecureId()){                 
                            bean.setRmRuleId(beanrule.getRuleId());
                            bean.setReviewIds("|"+ bean.getUserReply() +"|");
                            bo.insertReview(bean,(int)me.getId());                            
                            bo.updateReadedReply(bean);// 
                            
                            bean.setSecureId(System.currentTimeMillis());
                            request.getSession().setAttribute("secureId",bean.getSecureId());
                            errors.add("alert",new ActionError("errors.send.review.successfull.repply"));
                    } 
                    
                }
                           
                request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId())); 
                if(beanrule.getStatusIdsNameTemp()!=null){
                    bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                    request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
                }
                
                bean.setRmStatus(IKeyDoc.RM_STATUS_UNREAD);
                request.setAttribute("BRequires",bo.getAllRequires(bean));  
                target = "_REQUIRE_LIST"; 
            }else if (anchor.equals("_DETAIL")){
                request.setAttribute("BRequire",bo.getById(bean)); 
                target ="_DETAIL";
            }
            
        }else {
        
            request.setAttribute("BRmStatuses",new BRmStatus().getAllRmStatus());
            request.setAttribute("BCatRequires",new BCatRequire().getAllCatRequire());
            
            FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);            
            if (beanrule.getDirect()==1 && beanrule.getSelectDep()==1){
                    request.setAttribute("BDepartments",new BRequires().getAllDepartmentPri(me.getId(),0));  
            }    
            
            // Tinh so luong trang thai xu ly yeu cau
            request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
            request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
            if(beanrule.getStatusIdsNameTemp()!=null){
                bean.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(bean));                  
            }
            target = "_PREPARE_EDIT";
        }
        if(!errors.isEmpty())
        saveErrors(request,errors);   
        
        return mapping.findForward(target);
    }
    
    
    public void insertRm(HttpServletRequest request,FRequire bean,BRequires bo,ActionErrors errors) throws  EException,SQLException{
        FRequireTrailer BRules =(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);       
        BRules.setCreator((int)me.getId());
        Long secureId = (Long)request.getSession().getAttribute("secureId");
        if(secureId!=null && secureId==bean.getSurcureId()){
                bean.setRmStatus(AppConfigs.RM_STATUS_NEW);
                if(bo.AddNew(bean)){
                bean.setRmId(bo.getTopId(bean.getCreator()).getRmId());
                bean.setRmStatus(BRules.getStatus_id());
                bean.setUserSend(me.getId());
                bo.insertMe(bean);  
                    bean=bo.getById(bean);
                    bean.setSurcureId(System.currentTimeMillis());
                    request.getSession().setAttribute("secureId",bean.getSurcureId());
                }else{                                 
                    errors.add("alert",new ActionError("errors.insert.code.exits"));   
                }
                
                request.setAttribute("frmRequire",bean);
        }else{           
            bean=bo.getTopId((int)me.getId());
            request.setAttribute("frmRequire",bo.getById(bean));    
        }      
    }
    
    public void prepareView(HttpServletRequest request,FRequire bean,BRequires bo) throws  EException,SQLException{
        
              
        FDepartment beanDept =new FDepartment();
        beanDept.setId((int)me.getDepartmentID());
        beanDept=new BDepartments().getRecordByID(beanDept);
        bean.setCode("YC"+"/"+me.getId()+"/"+ beanDept.getCode().toUpperCase());       
        bean.setSurcureId(System.currentTimeMillis());
        bean.setDateline(bean.dateToString(bean.getCurrentSqlDate()));
        bean.setDatetimto(bean.getDateline());
        bean.setDatetimFrom(bean.getDateline());
        request.getSession().setAttribute("secureId",bean.getSurcureId());
        request.setAttribute("frmRequire",bean);
    }
    private String validate(FRequire bean,String anchor,ActionErrors errors){
            
            if (anchor.equals("_RM_ASSIGN_CREATE") || anchor.equals("_PREPARED_CREATE_AND_CREATE_RM")){
                if (bean.getCode()==null || bean.getCode().equals("")){
                    errors.add("alert",new ActionError("errors.requires.code.caption"));   
                }else if (bean.getCateId()==0){
                    errors.add("alert",new ActionError("errors.requires.cateId.caption"));
                }else if (bean.getName()==null || bean.getName().equals("")){
                    errors.add("alert",new ActionError("errors.requires.name.caption"));
                }else if (bean.getDatetimFrom()!=null && !bean.getDatetimFrom().equals("") && !bean.isDate(bean.getDatetimFrom())){
                    errors.add("alert",new ActionError("errors.requires.datetimfrom.caption"));
                }else if (bean.getDatetimto()!=null && !bean.getDatetimto().equals("") && !bean.isDate(bean.getDatetimto())){
                    errors.add("alert",new ActionError("errors.requires.datetimto.caption"));
                }
            }
            return anchor;
    }     
  
}