package com.action.mail.login;


import com.action.ACore;

import com.bo.admin.mail.BRegister;
import com.bo.mail.BLoginMail;
import com.bo.mail.BReceiveMail;
import com.bo.mycontact.BMycontact;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.mail.FMailAccount;
import com.form.mail.FMail;

import com.inf.IKey;

import java.io.IOException;

import java.sql.SQLException;

import javax.mail.Folder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ALoginMail extends  ACore {
        public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
        //,Throwable
        {
            final String LOCATION = this + "->executeAction()";
            String target = _ERROR;
            FMail bean = (FMail)form;
            String anchor=bean.getValue(APP_ANCHOR,"");
            BLoginMail bo =new BLoginMail();
            ActionErrors errors = new ActionErrors();
        Folder folder;
        folder = null;
        if(anchor.equals(_LOGOUT)){
            if(request.getSession().getAttribute("folderStore")!=null){
                folder=(Folder)request.getSession().getAttribute("folderStore");
                if(folder!=null && folder.getStore()!=null){
                    //folder.getStore().close();
                    //folder.close(true);
                }
                request.getSession().removeAttribute("meEmail");
                request.getSession().removeAttribute("folderStore");                
            }
                request.getSession().invalidate();
                target = _LOGIN;

            }else if(anchor.equals(_LOGIN)){
                if(request.getSession().getAttribute("folderStore")!=null){
                    folder=(Folder)request.getSession().getAttribute("folderStore");
                    if(folder!=null && folder.getStore()!=null){
                        //folder.getStore().close();
                    }
                }
                FBeans beans=null;
                request.getSession().removeAttribute("meEmail");
                request.getSession().removeAttribute("folderStore");
                FMailAccount beanT=new FMailAccount();
                if(bean.getAccountId()>0){
                    beanT=new BRegister().getById(bean.getAccountId());
                }else{
                        beans=new BRegister().getInforLogin(me.getId());
                        if(beans!=null && beans.size()>0){
                            beanT=(FMailAccount)beans.get(0);
                        }
                }
                if(beanT.getUserMail()!=null && beanT.getPassMail()!=null){
                        bean.setHostMail(beanT.getServerMail());
                        bean.setUserMail(beanT.getUserMail());
                        bean.setPassMail(beanT.getPassMail());
                        bean.setPostMail(beanT.getSercure()==0?25:993);
                        bean.setSercure(beanT.getSercure());
                        folder=bo.loginMail(bean,folder);
                        request.getSession().setAttribute("folderStore",folder);
                            if(folder!=null){
                                request.getSession().setAttribute("meEmail",bean);
                                //total(bean,request,folder);
                                bean.setFolderName(IKey.FOLDER_INBOX);
                                request.setAttribute("BInBoxs",new BReceiveMail().getINBOX(bean,folder));
                                bean.setFolderName(IKey.FOLDER_INBOX);
                                request.setAttribute("sendMail",bean);
                                target =_SUCCESS;
                            }else{
                                errors.add("alert",new ActionError("alert.login.errors"));
                                request.setAttribute("BRegisters",new BRegister().getByMeId(me.getId()));     
                                target =_ERROR;
                            }
                }else{
                    request.setAttribute("BRegisters",new BRegister().getByMeId(me.getId()));     
                    errors.add("alert",new ActionError("userName.or.passwork.null"));   
                }
                
            }
            
            if(!errors.isEmpty()) saveErrors(request,errors);
            return mapping.findForward(target);
        }
        
        private void total(FMail bean,HttpServletRequest request,Folder folder) throws EException,Throwable{
            try{
                    if (folder == null) throw new Exception("No default folder");
                    folder = folder.getFolder(IKey.FOLDER_INBOX);
                    folder.open(Folder.READ_ONLY);
                    bean.setTotalUnReadedMessage(folder.getUnreadMessageCount());
                    request.setAttribute("BMycontacts",new BMycontact().getViewMycontact(me.getId()));    
                    request.setAttribute("BRegisters",new BRegister().getByMeId(me.getId()));     
            }catch (Exception ex){
                ex.printStackTrace();
            }finally{
                try{
                  if (folder!=null) folder.close(false);
                }catch (Exception ex2) {ex2.printStackTrace();}
              } 
            request.setAttribute("BTotals",bean);  
        }
      
    
}