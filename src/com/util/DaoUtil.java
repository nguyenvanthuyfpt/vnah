package com.util;

import com.dao.disability.DSqlDisability;
import com.dao.disability.report.DReportKpi;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.report.FReportKpi;

import com.lib.AppConfigs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DaoUtil {

    final static Logger logger = Logger.getLogger(DaoUtil.class);

    public static int get_rc(ResultSet rs) throws Exception {
        rs.last();
        int size = rs.getRow();
        rs.beforeFirst();
        return size;
    }

    public static String print_error(Exception ex) {
        StringBuffer sb = new StringBuffer(ex.getMessage());
        sb.append("\r\nThông tin l?i chi ti?t :");
        StackTraceElement[] ste_arr = ex.getStackTrace();
        String msg;
        for (int i = 0; i < ste_arr.length; i++) {
            msg = ste_arr[i].toString();
            if (msg.startsWith("com.action.disabilitypeople."))
                sb.append("\r\n" +
                        ste_arr[i].toString());
        }
        return sb.toString();
    }

    public static void execSchedulerJobs(Connection cnn,
                                         String job_exec) throws EException {
        String LOCATION = "~~>execSchedulerJobs()";
        CallableStatement state = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            logger.debug("BEGIN::execSchedulerJobs");
            logger.debug("job_exec:: "+job_exec);
            state = cnn.prepareCall(job_exec);
            state.execute();
            logger.debug("END::execSchedulerJobs");
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
        } finally {
            try {
                state.close();
                cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
