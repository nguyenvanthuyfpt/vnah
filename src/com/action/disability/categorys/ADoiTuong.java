package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDoiTuong;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FDoiTuong;

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


public class ADoiTuong extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDoiTuong bean =(FDoiTuong)form;
        FBeans beans = new FBeans();
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {
            BDoiTuong doituong = new BDoiTuong();
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", new ActionError("errors.doituong.edit.circle"));
            } else if (doituong.update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.update.departmentcode"));
            }
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
            BDoiTuong doituong = new BDoiTuong();
            int saveID = bean.getId();
            bean.setId(0);
            if (doituong.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.insert.departmentcode"));
            }
            bean.setId(saveID);

            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            BDoiTuong doituong = new BDoiTuong();
            if (doituong.delete(bean)) {
                bean = new FDoiTuong();
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.delete.havechild"));
            }
            target = anchor;
        }
        
        String SQL="SELECT doituong_id,parent_id,name FROM dr_doituong WHERE parent_id = ?";        
        String characters="/ ";        
        beans = new BTreeView().getTree(0,true,SQL,characters,"");
        
        BDoiTuong nguyennhan = new BDoiTuong();
        request.setAttribute("BTreeDoiTuongs",beans);
        request.setAttribute("BDoiTuongs",nguyennhan.getAllRecord(0));        
        request.setAttribute("doituong",bean);
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }

    private String validate(FDoiTuong bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.doituong.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.doituong.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.doituong.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.doituong.edit.idnull"));
            }
        }
        return anchor;
    }
}
