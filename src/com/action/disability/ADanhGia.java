package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDanhGia;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanhGia;

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


public class ADanhGia extends ACore {
    
    final static Logger logger = Logger.getLogger(ADanhGia.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        try {
            final String LOCATION = this + "->executeAction()";
            String target = _LOGOUT;
            ActionErrors errors = new ActionErrors();
            FDanhGia bean = (FDanhGia)form;
  
            FBeans beans = new FBeans();
            BDanhGia bo = new BDanhGia();
            String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
  
            if (anchor.equals("_PREPARED")) {
  
            } else if (anchor.equals(_CREATE)) {
                bean.setUserId((int)me.getId());
                if (bo.insert(bean)) {
                    errors.add("alert", 
                               new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("alert", 
                               new ActionError("alert.insert.unSuccessfull"));
                }
  
                bean.reset();
                request.setAttribute("danhgiaht", bean);
  
                beans = 
                        bo.getAllByNktId(bean.getIdNkt(), bean.getKyDanhGia(), bean.getNamDanhGia());
                target = _SUCCESS;
  
            } else if (anchor.equals("_DETAIL")) {
  
                FDanhGia beantemp = new FDanhGia();
  
                beantemp = bo.getById(bean.getId());
                beantemp.setIdNkt(bean.getIdNkt());
                beantemp.setKyDanhGia(bean.getKyDanhGia());
                beantemp.setNamDanhGia(bean.getNamDanhGia());
                request.setAttribute("danhgiaht", beantemp);
  
                beans = 
                        bo.getAllByNktId(bean.getIdNkt(), bean.getKyDanhGia(), bean.getNamDanhGia());
  
                target = _SUCCESS;
  
            } else if (anchor.equals("_UPDATE")) {
                bean.setUserId((int)me.getId());
                if (bo.update(bean)) {
                    errors.add("alert", 
                               new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", 
                               new ActionError("alert.update.unSuccessfull"));
                }
  
                bean.reset();
                request.setAttribute("danhgiaht", bean);
  
                beans = 
                        bo.getAllByNktId(bean.getIdNkt(), bean.getKyDanhGia(), bean.getNamDanhGia());
                target = _SUCCESS;
  
            } else if (anchor.equals("_VIEW")) {
  
                request.setAttribute("danhgiaht", bo.getById(bean.getId()));
  
                beans = 
                        bo.getAllByNktId(bean.getIdNkt(), bean.getKyDanhGia(), bean.getNamDanhGia());
  
                anchor = _SUCCESS;
  
            } else if (anchor.equals("_DELETE")) {
  
                if (bo.delete(bean.getId())) {
                    errors.add("alert", 
                               new ActionError("alert.delete.successfull"));
                }
                bean.reset();
                request.setAttribute("danhgiaht", bean);
  
                beans = 
                        bo.getAllByNktId(bean.getIdNkt(), bean.getKyDanhGia(), bean.getNamDanhGia());
  
                target = _SUCCESS;
  
            } else if (anchor.equals("_LIST")) {
  
                FDanhGia beantemp = new FDanhGia();
  
                beantemp = bo.getById(bean.getId());
                beantemp.setIdNkt(bean.getIdNkt());
                beantemp.setKyDanhGia(bean.getKyDanhGia());
                beantemp.setNamDanhGia(bean.getNamDanhGia());
  
                beans = 
                        bo.getAllByNktId(beantemp.getIdNkt(), beantemp.getKyDanhGia(), 
                                         beantemp.getNamDanhGia());
  
                target = _SUCCESS;
            }
            
            request.setAttribute("BDanhGias", beans);
  
            if (!errors.isEmpty())
                saveErrors(request, errors);
            return mapping.findForward(target);
        } catch (EException ex) {
            logger.info(ex.toString());
            return mapping.findForward(_ERROR);
        }        
    }
}
