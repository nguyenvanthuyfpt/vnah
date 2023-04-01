package com.action.disability;


import com.action.ACore;

import com.bo.admin.users.BUsers;
import com.bo.disability.BDataDtl;
import com.bo.disability.BDataHdr;
import com.bo.disability.BDataNkt;
import com.bo.disability.BDataPer;
import com.bo.disability.BDataRank;
import com.bo.disability.BDisProfile;
import com.bo.disability.BDisReport;
import com.bo.disability.BDisability;
import com.bo.disability.BEventObjInd;
import com.bo.disability.BHomeVisit;
import com.bo.disability.BObjectInd;
import com.bo.disability.BPerson;
import com.bo.disability.BPhanLoai;
import com.bo.disability.BSupport;
import com.bo.disability.categorys.BDangTat;
import com.bo.disability.categorys.BEvent;
import com.bo.disability.categorys.BIndicator;
import com.bo.disability.categorys.BNguonhotro;
import com.bo.disability.categorys.BObject;
import com.bo.disability.categorys.BRank;
import com.bo.disability.categorys.BTinh;
import com.bo.disability.search.BSearch;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportKpiChart;
import com.dao.disability.report.DReportKpiData;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FDataDtl;
import com.form.disability.FDataHdr;
import com.form.disability.FDataNkt;
import com.form.disability.FDataPer;
import com.form.disability.FDataRank;
import com.form.disability.FDisChart;
import com.form.disability.FDisProfile;
import com.form.disability.FDisReport;
import com.form.disability.FDisability;
import com.form.disability.FEventObjInd;
import com.form.disability.FHomeVisit;
import com.form.disability.FObjectInd;
import com.form.disability.FPerson;
import com.form.disability.FPhanLoai;
import com.form.disability.FSupport;
import com.form.disability.categorys.FEvent;
import com.form.disability.categorys.FIndicator;
import com.form.disability.categorys.FObject;
import com.form.disability.categorys.FRank;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportKpiData;
import com.form.disability.search.FSearch;

import com.inf.disability.IKeyDisability;

import com.lib.Base64Coder;

import com.util.Constant;
import com.util.Formater;
import com.util.Utilities;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


public class AIndicatorKpi extends  ACore {
    
    final static Logger logger = Logger.getLogger(AIndicatorKpi.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
      throws EException, IOException, ServletException, SQLException {
        try { 
             
            final String LOCATION = this + "->executeAction()";
            String target = _LOGIN;
            FDataHdr fdataHdr =(FDataHdr)form;        
            
            FDataDtl fdataDtl = new FDataDtl();
            FPerson fPerson = new FPerson();
            FDisability fDis = new FDisability();      
            FEvent fEvent = new FEvent();
          
            FDataNkt fdataNkt = new FDataNkt();
            FDataPer fdataPer = new FDataPer();
            FDataRank fdataRank = new FDataRank();
            
            FBeans beans = null;
            String anchor = fdataHdr.getValue(APP_ANCHOR,"");
            HttpSession session = seed.getRequest().getSession();
            ActionErrors errors = new ActionErrors();        
            ActionMessages success = new ActionMessages();
            
            target = validate(fdataHdr, anchor, errors);
            
            int userId = (int)fdataHdr.me.getId();   
            int inputType = fdataHdr.getInputType();
            int type = fdataHdr.getType();
            int perSel = fdataHdr.getPerSel();
            int hdrId = 0, dtlId = 0, perId =0, refId = 0, disId = 0, indId = 0, objId = 0; 
            int locationId = 0, districtId = 0, communeId = 0;
            int total = 0, typeSel = 0, period = 1;
            int nktId = 0, eventId = 0, yearReport = 0;
            int rankId = fdataHdr.getRankId();
            int rankDtlId = fdataHdr.getDtlId();
            int state = fdataHdr.getState();
            int tabId = fdataHdr.getTabId();
            int lvl = fdataHdr.getLvl();
            period = fdataHdr.getTypePeriod();
            perId = fdataHdr.getPerId();
            
            int defaultParent = 1;
            int parRankId = fdataHdr.getParRankId();        
            int parentId = 0;
            String msgInfoNum = "Hi&#7879;n b&#7841;n m&#7899;i &#273;&#225;nh gi&#225; [$num-indicator$] ch&#7881; ti&#234;u.";            
            String msgInfoInit = "Hi&#7879;n NKT ch&#432;a th&#7921;c hi&#7879;n &#273;&#225;nh gi&#225; l&#7847;n n&#224;o.B&#7841;n vui l&#242;ng th&#7921;c hi&#7879;n &#272;&#225;nh gi&#225; ban &#273;&#7847;u cho NKT.";
            String msgInfoNext = "Ng&#224;y &#273;&#225;nh gi&#225; g&#7847;n nh&#7845;t c&#7911;a NKT: [$rank-date$], B&#7841;n vui l&#242;ng th&#7921;c hi&#7879;n \"T&#225;i &#273;&#225;nh gi&#225;\" cho NKT";
            int totalIndicator = 0;
            int numRankIndicator = 0;
            
          
            FIndicator indTemp = new FIndicator();
            
            String createDate = "", locationName = "", disCode = "", rankInitDate = "", rankCurDate = "";
            String profileDate = "";
            String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
            String characters = "/ ";
            String member = "";
            
            if ((fdataHdr.me.getDepartmentName() != null) && 
                (!fdataHdr.me.getDepartmentName().equals(""))) {
                member = fdataHdr.me.getDepartmentName();            
            }
            
            locationId = fdataHdr.getLocationId();
            districtId = fdataHdr.getDistrictId();
            communeId = fdataHdr.getCommuneId();
            FTinh fTinh = new FTinh();
            BTinh bTinh = new BTinh();          
            fTinh.setId(locationId);
            
            if (locationId > 0) {
                fTinh = bTinh.getRecordByID(fTinh);            
                locationName = fTinh.getName();
            } else {
                locationName = "";
            }
            
            //fdataHdr.setDisCode(disCode);
            fdataHdr.setLocationName(locationName);
            
            String indIds = (String)request.getSession().getAttribute("INDICATOR_IDS");        
            
            beans = (FBeans)session.getAttribute("BTreeTinhs");
            request.setAttribute("BTreeTinhs", beans);
            request.setAttribute("BSearchTinhs", beans);
            
            // Tree Object
            FBeans optObjects = new FBeans();
            optObjects = new BTreeView().getTree(0, false, SQL, characters, member);        
            request.setAttribute("BTreeObjects", optObjects);       
          
            // Tree Indicator
            FBeans optIndicators = new FBeans();
            SQL = "SELECT id,parent_id,name FROM kpi_indicator WHERE parent_id = ?";
            characters = "/ ";
            member = "";
            optIndicators = new BTreeView().getTree(0, false, SQL, characters, member);
            
            // Menu Object
            FBeans menuObject = (FBeans)request.getSession().getAttribute("MENU_OBJECT");
            FBeans menuIndicator = (FBeans)request.getSession().getAttribute("MENU_INDICATOR");
                            
            // Ho tro
            FBeans beansHoTro = (FBeans)request.getSession().getAttribute("LIST_HOTRO");
            FBeans beansNhuCau = (FBeans)request.getSession().getAttribute("LIST_NHUCAU");
            request.setAttribute("BSupports", (tabId==0)?beansNhuCau:beansHoTro);
           
            // Nguon-ho-tro
            FBeans beansNguonHoTro  = (FBeans)request.getSession().getAttribute("LIST_NGUONHOTRO");
            request.setAttribute("BNguonHoTros", beansNguonHoTro);
            
            // Dieu-kien-kinh-te
            FBeans beansDieuKien = (FBeans)request.getSession().getAttribute("LIST_DIEUKIEN");
            request.setAttribute("BTreeDienKien", beansDieuKien);
            
            // Nguyen-nhan
            FBeans beansNguyenNhan = (FBeans)request.getSession().getAttribute("LIST_NGUYENNHAN");
            request.setAttribute("BTreeNguyennhans", beansNguyenNhan);
            
            // Muc-do
            FBeans beansMucDo = (FBeans)request.getSession().getAttribute("LIST_MUCDO");
            request.setAttribute("BTreeMucdos", beansMucDo);
            
            // Dan-toc
            FBeans beansDanToc = (FBeans)request.getSession().getAttribute("LIST_DANTOC");
            request.setAttribute("BDanTocs", beansDanToc);
            
            // Trang-thai
            FBeans beansTinhTrang = (FBeans)request.getSession().getAttribute("LIST_TINHTRANG");
            request.setAttribute("BTinhTrangsForms", beansTinhTrang);
            
            // Ly-do
            FBeans beansLyDo = (FBeans)request.getSession().getAttribute("LIST_LYDO");
            request.setAttribute("BResons", beansLyDo);
            
            // Person
            FBeans acPersonBeans = (FBeans)request.getSession().getAttribute("LIST_PERSON");        
            request.setAttribute("listPersonAc", acPersonBeans);
            
            BDataHdr bdataHdr = new BDataHdr();
            BDataDtl bdataDtl = new BDataDtl();
            BPerson bPerson = new BPerson();        
            BEvent bEvent = new BEvent();        
            BSupport bSupport = new BSupport();
            BDisability bDisability = new BDisability();
            
            // Map        
            BDataPer bdataPer = new BDataPer();
            
            Map<String, Object> mapObject = (Map<String, Object>)request.getSession().getAttribute("MAP_OBJECT");
            Map<String, String> map_combobox = (HashMap<String,String>)mapObject.get("KPI_COMBOX");
            Map<String, String> map_event_type = (HashMap<String,String>)mapObject.get("KPI_EVENT_TYPE");
            Map<String, String> map_event_field = (HashMap<String,String>)mapObject.get("KPI_EVENT_FIELD");
            Map<String, String> map_kpi_vote_result = (HashMap<String,String>)mapObject.get("KPI_VOTE_RESULT"); 
            Map<String, String> map_phanloai_diadiem = (HashMap<String,String>)mapObject.get("KPI_PHANLOAI_DIADIEM");
            Map<String, String> map_hotro = (HashMap<String,String>)mapObject.get("NKT_HOTRO");
            Map<String, String> map_quanhe = (HashMap<String,String>)mapObject.get("NKT_QUANHE");
            
            Map<String, String> map_hotro_kn_chitra = (HashMap<String,String>)mapObject.get("KPI_HOTRO_KN_CHITRA");
            Map<String, String> map_hotro_the_bhyt = (HashMap<String,String>)mapObject.get("KPI_HOTRO_THE_BHYT");
            Map<String, String> map_hotro_sd_the = (HashMap<String,String>)mapObject.get("KPI_HOTRO_SD_THE");
            Map<String, String> map_hotro_sd_the_phcn = (HashMap<String,String>)mapObject.get("KPI_HOTRO_SD_THE_PHCN");        
            Map<String, String> map_hotro_danhgia = (HashMap<String,String>)mapObject.get("KPI_HOTRO_DANHGIA");
            Map<String, FBeans> map_district = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_DISTRICT");
            Map<String, FBeans> map_commune = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_COMMUNE");
             
            hdrId = fdataHdr.getId();           
            dtlId = fdataHdr.getDtlId();
            objId = fdataHdr.getObjId();
            indId = fdataHdr.getIndId();
            eventId = fdataHdr.getEventId();
            yearReport = fdataHdr.getYearReport();        
            disId = fdataHdr.getNktId();
            perId = fdataHdr.getPerId();
            nktId = fdataHdr.getNktId();
            createDate = fdataHdr.getCreateDate();
            profileDate = fdataHdr.getCreateDate();
            typeSel = fdataHdr.getTypeSel();
            period = fdataHdr.getTypePeriod();
            
            String rankDate = "", strRankDate = "";
            String[] arrRankDate = null;
            rankInitDate = new BRank().getRankDate(nktId, null, 1, 0, "ASC");
            rankCurDate = new BRank().getRankDate(nktId, null, 1, 0, "DESC");            
            int countNumRanked = new BDataRank().getNumRanked(nktId);
            
            // System.out.println("countNumRanked " + countNumRanked);
            
            if (!"".equals(rankInitDate) && rankInitDate!=null){
                rankInitDate = rankInitDate.substring(0, rankInitDate.length()-1);
                fdataHdr.setRankInitDate(rankInitDate); 
            } else {
                fdataHdr.setRankInitDate(Formater.date2str(new Date())); 
            }
            
            if (!"".equals(rankCurDate)){
                rankCurDate = rankCurDate.substring(0, rankCurDate.length()-1);
            }
                        
            if (yearReport==0) {
                String strYearReport = (String)request.getParameter("yearReport");
                yearReport =  (strYearReport!=null)? Integer.parseInt(strYearReport):seed.getYear(seed.getCurrentSqlDate());  
            }
            
            if (type==Constant.KPI_DATA_HOURS) {
                fdataHdr.setObjId(objId);
                fdataHdr.setIndId(indId);
                refId = new BDataHdr().getRefId(fdataHdr);  
            }        
            
            FDataDtl dataDtl = new FDataDtl();
            FPerson dataPer = new FPerson();
            FDataNkt dataNkt = new FDataNkt();
            
            // ComboBox Event
            fEvent.setHdrId(hdrId);
            fEvent.setLocationId(locationId);
            fEvent.setCreateFrom(Utilities.createFrom(yearReport));
            fEvent.setCreateTo(Utilities.createTo(yearReport));
            FBeans events = new BEvent().getEventByObjInd(fEvent);
            request.setAttribute("events", events);
            
            if (!errors.isEmpty()) {
                // Hdr            
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);
                fdataHdr.setYearReport(yearReport);
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                
                // SET HDR
                fdataHdr.setType(type);            
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                fdataHdr.setTypePeriod(period);
                
                // SET DTL
                dataDtl.setDataId(fdataHdr.getId());
                dataDtl.setIndId(indId);
                dataDtl.setObjId(objId);
                dataDtl.setLocationId(locationId);
                
                dataPer.setDataId(fdataHdr.getId());
                total = new BDataHdr().count(fdataHdr);
                
                if (total>0) {
                    if (fdataHdr.getType()==Constant.KPI_DATA_VALUE)                    
                        beans = new BDataDtl().getAll(dataDtl, session);
                    else if (fdataHdr.getType()==Constant.KPI_DATA_DIS)  
                        beans = new BDataNkt().getAll(dataNkt, session);
                    else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON 
                          || fdataHdr.getType()==Constant.KPI_DATA_HOURS)  
                        beans = new BPerson().getAll(dataPer, session);
                } else {
                    if (fdataHdr.getType()==Constant.KPI_DATA_HOURS){
                        dataPer.setDataId(refId);
                        beans = new BPerson().getAll(dataPer, session);
                    } else {
                        beans = new FBeans();
                    }
                }
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("BTreeObjects", optObjects);
                request.setAttribute("kpi", fdataHdr);
            } else if (anchor.equals(_EDIT)) {            
                if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {        // Input Values
                    if (indId>0 && dtlId>0) {                                    
                        int actual = fdataHdr.getActual();                    
                        
                        hdrId = fdataHdr.getId();
                        int typePeriod = fdataHdr.getTypePeriod();
                        int month = fdataHdr.getMonth();
                        int quarter = fdataHdr.getQuarter();
                        int year = fdataHdr.getYear();
                        
                        int tw = fdataHdr.getTw();
                        int ttp = fdataHdr.getTtp();
                        int qhu = fdataHdr.getQhu();
                        int pxa = fdataHdr.getPxa();
                        
                        int targetTw = fdataHdr.getTw();
                        int targetTtp = fdataHdr.getTtp();
                        int targetQhu = fdataHdr.getQhu();
                        int targetPxa = fdataHdr.getPxa();
                        
                        int targetM = fdataHdr.getTargetM();
                        int targetQ = fdataHdr.getTargetQ();
                        int targetY = fdataHdr.getTargetY();
                        
                        int accM = fdataHdr.getAccM();
                        int accQ = fdataHdr.getAccQ();
                        int accY = fdataHdr.getAccY();
                        
                        String note = fdataHdr.getNote();
                        
                        // Hdr
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setYearReport(yearReport);
                        fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setCreateDate(createDate);
                        
                        // Dtl
                        fdataDtl.setId(dtlId);
                        fdataDtl.setCreateDate(createDate);
                        fdataDtl.setDataId(fdataHdr.getId());
                        fdataDtl.setMonth(fdataHdr.getMonth());
                        fdataDtl.setYear(fdataHdr.getYear());
                        fdataDtl.setLocationId(locationId);
                        fdataDtl.setLocation(locationName);
                        fdataDtl.setPeriod(typePeriod);
                        fdataDtl.setMonth(typePeriod==0?month:0);
                        fdataDtl.setQuarter(typePeriod==1?quarter:0);
                        fdataDtl.setYear(year);
                        fdataDtl.setActual(actual);
                        fdataDtl.setTarget(fdataHdr.getTarget());
                        
                        fdataDtl.setTw(tw);
                        fdataDtl.setTtp(ttp);
                        fdataDtl.setQhu(qhu);
                        fdataDtl.setPxa(pxa);
                        
                        fdataDtl.setTargetTw(targetTw);
                        fdataDtl.setTargetTtp(targetTtp);
                        fdataDtl.setTargetQhu(targetQhu);
                        fdataDtl.setTargetPxa(targetPxa);
                        
                        fdataDtl.setTargetM(targetM);
                        fdataDtl.setTargetQ(targetQ);
                        fdataDtl.setTargetY(targetY);
                        
                        fdataDtl.setAccM(accM);
                        fdataDtl.setAccQ(accQ);
                        fdataDtl.setAccY(accY);
                        
                        fdataDtl.setNote(note);                    
                        fdataDtl.setUserId((int)me.getId());
                      
                        if (new BDataDtl().update(fdataDtl)) {
                            bdataHdr.update(fdataHdr);
                            errors.add("alert", new ActionError("alert.update.successfull"));
                        } else {
                            errors.add("alert", new ActionError("alert.update.unsuccessfull"));
                        }
                        
                        fdataDtl.setDataId(hdrId);
                        fdataDtl.setIndId(indId);
                        fdataDtl.setObjId(objId);
                        fdataDtl.setLocationId(locationId);
                        fdataDtl.setPeriod(period);                    
                        getListValue(fdataDtl, request, session);
                        request.setAttribute("kpi", fdataHdr);
                    } else {
                        request.setAttribute("kpi", new FDataHdr());
                    } 
                } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {
                    dtlId = (disId==0)? nktId : disId;
                    fdataHdr.setDtlId(dtlId);
                    populateFormToForm(fdataHdr, fDis);
                    if (bDisability.update(fDis)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("alert", new ActionError("alert.update.unsuccessfull"));
                    }                
                    
                    fdataHdr.setLocationId(locationId);
                    fdataHdr.setYearReport(yearReport);
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);                
                    fdataHdr.setStatusId(fDis.getTrangthai());
                    
                    FObject fObject = new FObject();
                    fObject.setId(objId);
                    fObject = new BObject().getRecordByID(fObject);
                    
                    fdataHdr.setObjCode(fObject.getCode());
                    fdataHdr.setObjName(fObject.getName());
                    fdataHdr.setObjDesc(fObject.getDescription());
                    
                    fdataHdr.setIndId(indId);
                    fdataNkt.setDataId(hdrId);
                    
                    fDis.setTinhId(locationId);
                    beans = new BDisability().getDisKpi(fDis);
                    FBeans acBeans = new BDisability().getDisKpiAc(fDis);
                    
                    FBeans districts = map_district.get(String.valueOf(locationId));
                    FBeans communes = map_commune.get(String.valueOf(districtId));
                    request.setAttribute("districts", districts);
                    request.setAttribute("communes", communes);
                    
                    request.setAttribute("listDataDtlAc", acBeans);
                    request.setAttribute("listDataDtl", beans);            
                    request.setAttribute("total", beans.size());            
                    request.setAttribute("kpi", fdataHdr);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON){
                    //  Person
                    fPerson.setId(fdataHdr.getDtlId());
                    fPerson = bPerson.getDetailByID(fPerson);
                    fPerson.setDataId(fdataHdr.getId());
                    fPerson.setName(fdataHdr.getPerName());
                    fPerson.setSex(fdataHdr.getPerSex());
                    fPerson.setTitle(fdataHdr.getPerTitle());
                    fPerson.setAgency(fdataHdr.getPerAgency());
                    fPerson.setAddress(fdataHdr.getPerAddress());
                    fPerson.setContact(fdataHdr.getPerContact());
                    
                    // Map Person-Data
                    fdataPer.setCreateDate(fdataHdr.getCreateDate());
                    fdataPer.setDataId(fdataHdr.getId());
                    fdataPer.setPerId(fdataHdr.getDtlId());
                    fdataPer.setEventId(fdataHdr.getEventId());
                    fdataPer.setHours(0);
                    fdataPer.setResult(0);
                    
                    if (bPerson.update(fPerson, false) && bdataPer.update(fdataPer)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("alert", new ActionError("alert.update.unsuccessfull"));
                    }
                    
                    // Person
                    fPerson.setState(state);
                    fPerson.setDataId(hdrId); 
                    fPerson.setLocationId(locationId);
                    fPerson.setEventId(eventId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);
                    fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                    fPerson.setCreateTo(Utilities.createTo(yearReport));
                    getListDtl(fPerson, request, session);
                  
                    fdataHdr.setLocationId(locationId);
                    fdataHdr.setObjId(objId);
                    fdataHdr.setIndId(indId);
                    fdataHdr.setYearReport(yearReport);
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    
                    // Event
                    fEvent.setEventId(eventId);
                    fEvent.setLocationId(locationId);
                    fEvent = bEvent.getRecordByID(fEvent);
                    
                    fdataHdr.setEventId(eventId);
                    fdataHdr.setActivity(fEvent.getActivity());
                    fdataHdr.setLocation(fEvent.getLocation());
                    fdataHdr.setStartDate(fEvent.getStartDate());
                    fdataHdr.setEndDate(fEvent.getEndDate());
                    
                    request.setAttribute("kpi", fdataHdr);
                } else {
                    // Map Person-Data
                    boolean hasDtl = false;
                    hasDtl = new BDataHdr().isExistsDtl(fdataHdr);
                    
                    if (hdrId==0) {
                        fdataHdr.setCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentSqlDate()));
                        fdataHdr.setUserId(userId);
                        hdrId = bdataHdr.insert(fdataHdr);
                    }
                    
