package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DEventInd;

import com.exp.EException;

import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BEventInd {
    DEventInd dao = new DEventInd();
    
    public boolean insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        boolean result = false;
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

}
