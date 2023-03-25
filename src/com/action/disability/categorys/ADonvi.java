package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDonvi;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FDonvi;

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


public class ADonvi extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDonvi bean =(FDonvi)form;
        FBeans beans = new FBeans();
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        }else if(anchor.equals(_EDIT)){
                BDonvi donvi = new BDonvi();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.donvi.edit.circle"));   
                }else if(donvi.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.donvi.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDonvi donvi = new BDonvi();
                int saveID = bean.getId();
                bean.setId(0);
                if(donvi.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.donvi.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDonvi donvi = new BDonvi();
                if(donvi.delete(bean)){
                    bean = new FDonvi();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.donvi.delete.havechild"));   
                }
            target = anchor;
        }
        
        String SQL="SELECT donvi_id,parent_id,name FROM dr_donvi WHERE parent_id = ?";        
        String characters="/ ";        
        beans = new BTreeView().getTree(0,true,SQL,characters,"");
        
        BDonvi donvi = new BDonvi();
        request.setAttribute("BTreeDonvis",beans);
        request.setAttribute("BDonvis",donvi.getAllRecord(0));
        request.setAttribute("donvi",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDonvi bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.donvi.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.donvi.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.donvi.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.donvi.edit.idnull"));   
            }
        }
    return anchor;
    }
}
