package com.action.disability;


import com.action.ACore;

import com.bo.disability.BImport;

import com.dao.disability.DImport;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FImport;

import com.lib.AppConfigs;

import com.util.Constant;

import java.io.File;
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


public class AImport extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGOUT;
        ActionErrors errors = new ActionErrors();        
        FImport bean = (FImport)form;
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
        target = validate(bean, anchor, errors);
        if (anchor.equals(_SWAP)) {
            String dirs = 
                AppConfigs.APP_SYSTEM_PATH + AppConfigs.CABIN_FILE_PATH + 
                AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + 
                AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs();
            String filename = "";
            int rowBegin = 0,typeImport = 0;
            boolean haveFile = bean.getUpFile().getFileSize() > 0;
            
            if (haveFile) {
                filename = dirs + encodeFileName(bean.me.getId()) + ".xls";
                rowBegin = bean.getRowBegin();
                typeImport = bean.getTypeImport();
                bean.upload(bean.getUpFile(), filename);
                DImport dao = new DImport();
                bean = dao.readFileExcel(filename, rowBegin);                
                // bean = dao.readFile(filename, rowBegin);
                bean.setRowBegin(rowBegin);
                
                if(typeImport==1){
                    bean.setTableName("dr_area");
                    request.setAttribute(Constant.map_columns, new BImport().getAllColumn("dr_area"));
                } else if (typeImport==3) {
                    
                } else {
                    bean.setTableName("dr_disabilitypeople");
                    request.setAttribute(Constant.map_columns, new BImport().getAllColumn("dr_disabilitypeople"));
                }
                
                bean.setTypeImport(typeImport);
                request.getSession().setAttribute("BDatas", bean);
                request.getSession().setAttribute("BTables", new BImport().getAllTable(false));                
                request.getSession().setAttribute("BImport", bean);                
                
            }
            target = anchor;

        } else if (anchor.equals("_APPLY")) {
            FImport beanTemp = (FImport)request.getSession().getAttribute("BDatas");
            if (new BImport().insert(bean, beanTemp.getDataSheet0())) {
                errors.add("alert", new ActionError("alert.load.todata.successfull",String.valueOf(bean.getNoUpdate()), 
                                                                                    String.valueOf(bean.getNoInsert())));
                request.getSession().removeAttribute("BDatas");
                request.getSession().removeAttribute("BTables");                
            } else {
                errors.add("alert", new ActionError("alert.load.todata.error"));
            }
            target = _SWAP;
        }
        
        request.setAttribute("beanImport", bean);
        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }

    private String encodeFileName(long userID) {
        return userID + "." + System.currentTimeMillis();
    }
    
    private String validate(FImport bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_SWAP)) {
        } else if (anchor.equals("_APPLY")) {
            if ("".equals(bean.getTableName())) {
                errors.add("alert", new ActionError("errors.import.data.must.select.table"));            
            }
        }
        return anchor;
    }
}
