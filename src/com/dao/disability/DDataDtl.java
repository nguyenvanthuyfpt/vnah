package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataDtl;
import com.form.disability.FIndicatorKpi;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class DDataDtl extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataDtl bean = (FDataDtl)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "SELECT count(1) FROM kpi_data_hdr hdr, kpi_data_dtl dtl  \n" + 
                    "WHERE hdr.id=dtl.data_id AND hdr.location_id=? AND hdr.ind_id=? AND hdr.obj_id=? \n" + 
                    "AND ((?=0 AND dtl.month=? AND dtl.period=?) " +
                    "OR (?=1 AND dtl.quarter=? AND dtl.period=?)" +
                    "OR (?=2 AND dtl.year=? AND dtl.period=?)) and dtl.year=?"; 
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());        
            
            pstmt.setInt(PARAM_04, bean.getPeriod());
            pstmt.setInt(PARAM_05, bean.getMonth()); 
            pstmt.setInt(PARAM_06, bean.getPeriod());
            
            pstmt.setInt(PARAM_07, bean.getPeriod());
            pstmt.setInt(PARAM_08, bean.getQuarter());
            pstmt.setInt(PARAM_09, bean.getPeriod());
            
            pstmt.setInt(PARAM_10, bean.getPeriod());
            pstmt.setInt(PARAM_11, bean.getYear());
            pstmt.setInt(PARAM_12, bean.getPeriod());
            
            pstmt.setInt(PARAM_13, bean.getYear());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public FDataDtl getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataDtl bean = new FDataDtl();
        bean = (FDataDtl)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DATA_INDICATOR_DTL_BY_ID);
            prstm.setInt(PARAM_01, bean.getId());
            prstm.setInt(PARAM_02, bean.getObjId());
            prstm.setInt(PARAM_03, bean.getIndId());
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
    
    public FBeans getAll(Connection cnn, FSeed seed, HttpSession session) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDataDtl bean    = (FDataDtl)seed;                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        //String SQL = SQL_SELECT_DATA_INDICATOR_DTL_BY_HDR;
        
        String SQL = "SELECT hdr.obj_id, hdr.ind_id, dtl.*, users.username, users.fullname FROM kpi_data_hdr hdr, kpi_data_dtl dtl, users \n" + 
                      "WHERE hdr.id=dtl.data_id AND dtl.user_id=users.user_id ";
        String SQL_REPORT   = "";
        try {
            List params = new ArrayList();
            params.add(bean.getDataId());
            params.add(bean.getDataId());
            params.add(bean.getLocationId());
            params.add(bean.getLocationId());
            params.add(bean.getObjId());
            params.add(bean.getIndId());
            params.add(bean.getPeriod());
            
            SQL += " AND ((?=0) OR (dtl.data_id=?))";            
            SQL += " AND ((?=0) OR (dtl.location_id=?))";            
            SQL += " AND hdr.obj_id=? ";                
            SQL += " AND hdr.ind_id=? ";
            SQL += " AND dtl.period=? ";
            
            if (bean.getPeriod()>=0) {
                SQL += " ORDER BY dtl.create_date DESC, dtl.location_id, ";
                if (bean.getPeriod()==0) {
                    SQL += " CASE WHEN dtl.period=1 THEN dtl.month END DESC ";
                } else if (bean.getPeriod()==1) {
                    SQL += " CASE WHEN dtl.period=1 THEN dtl.quarter END DESC ";
                } else if (bean.getPeriod()==2) {
                    SQL += " CASE WHEN dtl.period=2 THEN dtl.year END DESC ";
                }
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            SQL_REPORT = SQL;
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }            
            int i = 0;  
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FDataDtl();
                bean = getInformation(rs);
                beans.add(bean);
            }
            
            // Store param search to Session            
            session.setAttribute("SQL_REPORT", SQL_REPORT);
            session.setAttribute("params", params);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getReportAll(Connection cnn, FSeed seed, String SQL_REPORT, List params) throws EException {
        final String LOCATION = this.toString() + "getReportAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataDtl bean = (FDataDtl)seed;        
        try {           
            prstm = prepareStatement(cnn, SQL_REPORT, params);
            rs = prstm.executeQuery();
            beans = new FBeans();            
            int i = 0;           
            while ((rs != null) && (rs.next())) {
                i++;
                bean = new FDataDtl();
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
            result = execute(cnn, SQL_INSERT_DATA_INDICATOR_DTL, params) > 0;
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
            FDataDtl bean = (FDataDtl)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_DATA_INDICATOR_DTL, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDataDtl bean = (FDataDtl)seed;
        return 0 < delete(cnn, TABLE_KPI_DATA_DTL, KPI_DATA_DTL_ID + EQUAL + bean.getId());
    }


    public FDataDtl getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataDtl dataDtl = new FDataDtl();
        try {
            dataDtl.setObjId(rs.getInt("obj_id"));
            dataDtl.setIndId(rs.getInt("ind_id"));
            dataDtl.setId(rs.getInt(KPI_DATA_DTL_ID));
            dataDtl.setDataId(rs.getInt(KPI_DATA_DTL_DATA_ID));
            dataDtl.setCreateDate(dataDtl.dateToString(rs.getDate(KPI_DATA_DTL_CREATE_DATE)));
            dataDtl.setModifyDate(dataDtl.dateToString(rs.getDate(KPI_DATA_DTL_MODIFY_DATE)));
            dataDtl.setUserId(rs.getInt(KPI_DATA_DTL_USER_ID));
            dataDtl.setFullName(rs.getString(USERS_FULLNAME));
            dataDtl.setLocationId(rs.getInt(KPI_DATA_DTL_LOCATION_ID));
            dataDtl.setLocation(rs.getString(KPI_DATA_DTL_LOCATION));
            dataDtl.setPeriod(rs.getInt(KPI_DATA_DTL_PERIOD));
            dataDtl.setMonth(rs.getInt(KPI_DATA_DTL_MONTH));
            dataDtl.setQuarter(rs.getInt(KPI_DATA_DTL_QUARTER));
            dataDtl.setYear(rs.getInt(KPI_DATA_DTL_YEAR));
            dataDtl.setActivity(rs.getString(KPI_DATA_DTL_ACTIVITY));
            dataDtl.setTime(rs.getString(KPI_DATA_DTL_TIME));
            dataDtl.setActual(rs.getInt(KPI_DATA_DTL_ACTUAL));
            dataDtl.setTarget(rs.getInt(KPI_DATA_DTL_TARGET));
            
            dataDtl.setTw(rs.getInt("tw"));
            dataDtl.setTtp(rs.getInt("ttp"));
            dataDtl.setQhu(rs.getInt("qhu"));
            dataDtl.setPxa(rs.getInt("pxa"));
            
            dataDtl.setTargetTw(rs.getInt("target_tw"));
            dataDtl.setTargetTtp(rs.getInt("target_ttp"));
            dataDtl.setTargetQhu(rs.getInt("target_qhu"));
            dataDtl.setTargetPxa(rs.getInt("target_pxa"));
            
            dataDtl.setTargetM(rs.getInt(KPI_DATA_DTL_TARGET_M));
            dataDtl.setTargetQ(rs.getInt(KPI_DATA_DTL_TARGET_Q));
            dataDtl.setTargetY(rs.getInt(KPI_DATA_DTL_TARGET_Y));
            
            dataDtl.setAccM(rs.getInt(KPI_DATA_DTL_ACC_M));
            dataDtl.setAccQ(rs.getInt(KPI_DATA_DTL_ACC_Q));
            dataDtl.setAccY(rs.getInt(KPI_DATA_DTL_ACC_Y));
            
            dataDtl.setNote(rs.getString(KPI_DATA_DTL_NOTE));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataDtl;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDataDtl bean = (FDataDtl)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getDataId());           
            params.add(bean.getUserId());
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getLocationId());
            params.add(bean.getLocation());            
            params.add(bean.getPeriod());
            params.add(bean.getMonth());
            params.add(bean.getQuarter());
            params.add(bean.getYear());
            
            params.add(bean.getActivity());
            params.add(bean.getTime());            
            params.add(bean.getActual());
            params.add(bean.getTarget());
            
            params.add(bean.getTw());
            params.add(bean.getTtp());
            params.add(bean.getQhu());
            params.add(bean.getPxa());
            
            params.add(bean.getTargetTw());
            params.add(bean.getTargetTtp());
            params.add(bean.getTargetQhu());
            params.add(bean.getTargetPxa());
            
            params.add(bean.getTargetM());
            params.add(bean.getTargetQ());
            params.add(bean.getTargetY());
            
            params.add(bean.getAccM());
            params.add(bean.getAccQ());
            params.add(bean.getAccY());            
            params.add(bean.getNote());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
