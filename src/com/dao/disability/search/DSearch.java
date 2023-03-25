package com.dao.disability.search;


import com.dao.disability.DSqlDisability;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.report.FReportKpiData;
import com.form.disability.search.FSearch;

import com.lib.AppConfigs;

import com.util.Constant;
import com.util.Utilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


public class DSearch extends DSqlDisability {

    public FBeans getAll(Connection cnn, FSeed seed) throws EException, SQLException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = null, reports = null;
        PreparedStatement prstm = null, prstmCount = null;
        ResultSet rs = null, rsCount = null;
        FSearch bean = (FSearch)seed;
                
        String SQL_SEARCH   = SELECT_ALL_FROM_TABLE_DISABILITY;        
        String SQL_COUNT    = COUNT_ALL_FROM_TABLE_DISABILITY;
        String SQL_REPORT   = "";
        
        DTinh dao = new DTinh();
        try {
            Map<String, String>  mapParam = new HashMap<String, String>();
            SQL_SEARCH += WHERE + PARAM_01 + EQUAL + PARAM_01;
            SQL_COUNT += WHERE + PARAM_01 + EQUAL + PARAM_01;
            
            if (bean.getYearReport()>0) {
                mapParam.put("yearReport", String.valueOf(bean.getYearReport()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + ">='" + bean.stringToSqlDate(Utilities.createFrom(bean.getYearReport()), true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + ">='" + bean.stringToSqlDate(Utilities.createFrom(bean.getYearReport()), true) + "'";
                
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + "<='" + bean.stringToSqlDate(Utilities.createTo(bean.getYearReport()), true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + "<='" + bean.stringToSqlDate(Utilities.createTo(bean.getYearReport()), true) + "'";
            }
            
            if (bean.getTinhId() > 0) {
                mapParam.put("locationId", String.valueOf(bean.getTinhId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + bean.getTinhId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + bean.getTinhId();
            }
            
            if (bean.getQhuyenId() > 0) {
                mapParam.put("districtId", String.valueOf(bean.getQhuyenId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_QHUYEN + EQUAL + bean.getQhuyenId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_QHUYEN + EQUAL + bean.getQhuyenId();
            }
            
            if (bean.getPxaId() > 0) {
                mapParam.put("communeId", String.valueOf(bean.getPxaId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_PXA + EQUAL + bean.getPxaId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_PXA + EQUAL + bean.getPxaId();
            }
            
            if (bean.getDieuKienId() > 0) {
                mapParam.put("dieukienId", String.valueOf(bean.getDieuKienId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_DIEUKIEN + EQUAL + bean.getDieuKienId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_DIEUKIEN + EQUAL + bean.getDieuKienId();
            }
            
            if (bean.getSex() >= 0) {
                mapParam.put("sex", String.valueOf(bean.getSex()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_SEX + EQUAL + bean.getSex();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_SEX + EQUAL + bean.getSex();
            }

            if (bean.getDioxin() >= 0) {
                mapParam.put("dacam", String.valueOf(bean.getDioxin()));
                if (bean.getChatdocDaCam() == 1) {
                    SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + EQUAL + bean.getDioxin();
                    SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + EQUAL + bean.getDioxin();
                } else {
                    SQL_SEARCH += AND + OPEN + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + EQUAL + bean.getDioxin() + 
                                  OR + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + IS_NULL + CLOSE;
                            
                    SQL_COUNT += AND + OPEN + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + EQUAL + bean.getDioxin() + 
                                 OR + TABLE_DISABILITYPEOPLE + STOP + NKT_DACAM + IS_NULL + CLOSE;        
                }                
            }
         
            if (!"".equals(bean.getNkt())) {
                mapParam.put("nktTen", bean.getNkt());
                SQL_SEARCH += AND + " UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_TEN + CLOSE + LIKE + "'" + PER_CENT + bean.getNkt().toUpperCase() + PER_CENT + "'";                        
                SQL_COUNT += AND + " UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_TEN +  CLOSE + LIKE + "'" + PER_CENT + bean.getNkt().toUpperCase() + PER_CENT + "'";
            }

            if (!"".equals(bean.getSoNha())) {
                mapParam.put("nktSoNha", bean.getSoNha());
                SQL_SEARCH += AND + "UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_SONHA + ")" + LIKE + "'" + PER_CENT + bean.getSoNha().toUpperCase() + PER_CENT + "'";
                SQL_COUNT += AND + "UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_SONHA + ")" + LIKE + "'" + PER_CENT + bean.getSoNha().toUpperCase() + PER_CENT + "'";
            }           
         

            if (!"".equals(bean.getChuanDoan())) {
                mapParam.put("nktChuanDoan", bean.getChuanDoan());
                SQL_SEARCH += AND + "UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_CHUANDOAN + ")" + LIKE + "'" + PER_CENT + bean.getChuanDoan().toUpperCase() + PER_CENT + "'";                
                SQL_COUNT += AND + "UPPER(" + TABLE_DISABILITYPEOPLE + STOP + NKT_CHUANDOAN + ")" + LIKE + "'" + PER_CENT + bean.getChuanDoan().toUpperCase() + PER_CENT + "'";
            }

            if (!"".equals(bean.getMa())) {
                mapParam.put("nktMa", bean.getMa());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_MA + EQUAL + "'" +bean.getMa() + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_MA + EQUAL + "'" +bean.getMa() + "'";
            }
            
            if (!"".equals(bean.getMaSo())) {
                mapParam.put("nktMaSo", bean.getMaSo());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_MASO + EQUAL + "'" +bean.getMaSo() + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_MASO + EQUAL + "'" +bean.getMaSo() + "'";
            }
            
            /*
            if (!"".equals(bean.getCmnd())) {
                mapParam.put("nktCmnd", bean.getCmnd());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_CMND + EQUAL + "'" + bean.getCmnd() + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_MA + EQUAL + "'"+ bean.getCmnd() + "'";
            }
            */
            
            if (!"".equals(bean.getPhoneNumber())) {
                mapParam.put("nktPhoneNumber", bean.getPhoneNumber());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DIENTHOAI + EQUAL + "'" + bean.getPhoneNumber() + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DIENTHOAI + EQUAL + "'"+ bean.getPhoneNumber() + "'";
            }
                   
            // BEGIN : TIM KIEM THEO NGAY SINH
            if (!"".equals(bean.getNgaySinhTu()) && bean.getNgaySinhTu()!=null) {
                mapParam.put("nktNgaySinhTu", bean.getNgaySinhTu());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGAYSINH + ">='" + bean.stringToSqlDate(bean.getNgaySinhTu(), true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGAYSINH + ">='" + bean.stringToSqlDate(bean.getNgaySinhTu(), true) + "'";
            }

            if (!"".equals(bean.getNgaySinhDen()) && bean.getNgaySinhDen()!=null) {
                mapParam.put("nktNgaySinhDen", bean.getNgaySinhDen());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGAYSINH + "<='" + bean.stringToSqlDate(bean.getNgaySinhDen(), true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGAYSINH + "<='" + bean.stringToSqlDate(bean.getNgaySinhDen(), true) + "'";
            }
            // END : TIM KIEM THEO NGAY SINH
            
            // BEGIN : TIM KIEM THEO NGAY CAP NHAT
            if(!"".equals(bean.getNgayCapNhatTu()) && bean.getNgayCapNhatTu()!=null){
                mapParam.put("nktNgayCapNhatTu", bean.getNgayCapNhatTu());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + ">='" + bean.stringToSqlDate(bean.getNgayCapNhatTu(),true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + ">='" + bean.stringToSqlDate(bean.getNgayCapNhatTu(),true) + "'";
            }

            if(!"".equals(bean.getNgayCapNhatDen()) && bean.getNgayCapNhatDen()!=null){
                mapParam.put("nktNgayCapNhatDen", bean.getNgayCapNhatDen());
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + "<='" + bean.stringToSqlDate(bean.getNgayCapNhatDen(),true) + "'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DATE_LAST_UPDATE + "<='" + bean.stringToSqlDate(bean.getNgayCapNhatDen(),true) + "'";
            }
            // END : TIM KIEM THEO NGAY CAP NHAT            
            
            // BEGIN : TIM KIEM THEO DANG TAT
            // Ngay-cap-nhat
            if (!"".equals(bean.getDt_ngayTu()) && bean.getDt_ngayTu()!=null) {
                mapParam.put("dtatNgayCapNhatTu", bean.getDt_ngayTu());
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_DATECREATE + ">='" + bean.stringToSqlDate(bean.getDt_ngayTu(), true) + "'";
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_DATECREATE + ">='" + bean.stringToSqlDate(bean.getDt_ngayTu(), true) + "'";         
            }

            if (!"".equals(bean.getDt_ngayDen()) && bean.getDt_ngayDen()!=null) {
                mapParam.put("dtatNgayCapNhatDen", bean.getDt_ngayDen());
                SQL_SEARCH +=  AND + TABLE_PHANLOAI + STOP + PHANLOAI_DATECREATE + "<='" + bean.stringToSqlDate(bean.getDt_ngayDen(), true) + "'";
                SQL_COUNT +=  AND + TABLE_PHANLOAI + STOP + PHANLOAI_DATECREATE + "<='" + bean.stringToSqlDate(bean.getDt_ngayDen(), true) + "'";          
            }
            
            // Thoi-diem-tai-kham
            if (!"".equals(bean.getDt_tkhamTu()) && bean.getDt_tkhamTu()!=null) {
                mapParam.put("dtatNgayTaiKhamtTu", bean.getDt_tkhamTu());
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_THOIDIEM_TAIKHAM + ">='" + bean.stringToSqlDate(bean.getDt_tkhamTu(), true) + "'";
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_THOIDIEM_TAIKHAM + ">='" + bean.stringToSqlDate(bean.getDt_tkhamTu(), true) + "'";         
            }
  
            if (!"".equals(bean.getDt_tkhamDen()) && bean.getDt_tkhamDen()!=null) {
                mapParam.put("dtatNgayTaiKhamtDen", bean.getDt_tkhamDen());
                SQL_SEARCH +=  AND + TABLE_PHANLOAI + STOP + PHANLOAI_THOIDIEM_TAIKHAM + "<='" + bean.stringToSqlDate(bean.getDt_tkhamDen(), true) + "'";
                SQL_COUNT +=  AND + TABLE_PHANLOAI + STOP + PHANLOAI_THOIDIEM_TAIKHAM + "<='" + bean.stringToSqlDate(bean.getDt_tkhamDen(), true) + "'";          
            }

            // Tinh-trang-khuyet-tat
            if (!"".equals(bean.getDt_tinhTrang()) && bean.getDt_tinhTrang()!=null) {
                mapParam.put("dtatTinhTrang", bean.getDt_tinhTrang());
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_RESON + LIKE + "'" + PER_CENT + bean.getDt_lydo().toUpperCase() + PER_CENT + "'";
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_RESON + LIKE + "'" + PER_CENT + bean.getDt_lydo().toUpperCase() + PER_CENT + "'";
            }
            
            // Phan-loai-dang-tat
            if ((bean.getDt_phanLoaiIds() != null) && (bean.getDt_phanLoaiIds().length >0)) {
                if(bean.getDt_phanLoaiIds().length>1){
                    SQL_SEARCH += AND + OPEN;
                    SQL_COUNT += AND + OPEN;
                    for (int i = 0; i < bean.getDt_phanLoaiIds().length; i++) {                        
                        if(i<bean.getDt_phanLoaiIds().length-1){                            
                            SQL_SEARCH += " strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[i]+"#')>0 " + OR;
                            SQL_COUNT += " strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[i]+"#')>0 " + OR;
                        } else {                            
                            SQL_SEARCH += " strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[i]+"#')>0 ";
                            SQL_COUNT += " strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[i]+"#')>0 ";    
                        }
                    }
                    SQL_SEARCH += CLOSE;
                    SQL_COUNT += CLOSE;
                } else {
                    SQL_SEARCH += AND + "strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[0]+"#')>0 ";
                    SQL_COUNT += AND + "strpos(dr_phanloai.dangtat_ids,'#"+bean.getDt_phanLoaiIds()[0]+"#')>0 ";
                }
            }
            
            // Nguyen-nhan-khuyet-tat
            if (bean.getNguyenNhanId()>0) {
                mapParam.put("dtatNguyenNhan", String.valueOf(bean.getNguyenNhanId()));
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_NGUYENNHAN_ID + EQUAL + bean.getNguyenNhanId();
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_NGUYENNHAN_ID + EQUAL + bean.getNguyenNhanId();
            }
            
            // Muc-do-khuyet-tat
            if (bean.getDt_mucDo()>0) {
                mapParam.put("dtatMucDo", String.valueOf(bean.getDt_mucDo()));
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_CAPDO + EQUAL + bean.getDt_mucDo();
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_CAPDO + EQUAL + bean.getDt_mucDo();
            }
            
            // Dia-diem
            if (bean.getDt_diaDiem()>0) {
                mapParam.put("dtatDiaDiem", String.valueOf(bean.getDt_diaDiem()));
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_DIADIEM + EQUAL + bean.getDt_diaDiem();
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_DIADIEM + EQUAL + bean.getDt_diaDiem();
            }
            // END : TIM KIEM THEO DANG TAT
            
            
            // BEGIN : TIM KIEM THEO HO TRO DA NHAN
            if (!"".equals(bean.getHt_capNhatTu()) && bean.getHt_capNhatTu()!=null) {
                mapParam.put("htroCreateFrom", bean.getHt_capNhatTu());
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getHt_capNhatTu(), true) + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getHt_capNhatTu(), true) + "'";
            }
            
            if (!"".equals(bean.getHt_capNhatDen()) && bean.getHt_capNhatDen()!=null) {
                mapParam.put("htroCreateTo", bean.getHt_capNhatDen());
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getHt_capNhatDen(), true) + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getHt_capNhatDen(), true) + "'";              
            }
            
            if ((!"".equals(bean.getHt_ngayTu()) && bean.getHt_ngayTu()!=null) && (!"".equals(bean.getHt_ngayDen()) && bean.getHt_ngayDen()!=null)) {
                mapParam.put("htroTuNgay", bean.getHt_ngayTu());
                mapParam.put("htroTuDen", bean.getHt_ngayDen());
                SQL_SEARCH += AND + "( sp" + STOP + HOTRO_DATEFORM + " between '" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "' ) ";
                SQL_COUNT += AND + " (sp" + STOP + HOTRO_DATEFORM + " between '" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "' ) ";
                
                //SQL_SEARCH += OR + " (pf.create_on " + " between '" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "' )) ";
                //SQL_COUNT += OR + "  (pf.create_on " + " between '" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "' )) ";
            }  else {
                if (!"".equals(bean.getHt_ngayTu()) && bean.getHt_ngayTu()!=null) {
                    mapParam.put("htroTuNgay", bean.getHt_ngayTu());
                    SQL_SEARCH += AND + "(sp" + STOP + HOTRO_DATEFORM + ">='" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "') ";
                    SQL_COUNT += AND + "(sp" + STOP + HOTRO_DATEFORM + ">='" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "') ";
                    
                    //SQL_SEARCH += OR + " (pf.create_on" + ">='" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "')) ";
                    //SQL_COUNT += OR + " (pf.create_on" + ">='" + bean.stringToSqlDate(bean.getHt_ngayTu(), true) + "')) ";
                }
                
                if (!"".equals(bean.getHt_ngayDen()) && bean.getHt_ngayDen()!=null) {
                    mapParam.put("htroTuDen", bean.getHt_ngayDen());
                    SQL_SEARCH += AND + "(sp" + STOP + HOTRO_DATETO + "<='" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "') ";
                    SQL_COUNT += AND + "(sp" + STOP + HOTRO_DATETO + "<='" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "') ";      
                    
                    //SQL_SEARCH += OR + " (pf.create_on" + "<='" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "')) ";
                    //SQL_COUNT += OR + " (pf.create_on" + "<='" + bean.stringToSqlDate(bean.getHt_ngayDen(), true) + "')) ";
                }                  
            }
            
            if ((!"".equals(bean.getHt_ngayTu()) && bean.getHt_ngayTu()!=null) || (!"".equals(bean.getHt_ngayDen()) && bean.getHt_ngayDen()!=null)) {
                mapParam.put("htroStatusId", "1");
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
            }

            if (!"".equals(bean.getHt_lydo()) && bean.getHt_lydo()!=null) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_NGUONHOTRO + LIKE + "'" + PER_CENT + bean.getHt_lydo().toUpperCase() + PER_CENT + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_NGUONHOTRO + LIKE + "'" + PER_CENT + bean.getHt_lydo().toUpperCase() + PER_CENT + "'";
            }            
            
            if (!"".equals(bean.getHt_phanLoaiIds()) && bean.getHt_phanLoaiIds()!=null) {
                  SQL_SEARCH += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IN + OPEN;
                  SQL_COUNT += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IN + OPEN;                     
                  String temp = Utilities.parseArr2Str(bean.getHt_phanLoaiIds(), ",");
                  String htDaNhan = temp.substring(1, temp.length()-1).replace(",", "','");
                  
                  SQL_SEARCH += "'" + htDaNhan + "'" + CLOSE;
                  SQL_COUNT += "'" + htDaNhan + "'" + CLOSE;
                  
                  SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
                  SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
            }
            
            
            // Ngay-Tai-Kham-Tu-Den
            if ((!"".equals(bean.getHt_taiKhamTu()) && bean.getHt_taiKhamTu()!=null) && (!"".equals(bean.getHt_taiKhamDen()) && bean.getHt_taiKhamDen()!=null)) {
                mapParam.put("htroTaiKhamFrom", bean.getHt_taiKhamTu());
                mapParam.put("htroTaiKhamTo", bean.getHt_taiKhamDen());
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + " between '" + bean.stringToSqlDate(bean.getHt_taiKhamTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_taiKhamDen(), true) + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + " between '" + bean.stringToSqlDate(bean.getHt_taiKhamTu(), true) + "' AND '" + bean.stringToSqlDate(bean.getHt_taiKhamDen(), true) + "'";
            } else {
                if (!"".equals(bean.getHt_taiKhamTu()) && bean.getHt_taiKhamTu()!=null) {
                    mapParam.put("htroTaiKhamFrom", bean.getHt_taiKhamTu());
                    SQL_SEARCH += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + ">='" + bean.stringToSqlDate(bean.getHt_taiKhamTu(), true) + "'";
                    SQL_COUNT += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + ">='" + bean.stringToSqlDate(bean.getHt_taiKhamTu(), true) + "'";
                }
                
                if (!"".equals(bean.getHt_taiKhamDen()) && bean.getHt_taiKhamDen()!=null) {
                    mapParam.put("htroTaiKhamTo", bean.getHt_taiKhamDen());
                    SQL_SEARCH += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + "<='" + bean.stringToSqlDate(bean.getHt_taiKhamDen(), true) + "'";
                    SQL_COUNT += AND + "sp" + STOP + HOTRO_THOIDIEM_TAIKHAM + "<='" + bean.stringToSqlDate(bean.getHt_taiKhamDen(), true) + "'";              
                }
            }
            
            if (bean.getHt_diaDiemTK()>0) {
                mapParam.put("htroDiaDiemTK", String.valueOf(bean.getHt_diaDiemTK()));
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DIADIEM + "=" + bean.getHt_diaDiemTK();
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DIADIEM + "=" + bean.getHt_diaDiemTK();
            }
            
            
            /*
            else {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IS + NOT_NULL;
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IS + NOT_NULL;
            }
            */
            
            // END : TIM KIEM THEO HO TRO DA NHAN
                        
            // BEGIN : TIM KIEM THEO NHU CAU HO TRO
            if (!"".equals(bean.getNgayLapTu()) && bean.getNgayLapTu()!=null) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getNgayLapTu(), true) + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DATECREATE + ">='" + bean.stringToSqlDate(bean.getNgayLapTu(), true) + "'";
            }
            
            if (!"".equals(bean.getNgayLapDen()) && bean.getNgayLapDen()!=null) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DATECREATE + "<='" + bean.stringToSqlDate(bean.getNgayLapDen(), true) + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DATECREATE + "<='" + bean.stringToSqlDate(bean.getNgayLapDen(), true) + "'";
            }
            
            if((bean.getNc_phanLoaiIds() != null) && (!"".equals(bean.getNc_phanLoaiIds()))){
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IN + OPEN;
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DM_HOTRO_IDS + IN + OPEN;                     
                String temp = Utilities.parseArr2Str(bean.getNc_phanLoaiIds(), ",");
                String ncHoTro = temp.substring(1, temp.length()-1).replace(",", "','");
                
                SQL_SEARCH += "'" + ncHoTro + "'" + CLOSE;
                SQL_COUNT += "'" + ncHoTro + "'" + CLOSE;
                
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "0";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "0";                      
            }
            
            if (!"".equals(bean.getNcauDungCuKhac()) && bean.getNcauDungCuKhac()!=null) {
                SQL_SEARCH += AND + OPEN + "sp" + STOP + HOTRO_PHAUTHUAT_KHAC + LIKE + "'" + PER_CENT + bean.getNcauDungCuKhac() + PER_CENT + "'";
                SQL_COUNT += AND + OPEN + "sp" + STOP + HOTRO_PHAUTHUAT_KHAC + LIKE + "'" + PER_CENT + bean.getNcauDungCuKhac() + PER_CENT + "'";
                
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "0" + CLOSE;
                SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "0" + CLOSE;
            }
            
            if (!"".equals(bean.getDungCuKhac()) && bean.getDungCuKhac()!=null) {
                SQL_SEARCH += AND + OPEN + "sp" + STOP + HOTRO_PHAUTHUAT_KHAC + LIKE + "'" + PER_CENT + bean.getDungCuKhac() + PER_CENT + "'";
                SQL_COUNT += AND + OPEN + "sp" + STOP + HOTRO_PHAUTHUAT_KHAC + LIKE + "'" + PER_CENT + bean.getDungCuKhac() + PER_CENT + "'";
                
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1" + CLOSE;
                SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1" + CLOSE;
            }
            
            if (!"".equals(bean.getMoTaDCTG()) && bean.getMoTaDCTG()!=null) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_DUNGCU_KHAC + LIKE + "'" + PER_CENT + bean.getMoTaDCTG() + PER_CENT + "'";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_DUNGCU_KHAC + LIKE + "'" + PER_CENT + bean.getMoTaDCTG() + PER_CENT + "'";             
            }
            
            // END : TIM KIEM THEO NHU CAU HO TRO
            
            // BEGIN : TIM KIEM THEO NGUON HO TRO (CUSTOM)
            /*
            if (!"".equals(bean.getNguonHoTro()) && bean.getNguonHoTro()!=null) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + IN + OPEN + bean.getNguonHoTro() + CLOSE + AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
                SQL_COUNT += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + IN + OPEN + bean.getNguonHoTro() + CLOSE + AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";                
            }
            */
            
            if((bean.getNguonHoTroIds() != null) && (bean.getNguonHoTroIds().length > 0)){
                if(bean.getNguonHoTroIds().length>0){
                    SQL_SEARCH += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + IN + OPEN;
                    SQL_COUNT += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + IN + OPEN;                     
                    String temp = Utilities.parseArr2Str(bean.getNguonHoTroIds(), ",");                 
                    String nguonHT = temp.substring(1, temp.length()-1);
                    SQL_SEARCH += nguonHT + CLOSE;
                    SQL_COUNT += nguonHT + CLOSE;
                    
                    SQL_SEARCH += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";
                    SQL_COUNT += AND + "sp" + STOP + HOTRO_STATUS_ID + EQUAL + "1";       
                }
            } 
            
            // DAN TOC
            if (bean.getDanTocId()>0){
                mapParam.put("nktDanToc", String.valueOf(bean.getDanTocId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DANTOC_ID + EQUAL + bean.getDanTocId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DANTOC_ID + EQUAL + bean.getDanTocId();
            }
            
            // Trang-Thai
            if (bean.getStatusId()>-1){
                mapParam.put("nktStatusId", String.valueOf(bean.getStatusId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_TRANGTHAI + EQUAL + bean.getStatusId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_TRANGTHAI + EQUAL + bean.getStatusId();
            }
            
            if (bean.getDuAnId()>-1) {
                mapParam.put("nktDuAnId", String.valueOf(bean.getDuAnId()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DUAN + EQUAL + bean.getDuAnId();
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_DUAN + EQUAL + bean.getDuAnId();
            }
            
            // QUAN LY CA
            if (bean.getQuanLyCa() > 0) {
                if (bean.getQuanLyCa()==1){
                    SQL_SEARCH += AND + TABLE_RANK + STOP + RANK_DANHGIA_IDS + LIKE + "'%#141#%'";
                    SQL_COUNT += AND + TABLE_RANK + STOP + RANK_DANHGIA_IDS + LIKE + "'%#141#%'";
                } else {
                    SQL_SEARCH += AND + TABLE_RANK + STOP + RANK_DANHGIA_IDS + LIKE + "'%#85#%'";
                    SQL_COUNT += AND + TABLE_RANK + STOP + RANK_DANHGIA_IDS + LIKE + "'%#85#%'";
                }
                
                if(bean.getKyBC()>0){
                    SQL_SEARCH += " and dr_rank.datecreate>=fn_firstdate_of_month('" + bean.getKyBC()+"/"+bean.getNamBC() +"') " +
                                    " and dr_rank.datecreate<=fn_lastdate_of_month('" + bean.getKyBC()+"/"+bean.getNamBC()+"') ";
                    
                    SQL_COUNT += " and dr_rank.datecreate>=fn_firstdate_of_month('" + bean.getKyBC()+"/"+bean.getNamBC() +"') " +
                                    " and dr_rank.datecreate<=fn_lastdate_of_month('" + bean.getKyBC()+"/"+bean.getNamBC()+"') ";
                } else if (bean.getKyBC()==0) {
                    String from = "01/" + bean.getNamBC();
                    String to = "12/" + bean.getNamBC();
                    SQL_SEARCH += " and dr_rank.datecreate>=fn_firstdate_of_month('"+from +"') " +
                                    " and dr_rank.datecreate<=fn_lastdate_of_month('"+to+"') ";
                    
                    SQL_COUNT += " and dr_rank.datecreate>=fn_firstdate_of_month('"+from+"') " +
                                    " and dr_rank.datecreate<=fn_lastdate_of_month('"+to+"') ";
                }
            }
            
            // NGHE NGHIEP HIEN TAI
            if (bean.getNgheNghiepHT()>0) {
                mapParam.put("nktNgheNghiep", String.valueOf(bean.getNgheNghiepHT()));
                SQL_SEARCH += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGHE_NGHIEP_HT + EQUAL +  "'" + bean.getNgheNghiepHT() +"'";
                SQL_COUNT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_NGHE_NGHIEP_HT + EQUAL +  "'" + bean.getNgheNghiepHT() +"'";
            }
            
            // MUC DO KHUYET TAT
            if (bean.getMucDo() > 0) {
                mapParam.put("dtatMucDoc", String.valueOf(bean.getMucDo()));
                SQL_SEARCH += AND + TABLE_PHANLOAI + STOP + PHANLOAI_CAPDO + EQUAL + bean.getMucDo();                
                SQL_COUNT += AND + TABLE_PHANLOAI + STOP + PHANLOAI_CAPDO + EQUAL + bean.getMucDo();
            }
            
            // NGUON HO TRO
            if (bean.getNguonHotroId()>0) {
                SQL_SEARCH += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + EQUAL + bean.getNguonHotroId();
                SQL_COUNT += AND + "sp" + STOP + HOTRO_NGUONHOTRO_ID + EQUAL + bean.getNguonHotroId();                
            }
            
            SQL_REPORT = SQL_SEARCH;                       
            if(bean.getPageIndex()>0)
                SQL_SEARCH += " LIMIT 20 OFFSET " + (bean.getPageIndex()-1)*AppConfigs.APP_ROWS_VIEW;
            else 
                SQL_SEARCH += " LIMIT 20 OFFSET 0 ";

            int count = 0;
            prstmCount = prepareStatement(cnn, SQL_COUNT, null);            
            rsCount = prstmCount.executeQuery();    // Count
            
            if (rsCount != null && rsCount.next()) {
                count = rsCount.getInt("total");
            }
            
            String sql = "SELECT a.* FROM (" + SQL_SEARCH + ") a ORDER BY a.id_tinh, a.id";
            
            // Search
            prstm = prepareStatement(cnn, sql, null);
            rs = prstm.executeQuery();
            beans = new FBeans();
            beans.setTotalRows(count);
            bean.setTotalResult(count);
            beans.setPageIndex(bean.getPageIndex());
            
            int i = 0,j = 0;            
            while (rs != null && rs.next() && i<AppConfigs.APP_ROWS_VIEW) {                
                i++;
                bean = new FSearch();
                bean = getInformation(rs, true);                
                beans.add(bean);
            }
            
            // Store param search to Session
            HttpSession session = seed.getRequest().getSession();
            session.setAttribute("SQL_REPORT", SQL_REPORT);            
            session.setAttribute("mapParam", mapParam);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closeResultSet(rsCount);            
            closePreparedStatement(prstm);
            closePreparedStatement(prstmCount);            
        }
        return beans;
    }
    
    public FBeans getKpiValue(Connection cnn, FSeed seed) throws EException, SQLException {
        final String LOCATION = this.toString() + "getKpiValue()";
        FBeans beans = null, reports = null;
        PreparedStatement prstm = null, prstmCount = null;
        ResultSet rs = null, rsCount = null;
        FSearch bean = (FSearch)seed;
                
        String SQL_SEARCH   = "SELECT hdr.obj_id, hdr.ind_id, dtl.id, dtl.data_id, dtl.period period_type, dtl.create_date, u.username, u.fullname, dtl.location_id, a.name as location, " +
                              "CASE WHEN dtl.period = 0 THEN LPAD(dtl.month::text, 2, '0') || '/' || dtl.year \n" + 
                              "WHEN dtl.period = 1 THEN LPAD(dtl.quarter::text, 2, '0') || '/' || dtl.year \n" +
                              "ELSE '' || dtl.year END as period, \n" +
                              
                              "CASE WHEN dtl.period = 0 THEN '' || dtl.target_m \n" + 
                              "WHEN dtl.period = 1 THEN '' || dtl.target_q \n" +
                              "ELSE '' || dtl.target_y END as target, \n" +
                              
                              "dtl.actual, dtl.month, dtl.quarter, dtl.year, dtl.activity, dtl.time, \n" +
                              "dtl.tw, dtl.ttp, dtl.qhu, dtl.pxa, dtl.note, \n" +
                              "obj.name ||' - '|| obj.description obj_desc, ind.code || ' - ' || ind.name ind_desc \n" +
                              "FROM kpi_data_hdr hdr \n" + 
                              "INNER JOIN kpi_data_dtl dtl ON hdr.id=dtl.data_id \n" + 
                              "INNER JOIN kpi_indicator ind ON hdr.ind_id=ind.id AND ind.type=0 \n" +
                              "INNER JOIN kpi_object obj on hdr.obj_id=obj.id \n" +
                              "LEFT JOIN users u ON dtl.user_id=u.user_id \n" +
                              "LEFT JOIN dr_area a ON dtl.location_id = a.tinh_id ";                             
        
        String SQL_COUNT    = "SELECT COUNT(DISTINCT dtl.ID) FROM kpi_data_hdr hdr \n" + 
                              "INNER JOIN kpi_data_dtl dtl ON hdr.id=dtl.data_id \n" + 
                              "INNER JOIN kpi_indicator ind ON hdr.ind_id=ind.id AND ind.type=0 \n" +
                              "INNER JOIN kpi_object obj on hdr.obj_id=obj.id \n" +
                              "LEFT JOIN users u ON dtl.user_id=u.user_id \n" +
                              "LEFT JOIN dr_area a ON dtl.location_id = a.tinh_id"; 
        
        String SQL_REPORT   = "";        
        DTinh dao = new DTinh();
        try {
            List params = new ArrayList();
            SQL_SEARCH += WHERE + PARAM_01 + EQUAL + PARAM_01;
            SQL_COUNT += WHERE + PARAM_01 + EQUAL + PARAM_01;
            
            if (bean.getTinhId()>0) {
                params.add(bean.getTinhId());
                SQL_SEARCH += " AND hdr.location_id=?";
                SQL_COUNT += " AND hdr.location_id=?";
            }
            
            if (bean.getObjId()>0) {
                params.add(bean.getObjId());
                SQL_SEARCH += " AND hdr.obj_id=?";
                SQL_COUNT += " AND hdr.obj_id=?";
            }
            
            if (bean.getIndId() > 0) {
                params.add(bean.getIndId());
                SQL_SEARCH += " AND hdr.ind_id=?";
                SQL_COUNT += " AND hdr.ind_id=?";
            }
            
            if (bean.getYearReport()>0) {
                SQL_SEARCH += " AND hdr.create_date >= ?";
                SQL_COUNT += " AND hdr.create_date >= ?";
                Date dateNgayCapNhapTu = bean.stringToSqlDate(Utilities.createFrom(bean.getYearReport()));
                params.add(dateNgayCapNhapTu);
                
                SQL_SEARCH += " AND hdr.create_date <= ?";
                SQL_COUNT += " AND hdr.create_date <= ?";
                Date dateNgayCapNhatDen = bean.stringToSqlDate(Utilities.createTo(bean.getYearReport()));
                params.add(dateNgayCapNhatDen);              
            }
            
            if (!"".equals(bean.getNgayCapNhatTu()) && bean.getNgayCapNhatTu()!=null) {
                SQL_SEARCH += " AND hdr.create_date >= ?";
                SQL_COUNT += " AND hdr.create_date >= ?";
                Date dateNgayCapNhapTu = bean.stringToSqlDate(bean.getNgayCapNhatTu(), true);
                params.add(dateNgayCapNhapTu);
            }
            
            if (!"".equals(bean.getNgayCapNhatDen()) && bean.getNgayCapNhatDen()!=null) {
                SQL_SEARCH += " AND hdr.create_date <= ?";
                SQL_COUNT += " AND hdr.create_date <= ?";
                Date dateNgayCapNhatDen = bean.stringToSqlDate(bean.getNgayCapNhatDen(), true);
                params.add(dateNgayCapNhatDen);
            }
            
            if (bean.getDataType()==0) {
                SQL_SEARCH +=  " ORDER BY hdr.location_id,dtl.period, dtl.month, dtl.quarter";
            } else {
                SQL_SEARCH +=  " ORDER BY hdr.location_id,dtl.create_date DESC";  
            }
                        
            SQL_REPORT = SQL_SEARCH; 
            
            if(bean.getPageIndex()>0)
                SQL_SEARCH += " LIMIT 20 OFFSET " + ((bean.getPageIndex()-1)*AppConfigs.APP_ROWS_VIEW);
            else 
                SQL_SEARCH += " LIMIT 20 OFFSET 0 ";
  
            int count = 0;
            prstmCount = prepareStatement(cnn, SQL_COUNT, params);            
            rsCount = prstmCount.executeQuery();    // Count
            
            if (rsCount != null && rsCount.next()) {
                count = rsCount.getInt(PARAM_01);
            }               
            
            // Search
            prstm = prepareStatement(cnn, SQL_SEARCH, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
            beans.setTotalRows(count);
            bean.setTotalResult(count);
            beans.setPageIndex(bean.getPageIndex());
            
            int i = 0;            
            while (rs != null && rs.next() && i<AppConfigs.APP_ROWS_VIEW) {                
                i++;
                bean = new FSearch();
                bean = getInforKpiValue(rs);                              
                beans.add(bean);
            }
            
            // Store param search to Session
            HttpSession session = seed.getRequest().getSession();
            session.setAttribute("SQL_REPORT", SQL_REPORT);
            session.setAttribute("params", params);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closeResultSet(rsCount);            
            closePreparedStatement(prstm);
            closePreparedStatement(prstmCount);            
        }
        return beans;
    }
  
    public FBeans getKpiPerson(Connection cnn, FSeed seed) throws EException, SQLException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = null, reports = null;
        PreparedStatement prstm = null, prstmCount = null;
        ResultSet rs = null, rsCount = null;
        FSearch bean = (FSearch)seed;
                
        String SQL_SEARCH   = "SELECT a.* FROM (" +
                              "SELECT DISTINCT ON (m.per_id) per_id, p.create_date, u.username, u.fullname, p.location_id, a.name location_name, p.code, p.name, p.sex, p.agency, p.title, p.address, p.contact " +
                              "FROM kpi_data_per m \n" + 
                              "LEFT JOIN kpi_person p ON m.per_id = p.id \n" + 
                              "LEFT JOIN kpi_data_hdr hdr ON m.data_id=hdr.id \n" +                               
                              "LEFT JOIN users u ON p.user_id=u.user_id \n" +
                              "LEFT JOIN dr_area a ON p.location_id = a.tinh_id ";                              
        
        String SQL_COUNT    = "SELECT COUNT(DISTINCT m.per_id) \n" + 
                              "FROM kpi_data_per m \n" + 
                              "LEFT JOIN kpi_person p ON m.per_id = p.id \n" + 
                              "LEFT JOIN kpi_data_hdr hdr ON m.data_id=hdr.id \n" +                               
                              "LEFT JOIN users u ON p.user_id=u.user_id \n" +
                              "LEFT JOIN dr_area a ON p.location_id = a.tinh_id ";  
        
        String SQL_REPORT   = "";        
        DTinh dao = new DTinh();
        try {
            List params = new ArrayList();
            Map<String, String>  mapParam = new HashMap<String, String>();
            SQL_SEARCH += WHERE + PARAM_01 + EQUAL + PARAM_01;
            SQL_COUNT += WHERE + PARAM_01 + EQUAL + PARAM_01;
            
            
            SQL_SEARCH += " AND m.hours=0";
            SQL_COUNT += " AND m.hours=0";
            
            if (bean.getTinhId()>0) {
                params.add(bean.getTinhId());
                mapParam.put("locationId", String.valueOf(bean.getTinhId()));
                SQL_SEARCH += " AND p.location_id=?";
                SQL_COUNT += " AND p.location_id=?";
            }
            
            if (!"".equals(bean.getPerName()) && bean.getPerName()!=null) {
                mapParam.put("name", String.valueOf(bean.getPerName()));
                SQL_SEARCH += " AND UPPER(p.name) like '%" + bean.getPerName().toUpperCase() + "%'";
                SQL_COUNT += " AND UPPER(p.name) like '%" + bean.getPerName().toUpperCase() + "%'";
            }
            
            if (!"".equals(bean.getPerTitle()) && bean.getPerTitle()!=null) {
                mapParam.put("title", String.valueOf(bean.getPerTitle()));
                SQL_SEARCH += " AND UPPER(p.title) like '%" + bean.getPerTitle().toUpperCase() + "%'";
                SQL_COUNT += " AND UPPER(p.title) like '%" + bean.getPerTitle().toUpperCase() + "%'";
            }
            
            if (!"".equals(bean.getPerAgency()) && bean.getPerAgency()!=null) {                
                mapParam.put("agency", String.valueOf(bean.getPerAgency()));
                SQL_SEARCH += " AND UPPER(p.agency) like '%" + bean.getPerAgency().toUpperCase() + "%'";
                SQL_COUNT += " AND UPPER(p.agency) like '%" + bean.getPerAgency().toUpperCase() + "%'";
            }
            
            if (!"".equals(bean.getPerAddress()) && bean.getPerAddress()!=null) {                
                mapParam.put("address", String.valueOf(bean.getPerAddress()));
                SQL_SEARCH += " AND UPPER(p.address) like '%" + bean.getPerAddress().toUpperCase() + "%'";
                SQL_COUNT += " AND UPPER(p.address) like '%" + bean.getPerAddress().toUpperCase() + "%'";
            }
            
            if (bean.getPerSex()>-1) {
                params.add(bean.getPerSex());
                mapParam.put("sex", String.valueOf(bean.getPerSex()));
                SQL_SEARCH += " AND p.sex=?";
                SQL_COUNT += " AND p.sex=?";
            }
            
            if (bean.getObjId()>0) {
                params.add(bean.getObjId());
                mapParam.put("objId", String.valueOf(bean.getObjId()));
                SQL_SEARCH += " AND hdr.obj_id=?";
                SQL_COUNT += " AND hdr.obj_id=?";
            }
            
            if (bean.getEventId()>0) {
                params.add(bean.getEventId());
                mapParam.put("eventId", String.valueOf(bean.getEventId()));
                SQL_SEARCH += " AND m.event_id=?";
                SQL_COUNT += " AND m.event_id=?";
            }
            
            if (bean.getYearReport()>0) {
                SQL_SEARCH += " AND m.create_date >= ?";
                SQL_COUNT += " AND m.create_date >= ?";
                
                Date dateNgayCapNhatTu = bean.stringToSqlDate(Utilities.createFrom(bean.getYearReport()));
                params.add(dateNgayCapNhatTu);
                mapParam.put("createFrom", bean.getNgayCapNhatTu());
                
                SQL_SEARCH += " AND m.create_date <= ?";
                SQL_COUNT += " AND m.create_date <= ?";
                Date dateNgayCapNhatDen = bean.stringToSqlDate(Utilities.createTo(bean.getYearReport()));
                params.add(dateNgayCapNhatDen);
                mapParam.put("createTo", bean.getNgayCapNhatDen());
            }
            
            if (!"".equals(bean.getNgayCapNhatTu()) && bean.getNgayCapNhatTu()!=null) {
                SQL_SEARCH += " AND m.create_date >= ?";
                SQL_COUNT += " AND m.create_date >= ?";
                
                Date dateNgayCapNhatTu = bean.stringToSqlDate(bean.getNgayCapNhatTu(), true);
                params.add(dateNgayCapNhatTu);
                mapParam.put("createFrom", bean.getNgayCapNhatTu());
            }
            
            if (!"".equals(bean.getNgayCapNhatDen()) && bean.getNgayCapNhatDen()!=null) {
                SQL_SEARCH += " AND m.create_date <= ?";
                SQL_COUNT += " AND m.create_date <= ?";
                Date dateNgayCapNhatDen = bean.stringToSqlDate(bean.getNgayCapNhatDen(), true);
                params.add(dateNgayCapNhatDen);
                mapParam.put("createTo", bean.getNgayCapNhatDen());
            } 
            
            SQL_SEARCH += " ) a ";
            SQL_SEARCH += " ORDER BY a.location_id, a.name";
            SQL_REPORT = SQL_SEARCH;           
            
            if(bean.getPageIndex()>0)
                SQL_SEARCH += " LIMIT 20 OFFSET " + ((bean.getPageIndex()-1)*AppConfigs.APP_ROWS_VIEW);
            else 
                SQL_SEARCH += " LIMIT 20 OFFSET 0 ";
    
            int count = 0;
            prstmCount = prepareStatement(cnn, SQL_COUNT, params);            
            rsCount = prstmCount.executeQuery();
            
            if (rsCount != null && rsCount.next()) {
                count = rsCount.getInt(PARAM_01);
            }               
            
            // Search
            prstm = prepareStatement(cnn, SQL_SEARCH, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
            beans.setTotalRows(count);
            bean.setTotalResult(count);
            beans.setPageIndex(bean.getPageIndex());
            int i=0;
            while (rs != null && rs.next() && i<AppConfigs.APP_ROWS_VIEW) {
                  i++;
                  bean = new FSearch();
                  bean = getInforKpiPerson(rs);                         
                  beans.add(bean);
            }
            
            // Store param search to Session
            HttpSession session = seed.getRequest().getSession();
            session.setAttribute("SQL_REPORT", SQL_REPORT);
            session.setAttribute("params", params);
            session.setAttribute("mapParam", mapParam);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closeResultSet(rsCount);            
            closePreparedStatement(prstm);
            closePreparedStatement(prstmCount);            
        }
        return beans;
    }

    
    public String getTuNgay(FSearch bean){
        String tuNgay = "";        
        if (bean.getKyBC()==1||bean.getKyBC()==0) {
            tuNgay = "'01/01/" + bean.getNamBC() + "'";
        } else {            
            tuNgay = "'01/07/" + bean.getNamBC() + "'";                   
        }
        return tuNgay;
    }
    
    public String getDenNgay(FSearch bean){
        String denNgay = "";
        if (bean.getKyBC()==1) {
            denNgay = "'01/07/" + bean.getNamBC() + "'";
        } else if (bean.getKyBC()==2||bean.getKyBC()==0) {
            int nam = bean.getNamBC() + 1;
            denNgay = "'01/01/" + nam + "'";
        }
        return denNgay;
    }
    
    public FBeans getReportAll(Connection cnn, FSeed seed, String SQL_REPORT, List params, Map<String, String> mapParam) throws EException {
        final String LOCATION = this.toString() + "getReport()";
        String SQL_REPORT_DIS = "SELECT * FROM dr_report_param a WHERE 1=1 ";
        String SQL_REPORT_DIS_COMMUNE = "select\n" + 
                                        "	krt.*,\n" +
                                        " to_char(rpt.create_date,'dd/MM/yyyy') as dv_tuyenxa_ngay, \n" + 
                                        "	rpt.ktbt_thuongxuyen::text as P1,\n" + 
                                        "	rpt.ktbt_tapdung::text as P2,\n" + 
                                        "	rpt.dctg_phuhop::text as P3,\n" + 
                                        "	rpt.dctg_thuongxuyen::text as P4,\n" + 
                                        "	rpt.dctg_baoquan::text as P5,\n" + 
                                        "	rpt.hd_ncs::text as P6,\n" + 
                                        "	rpt.huong_ct::text as P7,\n" + 
                                        "	rpt.htro_dkien as P8\n" + 
                                        "from\n" + 
                                        "	dr_report_param krt\n" + 
                                        "left join kpi_v_report_comm rpt on\n" + 
                                        "	krt.nkt_id = rpt.nkt_id ";
        
        String SQL_PERSON_HOURS = "select a.per_id, p.name, case when p.sex=0 then 1 end male, case when p.sex=1 then 1 end female, \n" + 
                                  "p.agency, e.location, dm.name location_name, e.start_date, e.end_date, a.hours, e.activity from kpi_data_per a \n" + 
                                  "left join kpi_event e on a.event_id=e.id\n" + 
                                  "inner join kpi_person p on a.per_id = p.id\n" + 
                                  "left join dr_area dm on dm.tinh_id = e.location_id \n" + 
                                  "left join kpi_data_hdr hdr on a.data_id = hdr.id \n" + 
                                  "where 1=1 \n";                                  
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        CallableStatement csmt = null;        
        ResultSet rs = null;
        FSearch bean = (FSearch)seed;
        int dataType = bean.getDataType();
        try {
            if (dataType==Constant.KPI_DATA_DIS || dataType==Constant.KPI_DATA_LIST_DIS) {
                csmt = cnn.prepareCall("{call report_param(?)}");
                csmt.setString(1, SQL_REPORT);
                csmt.execute(); 
                
                if (mapParam.get("locationId")!=null) {
                    int locationId = Integer.parseInt(mapParam.get("locationId"));
                    SQL_REPORT_DIS += " AND a.location_id='"+locationId+"'"; 
                }
                
                SQL_REPORT_DIS += " ORDER BY a.location_id, a.qhu_id, a.nkt_maso";
                
                SQL_REPORT_DIS_COMMUNE += " order by kpr.location_id, a.nkt_id";
                prstm = prepareStatement(cnn, SQL_REPORT_DIS, null);
            } else if (dataType==Constant.KPI_DATA_DIS_COMMUNE) {
                csmt = cnn.prepareCall("{call report_param(?)}");
                csmt.setString(1, SQL_REPORT);
                csmt.execute(); 
                
                if (mapParam.get("locationId")!=null) {
                    int locationId = Integer.parseInt(mapParam.get("locationId"));
                    SQL_REPORT_DIS_COMMUNE += " AND krt.location_id='"+locationId+"'"; 
                }                    
                
                SQL_REPORT_DIS_COMMUNE += " order by krt.location_id, krt.qhu_id, krt.nkt_id, rpt.create_date desc";
                prstm = prepareStatement(cnn, SQL_REPORT_DIS_COMMUNE, null);
            } else {
                if (dataType==Constant.KPI_DATA_LIST_PERSON_HOURS) {
                    if (mapParam.get("locationId")!=null) {
                        int locationId = Integer.parseInt(mapParam.get("locationId"));
                        SQL_PERSON_HOURS += " AND p.location_id="+locationId; 
                    }                    

                    if (mapParam.get("name")!=null) {
                        String perName = mapParam.get("name");
                        SQL_PERSON_HOURS += " AND UPPER(p.name) like '%" + perName.toUpperCase() + "%'";
                    }
                    
                    if (mapParam.get("title")!=null) {
                        String title = mapParam.get("title");
                        SQL_PERSON_HOURS += " AND UPPER(p.title) like '%" + title.toUpperCase() + "%'";
                    }
                    
                    if (mapParam.get("agency")!=null) {
                        String agency = mapParam.get("agency");
                        SQL_PERSON_HOURS += " AND UPPER(p.agency) like '%" + agency.toUpperCase() + "%'";
                    }
                    
                    if (mapParam.get("address")!=null) {
                        String address = mapParam.get("address");
                        SQL_PERSON_HOURS += " AND UPPER(p.address) like '%" + address.toUpperCase() + "%'";
                    }
                    
                    if (mapParam.get("sex")!=null) {
                        int sex = Integer.parseInt(mapParam.get("sex"));
                        SQL_PERSON_HOURS += " AND p.sex="+sex; 
                    }
                      
                    if (mapParam.get("objId")!=null) {
                        int objId = Integer.parseInt(mapParam.get("objId"));
                        SQL_PERSON_HOURS += " AND hdr.obj_id="+objId; 
                    }
                    
                    if (mapParam.get("eventId")!=null) {
                        int eventId = Integer.parseInt(mapParam.get("eventId"));
                        SQL_PERSON_HOURS += " AND a.event_id="+eventId; 
                    }
                    
                    if (mapParam.get("createFrom")!=null) {
                        String createFrom = mapParam.get("createFrom");
                        SQL_PERSON_HOURS += " AND a.create_date >= '"+bean.stringToSqlDate(createFrom)+"'";
                    }
                    
                    if (mapParam.get("createTo")!=null) {
                        String createTo = mapParam.get("createTo");  
                        SQL_PERSON_HOURS += " AND a.create_date <= '"+bean.stringToSqlDate(createTo)+"'";
                    }
                
                    SQL_PERSON_HOURS += " AND (a.hours <> 0 OR a.hours = 0) ORDER BY p.location_id, p.name, e.start_date DESC";
                    prstm = prepareStatement(cnn, SQL_PERSON_HOURS, null);
                } else {
                    prstm = prepareStatement(cnn, SQL_REPORT, params);        
                } 
            }
            
            rs = prstm.executeQuery();
            beans = new FBeans();            
            int i = 0;           
            while ((rs != null) && (rs.next())) {
                i++;
                bean = new FSearch();
                if (dataType==Constant.KPI_DATA_VALUE)          
                    bean = getInforKpiValue(rs);
                else if (dataType==Constant.KPI_DATA_DIS)       
                    bean = getInformationDis(rs);
                else if (dataType==Constant.KPI_DATA_DIS_COMMUNE)
                    bean = getInformationDisCommune(rs);
                else if (dataType==Constant.KPI_DATA_PERSON)    
                    bean = getInforKpiPerson(rs);
                else if (dataType==Constant.KPI_DATA_HOURS)
                    bean = getInforKpiSP(rs);
                else if (dataType==Constant.KPI_DATA_EVENT)
                    bean = getInforKpiEvent(rs);
                else if (dataType==Constant.KPI_DATA_LIST_DIS)                    
                    bean = getInformationDis(rs);
                else if (dataType==Constant.KPI_DATA_LIST_PERSON_HOURS)
                    bean = getInforPersonHours(rs);
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }

    public FSearch getInformation(ResultSet rs, boolean full) throws EException {
        final String LOCATION = "->getInformation()";
        FSearch bean = new FSearch();
        try {
            bean.setId(rs.getInt(NKT_ID));
            bean.setNkt(rs.getString(NKT_TEN));
            bean.setMa(rs.getString(NKT_MA));
            bean.setMaSo(rs.getString(NKT_MASO));
            bean.setSex(rs.getInt(NKT_SEX));
            bean.setTrangthai(rs.getString(NKT_TRANGTHAI));
            bean.setCmnd(rs.getString(NKT_CMND));
            bean.setPhoneNumber(rs.getString(NKT_DIENTHOAI));
            bean.setSoNha(rs.getString(NKT_SONHA));
            bean.setThonTo(rs.getString(NKT_THONTO));
            bean.setChatdocDaCam(rs.getInt(NKT_DACAM));            
            
            int DinhDang_NS = rs.getInt(NKT_DINHDANG_NS)==0?3:rs.getInt(NKT_DINHDANG_NS);            
            Date dateBirthDay = rs.getDate(NKT_NGAYSINH);
            if (dateBirthDay != null)
                bean.setNgaySinh(bean.dateToString(dateBirthDay, AppConfigs.DATE_TYPES[DinhDang_NS - 1]));
            
            bean.setTinhId(rs.getInt(NKT_ID_TINH));
            bean.setTinhName(rs.getString(TINH_NAME));
            bean.setDieuKienId(rs.getInt(NKT_ID_DIEUKIEN));
            bean.setTrinhDoId(rs.getInt(NKT_TRINHDO_ID));
            
            bean.setMucDo(rs.getInt(NKT_CAPDO_ID));
            bean.setChucVuHT(rs.getString(NKT_CHUC_VU_HT));
            //bean.setTdChuyenMon(rs.getInt(NKT_TD_CHUYENMON));
            bean.setTtHonNhanId(rs.getInt(NKT_TT_HONNHAN));
            bean.setNgheNghiepHT(rs.getInt(NKT_NGHE_NGHIEP_HT));            
            bean.setNguonNuocId(rs.getInt(NKT_NGUONNUOC_CHUHO));
            bean.setDanTocId(rs.getInt(NKT_DANTOC_ID));
            bean.setDanTocName(rs.getString("dantoc_name"));
            
            bean.setNhaOId(rs.getInt(NKT_NHAO_CHUHO));
            bean.setChuanDoan(rs.getString(NKT_CHUANDOAN));            
            bean.setPhoneNumber(rs.getString(NKT_DIENTHOAI));
            bean.setLastupdate(bean.dateToString(rs.getDate(NKT_DATE_LAST_UPDATE)));
            bean.setDonViCt(rs.getString(NKT_DONVI_NQL));
            bean.setChuhoName(rs.getString(NKT_TEN_CHUHO));
            bean.setChuhoNamSinh(rs.getString(NKT_NAMSINH_CHUHO));
            bean.setStatusId(rs.getInt(NKT_TRANGTHAI));
            
            // NCS
            bean.setNcsTen(rs.getString(NKT_TEN_NCS));
            bean.setNcsNamSinh(rs.getString(NKT_NAMSINH_NCS));
            bean.setNcsQuanHe(rs.getInt(NKT_QUANHE_NCS));
            bean.setNcsDienThoai(rs.getString("sdt_ncs"));
            bean.setNcsGioiTinh(rs.getInt("gioitinh_ncs"));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FSearch getInformationDis(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FSearch bean = new FSearch();
        try {
            
            // Location
            bean.setTinhId(rs.getInt("location_id"));
            bean.setTinhName(rs.getString("location_name"));
            
            // NKT
            bean.setLastupdate(rs.getString("create_date"));
            bean.setId(rs.getInt("nkt_id"));
            bean.setMa(rs.getString("nkt_stt"));
            bean.setNkt(rs.getString("nkt_ten"));            
            bean.setMaSo(rs.getString("nkt_maso"));
            bean.setNamSinh(rs.getString("nkt_namsinh"));
            bean.setNgaySinh(rs.getString("nkt_ngaysinh"));
            bean.setCmnd(rs.getString("nkt_cmnd"));
            bean.setPhoneNumber(rs.getString("nkt_sdt"));
            bean.setDanTocName(rs.getString("nkt_dantoc"));
            bean.setGioiTinh(rs.getString("nkt_gioitinh"));
            bean.setDaCam(rs.getString("nkt_dacam"));
            bean.setNgheNghiep(rs.getString("nkt_nnghiep"));
            bean.setTrangthai(rs.getString("nkt_ttrang_hso")); 
            bean.setDuAnHT(rs.getString("nkt_duan"));
            bean.setNgayDongHS(rs.getString("nkt_ngaydong_hs"));
            bean.setLydoDongHS(rs.getString("nkt_lydo_donghs"));
            bean.setNguoiDongHS(rs.getString("nkt_nguoi_donghs"));
            bean.setSoNha(rs.getString("nkt_diachi"));
            bean.setDuAnHT(rs.getString("nkt_duan"));
                        
            // NCS            
            bean.setNcsTen(rs.getString("ncs_ten"));
            bean.setNcsNamSinh(rs.getString("ncs_namsinh"));
            bean.setNcsQuanHeName(rs.getString("ncs_qhe_nkt"));
            bean.setNcsDienThoai(rs.getString("ncs_dthoai"));
            bean.setNcsGioiTinhName(rs.getString("ncs_gioitinh"));
            
            // Dang-Tat
            bean.setDTatNgayCapNhat(rs.getString("dtat_createdate"));
            bean.setDTatName(rs.getString("dtat_ten"));
            bean.setDTatNgayTaiKham(rs.getString("dtat_ngay_kham"));
            bean.setDTatDiaDiemKham(rs.getString("dtat_ddiem_kham"));
            bean.setDTatTDiemKT(rs.getString("dtat_tdiem_ktat"));
            bean.setDTatNguyenNhan(rs.getString("dtat_nnhan_ktat"));
            bean.setDTatMucDo(rs.getString("dtat_mdo_ktat"));            
            
            // Nhu-Cau - Ho-Tro
            bean.setNcauCreateDate(rs.getString("ncau_createdate"));
            bean.setNcauName(rs.getString("ncau_ten"));
            bean.setNcauDungCuKhac(rs.getString("ncau_dungcu_khac"));
            bean.setHtroCreateDate(rs.getString("htro_createdate"));
            bean.setHtroName(rs.getString("htro_ten"));
            
            bean.setHtroTgianNhan(rs.getString("htro_tgian_nhan"));
            bean.setNguonHoTro(rs.getString("htro_nguon_htro"));
            bean.setKnChiTraName(rs.getString("htro_kn_chitra"));
            bean.setTheBhyteName(rs.getString("htro_the_bhyt"));
            bean.setSdTheName(rs.getString("htro_sd_bhyt"));
            bean.setSdThePhcnName(rs.getString("htro_the_phcn"));
            bean.setMtieuGdinh(rs.getString("htro_mtieu_gdinh"));
            bean.setMtieuDtri(rs.getString("htro_mtieu_dtri"));
            bean.setCtVltl(rs.getString("htro_ct_vltl"));
            bean.setCtHdtl(rs.getString("htro_ct_hdtl"));
            bean.setCtAntl(rs.getString("htro_ct_antl"));
            bean.setMdoPtdl(rs.getString("htro_mdo_dlpt"));
            bean.setMdoHlong(rs.getString("htro_mdo_hlong"));
            bean.setDungCuKhac(rs.getString("htro_dungcu_khac"));
            
            bean.setPhcnCanThiep(rs.getString("htro_phcn_canthiep"));
            bean.setPhcnDungCu(rs.getString("htro_phcn_dungcu"));
            bean.setPhcnDungCuKhac(rs.getString("htro_phcn_dc_khac"));
            bean.setMoTaDCTG(rs.getString("htro_dungcu_mota"));
            
            //createCell(row, cot++, beanC.getPhcnDungCu(), wb, csLeft);
            //createCell(row, cot++, beanC.getPhcnDungCuKhac(), wb, csLeft);
            //createCell(row, cot++, beanC.getMoTaDCTG(), wb, csLeft);
            
            bean.setHtNhaO(rs.getString("htro_tcan_nhao"));
            bean.setHtNgay(rs.getString("htro_phcn_ngay"));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FSearch getInformationDisCommune(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FSearch bean = new FSearch();
        try {
            
            // Location
            bean.setTinhId(rs.getInt("location_id"));
            bean.setTinhName(rs.getString("location_name"));
            bean.setHuyenName(rs.getString("qhu_name"));
            
            // NKT
            bean.setLastupdate(rs.getString("create_date"));
            bean.setId(rs.getInt("nkt_id"));
            bean.setMa(rs.getString("nkt_stt"));
            bean.setNkt(rs.getString("nkt_ten"));            
            bean.setMaSo(rs.getString("nkt_maso"));
            bean.setNamSinh(rs.getString("nkt_namsinh"));
            bean.setNgaySinh(rs.getString("nkt_ngaysinh"));
            bean.setCmnd(rs.getString("nkt_cmnd"));
            bean.setPhoneNumber(rs.getString("nkt_sdt"));
            bean.setDanTocName(rs.getString("nkt_dantoc"));
            bean.setGioiTinh(rs.getString("nkt_gioitinh"));
            bean.setDaCam(rs.getString("nkt_dacam"));
            bean.setNgheNghiep(rs.getString("nkt_nnghiep"));
            bean.setTrangthai(rs.getString("nkt_ttrang_hso"));
            bean.setNgayDongHS(rs.getString("nkt_ngaydong_hs"));
            bean.setLydoDongHS(rs.getString("nkt_lydo_donghs"));
            bean.setNguoiDongHS(rs.getString("nkt_nguoi_donghs"));
            bean.setSoNha(rs.getString("nkt_diachi"));
            bean.setDuAnHT(rs.getString("nkt_duan"));
                        
            // NCS            
            bean.setNcsTen(rs.getString("ncs_ten"));
            bean.setNcsNamSinh(rs.getString("ncs_namsinh"));
            bean.setNcsQuanHeName(rs.getString("ncs_qhe_nkt"));
            bean.setNcsDienThoai(rs.getString("ncs_dthoai"));
            bean.setNcsGioiTinhName(rs.getString("ncs_gioitinh"));
            
            // Dang-Tat
            bean.setDTatNgayCapNhat(rs.getString("dtat_createdate"));
            bean.setDTatName(rs.getString("dtat_ten"));
            bean.setDTatNgayTaiKham(rs.getString("dtat_ngay_kham"));
            bean.setDTatDiaDiemKham(rs.getString("dtat_ddiem_kham"));
            bean.setDTatTinhTrang(rs.getString("dtat_ttrang_ktat"));
            bean.setDTatTDiemKT(rs.getString("dtat_tdiem_ktat"));
            bean.setDTatNguyenNhan(rs.getString("dtat_nnhan_ktat"));
            bean.setDTatMucDo(rs.getString("dtat_mdo_ktat"));            
            
            // Nhu-Cau - Ho-Tro
            bean.setNcauCreateDate(rs.getString("ncau_createdate"));
            bean.setNcauName(rs.getString("ncau_ten"));
            bean.setNcauDungCuKhac(rs.getString("ncau_dungcu_khac"));
            bean.setHtroCreateDate(rs.getString("htro_createdate"));
            bean.setHtroName(rs.getString("htro_ten"));
            
            bean.setHtroTgianNhan(rs.getString("htro_tgian_nhan"));
            bean.setNguonHoTro(rs.getString("htro_nguon_htro"));
            bean.setKnChiTraName(rs.getString("htro_kn_chitra"));
            bean.setTheBhyteName(rs.getString("htro_the_bhyt"));
            bean.setSdTheName(rs.getString("htro_sd_bhyt"));
            bean.setSdThePhcnName(rs.getString("htro_the_phcn"));
            bean.setMtieuGdinh(rs.getString("htro_mtieu_gdinh"));
            bean.setMtieuDtri(rs.getString("htro_mtieu_dtri"));
            bean.setCtVltl(rs.getString("htro_ct_vltl"));
            bean.setCtHdtl(rs.getString("htro_ct_hdtl"));
            bean.setCtAntl(rs.getString("htro_ct_antl"));
            bean.setMdoPtdl(rs.getString("htro_mdo_dlpt"));
            bean.setMdoHlong(rs.getString("htro_mdo_hlong"));
            bean.setDungCuKhac(rs.getString("htro_dungcu_khac"));
            
            bean.setPhcnCanThiep(rs.getString("htro_phcn_canthiep"));
            bean.setPhcnDungCu(rs.getString("htro_phcn_dungcu"));
            bean.setPhcnDungCuKhac(rs.getString("htro_phcn_dc_khac"));
            bean.setMoTaDCTG(rs.getString("htro_dungcu_mota"));
            
            
            bean.setHtNhaO(rs.getString("htro_nhao"));
            bean.setHtCTVS(rs.getString("htro_ctvs"));
            bean.setHtNgay(rs.getString("htro_phcn_ngay"));
            
            // Commune
            bean.setCommCreateDate(rs.getString("dv_tuyenxa_ngay"));
            bean.setCommP1(rs.getString("P1")!=null?rs.getString("P1"):null);
            bean.setCommP2(rs.getString("P2")!=null?rs.getString("P2"):null);
            bean.setCommP3(rs.getString("P3")!=null?rs.getString("P3"):null);
            bean.setCommP4(rs.getString("P4")!=null?rs.getString("P4"):null);
            bean.setCommP5(rs.getString("P5")!=null?rs.getString("P5"):null);
            bean.setCommP6(rs.getString("P6")!=null?rs.getString("P6"):null);
            bean.setCommP7(rs.getString("P7")!=null?rs.getString("P7"):null);
            bean.setCommP8(rs.getString("P1")!=null?rs.getString("P8"):null);
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FSearch getInforKpiValue(ResultSet rs) throws EException {
        final String LOCATION = "->getInforKpiValue()";
        FSearch bean = new FSearch();
        try {
            int i=0;
            bean.setDtlId(rs.getInt("id"));
            bean.setHdrId(rs.getInt("data_id"));
            bean.setObjId(rs.getInt("obj_id"));
            bean.setIndId(rs.getInt("ind_id"));            
            bean.setCreateDate(bean.dateToString(rs.getDate("create_date")));
            bean.setUserName(rs.getString("username")); 
            bean.setUserFullName(rs.getString("fullname"));
            bean.setLocationId(rs.getInt("location_id"));
            bean.setLocationName(rs.getString("location"));              
            bean.setPeriod(rs.getString("period"));
            bean.setTarget(rs.getInt("target"));
            bean.setActual(rs.getInt("actual"));
            
            
            bean.setObjId(rs.getInt("obj_id"));
            bean.setObjDesc(rs.getString("obj_desc"));
            bean.setIndId(rs.getInt("ind_id"));
            bean.setIndDesc(rs.getString("ind_desc"));
            bean.setId(rs.getInt(KPI_DATA_DTL_ID));
            //bean.setDataId(rs.getInt(KPI_DATA_DTL_DATA_ID));
            bean.setCreateDate(bean.dateToString(rs.getDate(KPI_DATA_DTL_CREATE_DATE)));
            //bean.setModifyDate(bean.dateToString(rs.getDate(KPI_DATA_DTL_MODIFY_DATE)));
            //bean.setUserId(rs.getInt(KPI_DATA_DTL_USER_ID));
            //bean.setFullName(rs.getString(USERS_FULLNAME));
            bean.setLocationId(rs.getInt(KPI_DATA_DTL_LOCATION_ID));
            //bean.setLocation(rs.getString(KPI_DATA_DTL_LOCATION));
            bean.setPeriodType(rs.getInt("period_type"));
            bean.setMonth(rs.getInt(KPI_DATA_DTL_MONTH));
            bean.setQuarter(rs.getInt(KPI_DATA_DTL_QUARTER));
            bean.setYear(rs.getInt(KPI_DATA_DTL_YEAR));
            bean.setActivity(rs.getString(KPI_DATA_DTL_ACTIVITY));
            bean.setTime(rs.getString(KPI_DATA_DTL_TIME));
            bean.setActual(rs.getInt(KPI_DATA_DTL_ACTUAL));
            bean.setTarget(rs.getInt(KPI_DATA_DTL_TARGET));
            
            bean.setTw(rs.getInt("tw"));
            bean.setTtp(rs.getInt("ttp"));
            bean.setQhu(rs.getInt("qhu"));
            bean.setPxa(rs.getInt("pxa"));
            
            /*
            bean.setTargetTw(rs.getInt("target_tw"));
            bean.setTargetTtp(rs.getInt("target_ttp"));
            bean.setTargetQhu(rs.getInt("target_qhu"));
            bean.setTargetPxa(rs.getInt("target_pxa"));
            
            bean.setTargetM(rs.getInt(KPI_DATA_DTL_TARGET_M));
            bean.setTargetQ(rs.getInt(KPI_DATA_DTL_TARGET_Q));
            bean.setTargetY(rs.getInt(KPI_DATA_DTL_TARGET_Y));
            
            bean.setAccM(rs.getInt(KPI_DATA_DTL_ACC_M));
            bean.setAccQ(rs.getInt(KPI_DATA_DTL_ACC_Q));
            bean.setAccY(rs.getInt(KPI_DATA_DTL_ACC_Y));
            */
            
            bean.setNote(rs.getString(KPI_DATA_DTL_NOTE));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public FSearch getInforKpiPerson(ResultSet rs) throws EException {
        final String LOCATION = "->getInforKpiValue()";
        FSearch bean = new FSearch();        
        try {
            bean.setCreateDate(bean.dateToString(rs.getDate("create_date")));
            bean.setUserName(rs.getString("username"));
            bean.setUserFullName(rs.getString("fullname"));
            bean.setLocationName(rs.getString("location_name"));           
            bean.setPerCode(rs.getString("code"));
            bean.setPerName(rs.getString("name"));
            bean.setPerSex(rs.getInt("sex"));
            bean.setPerAgency(rs.getString("agency"));
            bean.setPerTitle(rs.getString("title"));
            bean.setPerAddress(rs.getString("address"));
            bean.setPerContact(rs.getString("contact"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FSearch getInforKpiSP(ResultSet rs) throws EException {
        final String LOCATION = "->getInforKpiValue()";
        FSearch bean = new FSearch();        
        try {
            bean.setCreateDate(bean.dateToString(rs.getDate("create_date")));
            bean.setUserName(rs.getString("username"));
            bean.setUserFullName(rs.getString("fullname"));
            bean.setLocationName(rs.getString("location_name"));           
            bean.setPerCode(rs.getString("code"));
            bean.setPerName(rs.getString("name"));
            bean.setPerSex(rs.getInt("sex"));
            bean.setPerAgency(rs.getString("agency"));
            bean.setPerTitle(rs.getString("title"));
            bean.setPerAddress(rs.getString("address"));
            bean.setPerContact(rs.getString("contact"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FSearch getInforKpiEvent(ResultSet rs) throws EException {
        final String LOCATION = "->getInforKpiValue()";
        FSearch bean = new FSearch();        
        try {
            bean.setEventCode(rs.getString("code"));
            bean.setLocationName(rs.getString("location_name"));
            bean.setEventActivity(rs.getString("activity"));
            bean.setEventLocation(rs.getString("location"));
            bean.setEventStartDate(bean.dateToString(rs.getDate("start_date")));
            bean.setEventEndDate(bean.dateToString(rs.getDate("end_date")));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FReportKpiData getReportKpiPerson(ResultSet rs) throws EException {
        final String LOCATION = "->getInforKpiValue()";
        FReportKpiData bean = new FReportKpiData();        
        try {            
            bean.setPerId(rs.getInt("id"));
            bean.setCreateDate(bean.dateToString(rs.getDate("create_date")));
            bean.setUserName(rs.getString("username"));
            bean.setUserFullName(rs.getString("fullname"));
            bean.setLocationName(rs.getString("location_name"));           
            bean.setPerCode(rs.getString("code"));
            bean.setPerName(rs.getString("name"));
            bean.setPerSex(rs.getInt("sex"));
            bean.setPerAgency(rs.getString("agency"));
            bean.setPerTitle(rs.getString("title"));
            bean.setPerAddress(rs.getString("address"));
            bean.setPerContact(rs.getString("contact"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FSearch bean = (FSearch)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getMa());
            params.add(bean.getMaSo());
            params.add(bean.getNkt());
            params.add(bean.getCmnd());
            params.add(bean.getSex());
            params.add(bean.stringToSqlDate(bean.getNgaySinh()));
            params.add(bean.getSoNha());
            params.add(bean.getTinhId());
            params.add(bean.getDieuKienId());
            params.add(bean.getChuanDoan());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FSearch getInforPersonHours(ResultSet rs) throws EException {
        final String LOCATION = "->getInforPersonHours()";
        FSearch bean = new FSearch();        
        try {
            bean.setId(rs.getInt("per_id"));
            bean.setPerName(rs.getString("name"));                       
            bean.setPerMale(rs.getInt("male"));
            bean.setPerFemale(rs.getInt("female"));
            bean.setPerAgency(rs.getString("agency"));
            bean.setEventLocation(rs.getString("location"));
            bean.setLocationName(rs.getString("location_name"));
            bean.setEventStartDate(bean.dateToString(rs.getDate("start_date")));
            bean.setEventEndDate(bean.dateToString(rs.getDate("end_date")));
            bean.setPerHours(rs.getInt("hours"));
            bean.setEventActivity(rs.getString("activity"));            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public int checkRank(Connection cnn, int nkt_id, String str_check) throws EException, SQLException {        
        final String LOCATION = this.toString() + "getCheckRank()";
        PreparedStatement prstm = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            cstmt = cnn.prepareCall("{call get_quanlyca(?,?)}");
            cstmt.setInt(1, nkt_id);
            cstmt.setString(2, str_check);            
            rs = cstmt.executeQuery();
            while (rs!= null && rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return result;
    }
}
