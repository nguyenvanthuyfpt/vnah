package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDungcu;

import com.exp.EException;

import com.form.disability.categorys.FDungcu;

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


public class ADungcu extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDungcu bean =(FDungcu)form;
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        }else if(anchor.equals(_EDIT)){
                BDungcu dungcu = new BDungcu();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.dungcu.edit.circle"));   
                }else if(dungcu.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dungcu.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDungcu dungcu = new BDungcu();
                int saveID = bean.getId();
                bean.setId(0);
                if(dungcu.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dungcu.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDungcu dungcu = new BDungcu();
                if(dungcu.delete(bean)){
                    bean = new FDungcu();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dungcu.delete.havechild"));   
                }
            target = anchor;
        }

        BDungcu dungcu = new BDungcu();
        request.setAttribute("BDungcus",dungcu.getAllRecord(0));
        request.setAttribute("dungcu",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDungcu bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dungcu.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dungcu.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dungcu.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dungcu.edit.idnull"));   
            }
        }
    return anchor;
    }
}
