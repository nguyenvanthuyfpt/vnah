package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FPhanLoai;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DPhanLoai extends DSqlDisability {


    public FBeans getAllByIdNkt(Connection cnn, int idNkt) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(idNkt);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_PHANLOAI_BY_ID_NKT, params);
            rs = prstm.executeQuery();
            FPhanLoai bean = null;
            while (rs != null && rs.next()) {
                bean = new FPhanLoai();
                bean = getInformation(rs, 0);
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

    public FPhanLoai getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FPhanLoai bean = new FPhanLoai();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(id);
            prstm =
                    prepareStatement(cnn, SQL_SELECT_ALL_PHANLOAI_BY_ID, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FPhanLoai();
                bean = getInformation(rs, id);

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
    
    public FPhanLoai getPhanLoaiByNktID(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FPhanLoai bean = new FPhanLoai();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            String sql = " SELECT get_dangtat(pl.id, pl.id_nkt) dangtat, dm.name mucdo, pl.reson as tinhtrang_kt, dn.name as nguyennhan \n" + 
                        " FROM dr_phanloai pl, dr_mucdo dm, dr_nguyennhan dn \n" + 
                        " WHERE pl.id_nkt = ? \n" + 
                        " AND pl.mucdo_id = dm.mucdo_id " +
                        " AND pl.nguyennhan_id = dn.nguyennhan_id " +
                        " ORDER BY pl.id DESC LIMIT 1 OFFSET 0";
            List params = new ArrayList();
            params.add(nktId);
            prstm = prepareStatement(cnn, sql, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FPhanLoai();
                bean.setDangTat(rs.getString("dangtat"));
                bean.setMucDoKT(rs.getString("mucdo"));
                bean.setReson(rs.getString("tinhtrang_kt"));
                bean.setNguyenNhan(rs.getString("nguyennhan"));
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
            prstm = prepareStatement(cnn, SQL_INSERT_PHANLOAI_DANGTAT, params);
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
            FPhanLoai bean = (FPhanLoai)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_PHANLOAI, params);
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
            delete(cnn, TABLE_PHANLOAI, PHANLOAI_ID + EQUAL + id) > 0;
        return result;
    }
    
    public int getRowExport(Connection cnn, int nktId) throws SQLException,
                                                             EException {
        final String LOCATION = this.toString() + "getRowExport";
        int result = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {            
            List params = new ArrayList();
            params.add(nktId);
            params.add(nktId);
            prstm = prepareStatement(cnn, SQL_GET_ROW_EXPORT, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }


    public FPhanLoai getInformation(ResultSet rs,
                                    int check) throws EException {
        final String LOCATION = "->getInformation()";
        FPhanLoai bean = new FPhanLoai();
        try {
            bean.setId(rs.getInt(PHANLOAI_ID));
            bean.setIdNkt(rs.getInt(PHANLOAI_ID_NKT));
            if (check == 0)
                bean.setFullName(rs.getString(USERS_FULLNAME));

            if (rs.getDate(PHANLOAI_DATECREATE) != null &&
                !rs.getDate(PHANLOAI_DATECREATE).equals("")) {
                bean.setDateCreate(bean.dateToString(rs.getDate(PHANLOAI_DATECREATE)));
            }

            bean.setReson(rs.getString(PHANLOAI_RESON));
            bean.setDangTatIds(rs.getString(PHANLOAI_DANGTAT_IDS));
            bean.setNguyenNhanId(rs.getInt(PHANLOAI_NGUYENNHAN_ID));
            bean.setVanDongKhac(rs.getString(PHANLOAI_VANDONG_KHAC));
            bean.setKhuyetTatKhac(rs.getString(PHANLOAI_KHUYETTAT_KHAC));
            bean.setNguyenNhanKhac(rs.getString(PHANLOAI_NGUYENNHAN_KHAC));
            bean.setCapdoKT(rs.getInt(PHANLOAI_CAPDO));
            bean.setThoiDiemKT(rs.getInt(PHANLOAI_THOIDIEM));

            if (rs.getDate(PHANLOAI_THOIDIEM_TAIKHAM) != null &&
                !rs.getDate(PHANLOAI_THOIDIEM_TAIKHAM).equals("")) {
                bean.setThoiDiemTK(bean.dateToString(rs.getDate(PHANLOAI_THOIDIEM_TAIKHAM)));
            }

            bean.setDiaDiemKham(rs.getInt(PHANLOAI_DIADIEM));
            bean.setDangTat(rs.getString("dangtat"));
            bean.setNguyenNhan(rs.getString("nnhan_name"));
            bean.setMucDoKT(rs.getString("mucdo_name"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FPhanLoai bean = (FPhanLoai)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getIdNkt());
            params.add(bean.getUserId());
            params.add(bean.stringToSqlDate(bean.getDateCreate()));
            params.add(bean.getReson());
            params.add(bean.getDangTatIds());
            params.add(bean.getNguyenNhanId());
            params.add(bean.getVanDongKhac());
            params.add(bean.getKhuyetTatKhac());
            params.add(bean.getNguyenNhanKhac());
            params.add(bean.getCapdoKT());
            params.add(bean.getThoiDiemKT());
            params.add(bean.stringToSqlDate("01/"+bean.getThoiDiemTK()));
            params.add(bean.getDiaDiemKham());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
