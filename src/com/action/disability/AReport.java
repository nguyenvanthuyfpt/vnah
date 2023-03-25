package com.action.disability;


import com.action.ACore;

import com.bo.admin.reportSystem.BReportSystem;
import com.bo.disability.report.BReport;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.reportSystem.FReportSystem;

import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AReport extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportSystem bean = (FReportSystem)form;
        BReportSystem bo=new BReportSystem();
        BReport boRP=new BReport();
        String anchor=bean.getValue(APP_ANCHOR,"");
        if(anchor.equals(_REPORT)){
            FReportSystem beantemp=bo.getById(bean);
            beantemp.setListId(bean.getListId());
                try  {
                    String pathFile = "";
                    bean.setRowNumber(beantemp.getRowNumber());
                    bean.setColumNumber(beantemp.getColumNumber());
                    FBeans beans=new FBeans();

                    if(beantemp.getId()==5){
                        String SQL="SELECT hotro_id,parent_id,name FROM dr_hotro WHERE parent_id = ?";
                        beans=new BTreeView().getTree(0,true,SQL,"","");
                        if(beantemp.getStylePrint()==0){
                            pathFile=bo.exportExcellVertical(boRP.getMutiData(beantemp,beans),bean,beantemp.getNameFile());
                        } else{
                            pathFile=bo.exportExcellHorizontal(bo.getOneData(beantemp),bean,beantemp.getNameFile());
                        }
                    }else if(beantemp.getId()==6){
                        String SQL="SELECT dangtat_id,parent_id,name FROM dr_classification WHERE parent_id = ?";
                        beans=new BTreeView().getTree(0,true,SQL,"","");
                        if(beantemp.getStylePrint()==0){
                            pathFile=bo.exportExcellVertical(boRP.getMutiData(beantemp,beans),bean,beantemp.getNameFile());
                        }else{
                            pathFile=bo.exportExcellHorizontal(bo.getOneData(beantemp),bean,beantemp.getNameFile());
                        }
                    }else{
                        if(beantemp.getStylePrint()==0){
                            pathFile=bo.exportExcellVertical(bo.getMutiData(beantemp),bean,beantemp.getNameFile());
                        }else{
                            pathFile=bo.exportExcellHorizontal(bo.getOneData(beantemp),bean,beantemp.getNameFile());
                        }
                    
                    }

                    bean.download(pathFile,URLEncoder.encode(beantemp.getNameOfFileVn()+".xls", "UTF-8"),null);
                    bean.deleteFile(pathFile);
                    target=null;
                } catch (Exception ex)  {
                    request.setAttribute("reportSystem",bean);
                    request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                    target=_ERROR;
                }
        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
   
}
