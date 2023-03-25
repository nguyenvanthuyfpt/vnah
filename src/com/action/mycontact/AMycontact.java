package com.action.mycontact;


import com.action.ACore;

import com.bo.admin.menu.BMenu;
import com.bo.messages.create.BCreate;
import com.bo.mycontact.BMycontact;
import com.bo.pgroups.BPgroups;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.departments.FDepartment;
import com.form.messages.create.FCreate;
import com.form.mycontact.FMycontact;
import com.form.pgroups.FPgroup;

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


public class AMycontact extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FMycontact bean = (FMycontact)form;        
        BMycontact bo = new BMycontact();
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        bean.setUserId(me.getId());
        target = validate(bean,anchor,errors);
        if(!errors.isEmpty()){
        
            
        }else if(anchor.equals("_LIST_MY_CONTACT")){
            request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));  
            target=_VIEW;
        }else if(anchor.equals("_EMPLOYER")){
        
                FBeans beans = new BCreate().getAllDepartment();
                request.setAttribute("BDepartments",beans);
                bean.setDepartmentID(((FDepartment)beans.get(0)).getId());
                request.setAttribute("BUsersDep",new BCreate().getUserByDepartmentId(new FCreate(),((FDepartment)beans.get(0)).getId()));             
                request.setAttribute("formMyContact",bean);
                target=anchor;
                
        }else if(anchor.equals("_SHOW_USER")){                
              
              request.setAttribute("BUsersDep",new BCreate().getUserByDepartmentId(new FCreate(),bean.getDepartmentID()));             
              request.setAttribute("formMyContact",bean);
              target=anchor;
              
        }else if(anchor.equals(_ACTIVE)){
                request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));
                request.setAttribute("BUsers",bo.getAllMycontactByPgroup(bean));            
                target = _ACTIVE;
                
        }else if(anchor.equals(_EDIT) || anchor.equals("_EDIT_OPEN")){        
                if(bo.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.update.username.error"));   
                }
                 
              
               if (anchor.equals("_EDIT_OPEN")){
                   request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));  
                   target =  "_LIST_MYCONTACT_VIEW";
               }else{
                   request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));  
                   target = _PREPARED_CREATE;
               }
                
        }else if(anchor.equals(_CREATE) || anchor.equals("_CREATE_OPEN")){
            
                if (bo.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.insert.error"));   
                }
                request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId())); 
                if (anchor.equals("_CREATE_OPEN")){
                    request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));  
                    target =  "_LIST_MYCONTACT_VIEW";
                }else{
                    target = _PREPARED_CREATE;    
                }
                
                
        }
        else if(anchor.equals(_DELETE)){
               
                if(bo.delete(bean)){
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }
                request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));
                request.setAttribute("BMycontacts",bo.getAllMycontactByPgroup(bean));   
                target = _LIST_MYCONTACT;
                
        
        }else if (anchor.equals("_ADD_MYCONTACT")){
        
            FMycontact beanT=new FMycontact();
            String email="";
            if(bean.getEmail()!=null && !bean.getEmail().equals("")){
                email=bean.getEmail();
                if(email.indexOf("<")>-1 && email.indexOf(">")>-1){
                    String fullName=email.split("<")[0];
                    if(!fullName.equals("")){
                        beanT.setFullName(fullName);
                    }
                    beanT.setIcq(email.split("<")[1].split(">")[0]);                
                }else{
                    beanT.setIcq(email);
                }
            }
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));    
            request.setAttribute("openMycontact",beanT); 
            target = "_OPEN_WINDOW";
        }
        else if (anchor.equals("_DELETE_PMYCONTACT")){
            if(bo.delete(bean)){
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.delete.error"));   
            }
            request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));  
            target =  "_LIST_MYCONTACT_VIEW";
        }
        else if (anchor.equals(_PREPARED_DELETE)){
            BPgroups groups = new BPgroups();
            FPgroup beanGroup = new FPgroup();
            beanGroup.setId(bean.getId());
                if(groups.delete(beanGroup)){                    
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.delete.error"));   
                }               
                request.setAttribute("BMycontacts",bo.getViewMycontact(me.getId()));   
                target = _PREPARED_DELETE;   
        }else if(anchor.equals(_SEARCH_PAGE)){
        
            bean.setUserId((int)me.getId());
            bean.setToAddress(bean.getToAddress().trim());
            request.setAttribute("BMycontacts",bo.searchList(bean));     
            target=anchor;
            
        } else if(anchor.equals(_VIEW) || anchor.equals("_LIST_MYCONTACT_VIEW") || anchor.equals("_SHOW_MYCONTACT")){
        
            request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));     
            target = "_LIST_MYCONTACT_VIEW";
            
        }else if(anchor.equals("_MENU_OPTION")){
            
            request.setAttribute("BMenus",new BMenu().getAllMenuGPermision(bean.getPgroupId(),1));
            target = "_MENU_OPTION";
            
        }else if (anchor.equals(_PREPARED_CREATE)){
            
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));    
            request.setAttribute("formMyContact",new FMycontact());    
            target = _PREPARED_CREATE;
            
        }else if (anchor.equals(_PREPARED_EDIT)){
            
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));    
            request.setAttribute("formMyContact",bo.getRecordById(bean));    
            target = _PREPARED_CREATE;
        
        }else if (anchor.equals("_OPEN_WINDOW")){
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));    
            request.setAttribute("openMycontact",bo.getRecordById(bean));    
            target = "_OPEN_WINDOW";
        }
        else if (anchor.equals(_LIST_MYCONTACT)){                
            
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));
            request.setAttribute("BMycontacts",bo.getAllMycontactByPgroup(bean));            
            target =_LIST_MYCONTACT;
            
        }else if (anchor.equals(_PREPARED_SAVE)){
            
             if (bean.getFullName()==null || !bean.getFullName().equals("")){
                 bean.setPgroupId(0);
                 request.setAttribute("BMycontactSearchs",bo.getAllMycontactByPgroup(bean));   
                 target = _PREPARED_SAVE ;
             }else{
                 request.setAttribute("BMycontacts",bo.getViewMycontact(bean.me.getId()));   
                 target = "_LIST_MYCONTACT_VIEW" ;
             }
        }else if (anchor.equals("_VIEW_MYCONTACT")){
            request.setAttribute("formMyContact",bo.getRecordById(bean));      
            target = "_VIEW_MYCONTACT";
        
        }else if (anchor.equals("_VIEW_PGROUP_EDIT")){
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));  
            FPgroup beanGroup =new FPgroup();
            beanGroup.setId(bean.getId());
            request.setAttribute("pgroup",new BPgroups().getRecordById(beanGroup)); 
            target = "_VIEW_PGROUP";
        }else if (anchor.equals("_VIEW_PGROUP")){
            request.setAttribute("BPgroups",new BPgroups().getAllRecord(me.getId()));  
            request.setAttribute("pgroup",new FPgroup()); 
            target = "_VIEW_PGROUP";
            
        }else if (anchor.equals(_SEARCH)){
            request.setAttribute("BUsers",bo.getAllMycontactByPgroup(bean));      
            target = _SEARCH;
        }

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FMycontact bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
            if (bean.getFullName()==null || bean.getFullName().trim().equals(_BLANK) ){
                errors.add("alert",new ActionError("mycontact.edit.fullname.message"));   
                return _PREPARED_CREATE;
            }else if (bean.getPgroupId()==0){
                errors.add("alert",new ActionError("mycontact.edit.pgroupid.eror.message"));
                return _PREPARED_CREATE;
            }
        }
        
    return anchor;
    }

}
