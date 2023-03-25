package com.action.disability;


import com.action.ACore;

import com.bo.admin.users.BUsers;
import com.bo.disability.BPopulation;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.disability.FPopulation;
import com.form.disability.categorys.FTinh;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class APopulation extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGOUT;
        ActionErrors errors = new ActionErrors();
        FPopulation bean = (FPopulation)form;
        BPopulation bo = new BPopulation();
        String anchor = bean.getValue(APP_ANCHOR, "");
        if (anchor.equals("_LIST_SHOW")) {
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
            bean.setId(0);
            if (bo.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("alert.insert.unSuccessfull"));
            }
            bean.reset();
            bean.setYearOfPeriod(bean.getYear(bean.getCurrentSqlDate()));
            request.setAttribute("population", bean);
            target = "_POPULATION";
        } else if (anchor.equals(_EDIT)) {
            if (bo.update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("alert.update.unSuccessfull"));
            }
            bean.setYearOfPeriod(bean.getYear(bean.getCurrentSqlDate()));
            request.setAttribute("population", bean);
            target = "_POPULATION";
        } else if (anchor.equals(_DELETE)) {
            bo.delete(bean.getId());
            bean.reset();
            bean.setYearOfPeriod(bean.getYear(bean.getCurrentSqlDate()));
            request.setAttribute("population", bean);
            target = "_POPULATION";
        } else if (anchor.equals("_DETAIL")) {
            bean = bo.getById(bean.getId());
            //bean.setYearOfPeriod(bean.getYear(bean.getCurrentSqlDate()));
            request.setAttribute("population", bean);
            target = "_POPULATION";
        }
        if (anchor.equals(_VIEW)) {
            request.setAttribute("BPopulations", bo.getAllByIdTinh(bean.getId_tinh()));
            target = anchor;
        } else {
            FUser beanU = new FUser();
            beanU.setId((int)me.getId());
            beanU = new BUsers().getRecordByID(beanU);
            String membersRule =beanU.getArea();
            FBeans beans=new FBeans();
            String SQL="SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
            if (membersRule!=null && !membersRule.equals("")){
                membersRule = membersRule.substring(1,membersRule.length()-1);
                SQL += " AND (tinh_id in (" + membersRule + ")" + " or tinh_id IN (select tinh_id from dr_area where parent_id =0 order by tinh_id LIMIT 1 ))" ;
            }
            String characters="/ ";
            beans=new BTreeView().getTree(0,true,SQL,characters,"");
            request.setAttribute("BTreeTinhs",beans);
            bean.setTinhName("");
            if(bean.getId_tinh()>0){
                FTinh beanCa=new FTinh();
                beanCa.setParentID(bean.getId_tinh());
                List params =new ArrayList();
                for (int i=bean.getId_tinh();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i=params.size()-1;i>-1;i--) {
                    bean.setTinhName(bean.getTinhName()+" - "+params.get(i));
                }
            } 
            request.setAttribute("BPopulations",bo.getAllByIdTinh(bean.getId_tinh()));
        }
        
        request.setAttribute("subanchor", "01.02");
        request.setAttribute("anchor", "01");
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
