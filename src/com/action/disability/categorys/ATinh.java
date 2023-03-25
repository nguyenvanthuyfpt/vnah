package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FTinh;

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


public class ATinh extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FTinh bean = (FTinh)form;
        BTinh tinh = new BTinh();
        
        String SQL = 
            "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
        String characters = "/ ";
        String member = "";

        FBeans beans = new FBeans();

        bean.setCreator((int)me.getId());
        String anchor = bean.getValue(APP_ANCHOR, "");
        ActionErrors errors = new ActionErrors();
        target = validate(bean, anchor, errors);
        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {

            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", 
                           new ActionError("errors.tinh.edit.circle"));
            } else if (tinh.update(bean)) {
                errors.add("alert", 
                           new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.tinh.update.departmentcode"));
            }
            target = anchor;
        } else if (anchor.equals(_CREATE)) {

            int saveID = bean.getId();
            bean.setId(0);
            if (tinh.insert(bean)) {
                errors.add("alert", 
                           new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.tinh.insert.departmentcode"));
            }
            bean.setId(saveID);

            target = anchor;
        } else if (anchor.equals(_SHOW)) {
            FTinh beanT = new FTinh();
            beanT.setId(bean.getParentID());
            String code = tinh.getMaxCode(beanT);
            //bean = tinh.getRecordByID(bean);
            bean.setCode(code);
            target = _DELETE;
        } else if (anchor.equals(_DELETE)) {

            if (tinh.delete(bean)) {
                bean = new FTinh();
                errors.add("alert", 
                           new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("errors.tinh.delete.havechild"));
            }            
            
            request.setAttribute("BListTinhs", new BTinh().getAllRecordByParentId(bean.getParentID()));            
            anchor = _SUCCESS;
            
        } else if (anchor.equals("_DETAIL")) {

            request.setAttribute("tinh", tinh.getRecordByID(bean));
            request.setAttribute("BListTinhs", new BTinh().getAllRecordByParentId(bean.getParentID()));            
            anchor = _SUCCESS;
        }
        
        if(anchor.equals(_SUCCESS)){

            request.setAttribute("BTinhs", new BTinh().getAllRecord(bean.getId()));
            target = anchor;
        }       
         
         beans = new BTreeView().getTree(0, false, SQL, characters, member);         
         request.setAttribute("BTreeAllTinhs", beans);

//        request.setAttribute("BListTinhs", 
//                             new BTinh().getAllRecordByParentId((bean.getId() > 
//                                                                 0) ? 
//                                                                bean.getId() : 
//                                                                0));

        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }

    private String validate(FTinh bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.tinh.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.tinh.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || 
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.tinh.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", 
                           new ActionError("errors.tinh.edit.idnull"));
            }
        }
        return anchor;
    }
}
