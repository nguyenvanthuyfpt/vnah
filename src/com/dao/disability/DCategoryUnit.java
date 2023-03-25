package com.dao.disability;


import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FCategoryUnit;
import com.form.disability.FUnit;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DCategoryUnit extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = 
                    prepareStatement(cnn, SQL_INSERT_INTO_TABLE_DR_CATEGORY_UNIT, 
                                     params);
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
            FUnit bean = (FUnit)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = 
                    prepareStatement(cnn, SQL_UPDATE_INTO_TABLE_DR_CATEGORY_UNIT, 
                                     params);
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
        return delete(cnn, TABLE_DR_CATEGORY_UNIT, 
                      DR_CATEGORY_UNIT_ID + EQUAL + id) > 0;
    }

    public FCategoryUnit getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FCategoryUnit bean = new FCategoryUnit();
        try {
            bean.setId(rs.getInt(DR_CATEGORY_UNIT_ID));
            bean.setName(rs.getString(DR_CATEGORY_UNIT_NAME));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FCategoryUnit bean = (FCategoryUnit)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getName());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FCategoryUnit getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FCategoryUnit bean = new FCategoryUnit();
        try {
            prstm = 
                    cnn.prepareStatement(SQL_SELECT_CATEGORY_UNIT_BY_ID);
            prstm.setInt(PARAM_01, id);
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FCategoryUnit();
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

}
