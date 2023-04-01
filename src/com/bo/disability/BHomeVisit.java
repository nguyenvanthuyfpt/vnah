package com.bo.disability;

import com.dao.connection.DBConnector;

import com.dao.disability.DHomeVisit;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;

import com.form.disability.FHomeVisit;
import com.form.disability.FSupport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BHomeVisit {

    DHomeVisit dao = new DHomeVisit();

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
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public FHomeVisit getHomeVisit(int nktId, String createdAt) throws EException, SQLException {
        final String LOCATION = this + "->getById()";
        FHomeVisit result = new FHomeVisit();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getHomeVisit(conn, nktId, createdAt);
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
    
    public boolean deleteHomeVisit(int supportId) throws EException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.delete(conn, supportId);
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
