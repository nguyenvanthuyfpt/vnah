package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanhGia;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDanhGia extends DSqlDisability {


    public FBeans getAllByIdNkt(Connection cnn, int IdNKT, int ky, 
                                int nam) throws EException {
        final String LOCATION = this.toString() + "getAllByIdNkt()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDanhGia bean = new FDanhGia();
        try {
            List params = new ArrayList();
            params.add(IdNKT);
            String tuNgay = "01/01/" + nam;
            String denNgay = "01/07/" + nam;
            if (ky == 2) {
                tuNgay = "01/07/" + nam;
                denNgay = "01/01/" + (nam + 1);
            }
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            ////.println(SQL_SELECT_ALL_DANHGIA_NKT_BY_ID_NKT);
            prstm = 
                    prepareStatement(cnn, SQL_SELECT_ALL_DANHGIA_NKT_BY_ID_NKT, params);
            rs = prstm.executeQuery();

            while (rs != null && rs.next()) {
                bean = getInformation(rs);
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
    
    public FDanhGia getDanhGia_Yte_ByIdNkt(Connection cnn, int IdNKT, int ky, 
                                int nam) throws EException {
        final String LOCATION = this.toString() + "getAllByIdNkt()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDanhGia bean = new FDanhGia();
        try {
            List params = new ArrayList();
            params.add(IdNKT);
            String tuNgay = "01/01/" + nam;
            String denNgay = "01/07/" + nam;
            if (ky == 2) {
                tuNgay = "01/07/" + nam;
                denNgay = "01/01/" + (nam + 1);
            }
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            
            prstm = prepareStatement(cnn, SQL_SELECT_DANHGIA_YTE_BY_ID_NKT, params);
            rs = prstm.executeQuery();

            while (rs != null && rs.next()) {
                bean = getInformation(rs);
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }
    
    public FDanhGia getDanhGia_KinhTe_XaHoi_ByIdNkt(Connection cnn, int IdNKT, int ky, 
                                int nam) throws EException {
        final String LOCATION = this.toString() + "getAllByIdNkt()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDanhGia bean = new FDanhGia();
        try {
            List params = new ArrayList();
            params.add(IdNKT);
            String tuNgay = "01/01/" + nam;
            String denNgay = "01/07/" + nam;
            if (ky == 2) {
                tuNgay = "01/07/" + nam;
                denNgay = "01/01/" + (nam + 1);
            }
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            
            prstm = prepareStatement(cnn, SQL_SELECT_DANHGIA_KINHTE_XAHOI_BY_ID_NKT, params);
            rs = prstm.executeQuery();

            while (rs != null && rs.next()) {
                bean = getInformation(rs);
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }

    public FDanhGia getDanhGia_GiaoDuc_ByIdNkt(Connection cnn, int IdNKT, int ky, 
                                int nam) throws EException {
        final String LOCATION = this.toString() + "getAllByIdNkt()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDanhGia bean = new FDanhGia();
        try {
            List params = new ArrayList();
            params.add(IdNKT);
            String tuNgay = "01/01/" + nam;
            String denNgay = "01/07/" + nam;
            if (ky == 2) {
                tuNgay = "01/07/" + nam;
                denNgay = "01/01/" + (nam + 1);
            }
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            
            prstm = prepareStatement(cnn, SQL_SELECT_DANHGIA_GIAODUC_BY_ID_NKT, params);
            rs = prstm.executeQuery();

            while (rs != null && rs.next()) {
                bean = getInformation(rs);
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }

    public FDanhGia getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FDanhGia bean = new FDanhGia();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(id);
            prstm = 
                    prepareStatement(cnn, SQL_SELECT_ALL_DANHGIA_NKT_BY_ID, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
               bean = getInformation(rs);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }


    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            
            prstm = prepareStatement(cnn, SQL_INSERT_DANHGIA, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException, 
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FDanhGia bean = (FDanhGia)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_DANHGIA_NKT, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean delete(Connection cnn, int id) throws EException {
        boolean result = 
            delete(cnn, TABLE_DANHGIA_NKT, DANHGIA_NKT_ID + EQUAL + id) > 0;
        return result;
    }


    public FDanhGia getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDanhGia bean = new FDanhGia();
        try {
            bean.setId(rs.getInt(DANHGIA_NKT_ID));
            bean.setIdNkt(rs.getInt(DANHGIA_NKT_ID_NKT));
            if (rs.getDate(DANHGIA_NKT_DATECREATE) != null && 
                !rs.getDate(DANHGIA_NKT_DATECREATE).equals("")) {
                bean.setDateCreate(bean.dateToString(rs.getDate(DANHGIA_NKT_DATECREATE)));
            }
            bean.setStatus(rs.getInt(DANHGIA_NKT_STATUS));
            bean.setIdDanhGia(rs.getInt(DANHGIA_NKT_DANHGIA));
            bean.setTrongKy(rs.getInt(DANHGIA_NKT_TRONGKY));
            bean.setBatDau(rs.getInt(DANHGIA_NKT_BATDAU));
            bean.setYteSucKhoe(rs.getInt(DANHGIA_NKT_YTE_SUCKHOE));
            bean.setKinhteXaHoi(rs.getInt(DANHGIA_NKT_KINHTE_XAHOI));
            bean.setGiaoDuc(rs.getInt(DANHGIA_NKT_GIAODUC));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDanhGia bean = (FDanhGia)seed;
        List params = new ArrayList();
        
        try {        
            params.add(bean.getIdNkt());
            params.add(bean.stringToSqlDate(bean.getDateCreate()));            
            params.add(bean.getUserId());
            params.add(bean.getStatus());
            params.add(bean.getIdDanhGia());
            params.add(bean.getTrongKy());
            params.add(bean.getBatDau());
            params.add(bean.getYteSucKhoe());
            params.add(bean.getKinhteXaHoi());
            params.add(bean.getGiaoDuc());
            
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public boolean check1to6From6To12(Connection cnn, 
                                      FSeed seed) throws EException {
        final String LOCATION = this.toString() + "check1to6From6To12()";
        PreparedStatement prstm = null;
        FDanhGia bean = (FDanhGia)seed;
        int year = bean.getYear(bean.stringToSqlDate(bean.getDateCreate()));
        int month = bean.getMonth(bean.stringToSqlDate(bean.getDateCreate()));
        String startDate = "01/01/" + year;
        String endDate = "01/07/" + year;
        if (month > 6) {
            startDate = "01/07/" + year;
            endDate = "01/01/" + (year + 1);
        }
        ResultSet rs = null;
        boolean result = false;
        try {
            List params = new ArrayList();
            params.add(bean.stringToSqlDate(startDate)); //>=
            params.add(bean.stringToSqlDate(endDate)); //<
            params.add(bean.getIdNkt());
            prstm = 
                    prepareStatement(cnn, SQL_CHECK_INSERT_1_12_DANHGIA, params);
            rs = prstm.executeQuery();
            result = rs != null && rs.next();

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
