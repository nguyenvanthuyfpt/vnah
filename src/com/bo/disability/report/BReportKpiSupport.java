package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReportKpiSupport;

import com.exp.EException;

import com.form.FBeans;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BReportKpiSupport {
    DReportKpiSupport dao = new DReportKpiSupport();

    public FBeans getData(int locationId, String period) throws EException, SQLException {
        final String LOCATION = this + "->getData()";
        FBeans beans = new FBeans();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            beans = dao.getData(conn, locationId, period);
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
