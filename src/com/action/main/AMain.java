package com.action.main;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.doc.category.thePen.BThePen;
import com.bo.admin.mail.BRegister;
import com.bo.admin.menu.BMenu;
import com.bo.admin.require.category.BCatRequire;
import com.bo.admin.require.rm_status.BRmStatus;
import com.bo.broadcast.BBroadcast;
import com.bo.calendar.agenda.BCalendar;
import com.bo.doc.assign.BAssignRecv;
import com.bo.mail.BLoginMail;
import com.bo.mail.BReceiveMail;
import com.bo.mail.mailFilter.BMailFilter;
import com.bo.main.BMain;
import com.bo.messages.create.BCreate;
import com.bo.mycontact.BMycontact;
import com.bo.require.requires.BRequires;
import com.bo.servey.BServey;
import com.bo.tasks.problem.BProblem;
import com.bo.template.BTemplate;
import com.bo.theme.BTheme;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.admin.mail.FMailAccount;
import com.form.admin.menu.FMenu;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.broadcast.FBroadcast;
import com.form.calendar.agenda.FAgenda;
import com.form.doc.assign.FDocAssign;
import com.form.mail.FMail;
import com.form.main.FMain;
import com.form.messages.create.FCreate;
import com.form.mycontact.FMycontact;
import com.form.require.requires.FRequire;
import com.form.tasks.problem.FProblem;
import com.form.template.FTemplate;
import com.form.theme.FTheme;

import com.inf.IKey;
import com.inf.doc.IKeyDoc;

import com.lib.AppConfigs;

import java.io.IOException;

import java.sql.SQLException;

