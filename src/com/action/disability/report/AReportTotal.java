package com.action.disability.report;


import com.action.ACore;

import com.bo.disability.BDanSoHuyen;
import com.bo.disability.BDanSoTinh;
import com.bo.disability.BPopulation;
import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportGroup;
import com.bo.disability.report.BReportInforNKT;
import com.bo.disability.report.BReportStatistics;
import com.bo.disability.report.BReportTotal;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportCommuneExport;
import com.dao.disability.report.DReportGroupData;
import com.dao.disability.report.DReportInforNKT;
import com.dao.disability.report.DReportStatistics;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FDanSoHuyen;
import com.form.disability.FDanSoTinh;
import com.form.disability.FPopulation;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportGroup;
import com.form.disability.report.FReportInforNKT;
import com.form.disability.report.FReportStatistics;
import com.form.disability.report.FReportTotal;

import com.inf.IKey;
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


public class AReportTotal extends  ACore {
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
        String func = bean.getFunc();
        int qlc = "_QUANLYCA".equals(func)?1:0;
        FBeans beans = new FBeans();
        FBeans tinhs = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = bean.me.getDepartmentName();
        
        int userId = (int)bean.me.getId();
        int defaultLocation = 0;
        String defaultLocationName = "";
        if (userId==375) {
            tinhs = new BTinh().getAllRecordByParentId(0);
            beanTinh = (FTinh)tinhs.get(0);
            defaultLocation = beanTinh.getId();
            defaultLocationName = beanTinh.getName();
        } else {
            tinhs = new BTreeView().getTree(0, false, SQL, characters, member);            
        }
        
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
        
