package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BQuanhe;

import com.exp.EException;

import com.form.disability.categorys.FQuanhe;

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


public class AQuanhe extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws EException, IOException, ServletException,
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FQuanhe bean = (FQuanhe)form;
        String anchor = bean.getValue(APP_ANCHOR, "");
        ActionErrors errors = new ActionErrors();
        target = validate(bean, anchor, errors);
        if (!errors.isEmpty()) {

        } else if (anchor.equals(_EDIT)) {
            BQuanhe quanhe = new BQuanhe();
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", new ActionError("errors.lydo.edit.circle"));
            } else if (quanhe.update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.lydo.update.departmentcode"));
            }
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
            BQuanhe quanhe = new BQuanhe();
            int saveID = bean.getId();
            bean.setId(0);
            if (quanhe.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.lydo.insert.departmentcode"));
            }
            bean.setId(saveID);

            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            BQuanhe quanhe = new BQuanhe();
            if (quanhe.delete(bean)) {
                bean = new FQuanhe();
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.lydo.delete.havechild"));
            }
            target = anchor;
        }

        BQuanhe quanhe = new BQuanhe();
        request.setAttribute("BLydos", quanhe.getAllRecord(0));
        request.setAttribute("quanhe", bean);

        if (!errors.isEmpty())
            saveErrors(request, errors);

        return mapping.findForward(target);
    }

    private String validate(FQuanhe bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.lydo.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.lydo.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.lydo.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.lydo.edit.idnull"));
            }
        }
        return anchor;
    }
}
