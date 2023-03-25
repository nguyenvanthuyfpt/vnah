package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BRank;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FRank;

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


public class ARank extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws EException,
                                                                            IOException,
                                                                            ServletException,
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FRank bean = (FRank)form;
        
        String anchor = bean.getValue(APP_ANCHOR, "");
        ActionErrors errors = new ActionErrors();
        //target = validate(bean, anchor, errors);

        String characters = "/ ";
        String member = "";
        String SQL = "SELECT id,parent_id,name FROM kpi_rank WHERE parent_id = ?";

        FBeans optRanks = new FBeans();
        optRanks = new BTreeView().getTree(0, false, SQL, characters, member);

        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert",
                           new ActionError("errors.doituong.edit.circle"));
            } else if (new BRank().update(bean)) {
                errors.add("alert",
                           new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert",
                           new ActionError("errors.doituong.update.departmentcode"));
            }
            request.setAttribute("rank", bean);
            request.setAttribute("optRanks", optRanks);
            request.setAttribute("menuRanks", new BRank().getAllRecord(0));
            //request.setAttribute("listRanks", new BRank().getAll(bean));
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
            BRank rank = new BRank();
            bean.setId(0);
            if (rank.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.rank.duplicate.code"));
            }
           request.setAttribute("rank", bean);
            bean.reset();
            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            if (new BRank().delete(bean)) {
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.delete.havechild"));
            }
            bean.reset();
            target = anchor;
        } else if (anchor.equals(_VIEW)) {
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
            target = anchor;
        } else if (anchor.equals("_DETAIL")) {
            if (bean.getDtlId()>0) {
                bean.setId(bean.getDtlId());
                FRank beanTemp = new BRank().getRecordByID(bean);
                request.setAttribute("rank", beanTemp);
            }
            
            request.setAttribute("optRanks", optRanks);
            request.setAttribute("menuRanks", new BRank().getAllRecord(0));
            target = anchor;
        }
        
        // request.setAttribute("rank", bean);
        request.setAttribute("optRanks", optRanks);
        request.setAttribute("menuRanks", new BRank().getAllRecord(0));
        request.setAttribute("listRanks", new BRank().getAllRecordByParent(bean.getParentID()));
        //request.setAttribute("listRanks", new BRank().getAll(bean));
        if (!errors.isEmpty())
            saveErrors(request, errors);

        return mapping.findForward(target);
    }

    private String validate(FRank bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) ||
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.rank.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.rank.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) ||
                 bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.rank.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.rank.edit.idnull"));
            }
        }
        return anchor;
    }
}
