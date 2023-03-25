package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BNguonhotro;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FNguonhotro;

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


public class ANguonhotro extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FNguonhotro bean =(FNguonhotro)form;
        FBeans beans = new FBeans();
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        }else if(anchor.equals(_EDIT)){
            BNguonhotro nguonhotro = new BNguonhotro();
            if(bean.getId()==bean.getParentID()){
                errors.add("alert",new ActionError("errors.nguonhotro.edit.circle"));   
            }else if(nguonhotro.update(bean)){
                errors.add("alert",new ActionError("alert.update.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.nguonhotro.update.departmentcode"));   
            }
        }else if(anchor.equals(_CREATE)){
            BNguonhotro nguonhotro = new BNguonhotro();
            int saveID = bean.getId();
            bean.setId(0);
            if(nguonhotro.insert(bean)){
                errors.add("alert",new ActionError("alert.insert.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.nguonhotro.insert.departmentcode"));   
            }
            bean.setId(saveID);            
        }else if(anchor.equals(_DELETE)){
            BNguonhotro nguonhotro = new BNguonhotro();
            if(nguonhotro.delete(bean)){
                bean = new FNguonhotro();
                errors.add("alert",new ActionError("alert.delete.successfull"));   
            }else{
                errors.add("alert",new ActionError("errors.nguonhotro.delete.havechild"));   
            }            
        }
        
        target = anchor;
        String SQL="SELECT nguonhotro_id,parent_id,name FROM dr_nguonhotro WHERE parent_id = ?";        
        String characters="/ ";        
        beans = new BTreeView().getTree(0,true,SQL,characters,"");
        
        BNguonhotro donvi = new BNguonhotro();
        request.setAttribute("BTreeNguonhotros",beans);
        request.setAttribute("BNguonhotros",donvi.getAllRecord(0));
        request.setAttribute("nguonhotro",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FNguonhotro bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.nguonhotro.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.nguonhotro.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.nguonhotro.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.nguonhotro.edit.idnull"));   
            }
        }
    return anchor;
    }
}
