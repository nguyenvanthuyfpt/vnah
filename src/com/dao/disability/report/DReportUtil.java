package com.dao.disability.report;


import com.dao.disability.DSqlReportUtil;
import com.dao.disability.categorys.DDonvi;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DReportUtil extends DSqlReportUtil{

    public String getAreaIds(Connection cnn, int areaId) throws EException {
        String strArea = String.valueOf(areaId);
        DTinh daoTinh = new DTinh();
        strArea = daoTinh.getMembers(cnn, areaId ,"");
        return strArea;
    }
    
    public String getNguyenNhanIds(Connection cnn, int nguyennhanId) throws EException {
        String strNguyenNhan = String.valueOf(nguyennhanId);
        DDonvi daoDonvi = new DDonvi();
        strNguyenNhan = daoDonvi.getMembers(cnn, nguyennhanId);
        return strNguyenNhan;
    }
    
    // Count NKT by Param
    public int countDisabilityByParam(Connection cnn, int sex, int dacam, int fromOld, int toOld, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByParam()";
        DTinh daoTinh = new DTinh();
        String areas = daoTinh.getMembers(cnn, areaId ,"");
        int total = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT;
        try {
            List params = new ArrayList();
            if(sex >-1){
                params.add(sex);
                SQL += "AND dr_disabilitypeople.sex=? ";
            }
            
            if(dacam >-1){
                params.add(dacam);
                SQL += "AND dr_disabilitypeople.dacam=? ";
            }
            
            if(fromOld >-1){
                params.add(fromOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>= ? ";
            }
            
            if(toOld >-1){
                params.add(toOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh) < ? ";
            }
            
            if(areas!=null){
                if(areaId!=99)
                    SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();            
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
    
    // Count NKT by DieuKien
     public int countDisabilityByDieuKienKT(Connection cnn, int dieukienId, int areaId) throws EException {
         final String LOCATION = this.toString() + "countNKTByDienKien()";
         int total = 0;
         PreparedStatement prstm = null;
         ResultSet rs = null;
         String SQL   = SQL_REPORT_COUNT_NKT;
         try {
             List params = new ArrayList();
             
             if(dieukienId >0){
                 params.add(dieukienId);
                 SQL += "AND dr_disabilitypeople.id_dieukien = ? ";
             }
                 
             if(areaId>-1){
                 SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
             }
             
             prstm = prepareStatement(cnn, SQL, params);
             rs = prstm.executeQuery();
             while (rs != null && rs.next()) {
                 total = rs.getInt("tong");
             }
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
             closeResultSet(rs);
             closePreparedStatement(prstm);
         }
         return total;
     }
     
    // Count Disability by TT Hon Nhan
     public int countDisabilityByTTrHonNhan(Connection cnn, int ttHonNhan, int areaId) throws EException {
         final String LOCATION = this.toString() + "countNKTByTTHonNhan()";    
         int total = 0;
         PreparedStatement prstm = null;
         ResultSet rs = null;
         String SQL   = SQL_REPORT_COUNT_NKT;
         try {
             List params = new ArrayList();
             
             if(ttHonNhan >0){
                 params.add(ttHonNhan);
                 SQL += "AND dr_disabilitypeople.tt_honnhan = ? ";
             }
                 
             if(areaId>-1){
                 SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
             }
             
             prstm = prepareStatement(cnn, SQL, params);
             rs = prstm.executeQuery();
             while (rs != null && rs.next()) {
                 total = rs.getInt("tong");
             }
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
             closeResultSet(rs);
             closePreparedStatement(prstm);
         }
         return total;
     }
     
     // Count NKT by Trinh Do
     public int countDisabilityByTrinhDoId(Connection cnn, int trinhdo_id, int areaId) throws EException {
         final String LOCATION = this.toString() + "countNKTByTrinhDo()";
         int total = 0;
         PreparedStatement prstm = null;
         ResultSet rs = null;
         String SQL   = SQL_REPORT_COUNT_NKT;
         try {
             List params = new ArrayList();
             
             if(trinhdo_id >0){
                 params.add(trinhdo_id);
                 SQL += "AND dr_disabilitypeople.trinhdo_id = ? ";
             }
                 
             if(areaId>-1){
                 SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
             }
             
             prstm = prepareStatement(cnn, SQL, params);
             rs = prstm.executeQuery();
             while (rs != null && rs.next()) {
                 total = rs.getInt("tong");
             }
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
             closeResultSet(rs);
             closePreparedStatement(prstm);
         }
         return total;
     }
     
    // Count NKT Duoc di hoc
    public int countDisabilityDuocDiHoc(Connection cnn, int trinhdo_id, int fromOld, int toOld, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByTrinhDo()";
        int total = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT;
        try {
            List params = new ArrayList();
            
            if(trinhdo_id >0){
                params.add(trinhdo_id);
                SQL += "AND dr_disabilitypeople.trinhdo_id != ? ";
            }
            
            if(fromOld >-1){
                params.add(fromOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>= ? ";
            }
            
            if(toOld >-1){
                params.add(toOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh) < ? ";
            }
                
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
    
    // Count NKT by Chuyen Mon
    public int countDisabilityByChuyenMon(Connection cnn, String td_chuyenmon, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByTrinhDo()";
        int total = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT;
        try {
            List params = new ArrayList();
            
            if(td_chuyenmon != ""){
                params.add(td_chuyenmon);
                SQL += "AND dr_disabilitypeople.td_chuyenmon = ? ";
            }
                
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
        
    
    // Count NKT Co viec lam
    public int countDisabilityHasJobs(Connection cnn, String nghenghiepHT, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByTrinhDo()";
        int total = 0;
        String[] arrNgheNghiepId = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT;
        try {
            List params = new ArrayList();
            
            if(nghenghiepHT != ""){
                if(nghenghiepHT.indexOf(",") > -1){
                    arrNgheNghiepId = nghenghiepHT.split(",");
                    if(arrNgheNghiepId != null){
                        for(int i = 0; i< arrNgheNghiepId.length; i++){
                            params.add(arrNgheNghiepId[i]);
                            SQL += (i==0)? "AND ((dr_disabilitypeople.nghe_nghiep_ht = ? )" : "(dr_disabilitypeople.nghe_nghiep_ht = ? )";
                            SQL += (i<arrNgheNghiepId.length-1) ? " OR " : " ";
                            SQL += (i==arrNgheNghiepId.length-1) ? ") " : " ";
                        }
                    }
                } else {
                    params.add(nghenghiepHT);
                    SQL += "AND dr_disabilitypeople.nghe_nghiep_ht = ? ";
                }
            }
            
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh IN (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
    
    // Count NKT By Nghe Nghiep
    public int countDisabilityNgheNghiepHT(Connection cnn, String nghenghiepHT, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByTrinhDo()";
        int total = 0;
        String[] arrNgheNghiepId = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT;
        try {
            List params = new ArrayList();
            
            if(nghenghiepHT != ""){
                params.add(nghenghiepHT);
                SQL += "AND dr_disabilitypeople.nghe_nghiep_ht = ?";                
            }
            
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }   
     
    // Count NKT by DangTat
     public int countDisabilityByDangtatId(Connection cnn, String dangtat_ids, int areaId) throws EException {
         final String LOCATION = this.toString() + "countNKTByDangTat()";
         int total = 0;
         PreparedStatement prstm = null;
         ResultSet rs = null;
         String SQL   = SQL_REPORT_COUNT_NKT_PHANLOAI;
         try {
             List params = new ArrayList();
             
             if(dangtat_ids != "" || dangtat_ids != null){
                 params.add("%" + dangtat_ids + "%");
                 SQL += "AND dr_phanloai.dangtat_ids LIKE ? ";
             }
                 
             if(areaId>-1){
                 SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";                 
                 //and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') 
                 //and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') 
             }
             
             prstm = prepareStatement(cnn, SQL, params);
             rs = prstm.executeQuery();
             while (rs != null && rs.next()) {
                 total = rs.getInt("tong");
             }
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
             closeResultSet(rs);
             closePreparedStatement(prstm);
         }
         return total;
     }
     
    // Count NKT Da duoc Ho tro
     public int countDisabilityByHoTro(Connection cnn, int status,  String dm_hotro_ids, String tuNgay, String denNgay,  int areaId) throws EException {
         final String LOCATION = this.toString() + "countNKTByDangTat()";
         int total = 0;
         PreparedStatement prstm = null;
         ResultSet rs = null;
         String SQL   = SQL_REPORT_COUNT_NKT_HOTRO;
         try {
             List params = new ArrayList();
             
             params.add(status);
             SQL += "AND dr_support.status_id= ? ";
             
             if(dm_hotro_ids != "" || dm_hotro_ids != null){
                 params.add("%" + dm_hotro_ids + "%");
                 SQL += "AND dr_support.dm_hotro_ids LIKE ? ";
             }
             
             if(tuNgay!= "" && denNgay!= ""){
                 SQL += "AND dr_support.dateform BETWEEN to_date(" + tuNgay + ",'dd/mm/YYYY') ";
                 SQL += "AND to_date(" + denNgay + ",'dd/mm/YYYY') ";
                 SQL += "AND dr_support.dateto BETWEEN to_date(" + tuNgay + ",'dd/mm/YYYY') ";
                 SQL += "AND to_date(" + denNgay + ",'dd/mm/YYYY') ";
             }
                 
             if(areaId>-1){
                 SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
             } 
             
             prstm = prepareStatement(cnn, SQL, params);
             rs = prstm.executeQuery();
             while (rs != null && rs.next()) {
                 total = rs.getInt("tong");
             }
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
             closeResultSet(rs);
             closePreparedStatement(prstm);
         }
         return total;
     }
    
    // Count NKT Duoc Ho tro theo tuoi 
    public int countDisabilityByHoTro(Connection cnn, int status,  String dm_hotro_ids, int fromOld, int toOld, String tuNgay, String denNgay,  int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByDangTat()";
        int total = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT_HOTRO;
        try {
            List params = new ArrayList();
            
            params.add(status);
            SQL += "AND dr_support.status_id= ? ";
            
            if(dm_hotro_ids != "" || dm_hotro_ids != null){
                params.add(dm_hotro_ids);
                SQL += "AND dr_support.dm_hotro_ids LIKE ? ";
            }
            
            if(tuNgay!= "" && denNgay!= ""){
                SQL += "AND dr_support.dateform BETWEEN to_date(" + tuNgay + ",'dd/mm/YYYY') ";
                SQL += "AND to_date(" + denNgay + ",'dd/mm/YYYY') ";
                SQL += "AND dr_support.dateto BETWEEN to_date(" + tuNgay + ",'dd/mm/YYYY') ";
                SQL += "AND to_date(" + denNgay + ",'dd/mm/YYYY') ";
            }
            
            if(fromOld >-1){
                params.add(fromOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>= ? ";
            }
            
            if(toOld >-1){
                params.add(toOld);
                SQL += "AND EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh) < ? ";
            }
                
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            }
            
             //    SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support 
             //    inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and 
             //    EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 
             //    and dr_disabilitypeople.id_tinh in ("+member+") 
             //    and dr_support.status_id=0 
             //    and dr_support.dm_hotro_ids like '%#18#%' 
             //    and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') 
             //    and to_date("+denNgay+",'dd/mm/yyyy') 
             //    and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') 
             //    and to_date("+denNgay+",'dd/mm/yyyy') 
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
    
    // Count NKT By Nguyen Nhan
    public int countDisabilityByNguyenNhan(Connection cnn, int nguyennhan_id, int areaId) throws EException {
        final String LOCATION = this.toString() + "countNKTByDangTat()";
        int total = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL   = SQL_REPORT_COUNT_NKT_NGUYENNHAN;
        try {
            List params = new ArrayList();
            
            if(nguyennhan_id>-1){                
                SQL += "AND dr_phanloai.nguyennhan_id IN (" + getNguyenNhanIds(cnn, nguyennhan_id) + ") ";                
            }
                
            if(areaId>-1){
                SQL += "AND dr_disabilitypeople.id_tinh IN (" + getAreaIds(cnn, areaId) + ")";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return total;
    }
}
