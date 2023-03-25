package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FIndicatorKpi;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DIndicatorKpi extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FIndicatorKpi bean = (FIndicatorKpi)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_INDICATOR_INFORMATION);
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

    public FIndicatorKpi getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FIndicatorKpi bean = new FIndicatorKpi();
        bean = (FIndicatorKpi)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_INDICATOR_BY_ID);
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
    
    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FIndicatorKpi bean    = (FIndicatorKpi)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_INDICATOR_BY_PARAM;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL + ORDER_BY + KPI_INDICATOR_ID, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }            
            int i = 0;  
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FIndicatorKpi();
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FIndicatorKpi bean = (FIndicatorKpi)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_INDICATOR_HAVECHILD);
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
            result = execute(cnn, SQL_INSERT_INDICATOR, params) > 0;
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
            FIndicatorKpi bean = (FIndicatorKpi)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_INDICATOR, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FIndicatorKpi bean = (FIndicatorKpi)seed;
        return 0 < delete(cnn, TABLE_KPI_INDICATOR, KPI_INDICATOR_ID + EQUAL + bean.getId());
    }


    public FIndicatorKpi getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FIndicatorKpi indicator = new FIndicatorKpi();
        try {
            indicator.setId(rs.getInt(KPI_INDICATOR_ID));
            indicator.setParentID(rs.getInt(KPI_INDICATOR_PARENT_ID));
            indicator.setCreateDate(indicator.dateToString(rs.getDate(KPI_INDICATOR_CREATE_DATE)));
            indicator.setModifyDate(indicator.dateToString(rs.getDate(KPI_INDICATOR_MODIFY_DATE)));
            indicator.setCode(rs.getString(KPI_INDICATOR_CODE));
            indicator.setName(rs.getString(KPI_INDICATOR_NAME));
            indicator.setDescription(rs.getString(KPI_INDICATOR_DESCRIPTION));
            indicator.setBaseline(rs.getString(KPI_INDICATOR_BASELINE));
            indicator.setTargetYear(rs.getInt(KPI_INDICATOR_TARGET_YEAR));
            indicator.setTargetJustification(rs.getInt(KPI_INDICATOR_TARGET_JUSTIFICATION));
            indicator.setQ1(rs.getInt(KPI_INDICATOR_Q1));
            indicator.setQ2(rs.getInt(KPI_INDICATOR_Q2));
            indicator.setQ3(rs.getInt(KPI_INDICATOR_Q3));
            indicator.setQ4(rs.getInt(KPI_INDICATOR_Q4));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return indicator;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FIndicatorKpi bean = (FIndicatorKpi)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID()); 
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getDescription());
            
            params.add(bean.getBaseline());
            params.add(bean.getTargetYear());
            params.add(bean.getTargetJustification());
            params.add(bean.getQ1());
            params.add(bean.getQ2());
            params.add(bean.getQ3());
            params.add(bean.getQ4());
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
            prstm = cnn.prepareStatement(SQL_SELECT_INDICATOR);
            rs = prstm.executeQuery();
            String members = ",";
            FIndicatorKpi bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FIndicatorKpi();
                    bean.setId(id);
                    bean.setCode(rs.getString(PARAM_02));
                    bean.setName(rs.getString(PARAM_03));
                    bean.setParentID(rs.getInt(PARAM_04));
                    if (id > 0) {
                        members += id + ",";
                        beans.add(bean);
                    }
                }
                if (start) {
                    if (all || members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_05);
                        bean = new FIndicatorKpi();
                        bean.setId(id);
                        bean.setCode(rs.getString(PARAM_06));
                        bean.setName(rs.getString(PARAM_07));
                        bean.setParentID(rs.getInt(PARAM_08));
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
