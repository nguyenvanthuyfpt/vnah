package com.dao.disability;


import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FDataPer;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDataPer extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_DATA_PERSON, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        FDataPer bean = (FDataPer)seed;
        try {
            List params = new ArrayList();
            params.add(bean.getResult());
            params.add(bean.getHours());
            params.add(bean.getDataId());
            params.add(bean.getPerId());
            params.add(bean.getEventId());
            result = execute(cnn, SQL_UPDATE_DATA_PERSON, params) > 0;            
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean check(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        FDataPer bean = (FDataPer)seed;
        ResultSet rs = null;
        int retval = 0;
        try {
            List params = new ArrayList();
            params.add(bean.getDataId());
            params.add(bean.getPerId());
            params.add(bean.getEventId());
            PreparedStatement ps = prepareStatement(cnn, SQL_CHECK_DATA_PERSON, params);            
            rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getInt(PARAM_01);                
            }
            
            result = (retval==0)?false:true;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    } 

    public FDataPer getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataPer dataPer = new FDataPer();
        try {
            int i = 1;
            dataPer.setCreateDate(rs.getString(i++));            
            dataPer.setDataId(rs.getInt(i++));
            dataPer.setPerId(rs.getInt(i++));
            dataPer.setEventId(rs.getInt(i++));
            dataPer.setResult(rs.getInt(i++));
            dataPer.setHours(rs.getInt(i++));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataPer;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDataPer bean = (FDataPer)seed;
        List params = new ArrayList();
        try {
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.getDataId());
            params.add(bean.getPerId());
            params.add(bean.getEventId());
            params.add(bean.getResult());
            params.add(bean.getHours());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDataPer bean = (FDataPer)seed;
        return 0 <
            delete(cnn, TABLE_KPI_DATA_PER, " data_id= "+bean.getDataId()+             
                                            " AND per_id=" + bean.getPerId()+
                                            " AND event_id = "+bean.getEventId());
    }
    
    public int getHours(Connection cnn, int eventId) throws EException {
        final String LOCATION = this.toString() + INSERT;
        int result = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT date_part('days',end_date-start_date)::integer*8 FROM kpi_event WHERE id=?";
            List params = new ArrayList();
            params.add(eventId);            
            ps = prepareStatement(cnn, sql, params);            
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(PARAM_01);                
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result==0?8:result;
    }
}
