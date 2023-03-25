package com.action.disability;


import com.action.ACore;

import com.bo.admin.reportSystem.BReportSystem;
import com.bo.admin.users.BUsers;
import com.bo.disability.BDanSoHuyen;
import com.bo.disability.BDanSoTinh;
import com.bo.disability.BDataDtl;
import com.bo.disability.BDataHdr;
import com.bo.disability.BDataNkt;
import com.bo.disability.BDisability;
import com.bo.disability.BIndicatorKpi;
import com.bo.disability.BObjectInd;
import com.bo.disability.BPerson;
import com.bo.disability.BPopulation;
import com.bo.disability.BThongTinTuyen;
import com.bo.disability.BUnit;
import com.bo.disability.categorys.BDangTat;
import com.bo.disability.categorys.BDanhgia;
import com.bo.disability.categorys.BDantoc;
import com.bo.disability.categorys.BDieuKien;
import com.bo.disability.categorys.BDoiTuong;
import com.bo.disability.categorys.BDungcu;
import com.bo.disability.categorys.BEvent;
import com.bo.disability.categorys.BHotro;
import com.bo.disability.categorys.BIndicator;
import com.bo.disability.categorys.BMucdo;
import com.bo.disability.categorys.BNguonhotro;
import com.bo.disability.categorys.BNguyennhan;
import com.bo.disability.categorys.BObject;
import com.bo.disability.categorys.BQuanhe;
import com.bo.disability.categorys.BRank;
import com.bo.disability.categorys.BTinh;
import com.bo.disability.jobs.BJobLog;
import com.bo.disability.list.BList;
import com.bo.disability.search.BSearch;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.form.disability.FDanSoHuyen;
import com.form.disability.FDanSoTinh;
import com.form.disability.FDataDtl;
import com.form.disability.FDataHdr;
import com.form.disability.FDataNkt;
import com.form.disability.FDisability;
import com.form.disability.FExport;
import com.form.disability.FImport;
import com.form.disability.FObjectInd;
import com.form.disability.FPerson;
import com.form.disability.FPopulation;
import com.form.disability.FThongTinTuyen;
import com.form.disability.FUnit;
import com.form.disability.categorys.FDangTat;
import com.form.disability.categorys.FDanhgia;
import com.form.disability.categorys.FDantoc;
import com.form.disability.categorys.FDieuKien;
import com.form.disability.categorys.FDoiTuong;
import com.form.disability.categorys.FDungcu;
import com.form.disability.categorys.FEvent;
import com.form.disability.categorys.FHotro;
import com.form.disability.categorys.FIndicator;
import com.form.disability.categorys.FMucdo;
import com.form.disability.categorys.FNguonhotro;
import com.form.disability.categorys.FNguyennhan;
import com.form.disability.categorys.FObject;
import com.form.disability.categorys.FQuanhe;
import com.form.disability.categorys.FRank;
import com.form.disability.categorys.FTinh;
import com.form.disability.jobs.FJobLog;
import com.form.disability.list.FList;
import com.form.disability.report.FReportAnalysis;
import com.form.disability.report.FReportCollect;
import com.form.disability.report.FReportKpi;
import com.form.disability.report.FReportTotal;
import com.form.disability.search.FSearch;

