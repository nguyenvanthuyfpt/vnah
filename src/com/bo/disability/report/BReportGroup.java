package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReportGroupData;

import com.exp.EException;

import com.form.FBeans;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BReportGroup {
    DReportGroupData dao = new DReportGroupData();

    public FBeans getData(int tinh_id, String report_type, int from, int to, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getData()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getData(conn, tinh_id, report_type, from, to, qlc);
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
    
    public FBeans getDataReport14(int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getDataReport14()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReport14(conn, tinh_id, level, qlc);
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
    
    public FBeans getDataReport15(int tinh_id, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getDataReport15()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReport15(conn, tinh_id, qlc);
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
    
    public FBeans getDataReport16(int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getDataReport16()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReport16(conn, tinh_id, level, qlc);
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
    
    public FBeans getDataReport171819(int tinh_id, int level, int from, int to, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getDataReport171819()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReport171819(conn, tinh_id, level, from, to, qlc);
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
    
    public FBeans getDataReport20(int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this + "->getDataReport20()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataReport20(conn, tinh_id, level, qlc);
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
