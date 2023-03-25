package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReportQLC;

import com.exp.EException;

import com.form.FBeans;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BReportQLC {
    DReportQLC dao = new DReportQLC();

    public FBeans getDataDetail(int tinh_id, int level, String ky_bc, String type) throws EException, SQLException {
        final String LOCATION = this + "->getData()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataDetail(conn, tinh_id, level, ky_bc, type);
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

    public FBeans getDataCollect(int tinh_id, String ky_bc, String loai_dl, String type) throws EException, SQLException {
        final String LOCATION = this + "->getData()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getDataCollect(conn, tinh_id, ky_bc, loai_dl, type);
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
