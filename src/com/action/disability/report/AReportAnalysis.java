package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportAnalysis;
import com.bo.tree.BTreeView;

import com.dao.connection.DBConnector;
import com.dao.disability.DThongTinTuyen;
import com.dao.disability.report.DReportAnalysisData;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FThongTinTuyen;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportAnalysis;

import com.inf.disability.IKeyDisability;

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


public class AReportAnalysis extends ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportAnalysis bean = (FReportAnalysis)form;
        String anchor = bean.getValue(APP_ANCHOR,"");                
        String func = bean.getFunc();
        
        FThongTinTuyen beanTuyen = new FThongTinTuyen();
        beanTuyen.setKyBC(bean.getKyBC());;
        beanTuyen.setNamBC(bean.getNamBC());
        beanTuyen.setId_tinh(bean.getId_tinh());
        
        FBeans beans        = new FBeans();   
        target = validate(beanTuyen,anchor,errors);
        int level = 0;
        
        if(!errors.isEmpty()){
        } else {        
            if(anchor.equals("_REPORT_ANALYSIS")){
            
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
                    }
                }
                
                bean.setLevel(level);
                request.setAttribute("reportAnalysis",bean);
                           
                BTinh boTinh    = new BTinh();
                FTinh beanTinh  = new FTinh();
                beanTinh.setId(bean.getId_tinh());
                beanTinh        = boTinh.getRecordByID(beanTinh);
                
                String FileName = "";               
                
                DReportAnalysisData dao = new DReportAnalysisData();
                String quanlyca = "_QUANLYCA".equals(func) ? "#141#" : "";
                try  {                    
                   if(level==3)            FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_WARD;
                   else if (level==2)      FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_DISTRIST;
                   else if (level==1)      FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_PROVINCE;                    
                   beans=new BReportAnalysis().getData(bean.getId_tinh(),beanTuyen.getKyBC(),beanTuyen.getNamBC(), level, quanlyca);
                    if(beans.size()>0){
                        FReportAnalysis beanTemp=new FReportAnalysis();
                        beanTemp.setStore(beans);
                        
                        if("_QUANLYCA".equals(func))    beanTemp.setQuanlyca(1);
                        else                            beanTemp.setQuanlyca(0);
                        
                        String report = dao.exportExcel(level,beanTemp, bean, FileName);
                        bean.download(report, FileName ,null);                
                        bean.deleteFile(report);
                    } else {
                        errors.add("alert",new ActionError("alert.disability.report.error"));
                        //errors.add("alert",new ActionError("alert.disability.report.nodata",tinh_name));
                    }
               } catch (Exception ex)  {                        
                    request.setAttribute("reportSystem",bean);
                    request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                    target=_ERROR;
                } 
                
                request.setAttribute("func", 2);
                target = anchor;
                
            } else if(anchor.equals("_REPORT_ANALYSIS_QLC")){
                
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
                    }
                }
                
                bean.setLevel(level);
                request.setAttribute("reportAnalysis",bean);
                BTinh boTinh    = new BTinh();                
                FTinh beanTinh  = new FTinh();
                beanTinh.setId(bean.getId_tinh());
                beanTinh        = boTinh.getRecordByID(beanTinh);
                
                String FileName = "";
                if(level==3)            FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_WARD;
                else if (level==2)      FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_DISTRIST;
                else if (level==1)      FileName = IKeyDisability.FILE_REPORT_ANALYSIS_DATA_PROVINCE;
                
                DReportAnalysisData dao = new DReportAnalysisData();
                String quanlyca = "_QUANLYCA".equals(func) ? "#141#" : "";
                beans=new BReportAnalysis().getData(bean.getId_tinh(),beanTuyen.getKyBC(),beanTuyen.getNamBC(), level, quanlyca);
                FReportAnalysis beanTemp=new FReportAnalysis();
                beanTemp.setStore(beans);
                
                String report = dao.exportExcel(level,beanTemp, bean, FileName);
                bean.download(report, FileName ,null);                
                bean.deleteFile(report);
                request.setAttribute("func", 2);
                target = anchor;
                    
            } else if (anchor.equals("_VIEW_OPTION")){
            
                bean.setId_tinh(bean.getId_tinh());
                bean.reset();
                
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
                        level++;
                        bean.setNameArea(bean.getNameArea()!="" ? bean.getNameArea()+" - "+params.get(i): "" );
                    }
                }
                
                request.setAttribute("reportAnalysis",bean);                
                target = anchor;
            }
        }
        
        String SQL          = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
        String characters   = "/ ";
        String member = "";
        if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }
        
        beans = new BTreeView().getTree(0,false,SQL,characters, member);
        request.setAttribute("BTreeTinhs",beans);
        request.setAttribute("reportAnalysis",bean);
        
        if("_QUANLYCA".equals(func)){
            request.setAttribute("anchor", "03");
            request.setAttribute("subanchor", "03.05");
        } else {
            request.setAttribute("anchor", "04");
            request.setAttribute("subanchor", "04.06");
        }
        
        request.setAttribute("div_search", "false");
        request.setAttribute("div_report", "true");
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }

    private String validate(FThongTinTuyen bean, String anchor, ActionErrors errors) throws EException {
        Connection cnn = DBConnector.getConnection();
        DThongTinTuyen dao = new DThongTinTuyen();
        boolean hasInfo = true;
        hasInfo = dao.checkInsert(cnn, bean);
        if (!anchor.equals("_REPORT_ANALYSIS")) {
        } else {
            if (!hasInfo) {
                errors.add("alert", new ActionError("alert.thongtintuyen.insert.info.before.report.analysis"));
            }
        }
        return anchor;
    }
}
