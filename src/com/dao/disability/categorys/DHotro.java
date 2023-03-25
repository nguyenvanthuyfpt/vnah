package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FHotro;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DHotro extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";

        boolean result = false;
        FHotro bean = (FHotro)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(SQL_SELECT_DM_HOTRO_INFORMATION);
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

    public FHotro getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FHotro bean = new FHotro();
        bean = (FHotro)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DM_HOTRO_BY_ID);
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
        FHotro bean = (FHotro)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_DM_HOTRO_HAVECHILD);
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
            result = execute(cnn, SQL_INSERT_DM_HOTRO_HOTRO, params) > 0;
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
            FHotro bean = (FHotro)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_DM_HOTRO_HOTRO, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FHotro bean = (FHotro)seed;
        return 0 < 
            delete(cnn, TABLE_DM_HOTRO, DM_HOTRO_HOTRO_ID + EQUAL + bean.getId());
    }

    public FBeans getMultiRecords(Connection cnn, 
                                  int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DM_HOTRO);
            rs = prstm.executeQuery();
            String members = ",";
            FHotro bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || 
                    (!start && id == idDepartment)) {
                    start = true;
                    bean = new FHotro();
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
                        bean = new FHotro();
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

    public String getMembers(Connection cnn, 
                             int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMembers()";
        String members = ",";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {

            prstm = cnn.prepareStatement(SQL_SELECT_DM_HOTRO);
            rs = prstm.executeQuery();
            boolean start = false;
            int id = 0;
            while ((rs != null) && (rs.next() && id >= 0)) {
                id = rs.getInt(PARAM_01);
                if (!start && id == idDepartment) {
                    start = true;
                    if (id > 0)
                        members += id + ",";
                }
                if (start) {
                    if (members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_04);
                        if (id > 0)
                            members += id + ",";
                    } else {
                        id = -1;
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
        return members.length() > 1 ? 
               members.substring(1, members.length() - 1) : "";
    }


    public FHotro getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FHotro requiretype = new FHotro();
        try {
            requiretype.setId(rs.getInt(DM_HOTRO_HOTRO_ID));
            requiretype.setCode(rs.getString(DM_HOTRO_CODE));
            requiretype.setName(rs.getString(DM_HOTRO_NAME));
            requiretype.setParentID(rs.getInt(DM_HOTRO_PARENT_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return requiretype;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FHotro bean = (FHotro)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getParentID());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

}
