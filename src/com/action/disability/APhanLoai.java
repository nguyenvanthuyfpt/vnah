package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDisability;
import com.bo.disability.BPhanLoai;
import com.bo.disability.categorys.BDangTat;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisability;
import com.form.disability.FPhanLoai;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class APhanLoai extends ACore {
    
    final static Logger logger = Logger.getLogger(APhanLoai.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        try{ 
            final String LOCATION = this + "->executeAction()";
            String target = _LOGOUT;
    
            ActionErrors errors = new ActionErrors();
            String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
            FDisability beanDis = new FDisability();
            
            FPhanLoai bean = (FPhanLoai)form;
            BPhanLoai bo = new BPhanLoai();
    
            beanDis.setId(bean.getIdNkt());
            request.setAttribute("BNkts", 
                                 new BDisability().getRecordByID(beanDis));
    
            if (anchor.equals("_LIST_SHOW")) {
                target = anchor;
    
            } else if (anchor.equals("_SELECT_PHANLOAI")) {
            
                if(bean.getDangTatIds().indexOf("#188#")<0)
                    bean.setVanDongKhac("");
                    
                if(bean.getDangTatIds().indexOf("#206#")<0)
                    bean.setKhuyetTatKhac("");
                    
                
                request.setAttribute("phanloai", bean);
                target = "_PHANLOAI";
    
            } else if (anchor.equals("_INSERT_PHANLOAI")) {
    
                bean.setUserId((int)me.getId());
                if (bo.insert(bean)) {
                    errors.add("alert", 
                               new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("errors", 
                               new ActionError("alert.insert.unSuccessfull"));
                }
                bean.reset();
                bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
    
                request.setAttribute("phanloai", bean);
                target = "_PHANLOAI";
    
            } else if (anchor.equals("_UPDATE_PHANLOAI")) {
    
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
                request.setAttribute("phanloai", bean);
                target = "_PHANLOAI";
    
            } else if (anchor.equals("_DELETE_PHANLOAI")) {
                
                if(bo.delete(bean.getId())){
                    errors.add("alert", 
                               new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", 
                               new ActionError("alert.delete.unSuccessfull"));
                }
                
                bean.reset();
                bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                request.setAttribute("rank", bean);
                request.setAttribute("phanloai", bean);
                target = "_PHANLOAI";
    
            } else if (anchor.equals("_DETAIL_PHANLOAI")) {
    
                FPhanLoai beantemp = new FPhanLoai();
                beantemp = bo.getById(bean.getId());
                beantemp.setIdNkt(bean.getIdNkt());
                request.setAttribute("phanloai", beantemp);
                target = "_PHANLOAI";
            }
    
            String characters = "/ ";
            String SQL_Nguyennhan = "SELECT nguyennhan_id,parent_id,name FROM dr_nguyennhan WHERE parent_id = ?";
            String SQL_Mucdo = "SELECT mucdo_id,parent_id,name FROM dr_mucdo WHERE parent_id = ?";
            FBeans NguyennhanBeans = new FBeans();
            FBeans MucdoBeans = new FBeans();
            String member = "";
            NguyennhanBeans = new BTreeView().getTree(0, false, SQL_Nguyennhan, characters, member);
            MucdoBeans = new BTreeView().getTree(0, false, SQL_Mucdo, characters, member);
            
            request.setAttribute("BTreeNguyennhans", NguyennhanBeans);
            request.setAttribute("BTreeMucdos", MucdoBeans);
            request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));
            request.setAttribute("BPhanLoaiTrailers", bo.getAllByIdNkt(bean.getIdNkt()));
            if (!errors.isEmpty())
                saveErrors(request, errors);
            return mapping.findForward(target);
        } catch (EException ex) {
            logger.info(ex.toString());
            return mapping.findForward(_ERROR);
        }
    }
}
