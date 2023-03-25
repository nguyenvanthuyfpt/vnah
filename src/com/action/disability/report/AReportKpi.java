package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.categorys.BTinh;
import com.bo.disability.jobs.BJobLog;
import com.bo.disability.report.BReportKpi;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportKpi;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FTinh;
import com.form.disability.jobs.FJobLog;
import com.form.disability.report.FReportKpi;

import com.inf.disability.IKeyDisability;

import com.util.Constant;

import com.util.Utilities;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AReportKpi extends  ACore {
    
    final static Logger logger = Logger.getLogger(AReportKpi.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException, SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportKpi bean = (FReportKpi)form;        

        String periodType = bean.getPeriodType();
        int locationId = bean.getTinhId();
        int extend = bean.getExtend();
        String subFunction = bean.getSubFunction();
        FTinh beanTinh = new FTinh();
        BTinh boTinh = new BTinh();
        
        beanTinh.setId(bean.getTinhId());
        beanTinh = boTinh.getRecordByID(beanTinh);
        String tinh_name = beanTinh.getName();
        
        Map<String, FTinh> map_location = (HashMap<String, FTinh>)request.getSession().getAttribute("MAP_LOCATION");
        FBeans beans = new FBeans();
        FBeans tinhs = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = "";
        if ((bean.me.getDepartmentName() != null) && 
            (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }         

        tinhs = new BTreeView().getTree(0, false, SQL, characters, member);        
        beans = (FBeans)request.getSession().getAttribute("BTreeTinhs");        
        int level = 0;
        if(bean.getTinhId()>0){
            List params     = new ArrayList();
            FTinh beanCa    = new FTinh();
            beanCa.setParentID(bean.getTinhId());            
            
            for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                beanCa.setId(beanCa.getParentID());
                beanCa=new BTinh().getRecordByID(beanCa);
                params.add(beanCa.getName());
            }
            for (int i=params.size()-1;i>-1;i--) {            
                level++;                
            }
        }
        
        String anchor = bean.getValue(APP_ANCHOR,"");
        bean.setPeriodType(periodType);
        bean.setTinhId(locationId);
        bean.setSubFunction(subFunction);
        
        if(anchor.equals("_REPORT_SELECT_TINH")){
            String tinhName = "";
            int defaultLocation = 0;
            if (bean.getTinhId()>0) {
                FTinh tinh = new FTinh();
                tinh.setId(bean.getTinhId());
                tinh = new BTinh().getRecordByID(tinh);
                defaultLocation = tinh.getId();
                tinhName = tinh.getName();
            }
            
            String func = "";
            if ("04.01".equals(subFunction)) {
                func = "_REPORT_INDICATOR";
            } else if ("04.02".equals(subFunction)) {
                func = "_REPORT_OBJECT";          
            } else if ("04.06".equals(subFunction)) {
                func = "_REPORT_EXPORT";
            } else if ("04.07".equals(subFunction)) {
                func = "_REPORT_SUPPORT_LIST";
                bean.setCreateDateFrom("");
                bean.setCreateDateTo("");
            } else if ("03.02".equals(subFunction)) {
                func = "_REPORT_EXPORT_2020";
                bean.setPeriodType("-1");
            }
            
            String msgJob = "B&#225;o c&#225;o/D&#7919; li&#7879;u NKT &#273;&#227; k&#7871;t xu&#7845;t th&#224;nh c&#244;ng v&#224;o l&#250;c [$last-update$], B&#7841;n c&#243; th&#7875; khai th&#225;c th&#244;ng tin.";
            String msgInit = "B&#225;o c&#225;o/D&#7919; li&#7879;u NKT ch&#432;a &#273;&#432;&#7907;c k&#7871;t xu&#7845;t l&#7847;n &#273;&#7847;u, b&#7841;n ch&#432;a th&#7875; khai th&#225;c th&#244;ng tin!";
            
            FJobLog jobLog = new FJobLog();
            jobLog.setJobCode(func);
            jobLog.setLocationId("_REPORT_EXPORT".equals(func)?0:defaultLocation);
            FBeans jobLogs = new BJobLog().getLogsByJobCode(jobLog);
            String jobLastUpdate = "";
            
            if (jobLogs!=null && jobLogs.size()>0) {
                jobLog = (FJobLog)jobLogs.get(0);
                jobLastUpdate = Utilities.parseDateToTringType4(jobLog.getEndExec());
            }
            
            bean.setJobMsg(!"".equals(jobLastUpdate)?bean.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):bean.ncrToString(msgInit));
            bean.setTinhName(tinhName);      
            request.setAttribute("BTreeTinhs", ("04.01|04.02|04.04".indexOf(subFunction)>-1)? beans:tinhs);
            request.setAttribute("subanchor", bean.getSubFunction());
            request.setAttribute("reportkpi", bean);
            target=anchor;
        } else if(anchor.equals("_REPORT")){
              beans=new FBeans();
              String val = "", strVal = "", period = "";              
              if (Constant.REPORT_PERIOD_TYPE_MONTH.equals(periodType)) {
                  val = String.valueOf(bean.getMonthReport());
                  strVal = val + "/" + bean.getYearReport();
              } else if (Constant.REPORT_PERIOD_TYPE_QUARTER.equals(periodType)) {
                  val = String.valueOf(bean.getQuarterReport());
                  strVal = "Q"+ val + "/" + bean.getYearReport();
              } else if (Constant.REPORT_PERIOD_TYPE_YEAR.equals(periodType)) {
                  val = String.valueOf(bean.getYearReport());
                  strVal = val;
              } else if (Constant.REPORT_PERIOD_TYPE_FT.equals(periodType)) {
                  val = bean.getFromMonth()+"/"+bean.getFromYear()+"-"+bean.getToMonth()+"/"+bean.getToYear();
                  strVal = val;
              } else if (Constant.REPORT_PERIOD_TYPE_FROM_TO.equals(periodType)) {
                  strVal = bean.getCreateDateFrom()+"-"+bean.getCreateDateTo();
              }
              
              String reportDtl = "";
              if ("04.01".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_INDICATOR";
              } else if ("04.02".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_OBJECT";
              } else if ("04.03".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_INSURANCE";   
              } else if ("04.04".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_SUPPORT";        
              } else if ("04.05".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_COMMUNE";
              } else if ("04.06".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_EXPORT";
              } else if ("04.07".equals(bean.getSubFunction())) {
                    reportDtl = "_REPORT_SUPPORT_LIST";
              } else if ("03.02".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_EXPORT_2020";
                  bean.setPeriodType("-1");
              }
              
              bean.setTinhName(tinh_name);
              try {                  
                  int lvl = 1;
                  FTinh tinh = (FTinh)map_location.get(String.valueOf(locationId));
                  if (tinh!=null) {
                      lvl = (tinh.getName().indexOf("3")>-1)?3:((tinh.getName().indexOf("2")>-1)?2:1);                      
                  }
                  
                  String fileName = "", report = "";
                  if ("_REPORT_OBJECT".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportObject(Integer.parseInt(periodType), locationId, val, bean.getYearReport(), extend);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_OBJECT;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportObject(beanTemp, bean, fileName);
                  } else if ("_REPORT_INDICATOR".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportIndicator(Integer.parseInt(periodType), locationId, val, bean.getYearReport(), extend);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_INDICATOR;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId); 
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportIndicator(beanTemp, bean, fileName);
                  } else if ("_REPORT_INSURANCE".equals(reportDtl)) {
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_HEALTH_INSURANCE;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportInsurance(beanTemp, bean, fileName);
                  } else if ("_REPORT_SUPPORT".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportSupport(locationId, strVal);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_SUPPORT;                                                
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);                    
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportSupport(beanTemp, bean, fileName);                                           
                  } else if ("_REPORT_COMMUNE".equals(reportDtl)) {
                      if (extend==0) {
                            beans = new BReportKpi().getDataDisCommuneSummary(lvl, locationId, strVal);
                            fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_COMMUNE_SUMMARY;
                      } else {
                            beans = new BReportKpi().getDataDisCommuneDetail(lvl, locationId, strVal);  
                            fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_COMMUNE_DETAIL;
                      }
                      
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setStore(beans);
                      beanTemp.setExtend(extend);
                      beanTemp.setVal(strVal);
                      beanTemp.setTinhName(tinh_name);
                      report = new DReportKpi().reportCommune(beanTemp, bean, fileName);
                   } else if ("_REPORT_EXPORT".equals(reportDtl)) {
                      int duAnId = bean.getDuAnId(); 
                      String createDateFrom = bean.getCreateDateFrom();
                      String createDateTo = bean.getCreateDateTo();
                      String dvuDateFrom = bean.getDvuDateFrom();
                      String dvuDateTo = bean.getDvuDateTo();
                      String tdgDateFrom = bean.getTdgDateFrom();
                      String tdgDateTo = bean.getTdgDateTo();
                      String dmcDateFrom = bean.getDmcDateFrom();
                      String dmcDateTo = bean.getDmcDateTo();
                      
                      beans = new BReportKpi().getDataDisExport(lvl, locationId, duAnId, createDateFrom, createDateTo,
                                                                    dvuDateFrom, dvuDateTo, tdgDateFrom, tdgDateTo, 
                                                                    dmcDateFrom, dmcDateTo);                      
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_EXPORT;                      
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setStore(beans);
                      report = new DReportKpi().reportDisExport(beanTemp, bean, fileName);
                  } else if ("_REPORT_EXPORT_2020".equals(reportDtl)) {
                      String createDateFrom = bean.getCreateDateFrom();
                      String createDateTo = bean.getCreateDateTo();
                      String dvuDateFrom = bean.getDvuDateFrom();
                      String dvuDateTo = bean.getDvuDateTo();
                      String tdgDateFrom = bean.getTdgDateFrom();
                      String tdgDateTo = bean.getTdgDateTo();
                      String dmcDateFrom = bean.getDmcDateFrom();
                      String dmcDateTo = bean.getDmcDateTo();
                      beans = new BReportKpi().getDataDisExport2020(lvl, locationId, createDateFrom, createDateTo, 
                                                                    dvuDateFrom, dvuDateTo, tdgDateFrom, tdgDateTo, 
                                                                    dmcDateFrom, dmcDateTo);
                      
                      fileName = IKeyDisability.REPORT_FILE_KPI_LIST_DIS_2020;
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setStore(beans);
                      report = new DReportKpi().reportDisExport2020(beanTemp, bean, fileName);
                  } else if ("_REPORT_SUPPORT_LIST".equals(reportDtl)) {                      
                      beans = new BReportKpi().getDataReportSupport(lvl, locationId, periodType, strVal);                      
                      fileName = IKeyDisability.REPORT_FILE_KPI_LIST_DIS_SUPPORT;
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().reportDisSupport(beanTemp, seed, fileName);
                  }
                  
                  bean.download(report,fileName,null);
                  bean.deleteFile(report);                   
                  target=null;
            } catch (Exception ex) {
                  logger.error("Exception" + ex.toString());
                  request.setAttribute("anchor", "04");
                  request.setAttribute("reportSystem",bean);
                  request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                  target="_REPORT_KPI";    
                  errors.add("alert", new ActionError("alert.disability.error.detail", ex.toString().replaceAll("com.exp.EException:","")));
            } 
        }
        
        request.setAttribute("anchor", "04");
        request.setAttribute("subanchor", subFunction);
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
