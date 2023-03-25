package com.bo.disability.categorys;


import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DIndicator;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FIndicator;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;


public class BIndicator {
    DIndicator dao = new DIndicator();    
    
    public FIndicator getRecordByID(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FIndicator result = null;
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

    public FBeans getAllRecord(int id) throws EException {
        final String LOCATION = this + "->getAllRecord()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getMultiRecords(conn, id);
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
    
    public FBeans getAll(FSeed seed) throws EException {
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
    
    public String getIndIds(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->delete()";
        String result = "";
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DIndicator dao = new DIndicator();            
            result = dao.getIndIds(conn, seed);
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
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            if (!dao.isExist(conn, seed)) {
                result = dao.insert(conn, seed);
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

    public boolean update(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            if (!dao.isExist(conn, seed)) {
                result = dao.update(conn, seed);
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

    public Map<String, String> get_map_Object() throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            String sql = "select doituong_id,name from dr_doituong";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put(String.valueOf(rs.getInt(1)), rs.getString(2));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DBConnector.closeConnection(conn);
        }
        return map;
    }
    
    public FIndicator getIndicatorByParentId(int parent_id) throws EException {
      final String LOCATION = this + "->getRecordByID()";
        FIndicator result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getRecordByParentId(conn, parent_id);
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
