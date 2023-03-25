package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDisability;
import com.bo.disability.BRank;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisability;
import com.form.disability.FRank;

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
        String target = _LOGOUT;
        ActionErrors errors = new ActionErrors();
        FRank bean = (FRank)form;
        BRank bo = new BRank();
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");

        FDisability beanDis = new FDisability();
        beanDis.setId(bean.getIdNkt());
        request.setAttribute("BNkts", 
                             new BDisability().getRecordByID(beanDis));

        if (anchor.equals("_LIST_SHOW")) {
            request.setAttribute("rank", bean);
            target = "_LIST";

        } else if (anchor.equals("_INSERT_RANK")) {
            bean.setUserId((int)me.getId());
            
            if (bo.insert(bean)) {
                errors.add("alert", 
                           new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("alert.insert.unSuccessfull.6.month"));
            }
            bean.reset();
            bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
            request.setAttribute("rank", bean);
            target = "_LIST";

        } else if (anchor.equals("_UPDATE_RANK")) {
            bean.setUserId((int)me.getId());
            
            if (bo.update(bean)) {
                errors.add("alert", 
                           new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("alert.update.unSuccessfull"));
            }
            
            bean.reset();
            bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
            request.setAttribute("rank", bean);
            target = "_LIST";

        } else if (anchor.equals("_DELETE_RANK")) {
           
            if ( bo.delete(bean.getId())) {
                errors.add("alert", 
                           new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", 
                           new ActionError("alert.delete.unSuccessfull"));
            }            
            
            bean.reset();
            bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
            request.setAttribute("rank", bean);
            target = "_LIST";

        } else if (anchor.equals("_DETAIL_RANK")) {
            /*
            FRank beantemp = new FRank();
            beantemp = bo.getById(bean.getId());
            beantemp.setIdNkt(bean.getIdNkt());
            request.setAttribute("rank", beantemp);
            target = "_LIST";
            */
        } else if (anchor.equals("_SELECT_CHECKBOX")) {

            request.setAttribute("rank", bean);
            target = "_TINHHINH";
        }

        String characters = "";
        String member = "";
        String SQL_HOTRO = 
            "SELECT tinhtrang_id,parent_id,name FROM DR_TINHTRANG WHERE parent_id = ? order by order_by";
        FBeans beans = new FBeans();
        beans = new BTreeView().getTreeList(0, SQL_HOTRO, characters, member);
        request.setAttribute("BRanks", beans);
        request.setAttribute("BRankTrailers", 
                             bo.getAllByIdNkt(bean.getIdNkt()));
        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }
}
