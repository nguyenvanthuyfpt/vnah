package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportInforNKT;
import com.bo.disability.report.BReportTotal;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportInforNKT;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportInforNKT;
import com.form.disability.report.FReportTotal;

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


public class AReportTab extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException, SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportTotal bean = (FReportTotal)form;         
        BReportTotal bo= new BReportTotal();
        
        FTinh beanTinh = new FTinh();
        BTinh boTinh = new BTinh();
        beanTinh.setId(bean.getTinhId());
        beanTinh = boTinh.getRecordByID(beanTinh);
        String tinh_name = beanTinh.getName();
        
        FBeans beans = new FBeans();
        FBeans tinhs = new FBeans();
        String func = bean.getFunc();
        int qlc = "_QUANLYCA".equals(func)?1:0;
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = "";
        if ((bean.me.getDepartmentName() != null) && 
            (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }
         
        tinhs = new BTreeView().getTree(0, false, SQL, characters, member);
        String anchor=bean.getValue(APP_ANCHOR,"");        
        
        if (anchor.equals("_REPORT_DETAIL")) {
            target=anchor;
        } else if (anchor.equals("_REPORT_OVERVIEW")) {
            target=anchor;        
        } else if(anchor.equals("_REPORT_INFOR_NKT")){
            beans=new FBeans();
            try {
                beans=new BReportInforNKT().getData(bean.getTinhId(), qlc);
            } catch (Exception ex) {
                request.setAttribute("reportSystem",bean);
                request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                target=_ERROR;    
            }
            
            FReportInforNKT beanTemp=new FReportInforNKT();
            String FileName=IKeyDisability.REPORT_FILE_DISABILITY_TT;
            beanTemp.setStore(beans);
            beanTemp.setTotal(beans.size()>0?beans.size():0);
            
            if(bean.getTinhId()>0){
                beanTemp.setTinhName("");
                FTinh beanCa=new FTinh();
                beanCa.setParentID(bean.getTinhId());
                List params =new ArrayList();
                
                for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i=params.size()-1;i>-1;i--) {
                    if(!beanTemp.getTinhName().equals("")){ 
                        beanTemp.setTinhName(beanTemp.getTinhName()+" - "+params.get(i));
                    }else{
                        beanTemp.setTinhName(""+params.get(i));
                    }
                }
            }
            
            String report = new DReportInforNKT().ReportExcel(beanTemp,bean,FileName);
            bean.download(report,FileName,null);
            bean.deleteFile(report);
            target = anchor;
        } else if(anchor.equals("_SELECT_TINH")){           
            if(bean.getTinhId()>0){
                bean.setTinhName("");
                FTinh beanCa=new FTinh();
                beanCa.setParentID(bean.getTinhId());
                List params =new ArrayList();
                    
                for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i=params.size()-1;i>-1;i--) {
                    bean.setTinhName(bean.getTinhName()+" - "+params.get(i));
                }
            }
            
            request.setAttribute("reportcommune", bean);
            request.setAttribute("BTreeTinhs",tinhs);
            target = anchor;           
        } 
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
