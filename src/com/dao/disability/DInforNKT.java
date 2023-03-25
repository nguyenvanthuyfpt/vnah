package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FInforNKT;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DInforNKT extends DSqlDisability {

    public FBeans getAllTemp(Connection cnn, FInforNKT bean) throws EException {
        final String LOCATION = this.toString() + "getAllTemp()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        FInforNKT beanT = new FInforNKT();
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            // params.add();
            prstm = prepareStatement(cnn, "SELECT * FROM dr_temp", params);
            rs = prstm.executeQuery();

            while (rs != null && rs.next()) {
                beanT = new FInforNKT();
                beanT.setId(rs.getInt(PARAM_01));
                beanT.setName(rs.getString(PARAM_02));
                beans.add(beanT);
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


    public String getSRC(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String scrString = "#";
        try {
            List params = new ArrayList();
            params.add(nktId);
            prstm =
                    prepareStatement(cnn, SELECT + STAR + FROM + TABLE_DR_TEMP_TG + WHERE + INFOR_NKT_NKT_ID + EQUAL + QUESTION,
                                     params);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                scrString += rs.getInt(PARAM_01) + "#";
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return scrString;
    }

    public boolean addBatch(Connection cnn, int[] ids, int nktId) throws SQLException, EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = true;
        PreparedStatement prstm = null;
        try {
            delete(cnn, TABLE_DR_TEMP_TG, INFOR_NKT_NKT_ID + EQUAL + nktId);
            prstm = cnn.prepareStatement(SQL_INSERT_INFOR_NKT);
            for (int i = 0; i < ids.length; i++) {
                prstm.setInt(PARAM_01, ids[i]);
                prstm.setInt(PARAM_02, nktId);
                prstm.addBatch();
            }
            result = prstm.executeBatch().length > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
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
            prstm = prepareStatement(cnn, SQL_INSERT_POPULATION, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FInforNKT bean = (FInforNKT)seed;
            List params = setParams(seed);
            //params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_POPULATION, params);
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
        boolean result = delete(cnn, TABLE_POPULATION, PPLT_ID + EQUAL + id) > 0;
        return result;
    }


    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FInforNKT bean = (FInforNKT)seed;
        List params = new ArrayList();
        try {
            // params.add(bean.getId_tinh());
            params.add(bean.getCurrentSqlDate());

        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
