package com.action.template;


import com.action.ACore;

import com.bo.admin.departments.BDepartments;
import com.bo.admin.templates.rules.BTemplatesRule;
import com.bo.admin.users.BUsers;
import com.bo.foryou.BForYou;
import com.bo.template.BTemplate;
import com.bo.template.templateType.BTemplateType;

import com.exp.EException;

import com.form.admin.templates.rules.FTemplatesRule;
import com.form.admin.users.FUser;
import com.form.template.FTemplate;

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


public class ATemplate extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_SUCCESS;
        FTemplate bean = (FTemplate)form;
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();
        BTemplate bo = new BTemplate();
        target = validate(bean,anchor,errors);
            if(!errors.isEmpty()){
                        request.setAttribute("template",bean);
                        request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                        request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
            }else if(anchor.equals(_EDIT)){
                        request.setAttribute("template",bean);
                        FTemplate beantemp =bo.getRecordByID(bean);
                        String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.TEMPLATE_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP+ bean.me.getUsername() ;
                        (new File(dirs)).mkdirs(); 
                        String filename="";
                        boolean haveFile = bean.getUpFile().getFileSize()>0;
                        if(haveFile){
                                    filename=encodeFileName(bean.me.getId());
                                    bean.upload(bean.getUpFile(),dirs + filename);
                                    bean.setId(beantemp.getId());
                                    bean.setFileStore(filename);
                                    bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
                                        if(bo.update(bean)){
                                            errors.add("alert",new ActionError("alert.template.update.successfull"));   
                                        }else{
                                            errors.add("alert",new ActionError("errors.template.insert.exitsName"));   
                                        }
                        }else{
                                    bean.setFileStore(beantemp.getFileStore());
                                    bean.setRealName(beantemp.getRealName());
                                    if(bo.update(bean)){
                                        errors.add("alert",new ActionError("alert.template.update.successfull"));   
                                    }else{
                                        errors.add("alert",new ActionError("errors.template.insert.exitsName"));   
                                    }
                        }
                        FTemplatesRule beanC1 =new FTemplatesRule();
                        beanC1.setUserId((int)me.getId());
                        request.setAttribute("BTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                        
                        target=anchor;
            }else if(anchor.equals(_DOWNLOAD)){
                        FTemplate beanTemp = new FTemplate();
                        beanTemp=bo.getRecordByID(bean);
                        BUsers boUsers =new BUsers();
                        FUser beanUsers =new FUser();
                        beanUsers.setId(beanTemp.getUserId());
                        beanUsers=boUsers.getRecordByID(beanUsers);
                        String filePath =AppConfigs.APP_SYSTEM_PATH+AppConfigs.TEMPLATE_FILE_PATH+AppConfigs.SYSTEM_FILE_SCHIP +beanUsers.getUsername()+AppConfigs.SYSTEM_FILE_SCHIP+beanTemp.getFileStore();
                        File file = new File(filePath);
                        if(file.exists()){
                            bean.download(filePath,URLEncoder.encode(beanTemp.getRealName(),"UTF-8"),null);
                            
                        }else{
                            bean.download(AppConfigs.APP_SYSTEM_PATH+AppConfigs.DOC_NOIMAGER_PATH_FILE,"NoFile",null);  
                        }
            }else if(anchor.equals(_PREPARED_EDIT)){
                        FTemplate beantemp =new FTemplate();
                        beantemp=bo.getRecordByID(bean);
                        beantemp.setType(bean.getType());
                        request.setAttribute("template",beantemp);
                        FTemplatesRule beanC1 =new FTemplatesRule();
                        beanC1.setUserId((int)me.getId());
                request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                
                request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                
                
                        target =_PREPARED_CREATE;
            }else if(anchor.equals(_PREPARED_CREATE)){
                        
                        bean.reset();
                        bean.setUserId((int)me.getId());
                        bean.setCreator(bean.me.getFullName());
                        bean.setDepartmentId((int)me.getDepartmentID());
                        request.setAttribute("template",bean);
                        FTemplatesRule beanC1 =new FTemplatesRule();
                        beanC1.setUserId((int)me.getId());
                        request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                        
                        request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                        request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                        target=anchor;
                        
            }else if(anchor.equals(_CREATE)){
                        
                        String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.TEMPLATE_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
                        (new File(dirs)).mkdirs();                        
                        String filename="";
                        boolean haveFile = bean.getUpFile().getFileSize()>0;
                                if(haveFile){
                                    filename=encodeFileName(bean.me.getId());
                                    bean.upload(bean.getUpFile(),dirs + filename);
                                    bean.setFileStore(filename);
                                    bean.setRealName(new String((bean.getUpFile().getFileName().getBytes()),"UTF-8"));
                                if(bo.insert(bean)){
                                    errors.add("alert",new ActionError("alert.template.insert.successfull"));
                                    bean.reset();
                                }else{
                                    errors.add("alert",new ActionError("errors.template.insert.exitsName"));   
                                }
                        }else{
                                errors.add("alert",new ActionError("errors.template.insert.file.null"));
                                
                        }
                        bean.setCreator(bean.me.getFullName());
                        bean.setType(1);
                        request.setAttribute("template",bean);
                        FTemplatesRule beanC1 =new FTemplatesRule();
                        beanC1.setUserId((int)me.getId());
                        request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                        request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                        request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                        
                        target=anchor;
            }else if(anchor.equals("_RESTORE")){
                        if(bo.restore(bean.getIds(),1)){
                            errors.add("alert",new ActionError("alert.template.restore.successfull"));   
                        }else{
                            errors.add("alert",new ActionError("errors.template.delete.fail"));   
                        }
                FTemplate beanT =new FTemplate();
                beanT.setTemplateType_id(bean.getTemplateType_id());
                beanT.setType(bean.getType());
                        request.setAttribute("BTemplate",new BTemplate().getAll(beanT,(int)me.getId(),0));
                        request.setAttribute("template",bean);
                        target=_SUCCESS;
            }else if(anchor.equals("_EMPTY")){
                String filePath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.TEMPLATE_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP;
                FTemplate beantemp = new FTemplate();
                beantemp=bo.getRecordByID(bean);
                bean.deleteFile(filePath + beantemp.getFileStore());
                        if(bo.delete(bean.getIds())){
                            errors.add("alert",new ActionError("alert.template.delete.successfull"));   
                        }else{
                            errors.add("alert",new ActionError("errors.template.delete.fail"));   
                        }
                        request.setAttribute("BTemplate",new BTemplate().getAll(bean,(int)me.getId(),0));
                        request.setAttribute("template",bean);
                        target=_SUCCESS;
            }else if(anchor.equals(_DELETE)){
                        if(bo.updatePermision(bean.getId(),0)){
                            errors.add("alert",new ActionError("alert.template.delete.successfull"));   
                        }else{
                            errors.add("alert",new ActionError("errors.template.delete.fail"));   
                        }
                        request.setAttribute("BTemplate",bo.getAll(bean,(int)me.getId(),1));
                        request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                        request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                        request.setAttribute("BUsers",new BForYou().getAllUsers());
                        FTemplatesRule beanC1 =new FTemplatesRule();
                        beanC1.setUserId((int)me.getId());
                        request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                        request.setAttribute("template",bean);
                        target=_SUCCESS;
            }else  if(anchor.equals(_SEARCH)){
                        request.setAttribute("BUsers",new BForYou().getAllUsers());
                        bean.setUserId((int)me.getId());
                        request.setAttribute("BTemplate",bo.getAll(bean,(int)me.getId(),bean.getType()));
                        request.setAttribute("template",bean);
                        request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                        request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                FTemplatesRule beanC1 =new FTemplatesRule();
                beanC1.setUserId((int)me.getId());
                request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                        target=_SUCCESS;
            }else if(anchor.equals(_SAVE_FALSE)){
                request.setAttribute("BFiles",bo.getByCode(bean.getCode(),bean.getType()));
                target=anchor;
            }else if (anchor.equals("_SEARCH_MAIN")){
                request.setAttribute("BTemplates",bo.getAll(bean,(int)me.getId(),bean.getType()));                
                target = "_SEARCH_MAIN";
            }
            
            if(anchor.equals(_SHOW)){
                request.setAttribute("BTemplate",bo.getAll(bean,(int)me.getId(),bean.getType()));
                request.setAttribute("template",bean);
                request.setAttribute("BUsers",new BForYou().getAllUsers());
                request.setAttribute("BTemplateTypes",new BTemplateType().getAllTemplateType());
                request.setAttribute("BDepartments",new BDepartments().getAllRecord(0));
                FTemplatesRule beanC1 =new FTemplatesRule();
                beanC1.setUserId((int)me.getId());
                request.setAttribute("BRuleTemplateTypes",new BTemplatesRule().checkRuleCreateTemplatesCategory(beanC1));
                target=_SUCCESS;
            }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
    private String validate(FTemplate bean,String anchor,ActionErrors errors){
        if(anchor.equals(_EDIT) || anchor.equals(_CREATE)){
            if(bean.getEffectiveDate()!=null){
                if(!bean.getEffectiveDate().equals(_BLANK) && !bean.isDate(bean.getEffectiveDate())){
                    errors.add("alert",new ActionError("errors.template.edit.EffectiveDate.isDate"));   
                }
            }else if(bean.getName()==null && bean.getName().trim().equals(_BLANK)){
                errors.add("alert",new ActionError("errors.template.edit.name.null"));   
            }else if(bean.getTemplateType_id()<=0){
                errors.add("alert",new ActionError("errors.template.thisUser.notRules"));   
            }
        }
    return anchor;
    }
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    }
   
}
