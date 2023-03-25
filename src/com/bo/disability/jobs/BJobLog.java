package com.bo.disability.jobs;


import com.dao.connection.DBConnector;
import com.dao.disability.jobs.DJobLog;
import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.jobs.FJobLog;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.SQLException;

public class BJobLog {
    
    DJobLog dao = new DJobLog();
    
    public FJobLog getRecordByID(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FJobLog result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getRecordByID(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public FBeans getLogsByJobCode(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FBeans beans = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            beans = dao.getLogsByJobCode(conn, seed);
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
    
    public int insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.insert(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public boolean update(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->update()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.update(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
}
