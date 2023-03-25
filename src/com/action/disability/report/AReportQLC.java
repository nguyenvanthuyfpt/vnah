package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportQLC;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportQLC;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportQLC;

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


public class AReportQLC extends ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportQLC bean = (FReportQLC)form;
        String anchor = bean.getValue(APP_ANCHOR,"");                
        String func = bean.getFunc();
        
        FBeans beans        = new FBeans();
        FBeans tinhs = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = "";
        if ((bean.me.getDepartmentName() != null) && 
            (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }
         
        tinhs = new BTreeView().getTree(0, false, SQL, characters, member);
        int level = 0;
        String diaban = "", diaban_par = "";
        String ky_bc = bean.getKyBC() + "/" + bean.getNamBC();
        if(bean.getId_tinh()>0){
            List params     = new ArrayList();
            FTinh beanCa    = new FTinh();
            beanCa.setParentID(bean.getId_tinh());            
            
            for (int i=bean.getId_tinh();i>0;i=beanCa.getParentID()) {
                beanCa.setId(beanCa.getParentID());
                beanCa=new BTinh().getRecordByID(beanCa);
                params.add(beanCa.getName());
            }
            for (int i=params.size()-1;i>-1;i--) {            
                level++;
                bean.setNameArea(bean.getNameArea()!="" ? bean.getNameArea()+" - "+params.get(i): "" + params.get(i) );
            }
        }
        
        if(!errors.isEmpty()){
        } else {        
            if(anchor.equals("_REPORT_QLC_DETAIL")){
                request.setAttribute("reportqlc",bean);
                request.setAttribute("BTreeTinhs",tinhs);
                request.setAttribute("anchor","03");                
                request.setAttribute("subanchor","03.06");
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                String FileName =  "";                
                
                BTinh boTinh    = new BTinh();
                FTinh beanTinh  = new FTinh();
                beanTinh.setId(bean.getId_tinh());
                beanTinh        = boTinh.getRecordByID(beanTinh);
                
                if (level==1) {
                    errors.add("alert",new ActionError("alert.disability.error.qlc.detail"));
                    target = anchor;
                } else if (bean.getNamBC()<2014) {
                    errors.add("alert",new ActionError("alert.disability.error.qlc.time"));
                    target = anchor;
                } else {
                    try {
                        if (level == 3)    FileName = IKeyDisability.FILE_REPORT_QLC_BIENDONG_PXA;
                        else if (level == 2)    FileName = IKeyDisability.FILE_REPORT_QLC_BIENDONG_QH;               
                        DReportQLC dao = new DReportQLC();                
                        beans = new BReportQLC().getDataDetail(bean.getId_tinh(), level, ky_bc, "1");                
                        FReportQLC beanTemp = new FReportQLC();
                        beanTemp.setStore(beans);
                        beanTemp.setNameArea(beanTinh.getName());
                        beanTemp.setKyBC(bean.getKyBC());
                        beanTemp.setNamBC(bean.getNamBC());  
                        
                        beans = new BReportQLC().getDataDetail(bean.getId_tinh(), level, ky_bc, "2");
                        beanTemp.setStore1(beans);
                        
                        String report = dao.exportExcelDetail(level, beanTemp, bean, FileName);
                        bean.download(report, FileName ,null);                
                        bean.deleteFile(report);
                        request.setAttribute("func", "_QUANLYCA");
                        target = anchor;
                    }  catch (Exception ex) {
                        request.setAttribute("reportSystem",bean);
                        request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                        errors.add("alert",new ActionError("alert.disability.error.detail",ex.toString().replaceAll("com.exp.EException:","")));
                        target=_ERROR;    
                    }
                }
            } else if(anchor.equals("_REPORT_QLC_COLLECT")) {
                bean.setLevel(level);
                request.setAttribute("reportqlc",bean);
                request.setAttribute("BTreeTinhs",tinhs);
                request.setAttribute("anchor","03");                
                request.setAttribute("subanchor","03.07");
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                String FileName =  "";                
                
                BTinh boTinh    = new BTinh();
                FTinh beanTinh  = new FTinh();
                beanTinh.setId(bean.getId_tinh());
                beanTinh        = boTinh.getRecordByID(beanTinh);
                
                if (level == 1){
                    errors.add("alert",new ActionError("alert.disability.error.qlc.collect"));
                    target = anchor;
                } else if (bean.getNamBC()<2014) {
                    errors.add("alert",new ActionError("alert.disability.error.qlc.time"));
                    target = anchor;
                } else {
                    try {
                        if (level == 3)         FileName = IKeyDisability.FILE_REPORT_QLC_TONGHOP_PXA;
                        else if (level == 2)    FileName = IKeyDisability.FILE_REPORT_QLC_TONGHOP_QH;

                        DReportQLC dao = new DReportQLC();
                        beans = new BReportQLC().getDataCollect(bean.getId_tinh(), ky_bc, "nkt", "");                        
                        FReportQLC beanTemp = new FReportQLC();
                        beanTemp.setStore(beans);
                        beanTemp.setNameArea(beanTinh.getName());
                        beanTemp.setKyBC(bean.getKyBC());
                        beanTemp.setNamBC(bean.getNamBC());
                        beanTemp.setLevel(bean.getLevel());
                        
                        beans = new BReportQLC().getDataCollect(bean.getId_tinh(), ky_bc, "qlc", "");
                        beanTemp.setStore1(beans);
                        
                        beans = new BReportQLC().getDataCollect(bean.getId_tinh(), ky_bc, "ctkn", "QLTH");
                        beanTemp.setStore2(beans);
                        
                        beans = new BReportQLC().getDataCollect(bean.getId_tinh(), ky_bc, "ctkn", "NC_QLTH");
                        beanTemp.setStore3(beans);
                        
                        String report = dao.exportExcelCollect(level, beanTemp, bean, FileName);
                        bean.download(report, FileName, null);
                        bean.deleteFile(report);
                        request.setAttribute("func", "_QUANLYCA");
                        target = anchor;
                    } catch (Exception ex) {
                        request.setAttribute("reportSystem", bean);
                        request.setAttribute("errorValue", ex.toString().replaceAll("com.exp.EException:", ""));
                        errors.add("alert",new ActionError("alert.disability.error.detail", ex.toString().replaceAll("com.exp.EException:","")));
                        target = _ERROR;
                    }
                }
            }
        }        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
    
    private String validate(FReportQLC bean, String anchor, ActionErrors errors) throws EException {
        int level = 0;
        level = bean.getLevel();        
        if (level==1){
            errors.add("alert",new ActionError("alert.disability.error.qlc"));
        }
        return anchor;
    }
}
