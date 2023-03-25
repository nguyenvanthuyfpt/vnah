package com.action.disability.search;


import com.action.ACore;

import com.bo.disability.categorys.BDangTat;
import com.bo.disability.categorys.BEvent;
import com.bo.disability.categorys.BTinh;
import com.bo.disability.list.BList;
import com.bo.disability.search.BSearch;

import com.dao.disability.report.DReportKpiData;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FPhanLoai;
import com.form.disability.FSupport;
import com.form.disability.categorys.FEvent;
import com.form.disability.categorys.FTinh;
import com.form.disability.list.FList;
import com.form.disability.report.FReportKpiData;
import com.form.disability.search.FSearch;

import com.inf.disability.IKeyDisability;

import com.util.Constant;
import com.util.Formater;

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

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ASearch extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();
      
        FBeans beans=new FBeans();
        FSearch bean = (FSearch)form;
        BSearch bo = new BSearch();
        String anchor=((FSeed)form).getValue(APP_ANCHOR,""); 
        HttpSession session = seed.getRequest().getSession();
        
        Map<String, Object> mapObject = (Map<String, Object>)request.getSession().getAttribute("MAP_OBJECT");
        bean.setMapObject(mapObject);
        
        FList beanL = new FList();
        beanL.setUserId((int)me.getId());   
        beanL.setListId(bean.getListId());
        beanL.setPageIndex(bean.getPageIndex());
        beanL.setEmps(bean.getEmps());
        beanL.setCheckEmp(bean.getCheckEmp());
        beanL.setCheckEmpAll(bean.getCheckEmpAll());
        beanL.setListName(bean.getListName());
        
        String      panel           = bean.getPanel();
        String      panelSelected   = bean.getPanelSelected()==null ? "0#0#0#0" : bean.getPanelSelected();
        
        String characters="";
        String member="";
        String SQL = "";
        int userId = (int)bean.me.getId();
        int locationId = 0, districtId = 0, communeId = 0;
        
        beans = (FBeans)session.getAttribute("BTreeTinhs");
        request.setAttribute("BTreeTinhs", beans);
        request.setAttribute("BSearchTinhs", beans);       
        characters = "/ ";
        member = "";
        
        FBeans districts = new FBeans();
        FBeans communes = new FBeans();
        
        // Tree Object
        FBeans optObjects = new FBeans();
        optObjects = (FBeans)request.getSession().getAttribute("OPT_TREE_OBJECT");
        request.setAttribute("optObjects", optObjects);
        
        // Tree Indicator        
        FBeans optIndicators = new FBeans();
        optIndicators = (FBeans)request.getSession().getAttribute("OPT_TREE_INDICATOR");
        request.setAttribute("optIndicators", optIndicators);
        
        // Category
        FBeans beansNguonHoTro  = (FBeans)request.getSession().getAttribute("LIST_NGUONHOTRO");
        FBeans beansDieuKien = (FBeans)request.getSession().getAttribute("LIST_DIEUKIEN");
        FBeans beansNguyenNhan = (FBeans)request.getSession().getAttribute("LIST_NGUYENNHAN");
        FBeans beansMucDo = (FBeans)request.getSession().getAttribute("LIST_MUCDO");
        FBeans beansDanToc = (FBeans)request.getSession().getAttribute("LIST_DANTOC");
        FBeans beansTinhTrang = (FBeans)request.getSession().getAttribute("LIST_TINHTRANG");
        Map<String, FBeans> map_district = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_DISTRICT");
        Map<String, FBeans> map_commune = (HashMap<String, FBeans>)request.getSession().getAttribute("MAP_COMMUNE");
        Map<String, String> map_phanloai_diadiem = (HashMap<String,String>)mapObject.get("KPI_PHANLOAI_DIADIEM");                
        
        if(anchor.equals("_PANEL")){        
            if(panel.equals("_PANEL_DANGTAT")){                
                FPhanLoai beanPhanLoai = new FPhanLoai();
                request.setAttribute("phanloai", beanPhanLoai); 
                request.setAttribute("BPhanLoais",new BDangTat().getAllRecord(0));
                request.setAttribute("BNguyenNhans", beansNguyenNhan);
                request.setAttribute("mapPLoaiDDiem", map_phanloai_diadiem);
            }            
            
            if(panel.equals("_PANEL_HOTRO")||panel.equals("_PANEL_NHUCAU")){
                FSupport beantemp=new FSupport();
                beantemp.setIdNkt(bean.getId());
                beantemp.setDateCreate(bean.dateToString(bean.getCurrentDate()));                
                request.setAttribute("support",new FSupport());
                
              // Ho tro
                FBeans beansHoTro = (FBeans)request.getSession().getAttribute("LIST_HOTRO");
                FBeans beansNhuCau = (FBeans)request.getSession().getAttribute("LIST_NHUCAU");                
                request.setAttribute("BSupports", ("_PANEL_NHUCAU".equals(panel))?beansNhuCau:beansHoTro);
                request.setAttribute("mapPLoaiDDiem", map_phanloai_diadiem);
                request.setAttribute("BNguonHoTros", beansNguonHoTro);
                bean.setHt_statusId(panel.equals("_PANEL_HOTRO")?1:0);
            } 
            
            request.setAttribute("timkiem",bean);
            target = panel;            
        } else if(anchor.equals("_QUANLYCA_RESULT")){        
            member="";
            if((bean.me.getDepartmentName()!=null)&&(!bean.me.getDepartmentName().equals(""))){
                member=bean.me.getDepartmentName();
            }
            
            String memberRule = member.substring(1,member.length()-1);
            bean.setMembersRule(memberRule);
            bean.setRoleId(me.getRole());
            request.setAttribute("BDisabilitys",bo.getAll(bean));
            request.setAttribute("total", Formater.num2str(bean.getTotalResult()));
            
            FTinh beantemp=new FTinh();
            beantemp.setId(bean.getId());
            beantemp=new BTinh().getRecordByID(beantemp);
            
            if(bean.getId()>0){
                bean.setTinhName("");
                bean.setTinhId(bean.getId());
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
            
            bean.reset();
            bean.setDt_phanLoaiIds(null);
            bean.setNc_phanLoaiIds(null);
            bean.setHt_phanLoaiIds(null);
            bean.setCt_phanLoaiIds(null);
            bean.setCheckEmpAll(0);
            request.setAttribute("timkiem",bean);
            target = anchor;
            
        } else if(anchor.equals("_SEARCH_RESULT")){            
            bean.setQuanLyCa(0);
            SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
            characters="/ ";
            member="";
            
            if((bean.me.getDepartmentName()!=null)&&(!bean.me.getDepartmentName().equals(""))){
                member=bean.me.getDepartmentName();
                String memberRule = member.substring(1,member.length()-1);
                bean.setMembersRule(memberRule);
            }
            
            bean.setRoleId(me.getRole());
            if (bean.getDataType()==Constant.KPI_DATA_VALUE) { 
                beans = bo.getKpiValue(bean);
                request.setAttribute("BValues", beans);
            } else if (bean.getDataType()==Constant.KPI_DATA_DIS) { 
                beans = bo.getAll(bean);
                request.setAttribute("BDisabilitys", beans);
            } else if (bean.getDataType()==Constant.KPI_DATA_PERSON) {  
                beans = bo.getKpiPerson(bean);
                request.setAttribute("BPersons", beans);
            }
                        
            request.setAttribute("total", Formater.num2str(bean.getTotalResult()));            
            FTinh beantemp = new FTinh();
            beantemp.setId(bean.getId());

            if(bean.getId()>0){
                bean.setTinhName("");
                bean.setTinhId(bean.getId());
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
            
            bean.reset();
            bean.setDt_phanLoaiIds(null);
            bean.setNc_phanLoaiIds(null);
            bean.setHt_phanLoaiIds(null);
            bean.setCt_phanLoaiIds(null);
            bean.setNguonHoTroIds(null);
            bean.setHt_statusId(-1);            
            bean.setCheckEmpAll(0);
            request.setAttribute("timkiem",bean);
            target =anchor;    
        } else if(anchor.equals("_SELECT_TINH")||anchor.equals("_SELECT_DATATYPE")||anchor.equals("_SELECT_DISTRICT")){
            locationId =  bean.getTinhId();
            districtId = bean.getQhuyenId();
            if(locationId>0){
                bean.setTinhName("");
                FTinh beanCa=new FTinh();
                beanCa.setParentID(bean.getTinhId());
                List params = new ArrayList();
                    
                for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i=params.size()-1;i>-1;i--) {
                    bean.setTinhName(bean.getTinhName()+" - "+params.get(i));
                }
                
                districts = map_district.get(String.valueOf(locationId));
                communes = (districtId>0) ? map_commune.get(String.valueOf(bean.getQhuyenId())):new FBeans();
            } else {
                bean.setTinhName("");
            }
            
            String dataType = String.valueOf(bean.getDataType());            
            FEvent fEvent = new FEvent();
            fEvent.setLocationId(bean.getTinhId());
            FBeans events = new BEvent().getEventByLocationId(fEvent);
            
            bean.reset();
            request.setAttribute("events", events);
            request.setAttribute("dataType", dataType);            
            request.setAttribute("districts", districts);
            request.setAttribute("communes", communes);
            request.setAttribute("BNguonHoTros", beansNguonHoTro);
            request.setAttribute("BNguyenNhans", beansNguyenNhan);
            request.setAttribute("BDieuKiens", beansDieuKien);            
            request.setAttribute("BMucDos", beansMucDo);
            request.setAttribute("BDanTocs", beansDanToc);            
            request.setAttribute("BTinhTrangForms", beansTinhTrang);
            
            request.setAttribute("timkiem", bean);
            target = anchor;
        } else if(anchor.equals("_INFORMATION")){  
            target =anchor;
        } else if(anchor.equals(_PREPARED_CREATE)){
            BList boL = new BList();
            request.setAttribute("beans",boL.getAllListReportCreate(beanL));
            bean.setListName("");
            target =anchor;            
        } else if (anchor.equals(_CREATE)){        
            BList boL = new BList();
            bean.setEmpEstablised((int)me.getId()); 
            if (!beanL.getListName().equals("")){
                if (boL.addNew(beanL,errors)){
                    if (boL.addNewListEmp(bean,errors,1)){
                        errors.add("listErrors", new ActionError("errors.new"));                     
                    }
                }
            }else{
                errors.add("listErrors", new ActionError("listReport.createNameEdit"));  
            }
            request.setAttribute("beans",boL.getAllListReportCreate(beanL));
            bean.resetListReport(); 
            target=_CREATE;
        } else if (anchor.equals(_PREPARED_EDIT)){        
            BList boL = new BList();
            boL.getListReportById(beanL);          
            bean.setListCode(beanL.getListCode());
            bean.setListName(beanL.getListName());
            request.setAttribute("beans",boL.getAllListReport(beanL));
            target=_PREPARED_EDIT;            
        } else if (anchor.equals(_EDIT)){         
            BList boL = new BList();
            bean.setEmpEstablised((int)me.getId());
            if (!bean.getListName().equals("")){
                if ( boL.update(beanL)){                        
                    if (boL.addNewListEmpTemp(bean,errors,0)){
                        if (bean.getCheckEmpAll()==1){
                           boL.addUpdateListEmp(bean,errors,0);
                        }
                        errors.add("listErrors", new ActionError("errors.edit"));                          
                    }              
                 }
            }else{
                errors.add("listErrors", new ActionError("listReport.createNameEdit"));  
            }
            request.setAttribute("beans",boL.getAllListReportCreate(beanL));
            bean.resetListReport();
            target =_EDIT; 
            
        }else if (anchor.equals("_LIST_REPORT")){
            BList boL = new BList();           
            request.setAttribute("beans",boL.getAllListReport(beanL));
            bean.resetListReport();
            target="_LIST_REPORT";
            
        } else if (anchor.equals("_PREPARED")){        
            target=anchor;
        } else if (anchor.equals("_DETAIL")){
            target=anchor;
        } else if(anchor.equals(_REPORT) || "_REPORT_TEMP".equals(anchor) || "_REPORT_TEMP_XA".equals(anchor)){
            FReportKpiData fReportData = new FReportKpiData();
            String fileName = ""; 
            boolean exportTemp = ("_REPORT_TEMP".equals(anchor)||"_REPORT_TEMP_XA".equals(anchor)) ? true:false;            
            String sql = (String)request.getSession().getAttribute("SQL_REPORT");            
            List params = (ArrayList)request.getSession().getAttribute("params");            
            Map<String, String> mapParam = (Map<String, String>)request.getSession().getAttribute("mapParam");
            
            if (exportTemp) {
                if (bean.getDataType()==1) {
                    fileName = "_REPORT_TEMP_XA".equals(anchor)?IKeyDisability.REPORT_FILE_KPI_DIS_COMMUNE:IKeyDisability.REPORT_FILE_KPI_LIST_DIS;
                    bean.setDataType("_REPORT_TEMP_XA".equals(anchor)?Constant.KPI_DATA_DIS_COMMUNE:Constant.KPI_DATA_LIST_DIS);
                } else {
                    fileName = IKeyDisability.REPORT_FILE_KPI_SP;
                    bean.setDataType(Constant.KPI_DATA_LIST_PERSON_HOURS);
                }
                beans = bo.getReportAll(bean, sql, params, mapParam);
            } else {
                if (bean.getDataType()==Constant.KPI_DATA_VALUE) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_VAL;
                    fReportData.setDataType(Constant.KPI_DATA_VALUE);
                } else if (bean.getDataType()==Constant.KPI_DATA_DIS) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_DIS;                   
                    fReportData.setDataType(Constant.KPI_DATA_DIS);
                } else if (bean.getDataType()==Constant.KPI_DATA_PERSON) {
                    fileName = IKeyDisability.REPORT_FILE_KPI_PER;
                    fReportData.setDataType(Constant.KPI_DATA_PERSON);
                }
                beans = bo.getReportAll(bean, sql, params, mapParam);
            }
            
            bean.setStore(beans);
            String report = new DReportKpiData().ReportExcel(bean, seed, fileName);
            bean.download(report, fileName, null);
            bean.deleteFile(report);
            target=null;
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }  
}