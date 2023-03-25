package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.reportSystem.FReportSystem;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BReport {
    public FBeans getMutiData(FReportSystem bean, FBeans beanTs) throws EException {
        final String LOCATION = this.toString() + "~>getDataToExport()";
        Connection cnn = null;
        DReport dao = null;
        FBeans beans = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DReport();
            beans = dao.getMutiData(cnn, bean, beanTs);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }

}
