package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FRank;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DRank extends DSqlDisability {


    public FBeans getAllByIdNkt(Connection cnn, int idNkt) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(idNkt);
            prstm = 
                    prepareStatement(cnn, SQL_SELECT_ALL_RANK_BY_ID_NKT, params);
            rs = prstm.executeQuery();
            FRank bean = null;
            while (rs != null && rs.next()) {
                bean = new FRank();
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

    public FRank getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FRank bean = new FRank();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(id);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_RANK_BY_ID, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FRank();
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

    public String getByQLCA(Connection cnn, String SQL) throws EException {
        final String LOCATION = this.toString() + "getByQLCA()";
        String result = "";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                result = rs.getString(1);
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

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_RANK_DANGTAT, params);
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
            FRank bean = (FRank)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_RANK, params);
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
        boolean result = delete(cnn, TABLE_RANK, RANK_ID + EQUAL + id) > 0;
        return result;
    }


    public FRank getInformation(ResultSet rs, int check) throws EException {
        final String LOCATION = "->getInformation()";
        FRank bean = new FRank();
        try {
            bean.setId(rs.getInt(RANK_ID));
            bean.setIdNkt(rs.getInt(RANK_ID_NKT));
            
            if (check == 0)
                bean.setFullName(rs.getString(USERS_FULLNAME));
            if (rs.getDate(RANK_DATECREATE) != null && 
                !rs.getDate(RANK_DATECREATE).equals("")) {
                bean.setCreateDate(bean.dateToString(rs.getDate(RANK_DATECREATE)));
            }
            
            bean.setReson(rs.getString(RANK_RESON));
            bean.setDanhgiaIds(rs.getString(RANK_DANHGIA_IDS));
            bean.setTochucKhac(rs.getString(RANK_TOCHUC_KHAC));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FRank bean = (FRank)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getIdNkt());
            params.add(bean.getUserId());
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.getReson());
            params.add(bean.getDanhgiaIds());
            params.add(bean.getTochucKhac());
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
        FRank bean = (FRank)seed;
        int year = bean.getYear(bean.stringToSqlDate(bean.getCreateDate()));
        int month = bean.getMonth(bean.stringToSqlDate(bean.getCreateDate()));
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
                    prepareStatement(cnn, SQL_CHECK_INSERT_1_12_TINHTRANG, params);
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