import javax.mail.Folder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AMain extends  ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FMain bean = (FMain)form;
        BMain bo = new BMain();
        String anchor = bean.getValue(APP_ANCHOR,"");
      
        if(anchor.equals("_OPEN_PORTLET")){
            bo.updateMinimize(bean.getMenuId(),(int)me.getId(),1);
            request.getSession().setAttribute(bean.getMenuId(),1);
            request.setAttribute("BPortletClose",bo.getPortletClose(bean.me.getId()));
            target=ajaxPortlet(request,bean,bo);
        }else if(anchor.equals("_CLOSE_PORTLET")){
            //TAM XOA CHUA DUNG DEN            
            bo.updateMinimize(bean.getMenuId(),(int)me.getId(),bean.getMinimize());
            //request.getSession().removeAttribute(bean.getMenuId());
            request.setAttribute("BPortletClose",bo.getPortletClose(bean.me.getId()));
            target=anchor;
          //  anchor =_HOME;
        }else if(anchor.equals("_MINIMIZE")){
            //TAM XOA CHUA DUNG
            bo.updateMinimize(bean.getMenuId(),(int)me.getId(),bean.getMinimize());
            request.getSession().removeAttribute(bean.getMenuId());
            request.getSession().setAttribute(bean.getMenuId(),""+bean.getMinimize());
            target=ajaxPortlet(request,bean,bo);
            
        }else if (anchor.equals("_DELETE_MYCONTACT")){
            BMycontact boM = new BMycontact();
            FMycontact beanM = new FMycontact();
            beanM.setId(bean.getId());
            beanM.setUserId(me.getId());
            boM.delete(beanM);
            anchor="_DELETE_MYCONTACT";    
        }else if (anchor.equals("_MANAGER_REQUIRE")){         
            if(request.getSession().getAttribute("12")!=null){
            request.setAttribute("BRmStatuses",new BRmStatus().getAllRmStatus());
            request.setAttribute("BCatRequires",new BCatRequire().getAllCatRequire());
            FRequire beanRequire =new FRequire();   
            beanRequire.setCreator(me.getId());
            FDepartment beanDept =new FDepartment();
            beanDept.setId((int)me.getDepartmentID());
            beanDept=new BDepartments().getRecordByID(beanDept);
            beanRequire.setCode("YC"+"/"+me.getId()+"/"+ beanDept.getCode().toUpperCase());       
            beanRequire.setSurcureId(System.currentTimeMillis());
            beanRequire.setDateline(bean.dateToString(bean.getCurrentSqlDate()));
            beanRequire.setDatetimto(beanRequire.getDateline());
            beanRequire.setDatetimFrom(beanRequire.getDateline());
            beanRequire.setSurcureId(System.currentTimeMillis());
            request.getSession().setAttribute("secureId",beanRequire.getSurcureId());
            request.setAttribute("frmRequireEmp",beanRequire);  
            request.setAttribute("frmRequire",beanRequire);
            
            FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
            if(beanrule!=null){
            if (beanrule.getDirect()==1 && beanrule.getSelectDep()==1){
                    request.setAttribute("BDepartments",new BRequires().getAllDepartmentPri(me.getId(),0));  
            }    
            }
            // Tinh so luong trang thai xu ly yeu cau
             
                    request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
                    request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
                    if(beanrule.getStatusIdsNameTemp()!=null){
                        beanRequire.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                        request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(beanRequire));                  
                    }
             }
             
            target = "_MANAGER_REQUIRE";
        }else if (anchor.equals("_REQUIRE_LIST")){         
            
            FRequire beanRequire =new FRequire();   
            beanRequire.setRmStatus(IKeyDoc.RM_STATUS_UNREAD);
            beanRequire.setCreator(me.getId());
            beanRequire.setPagesIndex(1);
            beanRequire.setPageIndex(1);            
           
            FRequireTrailer beanrule=(FRequireTrailer)request.getSession().getAttribute(AppConfigs.CHECK_RULE_REQUIRES);
            request.setAttribute("frmRequireEmp",beanRequire);  
            request.setAttribute("frmRequire",beanRequire);
            
            // Tinh so luong trang thai xu ly yeu cau
            request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
            request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
            if(beanrule.getStatusIdsNameTemp()!=null){
                beanRequire.setRmStatusIds(beanrule.getStatusIdsNameTemp());
                request.setAttribute("BExcuteStatus",new BRequires().getRmByStatus(beanRequire));                  
            }
            request.setAttribute("BRequires",new BRequires().getAllRequires(beanRequire));
            target = "_REQUIRE_LIST";
        }else if (anchor.equals("_HUMAN_LIST")){         
            target = "_HUMAN_LIST";
        }
        
        
        if (anchor.equals(_HOME) || anchor.equals("_ADDCOOKEIS")){
            if(anchor.equals("_ADDCOOKEIS")){
                request.setAttribute("BAddCookeis","_ADDCOOKEIS");
                FTheme beanT= new     FTheme();
                beanT.setId(bean.getId());
                FTheme beanTheme=new BTheme().getById(beanT);
                if(beanTheme.getPathImages()!=null && !beanTheme.getPathImages().equals("")){
                    Cookie cookie = new Cookie ("pathImages",beanTheme.getPathImages());
                    request.removeAttribute("pathImager");
                    request.setAttribute("pathImager",beanTheme.getPathImages());
                    cookie.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(cookie);
                }
                
                if(beanTheme.getPathStyle()!=null && !beanTheme.getPathStyle().equals("")){
                    Cookie cookie =new Cookie ("pathStyle",beanTheme.getPathStyle());
                    request.removeAttribute("pathStyle");
                    request.setAttribute("pathStyle",beanTheme.getPathStyle());
                    cookie.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(cookie);
                }
            }
            FBeans menus =new BMenu().getAllMenuByUserId(bean.me.getId(),me.getLanguage().equals("en")?1:0);
            for(int i=0;i<menus.size();i++){              
                FMenu beanMenu =(FMenu)menus.get(i); 
                if (beanMenu.getLevel()==0){
                    request.getSession().setAttribute(beanMenu.getMenuId(),beanMenu.getMinimize());
                }
                for(int k=0;k<beanMenu.getBeansMenu().size();k++){ 
                    FMenu beanMenu1 =(FMenu)beanMenu.getBeansMenu().get(k);
                    request.getSession().setAttribute(beanMenu1.getMenuId(),beanMenu1.getMinimize());
                }
            }
            
            
            FCreate beanC = new FCreate(); 
            FProblem beanP = new FProblem();
            FDocAssign beantemp =(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);
            
            if(beantemp!=null && (beantemp.getCheckNotIncharge()>0 || beantemp.getCheckUnReaded()>0)){
                request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
            }
            
            if(request.getSession().getAttribute("01.01")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("01.01").toString())==1){  
                        if(beantemp.getStatusIds()!=null){
                        request.setAttribute("BDocsRecv",bo.getDocRecvByStatus(beantemp,0));
                        }
                        int checkWait = 0 ;
                        if (request.getSession().getAttribute("checkWait")!=null){
                           checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                        }
                        request.setAttribute("BDocsRecvRead",bo.getDocRecvByRead(beantemp,0,checkWait==1?1:0,(beantemp.getStatusIds()!=null && !beantemp.getStatusIds().equals(""))?beantemp.getStatusIds():"1110"));                      
                         
                }
            }
            int checkWaitSend = 0 ;
            if (request.getSession().getAttribute("checkWaitSend")!=null){
               checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
            }
            if(request.getSession().getAttribute("01.02")!=null){
                
                if (Integer.parseInt(request.getSession().getAttribute("01.02").toString())==1){ 
                    FDocAssign BAssignsend = new FDocAssign();
                    BAssignsend.setMeId(bean.me.getId());
                    BAssignsend.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);     
                    BAssignsend = new BAssignRecv().checkAsignRule(BAssignsend);
                    if(BAssignsend.getStatusIds()!=null){
                    request.setAttribute("BDocsSend",bo.getAmountOfStatus(1,bean.me.getId(),0,BAssignsend.getStatusIds()));
                    }
                    request.setAttribute("BDocsSendRead",bo.getDocsendRead(1,bean.me.getId(),0,checkWaitSend,BAssignsend.getStatusIds()!=null?BAssignsend.getStatusIds():"1110"));
                    if(BAssignsend.getCheckNotIncharge()>0 || BAssignsend.getCheckUnReaded()>0){
                        request.setAttribute("BDocsSendWait",bo.getTotalWaitSend((int)bean.me.getId()));
                    }
                    
                }
            }
            
            if(request.getSession().getAttribute("01.03")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("01.03").toString())==1){ 
                    FDocAssign beanrule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                    request.setAttribute("BDocsDtSend",bo.getAmountOfStatus(2,me.getId(),0,beanrule.getStatusIds()));            
                    request.setAttribute("BDocsDtSendRead",bo.getDocsendRead(2,me.getId(),0,checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));           
                }
            }
            
            if(request.getSession().getAttribute("03")!=null){
                //if (Integer.parseInt(request.getSession().getAttribute("03").toString())==2){  
                    request.setAttribute("BAmount",new BCreate().getAllAmount(beanC,(int)me.getId()));     
                //}
            }
            
            if(request.getSession().getAttribute("02")!=null){

                BProblem boProblem = new BProblem();
                request.setAttribute("BAmountTask",boProblem.getAllAmount(beanP,(int)bean.me.getId()));
                request.setAttribute("BAmountDeadLineRecv",boProblem.getMoreDeadLine(bean.me.getId(),0));
                request.setAttribute("BAmountDeadLineSend",boProblem.getMoreDeadLine((int)bean.me.getId(),1));               
                request.setAttribute("BCheckHaveReport",boProblem.checkHaveReport(bean.me.getId()));               
                request.setAttribute("BCheckHaveReview",boProblem.checkHaveReview(bean.me.getId()));           

                    if(boProblem.getAssignCheck(bean.me.getId())==1){//quyen giao viec
                        request.getSession().setAttribute("Assign","");
                    }

            }
            
            if(request.getSession().getAttribute("06")!=null) {
                            if (Integer.parseInt(request.getSession().getAttribute("06").toString())==1){
                                FAgenda beanAgenda =new FAgenda();
                                beanAgenda.setDepartmentId((int)bean.me.getDepartmentID());
                                request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                                request.setAttribute("agenda",beanAgenda);
                                request.setAttribute("BCalendaInDeps",new BCalendar().getCalendar(bean.getCurrentSqlDate(),bean.me.getId(),2,me.getDepartmentID()));
                                request.setAttribute("BAgendas",new BCalendar().getCalendar(bean.getCurrentSqlDate(),me.getId(),0,me.getDepartmentID()));
                                request.setAttribute("BPublicAgendas",new BCalendar().getCalendar(bean.getCurrentSqlDate(),me.getId(),1,me.getDepartmentID()));
                            }
            }
            

            if(request.getSession().getAttribute("07")!=null) {
                if (Integer.parseInt(request.getSession().getAttribute("07").toString())==1){ 
                    FTemplate beantemplate =new FTemplate();
                    beantemplate.setDepartmentId((int)bean.me.getDepartmentID());
                    beantemplate.setPageIndex(-1);
                    request.setAttribute("BTemplates",new BTemplate().getAll(beantemplate,0,1));
                    request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                    request.setAttribute("template",beantemplate);
                }
            }
            
            if(request.getSession().getAttribute("09.01")!=null && (Integer.parseInt(request.getSession().getAttribute("09.01").toString())==1)) {
                        FBroadcast beanTemp = new FBroadcast();
                        beanTemp.setUser_id((int)me.getId());
                        beanTemp.setPageIndex(1);
                        beanTemp.setCreatetime(beanTemp.dateToString(beanTemp.getCurrentSqlDate()));
                        request.setAttribute("BBroadcasts",new BBroadcast().getAllBroadcastShow(beanTemp));
                        request.setAttribute("broadcast",beanTemp); 
            }
            
            if(request.getSession().getAttribute("05.02")!=null && (Integer.parseInt(request.getSession().getAttribute("05.02").toString())==1)) {
                    request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(bean.me.getId()));
            }
            request.getSession().setAttribute("BTotals",new BServey().getByActiveTrue());
            request.getSession().setAttribute("BThePen",new BThePen().getAllThePen());
            request.setAttribute("BPortletClose",bo.getPortletClose(bean.me.getId()));
            
            //Email 
           // countEmail(request,bean);
            
            request.setAttribute("BTotalRM",new BRequires().getTotalRMByEmpRecv(me.getId()));  
            request.setAttribute("BExcuteWailt",new BRequires().getTotalWaitRecv(me.getId()));  
            target = _HOME;
        
        }else if(anchor.equals("_SHOW_MODULE")){
//            FBeans menus =new BMenu().getAllMenuByUserId(bean.me.getId());
//            request.getSession().setAttribute("BMenus",menus);
            target =anchor;
        }else if (anchor.equals("_POST_USER_ONLINE")){
            //request.setAttribute("BListUserOnline",OnlineUsers.getAllUsers());
            target=anchor;
        }else if (anchor.equals("_LIST_ACCOUNT")){
            request.setAttribute("BRegisters",new BRegister().getByMeId(me.getId()));   
            target=anchor;
        }else if (anchor.equals("_LIST_FILTER")){
            request.setAttribute("BMailFilters",new BMailFilter().getAllByMeId(me.getId()));   
            target=anchor;
        }
        
        
        
        return mapping.findForward(target);
    }
    public String  ajaxPortlet(HttpServletRequest request,FMain bean,BMain bo) throws  EException,SQLException{
    String anchor="";
        FCreate beanC = new FCreate(); 
        FProblem beanP = new FProblem();
        int checkWaitSend = 0 ;
        if (request.getSession().getAttribute("checkWaitSend")!=null){
           checkWaitSend = Integer.parseInt(request.getSession().getAttribute("checkWaitSend").toString());            
        }
        if(bean.getMenuId().equals("01")){
        //////.println(request.getSession().getAttribute("01"));
              if(request.getSession().getAttribute("01")!=null){
                  if (Integer.parseInt(request.getSession().getAttribute("01").toString())==1){  
                          FDocAssign beanTempS = (FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSRECV);  
                          beanTempS.setMeId(me.getId());
                          request.setAttribute("BDocsRecv",bo.getDocRecvByStatus(beanTempS,0));
                          int checkWait = 0 ;
                          if (request.getSession().getAttribute("checkWait")!=null){
                             checkWait = Integer.parseInt(request.getSession().getAttribute("checkWait").toString());            
                          }
                          request.setAttribute("BDocsRecvRead",bo.getDocRecvByRead(beanTempS,0,checkWait==1?1:0,(beanTempS.getStatusIds()!=null && !beanTempS.getStatusIds().equals(""))?beanTempS.getStatusIds():"1110"));
                                  if (beanTempS.getCheckNotIncharge()>0 || beanTempS.getCheckUnReaded()>0){
                              request.setAttribute("BDocsRecvWait",new BMain().getTotalWaitRecv((int)bean.me.getId()));
                          }
                          
                  }
              }
              anchor="AJAX_DOCSRECV";
        }else if(bean.getMenuId().equals("01.02")){
            if(request.getSession().getAttribute("01.02")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("01.02").toString())==1){ 
                    FDocAssign BAssignsend = new FDocAssign();
                    BAssignsend.setMeId(bean.me.getId());
                    BAssignsend.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);     
                    BAssignsend = new BAssignRecv().checkAsignRule(BAssignsend);
                    if(BAssignsend.getStatusIds()!=null){
                    request.setAttribute("BDocsSend",bo.getAmountOfStatus(1,bean.me.getId(),0,BAssignsend.getStatusIds()));
                    }
                    request.setAttribute("BDocsSendRead",bo.getDocsendRead(1,bean.me.getId(),0,checkWaitSend,BAssignsend.getStatusIds()!=null?BAssignsend.getStatusIds():"1110"));
                    if(BAssignsend.getCheckNotIncharge()>0 || BAssignsend.getCheckUnReaded()>0){
                        request.setAttribute("BDocsSendWait",bo.getTotalWaitSend((int)bean.me.getId()));
                    }
                }
            }
              anchor="AJAX_DOCSSEND";
        }else if(bean.getMenuId().equals("01.03")){
            if(request.getSession().getAttribute("01.03")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("01.03").toString())==1){ 
                    FDocAssign beanrule=(FDocAssign)request.getSession().getAttribute(AppConfigs.CHECK_RULE_DOCSSEND);
                    request.setAttribute("BDocsDtSend",bo.getAmountOfStatus(2,me.getId(),0,beanrule.getStatusIds()));            
                    request.setAttribute("BDocsDtSendRead",bo.getDocsendRead(2,me.getId(),0,checkWaitSend,beanrule.getStatusIds()!=null?beanrule.getStatusIds():"1110"));           
                }
            }
               anchor="AJAX_DOCSSEND_DT";
        }else if(bean.getMenuId().equals("03")){            
            if(request.getSession().getAttribute("03")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("03").toString())==1){  
                    request.setAttribute("BAmount",new BCreate().getAllAmount(beanC,(int)me.getId()));     
                }
            }
               anchor="AJAX_MESSAGES";
        }else if(bean.getMenuId().equals("04")){            
            if(request.getSession().getAttribute("04")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("04").toString())==1){  
                    
                }
            }
               anchor="AJAX_CABIN";
        }else if(bean.getMenuId().equals("08")){            
//            if(request.getSession().getAttribute("08")!=null){
//                if (Integer.parseInt(request.getSession().getAttribute("08").toString())==1){  
//                    
//                }
//            }
               anchor="AJAX_REPORT";
        }else if(bean.getMenuId().equals("02")){
            if(request.getSession().getAttribute("02")!=null){
                if (Integer.parseInt(request.getSession().getAttribute("02").toString())==1){ 
                    request.setAttribute("BAmountTask",new BProblem().getAllAmount(beanP,(int)me.getId()));  
                    request.setAttribute("BAmountDeadLineRecv",new BProblem().getMoreDeadLine((int)bean.me.getId(),0));
                    request.setAttribute("BAmountDeadLineSend",new BProblem().getMoreDeadLine((int)bean.me.getId(),1));
                    if(new BProblem().getAssignCheck(bean.me.getId())==1){
                        request.getSession().setAttribute("Assign","");
                    }
                }
            }
           anchor="AJAX_TASK";
       
      
        }else if(bean.getMenuId().equals("07")){
            if(request.getSession().getAttribute("07")!=null) {
                if (Integer.parseInt(request.getSession().getAttribute("07").toString())==1){ 
                    FTemplate beantemplate =new FTemplate();
                    beantemplate.setDepartmentId((int)bean.me.getDepartmentID());
                    beantemplate.setPageIndex(-1);
                    request.setAttribute("BTemplates",new BTemplate().getAll(beantemplate,0,1));
                    request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                    request.setAttribute("template",beantemplate);
                }
            }
            anchor="AJAX_TEMPLATE";
        }else if(bean.getMenuId().equals("09.01")){
                if(request.getSession().getAttribute("09.01")!=null) {
                    if (Integer.parseInt(request.getSession().getAttribute("09.01").toString())==1){ 
                        FBroadcast beanTemp = new FBroadcast();
                        beanTemp.setUser_id((int)me.getId());
                        beanTemp.setPageIndex(1);
                        beanTemp.setCreatetime(beanTemp.dateToString(beanTemp.getCurrentSqlDate()));
                        request.setAttribute("BBroadcasts",new BBroadcast().getAllBroadcastShow(beanTemp));
                        request.setAttribute("broadcast",beanTemp);              
                    }
                anchor="AJAX_BROADCASTLIST";
                }
         }else if(bean.getMenuId().equals("05.02")){
             if(request.getSession().getAttribute("05.02")!=null) {
                 if (Integer.parseInt(request.getSession().getAttribute("05.02").toString())==1){ 
                     request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(bean.me.getId()));     
                 }
             }
             anchor="AJAX_MYCONTACT";
         }
    
    return anchor;
    }
    
    private void countEmail(HttpServletRequest request,FMain bean)  throws  EException{
        Folder folder=null;
        FMail beanEmail =new FMail();
        FMailAccount beanT=new FMailAccount();
        FBeans beans=new BRegister().getInforLogin(bean.me.getId());
        if(beans!=null && beans.size()>0){
            beanT=(FMailAccount)beans.get(0);
            if(beanT.getUserMail()!=null && beanT.getPassMail()!=null){
                beanEmail.setHostMail(beanT.getServerMail());
                beanEmail.setUserMail(beanT.getUserMail());
                beanEmail.setPassMail(beanT.getPassMail());
                beanEmail.setPostMail(beanT.getSercure()==0?25:993);
                beanEmail.setSercure(beanT.getSercure());
                folder=new BLoginMail().loginMail(beanEmail,folder);
                request.getSession().setAttribute("folderStore",folder);
                int totalUnReadedMessage=0;
                if(folder!=null){
                    request.getSession().setAttribute("meEmail",beanEmail);
                    try{
                            if (folder == null) throw new Exception("No default folder");
                            folder = folder.getFolder(IKey.FOLDER_INBOX);
                            new BReceiveMail().filterSpam(folder,bean.me.getId());
                            folder.open(Folder.READ_ONLY);
                            totalUnReadedMessage=folder.getUnreadMessageCount();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    request.setAttribute("BTotalEmailUnReaded",totalUnReadedMessage);  
                }
            }
        }      
        
        
    }
    
    
}
