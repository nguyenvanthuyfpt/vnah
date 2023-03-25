package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDantoc;

import com.exp.EException;

import com.form.disability.categorys.FDantoc;

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


public class ADantoc extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDantoc bean =(FDantoc)form;
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        }else if(anchor.equals(_EDIT)){
                BDantoc donvi = new BDantoc();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.dantoc.edit.circle"));   
                }else if(donvi.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dantoc.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDantoc donvi = new BDantoc();
                int saveID = bean.getId();
                bean.setId(0);
                if(donvi.insert(bean)){
                    bean.setName("");
                    bean.setId(0);
                    bean.setCode("");
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dantoc.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDantoc donvi = new BDantoc();
                if(donvi.delete(bean)){
                    bean = new FDantoc();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dantoc.delete.havechild"));   
                }
            target = anchor;
        }

        BDantoc donvi = new BDantoc();
        request.setAttribute("BDantocs",donvi.getAllRecord(0));
        request.setAttribute("dantoc",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDantoc bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dantoc.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dantoc.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dantoc.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dantoc.edit.idnull"));   
            }
        }
    return anchor;
    }
}
