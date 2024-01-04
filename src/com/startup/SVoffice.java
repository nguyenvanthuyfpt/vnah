package com.startup;


import com.bo.admin.menu.BMenu;
import com.bo.disability.BDisability;
import com.bo.disability.BIndicatorKpi;
import com.bo.disability.BPerson;
import com.bo.disability.categorys.BDangTat;
import com.bo.disability.categorys.BDanhgia;
import com.bo.disability.categorys.BDantoc;
import com.bo.disability.categorys.BDieuKien;
import com.bo.disability.categorys.BDoiTuong;
import com.bo.disability.categorys.BHotro;
import com.bo.disability.categorys.BIndicator;
import com.bo.disability.categorys.BMucdo;
import com.bo.disability.categorys.BNguonhotro;
import com.bo.disability.categorys.BNguyennhan;
import com.bo.disability.categorys.BObject;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.login.FLoginSystem;
import com.form.admin.menu.FMenu;
import com.form.disability.FDisability;
import com.form.disability.FPerson;
import com.form.disability.categorys.FIndicator;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportKpi;
import com.form.disability.search.FSearch;

import com.inf.disability.IKeyDisability;

import com.util.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;


public class SVoffice{
    
    final static Logger logger = Logger.getLogger(SVoffice.class);
    
    public boolean load(ActionForm form,ActionErrors errors) throws EException{
        boolean result = false;
        FLoginSystem bean = (FLoginSystem)form;
        HttpServletRequest request = bean.getRequest();
        if(bean.getLanguage()==1){
                bean.me.setLanguage("en");
                bean.me.setLocation("US");
        }
        try  {                      
            int[] menuActive = new int[4];
            FBeans menus =new BMenu().getAllMenuByUserId(bean.me.getId(),bean.getLanguage());
            for(int i=0;i<menus.size();i++){              
                FMenu beanMenu =(FMenu)menus.get(i); 
                if (beanMenu.getLevel()==0){
                    request.getSession().setAttribute(beanMenu.getMenuId(),beanMenu.getMinimize());
                }
                for(int k=0;k<beanMenu.getBeansMenu().size();k++){ 
                    FMenu beanMenu1 =(FMenu)beanMenu.getBeansMenu().get(k);
                        request.getSession().setAttribute(beanMenu1.getMenuId(),beanMenu1.getMinimize());
                }
              
                if (beanMenu.getMenuId().equals("01")){
                    menuActive[0] = i;
                }else if (beanMenu.getMenuId().equals("03")){
                    menuActive[1] = i;
                }else if (beanMenu.getMenuId().equals("02")){
                    menuActive[2] = i;
                }else if (beanMenu.getMenuId().equals("06")){
                     menuActive[3] = i;
                 }
            }
            request.getSession().setAttribute("BMenus",menus);
            request.getSession().setAttribute("menuActive",menuActive);
            FSearch beanTK = new FSearch();
            FBeans beans = new FBeans();
            FBeans districts = new FBeans();
            FBeans communes = new FBeans();
            FBeans beansTree = new FBeans(); 
            Map<String, FBeans> map_district = new HashMap<String, FBeans>();
            Map<String, FBeans> map_commune = new HashMap<String, FBeans>();
            Map<String, FTinh> map_location = new HashMap<String, FTinh>();
            beanTK.reset();
            
            int defaultLocation = 0;
            String defaultLocationName = "";
            
            // Tuyen
            String SQL="SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ORDER BY tinh_id";
            String subSQL = "SELECT level||'-'||tinh_id,parent_id,name FROM kpi_v_area WHERE parent_id = ?";
            String characters="/ ";
            String member="";
            if((bean.me.getDepartmentName()!=null)&&(!bean.me.getDepartmentName().equals(""))){
                member=bean.me.getDepartmentName();
            }
                        
            int userId = (int)bean.me.getId();
            if (userId==375||userId==667||userId==684) { // VNAH
                beans = new BTinh().getAllRecordByParentId(0);
                FTinh beanTinh = (FTinh)beans.get(0);
                defaultLocation = beanTinh.getId();
                defaultLocationName = beanTinh.getName();
                
                for (int i=0; i<beans.size(); i++) {
                    FTinh tinh = (FTinh)beans.get(i);
                    int locationId = tinh.getId();
                    if (tinh.getId()>0) {
                        districts = new BTinh().getAllRecordByParentId(locationId);
                        map_district.put(String.valueOf(locationId), districts);
                        for (int j=0; j< districts.size();j++) {
                            tinh = (FTinh) districts.get(j);
                            locationId = tinh.getId();
                            communes = new BTinh().getAllRecordByParentId(locationId);
                            map_commune.put(String.valueOf(locationId), communes);
                        }
                    }
                }
            } else {
                int locationId = 0;
                String userName = bean.me.getUsername();
                if (userName.startsWith("policy")) {
                    defaultLocation = 2748;
                } else if (userName.startsWith("dongnai")) {
                    defaultLocation = 537;
                } else if (userName.startsWith("binhphuoc")) {
                    defaultLocation = 2746;
                } else if (userName.startsWith("tayninh")) {
                    defaultLocation = 2747;
                } else if (userName.startsWith("baclieu")) {
                    defaultLocation = 2749;
                } else if (userName.startsWith("camau")) {
                    defaultLocation = 2750;
                } else if (userName.startsWith("quangnam")) {
                    defaultLocation = 2751;
                } else if (userName.startsWith("quangtri")) {
                    defaultLocation = 2752;
                }            
                bean.setId(defaultLocation);
                FTinh beanTinh = new FTinh();
                beanTinh.setId(defaultLocation);
                beanTinh = new BTinh().getRecordByID(beanTinh);
                beans.add(beanTinh);
                
                districts = new BTinh().getAllRecordByParentId(defaultLocation);
                map_district.put(String.valueOf(defaultLocation), districts);
                for (int j=0; j< districts.size();j++) {
                    FTinh tinh = (FTinh) districts.get(j);
                    locationId = tinh.getId();
                    communes = new BTinh().getAllRecordByParentId(locationId);
                    map_commune.put(String.valueOf(locationId), communes);
                }
            }
            
           // Location
            FBeans treeLocation = new FBeans();
            SQL = "SELECT tinh_id,parent_id,level||'#'||name FROM  kpi_v_area WHERE parent_id = ? order by tinh_id";
            characters = "/ ";
            member = "";
            treeLocation = new BTreeView().getTree(0, false, SQL, characters, "");
            for (int i=0;i<treeLocation.size();i++){
                FTinh tinh = (FTinh) treeLocation.get(i);
                map_location.put(String.valueOf(tinh.getId()), tinh);
            }
            
            // Object
            FBeans optObjects = new FBeans();
            SQL = "SELECT id,parent_id,name FROM kpi_object WHERE parent_id = ? order by id";
            characters = "/ ";
            member = "";
            optObjects = new BTreeView().getTree(0, false, SQL, characters, member);  
            
            // Menu
            FBeans menuObject = new FBeans();
            menuObject = new BObject().getAllRecord(0,0);
            
            FBeans menuIndicator = new FBeans();
            menuIndicator = new BIndicatorKpi().getAllRecord(0);
            
            // Indicator
            FBeans optIndicators = new FBeans();
            SQL = "SELECT id,parent_id,code||'-'||name FROM kpi_indicator WHERE parent_id = ? order by code";
            optIndicators = new BTreeView().getTree(0, false, SQL, characters, "");
            
            // Dang Tat
            FBeans beansDangTat = new FBeans();
            beansDangTat = new BDangTat().getAllRecord(0);
            
            // Nguyen nhan Khuyet tat
            String SQL_NGUYENNHAN = "SELECT nguyennhan_id,parent_id,name FROM DR_NGUYENNHAN WHERE parent_id = ?";
            FBeans beansNguyenNhan = new FBeans();
            beansNguyenNhan = new BTreeView().getTree(0, false, SQL_NGUYENNHAN, characters, "");
            
            // Muc do Khuyet tat
            String SQL_MUCDO = "SELECT mucdo_id, parent_id, name FROM dr_mucdo WHERE parent_id = ?";
            FBeans beansMucDo = new FBeans();
            beansMucDo = new BTreeView().getTree(0, false, SQL_MUCDO, characters, "");
            
            // Dieu kien Kinh te
            String SQL_DIEUKIEN = "SELECT dieukien_id,parent_id,name FROM DR_DIEUKIEN WHERE parent_id = ?";
            FBeans beansDieuKien = new FBeans();
            beansDieuKien = new BTreeView().getTree(0, false, SQL_DIEUKIEN, characters, "");
            request.setAttribute("BDieuKiens", beansDieuKien);            
            request.setAttribute("BSearchDieuKiens", new BDieuKien().getAllRecord(0));            
            
            // Nguon ho tro
            String SQL_NGUONHOTRO = "SELECT nguonhotro_id,parent_id,name FROM DR_NGUONHOTRO WHERE parent_id = ? ORDER BY position";
            FBeans beansNguonHTro = new FBeans();
            beansNguonHTro = new BTreeView().getTree(0, false, SQL_NGUONHOTRO, characters, "");
            
            // Nhu-cau
            FBeans beansNhuCau = new FBeans();
            String SQL_NHUCAU = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";        
            beansNhuCau = new BTreeView().getTreeList(0, SQL_NHUCAU, "", "");
            
            // Ho-tro
            FBeans beansHoTro = new FBeans();
            String SQL_HOTRO = "SELECT hotro_id,parent_id,name_htro FROM DR_HOTRO WHERE parent_id = ? order by order_by";        
            beansHoTro = new BTreeView().getTreeList(0, SQL_HOTRO, "", "");
            
            // Tinh trang
            String SQL_TINHTRANG = "SELECT tinhtrang_id,parent_id,name FROM DR_TINHTRANG WHERE parent_id = ?";
            FBeans beansTinhTrang = new FBeans();
            beansTinhTrang = new BTreeView().getTree(0, false, SQL_TINHTRANG, characters, "");
            
            // Ly Do
            String SQL_LYDO = "SELECT lydo_id,parent_id,name FROM DR_LYDO WHERE parent_id = ?";
            FBeans beansLyDo = new FBeans();
            beansLyDo = new BTreeView().getTree(0, false, SQL_LYDO, characters, "");
            
            // Dan Toc
            String SQL_DANTOC = "SELECT dantoc_id, parent_id, name FROM DR_DANTOC WHERE parent_id = ?";
            FBeans beansDanToc = new FBeans();
            beansDanToc = new BTreeView().getTree(0, false, SQL_DANTOC, characters, "");
            beanTK.setTinhName("");
            
            // Person
            FBeans acPersonBeans = new FBeans();
            FPerson fPerson = new FPerson();
            acPersonBeans = new BPerson().getPersonByParam(fPerson);
            
            // CountDown
            FBeans listDisCountDown = new FBeans();
            FBeans listDisCountDownLate = new FBeans();
            FDisability fDis = new FDisability();   

            fDis.setUserId(userId);
            fDis.setTinhId(defaultLocation);
            listDisCountDown = new BDisability().getCountdownDis(fDis);
            
            // CounDownLate
            fDis.setTypeCount(1);
            listDisCountDownLate = new BDisability().getCountdownDis(fDis);
            
            Map<String, String> map_nguonhotro = new BNguonhotro().get_map_nguonhotro();
            Map<String, String> map_dieukien = new BDieuKien().get_map_dieukien();
            Map<String, String> map_nguyennhan = new BNguyennhan().get_map_nguyennhan();
            Map<String, String> map_mucdo = new BMucdo().get_map_mucdo();
            Map<String, String> map_dantoc = new BDantoc().get_map_dantoc();
            Map<String, String> map_tinh = new BTinh().get_map_tinh();
            Map<String, String> map_dangtat = new BDangTat().get_map_dangtat();
            Map<String, String> map_hotro = new BHotro().get_map_hotro();
            Map<String, String> map_doituong = new BDoiTuong().get_map_doituong();
            Map<String, String> map_tinhtrang = new BDanhgia().get_map_tinhtrang();
            
            Map<String, String> map_quanhe = new HashMap<String, String>();
            map_quanhe.put("1", bean.ncrToString("&#212;ng"));
            map_quanhe.put("2", bean.ncrToString("B&#224;"));
            map_quanhe.put("3", bean.ncrToString("C&#244;"));
            map_quanhe.put("4", bean.ncrToString("D&#236;"));
            map_quanhe.put("5", bean.ncrToString("Ch&#250;"));
            map_quanhe.put("6", bean.ncrToString("B&#225;c"));
            map_quanhe.put("7", bean.ncrToString("Anh"));
            map_quanhe.put("8", bean.ncrToString("Ch&#7883;"));
            map_quanhe.put("9", bean.ncrToString("Em"));
            map_quanhe.put("10", bean.ncrToString("B&#7889;"));
            map_quanhe.put("11", bean.ncrToString("M&#7865;"));
            map_quanhe.put("12", bean.ncrToString("Ch&#7891;ng"));
            map_quanhe.put("13", bean.ncrToString("V&#7907;"));
            map_quanhe.put("14", bean.ncrToString("Kh&#225;c"));
            map_quanhe.put("15", bean.ncrToString("Con"));
            map_quanhe.put("16", bean.ncrToString("Ch&#225;u"));
            
            Map<String, String> map_nghenghiep = new HashMap<String, String>();
            map_nghenghiep.put("1", bean.ncrToString("C&#242;n nh&#7887;"));
            map_nghenghiep.put("2", bean.ncrToString("N&#7897;i tr&#7907;"));
            map_nghenghiep.put("3", bean.ncrToString("N&#244;ng nghi&#7879;p"));
            map_nghenghiep.put("4", bean.ncrToString("C&#244;ng nh&#226;n - vi&#234;n ch&#7913;c"));
            map_nghenghiep.put("5", bean.ncrToString("C&#244;ng nh&#226;n"));
            map_nghenghiep.put("6", bean.ncrToString("Th&#7907; th&#7911; c&#244;ng"));
            map_nghenghiep.put("7", bean.ncrToString("D&#7883;ch v&#7909;/Bu&#244;n b&#225;n"));
            map_nghenghiep.put("8", bean.ncrToString("Th&#7845;t nghi&#7879;p"));
            map_nghenghiep.put("9", bean.ncrToString("B&#7879;nh t&#7853;t kh&#244;ng l&#224;m g&#236; &#273;&#432;&#7907;c"));
            map_nghenghiep.put("10", bean.ncrToString("Kh&#225;c"));

            Map<String, String> map_kpi_combox = new HashMap<String, String>();
            Map<String, String> map_kpi_event_type = new HashMap<String, String>();
            Map<String, String> map_kpi_event_field = new HashMap<String, String>();
            Map<String, String> map_kpi_vote_result = new HashMap<String, String>();
            Map<String, String> map_kpi_phanloai_diadiem = new HashMap<String, String>();
            
            Map<String, String> map_hotro_kn_chitra = new HashMap<String, String>();
            Map<String, String> map_hotro_the_bhyt = new HashMap<String, String>();
            Map<String, String> map_hotro_sd_the = new HashMap<String, String>();
            Map<String, String> map_hotro_sd_the_phcn = new HashMap<String, String>();
            Map<String, String> map_hotro_danhgia = new HashMap<String, String>();
            
            request.getSession().setAttribute("LIST_NGUONHOTRO", beansNguonHTro);
            request.getSession().setAttribute("LIST_DIEUKIEN", beansDieuKien);
            request.getSession().setAttribute("LIST_NGUYENNHAN", beansNguyenNhan);
            request.getSession().setAttribute("LIST_DANGTAT", beansDangTat);
            request.getSession().setAttribute("LIST_MUCDO", beansMucDo);
            request.getSession().setAttribute("LIST_DANTOC", beansDanToc);
            request.getSession().setAttribute("LIST_TINHTRANG", beansTinhTrang);
            request.getSession().setAttribute("LIST_LYDO", beansLyDo);          
            request.getSession().setAttribute("LIST_NHUCAU", beansNhuCau);
            request.getSession().setAttribute("LIST_HOTRO", beansHoTro);
            request.getSession().setAttribute("LIST_PERSON", acPersonBeans);            
            request.getSession().setAttribute("LIST_DIS_COUNTDOWN", listDisCountDown);
            request.getSession().setAttribute("LIST_DIS_COUNTDOWN_LATE", listDisCountDownLate);
            request.getSession().setAttribute("MAP_DISTRICT", map_district);
            request.getSession().setAttribute("MAP_COMMUNE", map_commune);
            request.getSession().setAttribute("MAP_LOCATION", map_location);
            
            // Tree
            request.getSession().setAttribute("OPT_TREE_OBJECT", optObjects);
            request.getSession().setAttribute("OPT_TREE_INDICATOR", optIndicators);
            
            request.getSession().setAttribute("MENU_OBJECT", menuObject);
            request.getSession().setAttribute("MENU_INDICATOR", menuIndicator);
            
            request.getSession().setAttribute("BTreeTinhs", beans);
            request.getSession().setAttribute("BSearchTinhs", beans);            
            request.getSession().setAttribute("defaultLocation", defaultLocation);
            request.getSession().setAttribute("defaultLocationName", defaultLocationName);
            
            request.getSession().setAttribute("MAP_NGUONHOTRO", map_nguonhotro);
            request.getSession().setAttribute("MAP_DIEUKIEN", map_dieukien);
            request.getSession().setAttribute("MAP_NGUYENNHAN", map_nguyennhan);
            request.getSession().setAttribute("MAP_MUCDO", map_mucdo);
            request.getSession().setAttribute("MAP_DANTOC", map_dantoc);
            request.getSession().setAttribute("MAP_TINH", map_tinh);
            request.getSession().setAttribute("MAP_DANGTAT", map_dangtat);
            request.getSession().setAttribute("MAP_HOTRO", map_hotro);
            request.getSession().setAttribute("MAP_DOITUONG", map_doituong);
            request.getSession().setAttribute("MAP_TINHTRANG", map_tinhtrang);
            request.getSession().setAttribute("MAP_QUANHE", map_quanhe);
            request.getSession().setAttribute("MAP_NGHENGHIEP", map_nghenghiep);
                      
            // Kpi
            String val_combobox = bean.ncrToString(IKeyDisability.KPI_COMBOBOX);
            String[] arr_combobox = val_combobox.split("_");
            
            String val_event_type = bean.ncrToString(IKeyDisability.KPI_EVENT_TYPE);
            String[] arr_event_type = val_event_type.split("_");
            
            String val_event_field = bean.ncrToString(IKeyDisability.KPI_EVENT_FIELD);
            String[] arr_event_field = val_event_field.split("_");
            
            String val_vote_result = bean.ncrToString(IKeyDisability.KPI_VOTE_RESULT);
            String[] arr_vote_result = val_vote_result.split("_");
            
            String val_phanloai_diadiem = bean.ncrToString(IKeyDisability.KPI_PHANLOAI_DIADIEM);
            String[] arr_phanloai_diadiem = val_phanloai_diadiem.split("_");
            
            String val_hotro_kn_chitra = bean.ncrToString(IKeyDisability.KPI_HOTRO_KN_CHITRA);
            String[] arr_hotro_kn_chitra = val_hotro_kn_chitra.split("_");
            
            String val_hotro_the_bhyt = bean.ncrToString(IKeyDisability.KPI_HOTRO_THE_BHYT);
            String[] arr_hotro_the_bhyt = val_hotro_the_bhyt.split("_");
            
            String val_hotro_sd_the = bean.ncrToString(IKeyDisability.KPI_HOTRO_SD_THE);
            String[] arr_hotro_sd_the = val_hotro_sd_the.split("_");
            
            String val_hotro_sd_the_phcn = bean.ncrToString(IKeyDisability.KPI_HOTRO_SD_THE_PHCN);
            String[] arr_hotro_sd_the_phcn = val_hotro_sd_the_phcn.split("_");
            
            String val_hotro_danhgia = bean.ncrToString(IKeyDisability.KPI_HOTRO_DANHGIA);
            String[] arr_hotro_danhgia = val_hotro_danhgia.split("_");
            
            if (arr_combobox!=null && arr_combobox.length>0) {
                for(int i=0;i<arr_combobox.length;i++) {
                    String key = arr_combobox[i].substring(0, arr_combobox[i].indexOf("#"));
                    String value = arr_combobox[i].substring(arr_combobox[i].indexOf("#")+1);
                    map_kpi_combox.put(key, value);  
                }
            }
            
            if (arr_event_type!=null && arr_event_type.length>0) {
                for(int i=0;i<arr_event_type.length;i++) {
                    String key = arr_event_type[i].substring(0, arr_event_type[i].indexOf("#"));
                    String value = arr_event_type[i].substring(arr_event_type[i].indexOf("#")+1);
                    map_kpi_event_type.put(key, value);  
                }
            }
              
            if (arr_event_field!=null && arr_event_field.length>0) {
                for(int i=0;i<arr_event_field.length;i++) {
                    String key = arr_event_field[i].substring(0, arr_event_field[i].indexOf("#"));
                    String value = arr_event_field[i].substring(arr_event_field[i].indexOf("#")+1);
                    map_kpi_event_field.put(key, value);  
                }
            }
            
            if (arr_vote_result!=null && arr_vote_result.length>0) {
                for(int i=0;i<arr_vote_result.length;i++) {
                    String key = arr_vote_result[i].substring(0, arr_vote_result[i].indexOf("#"));
                    String value = arr_vote_result[i].substring(arr_vote_result[i].indexOf("#")+1);
                    map_kpi_vote_result.put(key, value);  
                }
            }
            
            if (arr_vote_result!=null && arr_vote_result.length>0) {
                for(int i=0;i<arr_vote_result.length;i++) {
                    String key = arr_phanloai_diadiem[i].substring(0, arr_phanloai_diadiem[i].indexOf("#"));
                    String value = arr_phanloai_diadiem[i].substring(arr_phanloai_diadiem[i].indexOf("#")+1);
                    map_kpi_phanloai_diadiem.put(key, value);  
                }
            }
            
            if (arr_hotro_kn_chitra!=null && arr_hotro_kn_chitra.length>0) {
                for(int i=0;i<arr_hotro_kn_chitra.length;i++) {
                    String key = arr_hotro_kn_chitra[i].substring(0, arr_hotro_kn_chitra[i].indexOf("#"));
                    String value = arr_hotro_kn_chitra[i].substring(arr_hotro_kn_chitra[i].indexOf("#")+1);
                    map_hotro_kn_chitra.put(key, value);  
                }
            }
            
            if (arr_hotro_the_bhyt!=null && arr_hotro_the_bhyt.length>0) {
                for(int i=0;i<arr_hotro_the_bhyt.length;i++) {
                    String key = arr_hotro_the_bhyt[i].substring(0, arr_hotro_the_bhyt[i].indexOf("#"));
                    String value = arr_hotro_the_bhyt[i].substring(arr_hotro_the_bhyt[i].indexOf("#")+1);
                    map_hotro_the_bhyt.put(key, value);  
                }
            }
              
            if (arr_hotro_sd_the!=null && arr_hotro_sd_the.length>0) {
                for(int i=0;i<arr_hotro_sd_the.length;i++) {
                    String key = arr_hotro_sd_the[i].substring(0, arr_hotro_sd_the[i].indexOf("#"));
                    String value = arr_hotro_sd_the[i].substring(arr_hotro_sd_the[i].indexOf("#")+1);
                    map_hotro_sd_the.put(key, value);  
                }
            }
              
            if (arr_hotro_sd_the_phcn!=null && arr_hotro_sd_the_phcn.length>0) {
                for(int i=0;i<arr_hotro_sd_the_phcn.length;i++) {
                    String key = arr_hotro_sd_the_phcn[i].substring(0, arr_hotro_sd_the_phcn[i].indexOf("#"));
                    String value = arr_hotro_sd_the_phcn[i].substring(arr_hotro_sd_the_phcn[i].indexOf("#")+1);
                    map_hotro_sd_the_phcn.put(key, value);  
                }
            }
            
            if (arr_hotro_danhgia!=null && arr_hotro_danhgia.length>0) {
                for(int i=0;i<arr_hotro_danhgia.length;i++) {
                    String key = arr_hotro_danhgia[i].substring(0, arr_hotro_danhgia[i].indexOf("#"));
                    String value = arr_hotro_danhgia[i].substring(arr_hotro_danhgia[i].indexOf("#")+1);
                    map_hotro_danhgia.put(key, value);  
                }
            }
            
            Map<String,Object> mapObject = new HashMap<String, Object>();
            mapObject.put("NKT_DOITUONG", map_doituong);
            mapObject.put("NKT_QUANHE", map_quanhe);
            mapObject.put("NKT_NGUONHOTRO", map_nguonhotro);
            mapObject.put("NKT_DIEUKIEN", map_dieukien);
            mapObject.put("NKT_NGUYENNHAN", map_nguyennhan);
            mapObject.put("NKT_MUCDO", map_mucdo);
            mapObject.put("NKT_DANTOC", map_dantoc);
            mapObject.put("NKT_TINH", map_tinh);
            mapObject.put("NKT_DANGTAT", map_dangtat);
            mapObject.put("NKT_HOTRO", map_hotro);
            mapObject.put("NKT_NGHENGHIEP", map_nghenghiep);
            
            mapObject.put("KPI_COMBOX", map_kpi_combox);
            mapObject.put("KPI_EVENT_TYPE", map_kpi_event_type);
            mapObject.put("KPI_EVENT_FIELD", map_kpi_event_field);
            mapObject.put("KPI_VOTE_RESULT", map_kpi_vote_result);
            mapObject.put("KPI_PHANLOAI_DIADIEM", map_kpi_phanloai_diadiem);
                        
            mapObject.put("KPI_HOTRO_KN_CHITRA", map_hotro_kn_chitra);
            mapObject.put("KPI_HOTRO_THE_BHYT", map_hotro_the_bhyt);
            mapObject.put("KPI_HOTRO_SD_THE", map_hotro_sd_the);
            mapObject.put("KPI_HOTRO_SD_THE_PHCN", map_hotro_sd_the_phcn);
            mapObject.put("KPI_HOTRO_DANHGIA", map_hotro_danhgia);
            
            request.getSession().setAttribute("MAP_OBJECT", mapObject);
                     
            if (beans!=null && beans.size()>0){
                FTinh beanTinh=(FTinh)beans.get(0);
                beanTK.setTinhId(beanTinh.getId());
            }

            if(beanTK.getTinhId()>0){
                FTinh beanCa=new FTinh();
                beanCa.setParentID(beanTK.getTinhId());
                List params =new ArrayList();
                
               for (int i=beanTK.getTinhId();i>0;i=beanCa.getParentID()) {
                   beanCa.setId(beanCa.getParentID());
                   beanCa=new BTinh().getRecordByID(beanCa);
                   params.add(beanCa.getName());
               }
                for (int i=params.size()-1;i>-1;i--) {
                    beanTK.setTinhName(beanTK.getTinhName()+" "+params.get(i));
                }
            }
            
            // Check Indicator has Permission
            String strIndIds = "";
            if (userId!=375) {
                FIndicator fIndicator = new FIndicator();
                fIndicator.setLocationId(beanTK.getTinhId());
                fIndicator.setYear(Utilities.getCurrentYear(new Date()));
                strIndIds = new BIndicator().getIndIds(fIndicator);                
                request.getSession().setAttribute("INDICATOR_IDS", strIndIds);
            } else {
                request.getSession().setAttribute("INDICATOR_IDS", strIndIds);
            }
            
            FReportKpi beanP = new FReportKpi();            
            beanP.setPeriodType("1");
            request.setAttribute("reportkpi", beanP);
            request.setAttribute("timkiem", beanTK);
            request.setAttribute("anchor", "04");
            request.setAttribute("subanchor", "04.01");;
            result = true;
        } catch (Exception ex)  {
            logger.info(ex.toString());
        }
        return result;
    }
}