                    fdataPer.setDataId(hdrId);
                    fdataPer.setPerId(fdataHdr.getDtlId());
                    fdataPer.setHours(fdataHdr.getPerHours());
                    fdataPer.setResult(fdataHdr.getVoteResult());
                    fdataPer.setEventId(fdataHdr.getEventId());
                    
                    if (bdataPer.update(fdataPer)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("alert", new ActionError("alert.update.unsuccessfull"));
                    }
                    
                    fdataHdr.setYearReport(yearReport);
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);                
                    fdataHdr.setLocationId(locationId);
                    
                    // Person
                    fPerson.setDataId(hdrId); 
                    fPerson.setLocationId(locationId);
                    fPerson.setEventId(eventId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);
                    fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                    fPerson.setCreateTo(Utilities.createTo(yearReport));
                    getListDtl(fPerson, request, session);
                    request.setAttribute("kpi", fdataHdr);
                }
                request.setAttribute("menuObjectInput", menuObject);
                target = _SUCCESS;
            } else if (anchor.equals("_EXPORT_REPORT")) {
                String fileName = IKeyDisability.REPORT_FILE_KPI_CHART; 
                request.setAttribute("map_quanhe", map_quanhe);
                String report = new DReportKpiChart().exportExcel(fdataHdr, map_quanhe, map_hotro, seed, fileName);
                fdataHdr.download(report, fileName, null);
                fdataHdr.deleteFile(report);
                target = null;
            } else if (anchor.equals(_CREATE)) {
                int refIndId = 0, refHdrId = 0;
                
                // Insert Hdr
                if (fdataHdr.getType()==Constant.KPI_DATA_PERSON 
                    || fdataHdr.getType()==Constant.KPI_DATA_HOURS
                    || fdataHdr.getType()==Constant.KPI_DATA_VALUE) {
    
                    if (hdrId == 0) {
                        fdataHdr.setCreateDate(createDate);
                        fdataHdr.setUserId(userId);
                        hdrId = bdataHdr.insert(fdataHdr);
                    }
                    
                    FIndicator refIndicator = new BIndicator().getIndicatorByParentId(indId);                
                    if (refIndicator!=null) {
                        refIndId = refIndicator.getId();
                    }
                    
                    if (refIndId>0) {
                        fdataHdr.setCreateDate(createDate);
                        fdataHdr.setUserId(userId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setEventIds(String.valueOf(eventId));
                        
                        refHdrId = bdataHdr.getRefId(fdataHdr);
                        if (refHdrId==0) {
                            fdataHdr.setCreateDate(createDate);
                            fdataHdr.setModifyDate(null);
                            fdataHdr.setUserId(userId);
                            fdataHdr.setObjId(objId);
                            fdataHdr.setIndId(refIndId);
                            fdataHdr.setEventIds(String.valueOf(eventId));
                            refHdrId = bdataHdr.insert(fdataHdr);  
                        }
                    }
                }
                
                if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {      // Input Values                 
                    // dtl
                    fdataDtl.setCreateDate(createDate);
                    fdataDtl.setUserId((int)me.getId());
                    fdataDtl.setDataId(hdrId);
                    fdataDtl.setIndId(indId);
                    fdataDtl.setObjId(objId);
                    fdataDtl.setLocationId(locationId);
                    fdataDtl.setLocation(locationName);
                    fdataDtl.setPeriod(fdataHdr.getTypePeriod());
                    fdataDtl.setMonth(fdataHdr.getTypePeriod()==1?0:fdataHdr.getMonth());     // Kieu Quy
                    fdataDtl.setQuarter(fdataHdr.getTypePeriod()==0?0:fdataHdr.getQuarter()); // Kieu Thang
                    fdataDtl.setYear(fdataHdr.getYear());
                    fdataDtl.setActual(fdataHdr.getActual());
                    fdataDtl.setTarget(fdataHdr.getTarget());
                    
                    fdataDtl.setTw(fdataHdr.getTw());                
                    fdataDtl.setTtp(fdataHdr.getTtp());
                    fdataDtl.setQhu(fdataHdr.getQhu());
                    fdataDtl.setPxa(fdataHdr.getPxa());
                    
                    fdataDtl.setTargetTw(fdataHdr.getTargetTw());                
                    fdataDtl.setTargetTtp(fdataHdr.getTargetTtp());
                    fdataDtl.setTargetQhu(fdataHdr.getTargetQhu());
                    fdataDtl.setTargetPxa(fdataHdr.getTargetPxa());
                    
                    fdataDtl.setTargetM(fdataHdr.getTargetM());
                    fdataDtl.setTargetQ(fdataHdr.getTargetQ());
                    fdataDtl.setTargetY(fdataHdr.getTargetY());
                    
                    fdataDtl.setAccM(fdataHdr.getAccM());
                    fdataDtl.setAccQ(fdataHdr.getAccQ());
                    fdataDtl.setAccY(fdataHdr.getAccY());                
                    fdataDtl.setNote(fdataHdr.getNote());
                    
                    if (bdataDtl.insert(fdataDtl)) {
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else {
                        errors.add("alert", new ActionError("errors.data.dtl.exists"));
                    }
                    
                    if (fdataHdr.getIndId() > 0) {              
                        fdataHdr.setIndId(indId);
                        fdataDtl.setDataId(hdrId);
                        fdataDtl.setLocationId(locationId);
                        
                        int typePeriod = fdataHdr.getTypePeriod();
                        int month = fdataHdr.getMonth();
                        int quarter = fdataHdr.getQuarter();
                        int year = fdataHdr.getYear();
                        
                        fdataHdr.setDtlId(0);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setYearReport(yearReport);
                        fdataHdr.setLocationId(locationId); 
                        fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        
                        fdataHdr.setTypePeriod(typePeriod);
                        fdataHdr.setMonth(month);
                        fdataHdr.setQuarter(quarter);
                        fdataHdr.setYear(year);
                        fdataHdr.setType(Constant.KPI_DATA_VALUE);                    
                        
                        fdataDtl.setLocationId(locationId);
                        fdataDtl.setObjId(objId);
                        fdataDtl.setIndId(indId);
                        fdataDtl.setDataId(hdrId);
                        fdataDtl.setPeriod(period);
                        
                        beans = new BDataDtl().getAll(fdataDtl, session);
                        request.setAttribute("listDataDtl", beans);
                        request.setAttribute("kpi", fdataHdr);
                    } else {
                        request.setAttribute("kpi", fdataHdr);
                    }            
                } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {     // Input Dis                
                    populateFormToForm(fdataHdr, fDis);
                    try { 
                        disId = bDisability.insertKpi(fDis);
                        if (disId==0) {
                            errors.add("alert", new ActionError("alert.catch.dis.input.unique"));
                        } else {
                            errors.add("alert", new ActionError("alert.insert.successfull"));
                        }
                    } catch (EException ex) {
                        errors.add("alert", new ActionError("alert.catch.dis.input.unique"));
                    }                
                    
                    fDis.setTinhId(fdataHdr.getLocationId());
                    beans = new BDisability().getDisKpi(fDis);
                    
                    FObject object = new FObject();
                    object.setId(objId);
                    object = new BObject().getRecordByID(object);
                    
                    fdataHdr.setDtlId(0);
                    fdataHdr.setIndId(indId);
                    fdataHdr.setObjId(objId);
                    fdataHdr.setYearReport(yearReport);
                    fdataHdr.setLocationId(locationId);               
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    fdataHdr.setObjCode(object.getCode());
                    fdataHdr.setObjName(object.getName());
                    fdataHdr.setObjDesc(object.getDescription());
                    
                    fTinh.setId(locationId);
                    fdataHdr.setDtlId(disId);
                    fdataHdr.setNktId(disId);
                    FBeans districts = map_district.get(String.valueOf(locationId));
                    FBeans communes = map_commune.get(String.valueOf(districtId));
                    request.setAttribute("districts", districts);
                    request.setAttribute("communes", communes);
                    
                    FBeans acBeans = new BDisability().getDisKpiAc(fDis);              
                    request.setAttribute("listDataDtlAc", acBeans);
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("kpi", fdataHdr);
                   
                } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON){      // Input Person                                
                    int hours = bdataPer.getHours(eventId);
                    
                    if (perId==0){   // Not select person
                        String codePerson = new BPerson().getNextCodePerson(fPerson);
                        fPerson.setCode(codePerson);
                        fPerson.setUserId((int)me.getId());
                        fPerson.setName(fdataHdr.getPerName());
                        fPerson.setSex(fdataHdr.getPerSex());
                        fPerson.setBirth(fdataHdr.getPerBirth());
                        fPerson.setAgency(fdataHdr.getPerAgency());
                        fPerson.setTitle(fdataHdr.getPerTitle());
                        fPerson.setHours(fdataHdr.getPerHours());
                        fPerson.setLocationId(fdataHdr.getLocationId());
                        fPerson.setVoteResult(fdataHdr.getVoteResult());
                        perId = bPerson.insert(fPerson);
                    }
        
                    // Insert Map data-per
                    fdataPer.setCreateDate(createDate);
                    fdataPer.setDataId(hdrId);
                    fdataPer.setPerId(perId);
                    fdataPer.setEventId(eventId);
                    fdataPer.setResult(0);
                    fdataPer.setHours(0);
                    
                    if (bdataPer.insert(fdataPer)==1) {
                        if (refHdrId>0) {
                            fdataPer.setCreateDate(createDate);
                            fdataPer.setDataId(refHdrId);
                            fdataPer.setPerId(perId);
                            fdataPer.setEventId(eventId);
                            fdataPer.setResult(0);
                            fdataPer.setHours(hours);
                            bdataPer.insert(fdataPer);
                        }
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else if (bdataPer.insert(fdataPer)==9) {
                        errors.add("alert", new ActionError("errors.input.person.has.selected"));
                    } else {
                        errors.add("alert", new ActionError("errors.common.insert"));
                    }
                    
                    if (fdataHdr.getIndId() > 0) {
                        fPerson.setState(0);
                        fPerson.setDataId(hdrId);
                        fPerson.setLocationId(locationId);
                        fPerson.setEventId(eventId);
                        
                        fdataHdr.setDtlId(0);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setYearReport(yearReport);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setType(type);
                        
                        // Process Event
                        fEvent.setEventId(eventId);
                        fEvent = bEvent.getRecordByID(fEvent);
                        
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setEventId(eventId);
                        fdataHdr.setActivity(fEvent.getActivity());
                        fdataHdr.setLocation(fEvent.getLocation());
                        fdataHdr.setCreateDate(fEvent.getEndDate());
                        fdataHdr.setStartDate(fEvent.getStartDate());
                        fdataHdr.setEndDate(fEvent.getEndDate());
                        
                        // Person
                        fPerson.setDataId(hdrId); 
                        fPerson.setLocationId(locationId);
                        fPerson.setEventId(eventId);
                        fPerson.setObjId(objId);
                        fPerson.setIndId(indId);
                        fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                        fPerson.setCreateTo(Utilities.createTo(yearReport));
                        getListDtl(fPerson, request, session);
                        request.setAttribute("kpi", fdataHdr);
                    } else {
                        request.setAttribute("kpi", fdataHdr);
                    }                   
                } else {
                    if (hdrId>0) {
                        bdataHdr.update(fdataHdr);
                    }
                    
                    // Insert Map data-per
                    fdataPer.setDataId(hdrId);
                    fdataPer.setPerId(perId);
                    fdataPer.setResult(fdataHdr.getVoteResult());
                    fdataPer.setHours(fdataHdr.getPerHours());
                                
                    if (bdataPer.insert(fdataPer)==1) {
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else if (bdataPer.insert(fdataPer)==9) {
                        errors.add("alert", new ActionError("errors.input.person.has.selected"));
                    } else {
                        errors.add("alert", new ActionError("errors.common.insert"));
                    }
                    
                    if (fdataHdr.getIndId() > 0) {
                        fPerson.setState(0);
                        fPerson.setDataId(fdataHdr.getId());
                        
                        fdataHdr.setId(hdrId);
                        fdataHdr.setDtlId(0);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setYearReport(yearReport);
                        fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        
                        // Person
                        refId = new BDataHdr().getRefId(fdataHdr);
                        fPerson.setDataId(hdrId); 
                        fPerson.setLocationId(locationId);
                        fPerson.setEventId(eventId);
                        fPerson.setObjId(objId);
                        fPerson.setIndId(indId);
                        fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                        fPerson.setCreateTo(Utilities.createTo(yearReport));
                        getListDtl(fPerson, request, session);
                        request.setAttribute("kpi", fdataHdr);
                    } else {
                        request.setAttribute("kpi", fdataHdr);
                    }
                }
                
                request.setAttribute("total", beans.size());
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            } else if (anchor.equals("_DELETE_DIS")                
                  || anchor.equals("_VALUE_DELETE")
                  || anchor.equals("_PERSON_DELETE")
                  || anchor.equals("_PERSON_SEL_DELETE")) {
                if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {               
                    fdataDtl.setId(dtlId);
                    fdataDtl.setPeriod(period);
                    if (new BDataDtl().delete(fdataDtl)) {
                        errors.add("alert", new ActionError("alert.delete.successfull"));
                    } else {
                        errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                    }  
                } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {                
                    if (new BDisability().delete((disId==0)?dtlId:disId)) {
                        errors.add("alert", new ActionError("alert.delete.successfull"));
                    } else {
                        errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                    }
                } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON
                            || fdataHdr.getType()==Constant.KPI_DATA_HOURS) {                
                    if ("_PERSON_DELETE".equals(anchor)) {
                        fdataPer.setPerId(dtlId);
                        fdataPer.setDataId(hdrId);                    
                        fdataPer.setEventId(eventId);
                        if (new BDataPer().delete(fdataPer)) {
                            errors.add("alert", new ActionError("alert.delete.successfull"));  
                        } else {
                            errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                        }
                    } else {
                        fPerson.setId(dtlId);
                        if (new BPerson().delete(fPerson)) {
                            errors.add("alert", new ActionError("alert.delete.successfull"));  
                        } else {
                            errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                        }
                    }
                }
              
                if (fdataHdr.getIndId() > 0) {              
                    fdataHdr.setIndId(fdataHdr.getIndId());
                    fdataDtl.setDataId(fdataHdr.getId());
                    fdataNkt.setDataId(fdataHdr.getId());
                    fPerson.setDataId(fdataHdr.getId());
                    
                    if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {
                        fdataDtl.setDataId(hdrId);
                        fdataDtl.setIndId(indId);
                        fdataDtl.setObjId(objId);
                        fdataDtl.setLocationId(locationId);
                        getListValue(fdataDtl, request, session);
                    } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON
                            || fdataHdr.getType()==Constant.KPI_DATA_HOURS) {
                        fPerson.setDataId(hdrId);
                        fPerson.setIndId(indId);
                        fPerson.setObjId(objId);
                        fPerson.setLocationId(locationId);
                        fPerson.setState(state);
                        fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                        fPerson.setCreateTo(Utilities.createTo(yearReport));
                        getListDtl(fPerson, request, session);
                        
                        // Event
                        fEvent.setEventId(eventId);
                        fEvent.setLocationId(locationId);
                        fEvent = bEvent.getRecordByID(fEvent);
                    }
                    fdataHdr.setDtlId(0);  
                    fdataHdr.setYearReport(yearReport); 
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    fdataHdr.setTypePeriod(1);
                    fdataHdr.setCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                    fdataHdr.setLocationId(locationId);
                    fdataHdr.setLocationName(locationName);                
                    fdataHdr.setState(state);
                    fdataHdr.setType(type);
                    fdataHdr.setEventId(eventId);
                    fdataHdr.setActivity(fEvent.getActivity());
                    fdataHdr.setLocation(fEvent.getLocation());
                    fdataHdr.setStartDate(fEvent.getStartDate());
                    fdataHdr.setEndDate(fEvent.getEndDate());
                    
                    request.setAttribute("kpi", fdataHdr);
                } else {
                    // get Data
                    fTinh.setId(locationId);
                    fDis.setTinhId(locationId);
                    fDis.setPageIndex(fdataHdr.getPageIndex());
                    
                    beans = new BDisability().getDisKpi(fDis);
                    FBeans acBeans = new BDisability().getDisKpiAc(fDis);                  
                    request.setAttribute("listDataDtlAc", acBeans);
                    
                    fdataHdr.resetDis();
                    disCode = bTinh.getMaxCodeDis(fTinh);
                    fdataHdr.setDisCode(disCode);
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("kpi", fdataHdr);
                }
                
                request.setAttribute("total", beans.size());
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            } else if (anchor.equals(_VIEW) 
                      || anchor.equals("_VIEW_EVENT") 
                      || anchor.equals("_VIEW_PERSON")
                      || anchor.equals("_VIEW_VALUE")) {
                if (fdataDtl.getPageIndex() <= 0)
                    fdataDtl.setPageIndex(1);
                
                int pageIndex = fdataHdr.getPageIndex();
                
                if (fdataHdr.getIndId() > 0) {              
                    fdataHdr.setIndId(indId);
                    fdataHdr.setObjId(objId);
                    fdataHdr.setEventId(eventId);
                    
                    fdataHdr.setYearReport(yearReport); 
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    fdataHdr.setEventSel((fdataHdr.getEventIds()!=null)?fdataHdr.getEventIds():"");
                    
    
                    if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {
                        fdataDtl.setDataId(locationId==0?0:hdrId);
                        fdataDtl.setPeriod(period);
                        fdataDtl.setObjId(objId);
                        fdataDtl.setIndId(indId);
                        fdataDtl.setLocationId(locationId);
                        fdataDtl.setPageIndex(pageIndex);
                        beans = new BDataDtl().getAll(fdataDtl, session);
                        request.setAttribute("listDataDtl", beans);
                    } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {
                        fDis.setPageIndex(pageIndex);
                        fDis.setTinhId(locationId);
                        beans = new BDisability().getDisKpi(fDis);
                        request.setAttribute("listDataDtl", beans);
                    } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON 
                               ||fdataHdr.getType()==Constant.KPI_DATA_HOURS) {
                        if (anchor.equals("_VIEW_EVENT")) { 
                            fEvent.setPageIndex(pageIndex);
                            fEvent.setLocationId(locationId);
                            getListEvent(fEvent, request, session);
                        } else {
                            fPerson.setState(state);
                            fPerson.setDataId((locationId==0)?0:hdrId);
                            fPerson.setPageIndex(pageIndex);
                            fPerson.setLocationId(locationId);
                            fPerson.setEventId(eventId);
                            fPerson.setObjId(objId);
                            fPerson.setIndId(indId);                        
                            fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                            fPerson.setCreateTo(Utilities.createTo(yearReport));
                            getListDtl(fPerson, request, session);
                        }
                    }
                    
                    fdataHdr.setEventId(eventId);
                    fdataHdr.setState(state);
                    request.setAttribute("kpi", fdataHdr);
                } else {
                    fDis.setPageIndex(pageIndex);
                    fDis.setTinhId(locationId);
                    beans = new BDisability().getDisKpi(fDis);
                    fdataHdr.resetDis();              
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("kpi", fdataHdr);
                }
                request.setAttribute("mapBaseline", map_combobox);
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            } else if (anchor.equals("_DETAIL") 
                    || anchor.equals("_DELTAIL_DTL") 
                    || anchor.equals("_DETAIL_LIST") 
                    || anchor.equals("_PERSON_VIEW_DETAIL")
                    || anchor.equals("_PERSON_AUTO_COMPLETE")
                    || anchor.equals("_VALUE_VIEW_DETAIL")
                    || anchor.equals("_PERSON_SEL_DETAIL")
                    || anchor.equals("_DIS_VIEW_DETAIL")) {            
                FBeans acBeans = new FBeans();
                if (dtlId > 0 || nktId > 0 || perId >0) {
                    inputType = fdataHdr.getInputType();
                    fdataHdr.setObjId(objId);
                    fdataHdr.setIndId(indId);
                    fdataHdr.setInputType(inputType);
                                       
                    if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {    // Values                    
                        fdataDtl.setId(dtlId);
                        fdataDtl.setObjId(objId);
                        fdataDtl.setIndId(indId);
                        fdataDtl = new BDataDtl().getRecordByID(fdataDtl);  
                        
                        // Hdr
                        hdrId = fdataHdr.getId();
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setDtlId(dtlId);
                        fdataHdr.setLocationId(fdataDtl.getLocationId());
                        fdataHdr.setLocationName(fdataDtl.getLocation());
                        fdataHdr.setActual(fdataDtl.getActual());
                        fdataHdr.setTarget(fdataDtl.getTarget());
                        
                        fdataHdr.setQuarter(fdataDtl.getQuarter());
                        fdataHdr.setYear(fdataDtl.getYear());
                        
                        fdataHdr.setTw(fdataDtl.getTw());
                        fdataHdr.setTtp(fdataDtl.getTtp());
                        fdataHdr.setQhu(fdataDtl.getQhu());
                        fdataHdr.setPxa(fdataDtl.getPxa());
                        
                        fdataHdr.setTargetTw(fdataDtl.getTargetTw());
                        fdataHdr.setTargetTtp(fdataDtl.getTargetTtp());
                        fdataHdr.setTargetQhu(fdataDtl.getTargetQhu());
                        fdataHdr.setTargetPxa(fdataDtl.getTargetPxa());
                        
                        fdataHdr.setTargetM(fdataDtl.getTargetM());
                        fdataHdr.setTargetQ(fdataDtl.getTargetQ());
                        fdataHdr.setTargetY(fdataDtl.getTargetY());
                        
                        fdataHdr.setAccM(fdataDtl.getAccM());
                        fdataHdr.setAccQ(fdataDtl.getAccQ());
                        fdataHdr.setAccY(fdataDtl.getAccY());
                        
                        fdataHdr.setNote(fdataDtl.getNote());
                        fdataHdr.setCreateDate(fdataDtl.getCreateDate());
                        fdataHdr.setModifyDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                        
                        indTemp.setId(indId);
                        FIndicator ind = new BIndicator().getRecordByID(indTemp);                    
                        fdataHdr.setCode(ind.getCode());
                        fdataHdr.setLvl(ind.getLvl());
                        
                        // Dtl
                        fdataDtl.setDataId(hdrId);                    
                        fdataDtl.setPageIndex(fdataHdr.getPageIndex());
                        getListValue(fdataDtl, request, session);
                        request.setAttribute("kpi", fdataHdr);
                    } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) { // Disabilitypeople
                        nktId = dtlId; 
                        fDis.setId(nktId);
                        fDis = new BDisability().getRecordByID(fDis);
                        
                        FTinh beanT = new FTinh();
                        beanT.setId(fDis.getTinhId());
                        beanT = new BTinh().getRecordByID(beanT);
                        locationName = beanT.getName();
                        
                        if (anchor.equals("_DETAIL_LIST")) {
                            fdataHdr.setMode("DETAIL_LIST");
                        } else {
                            fdataHdr.setMode("DETAIL");
                        }
                      
                        // Hdr
                        hdrId = fdataHdr.getId();
                        //fdataHdr.setDtlId(dtlId);
                        fdataHdr.setIndId(indId); 
                        fdataHdr.setObjId(objId);
                        fdataHdr.setCreateDate(fDis.getDateLastUpdate());
                        fdataHdr.setLocationId(fDis.getTinhId());
                        fdataHdr.setDistrictId(fDis.getDistrictId());
                        fdataHdr.setCommuneId(fDis.getCommuneId());
                        fdataHdr.setLocationName(locationName);
                        
                        FBeans districts = map_district.get(String.valueOf(fDis.getTinhId()));
                        request.setAttribute("districts", districts);
                        FBeans communes = map_commune.get(String.valueOf(fDis.getDistrictId()));
                        request.setAttribute("communes", communes);
                        
                        // NKT
                        fdataHdr.setDtlId(dtlId);
                        fdataHdr.setNktId(nktId);
                        fdataHdr.setDisCode(fDis.getMa());
                        fdataHdr.setDisCodeNkt(fDis.getMa_nkt());
                        fdataHdr.setDisName(fDis.getNkt());
                        fdataHdr.setDisBirth(fDis.getNgaySinh());
                        fdataHdr.setDisPassport(fDis.getCmnd());
                        fdataHdr.setDisPhone(fDis.getPhoneNumber());
                        fdataHdr.setDisSex(fDis.getSex());                    
                        fdataHdr.setDisNation(String.valueOf(fDis.getDantocId()));
                        fdataHdr.setDisCarrer(fDis.getNgheNghiepHT());
                        fdataHdr.setDisDioxin(fDis.getChatDocDaCam());
                        fdataHdr.setDisAddress(fDis.getSoNha());
                        fdataHdr.setStatusId(fDis.getTrangthai());
                        fdataHdr.setDuAnId(fDis.getDuAnId());
                                        
                        // NCS
                        fdataHdr.setNcsName(fDis.getTenChamSoc());
                        fdataHdr.setNcsBirth(fDis.getNamSinhChamSoc());
                        fdataHdr.setNcsRelation(String.valueOf(fDis.getQuanHeChamSoc()));
                        fdataHdr.setNcsPhone(fDis.getSdtLienLac());
                        fdataHdr.setNcsSex(fDis.getGioiTinhChamSoc());
                        
                        // Object
                        FObject object = new FObject();
                        object.setId(fdataHdr.getObjId());
                        object = new BObject().getRecordByID(object);
                        fdataHdr.setObjCode(object.getCode());
                        fdataHdr.setObjName(object.getName());
                        fdataHdr.setObjDesc(object.getDescription());
                        
                        // Get Data
                        fDis.setTinhId(locationId);
                        fDis.setPageIndex(fdataHdr.getPageIndex());                    
                        beans = new BDisability().getDisKpi(fDis);
                        acBeans = new BDisability().getDisKpiAc(fDis);                    
                        
                        request.setAttribute("nktId", String.valueOf(nktId));
                        request.setAttribute("kpi", fdataHdr);
                        request.setAttribute("listDataDtlAc", acBeans);
                        request.setAttribute("listDataDtl", beans);
                        request.setAttribute("total", beans.size());
                    } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON
                              || fdataHdr.getType()==Constant.KPI_DATA_HOURS){  // Person                    
                        
                        fPerson.setState(state);
                        fPerson.setId(dtlId>0?dtlId:perId);
                        fPerson.setObjId(objId);
                        fPerson.setIndId(indId);
                        fPerson.setDataId(hdrId);
                        fPerson.setLocationId(locationId);
                        fPerson.setEventId(eventId);
                        
                        if (!"_PERSON_VIEW_DETAIL".equals(anchor)) {
                            if ("_PERSON_AUTO_COMPLETE".equals(anchor)) {
                                fPerson.setState(Constant.KPI_STATE_AUTO);
                            }
                            fPerson = new BPerson().getDetailByID(fPerson);
                        } else {
                            fPerson = new BPerson().getDetailByPerId(fPerson);
                        }
                        
                        if ("_PERSON_VIEW_DETAIL".equals(anchor)) {
                            fPerson.setEventId("_PERSON_VIEW_DETAIL".equals(anchor)? eventId:fPerson.getEventId());
                        } else {
                            fPerson.setEventId(eventId);
                        }
                        
                        perId = (dtlId>0) ? dtlId:perId;
                        if (anchor.equals("_PERSON_SEL_DETAIL") || anchor.equals("_PERSON_AUTO_COMPLETE")) {
                            perSel = perId;
                        }
                        
                        eventId = fPerson.getEventId();  
                        fEvent.setEventId(eventId);
                        fEvent.setLocationId(locationId>0?locationId:fPerson.getLocationId());
                        fEvent = bEvent.getRecordByID(fEvent);
                        
                        // ComboBox Event
                        fEvent.setHdrId(hdrId);
                        fEvent.setLocationId(locationId);
                        fEvent.setCreateFrom(Utilities.createFrom(yearReport));
                        fEvent.setCreateTo(Utilities.createTo(yearReport));
                        events = new BEvent().getEventByObjInd(fEvent);
                        
                        // Hdr
                        fdataHdr.setInputType(inputType);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setEventId(eventId);
                        fdataHdr.setCreateDate("_PERSON_VIEW_DETAIL".equals(anchor)? fPerson.getCreateDate():fEvent.getEndDate());
                        fdataHdr.setModifyDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                        fdataHdr.setDtlId(dtlId);
                        fdataHdr.setPerId(perId);
                        fdataHdr.setPerSel(perSel);
                        fdataHdr.setLocationId(fPerson.getLocationId());
                        fdataHdr.setLocationName(fPerson.getLocationName());
                        fdataHdr.setLocation(fEvent.getLocation());
                        fdataHdr.setActivity(fEvent.getActivity());
                        fdataHdr.setStartDate(fEvent.getStartDate());
                        fdataHdr.setEndDate(fEvent.getEndDate());
                        
                        // Person
                        fdataHdr.setPerCode(fPerson.getCode());
                        fdataHdr.setPerName(fPerson.getName());
                        fdataHdr.setPerSex(fPerson.getSex());
                        fdataHdr.setPerAddress(fPerson.getAddress());
                        fdataHdr.setPerContact(fPerson.getContact());
                        fdataHdr.setPerBirth(fPerson.getBirth());
                        fdataHdr.setPerAgency(fPerson.getAgency());
                        fdataHdr.setPerTitle(fPerson.getTitle());
                        fdataHdr.setPerHours(fPerson.getHours());
                        fdataHdr.setVoteResult(fPerson.getVoteResult());                    
                        fdataHdr.setState(state);
                                            
                        // Dtl                    
                        fPerson.setDataId(hdrId>0?hdrId:fPerson.getDataId());
                        fPerson.setEventId(eventId>0?eventId:fPerson.getEventId());
                        fPerson.setLocationId(locationId);
                        fPerson.setObjId(objId);
                        fPerson.setIndId(indId);
                        fPerson.setPageIndex(fdataHdr.getPageIndex());
                        fPerson.setState(state);
                        fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                        fPerson.setCreateTo(Utilities.createTo(yearReport));
                        getListDtl(fPerson, request, session);
                        request.setAttribute("events", events);
                        request.setAttribute("kpi", fdataHdr);
                    } 
                }
               
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            } else if (anchor.equals("_SELECT")) {
                
                perSel = fdataHdr.getPerSel();
                int disSel = fdataHdr.getDisSel();
                objId = fdataHdr.getObjId();
                indId = fdataHdr.getIndId();
                
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);
                fdataHdr.setDtlId(0);
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                fdataHdr.setEventSel(fdataHdr.getEventIds());
                fdataHdr.setTypeSel(typeSel);
                fdataHdr.setPerSel(perSel);
                fdataHdr.setDisSel(disSel);
                fdataHdr.setState(Constant.KPI_STATE_SELECT);
                
                // set Location Name
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                
                if (typeSel==Constant.KPI_SELECT_EVENT) {       // sel Event
                    fEvent.setLocationId(locationId);
                    getListEvent(fEvent, request, session);
                    
                    String eventCode = new BEvent().getNextCodeEvent(fEvent);
                    fdataHdr.setEventSel((fdataHdr.getEventIds()!=null)?fdataHdr.getEventIds():"");
                    fdataHdr.setEventCode(eventCode);
                    fdataHdr.setEventCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));                
                } else if (typeSel==Constant.KPI_SELECT_PERSON) {  // sel Person                
                    fPerson.setState(Constant.KPI_STATE_SELECT);
                    fPerson.setDataId(hdrId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);                    
                    fPerson.setLocationId(locationId);                
                    fPerson.setEventId(eventId);
                    fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                    fPerson.setCreateTo(Utilities.createTo(yearReport));
                    String perCode = bPerson.getNextCodePerson(fdataHdr);                
                    fdataHdr.setPerCode(perCode);                
                    getListDtl(fPerson, request, session);
                } 
                
                // Object
                FObject object = new FObject();
                object.setId(objId);
                object = new BObject().getRecordByID(object);
                
                // Indicator
                if (fdataHdr.getCode()==null) {
                    FIndicator indicator = new FIndicator();
                    indicator.setId(indId);
                    indicator = new BIndicator().getRecordByID(indicator);
                    
                    fdataHdr.setCode(indicator.getCode());
                    fdataHdr.setName(indicator.getName());
                }
                
                fdataHdr.setObjCode(object.getCode());
                fdataHdr.setObjName(object.getName());
                fdataHdr.setObjDesc(object.getDescription());
                
                String strTypeSel = String.valueOf(typeSel);
                request.setAttribute("typeSel", strTypeSel);
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_SELECT_EVENT")||anchor.equals("_CANCEL_EVENT")) {
                fEvent = new BEvent().getRecordByID(fEvent);
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                hdrId = fdataHdr.getId();
                
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);
                fdataHdr.setUserId(userId);
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                fdataHdr.setEventSel("");
                     
                // Person
                fPerson.setDataId(hdrId); 
                fPerson.setLocationId(locationId);
                fPerson.setEventId(eventId);
                fPerson.setObjId(objId);
                fPerson.setIndId(indId);
                fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                fPerson.setCreateTo(Utilities.createTo(yearReport));
                getListDtl(fPerson, request, session);
                
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
          } else if (anchor.equals("_CREATE_EVENT") || anchor.equals("_EDIT_EVENT") || anchor.equals("_CHOICE_EVENT")) {
                // Create Event
                fEvent.setLocationId(locationId);
                fEvent.setCode(fdataHdr.getEventCode());
                fEvent.setCreateDate(fdataHdr.getEventCreateDate());
                fEvent.setStartDate(fdataHdr.getEventStartDate());
                fEvent.setEndDate(fdataHdr.getEventEndDate());
                fEvent.setActivity(fdataHdr.getEventActivity());
                fEvent.setLocation(fdataHdr.getEventLocation());
                fEvent.setEventType(fdataHdr.getEventType());
                fEvent.setEventField(fdataHdr.getEventField());
                
                if (anchor.equals("_EDIT_EVENT") || anchor.equals("_CHOICE_EVENT")) {
                    dtlId = fdataHdr.getEventId();
                }
                
                eventId = 0;
                if ((dtlId>0) && anchor.equals("_EDIT_EVENT")) {
                    fEvent.setEventId(dtlId);
                    fEvent.setLocationId(locationId);                
                    if(bEvent.update(fEvent)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("alert", new ActionError("alert.update.error"));
                    }                
                } else {
                    BEventObjInd bEventObjInd = new BEventObjInd();
                    FEventObjInd fEventObjInd = new FEventObjInd();
                    
                    if (anchor.equals("_CREATE_EVENT")) {
                        eventId = new BEvent().insert(fEvent);
                    } else {
                        eventId = dtlId;
                    }
                    
                    if (eventId>0) {
                        fEventObjInd.setYear(fdataHdr.getYear());
                        fEventObjInd.setObjId(fdataHdr.getObjId());
                        fEventObjInd.setIndId(fdataHdr.getIndId());
                        fEventObjInd.setEventId(eventId);
                        bEventObjInd.insert(fEventObjInd);                
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else {
                        errors.add("alert", new ActionError("alert.insert.error"));
                    }            
                  
                    fEvent.setEventId(eventId);
                    fEvent = new BEvent().getRecordByID(fEvent);
                    
                    fdataHdr.setYearReport(yearReport); 
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    hdrId = fdataHdr.getId();
                    int refHdrId = 0, refIndId = 0;
                    
                    // Hours
                    FIndicator refIndicator = new BIndicator().getIndicatorByParentId(indId);                
                    if (refIndicator!=null) {
                        refIndId = refIndicator.getId();
                    }
                    
                    if (refIndId>0) {
                        fdataHdr.setCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentSqlDate()));
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setIndId(indId);
                        refHdrId = bdataHdr.getRefId(fdataHdr);                    
                    }
                    
                    BDataHdr bDataHdr = new BDataHdr();
                  
                    if (hdrId==0) {
                        fdataHdr.setUserId(userId);
                        fdataHdr.setIndId(indId);
                        fdataHdr.setObjId(objId);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setEventIds(String.valueOf(eventId));
                        fdataHdr.setCreateDate(fEvent.getEndDate());
                        hdrId = bDataHdr.insert(fdataHdr);
                        fdataHdr = bDataHdr.getRecordByIndicator(fdataHdr);                
                    } else {
                        fdataHdr.setLocationId(locationId);
                        fdataHdr = bDataHdr.getRecordByIndicator(fdataHdr);
                        String eventIds = fdataHdr.getEventIds();
                        eventIds = eventIds + "," + eventId;
                        
                        // PERSON
                        fdataHdr.setId(hdrId);
                        fdataHdr.setUserId(userId);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setEventIds(eventIds);
                        bDataHdr.update(fdataHdr);
                        
                        // HOURS
                        fdataHdr.setIndId(refIndId);
                        fdataHdr.setId(refHdrId);
                        fdataHdr.setUserId(userId);
                        fdataHdr.setLocationId(locationId);
                        fdataHdr.setLocationName(locationName);
                        fdataHdr.setEventIds(eventIds);
                        bDataHdr.update(fdataHdr);
                    }
                  
                    // Person
                    fPerson.setDataId(hdrId); 
                    fPerson.setLocationId(locationId);
                    fPerson.setEventId(eventId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);
                    getListDtl(fPerson, request, session);
                    
                    // ComboBox Event
                    fEvent.setHdrId(hdrId);
                    fEvent.setLocationId(locationId);
                    fEvent.setCreateFrom(Utilities.createFrom(yearReport));
                    fEvent.setCreateTo(Utilities.createTo(yearReport));
                    events = new BEvent().getEventByObjInd(fEvent);
                    request.setAttribute("events", events);
                    
                    fdataHdr.setIndId(indId);
                    fdataHdr.setId(hdrId);
                    fdataHdr.setLocationId(locationId);
                    fdataHdr.setLocationName(locationName);
                }
                
                fdataHdr.setLocationId(locationId);            
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                
                fdataHdr.setTypeSel(Constant.KPI_SELECT_EVENT);
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                fdataHdr.setEventSel((fdataHdr.getEventIds()!=null)?fdataHdr.getEventIds():"");
                
                getListEvent(fEvent, request, session);            
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                request.setAttribute("kpi", fdataHdr);
                target = anchor; 
            } else if (anchor.equals("_DETAIL_EVENT")) { 
                fEvent = new FEvent();                       
                fEvent.setEventId(dtlId);
                fEvent.setLocationId(locationId);
                fEvent = bEvent.getRecordByID(fEvent);
                getListEvent(fEvent, request, session);
                
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);
                fdataHdr.setDtlId(0);
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                
                fdataHdr.setDtlId(dtlId);
                fdataHdr.setEventId(dtlId);
                fdataHdr.setTypeSel(1);            
                fdataHdr.setEventSel((fdataHdr.getEventIds()!=null)?fdataHdr.getEventIds():"");
                fdataHdr.setEventCode(fEvent.getCode());
                fdataHdr.setEventCreateDate(fEvent.getCreateDate());
                fdataHdr.setEventStartDate(fEvent.getStartDate());
                fdataHdr.setEventEndDate(fEvent.getEndDate());
                fdataHdr.setEventActivity(fEvent.getActivity());
                fdataHdr.setEventLocation(fEvent.getLocation());
                
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_SELECT_PER")||anchor.equals("_CANCEL_PER")) {            
                fdataHdr.setIndId(indId);
                fdataHdr.setObjId(objId);  
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                
                if (anchor.equals("_SELECT_PER") || anchor.equals("_CANCEL_PER")) {
                    perSel = perId;
                    fPerson.setId(perId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);
                    fPerson.setLocationId(locationId);
                    fPerson.setEventId(eventId);
                    fPerson = new BPerson().getDetailByPerId(fPerson);
                    
                    fEvent.setEventId(eventId);
                    fEvent.setLocationId(locationId);
                    fEvent = bEvent.getRecordByID(fEvent);
                    getListEvent(fEvent, request, session);
                    
                    fdataHdr.setEventId(eventId);
                    fdataHdr.setCreateDate(fEvent.getEndDate());
                    fdataHdr.setEventCreateDate(fEvent.getCreateDate());
                    fdataHdr.setStartDate(fEvent.getStartDate());
                    fdataHdr.setEndDate(fEvent.getEndDate());
                    fdataHdr.setActivity(fEvent.getActivity());
                    fdataHdr.setLocation(fEvent.getLocation());
                    
                    fdataHdr.setPerId(perId);
                    fdataHdr.setPerName(fPerson.getName());
                    fdataHdr.setPerSex(fPerson.getSex());
                    fdataHdr.setPerCode(fPerson.getTitle());
                    fdataHdr.setPerTitle(fPerson.getTitle());
                    fdataHdr.setPerAgency(fPerson.getAgency());
                    fdataHdr.setPerSel(perSel);
                    fdataHdr.setLocationId(locationId);
                    fdataHdr.setLocationName(locationName);
                } else {
                    fdataHdr.setState(Constant.KPI_STATE_INPUT);
                }
                
                fPerson.setState(Constant.KPI_STATE_INPUT);
                fPerson.setLocationId(locationId);
                fPerson.setObjId(objId);
                fPerson.setIndId(indId);
                fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                fPerson.setCreateTo(Utilities.createTo(yearReport));
                getListDtl(fPerson, request, session);
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                request.setAttribute("kpi", fdataHdr);
                if ("_CANCEL_PER".equals(anchor)) {
                    fdataHdr.setState(Constant.KPI_STATE_SELECT);
                    fdataHdr.setTypeSel(Constant.KPI_SELECT_PERSON);
                    
                    fPerson.setState(Constant.KPI_STATE_SELECT);
                    fPerson.setDataId(hdrId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);                    
                    fPerson.setLocationId(locationId);                
                    fPerson.setEventId(eventId);
                    fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                    fPerson.setCreateTo(Utilities.createTo(yearReport));
                    String perCode = bPerson.getNextCodePerson(fdataHdr);                
                    fdataHdr.setPerCode(perCode);                
                    getListDtl(fPerson, request, session);
                    
                    target = "_SELECT";  
                } else {
                    target = anchor;  
                }
            } else if (anchor.equals("_CREATE_PER") || anchor.equals("_EDIT_PER")) {            
                perSel = fdataHdr.getPerSel();
                int disSel = fdataHdr.getDisSel();
                
                fPerson.setUserId((int)me.getId());
                fPerson.setLocationId(fdataHdr.getLocationId());            
                fPerson.setCode(fdataHdr.getPerCode());
                fPerson.setName(fdataHdr.getPerName());
                fPerson.setSex(fdataHdr.getPerSex());
                fPerson.setAddress(fdataHdr.getPerAddress());
                fPerson.setContact(fdataHdr.getPerContact());
                fPerson.setAgency(fdataHdr.getPerAgency());
                fPerson.setTitle(fdataHdr.getPerTitle());
                fPerson.setDataId(0);
                fPerson.setState(Constant.KPI_STATE_SELECT);
                if (fdataHdr.getDtlId()==0) {
                    if (bPerson.insert(fPerson)>0) {
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else {
                        errors.add("alert", new ActionError("errors.indicator.insert.unique.code"));
                    }
                } else {
                    fPerson.setId(fdataHdr.getDtlId());
                    if (bPerson.update(fPerson, true)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("alert", new ActionError("errors.indicator.insert.unique.code"));
                    }
                }
                
                String perCode = bPerson.getNextCodePerson(fdataHdr);
                fdataHdr.setYearReport(yearReport); 
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                fdataHdr.setPerCode(perCode);
                fdataHdr.setIndId(fdataHdr.getIndId());
                fdataHdr.setState(Constant.KPI_STATE_SELECT);
                
                fPerson.setDataId(fdataHdr.getId());            
                fdataHdr.setTypeSel(typeSel);
                fdataHdr.setPerSel(perSel);
                fdataHdr.setDisSel(disSel);
                
                fPerson.setState(Constant.KPI_STATE_SELECT);  // State select-person
                fPerson.setDataId(hdrId); 
                fPerson.setLocationId(locationId);
                fPerson.setEventId(eventId);
                fPerson.setObjId(objId);
                fPerson.setIndId(indId);
                getListDtl(fPerson, request, session);
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("kpi", fdataHdr);
                if (state==0){
                    target = "_PERSON_CHANGE_OPTION";
                } else {
                    target = "_SELECT";  
                }
                
            } else if (anchor.equals("_DELETE_PER") || anchor.equals("_DELETE_EVENT")) {
                fdataHdr.setObjId(objId);
                fdataHdr.setIndId(indId);
                fdataHdr.setDtlId(0);
                fdataHdr.setLocationId(locationId);
                fdataHdr = bdataHdr.getRecordByIndicator(fdataHdr);
                if (anchor.equals("_DELETE_PER")) {
                    fPerson.setDataId(0);
                    fPerson.setState(0);
                    fPerson.setId(fdataHdr.getDtlId());
                    if (bPerson.delete(fPerson)) {
                          fdataPer.setPerId(fdataHdr.getDtlId()); 
                          fdataPer.setDataId(fdataHdr.getId());
                          bdataPer.delete(fdataPer);
                          errors.add("alert", new ActionError("alert.delete.successfull"));
                    } else {
                          errors.add("alert", new ActionError("errors.indicator.insert.unique.code"));
                    }            
                    
                    String perCode = bPerson.getNextCodePerson(fdataHdr);
                    fdataHdr.setPerCode(perCode);                
                    getListDtl(fPerson, request, session);
                } else {
                    fEvent.setEventId(dtlId);
                    fEvent.setLocationId(locationId);
                    getListEvent(fEvent, request, session);
                    try {
                        if (bEvent.delete(fEvent)) {
                            errors.add("alert", new ActionError("alert.delete.successfull"));
                        } else {
                            errors.add("alert", new ActionError("alert.delete.fail"));
                        }  
                    } catch (Exception ex) {
                        errors.add("alert", new ActionError("alert.catch.event.delete.constraint"));
                    }
                    
                    String eventCode = bEvent.getNextCodeEvent(fEvent);
                    fdataHdr.setEventCode(eventCode);
                    fdataHdr.setTypeSel(1);
                    fdataHdr.setEventSel((fdataHdr.getEventIds()!=null)?fdataHdr.getEventIds():"");                
                    fdataHdr.setEventCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));                
                }
                
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("kpi", fdataHdr);
                target = "_SELECT";
            } else if (anchor.equals("_SELECT_IDTINH") 
                      || anchor.equals("_PERSON_CHANGE_OPTION")
                      || anchor.equals("_PERSON_CHANGE_SEL_OPTION")
                      || anchor.equals("_VALUE_CHANGE_OPTION")
                      || anchor.equals("_DIS_CHANGE_OPTION")
                      || anchor.equals("_CHANGE_OPT_DISTRICT")) {
                
                if (!"_PERSON_CHANGE_SEL_OPTION".equals(anchor)) {
                    fdataHdr.setObjId(objId);
                    fdataHdr.setIndId(indId);
                    fdataHdr.setLocationId(locationId);            
                    hdrId = new BDataHdr().getHdrId(fdataHdr);
                }
              
            
                fdataHdr.setNktId(0);
                fdataHdr.setDtlId(0);
                fdataHdr.setInputType(inputType);
                fdataHdr.setObjId(objId);
                fdataHdr.setId(hdrId);
                fdataHdr.setYearReport(yearReport);
                fdataHdr.setLocationId(locationId);
                fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                hdrId = fdataHdr.getId();
                
                indTemp.setId(indId);
                FIndicator ind = new BIndicator().getRecordByID(indTemp);                    
                fdataHdr.setCode(ind.getCode());
                fdataHdr.setLvl(ind.getLvl());
              
                // Value            
                fdataHdr.setObjId(objId);
                fdataHdr.setIndId(indId);
                fdataHdr.setLocationId(locationId);
                fdataHdr.setLocationName(locationName);
                fdataHdr.setInputType(inputType);
                fdataHdr.setTypePeriod(period);                
                
                if (locationId>0 && eventId>0) {            
                    fEvent.setEventId(eventId);
                    fEvent.setLocationId(locationId);
                    fEvent = bEvent.getRecordByID(fEvent);
                    
                    fdataHdr.setEventId(eventId);
                    fdataHdr.setActivity(fEvent.getActivity());
                    fdataHdr.setLocation(fEvent.getLocation());
                    fdataHdr.setCreateDate(fEvent.getEndDate());
                    fdataHdr.setStartDate(fEvent.getStartDate());
                    fdataHdr.setEndDate(fEvent.getEndDate());
                    perId = 0;
                } else {
                    eventId = 0;
                    fdataHdr.setEventId(0);
                    fdataHdr.setActivity("");
                    fdataHdr.setLocation("");
                    fdataHdr.setCreateDate(Formater.date2str(new Date()));
                    fdataHdr.setStartDate("");
                    fdataHdr.setEndDate("");
                }
                
                state = anchor.equals("_PERSON_CHANGE_OPTION") ? Constant.KPI_STATE_INPUT : Constant.KPI_STATE_SELECT;
                
                if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {                
                    fdataDtl.setDataId(locationId==0?0:fdataHdr.getId());
                    fdataDtl.setObjId(objId);
                    fdataDtl.setIndId(indId);
                    fdataDtl.setLocationId(locationId);
                    fdataDtl.setPeriod(period);
                    getListValue(fdataDtl, request, session);                
                } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {
                    if (locationId>0 && anchor.equals("_DIS_CHANGE_OPTION")) {
                        districtId = 0;
                        communeId = 0;
                        fTinh.setId(locationId);
                        disCode = bTinh.getMaxCodeDis(fTinh);
                        fdataHdr.resetDis();
                        fdataHdr.setDisCode(disCode);
                        fdataHdr.setCreateDate(createDate);
                    }
                    
                    if (anchor.equals("_CHANGE_OPT_DISTRICT")) {
                        fdataHdr.setCreateDate(createDate);
                    }
                    
                    fdataHdr.setDtlId(dtlId);
                    fdataHdr.setNktId(nktId);
                    FBeans districts = map_district.get(String.valueOf(locationId));
                    request.setAttribute("districts", districts);
                    if (districtId>0) {
                        FBeans communes = map_commune.get(String.valueOf(districtId));
                        request.setAttribute("communes", communes);
                    }
                    
                    fDis.setTinhId(locationId);
                    beans = new BDisability().getDisKpi(fDis);                
                    FBeans acBeans = new BDisability().getDisKpiAc(fDis);
                    
                    request.setAttribute("listDataDtlAc", acBeans);
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("total", total);            
                } else if ((fdataHdr.getType()==Constant.KPI_DATA_PERSON)
                           ||(fdataHdr.getType()==Constant.KPI_DATA_HOURS)) {
                    
                    perId = (perSel>0)? perSel:0;
                    if (perId>0) {
                        fPerson.setId(perId);
                        fPerson = bPerson.getPerson(fPerson);
                        fdataHdr.setPerAddress(fPerson.getAddress());
                        fdataHdr.setPerAgency(fPerson.getAgency());
                        fdataHdr.setPerName(fPerson.getName());
                        fdataHdr.setPerContact(fPerson.getContact());
                        fdataHdr.setPerSel(fPerson.getId());
                        fdataHdr.setPerId(perId);
                    }
                    
                    fPerson = bPerson.getDetailByPerId(fPerson);
                    fdataHdr.setPerId(perId);                
                    fPerson.setCreateFrom(Utilities.createFrom(yearReport));
                    fPerson.setCreateTo(Utilities.createTo(yearReport));
                    fPerson.setState(state);    
                    fPerson.setDataId(locationId==0?0:hdrId); 
                    fPerson.setLocationId(locationId);
                    fPerson.setEventId(eventId);
                    fPerson.setObjId(objId);
                    fPerson.setIndId(indId);
                    getListDtl(fPerson, request, session);                
                    fdataHdr.setState(state);
                }
                
                // ComboBox Event
                fEvent.setHdrId(hdrId);
                fEvent.setLocationId(locationId);
                fEvent.setCreateFrom(Utilities.createFrom(yearReport));
                fEvent.setCreateTo(Utilities.createTo(yearReport));
                events = new BEvent().getEventByObjInd(fEvent);
                request.setAttribute("events", events);
                request.setAttribute("kpi", fdataHdr);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            } else if (anchor.equals("_CHANGE_OPTION")) {
                dataDtl.setLocationId(locationId);
                dataDtl.setDataId(locationId==0?0:hdrId);
                dataDtl.setObjId(objId);
                dataDtl.setIndId(indId);            
                dataDtl.setPeriod(period);
                beans = new BDataDtl().getAll(dataDtl, session);
                request.setAttribute("listDataDtl", beans);
                request.setAttribute("kpi", fdataHdr);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                request.setAttribute("menuObjectInput", menuObject);
                target = anchor;
            }  else if (anchor.equals("_PHANLOAI")) {
                FPhanLoai phanloai = new FPhanLoai();            
                phanloai.setIdNkt(nktId);            
                fdataHdr.resetPhanLoai();
                
                FBeans beansPLoai = new FBeans();
                beansPLoai = new BPhanLoai().getAllByIdNkt(nktId);
                    
                request.setAttribute("NKT", fDis);
                request.setAttribute("kpi", fdataHdr);
                request.setAttribute("phanloai", phanloai);            
                request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));            
                request.setAttribute("BPhanLoaiTrailers",  beansPLoai);
                request.setAttribute("total", beansPLoai.size());
                
                target = anchor;
            } else if (anchor.equals("_INSERT_PHANLOAI") || anchor.equals("_UPDATE_PHANLOAI")) { 
                FPhanLoai phanloai = new FPhanLoai();
                BPhanLoai bo = new BPhanLoai();
                
                phanloai.setId(fdataHdr.getDisPhanLoaiId());
                phanloai.setUserId((int)me.getId());
                phanloai.setIdNkt(fdataHdr.getNktId());
                phanloai.setDangTatIds(fdataHdr.getDisDangTatIds());
                phanloai.setReson(fdataHdr.getDisChuanDoan());
                phanloai.setNguyenNhanId(fdataHdr.getDisNguyenNhanId());
                phanloai.setCapdoKT(fdataHdr.getDisMucDoId());
                phanloai.setThoiDiemKT(Integer.parseInt(fdataHdr.getDisThoiDiemMac()));
                phanloai.setThoiDiemTK(fdataHdr.getDisNgayTK());
                phanloai.setDiaDiemKham(fdataHdr.getDisDiaDiem());
                phanloai.setDateCreate(fdataHdr.getCreateDate());
                
                total = 0;
                if (fdataHdr.getNktId()>0) {
                    total = new BPhanLoai().getAllByIdNkt(fdataHdr.getNktId()).size();  
                }
                
                if (anchor.startsWith("_INSERT")) {
                    if (bo.insert(phanloai)) {
                        errors.add("alert", new ActionError("alert.insert.successfull"));
                    } else {
                        errors.add("errors",new ActionError("alert.insert.unSuccessfull"));
                    }
                } else {
                    phanloai.setId(fdataHdr.getPlDtlId());
                    if (bo.update(phanloai)) {
                        errors.add("alert", new ActionError("alert.update.successfull"));
                    } else {
                        errors.add("errors",new ActionError("alert.update.unSuccessfull"));
                    }  
                }
              
                fdataHdr.resetPhanLoai();
                phanloai.setDateCreate(phanloai.dateToString(phanloai.getCurrentDate()));
                request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));
                request.setAttribute("BPhanLoaiTrailers",  new BPhanLoai().getAllByIdNkt(fdataHdr.getNktId()));
                request.setAttribute("total", total);
                request.setAttribute("phanloai", phanloai);
                request.setAttribute("kpi", fdataHdr);
                target = "_PHANLOAI";
            } else if (anchor.equals("_DELETE_PHANLOAI")) {
                  FPhanLoai fPhanLoai = new FPhanLoai();
                  BPhanLoai bPhanLoai = new BPhanLoai();
                  if(bPhanLoai.delete(fdataHdr.getPlDtlId())){
                      errors.add("alert", new ActionError("alert.delete.successfull"));
                  } else {
                      errors.add("alert", new ActionError("alert.delete.unSuccessfull"));
                  }
                  
                  total = 0;
                  if (fdataHdr.getNktId()>0) {
                      total = new BPhanLoai().getAllByIdNkt(fdataHdr.getNktId()).size();  
                  }
              
                  fdataHdr.resetPhanLoai();
                  request.setAttribute("total", total);
                  request.setAttribute("kpi", fdataHdr);
                  request.setAttribute("phanloai", fPhanLoai);
                  request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));
                  request.setAttribute("BPhanLoaiTrailers",  new BPhanLoai().getAllByIdNkt(fdataHdr.getNktId()));
                  target = "_PHANLOAI";
            } else if (anchor.equals("_DETAIL_PHANLOAI")) {
                FPhanLoai phanloai = new FPhanLoai();            
                BPhanLoai bo = new BPhanLoai();
                phanloai = bo.getById(fdataHdr.getPlDtlId());            
                
                FBeans beansPLoai = new FBeans();
                beansPLoai = new BPhanLoai().getAllByIdNkt(nktId);            
                String ngayTaiKham = phanloai.getThoiDiemTK()==null?"":phanloai.getThoiDiemTK().substring(phanloai.getThoiDiemTK().indexOf("/")+1);
                
                fdataHdr.setDisDangTatIds(phanloai.getDangTatIds());
                fdataHdr.setCreateDate(phanloai.getDateCreate());
                fdataHdr.setDisThoiDiemMac(String.valueOf(phanloai.getThoiDiemKT()));
                fdataHdr.setDisNgayTK(ngayTaiKham);
                fdataHdr.setDisDiaDiem(phanloai.getDiaDiemKham());            
                fdataHdr.setDisChuanDoan(phanloai.getReson());
                fdataHdr.setDisNguyenNhanId(phanloai.getNguyenNhanId());
                fdataHdr.setDisMucDoId(phanloai.getCapdoKT());
                
                request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));
                request.setAttribute("BPhanLoaiTrailers",  new BPhanLoai().getAllByIdNkt(fdataHdr.getNktId()));
                request.setAttribute("phanloai", phanloai);
                request.setAttribute("total", beansPLoai.size());
                request.setAttribute("kpi", fdataHdr);
                target = "_PHANLOAI";
            }  else if (anchor.equals("_INSERT_RANK") || anchor.equals("_UPDATE_RANK") || anchor.equals("_DELETE_RANK")) { 
                  BDataRank bo = new BDataRank();
                  
                  FRank rank = new FRank();
                  fdataRank.setId(fdataHdr.getId());
                  fdataRank.setRankId(fdataHdr.getRankId());
                  fdataRank.setCreateDate(fdataHdr.getRankCreateDate());
                  fdataRank.setNktId(nktId);
                  fdataRank.setUserId(userId);
                  fdataRank.setId(dtlId);
                  fdataRank.setLocationId(locationId);
                  
                  FRank fRank = new BRank().getRankByDtlId(rankId, nktId, dtlId);
                  FRank initRank = new BRank().getRankByDtlId(rankId, nktId, dtlId, rankInitDate);
                  String[] req = request.getParameterValues("rankHas");
                  String hasRank = "0";
                  if (req != null && req.length > 1) {
                      for (int i = 0; i < req.length; i++) {
                          if (req[i].endsWith(String.valueOf(rankId))) {
                              hasRank = req[i];
                              break;
                          }
                      }
                  } else {
                      hasRank = fdataHdr.getRankHas();
                  }
                  
                  System.out.println("hasRank " + hasRank);
                  
                  boolean chkCombo = hasRank.startsWith("0") ? false:true;
                  boolean chkCurDtl =  fdataHdr.getCurRankHas().startsWith("0") ? false:true;
                  System.out.println("hasRank: " +hasRank +" chkCombo: " + chkCombo + " chkCurDtl: "+ chkCurDtl);
                  
                  int result = hasRank.startsWith("0")?-1:fdataHdr.getRankResult();                  
                  int hasRQ = hasRank.startsWith("0")?0:fdataHdr.getRankHasRQ();
                  int hasSP = hasRank.startsWith("0")?0:fdataHdr.getRankHasSP();                  
                  
                  fdataRank.setP0(result==0?1:0);
                  fdataRank.setP1(result==1?1:0);
                  fdataRank.setP2(result==2?1:0);
                  fdataRank.setP3(result==3?1:0);
                  fdataRank.setP4(result==4?1:0);                  
                  
                  fdataRank.setHasRK(hasRank.startsWith("1")?1:0);                  
                  fdataRank.setHasRQ(result>0?1:0);
                  fdataRank.setHasSP(hasSP);
              
                  parentId = (parRankId!=defaultParent) ? parRankId:defaultParent;
                  FBeans listRank = new BRank().getAllRecordByR_D(rankId, nktId);
                  
                  boolean allowAdd = true; //((hasRank.startsWith("1"))) ?true:false;
                  boolean allowUpdate = (listRank.size()>1 && rankInitDate.equals(fRank.getCreateDate())) ? false:true;
                  boolean allowDelete = (listRank.size()>1 && rankInitDate.equals(fRank.getCreateDate())) ? false:true;                  
                  boolean check = true;
                  
                  if (anchor.equals("_INSERT_RANK") || anchor.equals("_UPDATE_RANK")) {
                      createDate = fdataRank.getCreateDate();                      
                      try {
                          Date rankInsertDate = Formater.str2date(createDate);
                          Date rankCurrentDate = Formater.str2date(rankInitDate);
                          if (rankInsertDate.before(rankCurrentDate)) {
                              check = false;
                              errors.add("alert", new ActionError("common.label.rank.date-before-init"));
                          } 
                          
                          int numRanked = new BRank().countNumRanked(rank, nktId, rankInitDate, 0);
                          if ((numRanked<32) && rankInsertDate.after(rankCurrentDate)) {
                              check = false;
                              errors.add("alert", new ActionError("common.label.rank.unable-rank-next"));
                          }                         
                      } catch (Exception e) {
                      }
                  }
                  
                  if (anchor.equals("_DELETE_RANK")) {
                      if (!allowDelete) {
                          errors.add("alert", new ActionError("common.label.rank.cannot-delete"));
                      } else {
                          if (bo.delete(fdataRank)) {
                              rankCurDate = new BRank().getRankDate(nktId, null, 1, 0, "DESC");
                              if (!"".equals(rankCurDate)) {
                                  rankCurDate = rankCurDate.substring(0, rankCurDate.length()-1);
                              }
                              fdataHdr.setHdrCreateDate(rankCurDate);
                              fdataHdr.setDtlId(0);
                              fdataHdr.setCurResult(0);
                              errors.add("alert", new ActionError("alert.delete.successfull"));
                          } else {
                              errors.add("errors",new ActionError("alert.delete.unSuccessfull"));
                          }
                      }                      
                  } else {
                      if (anchor.startsWith("_INSERT")) {                        
                          if (allowAdd) {
                              if (check) {
                                  if (bo.isExist(fdataRank)) {
                                        errors.add("alert", new ActionError("common.label.rank.exists"));
                                  } else if (bo.insert(fdataRank)) {
                                        rankCurDate = createDate;
                                        fdataHdr.setRankResult(-1);
                                        fdataHdr.setRankHasRQ(0);
                                        fdataHdr.setRankHasSP(0);
                                        fdataHdr.setParRankId(parentId);
                                        fdataHdr.setDtlId(0);
                                        
                                        fdataHdr.setCurRankHas(rankCurDate);
                                        fdataHdr.setHdrCreateDate(rankCurDate);
                                        rankInitDate = new BRank().getRankDate(nktId, null, 1, 0, "ASC");
                                        rankInitDate = rankInitDate.substring(0, rankInitDate.length()-1);
                                        
                                        errors.add("alert", new ActionError("alert.insert.successfull"));
                                  } else {
                                        errors.add("alert",new ActionError("alert.insert.unSuccessfull"));
                                  }  
                              }                              
                          } else {
                              errors.add("alert", new ActionError("common.label.rank.cannot-rank"));
                          }                         
                      } else {
                          if (!allowUpdate) {
                              errors.add("alert", new ActionError("common.label.rank.cannot-update"));
                          } else {                             
                              if (check) {
                                  if (bo.update(fdataRank)) {
                                      rankCurDate = createDate;
                                      fdataHdr.setRankResult(-1);
                                      fdataHdr.setRankHasRQ(0);
                                      fdataHdr.setRankHasSP(0);
                                      fdataHdr.setParRankId(parentId);
                                      fdataHdr.setDtlId(0);
                                      
                                      fdataHdr.setCurRankHas(rankCurDate);
                                      fdataHdr.setHdrCreateDate(rankCurDate);
                                      errors.add("alert", new ActionError("alert.update.successfull"));
                                  } else {
                                      errors.add("alert",new ActionError("alert.update.unSuccessfull"));
                                  }
                              }                              
                          }
                      }
                  }
                  
                  // Reset form
                  //fdataHdr.setRankCreateDate(Formater.date2str(new Date()));
                  fdataHdr.setRankResult(0);            
                  
                  totalIndicator = new BRank().countIndicator(parentId);
                  numRankIndicator = new BRank().countNumRanked(rank, nktId, rankInitDate.equals(rankCurDate)?rankInitDate:rankCurDate, parentId);
                  
                  if ("".equals(rankInitDate)) {
                      fdataHdr.setNotifyNumInput("");
                      fdataHdr.setNotifyInit(fdataHdr.ncrToString(msgInfoInit));
                  } else {                  
                      String num_indicator = String.valueOf(numRankIndicator) + "/" + String.valueOf(totalIndicator);
                      if (numRankIndicator<totalIndicator) {
                          fdataHdr.setNotifyInit(""); 
                          fdataHdr.setNotifyNumInput(fdataHdr.ncrToString(msgInfoNum.replace("[$num-indicator$]", num_indicator)));                        
                          fdataHdr.setNotifyNext("");
                      } else {                
                          fdataHdr.setNotifyInit("");
                          fdataHdr.setNotifyNumInput("");
                          fdataHdr.setNotifyNext(fdataHdr.ncrToString(msgInfoNext.replace("[$rank-date$]", "".equals(rankCurDate)?rankInitDate:rankCurDate)));
                      }             
                  }
                  
                  getRankResults(request, rank, nktId, (listRank.size()==1)?rankInitDate:rankCurDate);
                  fdataHdr.setRankHas((listRank.size()>0) ? "1":"0");
                  
                  request.setAttribute("BRankByR_D", listRank);
                  request.setAttribute("BComboRank", new BRank().getAllRecordByParent(0));
                  request.setAttribute("ListRanks", new BRank().getListRanks(rank, nktId, rankInitDate));
                  
                  if (countNumRanked>1) {
                      request.setAttribute("BRanks", new BRank().getAllRecordByDisContinues(rank, nktId, rankInitDate, rankCurDate, parentId));
                  } else {
                      request.setAttribute("BRanks", new BRank().getAllRecordByDis(rank, nktId, rankInitDate.equals(rankCurDate)?rankInitDate:rankCurDate, parentId));
                  }
                  
                  request.setAttribute("kpi", fdataHdr);
                  target = "_RANK";
            } else if (anchor.equals("_DETAIL_RANK") || anchor.equals("_DETAIL_RANK_DTL") 
                       || anchor.equals("_PRE_CRUD_RANK") || anchor.equals("_PRE_EDIT_RANK")
                       || anchor.equals("_PRE_CRUD_RE_RANK")) {
                FRank rank = new FRank();
                FDataRank dataRank = new FDataRank();
                String curRankDate = fdataHdr.getHdrCreateDate();            
                boolean isReRank = fdataHdr.getReRank()==1?true:false;

                if (anchor.equals("_PRE_CRUD_RANK")) {             
                    fdataHdr.setDtlId(0);
                    fdataHdr.setRankHasSP(0);
                    if (isReRank) {
                        curRankDate  = fdataHdr.getHdrCreateDate();
                        fdataHdr.setRankCreateDate(curRankDate);
                    } else {
                        fdataHdr.setRankCreateDate("".equals(rankCurDate)?profileDate:rankCurDate);
                    }
                } else if (anchor.equals("_PRE_CRUD_RE_RANK")) {
                    isReRank = true;
                    fdataHdr.setDtlId(0);
                    curRankDate = Formater.date2str(new Date());
                    fdataHdr.setHdrCreateDate(curRankDate);
                    fdataHdr.setRankCreateDate(curRankDate);
                    fdataHdr.setRankHasSP(0);
                } else if (anchor.equals("_PRE_EDIT_RANK")) {
                    if (isReRank) {
                        curRankDate  = fdataHdr.getRankCreateDate();
                        fdataHdr.setRankCreateDate(fdataHdr.getRankCreateDate());
                    } else {
                        fdataHdr.setRankCreateDate("".equals(rankCurDate)?profileDate:rankCurDate);
                    }
                    fdataHdr.setRankHasSP(0);
                } else if (anchor.equals("_DETAIL_RANK")) {
                    fdataHdr.setRankCreateDate(curRankDate);
                    fdataHdr.setCreateDate(curRankDate);
                }
                 
                // Get RankResult
                 getRankResults(request, rank, nktId, rankInitDate);
                
                fDis.setId(fdataHdr.getNktId());
                FBeans listRank = new BRank().getAllRecordByR_D(rankId, nktId);
                parentId = (parRankId!=defaultParent) ? parRankId:defaultParent;
                
                if (rankId>0) {
                    rank.setId(rankId);                   
                    rank = new BRank().getRankByDtlId(rankId, nktId, dtlId);
                    String[] req = request.getParameterValues("rankHas");

                    String hasRank = "0";
                    if (req != null && req.length > 0) {
                        for (int i = 0; i < req.length; i++) {
                            if (req[i].endsWith(String.valueOf(rankId))) {
                                hasRank = req[i];
                                break;
                            }
                        }
                    } else {
                        hasRank = fdataHdr.getRankHas();
                    }
                    
                    boolean chkCombo = hasRank.startsWith("0") ? false:true;
                    boolean chkCurDtl =  fdataHdr.getCurRankHas().startsWith("0") ? false:true;
                    
                    if (chkCombo && chkCurDtl) {
                        fdataHdr.setRankResult(-1);
                        fdataHdr.setRankHasSP(0);                     
                        fdataHdr.setRankHas("1_"+rankId);
                    } else if (!chkCombo && chkCurDtl) {
                        fdataHdr.setDtlId(dtlId);
                        if (anchor.equals("_PRE_CRUD_RANK")) {
                            fdataHdr.setRankHas("0_"+rankId);
                        } else {
                            fdataHdr.setRankHas("1_"+rankId);    
                        }
                    } else if (!chkCombo && !chkCurDtl) {
                        fdataHdr.setDtlId(dtlId);
                        if (anchor.equals("_PRE_CRUD_RANK")) {
                            fdataHdr.setRankHas("0_"+rankId);
                        }
                        fdataHdr.setRankHas("0_"+rankId);
                    } else if (chkCombo && !chkCurDtl) {
                        fdataHdr.setDtlId(dtlId);
                        if (anchor.equals("_PRE_CRUD_RANK")) {
                            if (hasRank.startsWith("1")) {
                                fdataHdr.setRankHas("1_"+rankId);            
                            } else {
                                fdataHdr.setRankHas("0_"+rankId);
                            }                            
                        }
                    } else {
                        fdataHdr.setRankHas(fdataHdr.getCurRankHas());
                        fdataHdr.setRankResult(rank.getResult());
                        fdataHdr.setRankHasSP(rank.getHasSP());  
                    }
                    
                    fdataHdr.setRankId(rank.getId());                   
                    fdataHdr.setRankResult(rank.getResult());
                    // fdataHdr.setParRankId(parentId);
                    fdataHdr.setRankName(rank.getName());
                    fdataHdr.setBreadcrumb(rank.getBreadcrumb());
                    if (dtlId>0) {
                        fdataHdr.setRankCreateDate(rank.getCreateDate());
                    }
                    request.setAttribute("BRankByR_D", listRank);
                }
                               
                fdataHdr.setNotifyInit("");               
                fdataHdr.setMode((anchor.equals("_PRE_CRUD_RANK")||anchor.equals("_PRE_EDIT_RANK")||anchor.equals("_PRE_CRUD_RE_RANK"))?"CRUD_RANK":"VIEW_RANK");
                request.setAttribute("BComboRank", new BRank().getAllRecordByParent(0));
                request.setAttribute("ListRanks", new BRank().getListRanks(rank, nktId, rankInitDate));
                if (anchor.equals("_PRE_CRUD_RANK")) {
                    if (isReRank) {
                        request.setAttribute("BRanks", new BRank().getAllRecordByDisContinues(rank, nktId, rankInitDate, curRankDate, parentId));
                    } else {
                        request.setAttribute("BRanks", new BRank().getAllRecordByDis(rank, nktId, curRankDate, parentId));
                    }
                    
                    /*                     
                    if (countNumRanked>1) {
                        request.setAttribute("BRanks", new BRank().getAllRecordByDisContinues(rank, nktId, rankInitDate, curRankDate, parentId));
                    } else {
                        
                    }
                    */
                } else if (anchor.equals("_PRE_CRUD_RE_RANK")) {
                    request.setAttribute("BRanks", new BRank().getAllRecordByDisContinues(rank, nktId, rankInitDate, curRankDate, parentId));
                } else {
                    request.setAttribute("BRanks", new BRank().getAllRecordByDis(rank, nktId, rankInitDate, parentId));  
                }
                
                request.setAttribute("kpi", fdataHdr);
                request.setAttribute("rank", rank);   
                target = anchor;            
            } else if (anchor.equals("_RANK")
                        ||anchor.equals("_PRE_INSERT_RANK")
                        ||anchor.equals("_CRUD_RANK_CHANGE_OPTION") 
                        || anchor.equals("_VIEW_RANK_CHANGE_OPTION")) {
                if (anchor.equals("_RANK")){                    
                    //parentId = defaultParent;
                    fdataHdr.setRankId(0);
                    fdataHdr.setRankResult(0);
                    fdataHdr.setCurResult(0);
                    fdataHdr.setParRankId(defaultParent);
                    fdataHdr.setBreadcrumb("");
                    fdataHdr.setMode("");
                    fdataHdr.setHdrCreateDate(rankCurDate);
                } else {
                    //parentId = (parRankId!=defaultParent) ? parRankId:defaultParent;
                    fdataHdr.setRankId(0);
                    fdataHdr.setBreadcrumb("");
                    fdataHdr.setRankResult(0);
                    fdataHdr.setMode("_CRUD_RANK_CHANGE_OPTION".equals(anchor)?"CRUD_RANK":"VIEW_RANK");
                }
                
                FRank rank = new FRank();
                
                
                // Get RankResult
                getRankResults(request, rank, nktId, rankInitDate);
                                
                // Check num inpur rank                
                totalIndicator = new BRank().countIndicator(parentId);
                numRankIndicator = new BRank().countNumRanked(rank, nktId, rankInitDate, parentId);
                
                FBeans listRank = new BRank().getAllRecordByR_D(rankId, nktId);
                int rankSize = listRank.size();
                int hdrTotalInd = new BRank().countIndicatorChild();
                int hdrTotalIndRanked = new BRank().countNumRanked(rank, nktId, rankInitDate.equals(rankCurDate)?rankInitDate:rankCurDate, 0);
                
                boolean chk = (numRankIndicator==total) ? true: false;
                
                if ("".equals(rankInitDate)) {
                    fdataHdr.setNotifyNumInput("");
                    fdataHdr.setNotifyInit(fdataHdr.ncrToString(msgInfoInit));
                    fdataHdr.setNotifyNext("");
                } else if (hdrTotalIndRanked==hdrTotalInd) {
                    fdataHdr.setNotifyInit("");
                    fdataHdr.setNotifyNumInput("");
                    fdataHdr.setNotifyNext(fdataHdr.ncrToString(msgInfoNext.replace("[$rank-date$]", ("".equals(rankCurDate)?rankInitDate:rankCurDate))));                    
                } else {
                    String num_indicator = String.valueOf(numRankIndicator) + "/" + String.valueOf(totalIndicator);
                    if (numRankIndicator<totalIndicator) {
                        fdataHdr.setNotifyInit(""); 
                        fdataHdr.setNotifyNumInput(fdataHdr.ncrToString(msgInfoNum.replace("[$num-indicator$]", num_indicator)));                        
                        fdataHdr.setNotifyNext("");
                    } else {
                        fdataHdr.setNotifyInit("");
                        fdataHdr.setNotifyNumInput("");
                        //fdataHdr.setNotifyNext(fdataHdr.ncrToString(msgInfoNext.replace("[$rank-date$]", ("".equals(rankCurDate)?rankInitDate:rankCurDate))));
                    }
                }                                  
               
                request.setAttribute("ListRanks", new BRank().getListRanks(rank, nktId, rankInitDate));
                if (anchor.equals("_PRE_INSERT_RANK")||anchor.equals("_CRUD_RANK_CHANGE_OPTION")||anchor.equals("_VIEW_RANK_CHANGE_OPTION")) {                  
                    request.setAttribute("BComboRank", new BRank().getAllRecordByParent(0));
                    request.setAttribute("BRanks", new BRank().getAllRecordByDis(rank, nktId, rankInitDate.equals(rankCurDate)?rankInitDate:rankCurDate, parentId));
                }
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_COMMUNE")||(anchor.equals("_CHANGE_COMMUNE"))) {
                  if (anchor.equals("_COMMUNE")){  
                      fdataHdr.resetReport();
                  } else {
                      int chk = fdataHdr.getRptHuongCanThiep();
                      if(chk==0) {
                          fdataHdr.setRptCanThiep("");
                      }
                  }
                  request.setAttribute("BCommunes", new BDisReport().getReporByNktId(nktId));
                  request.setAttribute("kpi", fdataHdr);
                  target = anchor;        
            } else if (anchor.equals("_DETAIL_COMMUNE")) {
                  FDisReport fReport = new FDisReport();
                  fReport = new BDisReport().getRecordById(fdataHdr.getRptId());
                  nktId = fReport.getNktId();
                  
                  fdataHdr.setRptCreateDate(fReport.getCreateDate());
                  fdataHdr.setRptCreateBy(fReport.getCreateBy());
                  fdataHdr.setRptP1(fReport.getKtbtThuongXuyen());
                  fdataHdr.setRptP2(fReport.getKtbtTapDung());
                  fdataHdr.setRptP3(fReport.getDctgPhuHop());
                  fdataHdr.setRptP4(fReport.getDctgThuongXuyen());
                  fdataHdr.setRptP5(fReport.getDctgBaoQuan());
                  fdataHdr.setRptNcs(fReport.getHdNcs());
                  fdataHdr.setRptHuongCanThiep(fReport.getCanThiep());
                  fdataHdr.setRptCanThiep(fReport.getHuongCanThiep());
                  fdataHdr.setRptHtroDKien(fReport.getHtroDuKien());
                  // fdataHdr.setRptObj(fReport.getObjId());
                  request.setAttribute("BCommunes", new BDisReport().getReporByNktId(nktId));
                  request.setAttribute("kpi", fdataHdr);
                  target = anchor;
            } else if (anchor.equals("_INSERT_COMMUNE") || anchor.equals("_UPDATE_COMMUNE") ) {
                  boolean retval = false;
                  nktId = fdataHdr.getNktId();
                  FDisReport disReport = new FDisReport();
                  if (anchor.startsWith("_UPDATE")) {
                      disReport.setId(fdataHdr.getRptId());
                      disReport.setNktId(nktId);
                      disReport.setCreateDate(fdataHdr.getRptCreateDate());
                      disReport.setCreateBy(fdataHdr.getRptCreateBy());
                      
                      disReport.setKtbtThuongXuyen(fdataHdr.getRptP1());
                      disReport.setKtbtTapDung(fdataHdr.getRptP2());                  
                      disReport.setDctgPhuHop(fdataHdr.getRptP3());
                      disReport.setDctgThuongXuyen(fdataHdr.getRptP4());
                      disReport.setDctgBaoQuan(fdataHdr.getRptP5());
                      
                      disReport.setHdNcs(fdataHdr.getRptNcs());
                      disReport.setCanThiep(fdataHdr.getRptHuongCanThiep());
                      disReport.setHuongCanThiep(fdataHdr.getRptHuongCanThiep()==1?fdataHdr.getRptCanThiep():"");
                      disReport.setHtroDuKien(fdataHdr.getRptHtroDKien());
                      // disReport.setObjId(fdataHdr.getRptObj());
                      retval = new BDisReport().update(disReport);
                  } else {
                      disReport.setNktId(nktId);
                      disReport.setCreateDate(fdataHdr.getRptCreateDate());
                      disReport.setCreateBy(fdataHdr.getRptCreateBy());
                      
                      disReport.setKtbtThuongXuyen(fdataHdr.getRptP1());
                      disReport.setKtbtTapDung(fdataHdr.getRptP2());                  
                      disReport.setDctgPhuHop(fdataHdr.getRptP3());
                      disReport.setDctgThuongXuyen(fdataHdr.getRptP4());
                      disReport.setDctgBaoQuan(fdataHdr.getRptP5());
                      
                      disReport.setHdNcs(fdataHdr.getRptNcs());
                      disReport.setCanThiep(fdataHdr.getRptHuongCanThiep());
                      disReport.setHuongCanThiep(fdataHdr.getRptHuongCanThiep()==1?fdataHdr.getRptCanThiep():"");
                      disReport.setHtroDuKien(fdataHdr.getRptHtroDKien());
                      // disReport.setObjId(fdataHdr.getRptObj());
                      retval = new BDisReport().insert(disReport);
                  }
                  if (retval) {
                      if (anchor.equals("_UPDATE_COMMUNE")) {
                          errors.add("alert", new ActionError("alert.update.successfull"));
                      } else {
                          errors.add("alert", new ActionError("alert.insert.successfull"));
                      }
                  }
                  
                  fdataHdr.resetReport();
                  request.setAttribute("BCommunes", new BDisReport().getReporByNktId(nktId));
                  request.setAttribute("kpi", fdataHdr);
                  target = "_COMMUNE";
            } else if (anchor.equals("_DELETE_COMMUNE")) {
                  FDisReport disReport = new FDisReport();
                  disReport.setId(fdataHdr.getRptId());
                  if (new BDisReport().delete(disReport)) {
                      errors.add("alert", new ActionError("alert.delete.successfull"));
                  } else {
                      errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                  }
                  fdataHdr.resetReport();
                  request.setAttribute("BCommunes", new BDisReport().getReporByNktId(fdataHdr.getDtlId()));
                  target = "_COMMUNE";  
            } else if (anchor.equals("_PROFILE")) {
                  fdataHdr.resetProfile();
                  request.setAttribute("BProfiles", new BDisProfile().getProfileByNktId(nktId));
                  request.setAttribute("kpi", fdataHdr);
                  target = anchor;
            } else if (anchor.equals("_DETAIL_PROFILE")) {
                  FDisProfile profile = new BDisProfile().getProfileById(fdataHdr.getPfId());
                  fdataHdr.setPfStatus(profile.getStatus());
                  fdataHdr.setPfResonId(profile.getResonId());
                  fdataHdr.setPfCreateOn(profile.getCreateOn());
                  fdataHdr.setPfCreateBy(profile.getCreateBy());
                  fdataHdr.setPfUpdateOn(profile.getUpdateOn());
                  fdataHdr.setPfUpdateBy(profile.getUpdateBy());
                  fdataHdr.setPfAssessment(profile.getAssessment());
                  
                  fdataHdr.setStatusId(-1);
                  request.setAttribute("BProfiles", new BDisProfile().getProfileByNktId(nktId));
                  request.setAttribute("kpi", fdataHdr);
                  target = anchor;
            } else if (anchor.equals("_INSERT_PROFILE")||anchor.equals("_UPDATE_PROFILE")) {
                boolean retval = false;
                FDisProfile profile = new FDisProfile();
                String createOn = fdataHdr.getPfCreateOn();
                
                //if (fdataHdr.stringToDate(createOn).before(new Date())){
                //    errors.add("alert", new ActionError("alert.createon.must.before.current.date"));
                //}
                
                if (anchor.startsWith("_UPDATE")) {
                    profile.setId(fdataHdr.getPfId());
                    profile.setNktId(fdataHdr.getNktId());
                    profile.setStatus(fdataHdr.getPfStatus());
                    profile.setResonId(fdataHdr.getPfResonId());
                    profile.setCreateOn(fdataHdr.getPfCreateOn());
                    profile.setCreateBy(fdataHdr.getPfCreateBy());
                    profile.setUpdateOn(fdataHdr.getPfUpdateOn());
                    profile.setUpdateBy(fdataHdr.getPfUpdateBy());
                    profile.setAssessment(fdataHdr.getPfAssessment());                
                    retval = new BDisProfile().update(profile);
                } else {
                    profile.setNktId(fdataHdr.getNktId());
                    profile.setStatus(fdataHdr.getPfStatus());
                    profile.setResonId(fdataHdr.getPfResonId());
                    profile.setCreateOn(fdataHdr.getPfCreateOn());
                    profile.setCreateBy(fdataHdr.getPfCreateBy());
                    profile.setUpdateOn(fdataHdr.getPfUpdateOn());
                    profile.setUpdateBy(fdataHdr.getPfUpdateBy());
                    profile.setAssessment(fdataHdr.getPfAssessment());
                    retval = new BDisProfile().insert(profile);
                }
                
                if (retval) {               
                      fDis.setId(nktId);
                      fDis.setTrangthai(fdataHdr.getPfStatus());
                      boolean chk = new BDisability().updateStatus(fDis);
                      if (chk) {
                          errors.add("alert", new ActionError("alert.update.successfull"));
                      }
                  } else {
                      errors.add("alert", new ActionError("alert.insert.successfull"));
                  }
                
                fdataHdr.resetProfile();
                request.setAttribute("BProfiles", new BDisProfile().getProfileByNktId(nktId));
                request.setAttribute("kpi", fdataHdr);
                target = "_PROFILE";
            }  else if (anchor.equals("_DELETE_PROFILE")) {
                FDisProfile profile = new FDisProfile();
                profile.setId(fdataHdr.getPfId());
                if (new BDisProfile().delete(profile)) {
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                }
                request.setAttribute("BProfiles", new BDisProfile().getProfileByNktId(nktId));
                fdataHdr.resetProfile();
                target = "_PROFILE"; 
              } else if (anchor.equals("_HOTRO")) {
                int statusId = fdataHdr.getTabId();
                fdataHdr.setDisDoiTuong(0);
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(nktId, statusId));
                if (anchor.equals("_RANK")) { 
                    FObject object = new FObject();                
                    objId = fdataHdr.getObjId();
                    inputType = 1;                
                    
                    FTinh beanT = new FTinh();
                    beanT.setId(locationId);
                    
                    if (userId!=375) {
                        disCode = new BTinh().getMaxCodeDis(beanT);
                    }
                    
                    fdataHdr.setDisCode(disCode);
                    fdataHdr.setObjId(objId);
                    fdataHdr.setType(1);
                    fdataHdr.setInputType(inputType);
                    object.setId(fdataHdr.getObjId());
                    object = new BObject().getRecordByID(object);
                    fdataHdr.setObjCode(object.getCode());
                    fdataHdr.setObjName(object.getName());
                    fdataHdr.setObjDesc(object.getDescription());
                   
                    
                    String valMdoHLong = "";                    
                    String keyMdoHLong = fdataHdr.getMdoHlong();
                    if (!"-1".equals(keyMdoHLong)){
                        valMdoHLong =  map_hotro_danhgia.get(keyMdoHLong);
                    }
                    
                    FSupport fSupport = new FSupport();
                    fSupport.setMdoHlong(valMdoHLong);
                    fSupport.setIdNkt(dtlId);
                    fSupport.setCreateDate(fdataHdr.getCreateDate());
                    fSupport.setStatusId(1);
                    fSupport.setHotroIds(String.valueOf(fdataHdr.getSupportId()));
                    fSupport.setThoiDiemTK(fdataHdr.getDisNgayTK());
                    fSupport.setDiaDiemKham(fdataHdr.getDisDiaDiem());
                    fSupport.setDoiTuong(fdataHdr.getDisDoiTuong());
                    if (new BSupport().updateSupport(fSupport)) {
                        errors.add("alert", new ActionError("alert.rank.successfull"));
                    } else {
                        errors.add("errors",new ActionError("alert.rank.unsuccessfull"));
                    }
                    
                    request.setAttribute("kpi", fdataHdr);
                    request.setAttribute("listDataDtl", beans);
                    request.setAttribute("subanchor", "01.01");
                    request.setAttribute("anchor", "01");
                    request.setAttribute("menuObjectInput", menuObject);
                    target = _SUCCESS;
                } else {
                    // set Status 0/1                
                    FSupport support = new FSupport();            
                    support.setIdNkt(nktId);
                    support.setCreateDate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                    support.setDateCreate(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                    support.setDateForm(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                    support.setDateTo(fdataHdr.dateToString(fdataHdr.getCurrentDate()));
                    support.setThoiDiemTK(fdataHdr.getDisNgayTK());
                    support.setDiaDiemKham(fdataHdr.getDisDiaDiem());
                    support.setDoiTuong(fdataHdr.getDisDoiTuong());
                    support.setStatusId(tabId);
                    request.setAttribute("kpi", fdataHdr);
                    request.setAttribute("support", support);
                    request.setAttribute("statusId", statusId);            
                    
                    request.setAttribute("subanchor", "01.01");
                    request.setAttribute("anchor", "01");
                    request.setAttribute("menuObjectInput", menuObject);
                    target = anchor;  
                }            
            } else if (anchor.equals("_INSERT_SUPPORT") || anchor.equals("_UPDATE_SUPPORT")) {            
                BSupport bo = new BSupport();
                FSupport support = new FSupport();
                boolean retval = false;
                                
                int nguonHoTroId = fdataHdr.getNguonHoTroId();
                int status = fdataHdr.getStatusId();
                int numSupport = new BSupport().countNCauHTroByNguonId(nktId, fdataHdr.getCreateDate(), nguonHoTroId);
                int stt = 0;
                boolean checkReq = true;
                
                Map<String, String> map_nguonhotro = new HashMap<String, String>();
                try {
                    map_nguonhotro = new BNguonhotro().get_map_nguonhotro();
                } catch (Exception e) {
                }
                
                if (status==1) {
                   if (nguonHoTroId==0) {
                        checkReq = false;
                        errors.add("alert", new ActionError("alert.support.source.must-select"));
                    } else if (nguonHoTroId>0 && nguonHoTroId != 8) {
                       if (numSupport>0 && anchor.startsWith("_INSERT")){
                          checkReq = false;
                          errors.add("alert", new ActionError("alert.support.source.invalid", map_nguonhotro.get(String.valueOf(nguonHoTroId))));
                       }
                    } else if (nguonHoTroId==8) {
                        if (anchor.equals("_INSERT_SUPPORT")) {
                            stt = ++numSupport;
                        } else {
                            stt = fdataHdr.getStt();
                        }
                    }
                }
                
                String strSupport = "";
                String strChecked = "";
                
                int[] arrSupportIds = fdataHdr.getSupportIds();
                if (!Utilities.contains(arrSupportIds, 0) && arrSupportIds!=null) {
                    for (int i = 0; i < arrSupportIds.length; i++) {
                        strChecked += arrSupportIds[i] != 0 ? arrSupportIds[i] + "#" : "";
                    }
                }
                
                if (strChecked!="" && strChecked.length()>1) {
                    strSupport = strChecked.substring(0, strChecked.length()-1);  
                }
                
                try {
                    if (checkReq) {                        
                        if (strSupport.indexOf("#") > -1) {
                            String[] arrSupport = strSupport.split("#");
                            for (int i = 0; i < arrSupport.length; i++) {
                                support.setHotroIds(arrSupport[i]);
                                support.setStt(stt);
                                populateFormToForm(fdataHdr, support);
                                if (anchor.startsWith("_UPDATE") && (i==0)) {
                                    bo.delete(nktId, fdataHdr.getStatusId(), fdataHdr.stringToSqlDate(fdataHdr.getDateCreate()), stt, fdataHdr.getNguonHoTroId());
                                    retval = bo.insert(support);
                                } else {
                                    retval = bo.insert(support);  
                                }
                            }
                        } else {
                            support.setHotroIds(strSupport);
                            support.setStt(stt);
                            populateFormToForm(fdataHdr, support);                    
                            if (anchor.startsWith("_UPDATE")) {
                                bo.delete(fdataHdr.getNktId(), fdataHdr.getStatusId(), fdataHdr.stringToSqlDate(fdataHdr.getDateCreate()), stt, fdataHdr.getNguonHoTroId());
                                retval = bo.insert(support);
                            } else {
                                retval = bo.insert(support);  
                            }
                        }
                        
                        if (retval) {
                            if (anchor.equals("_UPDATE_SUPPORT")) {
                                errors.add("alert", new ActionError("alert.update.successfull"));
                            } else {
                                errors.add("alert", new ActionError("alert.insert.successfull"));
                            }
                        } else {
                            errors.add("alert", new ActionError("alert.insert.unSuccessfull.6.month"));                        
                        }
                    }
                } catch (EException ex) {
                    errors.add("alert", new ActionError("alert.catch.support.input.unique"));
                }
                
                support = new FSupport();
                support.reset();
                support.setIdNkt(fdataHdr.getNktId());
                support.setStatusId(fdataHdr.getStatusId());
                fdataHdr.setDisNgayTK("");
                fdataHdr.setDisDiaDiem(0);
                fdataHdr.setDisDoiTuong(0);
                fdataHdr.setSupportIds(null);
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(fdataHdr.getNktId(), fdataHdr.getStatusId()));
                request.setAttribute("support", support);
                request.setAttribute("BNkts", fDis);
                request.setAttribute("kpi", fdataHdr);
                target = "_HOTRO";
            }  else if (anchor.equals("_CHANGE_COMBO_BOX")) {
                FSupport support = new FSupport();
                request.setAttribute("support", support);
                target = "_HOTRO";
            } else if (anchor.equals("_VIEW_COMBO_BOX")) {
                FSupport fSupport = null;
                String supportSel = "";
                int retval = new BSupport().countById(fdataHdr.getNktId(),fdataHdr.getStatusId(),createDate);
                
                if (retval>0) {
                    fSupport = new BSupport().getById(fdataHdr.getNktId(),fdataHdr.getStatusId(),createDate);                  
                    fSupport.setIdNkt(fdataHdr.getNktId());
                    fSupport.setMode("UPDATE");
                } else {
                    fSupport = new FSupport();
                    fSupport.setCreateDate(createDate);
                    fSupport.setStatusId(fdataHdr.getStatusId());
                    fSupport.setIdNkt(fdataHdr.getNktId());
                }
                
                supportSel = Utilities.parseArr2Str(fdataHdr.getSupportIds(), "#");                          
                fSupport.setSupportSel(supportSel);
                fSupport.setHotroIds(supportSel); 
                
                request.setAttribute("BNkts", fDis);            
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(fdataHdr.getNktId(), fdataHdr.getStatusId()));            
                request.setAttribute("support", fSupport);
                target = "_HOTRO";
            } else if (anchor.equals("_DETAIL_SUPPORT")) {
                fDis.setId(disId);
                FSupport fSupport = new FSupport();
                fSupport.reset();
                
                int stt = fdataHdr.getStt();
                if (stt>0) {
                    fSupport = new BSupport().getSupportByStt(fdataHdr.getNktId(),fdataHdr.getStatusId(),fdataHdr.getDateCreate(), stt);
                } else {
                    fSupport = new BSupport().getById(fdataHdr.getNktId(),fdataHdr.getStatusId(),fdataHdr.getDateCreate());
                }
                
                FHomeVisit fHome = new BHomeVisit().getHomeVisit(fdataHdr.getNktId(), fdataHdr.getDateCreate());
                
                FHomeVisit visit = new FHomeVisit();
                visit.setNktId(fdataHdr.getNktId());
                visit.setSupportId(fHome.getSupportId());
                FBeans homeVisits = new BHomeVisit().getAll(fHome);
                
                FHomeVisit homeVisit = null;
                if (homeVisits.size()>0) {
                    homeVisit = (FHomeVisit)homeVisits.get(0);
                    fSupport.setLatitude(String.valueOf(homeVisit.getLatitude()));
                    fSupport.setLongitude(String.valueOf(homeVisit.getLonggitude()));
                    fSupport.setLocation(homeVisit.getLocation());                    
                    fSupport.setStartAt(homeVisit.getStartAt());
                    fSupport.setEndAt(homeVisit.getEndAt());                    
                    fSupport.setHasVisit(1);
                    
                    fdataHdr.setHasVisit(1);
                    fdataHdr.setSupportId(homeVisit.getSupportId());
                }
                
                String supportSel = fSupport.getHotroIds();
                fSupport.setSupportSel(supportSel);
                fSupport.setHotroIds(supportSel);
                fSupport.setMode("UPDATE");
                fSupport.setStt(stt);
                
                String ngayTaiKham = fSupport.getThoiDiemTK()==null?"":fSupport.getThoiDiemTK().substring(fSupport.getThoiDiemTK().indexOf("/")+1);
                
                fdataHdr.setDisNgayTK(ngayTaiKham);
                fdataHdr.setDisDiaDiem(fSupport.getDiaDiemKham()); 
                fdataHdr.setDisDoiTuong(fSupport.getDoiTuong());
                fDis = (FDisability)request.getAttribute("NKT");
                if (fDis!=null) {
                    supportSel = Utilities.parseArr2Str(fdataHdr.getSupportIds(), "#");                          
                    fSupport.setSupportSel(supportSel);
                    fSupport.setHotroIds(supportSel);             
                } else {
                    fDis = new FDisability(); 
                }
                
                request.setAttribute("BNkts", fDis);           
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(fdataHdr.getNktId(), fdataHdr.getStatusId()));
                request.setAttribute("support", fSupport);
                request.setAttribute("kpi", fdataHdr);
                target = "_HOTRO";
            } else if (anchor.equals("_DELETE_SUPPORT")) {
                fDis.setId(fdataHdr.getNktId());
                BSupport bo = new BSupport();
                BHomeVisit boHome = new BHomeVisit();
                FSupport fSupport = new FSupport();
                if (bo.delete(fdataHdr.getNktId(), fdataHdr.getStatusId(), fdataHdr.stringToSqlDate(fdataHdr.getDateCreate()),fdataHdr.getStt(), fdataHdr.getNguonId())) {
                    if (fdataHdr.getHasVisit()==1) {
                        boHome.deleteHomeVisit(fdataHdr.getSupportId());  
                    }
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.delete.unSuccessfull"));
                }
                
                fSupport.setIdNkt(fdataHdr.getNktId());
                fSupport.setDateCreate(Formater.date2str(fdataHdr.getCurrentDate()));
                fSupport.setStatusId(fdataHdr.getStatusId());
                fDis = (FDisability)request.getAttribute("NKT");
                       
                request.setAttribute("BNkts", fDis);           
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(fdataHdr.getNktId(), fdataHdr.getStatusId()));
                request.setAttribute("support", fSupport);
                target = "_HOTRO";
            }  else if (anchor.equals("_INFORMATION")) {
                if (nktId==0){
                    if (locationId>0) {
                        fdataHdr = new FDataHdr();
                        fTinh.setId(locationId);
                        disCode = bTinh.getMaxCodeDis(fTinh);
                        fdataHdr.setDisCode(disCode);    
                    } else {
                        fdataHdr.setDisCode("");    
                    }
                } else {
                    fdataHdr.setYearReport(yearReport);
                    fdataHdr = new BDataHdr().getRecordByIndicator(fdataHdr);
                    
                    fDis.setId(nktId);
                    fDis = new BDisability().getRecordByID(fDis);
                }
                 
                fDis.setTinhId(locationId);
                fDis.setPageIndex(fdataHdr.getPageIndex());            
                FBeans beansDtl = new BDisability().getDisKpi(fDis);
                FBeans acBeans = new BDisability().getDisKpiAc(fDis);
                
                FBeans districts = map_district.get(String.valueOf(locationId));
                request.setAttribute("districts", districts);
                if (districtId>0) {
                    FBeans communes = map_commune.get(String.valueOf(districtId));
                    request.setAttribute("communes", communes);
                }
                
                request.setAttribute("listDataDtl", beansDtl);
                request.setAttribute("listDataDtlAc", acBeans);
                
                fdataHdr.setCreateDate(nktId>0?fDis.getDateLastUpdate():fdataHdr.getCreateDate());
                fdataHdr.setNktId(nktId);
                fdataHdr.setStatusId(fDis.getTrangthai());
                request.setAttribute("kpi", fdataHdr);            
                request.setAttribute("BTreeDieuKiens", beansDieuKien);
                request.setAttribute("BDanTocs", beansDanToc);
                target = anchor;
            } else if (anchor.equals("_LIST_DIS")) {            
                beans = new BDataNkt().getAll(dataNkt, session);                
                fdataHdr.setObjId(objId);
                fdataHdr.setType(1);
                request.setAttribute("listDataDtl", beans);
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_EDIT_EVENT")) {            
                if (new BEvent().update(fEvent)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.indicator.update.unique.code"));
                }
                
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_DELETE_EVENT")) {
                if (new BEvent().delete(fEvent)) {
                    fEvent = new FEvent();
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.indicator.delete.havechild"));
                }
                target = anchor;
            } else if (anchor.equals("_PREPARED_DGIA")) {
                String supportName = "";
                if (fdataHdr.getSupportId()>0) {
                    supportName = map_hotro.get(String.valueOf(fdataHdr.getSupportId()));
                    fdataHdr.setSupportName(supportName);
                }
                
                FSupport fSupport = bSupport.getById(dtlId, 1, fdataHdr.getDateCreate());
                int idx = 0;
                String valMdo = "", keyMdo = "";
                valMdo = fSupport.getMdoHlong();
                if (!"".equals(valMdo)) {
                    idx = valMdo.indexOf("-");
                    keyMdo = valMdo.substring(0, idx);                
                }
                
                fdataHdr.setMdoHlong(keyMdo);
                request.setAttribute("kpi", fdataHdr);
                target = anchor;
            } else if (anchor.equals("_POPUP_DIS_COUNTDOWN")) {
                //FBeans disCountDown = (FBeans)request.getSession().getAttribute("LIST_DIS_COUNTDOWN");
                //FBeans disCountDownLate = (FBeans)request.getSession().getAttribute("LIST_DIS_COUNTDOWN_LATE");
                
                fDis.setUserId(userId);
                fDis.setTinhId(locationId);
                FBeans listDisCountDown = new BDisability().getCountdownDis(fDis);
                
                request.setAttribute("listCountDownLate", listDisCountDown);
                request.setAttribute("listCountDown", listDisCountDown);
                target = anchor;
            } else if(anchor.equals("_EXPORT_DATA") || anchor.equals("_EXPORT_EVENT")){
                FReportKpiData fReportData = new FReportKpiData();
                String fileName = ""; 
                
                if (anchor.equals("_EXPORT_EVENT")) {
                    fdataHdr.setType(4);
                }
                
                if (fdataHdr.getType()==Constant.KPI_DATA_VALUE) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_VAL;
                    fReportData.setDataType(Constant.KPI_DATA_VALUE);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_DIS) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_DIS;
                    fReportData.setDataType(Constant.KPI_DATA_DIS);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_PERSON) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_PER;
                    fReportData.setDataType(Constant.KPI_DATA_PERSON);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_EVENT) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_EVENT;
                    fReportData.setDataType(Constant.KPI_DATA_EVENT);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_HOURS) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_SP;
                    fReportData.setDataType(Constant.KPI_DATA_HOURS);
                } else if (fdataHdr.getType()==Constant.KPI_DATA_LIST_COUNT) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_LIST_DIS_COUNT;                
                } else if (fdataHdr.getType()==Constant.KPI_DATA_LIST_COUNT_LATE) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_LIST_DIS_COUNT_LATE;
                }
                
                String sql = (String)request.getSession().getAttribute("SQL_REPORT");            
                List  params = (ArrayList)request.getSession().getAttribute("params");            
                Map<String, String> mapParam = (Map<String, String>)request.getSession().getAttribute("mapParam");  
                
                FSearch fSearch = new FSearch();
                fSearch.setDataType(fdataHdr.getType());
                
                if (fdataHdr.getType()>=7) {
                    fDis.setUserId(userId);
                    fDis.setTinhId(locationId);
                    FBeans listDisCountDown = new BDisability().getCountdownDis(fDis);
                    beans = (fdataHdr.getType()==Constant.KPI_DATA_LIST_COUNT)?listDisCountDown:
                                                                      (FBeans)request.getSession().getAttribute("LIST_DIS_COUNTDOWN_LATE");
                } else {
                    beans = new BSearch().getReportAll(fSearch, sql, params, mapParam);   
                }
                
                fSearch.setStore(beans);
                
                String report = new DReportKpiData().ReportExcel(fSearch, seed, fileName);
                fdataHdr.download(report, fileName, null);
                fdataHdr.deleteFile(report);
                target = null;
            } else if (anchor.equals("_CHANGE_YEAR")) {
               
                FObject object = new FObject();
                FObjectInd objectInd = new FObjectInd();
                
                if (objId > 0) {                
                    object.setId(objId);
                    object = new BObject().getRecordByID(object);
                    fdataHdr.setIns(object.getIns());
                    fdataHdr.setCode(object.getCode());
                    fdataHdr.setName(object.getName());
                    fdataHdr.setDescription(object.getDescription());
                    
                    objectInd.setObjId(objId);
                    objectInd.setYear(yearReport);
                    objectInd.setLocationId(locationId);
                    FBeans listIndicator = new BObjectInd().getAll(objectInd, indIds);
                    request.setAttribute("listIndicator", listIndicator);
                }
                
                fdataHdr.setYearReport(yearReport);
                fdataHdr.setObjId(objId);
                request.setAttribute("kpi", fdataHdr);
                request.setAttribute("listDataDtl", beans);
                request.setAttribute("menuIndicatorsKpi", menuIndicator);
                request.setAttribute("menuObjectInput", menuObject);
                request.setAttribute("input", 1);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;  
            }
            
            fPerson.setLocationId(locationId); 
            FBeans acBeans = new FBeans();
            if ((fdataHdr.getType()==Constant.KPI_DATA_DIS) && (anchor.equals("_INFORMATION") || anchor.equals("_CREATE")) ) {            
                fDis.setTinhId(locationId);
                acBeans = new BDisability().getDisKpiAc(fDis);
                request.setAttribute("listDataDtlAc", acBeans);
                
                // Object
                FObject object = new FObject();
                object.setId(objId);
                object = new BObject().getRecordByID(object);
                
                // Indicator
                if (fdataHdr.getCode()==null) {
                    FIndicator indicator = new FIndicator();
                    indicator.setId(indId);
                    indicator = new BIndicator().getRecordByID(indicator);
                    
                    fdataHdr.setCode(indicator.getCode());
                    fdataHdr.setName(indicator.getName());
                }
                
                fdataHdr.setObjCode(object.getCode());
                fdataHdr.setObjName(object.getName());
                fdataHdr.setObjDesc(object.getDescription());
            }
            
            request.setAttribute("kpi", fdataHdr); 
            
            request.setAttribute("mapBaseline", map_combobox);
            request.setAttribute("mapEventType", map_event_type);
            request.setAttribute("mapEventField", map_event_field);
            request.setAttribute("mapVoteResult", map_kpi_vote_result);
            request.setAttribute("mapPLoaiDDiem", map_phanloai_diadiem);
            
            request.setAttribute("map_hotro_kn_chitra", map_hotro_kn_chitra);
            request.setAttribute("map_hotro_sd_the", map_hotro_sd_the);
            request.setAttribute("map_hotro_sd_the_phcn", map_hotro_sd_the_phcn);
            request.setAttribute("map_hotro_the_bhyt", map_hotro_the_bhyt);
            request.setAttribute("map_hotro_danhgia", map_hotro_danhgia);
            
            request.setAttribute("input", 1);
            request.setAttribute("subanchor", "01.01");
            request.setAttribute("anchor", "01");
            
            if (!errors.isEmpty())
                saveErrors(request, errors);
            return mapping.findForward(target);
        } catch (EException ex) {
            logger.info(ex.toString());
            return mapping.findForward(_ERROR);
        }
    }

    private String validate(FDataHdr bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if (bean.getType()==Constant.KPI_DATA_DIS) {
                if (bean.getDisName().trim().equals(_BLANK)) {
                    errors.add("alert", new ActionError("errors.indicator.edit.short"));
                }
            }
            
            if ((bean.getInputType()==0)) {
                int curMonth = bean.getMonth(bean.getCurrentSqlDate());
                int curQuarter = Formater.getPeriod(curMonth);
                int curYear = bean.getYearReport();
                int year = bean.getYear();
                
                if (year>curYear) {
                    errors.add("alert", new ActionError("errors.indicator.year.invalid"));                 
                } else if (year==curYear) {
                    if ((bean.getMonth()> curMonth || bean.getQuarter()>curQuarter)) {
                        errors.add("alert", new ActionError("errors.indicator.period.invalid"));
                    }  
                }   
            } 
        } else if (anchor.equals(_CREATE)) {
            if (bean.getType()==Constant.KPI_DATA_PERSON) {           
                if (bean.getPerName().trim().equals(_BLANK)) {
                    errors.add("alert", new ActionError("errors.people.name.edit.short"));
                }
            } 
            
            if ((bean.getInputType()==Constant.KPI_DATA_VALUE)) {
                int curMonth = bean.getMonth(bean.getCurrentSqlDate());
                int curQuarter = Formater.getPeriod(curMonth);
                int curYear = bean.getYearReport();
                int year = bean.getYear();
                
                if (year>curYear) {
                    errors.add("alert", new ActionError("errors.indicator.year.invalid"));                 
                } else if (year==curYear) {
                    if ((bean.getMonth()> curMonth || bean.getQuarter()>curQuarter)) {
                        errors.add("alert", new ActionError("errors.indicator.period.invalid"));
                    }  
                }                
            } 
        } 
        return anchor;
    }
    
    private void populateFormToForm (FDataHdr fdataHdr, FDisability fDis) {
        fDis.setId(fdataHdr.getDtlId());
        fDis.setDateLastUpdate(fdataHdr.getCreateDate());
        // NKT
        fDis.setTinhId(fdataHdr.getLocationId());
        fDis.setDistrictId(fdataHdr.getDistrictId());
        fDis.setCommuneId(fdataHdr.getCommuneId());
        fDis.setMa(fdataHdr.getDisCode());
        fDis.setMa_nkt(fdataHdr.getDisCodeNkt());
        fDis.setDateLastUpdate(fdataHdr.getCreateDate());
        fDis.setNkt(fdataHdr.getDisName());
        fDis.setSex(fdataHdr.getDisSex());
        fDis.setPhoneNumber(fdataHdr.getDisPhone());
        fDis.setNgaySinh(fdataHdr.getDisBirth());
        fDis.setCmnd(fdataHdr.getDisPassport());
        fDis.setDantocId(Integer.parseInt(fdataHdr.getDisNation()));
        fDis.setNgheNghiepHT(fdataHdr.getDisCarrer());
        fDis.setChatDocDaCam(fdataHdr.getDisDioxin());
        fDis.setSoNha(fdataHdr.getDisAddress());
        
        // NCS
        fDis.setTenChamSoc(fdataHdr.getNcsName());
        fDis.setNamSinhChamSoc(fdataHdr.getNcsBirth());                
        fDis.setQuanHeChamSoc(Integer.parseInt(fdataHdr.getNcsRelation()));
        fDis.setSdtLienLac(fdataHdr.getNcsPhone());
        fDis.setGioiTinhChamSoc(fdataHdr.getNcsSex()); 
       
        fDis.setTrangthai(fdataHdr.getStatusId());
        fDis.setDuAnId(fdataHdr.getDuAnId());
    }
    
    private void populateFormToForm (FDataHdr fdataHdr, FSupport support) {
        support.setIdNkt(fdataHdr.getNktId());
        support.setCreateDate(fdataHdr.getCreateDate());
        support.setDateCreate(fdataHdr.getCreateDate());
        support.setStatusId(fdataHdr.getStatusId());
        support.setUserId((int)me.getId());
        
        support.setNguonHoTroId(fdataHdr.getNguonHoTroId());
        support.setNguonhotro(fdataHdr.getNguonhotro());
        support.setReson(fdataHdr.getReson());
        support.setDateForm(fdataHdr.getDateForm());
        support.setDateTo(fdataHdr.getDateTo());
              
        support.setKnChiTra(fdataHdr.getKnChiTra());
        support.setTheBhyte(fdataHdr.getTheBhyte());
        support.setSdThe(fdataHdr.getSdThe());
        support.setSdThePhcn(fdataHdr.getSdThePhcn());
        support.setMtieuGdinh(fdataHdr.getMtieuGdinh());
        support.setMtieuDtri(fdataHdr.getMtieuDtri());
        support.setCtVltl(fdataHdr.getCtVltl());
        support.setCtHdtl(fdataHdr.getCtHdtl());
        support.setCtAntl(fdataHdr.getCtAntl());
        support.setDungcuKhac(fdataHdr.getDungcuKhac());
        support.setPhcnKhac(fdataHdr.getPhcnKhac());
        support.setMdoPtdl(fdataHdr.getMdoPtdl());
        
        support.setThoiDiemTK(fdataHdr.getDisNgayTK());
        support.setDiaDiemKham(fdataHdr.getDisDiaDiem());
        support.setBaoQuanDC(fdataHdr.getDisDungCu());
        support.setDoiTuong(fdataHdr.getDisDoiTuong());
        
        support.setCtGddb(fdataHdr.getCtGddb());
        support.setCtCsgn(fdataHdr.getCtCsgn());
        
        support.setNguoiTHTen(fdataHdr.getNguoiTHTen());
        support.setNguoiTHCv(fdataHdr.getNguoiTHCv());
    }
    
    private void getListDtl(FPerson fPerson, HttpServletRequest request, HttpSession session) {
        FBeans beans = new FBeans();
        try {
            beans = new BPerson().getAll(fPerson, session);
            request.setAttribute("listDataDtl", beans);
            request.setAttribute("total", beans.size());
        } catch (EException e) {            
        }
    }
    
    private void getListValue(FDataDtl fdataDtl, HttpServletRequest request, HttpSession session) {
        FBeans beans = new FBeans();
        try {
            beans = new BDataDtl().getAll(fdataDtl, session);
            request.setAttribute("listDataDtl", beans); 
            request.setAttribute("total", beans.size());
        } catch (EException e) {            
        
        }
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
    
    private void getRankResults(HttpServletRequest request, FRank rank, int nktId, String rankInitDate) throws EException, SQLException {
        // Process next rank 1,2,3
        java.sql.Date initDate = rank.stringToSqlDate(rankInitDate);
        String nextRankDate =  new BRank().getRankDate(nktId, initDate, 100, 0, "DESC");
        String strNextRankDate = "".equals(nextRankDate)? "" : nextRankDate.substring(0, nextRankDate.length()-1);
        String[] arrNextRankDate = StringUtils.split(strNextRankDate, ",");
        
        Map<String, String> mapResult = new HashMap<String, String>();
        Map<String, Object> mapRanks = new HashMap<String, Object>();
        int len = arrNextRankDate.length;
        for (int i=0;i<len;i++) {
            String createDate = arrNextRankDate[i];
            FBeans ranksByCreate = new BRank().getRankByCreateDate(rank, nktId, createDate);
            
            for (int j=0;j<ranksByCreate.size();j++) {
                com.form.disability.categorys.FRank r = (FRank)ranksByCreate.get(j);
                mapResult.put(String.valueOf(r.getId()), String.valueOf(r.getResult()));
            }            
            mapRanks.put(createDate, mapResult);
            mapResult = new HashMap<String, String>();
        }
        request.setAttribute("mapRanks", mapRanks);
    }
}