        String anchor=bean.getValue(APP_ANCHOR,""); 
        if(anchor.equals("_REPORT_TOTAL")){ 
            bean.setCommune(beanTinh.getName());
            bean.setDistrict(beanTinh.getName());
            bean.setProvince(beanTinh.getName());

            FPopulation beanPo = new FPopulation();
            FPopulation beanPoTruoc = new FPopulation();
            BPopulation boPo = new BPopulation();
            beanPo=boPo.getByPeriod(bean.getPeriod(),bean.getYearPeriod(),bean.getTinhId());
            beanPoTruoc=boPo.getByPeriod(bean.getPeriod()==1?1:2,bean.getPeriod()==1?bean.getYearPeriod()-1:bean.getYearPeriod(),bean.getTinhId());
            FDanSoHuyen beanDanSoHuyen = new FDanSoHuyen();
            FDanSoHuyen beanDanSoHuyenTruoc = new FDanSoHuyen();
            BDanSoHuyen boDanSoHuyen = new BDanSoHuyen();

            beanDanSoHuyen=boDanSoHuyen.getByPeriod(bean.getPeriod(),bean.getYearPeriod(),bean.getTinhId());
            beanDanSoHuyenTruoc=boDanSoHuyen.getByPeriod(bean.getPeriod()==1?1:2,bean.getPeriod()==1?bean.getYearPeriod()-1:bean.getYearPeriod(),bean.getTinhId());
            
            FDanSoTinh beanDanSoTinh = new FDanSoTinh();
            FDanSoTinh beanDanSoTinhTruoc = new FDanSoTinh();
            BDanSoTinh boDanSoTinh = new BDanSoTinh();
            beanDanSoTinh=boDanSoTinh.getByPeriod(bean.getPeriod(),bean.getYearPeriod(),bean.getTinhId());
            beanDanSoTinhTruoc=boDanSoTinh.getByPeriod(bean.getPeriod()==1?1:2,bean.getPeriod()==1?bean.getYearPeriod()-1:bean.getYearPeriod(),bean.getTinhId());
            int k=0;
            if((beanPo.getId()>0)||(beanDanSoHuyen.getId()>0)||(beanDanSoTinh.getId()>0)){
                FTinh beantemp=new FTinh();
                beantemp.setId(bean.getTinhId());
                beantemp=new BTinh().getRecordByID(beantemp);
                if(bean.getTinhId()>0){
                    bean.setCommune("");
                    FTinh beanCa=new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params =new ArrayList();
                    for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa=new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                   
                    for (int i=params.size()-1;i>-1;i--) {
                        k++;
                        if(!bean.getCommune().equals("")){ 
                            bean.setCommune(bean.getCommune()+" - "+params.get(i));
                        }else{
                            bean.setCommune(""+params.get(i));
                        }
                    }
                }
                if(k<=2){
                    beanPo=boPo.getSumByPeriod(bean.getPeriod(),bean.getYearPeriod(),bean.getTinhId());
                }
          
                if(bo.insert(bean,beanPo)){
                    try  {
                        FReportTotal beanT = new FReportTotal(); 
                        beanT=bo.getById(3);
                        DReportCommuneExport dao=new DReportCommuneExport();                        
                        String FileName=IKeyDisability.REPORT_FILE_DISABILITY_COMMUNE;                    
                        if(k==1)        FileName="baocaotaitinh.xls";
                        else if(k==2)   FileName="baocaotaihuyen.xls";                    
                        
                        String report = dao.ReportExcelCommune(bean,beanT,beanPoTruoc,beanPo,beanDanSoHuyenTruoc,beanDanSoHuyen,beanDanSoTinhTruoc,beanDanSoTinh,k,FileName);
                        bean.download(report,IKey.REPORT_FILE_NAME,null);
                        bean.deleteFile(report);
                    } catch (SQLException ex) {
                        request.setAttribute("reportSystem",bean);
                        request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                        target=_ERROR;
                    } catch (Exception ex)  {                        
                        request.setAttribute("reportSystem",bean);
                        request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                        target=_ERROR;
                    }            
                }            
            }
            else
            {
                errors.add("alert",new ActionError("alert.disability.report.error"));                
            }
            request.setAttribute("reportcommune", bean);            
            request.setAttribute("BTreeTinhs",tinhs);
            
            if("_QUANLYCA".equals(func)){
                request.setAttribute("anchor", "03");
                request.setAttribute("subanchor", "03.04");
            } else {
                request.setAttribute("anchor", "04");
                request.setAttribute("subanchor", "04.03");
            }
            
            request.setAttribute("div_search", "false");
            request.setAttribute("div_report", "true");
            target=anchor;
        } else if(anchor.equals("_REPORT_SELECT_TINH")){
            target=anchor;
        } else if (anchor.equals("_REPORT_DETAIL")) {
            target=anchor;
        } else if (anchor.equals("_REPORT_OVERVIEW")) {
            target=anchor; 
        } else if (anchor.equals("_CHANGE_DATATYPE")) {
            FReportTotal beanP = new FReportTotal();
            beanP.setPeriodType(bean.getPeriodType());
            request.setAttribute("reportInfo", beanP);
            request.setAttribute("BTreeTinhs", tinhs);           
            if("_REPORT_INDICATOR".equals(func)) {
                request.setAttribute("anchor", "04");
                request.setAttribute("subanchor", "04.01");
            } else if ("_REPORT_OBJECT".equals(func)) {
                request.setAttribute("anchor", "04");
                request.setAttribute("subanchor", "04.02");
            } else {
                request.setAttribute("anchor", "03");
                request.setAttribute("subanchor", "03.08");
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
            }
            target = anchor;
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
        } else if(anchor.equals("_REPORT_PARAM")){            
            String FileName="";            
            String report_type = bean.getReportType();
            int from = 0, to = 0;
            if ("01|02|03|04|05|06|08|12|13".indexOf(report_type)>-1) {
                FileName = IKeyDisability.FILE_REPORT_GROUP_BY_PARAM;
                if("01".equals(report_type)){
                    from = 0; to = 6;    
                } else if ("02".equals(report_type)){
                    from = 6; to = 13;
                } else if ("03".equals(report_type)){
                    from = 13; to = 16;
                } else if ("04".equals(report_type)){ 
                    from = 15; to = 16;
                } else if ("05".equals(report_type)){ 
                    from = 0; to = 55;
                } else if ("06".equals(report_type)){
                    from = 0; to = 55;
                } else if ("12".equals(report_type)){
                    from = 60; to = 79;                               
                } else if ("13".equals(report_type)){
                    from = 80; 
                } else if ("08".equals(report_type)) {
                    from = 16; to = 55;
                } 
            } else if ("07".equals(report_type)){
                from = 18; to = 55;
                FileName = IKeyDisability.FILE_REPORT_GROUP_NHAO;
            } else if ("09|10".indexOf(report_type)>-1) {
                FileName = IKeyDisability.FILE_REPORT_GROUP_BY_LAODONG;
                if ("09".equals(report_type)){
                    from = 13;
                    to = 16;
                } else {
                    from = 15;
                    to = 55;
                }
            } else if ("11".equals(report_type)){
                from = 6; to = 18;
                FileName = IKeyDisability.FILE_REPORT_GROUP_BY_VANHOA;
            } else if ("14".equals(report_type)){
                FileName = IKeyDisability.FILE_REPORT_SUMMARY_14;
            } else if ("15".equals(report_type)){
                FileName = IKeyDisability.FILE_REPORT_SUMMARY_15B;
            } else if ("16".equals(report_type)){
                FileName = IKeyDisability.FILE_REPORT_SUMMARY_16;
            } else if ("17|18|19".indexOf(report_type)>-1){
                FileName = IKeyDisability.FILE_REPORT_SUMMARY_171819;
            } else if ("20".equals(report_type)){
                FileName = IKeyDisability.FILE_REPORT_SUMMARY_20;
            }
            
            beans=new FBeans();
            try {
                if ("14".equals(report_type)) 
                    beans=new BReportGroup().getDataReport14(bean.getTinhId(), level, qlc);
                else if ("15".equals(report_type))
                    beans=new BReportGroup().getDataReport15(bean.getTinhId(), qlc);
                else if ("16".equals(report_type))
                    beans=new BReportGroup().getDataReport16(bean.getTinhId(), level, qlc);
                else if ("17|18|19".indexOf(report_type)>-1) {
                    if ("17".equals(report_type)) {
                        from = 0; to = 16;
                    } else if ("18".equals(report_type)) {
                        from = 16; to = 60;
                    } else {
                        from = 60; to = 1000;
                    }
                    beans=new BReportGroup().getDataReport171819(bean.getTinhId(), level, from, to, qlc);
                }
                    
                else if ("20".equals(report_type))
                    beans=new BReportGroup().getDataReport20(bean.getTinhId(), level, qlc);
                else 
                    beans=new BReportGroup().getData(bean.getTinhId(),report_type, from, to, qlc);
                FReportGroup beanTemp=new FReportGroup();            
                if(beans.size()>0){
                    bean.setTinhName(beanTinh.getName());
                    beanTemp.setStore(beans);
                    beanTemp.setTotal(beans.size());
                    
                    String report = new DReportGroupData().ReportExcel(beanTemp,bean,FileName);
                    bean.download(report,FileName,null);
                    bean.deleteFile(report);
                } else {
                    errors.add("alert",new ActionError("alert.disability.report.nodata",tinh_name));
                }
                request.setAttribute("reportcommune", bean);            
                request.setAttribute("BTreeTinhs",tinhs);
                if("_QUANLYCA".equals(func)) {
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.02");
                } else {
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.03");
                }
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                target = anchor;
            }  catch (Exception ex) {
                request.setAttribute("reportSystem",bean);
                request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                errors.add("alert",new ActionError("alert.disability.error.detail",ex.toString().replaceAll("com.exp.EException:","")));
                target=_ERROR;    
            }
        } else if(anchor.equals("_REPORT_STATISTICS")){
            String FileName="";
            int fromMonth = 0, toMonth = 0, fromYear = 0, toYear = 0;
            String field = bean.getFieldType();
            fromYear = bean.getFromYear();
            fromMonth = bean.getFromMonth();
            
            toMonth = bean.getToMonth();            
            toYear = bean.getToYear();
            
            if (fromYear>toYear) {
                errors.add("alert",new ActionError("alert.invalid.year.report"));
            } else if ((fromYear==toYear)&&(fromMonth>toMonth)) {
              errors.add("alert",new ActionError("alert.invalid.month.report"));
            }
            
            
              String fromPeriod = (fromMonth<10?"0"+fromMonth:fromMonth)+"/"+fromYear;
              String toPeriod = (toMonth<10?"0"+toMonth:toMonth)+"/"+toYear;
              
              if ("1".equals(field)){
                  FileName = IKeyDisability.FILE_REPORT_STATISTICS_GIAODUC;            
              } else if ("2".equals(field)){
                  FileName = IKeyDisability.FILE_REPORT_STATISTICS_YTE;
              } else if ("3".equals(field)){
                  FileName = IKeyDisability.FILE_REPORT_STATISTICS_XAHOI;
              }            
              beans=new FBeans();            
              FReportStatistics beanTemp = new FReportStatistics();
              try {
                  if (errors.isEmpty()) {
                    beans=new BReportStatistics().getData(bean.getTinhId(),field, fromPeriod, toPeriod, qlc);
                    if(beans.size()>0){
                        bean.setTinhName(beanTinh.getName());
                        beanTemp.setStore(beans);
                        beanTemp.setTotal(beans.size());            
                        String report = new DReportStatistics().ReportExcel(beanTemp,bean,FileName);
                        bean.download(report,FileName,null);
                        bean.deleteFile(report);                    
                    } else {
                        errors.add("alert",new ActionError("alert.disability.report.nodata",tinh_name));
                    }
                  }
                  request.setAttribute("reportcommune", bean);            
                  request.setAttribute("BTreeTinhs",tinhs);
                  if("_QUANLYCA".equals(func)) {
                      request.setAttribute("anchor", "03");
                      request.setAttribute("subanchor", "03.04");
                  } else {
                      request.setAttribute("anchor", "04");
                      request.setAttribute("subanchor", "04.05");
                  }
                  request.setAttribute("div_search", "false");
                  request.setAttribute("div_report", "true");
                  
              } catch (Exception ex) {
                  request.setAttribute("reportSystem",bean);
                  request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                  target=_ERROR;    
              }
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
        }else if (anchor.equals("_EXPORT_DATA")) {        
           
            String srcString = boTinh.getMembers(bean.getTinhId(), "");
            int tinh_id = bean.getTinhId();
            
            beans = new FBeans();
            beans = new BReportInforNKT().getData(tinh_id, qlc);
            
            if(beans.size()>0){
                FReportInforNKT beanTemp = new FReportInforNKT();
                String FileName = IKeyDisability.EXPORT_FILE_DISABILITY_NKT;
                beanTemp.setStore(beans);
                
                if (bean.getTinhId() > 0) {
                    beanTemp.setTinhName("");
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
                        if (!beanTemp.getTinhName().equals("")) {
                            beanTemp.setTinhName(beanTemp.getTinhName() + " - " + params.get(i));
                        } else {
                            beanTemp.setTinhName("" + params.get(i));
                        }
                    }
                }
                
                String report = new DReportInforNKT().ReportExcel(beanTemp,bean,FileName);
                bean.download(report,FileName,null);
                bean.deleteFile(report);
            } else {
                errors.add("alert",new ActionError("alert.disability.report.nodata",tinh_name));
            }
            request.setAttribute("reportcommune", bean);
            request.setAttribute("BTreeTinhs",tinhs);
            target = anchor;        
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
