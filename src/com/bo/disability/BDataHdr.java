package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DDataHdr;
import com.dao.disability.categorys.DIndicator;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FDataHdr;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BDataHdr {
    DDataHdr dao = new DDataHdr();
    
    public int count(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        int retval = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            retval = dao.count(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }
    
    public FDataHdr getRecordByIndicator(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FDataHdr result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getRecordByIndicator(conn, seed);
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
    
    public FDataHdr getRecordById(int hdrId) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FDataHdr result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getDataHdrById(conn, hdrId);
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

    public boolean delete(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DIndicator dao = new DIndicator();
            if (!dao.haveChild(conn, seed)) {
                result = dao.delete(conn, seed);
            }
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

    public int insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        int retval = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            //if (!dao.isExists(conn, seed)) {
                retval = dao.insert(conn, seed);  
            //}
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }

    public boolean update(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
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
    
    public int getHdrId(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        int retval = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            if (dao.isExistsHdr(conn, seed)) {
                retval = dao.getHdrId(conn, seed);
            }
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }
    
    public int getRefId(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRefId()";
        int retval = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            if (dao.isExistsRef(conn, seed)) {
                retval = dao.getRefId(conn, seed);
            }
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }
    
    public boolean isExistsDtl(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getHdrId()";
        boolean retval = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            retval = dao.isExistsDtl(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }
}
