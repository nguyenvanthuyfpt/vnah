package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BHotro;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FHotro;

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


public class AHotro extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FHotro bean = (FHotro)form;
        String anchor = bean.getValue(APP_ANCHOR, "");
        ActionErrors errors = new ActionErrors();
        target = validate(bean, anchor, errors);
        if (!errors.isEmpty()) {
        
        } else if (anchor.equals(_EDIT)) {
            BHotro hotro = new BHotro();
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", 
                           new ActionError("errors.hotro.edit.circle"));
            } else if (hotro.update(bean)) {
                errors.add("alert", 
                           new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.hotro.update.departmentcode"));
            }
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
        
            BHotro hotro = new BHotro();
            int saveID = bean.getId();
            bean.setId(0);
            if (hotro.insert(bean)) {
                errors.add("alert", 
                           new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.hotro.insert.departmentcode"));
            }
            bean.setId(saveID);

            target = anchor;
        } else if (anchor.equals(_DELETE)) {
        
            BHotro hotro = new BHotro();
            if (hotro.delete(bean)) {
                bean = new FHotro();
                errors.add("alert", 
                           new ActionError("alert.delete.successfull"));

            } else {
                errors.add("alert", 
                           new ActionError("errors.hotro.delete.havechild"));
            }
            target = anchor;
        }

        String characters = "/ ";
        String member = "";
        String SQL = 
            "SELECT hotro_id,parent_id,name FROM dr_hotro WHERE parent_id = ?";

        FBeans beans = new FBeans();
        beans = new BTreeView().getTree(0, false, SQL, characters, member);

        
        request.setAttribute("hotro", bean);
        request.setAttribute("BHotros", new BHotro().getAllRecord(0));
        request.setAttribute("BTreeHotros", beans);

        if (!errors.isEmpty())
            saveErrors(request, errors);

        return mapping.findForward(target);
    }

    private String validate(FHotro bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", 
                           new ActionError("errors.hotro.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.hotro.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", 
                           new ActionError("errors.hotro.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.hotro.edit.idnull"));
            }
        }
        return anchor;
    }
}
