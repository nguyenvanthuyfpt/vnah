package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReportKpi;

import com.exp.EException;

import com.form.FBeans;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class BReportKpi {
    
    DReportKpi dao = new DReportKpi();
    
    final static Logger logger  = Logger.getLogger(BReportKpi.class);
    
    public FBeans getDataReportObject(int period, int tinh_id, String parameter, int year, int extend) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportObject()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportObject(conn, period, tinh_id, parameter, year, extend);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            logger.error(ex.toString());
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataReportIndicator(int period, int tinh_id, String parameter, int year, int extend) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportIndicator()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportIndicator(conn, period, tinh_id, parameter, year, extend);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataReportSupport(int lvl, int location_id, String periodType, String strVal) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportIndicator()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportSupport(conn, lvl, location_id, periodType, strVal);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataReportInsurance(int locationId, String period) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportInsurance()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportInsurance(conn, locationId, period);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
           
    public FBeans getDataReportSupport(int locationId, String periodType, String period) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportSupport()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportSupport(conn, locationId, periodType, period);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataReportHomeVisit(int locationId, String periodType, String period) throws EException, SQLException {
        final String LOCATION = this + "->getDataReportHomeVisit()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReportHomeVisit(conn, locationId, periodType, period);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataDisExport(int lvl, int locationId, int duAnId, int statusId,
                                       String createDateFrom, String createDateTo, 
                                       String dvuDateFrom, String dvuDateTo,
                                       String tdgDateFrom, String tdgDateTo,
                                       String dmcDateFrom, String dmcDateTo) throws EException, SQLException {
        final String LOCATION = this + "->getDataDisExport()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataDisExport(conn, lvl, locationId, duAnId, statusId,
                                  createDateFrom, createDateTo,
                                  dvuDateFrom, dvuDateTo, 
                                  tdgDateFrom, tdgDateTo, 
                                  dmcDateFrom, dmcDateTo);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataDisExport2020(int lvl, int locationId,                                        
                                       String createDateFrom, String createDateTo, 
                                       String dvuDateFrom, String dvuDateTo,
                                       String tdgDateFrom, String tdgDateTo,
                                       String dmcDateFrom, String dmcDateTo) throws EException, SQLException {
        final String LOCATION = this + "->getDataDisExport()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataDisExport2020(conn, lvl, locationId, 
                                            createDateFrom, createDateTo, 
                                            dvuDateFrom, dvuDateTo, 
                                            tdgDateFrom, tdgDateTo, 
                                            dmcDateFrom, dmcDateTo);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataDisCommuneSummary (int lvl, int locationId, String val) throws EException, SQLException {
        final String LOCATION = this + "->getDataDisCommuneSummary()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataDisCommuneSummary(conn, lvl, locationId, val);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
    
    public FBeans getDataDisCommuneDetail (int lvl, int locationId, String val) throws EException, SQLException {
        final String LOCATION = this + "->getDataDisCommuneDetail()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataDisCommuneDetail(conn,lvl, locationId, val);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return beans;
    }
}
