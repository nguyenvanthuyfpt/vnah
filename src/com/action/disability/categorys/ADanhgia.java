package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDanhgia;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FDanhgia;

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


public class ADanhgia extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDanhgia bean =(FDanhgia)form;
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        }else if(anchor.equals(_EDIT)){
                BDanhgia danhgia = new BDanhgia();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.danhgia.edit.circle"));   
                }else if(danhgia.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.danhgia.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDanhgia danhgia = new BDanhgia();
                int saveID = bean.getId();
                bean.setId(0);
                if(danhgia.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.danhgia.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDanhgia danhgia = new BDanhgia();
                if(danhgia.delete(bean)){
                    bean = new FDanhgia();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.danhgia.delete.havechild"));   
                }
            target = anchor;
        }
        
        String characters="/ ";
        String member="";
        String SQL="SELECT tinhtrang_id,parent_id,name FROM dr_tinhtrang WHERE parent_id = ?";
        
        FBeans beans=new FBeans();
        beans=new BTreeView().getTree(0,false,SQL,characters,member);

        BDanhgia danhgia = new BDanhgia();
        request.setAttribute("BTreeDanhgias",beans);
        request.setAttribute("BDanhgias",danhgia.getAllRecord(0));
        request.setAttribute("danhgia",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDanhgia bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.danhgia.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.danhgia.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.danhgia.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.danhgia.edit.idnull"));   
            }
        }
    return anchor;
    }
}
