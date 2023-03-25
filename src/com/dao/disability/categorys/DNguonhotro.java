package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FNguonhotro;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DNguonhotro extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FNguonhotro bean = (FNguonhotro)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_NGUONHOTRO_INFORMATION);
            pstmt.setString(PARAM_01, bean.getCode());
            pstmt.setInt(PARAM_02, bean.getId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public FNguonhotro getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FNguonhotro bean = new FNguonhotro();
        bean = (FNguonhotro)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_NGUONHOTRO_BY_ID);
            prstm.setInt(PARAM_01, bean.getId());
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FNguonhotro bean = (FNguonhotro)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_NGUONHOTRO_HAVECHILD);
            pstmt.setInt(PARAM_01, bean.getId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_NGUONHOTRO, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FNguonhotro bean = (FNguonhotro)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_NGUONHOTRO, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FNguonhotro bean = (FNguonhotro)seed;
        return 0 < delete(cnn, TABLE_NGUONHOTRO, NGUONHOTRO_ID + EQUAL + bean.getId());
    }


    public FNguonhotro getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FNguonhotro nguonhotro = new FNguonhotro();
        try {
            nguonhotro.setId(rs.getInt(NGUONHOTRO_ID));
            nguonhotro.setCode(rs.getString(NGUONHOTRO_CODE));
            nguonhotro.setName(rs.getString(NGUONHOTRO_NAME));
            nguonhotro.setDescription(rs.getString(NGUONHOTRO_DESCRIPTION));
            nguonhotro.setParentID(rs.getInt(NGUONHOTRO_PARENT_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return nguonhotro;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FNguonhotro bean = (FNguonhotro)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID());
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getDescription());
            params.add(1);
            params.add(bean.getCurrentSqlDate());            
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FBeans getMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_NGUONHOTRO);
            rs = prstm.executeQuery();
            String members = ",";
            FNguonhotro bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FNguonhotro();
                    bean.setId(id);
                    bean.setName(rs.getString(PARAM_02));
                    bean.setParentID(rs.getInt(PARAM_03));
                    if (id > 0) {
                        members += id + ",";
                        beans.add(bean);
                    }
                }
                if (start) {
                    if (all || members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_04);
                        bean = new FNguonhotro();
                        bean.setId(id);
                        bean.setName(rs.getString(PARAM_05));
                        bean.setParentID(rs.getInt(PARAM_06));
                        if (id > 0) {
                            members += id + ",";
                            beans.add(bean);
                        } else {
                            all = true;
                        }
                    } else if (!all) {
                        members = null;
                        start = false;
                    }
                }
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

}