import com.util.ChartEventServlet;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADisabilityFuntion extends ACore {
    
    final static Logger logger = Logger.getLogger(ADisabilityFuntion.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        
        try {
            
            final String LOCATION = this + "->executeAction()";
            String target = _LOGOUT;
            ActionErrors errors = new ActionErrors();
            
            FDisability bean = (FDisability)form;
            String func = bean.getFunc();
            FBeans beans = new FBeans();
            
            int userId = (int)bean.me.getId();
            int defaultLocation = 0;
            String defaultLocationName = "";
            String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
            HttpSession session = seed.getRequest().getSession();
            String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
            String characters = "/ ";
            String member = bean.me.getDepartmentName();
            
            // Tree Object        
            FBeans optObjects = new FBeans();        
            
            // Tree Indicator        
            FBeans optIndicators = new FBeans();
            
            // Category
            FBeans beansNguonHoTro  = (FBeans)request.getSession().getAttribute("LIST_NGUONHOTRO");
            FBeans beansDieuKien = (FBeans)request.getSession().getAttribute("LIST_DIEUKIEN");
            FBeans beansNguyenNhan = (FBeans)request.getSession().getAttribute("LIST_NGUYENNHAN");
            FBeans beansMucDo = (FBeans)request.getSession().getAttribute("LIST_MUCDO");
            FBeans beansDanToc = (FBeans)request.getSession().getAttribute("LIST_DANTOC");
            FBeans beansTinhTrang = (FBeans)request.getSession().getAttribute("LIST_TINHTRANG");        
            
            Map<String, Object> mapObject = (Map<String, Object>)request.getSession().getAttribute("MAP_OBJECT");
            Map<String, String> map_phanloai_diadiem = (HashMap<String,String>)mapObject.get("KPI_PHANLOAI_DIADIEM"); 
            Map<String, FBeans> map_district = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_DISTRICT");
            Map<String, FBeans> map_commune = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_COMMUNE");
            String indIds = (String)request.getSession().getAttribute("INDICATOR_IDS");
            
            beans = (FBeans)request.getSession().getAttribute("BTreeTinhs");
            FBeans beansTree = new BTreeView().getTree(0, false, SQL, characters, member);
            // Tree 
            optObjects = (FBeans)request.getSession().getAttribute("OPT_TREE_OBJECT");
            request.setAttribute("optObjects", optObjects);
            
            optIndicators = (FBeans)request.getSession().getAttribute("OPT_TREE_INDICATOR");
            request.setAttribute("optIndicators", optIndicators);
            
            // Person
            FBeans acPersonBeans = (FBeans)request.getSession().getAttribute("LIST_PERSON");
            request.setAttribute("listPersonAc", acPersonBeans);
                
            int level = 0;
            if (anchor.equals("_QUANLYCA") || anchor.equals("_NC_QUANLYCA")) {                      
                FSearch beanTK = new FSearch();            
                beanTK.reset();            
                request.setAttribute("BSearchTinhs", beans);
                beanTK.setTinhName("");
                if (beans != null && beans.size() > 0) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    bean.setTinhId(beanTinh.getId());
                }
    
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
                        beanTK.setTinhName(beanTK.getTinhName() + " - " + params.get(i));
                    }
                }   
                
                if (anchor.equals("_NC_QUANLYCA")){
                    request.setAttribute("subanchor", "03.00");
                    beanTK.setQuanLyCa(1);
                } else {
                    request.setAttribute("subanchor", "03.01");    
                    beanTK.setQuanLyCa(9); // Dang duoc QLC
                }
                
                request.setAttribute("div_report", "false");
                request.setAttribute("div_search", "true");
                
                request.setAttribute("BNguonHoTros", beansNguonHoTro);
                request.setAttribute("BNguyenNhans", beansNguyenNhan);
                request.setAttribute("BDieuKiens", beansDieuKien);            
                request.setAttribute("BMucDos", beansMucDo);
                request.setAttribute("BDanTocs", beansDanToc);
                request.setAttribute("BTinhTrangForms", beansTinhTrang);
                request.setAttribute("timkiem", beanTK);
                request.setAttribute("anchor", "03");
                
                target = anchor;            
            } else if (anchor.equals("_QUANLYCA_RESULT")) {
            
                BSearch bo = new BSearch();
                FSearch beanSearch = new FSearch();
    
                beanSearch.reset();
                beanSearch.setTinhId(bean.getId());
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                beanSearch.setPageIndex(bean.getPageIndex());
                
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("BSearchDieuKiens", beansDieuKien);
                request.setAttribute("BDisabilitys", bo.getAll(beanSearch));
                request.setAttribute("total", beanSearch.getTotalResult());
    
                FTinh beantemp = new FTinh();
                beantemp.setId(bean.getId());
                beantemp = new BTinh().getRecordByID(beantemp);
                beanSearch.setTinhName("");
                if (bean.getId() > 0) {
                    bean.setTinhId(bean.getId());
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
                        beanSearch.setTinhName(beanSearch.getTinhName() + " - " + params.get(i));
                    }
                }
                beanSearch.setTypeResult(1);
                request.setAttribute("timkiem", beanSearch);
                target = anchor;
            } else if (anchor.equals(_SEARCH)) {
                FSearch beanTK = new FSearch();
                beanTK.reset();
                
                request.setAttribute("mapPLoaiDDiem", map_phanloai_diadiem);
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("BSearchDieuKiens", beansDieuKien);
                beanTK.setPageIndex(1);            
                beanTK.setTinhName("");
                
                if (beans != null && beans.size() > 0) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    bean.setTinhId(beanTinh.getId());
                }
                
                if (userId!=375) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    defaultLocation = beanTinh.getId();
                    defaultLocationName = beanTinh.getName();
                }
                FBeans districts = map_district.get(String.valueOf(defaultLocation));
                FBeans communes = map_commune.get(String.valueOf(0));
                request.setAttribute("districts", districts);
                request.setAttribute("communes", communes);
                
                request.setAttribute("BNguonHoTros", beansNguonHoTro);
                request.setAttribute("BNguyenNhans", beansNguyenNhan);
                request.setAttribute("BDieuKiens", beansDieuKien);            
                request.setAttribute("BMucDos", beansMucDo);
                request.setAttribute("BDanTocs", beansDanToc);            
                request.setAttribute("BTinhTrangForms", beansTinhTrang);
                request.setAttribute("timkiem", beanTK);            
                request.setAttribute("anchor", "02");
                request.setAttribute("subanchor", "02.01");
                target = anchor;
            } else if (anchor.equals("_CHART")) {
                request.setAttribute("BTreeTinhs", beans);
                
                if("_CHART_DIS".equals(func)) {
                    request.setAttribute("anchor", "08");
                    request.setAttribute("subanchor", "08.01");
                } else if ("_CHART_PERSON".equals(func)) {
                    request.setAttribute("anchor", "08");
                    request.setAttribute("subanchor", "08.02");
                } else if ("_CHART_HOURS".equals(func)) {                
                    request.setAttribute("anchor", "08");
                    request.setAttribute("subanchor", "08.03");                            
                } else if ("_CHART_EVENT".equals(func)) {
                      request.setAttribute("anchor", "08");
                      request.setAttribute("subanchor", "08.04");              
                } else {
                    request.setAttribute("anchor", "08");
                    request.setAttribute("subanchor", "08.05");
                }
                
                target = anchor;
            } else if (anchor.equals("_SEARCH_RESULT")) {
                BSearch bo = new BSearch();
                FSearch beanSearch = new FSearch();
                
                if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
                    member = bean.me.getDepartmentName();
                    String memberRule = member.substring(1, member.length() - 1);
                    beanSearch.setMembersRule(memberRule);
                }
                
                beanSearch.reset();
                beanSearch.setPageIndex(bean.getPageIndex());
                beanSearch.setRequest(request);
                beanSearch.setMembers("");
                beanSearch.setTinhId(bean.getId());
    
                beanSearch.setPageIndex(bean.getPageIndex());
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("BSearchDieuKiens", new BDieuKien().getAllRecord(0));
                request.setAttribute("BDisabilitys", bo.getAll(beanSearch));
    
                FTinh beantemp = new FTinh();
                beantemp.setId(bean.getId());
                beantemp = new BTinh().getRecordByID(beantemp);
                beanSearch.setTinhName("");
    
                if (bean.getId() > 0) {
                    bean.setTinhId(bean.getId());
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
                        beanSearch.setTinhName(beanSearch.getTinhName() + " - " + params.get(i));
                    }
                }
                
                beanSearch.setTypeResult(1);
                request.setAttribute("timkiem", beanSearch);
                target = anchor;
            } else if (anchor.equals(_PREPARED_CREATE)) {
                request.setAttribute("input", 1);
                FTinh beanTree = new FTinh();
                if (beans != null && beans.size() > 0) {    
                    beanTree = (FTinh)beans.get(0);
                }
                FTinh beanT = new FTinh();
                BTinh tinh = new BTinh();
                beanT.setId(beanTree.getId());
    
                String code = tinh.getMaxCodeDis(beanT);            
                FDisability beanDis = new FDisability();
                beanDis.setDateLastUpdate(bean.dateToString(bean.getCurrentDate()));
                beanDis.setChuyenMonKhac("");
                beanDis.setGiaoDucKhac("");
                beanDis.setNgheNghiepKhac("");
                beanDis.setHonNhanKhac("");            
                beanDis.setMa(code);
                beanDis.setId(0);
    
                request.setAttribute("BTreeTinhs", beans);
                beanDis.setTinhName("");
                FTinh beanTinh = (FTinh)beans.get(0);
                beanDis.setTinhId(beanTinh.getId());
    
                if (beanDis.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanDis.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanDis.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanDis.setTinhName(beanDis.getTinhName() + " - " + params.get(i));
                    }
                }
                request.setAttribute("disability", beanDis);
                request.setAttribute("BDieuKiens", beansDieuKien);
                request.setAttribute("BDantocForms", beansDanToc);            
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;
            } else if (anchor.equals("_PREPARED_SELECT_KPI")) {
                FDataHdr dataHdr = new FDataHdr();
                FObject object = new FObject();
                FObjectInd objectInd = new FObjectInd();
                String temp = ""; 
                int locationId = 0, objId = 0;
                /*
                 * temp = bean.me.getDepartmentName();            
                if (userId!=375 && !"".equals(temp)) {
                    locationId = Integer.parseInt(temp.replaceAll(",", ""));
                }
                */
                int curYearReport =  Utilities.getCurrentYear(seed.getCurrentDate());
                String strYearReport = (String)request.getParameter("yearReport");
                int yearReport = 0;
                try {
                    yearReport =  (strYearReport!=null)? Integer.parseInt(strYearReport):curYearReport;
                } catch (NumberFormatException nfe) {
                }
                
                objId = bean.getObjId();
                if (objId > 0) {                
                    object.setId(bean.getObjId());
                    object = new BObject().getRecordByID(object);
                    dataHdr.setIns(object.getIns());
                    dataHdr.setCode(object.getCode());
                    dataHdr.setName(object.getName());
                    dataHdr.setDescription(object.getDescription());
                    
                    objectInd.setObjId(bean.getObjId());
                    objectInd.setYear(((curYearReport==yearReport)||yearReport==0)?curYearReport:yearReport);
                    objectInd.setLocationId(locationId);
                    FBeans listIndicator = new BObjectInd().getAll(objectInd, indIds);
                    request.setAttribute("listIndicator", listIndicator);
                } else {
                    //objectInd.setObjId(bean.getObjId());
                    //objectInd.setYear(((curYearReport==yearReport)||yearReport==0)?curYearReport:yearReport);
                    //objectInd.setLocationId(locationId);
                    FIndicator fIndicator = new FIndicator();
                    FBeans listIndicator = new BIndicator().getAll(fIndicator);
                    request.setAttribute("listIndicator", listIndicator);
                }
                
                dataHdr.setYearReport(((curYearReport==yearReport)||yearReport==0)?curYearReport:yearReport);
                dataHdr.setObjId(objId);
                request.setAttribute("kpi", dataHdr);                        
                request.setAttribute("menuIndicatorsKpi", new BIndicatorKpi().getAllRecord(0));
                request.setAttribute("menuObjectInput", new BObject().getAllRecord(0,0));
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;       
            } else if (anchor.equals("_PREPARED_CREATE_KPI") || anchor.equals("_PREPARED_CREATE_MENU_KPI")) {
                if (anchor.equals("_PREPARED_CREATE_MENU_KPI")) {
                    bean.setDtlId(0);    
                }
                
                int typePeriod = 1;
                
                errors.clear();
                FDataHdr dataHdr = new FDataHdr();
                FDataDtl dataDtl = new FDataDtl();
                FPerson dataPer = new FPerson();
                FDataNkt dataNkt = new FDataNkt();            
                Map<String, String> map_combobox = (HashMap<String,String>)mapObject.get("KPI_COMBOX");
                Map<String, String> map_event_type = (HashMap<String,String>)mapObject.get("KPI_EVENT_TYPE");
                Map<String, String> map_event_field = (HashMap<String,String>)mapObject.get("KPI_EVENT_FIELD");
                Map<String, String> map_kpi_vote_result = (HashMap<String,String>)mapObject.get("KPI_VOTE_RESULT");          
                            
                request.setAttribute("BTreeTinhs", beans);
                FTinh beanTinh = new FTinh();
                int locationId = 0;
                String locationName = "";
                if (userId!=375) {
                    beanTinh = (FTinh)beans.get(0);
                    locationId = beanTinh.getId();
                    locationName = beanTinh.getName();
                } else {
                    locationId = defaultLocation;
                    locationName = defaultLocationName;
                }
                
                FBeans districts = map_district.get(String.valueOf(locationId));
                FBeans communes = null;
                int total = 0 ;
                String strYearReport = (String)request.getParameter("yearReport");
                int yearReport =  (strYearReport!=null)? Integer.parseInt(strYearReport): Utilities.getCurrentYear(seed.getCurrentDate());
                
                dataHdr.setLocationId(locationId);            
                if (bean.getIndId() > 0) {
                    FBeans events = new FBeans();
                    FEvent fEvent = new FEvent();
                    
                    int indId = bean.getIndId();
                    int objId = bean.getObjId();
                    int inputType = bean.getInputType();                                
                    int hdrId = 0;
                    int refId = 0;
                    int refIndId = 0;
                    
                    dataHdr.setIndId(indId);
                    dataHdr.setObjId(objId);
                    dataHdr.setInputType(inputType);
                    dataHdr.setLocationId(locationId);
                    dataHdr.setYearReport(yearReport);
                    dataHdr.setType(inputType);
                    
                    
                    FIndicator refIndicator = new FIndicator();
                    refIndicator.setId(indId);
                    refIndicator = new BIndicator().getRecordByID(refIndicator);
                    refIndId = refIndicator.getParentID();                
                    
                    if (inputType!=1) {
                        total = new BDataHdr().count(dataHdr); 
                    }
                    
                    dataHdr.setTotal(total);
                    dataHdr = new BDataHdr().getRecordByIndicator(dataHdr);                
                    
                    dataHdr.setIndId(indId);
                    dataHdr.setObjId(objId);
                    dataHdr.setYearReport(yearReport);
                    dataHdr.setCreateDate(bean.dateToString(dataHdr.getCurrentDate()));
                    dataHdr.setLocationId(locationId);                
                    dataHdr.setLocationName(locationName);
                    dataHdr.setInputType(inputType);
                    dataHdr.setTypePeriod(typePeriod);
                    
                    hdrId = new BDataHdr().getHdrId(dataHdr);
                    refId = new BDataHdr().getRefId(dataHdr);
                                    
                    dataDtl.setLocationId(locationId);
                    dataDtl.setObjId(objId);
                    dataDtl.setIndId(indId);
                    dataDtl.setDataId(hdrId);
                    
                    FDisability fDis = new FDisability();                
                    FBeans acBeans = new FBeans();
                    
                    if (total>0) {
                        if (dataHdr.getType()==Constant.KPI_DATA_VALUE) {
                            dataDtl.setPeriod(1);
                            beans = new BDataDtl().getAll(dataDtl, session);
                        } else if (dataHdr.getType()==Constant.KPI_DATA_DIS)  {
                            beans = new BDataNkt().getAll(dataNkt, session);                        
                            acBeans = new BDisability().getDisKpiAc(fDis);
                        } else if (dataHdr.getType()==Constant.KPI_DATA_PERSON 
                                  ||dataHdr.getType()==Constant.KPI_DATA_HOURS) {
                            
                            dataPer.setEventId(0);
                            dataPer.setLocationId(locationId);
                            dataPer.setDataId(hdrId);
                            dataPer.setObjId(objId);
                            dataPer.setIndId(indId);
                            dataPer.setState(Constant.KPI_STATE_INPUT);
                            dataPer.setCreateFrom(Utilities.createFrom(yearReport));
                            dataPer.setCreateTo(Utilities.createTo(yearReport));
                            beans = new BPerson().getAll(dataPer, session);
                        }
                    } else {
                        if (dataHdr.getType()==Constant.KPI_DATA_HOURS){
                            dataPer.setDataId(refId);
                            beans = new BPerson().getAll(dataPer, session);
                        } else {
                            beans = new FBeans();
                        }
                    }
                    
                    // Object
                    FObject object = new FObject();
                    object.setId(objId);
                    object = new BObject().getRecordByID(object);
                    
                    // Indicator
                    if (dataHdr.getCode()==null) {
                        FIndicator indicator = new FIndicator();
                        indicator.setId(indId);
                        indicator = new BIndicator().getRecordByID(indicator);
                        
                        dataHdr.setCode(indicator.getCode());
                        dataHdr.setName(indicator.getName());
                    }
                    
                    dataHdr.setObjCode(object.getCode());
                    dataHdr.setObjName(object.getName());
                    dataHdr.setObjDesc(object.getDescription());
                    
                    if (dataHdr.getType()==Constant.KPI_DATA_DIS)  {
                        request.setAttribute("BDanTocs", beansDanToc);
                        request.setAttribute("listDataDtlAc", acBeans);
                    } else if (dataHdr.getType()==Constant.KPI_DATA_PERSON) {
                        //FBeans acPersonBeans = (FBeans)request.getSession().getAttribute("LIST_PERSON");
                        request.setAttribute("listPersonAc", acPersonBeans);
                    }
                    
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("kpi", dataHdr);
                    
                    fEvent.setHdrId(hdrId);
                    fEvent.setObjId(objId);
                    fEvent.setIndId(indId);
                    fEvent.setLocationId(locationId);
                    fEvent.setCreateFrom(Utilities.createFrom(yearReport));
                    fEvent.setCreateTo(Utilities.createTo(yearReport));
                    events = new BEvent().getEventByObjInd(fEvent);
                    request.setAttribute("events", events);        
                } else if ((bean.getIndId()==0) && (bean.getObjId()!=0)) {
                    FDisability fDis = new FDisability();
                    FBeans acBeans = new FBeans();
                    FObject object = new FObject();
                    
                    int objId = bean.getObjId();
                    int inputType = 1;                
                    
                    FTinh beanT = new FTinh();
                    beanT.setId(locationId);
                    
                    String disCode = "";
                    if ("375||667".indexOf(String.valueOf(userId))==-1) {
                        disCode = new BTinh().getMaxCodeDis(beanT);
                    }
                    
                    //FBeans disCountDown = (FBeans)request.getSession().getAttribute("LIST_DIS_COUNTDOWN");
                    //FBeans disCountDownLate = (FBeans)request.getSession().getAttribute("LIST_DIS_COUNTDOWN_LATE");
                    fDis.setUserId(userId);
                    fDis.setTinhId(locationId);
                    FBeans listDisCountDown = new BDisability().getCountdownDis(fDis);
                    
                    if (listDisCountDown.size()>0) {
                        dataHdr.setTotalDis(listDisCountDown.size()); 
                    }
                                    
                    fDis.setTinhId(locationId);
                    beans = new BDisability().getDisKpi(fDis);
                    acBeans = new BDisability().getDisKpiAc(fDis);
                    
                    if (bean.getDtlId()>0) {
                        int nktId = bean.getDtlId();
                        fDis.setId(nktId);
                        fDis = new BDisability().getRecordByID(fDis);
                        
                        beanT.setId(fDis.getTinhId());
                        beanT = new BTinh().getRecordByID(beanT);                    
                        locationName = beanT.getName();
                        districts = map_district.get(String.valueOf(fDis.getTinhId()));
                        communes = map_commune.get(String.valueOf(fDis.getDistrictId()));
                        
                        if (anchor.equals("_DETAIL_LIST")) {
                            dataHdr.setMode("DETAIL_LIST");
                        } else {
                            dataHdr.setMode("DETAIL");
                        }
                        
                        // Hdr
                        // hdrId = dataHdr.getId();
                        dataHdr.setDtlId(nktId);
                        dataHdr.setObjId(objId);
                        dataHdr.setType(Constant.KPI_DATA_DIS);
                        dataHdr.setCreateDate(fDis.getDateLastUpdate());
                        dataHdr.setLocationId(fDis.getTinhId());
                        dataHdr.setDistrictId(fDis.getDistrictId());
                        dataHdr.setCommuneId(fDis.getCommuneId());
                        
                        // NKT
                        dataHdr.setNktId(nktId);
                        dataHdr.setDisCode(fDis.getMa());
                        dataHdr.setDisCodeNkt(fDis.getMa_nkt());
                        dataHdr.setDisName(fDis.getNkt());
                        dataHdr.setDisBirth(fDis.getNgaySinh());
                        dataHdr.setDisSex(fDis.getSex()); 
                        dataHdr.setDisPassport(fDis.getCmnd());
                        dataHdr.setDisNation(String.valueOf(fDis.getDantocId()));
                        dataHdr.setDisCarrer(fDis.getNgheNghiepHT());
                        dataHdr.setDisDioxin(fDis.getChatDocDaCam());
                        dataHdr.setDisAddress(fDis.getSoNha());
                        dataHdr.setStatusId(fDis.getTrangthai());
                        dataHdr.setDisPhone(fDis.getPhoneNumber());
                                        
                        // NCS
                        dataHdr.setNcsName(fDis.getTenChamSoc());
                        dataHdr.setNcsBirth(fDis.getNamSinhChamSoc());
                        dataHdr.setNcsRelation(String.valueOf(fDis.getQuanHeChamSoc()));
                        dataHdr.setNcsPhone(fDis.getSdtLienLac());
                        dataHdr.setNcsSex(fDis.getGioiTinhChamSoc());
                        dataHdr.setDuAnId(fDis.getDuAnId());
                    }
                    
                    dataHdr.setLocationName(locationName);
                    dataHdr.setDisCode(dataHdr.getNktId()>0?dataHdr.getDisCode():disCode);
                    dataHdr.setObjId(objId);
                    dataHdr.setType(Constant.KPI_DATA_DIS);
                    dataHdr.setInputType(inputType);
                    object.setId(bean.getObjId());
                    object = new BObject().getRecordByID(object);
                    dataHdr.setObjCode(object.getCode());
                    dataHdr.setObjName(object.getName());
                    dataHdr.setObjDesc(object.getDescription());
                    request.setAttribute("districts", districts);
                    request.setAttribute("communes", communes);
                    request.setAttribute("listCountDown", listDisCountDown);
                    request.setAttribute("listCountDownLate", listDisCountDown);
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("listDataDtlAc", acBeans);
                    request.setAttribute("kpi", dataHdr);
                } else {
                    dataHdr.setCreateDate(bean.dateToString(dataHdr.getCurrentDate()));
                    dataHdr.setTypePeriod(typePeriod);
                    dataHdr.setMonth(0);
                    dataHdr.setQuarter(0);
                    dataHdr.setYear(0);                
                    dataHdr.setActivity("");
                    dataHdr.setLocation("");
                    request.setAttribute("kpi", dataHdr);              
                }            
                
                FIndicator beanTemp = new FIndicator();  
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                
                beanTemp.setPageIndex(bean.getPageIndex());
                
                request.setAttribute("total", total);
                request.setAttribute("mapBaseline", map_combobox);
                request.setAttribute("mapEventType", map_event_type);
                request.setAttribute("mapEventField", map_event_field);
                request.setAttribute("mapVoteResult", map_kpi_vote_result);
                request.setAttribute("BDanTocs", beansDanToc);
                request.setAttribute("menuIndicatorsKpi", new BIndicatorKpi().getAllRecord(0));
                request.setAttribute("menuObjectInput", new BObject().getAllRecord(0,0));
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;
            } else if (anchor.equals("_IMPORT_DATA")) {
                
                FImport beanImport = new FImport();
                request.setAttribute("beanImport", beanImport);
                request.removeAttribute("Map_Columns");
                request.getSession().removeAttribute("BDatas");
                request.getSession().removeAttribute("BTables");
                request.getSession().removeAttribute("BImport");
                target = anchor;
            } else if (anchor.equals("_PREPARED_CREATE_INFO")) {
            
                FUser beanU = new FUser();
                beanU.setId((int)me.getId());
                beanU = new BUsers().getRecordByID(beanU);
                request.setAttribute("input", 1);
                
                if (bean.getLevel() == 0 && bean.getTinhId() == 0 && 
                    beans != null && beans.size() > 0) {
                    FTinh bT = (FTinh)beans.get(0);
                    bean.setTinhId(bT.getId());
                }
                
                int[] tempLevel = getLevels(beans);
                level = tempLevel[bean.getLevel()];
            
                FThongTinTuyen beanP = new FThongTinTuyen();
                beanP.reset();
                beanP.setPageIndex(1);
                
                request.setAttribute("BThongTinTuyens", new BThongTinTuyen().getAll(beanP));
                request.setAttribute("thongtinTuyen", beanP);
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("anchor", "01");
                request.setAttribute("subanchor", "01.03");
                target = anchor;       
                
            } else if (anchor.equals("_EXPORT_DATA")) {
    
                FExport beanExport = new FExport();
                request.setAttribute("exportdata", beanExport);
                request.setAttribute("BTreeTinhs", beans);
                target = anchor;
    
            } else if (anchor.equals(_REPORT)) {
    
                FList beanL;
                beanL = new FList();
                beanL.setUserId((int)me.getId());
                beanL.setPageIndex(bean.getPageIndex());
                BList boL = new BList();
                request.setAttribute("beans", boL.getAllListReport(beanL));
                request.setAttribute("BReports", new BReportSystem().getAll());
                request.setAttribute("anchor", "01");
                target = anchor;
                
            } else if (anchor.equals("_REPORT_DETAIL")){
                
                FList beanL;
                beanL = new FList();
                beanL.setUserId((int)me.getId());
                beanL.setPageIndex(bean.getPageIndex());
                BList boL = new BList();
                request.setAttribute("beans", boL.getAllListReport(beanL));
                request.setAttribute("BReports", new BReportSystem().getAll());
                request.setAttribute("anchor", "01");
                target = anchor;
                
            } else if (anchor.equals("_REPORT_COMMUNE")) {
                request.setAttribute("BTreeTinhs", beans);
                FReportTotal beanP = new FReportTotal();
                beanP.setYearPeriod(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setProvince("");
                beanP.setTinhName("");
                if (beans != null && beans.size() > 0) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    beanP.setTinhId(beanTinh.getId());
                }
    
                if (beanP.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanP.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanP.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
                if (userId!=375) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    defaultLocation = beanTinh.getId();
                    defaultLocationName = beanTinh.getName();
                }
                FBeans districts = map_district.get(String.valueOf(defaultLocation));
                FBeans communes = map_commune.get(String.valueOf(0));
                request.setAttribute("districts", districts);
                request.setAttribute("communes", communes);
                request.setAttribute("reportcommune", beanP);            
                if("_QUANLYCA".equals(func)){
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.05");
                } else {
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.04");
                }
                
                target = anchor;
            } else if (anchor.equals("_REPORT_PARAM")) {
                request.setAttribute("BTreeTinhs", beans);
    
                FReportTotal beanP = new FReportTotal();
                beanP.setYearPeriod(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setTinhName("");
                if (beans != null && beans.size() > 0) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    beanP.setTinhId(beanTinh.getId());
                }
    
                if (beanP.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanP.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanP.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
                request.setAttribute("reportcommune", beanP);            
                func = bean.getFunc();
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
            }  else if (anchor.equals("_REPORT_ANALYSIS_DATA")) {  
                FReportAnalysis beanP = new FReportAnalysis();
                
                request.setAttribute("reportAnalysis", beanP);            
                request.setAttribute("BTreeTinhs", beans);
                
                if("_REPORT_COMMUNE".equals(func)){
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.06");
                } else {
                    request.setAttribute("anchor", "03"); 
                    request.setAttribute("subanchor", "03.05");            
                }
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                target = anchor;
            } else if (anchor.equals("_REPORT_COLLECT_DATA")) {          
                FReportCollect beanP = new FReportCollect();            
                request.setAttribute("reportCollect", beanP);
                request.setAttribute("BTreeTinhs", beans);
                
                if("_REPORT_COMMUNE".equals(func)) {
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.04");
                } else {
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.03");
                }
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                target = anchor;
            } else if (anchor.equals("_REPORT_STATISTICS_DATA")) {          
                request.setAttribute("BTreeTinhs", beans);
    
                FReportTotal beanP = new FReportTotal();
                beanP.setYearPeriod(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setTinhName("");
                if (beans != null && beans.size() > 0) {
                    FTinh beanTinh = (FTinh)beans.get(0);
                    beanP.setTinhId(beanTinh.getId());
                }
    
                if (beanP.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanP.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanP.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
    
                request.setAttribute("reportcommune", beanP);
                if("_REPORT_COMMUNE".equals(func)) {
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.05");
                } else {
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.04");
                }
                request.setAttribute("div_search", "false");
                request.setAttribute("div_report", "true");
                target = anchor;            
            } else if (anchor.equals("_REPORT_KPI")) {
                FReportKpi beanP = new FReportKpi(); 
                String msgJob = "B&#225;o c&#225;o/D&#7919; li&#7879;u NKT &#273;&#227; k&#7871;t xu&#7845;t th&#224;nh c&#244;ng v&#224;o l&#250;c [$last-update$], B&#7841;n c&#243; th&#7875; khai th&#225;c th&#244;ng tin.";
                String msgInit = "B&#225;o c&#225;o/D&#7919; li&#7879;u NKT ch&#432;a &#273;&#432;&#7907;c k&#7871;t xu&#7845;t l&#7847;n &#273;&#7847;u, b&#7841;n ch&#432;a th&#7875; khai th&#225;c th&#244;ng tin!";
                
                FJobLog jobLog = new FJobLog();
                jobLog.setJobCode(func);
                jobLog.setLocationId(defaultLocation);
                FBeans jobLogs = new BJobLog().getLogsByJobCode(jobLog);
                String jobLastUpdate = "";
                
                if (jobLogs!=null && jobLogs.size()>0) {
                    jobLog = (FJobLog)jobLogs.get(0);
                    jobLastUpdate = Utilities.parseDateToTringType4(jobLog.getEndExec());
                }
                
                if("_REPORT_INDICATOR".equals(func)) {
                    beanP.setJobMsg(!"".equals(jobLastUpdate)?beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):beanP.ncrToString(msgInit));
                    beanP.setPeriodType("1");
                    beanP.setSubFunction("04.01");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.01");
                } else if ("_REPORT_OBJECT".equals(func)) {
                  beanP.setJobMsg(!"".equals(jobLastUpdate)?beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):beanP.ncrToString(msgInit));
                    beanP.setPeriodType("1");
                    beanP.setSubFunction("04.02");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.02");
                } else if ("_REPORT_INSURANCE".equals(func)) {
                    beanP.setSubFunction("04.03");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.03");                            
                } else if ("_REPORT_SUPPORT".equals(func)) {
                    beanP.setYearReport(bean.getYear(bean.getCurrentSqlDate()));
                    beanP.setSubFunction("04.04");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.04"); 
                } else if ("_REPORT_COMMUNE".equals(func)) {
                    beanP.setJobMsg(beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)));
                    beanP.setYearReport(bean.getYear(bean.getCurrentSqlDate()));
                    beanP.setSubFunction("04.05");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.05"); 
                } else if ("_REPORT_EXPORT".equals(func)) {
                    beanP.setJobMsg(!"".equals(jobLastUpdate)?beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):beanP.ncrToString(msgInit));
                    beanP.setYearReport(bean.getYear(bean.getCurrentSqlDate()));
                    beanP.setSubFunction("04.06");
                    request.setAttribute("anchor", "04");
                    request.setAttribute("subanchor", "04.06"); 
                } else if ("_REPORT_SUPPORT_LIST".equals(func)) {
                      //beanP.setJobMsg(!"".equals(jobLastUpdate)?beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):beanP.ncrToString(msgInit));
                      //beanP.setYearReport(bean.getYear(bean.getCurrentSqlDate()));
                      beanP.setSubFunction("04.07");
                      request.setAttribute("anchor", "04");
                      request.setAttribute("subanchor", "04.07");
                } else if ("_REPORT_EXPORT_2020".equals(func)) {
                    beanP.setJobMsg(!"".equals(jobLastUpdate)?beanP.ncrToString(msgJob.replace("[$last-update$]", jobLastUpdate)):beanP.ncrToString(msgInit));
                    beanP.setYearReport(bean.getYear(bean.getCurrentSqlDate()));
                    beanP.setSubFunction("03.02");
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.02");               
                } else {
                    request.setAttribute("anchor", "03");
                    request.setAttribute("subanchor", "03.08");
                    request.setAttribute("div_search", "false");
                    request.setAttribute("div_report", "true");
                }
                request.setAttribute("reportkpi", beanP);
                request.setAttribute("BTreeTinhs", ("|_REPORT_EXPORT|_REPORT_COMMUNE|_REPORT_EXPORT_2020|_REPORT_SUPPORT_LIST|".indexOf("|"+func+"|")>-1)? beansTree:beans);           
                target = anchor;
            } else if (anchor.equals("_LISTDISTRICT")) {
                if (bean.getId() > 0) {
                    FTinh beantemp = new FTinh();
                    beantemp.setId(bean.getId());
                    request.setAttribute("tinh", new BTinh().getRecordByID(beantemp));
                } else {
                    request.setAttribute("tinh", new FTinh());
                }            
                characters = "/ ";
                member = "";            
                SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ORDER BY order_by";
                FBeans tinhBeans = new FBeans();
                tinhBeans = new BTreeView().getTree(0, false, SQL, characters, member);
                request.setAttribute("BTreeAllTinhs", tinhBeans);
                request.setAttribute("BListTinhs", new BTinh().getAllRecordByParentId((bean.getId() > 0) ? bean.getId() : 0));            
                target = anchor;
            } else if (anchor.equals("_LIST_DANGTAT")) {    
                if (bean.getId() > 0) {
                    FDangTat beantemp = new FDangTat();
                    beantemp.setId(bean.getId());
                    request.setAttribute("dangtat", new BDangTat().getRecordByID(beantemp));
                } else {
                    FDangTat beantemp = new FDangTat();
                    request.setAttribute("dangtat", beantemp);
                }    
                SQL = "SELECT dangtat_id,parent_id,name FROM dr_classification WHERE parent_id = ?";
                characters = "/ ";
                member = "";
                
                FBeans DangTatbeans = new FBeans();
                DangTatbeans = new BTreeView().getTree(0, false, SQL, characters, member);    
                request.setAttribute("BTreeDangTats", DangTatbeans);
                request.setAttribute("BDangTats", new BDangTat().getAllRecord(0));
                target = anchor;    
            } else if (anchor.equals("_LISTCONDITION")) {    
                if (bean.getId() > 0) {
                    FDieuKien beantemp = new FDieuKien();
                    beantemp.setId(bean.getId());
                    request.setAttribute("dieukien", new BDieuKien().getRecordByID(beantemp));
                } else {
                    request.setAttribute("dieukien", new FDieuKien());
                }
                request.setAttribute("BTreeDieuKiens", new BDieuKien().getAllRecord(0));
                target = anchor;
    
            } else if (anchor.equals("_LIST_QUANHE")) {    
                if (bean.getId() > 0) {
                    FQuanhe beantemp = new FQuanhe();
                    beantemp.setId(bean.getId());
                    request.setAttribute("quanhe", new BQuanhe().getRecordByID(beantemp));
                } else {
                    request.setAttribute("quanhe", new FQuanhe());
                }
                request.setAttribute("BQuanhes", new BQuanhe().getAllRecord(0));
                target = anchor;
            } else if (anchor.equals("_LIST_NGUYENNHAN")) {
    
                if (bean.getId() > 0) {
                    FNguyennhan beantemp = new FNguyennhan();
                    beantemp.setId(bean.getId());
                    request.setAttribute("nguyennhan", new BNguyennhan().getRecordByID(beantemp));
                } else {
                    request.setAttribute("nguyennhan", new FNguyennhan());
                }
                characters = "/ ";
                member = "";
                SQL = "SELECT nguyennhan_id,parent_id,name FROM dr_nguyennhan WHERE parent_id = ?";
    
                FBeans NguyenNhanbeans = new FBeans();
                NguyenNhanbeans = new BTreeView().getTree(0, false, SQL, characters, member);
                request.setAttribute("BTreeNguyennhans", NguyenNhanbeans);
                request.setAttribute("BNguyennhans", new BNguyennhan().getAllRecord(0));
                target = anchor;
    
            } else if (anchor.equals("_LIST_DANTOC")) {
    
                if (bean.getId() > 0) {
                    FDantoc beantemp = new FDantoc();
                    beantemp.setId(bean.getId());
                    request.setAttribute("dantoc", new BDantoc().getRecordByID(beantemp));
                } else {
                    request.setAttribute("dantoc", new FDantoc());
                }
                request.setAttribute("BDantocs", new BDantoc().getAllRecord(0));
                target = anchor;
    
            } else if (anchor.equals("_LIST_DUNGCU")) {
    
                if (bean.getId() > 0) {
                    FDungcu beantemp = new FDungcu();
                    beantemp.setId(bean.getId());
                    request.setAttribute("dungcu", new BDungcu().getRecordByID(beantemp));
                } else {
                    request.setAttribute("dungcu", new FDungcu());
                }
                request.setAttribute("BDungcus", new BDungcu().getAllRecord(0));
                target = anchor;
    
            } else if (anchor.equals("_LIST_HOTRO")) {
    
                if (bean.getId() > 0) {
                    FHotro beantemp = new FHotro();
                    beantemp.setId(bean.getId());
                    request.setAttribute("hotro", new BHotro().getRecordByID(beantemp));
                } else {
                    request.setAttribute("hotro", new FDungcu());
                }
    
                characters = "/ ";
                member = "";
                SQL = "SELECT hotro_id,parent_id,name FROM dr_hotro WHERE parent_id = ?";
    
                FBeans HoTrobeans = new FBeans();
                HoTrobeans = new BTreeView().getTree(0, false, SQL, characters, member);
    
                request.setAttribute("BTreeHotros", HoTrobeans);
                request.setAttribute("BHotros", new BHotro().getAllRecord(0));
                target = anchor;        
            } else if (anchor.equals("_LIST_MUCDO")) {
                if (bean.getId() > 0) {
                    FMucdo beantemp = new FMucdo();
                    beantemp.setId(bean.getId());
                    request.setAttribute("mucdo", new BMucdo().getRecordByID(beantemp));
                } else {
                    request.setAttribute("mucdo", new FMucdo());
                }
    
                characters = "/ ";
                member = "";
                SQL = "SELECT mucdo_id,parent_id,name FROM dr_mucdo WHERE parent_id = ?";
                FBeans MucDobeans = new FBeans();
                MucDobeans = new BTreeView().getTree(0, false, SQL, characters, member);
    
                request.setAttribute("BTreeMucdos", MucDobeans);
                request.setAttribute("BMucdos", new BMucdo().getAllRecord(0));
                target = anchor;
            
            } else if (anchor.equals("_TINHHINH")) {
                if (bean.getId() > 0) {
                    FDangTat beantemp = new FDangTat();
                    beantemp.setId(bean.getId());
                    request.setAttribute("danhgia", new BDanhgia().getRecordByID(beantemp));
                } else {
                    request.setAttribute("danhgia", new FDanhgia());
                }
    
                characters = "/ ";
                member = "";
                SQL = "SELECT id,parent_id,name FROM kpi_rank WHERE parent_id = ?";
    
                FBeans beansRank = new FBeans();
                beansRank = new BTreeView().getTree(0, false, SQL, characters, member);
    
                request.setAttribute("BTreeRanks", beansRank);
                request.setAttribute("BRanks", new BRank().getAllRecordByParent(0));
                target = anchor;
            } else if (anchor.equals("_LIST_DANHGIA")) {
    
                if (bean.getId() > 0) {
                    FDanhgia beantemp = new FDanhgia();
                    beantemp.setId(bean.getId());
                    request.setAttribute("danhgia", new BDanhgia().getRecordByID(beantemp));
                } else {
                    request.setAttribute("danhgia", new FDanhgia());
                }
    
                characters = "/ ";
                member = "";
                SQL = "SELECT tinhtrang_id,parent_id,name FROM dr_tinhtrang WHERE parent_id = ?";
    
                FBeans TinhTrangbeans = new FBeans();
                TinhTrangbeans = new BTreeView().getTree(0, false, SQL, characters, member);
    
                request.setAttribute("BTreeDanhgias", TinhTrangbeans);
                request.setAttribute("BDanhgias", new BDanhgia().getAllRecord(0));
                target = anchor;
            } else if (anchor.equals("_LIST_NGUONHOTRO")) {
                if (bean.getId() > 0) {
                    FNguonhotro beantemp = new FNguonhotro();
                    beantemp.setId(bean.getId());
                    request.setAttribute("nguonhotro", new BNguonhotro().getRecordByID(beantemp));
                } else {
                    request.setAttribute("nguonhotro", new FNguonhotro());
                }
    
                characters = "/ ";
                member = "";
                SQL = "SELECT nguonhotro_id,parent_id,name FROM dr_nguonhotro WHERE parent_id = ?";
    
                FBeans NguonHoTrobeans = new FBeans();
                NguonHoTrobeans = new BTreeView().getTree(0, false, SQL, characters, member);
    
                request.setAttribute("BTreeNguonhotros", NguonHoTrobeans);
                request.setAttribute("BNguonhotros", new BNguonhotro().getAllRecord(0));
                target = anchor;
            } else if (anchor.equals("_LIST_DOITUONG")) {
                  if (bean.getId() > 0) {
                      FDoiTuong beantemp = new FDoiTuong();
                      beantemp.setId(bean.getId());
                      request.setAttribute("doituong", new BDoiTuong().getRecordByID(beantemp));
                  } else {
                      request.setAttribute("doituong", new FDoiTuong());
                  }
    
                  characters = "/ ";
                  member = "";
                  SQL = "SELECT doituong_id,parent_id,name FROM dr_doituong WHERE parent_id = ?";
    
                  FBeans DoiTuongbeans = new FBeans();
                  DoiTuongbeans = new BTreeView().getTree(0, false, SQL, characters, member);
    
                  request.setAttribute("BTreeDoiTuongs", DoiTuongbeans);
                  request.setAttribute("BDoiTuongs", new BDoiTuong().getAllRecord(0));
                  target = anchor;
             } else if (anchor.equals("_LIST_OBJECT")) {
                int parentId = 0;
                FObject beanTemp = new FObject();
                if (bean.getId() > 0) {                
                    beanTemp.setId(bean.getId());
                    parentId = bean.getId();
                    request.setAttribute("object", new BObject().getRecordByID(beanTemp));
                } else {
                    request.setAttribute("object", new FObject());
                }
    
                beanTemp = new FObject();  
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                
                beanTemp.setYear(2016);
                beanTemp.setParentID(parentId);
                beanTemp.setPageIndex(bean.getPageIndex());
    
                characters = "/ ";
                member = "";
                SQL = "SELECT id,parent_id,name FROM kpi_object WHERE parent_id = ? order by code";
    
                optObjects = new FBeans();
                optObjects = new BTreeView().getTree(0, false, SQL, characters, member);
                
                request.setAttribute("optObjects", optObjects);
                request.setAttribute("menuObjects", new BObject().getAllRecord(0,0));            
                request.setAttribute("listObjects", new BObject().getAll(beanTemp));
                target = anchor;  
            } else if (anchor.equals("_LIST_INDICATOR")) {
                errors.clear();
                if (bean.getId() > 0) {
                    FIndicator beantemp = new FIndicator();
                    beantemp.setId(bean.getId());
                    request.setAttribute("indicator", new BIndicator().getRecordByID(beantemp));
                } else {
                    FIndicator indicator = new FIndicator();
                    indicator.setType(1);
                    indicator.setTypePeriod(0);
                    request.setAttribute("indicator", indicator);
                }
      
                FIndicator beanTemp = new FIndicator();  
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                
                beanTemp.setPageIndex(bean.getPageIndex());
      
                request.setAttribute("menuIndicators", new BIndicator().getAllRecord(0));
                request.setAttribute("listIndicators", new BIndicator().getAll(beanTemp));
                target = anchor;
            } else if (anchor.equals("_LIST_RANK")) {
                errors.clear();
                if (bean.getId() > 0) {
                    FRank beantemp = new FRank();
                    beantemp.setId(bean.getId());
                    request.setAttribute("rank", new BRank().getRecordByID(beantemp));
                } else {
                    FRank rank = new FRank();
                    request.setAttribute("rank", rank);
                }
            
                FRank beanTemp = new FRank();  
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                
                beanTemp.setPageIndex(bean.getPageIndex());
            
                characters = "/ ";
                member = "";
                SQL = "SELECT id,parent_id,name FROM kpi_rank WHERE parent_id = ?";
            
                FBeans optRanks = new FBeans();
                optRanks = new BTreeView().getTree(0, false, SQL, characters, member);
              
                request.setAttribute("optRanks", optRanks);
                request.setAttribute("menuRanks", new BRank().getAllRecord(0));
                request.setAttribute("listRanks", new BRank().getAllRecord(0));
                target = anchor;
            } else if (anchor.equals("_LIST_EVENT")) {
                errors.clear();            
                Map<String, String> map_combobox = (HashMap<String,String>)mapObject.get("KPI_COMBOX");
                Map<String, String> map_event_type = (HashMap<String,String>)mapObject.get("KPI_EVENT_TYPE");
                Map<String, String> map_event_field = (HashMap<String,String>)mapObject.get("KPI_EVENT_FIELD");
                Map<String, String> map_kpi_vote_result = (HashMap<String,String>)mapObject.get("KPI_VOTE_RESULT");   
                FEvent beantemp = new FEvent();
                if (bean.getId()>0) {
                    beantemp.setEventId(bean.getId());
                    request.setAttribute("event", new BEvent().getRecordByID(beantemp));
                } else {
                    request.setAttribute("event", beantemp);
                }
                
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
                
                beantemp.setPageIndex(bean.getPageIndex());
                request.setAttribute("mapBaseline", map_combobox);
                request.setAttribute("mapEventType", map_event_type);
                request.setAttribute("mapEventField", map_event_field);
                request.setAttribute("mapVoteResult", map_kpi_vote_result);
                //request.setAttribute("menuEvents", new BEvent().getAllRecord(0));
                getListEvent(beantemp, request, session);
                target = anchor;
            } else if (anchor.equals("_UNIT")) {
                FUnit beanTemp = new FUnit();  
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
    
                beanTemp.setPageIndex(bean.getPageIndex());
                beanTemp.setId_type(bean.getCategory_id());
                beanTemp.setTinhId(0);
                
                request.setAttribute("BCategoryUnits", new BUnit().getAllCategory());
                request.setAttribute("BUnits", new BUnit().getAll(beanTemp));
                request.setAttribute("dr_unit", new FUnit());
    
                SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
                member = "";
                if ((bean.me.getDepartmentName() != null) && 
                    (!bean.me.getDepartmentName().equals(""))) {
                    member = bean.me.getDepartmentName();
                }
                request.setAttribute("BTreeTinhs", new BTreeView().getTree(0, false, SQL, "--", member));
                target = anchor;
            } else if (anchor.equals("_UNIT_HOME")) {
    
                FUnit beanTemp = new FUnit();            
                
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
    
                beanTemp.setPageIndex(bean.getPageIndex());
                beanTemp.setId_type(bean.getCategory_id());
                beanTemp.setTinhId(bean.getTinhId());
                
                request.setAttribute("BCategoryUnits", new BUnit().getAllCategory());
                request.setAttribute("BUnits", new BUnit().getAll(beanTemp));
                request.setAttribute("dr_unit", new FUnit());
    
                SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
                member = "";
                if ((bean.me.getDepartmentName() != null) && 
                    (!bean.me.getDepartmentName().equals(""))) {
                    member = bean.me.getDepartmentName();
                }
                request.setAttribute("BTinhs", new BTreeView().getTree(0, false, SQL, "--", member));
                target = anchor;    
            
            } else if (anchor.equals("_SEARCH_UNIT")) {
                FUnit beanTemp = new FUnit();            
                
                if (bean.getPageIndex() <= 0)
                    bean.setPageIndex(1);
    
                beanTemp.setPageIndex(bean.getPageIndex());
                beanTemp.setId_type(bean.getCategory_id());
                beanTemp.setTinhId(bean.getTinhId());
                
                request.setAttribute("BCategoryUnits", new BUnit().getAllCategory());
                request.setAttribute("BUnits", new BUnit().getAll(beanTemp));
                request.setAttribute("dr_unit", new FUnit());
    
                SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
                member = "";
                if ((bean.me.getDepartmentName() != null) && 
                    (!bean.me.getDepartmentName().equals(""))) {
                    member = bean.me.getDepartmentName();
                }
                request.setAttribute("BTinhs", new BTreeView().getTree(0, false, SQL, "--", member));
                target = anchor;
            } else if (anchor.equals("_VIEW_OPTION")) {
    
                FUser beanU = new FUser();
                beanU.setId((int)me.getId());
                beanU = new BUsers().getRecordByID(beanU);
                String membersRule = beanU.getArea();
                request.setAttribute("input", 1);
                 beans = new FBeans();
    
                SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
                if (membersRule != null && !membersRule.equals("")) {
                    membersRule = membersRule.substring(1, membersRule.length() - 1);
                    SQL += " AND (tinh_id in (" + membersRule + ")" + " or tinh_id IN (select tinh_id from dr_area where parent_id =0 order by tinh_id LIMIT 1 ))";
                }
    
                characters = "/ ";
                member = "";
                beans = new BTreeView().getTree(0, false, SQL, characters, member);
                request.setAttribute("BTreeTinhs", beans);
    
                if (bean.getLevel() == 0 && bean.getTinhId() == 0 && 
                    beans != null && beans.size() > 0) {
                    FTinh bT = (FTinh)beans.get(0);
                    bean.setTinhId(bT.getId());
                }
                bean.setTotalPopulation(new BTinh().getCount(bean.getTinhId()));
                int[] tempLevel = getLevels(beans);
                level = tempLevel[bean.getLevel()];
    
                if (level == 0)         anchor = "_MANAGER_DANSOTINH";
                else if (level == 1)    anchor = "_MANAGER_DANSOHUYEN";
                else {
                    if (level >= 3)
                        errors.add("alert", new ActionError("alert.disability.inforreport.error"));
                        anchor = "_MANAGER_POPULATION";
                }
                
                request.setAttribute("anchor","01");
                request.setAttribute("subanchor", "01.02");
            }
            if (anchor.equals("_MANAGER_DANSOTINH")) {
    
                FDanSoTinh beanP = new FDanSoTinh();
                beanP.setId_tinh(bean.getTinhId());
                beanP.setTongHuyen(bean.getTotalPopulation());
                beanP.setNam(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setTinhName("");
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
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
    
                request.setAttribute("danSoTinh", beanP);
                request.setAttribute("BDanSoTinhs", new BDanSoTinh().getAllByIdTinh(bean.getTinhId()));
                request.setAttribute("anchor","01");
                request.setAttribute("subanchor", "01.02");
                target = anchor;
            }
            if (anchor.equals("_MANAGER_DANSOHUYEN")) {
    
                FDanSoHuyen beanP = new FDanSoHuyen();
                beanP.setId_tinh(bean.getTinhId());
                beanP.setTongXa(bean.getTotalPopulation());
                beanP.setNam(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setTinhName("");
    
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
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
    
                request.setAttribute("danSoHuyen", beanP);
                request.setAttribute("BDanSoHuyens", new BDanSoHuyen().getAllByIdTinh(bean.getTinhId()));
                request.setAttribute("anchor","01");
                request.setAttribute("subanchor", "01.02");
                target = anchor;
            }
    
            if (anchor.equals("_MANAGER_POPULATION")) {
    
                FPopulation beanP = new FPopulation();
                beanP.setId_tinh(bean.getTinhId());
                beanP.setYearOfPeriod(bean.getYear(bean.getCurrentSqlDate()));
                beanP.setLevel_province(level);
    
                beanP.setTinhName("");
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
                        beanP.setTinhName(beanP.getTinhName() + " - " + params.get(i));
                    }
                }
    
                request.setAttribute("population", beanP);
                request.setAttribute("BPopulations", new BPopulation().getAllByIdTinh(bean.getTinhId()));
                request.setAttribute("anchor","01");
                request.setAttribute("subanchor", "01.02");
                target = anchor;
             }            
              
              if (!errors.isEmpty())
                  saveErrors(request, errors);
      
              return mapping.findForward(target);
        } catch (Exception ex) {
              logger.info(ex.toString());
              return mapping.findForward(_ERROR);
        }
    }

    private int[] getLevels(FBeans beans) {
        int[] levels = null;
        if (beans != null) {
            FTinh beant = null;
            levels = new int[beans.size()];
            for (int k = 0; k < beans.size(); k++) {
                beant = (FTinh)beans.get(k);
                levels[k] = beant.getLevel();
            }
        }
        return levels;
    }
    
    private void getListEvent(FEvent fEvent, HttpServletRequest request, HttpSession session) {
        FBeans beans = new FBeans();
        try {
            beans = new BEvent().getAll(fEvent, session);
            request.setAttribute("listEvents", beans);
            request.setAttribute("total", beans.size());
        } catch (EException e) {            
        }
    }
}
