package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDangTat;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FDangTat;

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


public class ADangTat extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDangTat bean = (FDangTat)form;
        String anchor = bean.getValue(APP_ANCHOR, "");
        ActionErrors errors = new ActionErrors();
        target = validate(bean, anchor, errors);

        if (!errors.isEmpty()) {

        } else if (anchor.equals(_EDIT)) {

            BDangTat dangtat = new BDangTat();
            int isOther = bean.getIsOther();
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", 
                           new ActionError("errors.dangtat.edit.circle"));
            } else if (dangtat.update(bean)) {
                errors.add("alert", 
                           new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.dangtat.update.departmentcode"));
            }

            bean.setIsOther(isOther);
            target = anchor;

        } else if (anchor.equals(_CREATE)) {

            BDangTat dangtat = new BDangTat();
            int saveID = bean.getId();
            int isOther = bean.getIsOther();
            bean.setId(0);
            if (dangtat.insert(bean)) {
                errors.add("alert", 
                           new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.dangtat.insert.departmentcode"));
            }

            bean.setIsOther(isOther);
            bean.setId(saveID);
            target = anchor;

        } else if (anchor.equals(_DELETE)) {

            BDangTat dangtat = new BDangTat();
            if (dangtat.delete(bean)) {
                bean = new FDangTat();
                errors.add("alert", 
                           new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.dangtat.delete.havechild"));
            }
            target = anchor;
        }

        String SQL = 
            "SELECT dangtat_id,parent_id,name FROM dr_classification WHERE parent_id = ?";
        String characters = "/ ";
        FBeans beans = new FBeans();
        beans = new BTreeView().getTree(0, true, SQL, characters, "");

        request.setAttribute("BTreeDangTats", beans);
        request.setAttribute("BDangTats", new BDangTat().getAllRecord(0));
        request.setAttribute("dangtat", bean);

        if (!errors.isEmpty())
            saveErrors(request, errors);

        return mapping.findForward(target);
    }

    private String validate(FDangTat bean, String anchor, 
                            ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", 
                           new ActionError("errors.dangtat.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.dangtat.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", 
                           new ActionError("errors.dangtat.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.dangtat.edit.idnull"));
            }
        }
        return anchor;
    }
}
