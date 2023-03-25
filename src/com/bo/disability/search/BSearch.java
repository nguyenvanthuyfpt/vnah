package com.bo.disability.search;


import com.dao.connection.DBConnector;
import com.dao.disability.search.DSearch;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;


public class BSearch {
    DSearch dao = new DSearch();
    
    // Quan ly ca
    public FBeans getDisability(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getAll()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getAll(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public FBeans getAll(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getAll()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getAll(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    // Get Kpi Value    
    public FBeans getKpiValue(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getKpiValue()";
        FBeans beans = null;
        Connection cnn = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            beans = dao.getKpiValue(cnn, seed);
            DBConnector.endTransaction(cnn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }
    
    // Get Kpi Person
    public FBeans getKpiPerson(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getKpiPerson()";
        FBeans beans = null;
        Connection cnn = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            beans = dao.getKpiPerson(cnn, seed);
            DBConnector.endTransaction(cnn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }

    public FBeans getReportAll(FSeed seed, String SQL_REPORT, List params, Map<String, String> mapParam) throws EException, SQLException {
        final String LOCATION = this + "->getReport()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getReportAll(conn,seed,SQL_REPORT,params,mapParam);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
}
