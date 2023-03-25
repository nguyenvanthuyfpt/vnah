package com.action.disability;


import com.action.ACore;

import com.bo.disability.BThongTinTuyen;
import com.bo.disability.categorys.BDieuKien;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FThongTinTuyen;
import com.form.disability.categorys.FTinh;

import java.io.IOException;

import java.sql.Connection;
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


public class AThongTinTuyen extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();   
        
        FThongTinTuyen bean = (FThongTinTuyen)form;
        String nameTinh =  "";
        BThongTinTuyen bo   = new BThongTinTuyen();        
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
        
        target = validate(bean,anchor,errors);
        
        FBeans beans = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
        String characters = "/ ";
        String member = "";
         
        if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }

        beans = new BTreeView().getTree(0, false, SQL, characters, member);
        request.setAttribute("BTreeTinhs", beans);
        
        if(!errors.isEmpty()){
        
        } else {            
            if(anchor.equals(_VIEW)){
               if (bean.getId_tinh() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params = new ArrayList();
                    
                    for (int i = bean.getId_tinh(); i > 0; i = beanCa.getParentID()) {
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
                request.setAttribute("thongtinTuyen", bean);
                request.setAttribute("BThongTinTuyens", bo.getAll(bean));
                target = anchor;           
            } else if (anchor.equals(_CREATE)) {    
                DTinh daoT = new DTinh();                
                Connection cnn  = DBConnector.getConnection();
                nameTinh = daoT.getTinhNameById(cnn,bean.getId_tinh());                
                bean.setName_tinh(nameTinh);
            
                if (bo.insert(bean)) {
                    errors.add("alert", new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.insert.unSuccessfull.already.insert"));
                }
                
                bean.reset();                
                bean.setId_tinh(bean.getId_tinh());
                
                if (bean.getId_tinh() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params = new ArrayList();
                    
                    for (int i = bean.getId_tinh(); i > 0; i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));                
                target = _SUCCESS;
                    
            } else if (anchor.equals(_EDIT)) {

                if (bo.update(bean)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.update.unSuccessfull"));
                }
                
                bean.reset();
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));
                target = _SUCCESS;
            } else if (anchor.equals("_DETAIL")) {
                bean = bo.getById(bean.getId());
                bean.setTinhName("");
                
                if (bean.getId_tinh() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params = new ArrayList();
                    
                    for (int i = bean.getId_tinh(); i > 0; i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("thongtinTuyen", bean);
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));
                target = _SUCCESS;
                
            } else if (anchor.equals(_DELETE)){
                if(bo.delete(bean.getId())){
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } 
                
                bean.reset();                
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));
                target = _SUCCESS;
                
            } else if(anchor.equals("_VIEW_OPTION_INFO")) {
                bean.setId_tinh(bean.getId_tinh());
                bean.setTinhName("");
                bean.reset();
                
                if (bean.getId_tinh() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params = new ArrayList();
                    
                    for (int i = bean.getId_tinh(); i > 0; i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("thongtinTuyen", bean);
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));
                anchor = "_PREPARED_CREATE_INFO";
                target = anchor;
            } else if (anchor.equals("_SELECT_IDTINH")) {
                FTinh beanT = new FTinh();                
                beanT.setId(bean.getId_tinh());
                request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));               
                bean.setTinhName("");
    
                if (bean.getId_tinh() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params = new ArrayList();
                    for (int i = bean.getId_tinh(); i > 0; 
                        i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("thongtinTuyen", bean);
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(bean));
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = _CREATE;
            }
        }  
        
        request.setAttribute("anchor", "01");
        request.setAttribute("subanchor", "01.03");
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
    
    
    private String validate(FThongTinTuyen bean,String anchor,ActionErrors errors){
       
       if (anchor.equals(_CREATE) || anchor.equals(_EDIT)){
           if (bean.getId_tinh()==0){
               errors.add("alert",new ActionError("alert.thongtintuyen.select.area"));
           }
       }            
       return anchor;
    }
}
