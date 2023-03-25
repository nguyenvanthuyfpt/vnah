package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDanhGia;
import com.bo.disability.BDisability;
import com.bo.disability.BSupport;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanhGia;
import com.form.disability.FDisability;
import com.form.disability.FSupport;

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


public class ASupport extends ACore {
    
    final static Logger logger = Logger.getLogger(ASupport.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws EException, IOException, ServletException,
                                                                            SQLException {
           
        try {
            final String LOCATION = this + "->executeAction()";
            String target = _LOGOUT;
            ActionErrors errors = new ActionErrors();
            FSupport bean;
            bean = (FSupport)form;
            BSupport bo = new BSupport();
            String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
            FDisability beanDis = new FDisability();
            beanDis.setId(bean.getIdNkt());
            request.setAttribute("BNkts", new BDisability().getRecordByID(beanDis));
    
    
            if (anchor.equals("_LIST_SHOW")) {
                target = anchor;
            } else if (anchor.equals("_INSERT_SUPPORT")) {
    
                bean.setUserId((int)me.getId());
    
                if (bean.getStatusId() == 0) {
                    bean.setDateForm("");
                    bean.setDateTo("");
                }
    
                if (bo.insert(bean)) {
                    errors.add("alert", new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.insert.unSuccessfull.6.month"));
                }
    
                bean.reset();
                bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                bean.setDateForm(bean.dateToString(bean.getCurrentDate()));
                bean.setDateTo(bean.dateToString(bean.getCurrentDate()));
    
                request.setAttribute("support", bean);
                target = "_SUPPORT";
    
            } else if (anchor.equals("_LIST_SUPPORT_TYPE")) {
    
                FSupport beantemp = new FSupport();
                beantemp.setIdNkt(bean.getId());
                request.setAttribute("support", beantemp);
                String characters = "";
                String member = "";
                String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";
                FBeans beans = new FBeans();
                beans = new BTreeView().getTree(bean.getId(), true, SQL_HOTRO, characters, member);
                request.setAttribute("BSupportsType", beans);
                target = anchor;
    
            } else if (anchor.equals("_VIEW_LIST")) {
    
                request.setAttribute("BSupportTrailers", bo.getAllByIdNkt(bean.getIdNkt(), bean.getStatusId()));
                request.setAttribute("support", bean);
                target = anchor;
    
            } else if (anchor.equals("_UPDATE_SUPPORT")) {
    
                bean.setUserId((int)me.getId());
    
                if (bean.getStatusId() == 0) {
                    bean.setDateForm("");
                    bean.setDateTo("");
                }
    
                if (bo.update(bean)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.update.unSuccessfull"));
                }
    
                bean.reset();
                bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                request.setAttribute("support", bean);
                target = "_SUPPORT";
    
            } else if (anchor.equals("_VIEW_COMBO_BOX")) {
                FBeans subSupport = new FBeans();
                String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";                        
                subSupport = new BTreeView().getTreeList(bean.getId(), SQL_HOTRO, "", "");
                
                /*    
                if (bean.getHotroIds().indexOf("#10#") > 0)
                    bean.setTrocapThuongXuyenKhac("");
    
                if (bean.getHotroIds().indexOf("#14#") > 0)
                    bean.setPhauthuatKhac("");
    
                if (bean.getHotroIds().indexOf("#55#") > 0)
                    bean.setYteKhac("");
    
                if (bean.getHotroIds().indexOf("#229#") > 0)
                    bean.setNhucauDoiSongKhac("");
    
                if (bean.getHotroIds().indexOf("#99#") > 0)
                    bean.setNhucauGiaoDucKhac("");
    
                if (bean.getHotroIds().indexOf("#99#") > 0)
                */
                request.setAttribute("subSupport", subSupport);
                request.setAttribute("support", bean);            
                target = "_SUPPORT";
    
            } else if (anchor.equals("_CHANGE_COMBO_BOX")) {
    
                request.setAttribute("support", bean);
                target = "_SUPPORT";
    
            } else if (anchor.equals("_DELETE_SUPPORT")) {
    
                /*if (bo.delete(bean.getId())) {
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.delete.unSuccessfull"));
                }*/
    
                bean.reset();
                bean.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                bean.setDateForm(bean.dateToString(bean.getCurrentDate()));
                bean.setDateTo(bean.dateToString(bean.getCurrentDate()));
                request.setAttribute("support", bean);
                target = "_SUPPORT";
    
            } else if (anchor.equals("_DETAIL_SUPPORT")) {
    
                //request.setAttribute("support", bo.getById(bean.getId()));
                target = "_SUPPORT";
    
            } else if (anchor.equals("_SUPPORT_DANHGIA")) {
    
                FSupport beanT = new FSupport();
                //beanT = bo.getById(bean.getId());
                FDanhGia beanDG = new FDanhGia();
                BDanhGia boDG = new BDanhGia();
                beanDG.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                request.setAttribute("danhgiaht", beanDG);
                //request.setAttribute("BDanhGiaTrailers",boDG.getAllByIdSupport(1,0));
                target = anchor;
    
            }
    
            String characters = "";
            String member = "";
            String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";
            String SQL_NGUONHOTRO = "SELECT nguonhotro_id,parent_id,name FROM DR_NGUONHOTRO WHERE parent_id = ?  ORDER BY position";
            
            FBeans HoTrobeans = new FBeans();
            FBeans NguonHoTrobeans = new FBeans();
            HoTrobeans = new BTreeView().getTreeList(0, SQL_HOTRO, characters, member);            
            NguonHoTrobeans = new BTreeView().getTreeList(0, SQL_NGUONHOTRO, characters, member);
            request.setAttribute("BSupports", HoTrobeans);
            request.setAttribute("BNguonHoTros", NguonHoTrobeans);
            
            request.setAttribute("BSupportTrailers", bo.getAllByIdNkt(bean.getIdNkt(), bean.getStatusId()));
    
            if (!errors.isEmpty())
                saveErrors(request, errors);
            return mapping.findForward(target);
        } catch (EException ex) {
            logger.info(ex.toString());
            return mapping.findForward(_ERROR);
        }
    }
}
