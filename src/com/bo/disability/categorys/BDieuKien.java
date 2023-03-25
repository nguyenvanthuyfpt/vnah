package com.bo.disability.categorys;


import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DDieuKien;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FDieuKien;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;


public class BDieuKien {

    public FDieuKien getRecordByID(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getRecordByID()";
        FDieuKien result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDieuKien dao = new DDieuKien();
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
            DDieuKien dao = new DDieuKien();
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

    public String getMembersDep(String members, int ruleId, long userId) throws EException {
        final String LOCATION = this + "->getMembersDep()";
        String result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDieuKien dao = new DDieuKien();
            //   result = dao.getUserByDeparmentInGroup(conn,members,ruleId,userId);
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

    public String getMembersGroup(String members, int ruleId, long userId) throws EException {
        final String LOCATION = this + "->getMembersGroup()";
        String result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDieuKien dao = new DDieuKien();
            // result = dao.getUserMemberGroup(conn,members,ruleId,userId);
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


    //    public FBeans getAllRecordByRule(FDocAssign bean,long userId)throws EException
    //      {
    //         final String LOCATION = this + "->getAllRecordByRule()";
    //         FBeans result = null;
    //         Connection conn = null;
    //         try {
    //             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
    //             DBConnector.startTransaction(conn);
    //             DDieuKien dao = new DDieuKien();
    //             result = dao.getAllRecordByRule(conn,bean,userId);
    //             DBConnector.endTransaction(conn);
    //          }
    //          catch (EException ex) {
    //             DBConnector.rollBackTransaction(conn);
    //             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
    //          }
    //          finally {
    //             DBConnector.closeConnection(conn);
    //         }
    //     return result;
    //     }


    public boolean delete(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDieuKien dao = new DDieuKien();
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

    public boolean insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DDieuKien dao = new DDieuKien();
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
            DDieuKien dao = new DDieuKien();
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

    public Map<String, String> get_map_dieukien() throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            String sql = "select dieukien_id,name from dr_dieukien";
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
}
