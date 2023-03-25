package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DDanhGia;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanhGia;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BDanhGia {
    DDanhGia dao = new DDanhGia();

    public boolean delete(int ids) throws EException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.delete(conn, ids);
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
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            if(!dao.check1to6From6To12(conn,seed)){
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
        final String LOCATION = this + "->update()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
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

    public FBeans getAllByNktId(int idNKT, int ky, int nam) throws EException, 
                                                                   SQLException {
        final String LOCATION = this + "->getAllByIdSupport()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getAllByIdNkt(conn, idNKT, ky, nam);
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
    
    public FDanhGia getDanhGia_Yte(int idNKT, int ky, int nam) throws EException, 
                                                                   SQLException {
        final String LOCATION = this + "->getAllByIdSupport()";
        FDanhGia result = new FDanhGia();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getDanhGia_Yte_ByIdNkt(conn, idNKT, ky, nam);
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

    public FDanhGia getDanhGia_KT_XH(int idNKT, int ky, int nam) throws EException, 
                                                                   SQLException {
        final String LOCATION = this + "->getAllByIdSupport()";
        FDanhGia result = new FDanhGia();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getDanhGia_KinhTe_XaHoi_ByIdNkt(conn, idNKT, ky, nam);
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
    
    public FDanhGia getDanhGia_GiaoDuc(int idNKT, int ky, int nam) throws EException, 
                                                                   SQLException {
        final String LOCATION = this + "->getAllByIdSupport()";
        FDanhGia result = new FDanhGia();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getDanhGia_GiaoDuc_ByIdNkt(conn, idNKT, ky, nam);
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

    public FDanhGia getById(int id) throws EException, SQLException {
        final String LOCATION = this + "->getById()";
        FDanhGia result = new FDanhGia();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getById(conn, id);
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
