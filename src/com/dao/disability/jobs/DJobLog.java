package com.dao.disability.jobs;


import com.dao.disability.DSqlDisability;
import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FDangTat;
import com.form.disability.jobs.FJobLog;
import com.form.disability.jobs.FJobScheduler;
import com.lib.AppConfigs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DJobLog extends DSqlDisability {
    
    public FJobLog getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FJobLog bean = (FJobLog)seed;
        String SQL = "SELECT log.* FROM kpi_job_log log WHERE log.id=?";
        try {
            prstm = cnn.prepareStatement(SQL);
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
    
    public FBeans getLogsByJobCode(Connection cnn, FSeed seed) throws EException,
                                                      SQLException {
        final String LOCATION = this.toString() + "getLogsByJobCode()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FBeans beans = null;
        FJobLog bean = (FJobLog)seed;
        String SQL = "SELECT l.* FROM kpi_job_log l, kpi_job_scheduler s\n" + 
                      "WHERE l.job_id = s.id \n" + 
                      "AND s.job_code = ? AND l.end_exec IS NOT NULL \n" + 
                      "ORDER BY l.end_exec DESC LIMIT 1";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setString(PARAM_01, bean.getJobCode());
            //prstm.setInt(PARAM_02, bean.getLocationId());
            rs = prstm.executeQuery();
            beans = new FBeans();
            
            while (rs != null && rs.next()) {
                bean = new FJobLog();
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
        
    public int insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {           
            List params = setParams(seed);
            ps = prepareStatement(cnn, SQL_INSERT_KPI_JOB_LOG, params);
            rs = ps.executeQuery();
            if (rs!=null && rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = new ArrayList();
            FJobLog bean = (FJobLog)seed;
            String SQL_UPDATE_KPI_JOB_LOG = "UPDATE kpi_job_log SET end_exec=?, msg_exec=? WHERE id=?";
            params.add(bean.getEndExec());
            params.add(bean.getMsgExec());
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_JOB_LOG, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FJobLog bean = (FJobLog)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getStartExec());
            params.add(bean.getEndExec());
            params.add(bean.getJobId());
            params.add(bean.getMsgExec());
            params.add(bean.getLocationId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

    public FJobLog getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FJobLog bean = new FJobLog();
        try {
            bean.setId(rs.getInt(TABLE_KPI_JOB_LOG_ID));
            bean.setStartExec(rs.getTimestamp(TABLE_KPI_JOB_LOG_START_EXEC));
            bean.setEndExec(rs.getTimestamp(TABLE_KPI_JOB_LOG_END_EXEC));
            bean.setJobId(rs.getInt(TABLE_KPI_JOB_LOG_JOB_ID));
            bean.setMsgExec(rs.getString(TABLE_KPI_JOB_LOG_MSG_EXEC));
            bean.setLocationId(rs.getInt(TABLE_KPI_JOB_LOG_LOCATION_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
}
