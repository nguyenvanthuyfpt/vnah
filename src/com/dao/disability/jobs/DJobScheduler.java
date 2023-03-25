package com.dao.disability.jobs;


import com.dao.disability.DSqlDisability;
import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.jobs.FJobScheduler;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DJobScheduler extends DSqlDisability {
    
    public FJobScheduler getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FJobScheduler bean = new FJobScheduler();
        bean = (FJobScheduler)seed;
        String SQL = "SELECT scheduler.* FROM kpi_job_scheduler scheduler WHERE scheduler.id=?";
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
    
    public FBeans getSchedulerJobs(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getSchedulerJobs()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FJobScheduler bean = new FJobScheduler();
        FBeans beans = new FBeans();
        bean = (FJobScheduler)seed;
        String SQL = "SELECT scheduler.* FROM kpi_job_scheduler scheduler WHERE scheduler.job_status=0";
        try {
            prstm = cnn.prepareStatement(SQL);
            rs = prstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FJobScheduler();
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
    
    public boolean updateScheduler(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FJobScheduler bean = (FJobScheduler)seed;
            String SQL_UPDATE_KPI_RANK = "UPDATE kpi_job_scheduler SET job_status=? WHERE id=?";
            List params = new ArrayList();
            params.add(bean.getJobStatus());
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_RANK, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;    
    }

    public FJobScheduler getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FJobScheduler bean = new FJobScheduler();
        try {
            bean.setId(rs.getInt(TABLE_KPI_JOB_SCHEDULER_ID));
            bean.setCreateDate(rs.getDate(TABLE_KPI_JOB_SCHEDULER_CREATE_DATE));
            bean.setJobCode(rs.getString(TABLE_KPI_JOB_SCHEDULER_JOB_CODE));
            bean.setJobName(rs.getString(TABLE_KPI_JOB_SCHEDULER_JOB_NAME));
            bean.setJobExec(rs.getString(TABLE_KPI_JOB_SCHEDULER_JOB_EXEC));
            bean.setJobCron(rs.getString(TABLE_KPI_JOB_SCHEDULER_JOB_CRON));
            bean.setJobStatus(rs.getInt(TABLE_KPI_JOB_SCHEDULER_JOB_STATUS));
            bean.setLocationId(rs.getInt(TABLE_KPI_JOB_SCHEDULER_JOB_LOCATION_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
}
