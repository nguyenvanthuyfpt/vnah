package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportCollect;

import com.dao.disability.report.DReportCollectData;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FThongTinTuyen;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportCollect;

import com.inf.disability.IKeyDisability;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AReportCollect extends ACore{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportCollect bean = (FReportCollect)form;
        String anchor = bean.getValue(APP_ANCHOR,"");                
        String func = bean.getFunc();
        
        FThongTinTuyen beanTuyen = new FThongTinTuyen();
        beanTuyen.setKyBC(bean.getKyBC());;
        beanTuyen.setNamBC(bean.getNamBC());
        beanTuyen.setId_tinh(bean.getId_tinh());

        FBeans beans        = new FBeans();                
        int level = 0;
        
        if(!errors.isEmpty()){   
        } else {        
            if(anchor.equals("_REPORT_COLLECT")){
            
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
                
                bean.setLevel(level);
                request.setAttribute("reportCollect",bean);
                request.setAttribute("anchor","02");                
                request.setAttribute("subanchor","02.03");                
                String FileName =  "";                
                
                BTinh boTinh    = new BTinh();
                FTinh beanTinh  = new FTinh();
                beanTinh.setId(bean.getId_tinh());
                beanTinh        = boTinh.getRecordByID(beanTinh);
                
                if (level == 3)         FileName = IKeyDisability.FILE_REPORT_COLLECT_DATA_WARD;
                else if (level == 2)    FileName = IKeyDisability.FILE_REPORT_COLLECT_DATA_DISTRIST;
                else if (level == 1)    FileName = IKeyDisability.FILE_REPORT_COLLECT_DATA_PROVINCE;
                String quanlyca = "_QUANLYCA".equals(func) ? "#141#" : "";
                
                DReportCollectData dao = new DReportCollectData();                
                beans = new BReportCollect().getData(bean.getId_tinh(),beanTuyen.getKyBC(),beanTuyen.getNamBC(), level, quanlyca);
                FReportCollect beanTemp=new FReportCollect();
                beanTemp.setStore(beans);
                
                if("_QUANLYCA".equals(func))    beanTemp.setQuanlyca(1);
                else                            beanTemp.setQuanlyca(0);
                
                String report = dao.exportExcel(level, beanTemp, bean, FileName);
                bean.download(report, FileName ,null);                
                bean.deleteFile(report);
                request.setAttribute("func", 2);
                target = anchor;
            } 
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
