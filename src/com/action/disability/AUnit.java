package com.action.disability;


import com.action.ACore;

import com.bo.disability.BUnit;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportUnit;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FUnit;
import com.form.disability.categorys.FTinh;

import com.inf.disability.IKeyDisability;

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


public class AUnit extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGOUT;
        ActionErrors errors;
        errors = new ActionErrors();

        FUnit bean = (FUnit)form; 
        String nameTinh =  "";
        FBeans beans = new FBeans();
        BUnit bo = new BUnit();
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
        target = validate(bean, anchor, errors);

        String characters = "/ ";
        String member = "";

        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
        beans = new BTreeView().getTree(0, false, SQL, characters, member);

        if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }

        request.setAttribute("BTreeTinhs", beans);
        
        if (!errors.isEmpty()) {        
        } else if (anchor.equals("_LIST_SHOW")) {
            target = anchor;            
        } else if (anchor.equals(_CREATE)) {
            if (bo.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("alert.insert.unSuccessfull"));
            }            
            bean.reset();
            request.setAttribute("dr_unit", bean);
            anchor = _SUCCESS;
        } else if (anchor.equals(_EDIT)) {
            if (bo.update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("alert.update.unSuccessfull"));
            }
            request.setAttribute("dr_unit", bo.getById(bean.getId()));
            anchor = _SUCCESS;
        } else if (anchor.equals(_DELETE)) {
            if (bo.delete(bean.getId())) {
                errors.add("alert", new ActionError("alert.delete.successfull"));
            }            
            //bean.setId(0);
            request.setAttribute("dr_unit", bean);
            anchor = _SUCCESS;

        } else if (anchor.equals("_DETAIL")) {
            request.setAttribute("dr_unit", bo.getById(bean.getId()));
            anchor = _SUCCESS;
        } else if (anchor.equals("_EXCEL")) {
            String FileName = IKeyDisability.REPORT_FILE_DISABILITY_UNIT;
            String report   = new DReportUnit().excelUnit(bo.getData(bean.getId_type(), bean.getTinhId()), bean, FileName, bean.getId_type());
            bean.download(report, FileName, null);
            bean.deleteFile(report);
            target = null;
        } else if (anchor.equals(_VIEW)) {            
            if (bean.getTinhId() > 0) {
                FTinh beanCa = new FTinh();
                beanCa.setParentID(bean.getTinhId());
                List params = new ArrayList();
                
                for (int i = bean.getTinhId(); i > 0; i = beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa = new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i = params.size() - 1; i > -1; i--) {
                    nameTinh += " - " + params.get(i);
                }
            }    
            
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
            
            bean.setTinhName(nameTinh);            
            request.setAttribute("dr_unit", bean);
            request.setAttribute("BUnits", bo.getAll(bean));
            target = anchor;
        
        } else if (anchor.equals("_VIEW_HOME")) {     
            request.setAttribute("BUnits", bo.getAll(bean));
            target = anchor;
        } else if (anchor.equals("_SEARCH_RESULT")){                
            request.setAttribute("BUnits", bo.getAll(bean));
            request.setAttribute("total", bean.getTotalResult());
            target = anchor;    
        } else if (anchor.equals("_SELECT_IDTINH")) {
            FTinh beanT = new FTinh();                
            beanT.setId(bean.getTinhId());            
            bean.setTinhName("");
            if (bean.getTinhId() > 0) {
                FTinh beanCa = new FTinh();
                beanCa.setParentID(bean.getTinhId());
                List params = new ArrayList();
                for (int i = bean.getTinhId(); i > 0; 
                    i = beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa = new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i = params.size() - 1; i > -1; i--) {
                    bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                }
            }
            
            request.setAttribute("BCategoryUnits", bo.getAllCategory());
            request.setAttribute("BUnits", bo.getAll(bean));
            request.setAttribute("BTreeTinhs", beans);
            request.setAttribute("dr_unit", bean);           
            target = _CREATE;
        }
        
        if (anchor.equals(_SUCCESS)) {
            request.setAttribute("BCategoryUnits", bo.getAllCategory());
            request.setAttribute("BUnits", bo.getAll(bean));
            request.setAttribute("BTreeTinhs", beans);
            target = anchor;
        }
        
        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }

    private String validate(FUnit bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_EDIT) || anchor.equals(_CREATE)) {

        }
        return anchor;
    }
}
